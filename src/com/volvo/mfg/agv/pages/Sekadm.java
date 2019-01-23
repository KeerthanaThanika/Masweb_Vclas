//**************************************************************************************************
//@File Name: Sekadm
//@Description: Tugger Flow - Creation of new racks
//@Input Parameters: Sequence type,Number of racks,Reason
//@Output Parameters: Object,Lopnr
//@Created by: Priya Balasubramanian
//@Date Created: 18-Mar-2018
//@Last Modified: 
//**************************************************************************************************
package com.volvo.mfg.agv.pages;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.volvo.automation.commonutilities.DB_Connectivity;
import com.volvo.automation.commonutilities.ExcelUtils;
import com.volvo.automation.commonutilities.QueryInput;
import com.volvo.mfg.StepDefinition.DriverSuite;

public class Sekadm extends DriverSuite implements QueryInput {
	public int checkValue = 0;
	public String deletedRackNo = "", fsnr = "", Dfbopnr = "", Dlbopnr = "", Dlevnr = "", Dokollinr = "", Dcontnr = "",
			Dfsnr = "";

	BigInteger checkTime = BigInteger.ZERO;
	public HashMap<String, String> tdrow;
	// Excel class object to access the function
	ExcelUtils excelUtils = new ExcelUtils();

	public void sekadmCreate() throws InterruptedException {
		String sheetName = "vclas_sekadm";
		try {

			// retrieve data from MasWeb_Page
			// Load Test Data File
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);

			reportStep("--------SEKADM Rack Creation from : #B" + tdrow.get("Sequence") + " " + tdrow.get("Racksnr")+ "#C No. of Racks: #B" + tdrow.get("Num_racks") + "#C Initiated----", "Info", false);

			// Enter the Sequence type
			if (!tdrow.get("Sequence").equals("")) {
				sendKeys(" Sequence type", By.xpath(prop.getProperty("Vclass.sekadm.new.seq.text")),
						tdrow.get("Sequence"));
			}

			// Click Create button
			anyClick("create button", By.xpath(prop.getProperty("Vclass.sekadm.new.create.button")));

			// Verify the Page is Displayed
			verifyPageTitle(tdrow.get("Expected_Value"));

			// Enter the Racksnr

			if (!tdrow.get("Racksnr").equals("")) {
				sendKeys("Racksnr", By.xpath(prop.getProperty("Vclass.sekadm.new.create.racksnr.text")),
						tdrow.get("Racksnr"));
			}

			// Enter the Number of racks

			if (!tdrow.get("Num_racks").equals("")) {
				selectDropDownValue("Num_racks", By.xpath(prop.getProperty("Vclass.sekadm.new.create.num.dropdown")),
						tdrow.get("Num_racks"));
			}
			Thread.sleep(3000);

			// Enter the Reason

			if (!tdrow.get("Reason").equals("")) {
				sendKeys("Reason", By.xpath(prop.getProperty("Vclass.sekadm.new.create.reason.text")),
						tdrow.get("Reason"));
			}

			// storing rackNo, Lopnr,toPos and sequence type value to variables

			String rackNo = driver.findElement(By.xpath(prop.getProperty("Vclass.sekadm.new.create.racksnr.text")))
					.getAttribute("value");

			String No_of_Racks = driver.findElement(By.xpath(prop.getProperty("Vclass.sekadm.new.create.num.dropdown")))
					.getAttribute("value");

			String lopnr = driver.findElement(By.xpath(prop.getProperty("Vclass.sekadm.new.lopnr.text")))
					.getAttribute("value");
			String fPos = driver.findElement(By.xpath(prop.getProperty("Vclass.sekadm.new.stationCode.text")))
					.getAttribute("value");
			System.out.println(fPos);
			String sequenceType = driver.findElement(By.xpath(prop.getProperty("Vclass.sekadm.sequencetype.text")))
					.getAttribute("value");
			String toPos = driver.findElement(By.xpath(prop.getProperty("Vclass.sekadm.toposition.text")))
					.getAttribute("value");
			System.out.println(sequenceType);

			// Click Save Button
			anyClick("save button", By.xpath(prop.getProperty("Vclass.sekadm.new.create.Save.button")));
			Thread.sleep(3000);

			// Verifying The Message
			WebElement message = driver.findElement(By.xpath(prop.getProperty("Vclass.sekadm.msg")));
			String status = message.getText();

			if (status.equalsIgnoreCase(tdrow.get("Message"))) {
				System.out.println(" Verification status : " + status);

				reportStep("Creation of Racks - Message displayed " + status, "pass", true);

				reportStep("--------SEKADM Rack Creation from : #B" + tdrow.get("Sequence") + " " + tdrow.get("Racksnr")
						+ "#C No. of Racks: #B" + tdrow.get("Num_racks") + "#C Completed----", "Info", false);

				// Update the Values in Vclas_task and vclas assignment Excel Sheet
				excelUtils.excelUpdateValue("Vclas_tasks", "Object", refTestDataName, rackNo);
				excelUtils.excelUpdateValue("Vclas_Assignments", "Object", refTestDataName, rackNo);
				excelUtils.excelUpdateValue("Vclas_Assignments", "Lopnr", refTestDataName, lopnr);
				// excelUtils.excelUpdateValue("Multiple_Tugger", "T.o.m. löpnr",
				// refTestDataName, lopnr);
				excelUtils.excelUpdateValue("Vclas_Assignments", "Sequence_Type", refTestDataName, sequenceType);
				excelUtils.excelUpdateValue("Vclas_tasks", "PartNumber", refTestDataName, sequenceType);
				excelUtils.excelUpdateValue("Vclas_Assignments", "No_of_Racks", refTestDataName, No_of_Racks);
				excelUtils.excelUpdateValue("Vclas_tasks", "No_of_Racks", refTestDataName, No_of_Racks);
				excelUtils.excelUpdateValue("Admin_Simulator", "Sequence", refTestDataName, sequenceType);
				excelUtils.excelUpdateValue("Vclas_Assignments", "toPos", refTestDataName, toPos);

			} else {
				System.out.println(" Rack Already Exists");
				reportStep("Rack Already Exixts Expeted:" + tdrow.get("Message") + "Actual:" + status, "Fail", true);

			}
			fPos=fPos.trim();
			if(fPos.equalsIgnoreCase("UNKNOWN")) {
				System.out.println(" Station Code : " + fPos);
				reportStep("Station Code :  " + fPos +"has been verified - Without GR", "pass", true);
			}
		} catch (Exception e) {

			System.out.println("Exception thrown in create New rack method");
			e.printStackTrace();
			reportStep("Exception thrown in create New rack method ", "Fail", true);

		}
	}

	public boolean checkOrderSequence() throws InterruptedException {
		try {

			String tableData = prop.getProperty("Assembly_line.Search.Table") + "/tbody/tr";
			// To locate table and table header column
			String tableHeaderColumn = prop.getProperty("Assembly_line.Search.Table") + "/thead/tr";

			// Row Fetch
			List<WebElement> rowsFetch = driver.findElements(By.xpath(tableData));
			// calculate no of rows In table.
			int racksnrOrder = htmlTableColumnNamePosition("Racksnr", tableHeaderColumn);
			int timeOrder = htmlTableColumnNamePosition("Mastime", tableHeaderColumn);

			// verifying the order creation
			String orderNo, orderNo_Next, masTime, masTime_Next;
			int orderFlag = 0, masTimeFlag = 0;

			if (rowsFetch.size() == 1) {
				orderNo = rowsFetch.get(0).findElements(By.tagName("td")).get(racksnrOrder).getText();

				masTime = rowsFetch.get(0).findElements(By.tagName("td")).get(timeOrder).getText();

				reportStep("Order Creation Sequence is: " + orderNo + " creation time: " + masTime, "Pass", true);
				return true;
			}

			else {
				for (int i = 0; i < rowsFetch.size() - 1;) {

					// Retrieve the Racknr
					orderNo = rowsFetch.get(i).findElements(By.tagName("td")).get(racksnrOrder).getText();

					orderNo_Next = rowsFetch.get(i + 1).findElements(By.tagName("td")).get(racksnrOrder).getText();

					masTime = rowsFetch.get(i).findElements(By.tagName("td")).get(timeOrder).getText();

					masTime_Next = rowsFetch.get(i + 1).findElements(By.tagName("td")).get(timeOrder).getText();

					// Compare the Sequence Values
					if (Integer.parseInt(orderNo) <= Integer.parseInt(orderNo_Next)) {
						orderFlag = 1;
					}

					// Compare the MasTime
					if ((new BigInteger(masTime)).compareTo(new BigInteger(masTime_Next)) == -1) {
						masTimeFlag = 1;
					}

					if (orderFlag == 1 && masTimeFlag == 1) {
						reportStep(
								"Order Creation Sequence is: " + orderNo + " creation time: " + masTime
										+ " <br> followed by: " + orderNo_Next + " creation time: " + masTime_Next,
								"Pass", true);
						return true;
					} else {
						reportStep(
								"Order Sequence inCorrect: " + orderNo + " creation time: " + masTime
										+ " <br> followed by: " + orderNo_Next + " creation time: " + masTime_Next,
								"Fail", true);
						return false;
					}

				}
			}

		} catch (Exception e) {
			System.out.println("Exception thrown in checkOrderSequence method");
			e.printStackTrace();
			reportStep("Exception thrown in checkOrderSequence method ", "Fail", true);
		}
		return false;

	}

	public void Prerequiste_tugger() throws InterruptedException {
		String sheetName = "vclas_sekadm";

		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		String flowName = tdrow.get("Sequence");

		// vclas connection details
		String ClassName = prop.getProperty(Environment + ".VCLAS.ORACLE.ClassName");
		String ConnectionString = prop.getProperty(Environment + ".VCLAS.ORACLE.Connection.String");
		String UserName = prop.getProperty(Environment + ".VCLAS.ORACLE.User.Name");
		String Password = prop.getProperty(Environment + ".VCLAS.ORACLE.User.Password");

		// vclas deletion
		DB_Connectivity db = new DB_Connectivity();

		// deleting Assignment_ID in Alarm table(1)
		db.Update_DB(ClassName, ConnectionString, UserName, Password, tugger_VCLAS_Alarm.replace("#FLOW#", flowName));

		// deleting TA_ID in TA_PARAM table(2)
		db.Update_DB(ClassName, ConnectionString, UserName, Password,
				tugger_VCLAS_TA_Param.replace("#FLOW#", flowName));
		// deleting actor_id in planning_trigger table(3)
		db.Update_DB(ClassName, ConnectionString, UserName, Password,
				tugger_VCLAS_planing_trigger.replace("#FLOW#", flowName));
		// deleting transport_order in TRANSPORT_ASSIGNMENT table(4)
		db.Update_DB(ClassName, ConnectionString, UserName, Password,
				tugger_VCLAS_Transport_Assignment.replace("#FLOW#", flowName));
		// deleting TRANSPORT_ORDER_ID in MAS_PACKAGE table(5)
		db.Update_DB(ClassName, ConnectionString, UserName, Password,
				tugger_VCLAS_MAS_Package.replace("#FLOW#", flowName));
		// deleting TO_ID in TO_PARAM table(6)
		db.Update_DB(ClassName, ConnectionString, UserName, Password,
				tugger_VCLAS_TO_PARAM.replace("#FLOW#", flowName));
		// deleting flow in transport order table(7)
		db.Update_DB(ClassName, ConnectionString, UserName, Password,
				tugger_VCLAS_transport_order.replace("#FLOW#", flowName));

		// MASWEB connection details
		ClassName = prop.getProperty(Environment + ".MASWEB.MIMER.ClassName");
		ConnectionString = prop.getProperty(Environment + ".MASWEB.MIMER.Connection.String");
		UserName = prop.getProperty(Environment + ".MASWEB.MIMER.User.Name");
		Password = prop.getProperty(Environment + ".MASWEB.MIMER.Password");

		// MASWEB Updation and Deletion

		// deleting the flow in masweb
		db.Update_DB(ClassName, ConnectionString, UserName, Password,
				tugger_MASWEB_DeleteFlow.replace("#FLOW#", flowName));

		// updating the status
		db.Update_DB(ClassName, ConnectionString, UserName, Password,
				tugger_MASWEB_UpdateStatus.replace("#FLOW#", flowName));

		reportStep("Prerequisite Data Cleaning for Tugger Flow: " + flowName + " - Completed", "pass", false);

	}

	public void Prerequiste_JISJIT_Tugger() throws InterruptedException {
		String sheetName = "vclas_sekadm";

		

		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		String flowName = tdrow.get("Sequence");

		// vclas connection details
		String ClassName = prop.getProperty(Environment + ".VCLAS.ORACLE.ClassName");
		String ConnectionString = prop.getProperty(Environment + ".VCLAS.ORACLE.Connection.String");
		String UserName = prop.getProperty(Environment + ".VCLAS.ORACLE.User.Name");
		String Password = prop.getProperty(Environment + ".VCLAS.ORACLE.User.Password");

		// vclas deletion
		DB_Connectivity db = new DB_Connectivity();

		// deleting Assignment_ID in Alarm table(1)
		db.Update_DB(ClassName, ConnectionString, UserName, Password, tugger_VCLAS_Alarm.replace("#FLOW#", flowName));

		// deleting TA_ID in TA_PARAM table(2)
		db.Update_DB(ClassName, ConnectionString, UserName, Password,
				tugger_VCLAS_TA_Param.replace("#FLOW#", flowName));
		// deleting actor_id in planning_trigger table(3)
		db.Update_DB(ClassName, ConnectionString, UserName, Password,
				tugger_VCLAS_planing_trigger.replace("#FLOW#", flowName));
		// deleting transport_order in TRANSPORT_ASSIGNMENT table(4)
		db.Update_DB(ClassName, ConnectionString, UserName, Password,
				tugger_VCLAS_Transport_Assignment.replace("#FLOW#", flowName));
		// deleting TRANSPORT_ORDER_ID in MAS_PACKAGE table(5)
		db.Update_DB(ClassName, ConnectionString, UserName, Password,
				tugger_VCLAS_MAS_Package.replace("#FLOW#", flowName));
		// deleting TO_ID in TO_PARAM table(6)
		db.Update_DB(ClassName, ConnectionString, UserName, Password,
				tugger_VCLAS_TO_PARAM.replace("#FLOW#", flowName));
		// deleting flow in transport order table(7)
		db.Update_DB(ClassName, ConnectionString, UserName, Password,
				tugger_VCLAS_transport_order.replace("#FLOW#", flowName));

		// MASWEB connection details
		ClassName = prop.getProperty(Environment + ".MASWEB.MIMER.ClassName");
		ConnectionString = prop.getProperty(Environment + ".MASWEB.MIMER.Connection.String");
		UserName = prop.getProperty(Environment + ".MASWEB.MIMER.User.Name");
		Password = prop.getProperty(Environment + ".MASWEB.MIMER.Password");
		try {
		
			// updating the Kamera Id
		db.Update_DB(ClassName, ConnectionString, UserName, Password, JISJIT_MASWEB_KameraId
				.replace("#BEOLOPNR#", tdrow.get("Beolopnr")).replace("#KAMERAID#", tdrow.get("Kamera_Id")));
		System.out.println("Kamera Id has been set for " + flowName + " flow ");
		
		// MASWEB Updation and Deletion

		// deleting the flow in masweb
		db.Update_DB(ClassName, ConnectionString, UserName, Password,
				tugger_MASWEB_DeleteFlow.replace("#FLOW#", flowName));

		// updating the status
		db.Update_DB(ClassName, ConnectionString, UserName, Password,
				tugger_MASWEB_UpdateStatus.replace("#FLOW#", flowName));
		System.out.println("Prerequisite Data Cleaning for Tugger Flow: " + flowName + " - Completed");
		reportStep("Prerequisite Data Cleaning for Tugger Flow: " + flowName + " - Completed", "pass", false);

		

			


			
		} catch (Exception e) {
			System.out.println("Pre-requisite failed - Exception: " + e);
		}

		

	}

	public void JISJIT_EmptyRack_Delivery() throws InterruptedException {
		String sheetName = "vclas_sekadm";
		ResultSet rsArticleCount;
		String articleCount="";
		// Load Test Data File
				tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
				String flowName = tdrow.get("Sequence");
				DB_Connectivity db = new DB_Connectivity();
				// vclas connection details
				String ClassName = prop.getProperty(Environment + ".VCLAS.ORACLE.ClassName");
				String ConnectionString = prop.getProperty(Environment + ".VCLAS.ORACLE.Connection.String");
				String UserName = prop.getProperty(Environment + ".VCLAS.ORACLE.User.Name");
				String Password = prop.getProperty(Environment + ".VCLAS.ORACLE.User.Password");
try {
	do {
		 rsArticleCount= db.Connect_DB(ClassName, ConnectionString, UserName, Password, VCLAS_ArticleCount.replace("#FLOW#", flowName));
		
		 if(rsArticleCount.next()) {
             articleCount = rsArticleCount.getString(2);
             System.out.println("articleCount:" +articleCount);
             excelUtils.excelUpdateValue("Vclas_Assignments", "Article_Count", refTestDataName, articleCount);
             
		 }
	}while(!(articleCount.length()==2));
	}
	catch (Exception e)
{
        System.out.println("Pre-requisite failed - Exception: "+e);
}	
	}
	public void Data_Insertion_JISJIT_Tugger() throws InterruptedException {
		String sheetName = "vclas_sekadm";
		
		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		String flowName = tdrow.get("Sequence");

		// MASWEB connection details
		String ClassName = prop.getProperty(Environment + ".MASWEB.MIMER.ClassName");
		String ConnectionString = prop.getProperty(Environment + ".MASWEB.MIMER.Connection.String");
		String UserName = prop.getProperty(Environment + ".MASWEB.MIMER.User.Name");
		String Password = prop.getProperty(Environment + ".MASWEB.MIMER.Password");
		// vclas deletion
		DB_Connectivity db = new DB_Connectivity();

		db.Update_DB(ClassName, ConnectionString, UserName, Password,
				JISJIT_MASWEB_InsertRecord.replace("#FLOW#", flowName).replace("#LEVNR#", Dlevnr)
						.replace("#OKOLLINR#", Dokollinr).replace("#deletedRackNo#", deletedRackNo)
						.replace("#CONTNR#", Dcontnr).replace("#FSNR#", Dfsnr).replace("#Dfbopnr#", Dfbopnr)
						.replace("#Dlbopnr#", Dlbopnr).replace("#RECCONTNR#", ""));

		System.out.println("data inserted");
		reportStep(deletedRackNo + "has been inserted", "pass", false);
	}
	
	public void Data_Deletion_JISJIT_Tugger() throws InterruptedException {
		String sheetName = "vclas_sekadm";
		String rackStart, rackEnd = "";
		
		ResultSet rsRackSet = null, rsLastRack = null;
		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		String flowName = tdrow.get("Sequence");

		// MASWEB connection details
		String ClassName = prop.getProperty(Environment + ".MASWEB.MIMER.ClassName");
		String ConnectionString = prop.getProperty(Environment + ".MASWEB.MIMER.Connection.String");
		String UserName = prop.getProperty(Environment + ".MASWEB.MIMER.User.Name");
		String Password = prop.getProperty(Environment + ".MASWEB.MIMER.Password");
		// vclas deletion
		DB_Connectivity db = new DB_Connectivity();	
		
		try {
//To delete the particular record from resultset - Dummy ASN
		rackStart = flowName + tdrow.get("Racksnr");
		System.out.println(rackStart);
		fsnr = tdrow.get("fsnr");
		rsLastRack = db.Connect_DB(ClassName, ConnectionString, UserName, Password,
				JISJIT_MASWEB_LASTRACK.replace("#FLOW#", flowName).replace("#FSNR#", fsnr));

		if (rsLastRack.next()) {

			rackEnd = rsLastRack.getString(1);

			System.out.println(rackEnd);

		}

		rsRackSet = db.Connect_DB(ClassName, ConnectionString, UserName, Password,
				JISJIT_MASWEB_RACKSTART.replace("#FLOW#", flowName).replace("#FSNR#", fsnr)
						.replace("#RACKSTART#", rackStart).replace("#RACKEND#", rackEnd));
		for (int i = 0; i <= 2; i++) {
			rsRackSet.next();
			if (i == 1) {
				deletedRackNo = rsRackSet.getString("RACKNR");
				System.out.println("deletedRackNo:" + deletedRackNo);
				Dfsnr = rsRackSet.getString("FSNR");
				Dfbopnr = rsRackSet.getString("FBOPNR");
				Dlbopnr = rsRackSet.getString("LBOPNR");
				Dlevnr = rsRackSet.getString("LEVNR");
				Dokollinr = rsRackSet.getString("OKOLLINR");
				Dcontnr = rsRackSet.getString("CONTNR");

				System.out.println("Dfbopnr:" + Dfbopnr);
				System.out.println("Dlbopnr:" + Dlbopnr);
				System.out.println("Dlevnr:" + Dlevnr);
				System.out.println("Dokollinr:" + Dokollinr);
				System.out.println("Dcontnr:" + Dcontnr);
				System.out.println("Dfsnr:" + Dfsnr);

			}

		}

		String BOPLOPRANGE = Dfbopnr.substring(3) + Dlbopnr.substring(3);
		excelUtils.excelUpdateValue("Vclas_Assignments", "BOPLOP_Range", refTestDataName, BOPLOPRANGE);

		System.out.println("BOPLOPRANGE:" + BOPLOPRANGE);

		

		db.Update_DB(ClassName, ConnectionString, UserName, Password,
				JISJIT_MASWEB_DeleteRecord.replace("#RACKNR#", deletedRackNo));
		System.out.println(deletedRackNo + "deleted");
		reportStep(deletedRackNo + "has been deleted - Dummy ASN", "pass", false);
		 reportStep("#B 45.Verify the functonality of no ASN and no records in PMR861 and PMR862, while scanning VCLAS system ask for BOPLOP range Test case Id:3203--completed#C", "pass", false);
		rsLastRack.close();
		rsRackSet.close();
	} catch (Exception e) {
		System.out.println("Pre-requisite failed - Exception: " + e);
	}

	

}

}

