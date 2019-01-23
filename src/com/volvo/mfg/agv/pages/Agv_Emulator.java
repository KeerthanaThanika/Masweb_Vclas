package com.volvo.mfg.agv.pages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;

import com.volvo.automation.commonutilities.DB_Connectivity;
import com.volvo.automation.commonutilities.ExcelUtils;
import com.volvo.automation.commonutilities.QueryInput;
import com.volvo.mfg.StepDefinition.DriverSuite;

public class Agv_Emulator extends DriverSuite implements QueryInput {

	String sheetName = "Vclas_Assignments";

	public HashMap<String, String> tdrow;
	int i;

	// Excel class object to access the function
	ExcelUtils excelUtils = new ExcelUtils();
	Vclas_Assignment VclasAssign = new Vclas_Assignment();
	LoginPage lp = new LoginPage();
	MasWeb_Home mwHome =new MasWeb_Home();
	
	public void First_Subtask_AssignmentList_Delivery() throws InterruptedException {

		// Getting the Room Value
		String stRoomValue = tdrow.get("Select_Work_Area_Room");
		String objectId = tdrow.get("Object");
		String strFullTaskId = tdrow.get("Full_Task_1");
		String strEmptyTaskId = tdrow.get("Empty_Task_1");
		String strFullType = tdrow.get("Shortage_Type_Expected").split(",")[0];
		String strEmptyType = tdrow.get("Shortage_Type_Expected").split(",")[1];
		String strStatusVerify[] = { "Active", "Planned" };

		

		// Selection Work Area
		VclasAssign.Vclas_Assignment_SelectWorkArea("Room", stRoomValue.split(",")[0]);

		reportStep(" Selected Work Area: #B" + stRoomValue.split(",")[0] + "#C", "Info", false);
		logger.info(" Selected Work Area: #B" + stRoomValue.split(",")[0] + "#C");

		// picking up First SubTask of AGVFKOLL
		VclasAssign.vclas_Agv_Rackpickup(strFullType, objectId);

		// delivering first subtask of AGVFKOLL
		VclasAssign.Vclas_Agv_Delivery("drop", strFullType);

	}

	public void Emulator_delivery() {

		// clicking on the home button
		anyClick("Home Button", By.xpath(prop.getProperty("Vclas_Home.Home.Button")));

		// Clicking Administration menu
		anyClick("Administration Menu", By.xpath(prop.getProperty("Vclas_Home.Administration.Button")));

		// clicking on emulator menu
		anyClick("Emulator Menu", By.xpath(prop.getProperty("Vclas.Administration.Emulator")));

		// Verifying Emulator screen display
		if (verifyElementExistReturn(
				By.xpath(prop.getProperty("Vclas.Administration.Emulator.ReceiveMessage.Button"))) == true) {

			// Clicking on Receive button
			anyClick("Receive Message Button",
					By.xpath(prop.getProperty("Vclas.Administration.Emulator.ReceiveMessage.Button")));

			// verifying the displayed table
			if (verifyElementExist("Emulator table",
					By.xpath(prop.getProperty("Vclas.Administration.Emulator.Table"))) == true) {

			} else {
				reportStep("Emulator table doesn't displayed", "fail", false);
			}

		} else {
			reportStep("Emulator screen doesn't displayed", "fail", true);
		}

	}

	/*
	 * select id from TRANSPORT_ORDER where task_ID=688688;--1267163 select * from
	 * TRANSPORT_ASSIGNMENT where TRANSPORT_ORDER=1267163 and PARENT_ID!=0;--1267172
	 * select * from TRANSPORT_ASSIGNMENT where TRANSPORT_ORDER=1267172 and
	 * PARENT_ID!=0 and status='STATUS_LEDIG';
	 */

	public void AGV_Emulator_OrderNo_Fetch() throws InterruptedException, SQLException {
		String sheetName = "Vclas_Assignments";

		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		String Full_Task_Id = tdrow.get("Full_Task_1");
		String Empty_Task_Id = tdrow.get("Empty_Task_1");

		// vclas connection details
		String ClassName = prop.getProperty(Environment + ".VCLAS.ORACLE.ClassName");
		String ConnectionString = prop.getProperty(Environment + ".VCLAS.ORACLE.Connection.String");
		String UserName = prop.getProperty(Environment + ".VCLAS.ORACLE.User.Name");
		String Password = prop.getProperty(Environment + ".VCLAS.ORACLE.User.Password");

		// vclas deletion
		DB_Connectivity db = new DB_Connectivity();

		ResultSet rsFullRackId = db.Connect_DB(ClassName, ConnectionString, UserName, Password,
				AGV_Emulator_TransportOrder.replace("#TASKID#", tdrow.get("Full_Task_1")));

		if (rsFullRackId.next()) {
			int TO_FullRack_Id = rsFullRackId.getInt(1);
			System.out.println("Transport Order FullRack Id:" + TO_FullRack_Id);
			ResultSet rsFullTaskOrderNO = db.Connect_DB(ClassName, ConnectionString, UserName, Password,
					AGV_Emulator_Transport_Assignment_FullTask.replace("#ID#", String.valueOf(TO_FullRack_Id)));
			if (rsFullTaskOrderNO.next()) {
				int FullTaskOrderNo = rsFullTaskOrderNO.getInt(1);
				System.out.println("FullTaskOrderNo: " + FullTaskOrderNo);
				excelUtils.excelUpdateValue("Vclas_Assignments", "Full_Task_OrderNo", refTestDataName,
						String.valueOf(FullTaskOrderNo));
				rsFullTaskOrderNO.close();
			}
			rsFullRackId.close();
		}

		ResultSet rsEmptyRackId = db.Connect_DB(ClassName, ConnectionString, UserName, Password,
				AGV_Emulator_TransportOrder.replace("#TASKID#", tdrow.get("Empty_Task_1")));

		if (rsEmptyRackId.next()) {
			int TO_EmptyRack_Id = rsEmptyRackId.getInt(1);
			System.out.println("Transport Order EmptyRack Id':" + TO_EmptyRack_Id);
			ResultSet rsEmptyTaskOrderNo = db.Connect_DB(ClassName, ConnectionString, UserName, Password,
					AGV_Emulator_Transport_Assignemnt_EmptyTask.replace("#ID#", String.valueOf(TO_EmptyRack_Id)));
			if (rsEmptyTaskOrderNo.next()) {
				int EmptyTaskOrderNo = rsEmptyTaskOrderNo.getInt(1);
				System.out.println("EmptyTaskOrdeNo:" + EmptyTaskOrderNo);
				excelUtils.excelUpdateValue("Vclas_Assignments", "Empty_Task_OrderNo", refTestDataName,
						String.valueOf(EmptyTaskOrderNo));
				rsEmptyTaskOrderNo.close();

			}
			rsEmptyRackId.close();
		}

		// reportStep("Prerequisite Data Cleaning for Tugger Flow: " + flowName + " -
		// Completed", "pass", false);

	}
	
	public void AGV_PlanningStatus_delivery() throws InterruptedException {

		tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_Assignments");
		// Getting the Room Value
		String stRoomValue = tdrow.get("Select_Work_Area_Room");
		String objectId = tdrow.get("Object");
		String strFullTaskId = tdrow.get("Full_Task_1");
		String strEmptyTaskId = tdrow.get("Empty_Task_1");
		String strFullType = tdrow.get("Shortage_Type_Expected").split(",")[0];
		String strEmptyType = tdrow.get("Shortage_Type_Expected").split(",")[1];
		String strStatusVerify[] = { "Active", "Planned" };

		// Submitting the Full Racks
		

			// Selection Work Area
			VclasAssign.Vclas_Assignment_SelectWorkArea("Room", stRoomValue.split(",")[0]);

			reportStep(" Selected Work Area: #B" + stRoomValue.split(",")[0] + "#C", "Info", false);

			// Navigation to Shortage
			anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));

			// Waiting to verify the screen is displayed
			waitForElement(driver, By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text")), 5);

			//
			VclasAssign.Vclas_Shortage_Search(objectId, "", "");

			// Verify the Task id in Shortage screen
			VclasAssign.Vclas_Shortage_TypeVerify(objectId, strFullTaskId, strFullType, strStatusVerify[0]);
			reportStep("Object Id: #B" + objectId+ "#C  Rack Type: #B  AGVFKOLL  #C delivery  from Assignment List screen initiated","Info", false);
			
			// Navigation to Assignment List tab
				anyClick("Assignment list Screen", By.xpath(prop.getProperty("Vclas_Home.Assignment.List.Button")));
				driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Assignment.List.Button"))).get(0).click();
				
		  //picking up the AGVFkoll from assignment list screen
				String rackName = prop.getProperty("Vclas_home.AssignmentList.Column.Type").replace("&Value", objectId)
						.replace("&1Value", strFullType);
				System.out.println("rack name" + rackName);
				
				driver.findElements(By.xpath(rackName)).get(0).click();
				
				reportStep("#B" + objectId + "and the racktype:" + strFullType+ " has been picked up for deliver from Assignment List screen #C", "pass", true);
				
		//cliking the drop button from AL Screen
				
				driver.findElements(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Drop.icon"))).get(0).click();
						
			
		/*	// Rack Pick/Drop in the Assignment List
			vclas_Agv_Rackpickup(strFullType, objectId);
			// Nodes_Conveyor_Data_Pickup();

			if (i == 0) {
				// Deliver depends on Drop id of Conveyer
				Vclas_Agv_Delivery("drop", strFullType);
			} else {
				// Deliver depends on Pick id of Conveyer
				Vclas_Agv_Delivery("pick", strFullType);

				String rackName = prop.getProperty("Vclas_home.AssignmentList.Column.Type").replace("&Value", objectId)
						.replace("&1Value", strFullType);
				Thread.sleep(2000);
				if (driver.findElements(By.xpath(rackName)).size() > 0) {
					driver.findElements(By.xpath(rackName)).get(0).click();

					Vclas_Agv_Delivery("", "");
				}
			}
			reportStep(
					"Object Id: #B" + objectId
							+ "#C  Rack Type: #B AGVFKOLL #C delivery  from Assignment List screen completed",
					"Info", false);
		

		// Submitting the Empty Racks
		for (int j = 0; j <= 1; j++) {

			// Navigation to Shortage
			anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));

			Vclas_Shortage_Search(objectId, "", "");

			// Verify the Task id in Shortage screen
			Vclas_Shortage_TypeVerify(objectId, strEmptyTaskId, strEmptyType, strStatusVerify[j]);
			reportStep(
					"Object Id: #B" + objectId
							+ "#C  Rack Type: #B AGVEKOLL #C delivery  from Assignment List screen initiated",
					"Info", false);
			// Rack Pick/Drop in the Assignment List
			vclas_Agv_Rackpickup(strEmptyType, objectId);

			reportStep("---Submitting Empty Rack: #B" + strEmptyType + "#C Object Id: #B" + objectId + "#C----", "Info",
					false);
			// Deliver depends on Drop id of Conveyer
			Vclas_Agv_Delivery("", strEmptyType);

			if (j == 0) {
				// Select Room in the list
				Vclas_Assignment_SelectWorkArea("Room", stRoomValue.split(",")[j]);

				reportStep(" Selected Work Area: #B" + stRoomValue.split(",")[j] + "#C", "Info", false);
			}

		}
		reportStep(
				"Object Id: #B" + objectId
						+ "#C  Rack Type: #B AGVEKOLL #C delivery  from Assignment List screen completed",
				"Info", false);

*/	}
	

public void Agv_Picking_Multiple_Assignments() throws InterruptedException {
	
	String sheetName = "Vclas_Assignments";
	tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_Assignments");
	
	for( i=0;i<=1;i++) {
		
	mwHome.Navigate_AGV_RackChanger();

    // RacKChanger Page
    AGV_RackChanger agvRack = new AGV_RackChanger();

    FIDselection(i);
  //Enter the Search Value
    String strFID = agvRack.MachineID_Pick("A");
    mwHome.Navigate_AGV_MachineIDScan();

    // Machine Scan ID page
    AGV_MachineIDScan agvMach = new AGV_MachineIDScan();
    agvMach.MachineID_Scan_Submit(strFID);
    mwHome.Agv_Navigate_Vclas_Task();

    // Vclas task Page
    Vclas_tasks vclasTask = new Vclas_tasks();
    vclasTask.Vclas_Task_Search();
    vclasTask.Vclas_getAGVTask();
/*    System.out.println("ObjectId:"+vclasTask.ObjectArray.get(i));
    System.out.println("Full Task:"+vclasTask.FtaskArray.get(i));
    System.out.println("Empty Task:"+vclasTask.EtaskArray.get(i));
*/	}
}

public void Vclas_Picking_Multiple_Assignments() throws InterruptedException {
	
	tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_Assignments");
	// Getting the Room Value
	String stRoomValue = tdrow.get("Select_Work_Area_Room");
	//Getting Object ID from test data
	String objectId = tdrow.get("Object");
	//Getting Fulltask ID from test data
	String strFullTaskId = tdrow.get("Full_Task_1");
	String strFullType = tdrow.get("Shortage_Type_Expected").split(",")[0];
	String strStatusVerify = ("Active");
	
	// Selection Work Area
	VclasAssign.Vclas_Assignment_SelectWorkArea("Room", stRoomValue.split(",")[0]);
	reportStep(" Selected Work Area: #B" + stRoomValue.split(",")[0] + "#C", "Info", false);
	
	// Navigation to Shortage
	anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));

	
	
	//
	//VclasAssign.Vclas_Shortage_Search(objectId, "", "");

	if (driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text"))).size() > 0) {

		/*driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text"))).get(0)
				.sendKeys(Keys.LEFT_CONTROL + "a" + Keys.DELETE);*/
		Vclas_Assignment.clearByLocator(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text")));
		//clearByLocator(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text")));
		// Enter the Object Id
		driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text"))).get(0)
				.sendKeys(objectId);
		
		//WebDriverWait wait = new WebDriverWait(driver, 2);
		//wait.until(ExpectedConditions.stalenessOf(driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Button"))).get(0))); 
		waitForElement(driver, By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Button")), 5);
		retryingFindClick(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Button"))); 
	}
	
	
	
	// Verify the Task id in Shortage screen
	VclasAssign.Vclas_Shortage_TypeVerify(objectId, strFullTaskId, strFullType, strStatusVerify);
	
	// Rack Pick/Drop in the Assignment List
	VclasAssign.vclas_Agv_Rackpickup(strFullType, objectId);
	
	
}

public void FIDselection(int i) throws InterruptedException {
	
	//tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_Assignments");
	String sheetName = "AGV_RackChanger";
	tdrow=excelUtils.testCaseRetrieve(refTestDataName,sheetName);
	//int FIDvalue = Integer.parseInt(tdrow.get("FID"));
	
	//Enter Plant to select
		if (!tdrow.get("Plant").equals(""))
		{
			selectDropDownValue("Plant dropdown", By.xpath(prop.getProperty("AGV_RackChanger.Plant.DropDown")), tdrow.get("Plant"));
		}
		
		//Enter the FID value
		if (!tdrow.get("FID").split(",")[i].equals(""))
		{
			//sendKeys("FID", By.xpath(prop.getProperty("AGV_RackChanger.FID.Text")), tdrow.get("FID"));
			// Enter the data to select
			sendKeys("FID selection",
					By.xpath(prop.getProperty("AGV_RackChanger.FID.Text")), tdrow.get("FID").split(",")[i]);
			
			Thread.sleep(2000);

		}
			
		//Enter the FID value
		if (!tdrow.get("Actor").equals(""))
		{
			selectDropDownValue("Actor dropdown", By.xpath(prop.getProperty("AGV_RackChanger.Actor.DropDown")), tdrow.get("Actor"));
		}
		
		//anyClick("Search Machine ID", By.xpath(prop.getProperty("AGV_RackChanger.Display.Button")));
		driver.findElements(By.xpath(prop.getProperty("AGV_RackChanger.Display.Button"))).get(0).click();
		Thread.sleep(2000);
		
		//Verify the table is displayed 
		
		verifyElementExist("Machine ID Table Search Data", By.xpath(prop.getProperty("AGV_RackChanger.Search.Table")+"/tbody/tr[1]"));
		
		//Clearing the Memory
		tdrow.clear();
	
	
}

}
