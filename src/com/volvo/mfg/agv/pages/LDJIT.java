package com.volvo.mfg.agv.pages;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import com.volvo.automation.commonutilities.DB_Connectivity;
import com.volvo.automation.commonutilities.ExcelUtils;
import com.volvo.automation.commonutilities.QueryInput;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.volvo.mfg.StepDefinition.DriverSuite;;

public class LDJIT extends DriverSuite implements QueryInput{
	MasWeb_Home mwHome = new MasWeb_Home();
	Vclas_tasks vt = new Vclas_tasks();
	 Vclas_Assignment vclasAgn = new Vclas_Assignment();
	String	tableObj=prop.getProperty("Vclas_tasks.RCPAS.Table");
	String tableHeaderColumn = prop.getProperty("Vclas_tasks.RCPAS.Table")+"/tbody/tr[1]";
	String status, updateEmptyStatus, parkingPlace;
public String place;
	public HashMap<String, String> tdrow;
	// Excel class object to access the function
	ExcelUtils excelUtils = new ExcelUtils();
	String sheetName = "LDJIT";

	public void navigateLdjit() {


		// Navigate the Page
		anyClick("LDJIT Menu", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu")));
		anyClick("Yard Zone Maintenance Menu", By.xpath(prop.getProperty("LDJIT_YardZone.Menu")));
		

		// Verify the Page is displayed
		//verifyElementExist("Trailers Info Page", By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Page.Text")));


	}

	public void ldjitTrailersInfoPark() throws InterruptedException {
int checkCode;
		// retrieve data from MasWeb_Page
		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		anyClick("Trailers Info Menu", By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Menu")));
		
		// Verify the Page is displayed
		verifyElementExist("Trailers Info Page", By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Page.Text")));

		// Clicking the park button
		anyClick("Click Park Button", By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Park.Button")));

		sendKeys("Trailer Id", By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.TrailerId.Text")),
				tdrow.get("Trailer_Id"));

		
		sendKeys("ASN Number", By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.AsnNum.Text")),
				tdrow.get("ASN_Num"));
		
		anyClick("Display button", By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Display.Button")));
		
		if (driver.findElements(By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Table") + "/tbody/tr")).size() > 1) {
			reportStep("Trailer Info -Table displayed successfully", "PASS", false);
			
			Select select = new Select(driver.findElement(By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Table") + "/tbody/tr[2]/td[6]/select")));
			WebElement option = select.getFirstSelectedOption();
			String zone= option.getText();
			System.out.println("zone:" +zone );
			Select select1 = new Select(driver.findElement(By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Table") + "/tbody/tr[2]/td[7]/select")));
			option = select1.getFirstSelectedOption();
			parkingPlace= option.getText();
			System.out.println("parkingPlace:"+parkingPlace );
			
			String trailerType =driver.findElements(By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Table") + "/tbody/tr[2]/td[8]")).get(0).getText();
			System.out.println("trailerType:" +trailerType);
			anyClick("Click Park Button", By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Table.Park")));
			
		}
		
		if(verifyElementExistReturn(By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Table.Form")))==true) {
			checkCode=Checkcode_LDJIT_Trailer();
			 String checkCodes=String.valueOf(checkCode);
			sendKeys("Check code", By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Form.CheckCode.Text")),checkCodes);
			//anyClick("Save button", By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Form.Save.Button")));
			driver.findElement(By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Form.Save.Button"))).click();
			
		}

		// Verifying The Message
		WebElement message = driver.findElement(By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.msg")));
		status = message.getText();

		if (status.equalsIgnoreCase(tdrow.get("Message"))) {
			System.out.println(" Verification status : " + status);

			reportStep("Trailer Information added - Message displayed " + status, "pass", true);
		}
		 excelUtils.excelUpdateValue("Vclas_Assignments", "Trailer_Id", refTestDataName, tdrow.get("Trailer_Id"));
		 excelUtils.excelUpdateValue("Elanders", "TrailerId", refTestDataName, tdrow.get("Trailer_Id"));  //new Elanders
		// verify supplier, flow, asn-DB

		// CLick the Park Button
		//anyClick("Click Park Button", By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Park.Button")));

	}

	private void select(WebElement findElement) {
		// TODO Auto-generated method stub
		
	}

	public void createTrailerAssignments(String type) 

	{

		// retrieve data from MasWeb_Page
		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		// Navigate to create trailer Assignment
		anyClick("Create Trailer Assignments", By.xpath(prop.getProperty("LDJIT_YardZone_Create_Trailers_Assignment")));
		
		
int placeSize= driver.findElements(By.xpath(prop.getProperty("LDJIT_YardZone_Create_Trailers_Assignment_Place.Dropdown"))).size();

System.out.println("placeSize:" +placeSize);
for(int i=2;i<=placeSize;i++) {
	place = driver.findElements(By.xpath(prop.getProperty("LDJIT_YardZone_Create_Trailers_Assignment_Place.Dropdown")+"["+i+"]")).get(0).getText();
	System.out.println("place:" +place);
	excelUtils.excelUpdateValue("Elanders", "Place", refTestDataName, place); //new Elanders
	selectDropDownByIndex("Place dropdown",
			By.xpath(prop.getProperty("LDJIT_YardZone_Create_Trailers_Assignment_Place.Dropdown.select")),place
			);
	int flowSize= driver.findElements(By.xpath(prop.getProperty("LDJIT_YardZone_Create_Trailers_Assignment_Flow.Dropdown"))).size();
	System.out.println("flowSize:" +flowSize);

	int j=2;
		String flow = driver.findElements(By.xpath(prop.getProperty("LDJIT_YardZone_Create_Trailers_Assignment_Flow.Dropdown")+"["+j+"]")).get(0).getText();
		System.out.println("flow: " +flow);
		if(flow.equalsIgnoreCase(tdrow.get("Flow"))) {
			//System.err.println("BREAK");
			//selectDropDownByIndex("Flow dropdown",By.xpath(prop.getProperty("LDJIT_YardZone_Create_Trailers_Assignment_Flow.Dropdown.select")),flow);
			
			break;
		}
		
		j++;
	
	}
System.out.println("place:" +place);

		
		if (type.equalsIgnoreCase("Full")) {
			anyClick("Bring Full Button", By.xpath(prop.getProperty("LDJIT_YardZone_Create_Trailers_Assignment.Button")
					.replace("&Value", "bringfull")));
		} else {
			anyClick("Remove Empty Button", By.xpath(prop
					.getProperty("LDJIT_YardZone_Create_Trailers_Assignment.Button").replace("&Value", "removeEmpty")));
		}

		// Verifying The Message
				WebElement message = driver.findElement(By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.msg")));
				status = message.getText();
				if (type.equalsIgnoreCase("Full")) {
				if (status.equalsIgnoreCase(tdrow.get("Full_Trailer_Message"))) {
					System.out.println(" Verification status : " + status);

					reportStep("Creation of Full Trailer Assignments in Create Trailer Assignments screen - Message displayed - Testcase Id-1052 " + status, "pass", true);
					
				}
				}else {
					if (status.equalsIgnoreCase(tdrow.get("Empty_Trailer_Message"))) {
						System.out.println(" Verification status : " + status);

						
						reportStep("Creation of Empty Trailer Assignments in Create Trailer Assignments screen - Message displayed - Testcase Id-1053 " + status, "pass", true);
				}
				}
	}
	
	public void verifyFullTrailer(String type) throws InterruptedException
	{

		// retrieve data from MasWeb_Page
		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		String[] strReserved_Type;
		strReserved_Type= tdrow.get("Reserved_Type").split(",");


		anyClick("Trailers Info Menu", By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Menu")));
		sendKeys("Trailer Id", By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.SearchTrailerId.Text")),
				tdrow.get("Trailer_Id"));
		anyClick("Display Button ", By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Display.Button")));
		
		if (driver.findElements(By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Display.Table") + "/tbody/tr")).size() >= 1) {
			reportStep("Trailer Info-Table displayed successfully", "PASS", false);
			
			String trailerReservedType =driver.findElements(By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Display.Table") + "/tbody/tr[1]/td[6]")).get(0).getText();
			System.out.println("trailerReservedType:" +trailerReservedType);
			if (type.equalsIgnoreCase("Full"))  {
				if(trailerReservedType.equalsIgnoreCase(strReserved_Type[0])) {
				reportStep(strReserved_Type[0] + "trailer is verified successfully", "PASS", false);
			}
			}else {
				if(trailerReservedType.equalsIgnoreCase(strReserved_Type[1])) {
					reportStep(strReserved_Type[1] +"trailer is verified successfully", "PASS", false);
			}
		}
	}
	}
	public void verifyTrailerAck() throws InterruptedException
	{

		// retrieve data from MasWeb_Page
		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_tasks");
		/*// Enter PartNumber
				if (!tdrow.get("PartNumber").equals("")) {
					sendKeys("PartNumber", By.xpath(prop.getProperty("Vclas_tasks.Part.Number.Text")), tdrow.get("PartNumber"));
				}
				
		if (!tdrow.get("Factory").equals("")) {
			selectRadioButtonByValue("Factory", By.xpath(prop.getProperty("Vclas_tasks.Factory.Radio")),
					tdrow.get("Factory"));
		}

		// Select Radio button
		if (!tdrow.get("Flow_Type").equals("")) {
			selectRadioButtonByValue("Flow_Type", By.xpath(prop.getProperty("Vclas_tasks.Flow.Type.Radio")),
					"Trailer");
		}

		// Select Radio button
		if (!tdrow.get("Task_Type").equals("")) {
			selectRadioButtonByValue("Task_Type", By.xpath(prop.getProperty("Vclas_tasks.Task.Type.Radio")),
					tdrow.get("Task_Type"));
		}
		anyClick("Display button", By.xpath(prop.getProperty("Vclas_tasks.Display.Button")));

		Thread.sleep(2000);
*/		vt.Vclas_getTrailerTask(tdrow.get("PartNumber"), tdrow.get("Trailer_Id"), 1);
		/*int iTemp=0;
		do {

			anyClick("Display button", By.xpath(prop.getProperty("Vclas_tasks.Display.Button")));

			Thread.sleep(2000);
			
		
		int colTaskEvents = htmlTableColumnNamePosition("Task events", tableHeaderColumn);
			String objectTableTaskEvents = tableObj + "/tbody/tr[2]/td[" + (colTaskEvents + 1) + "]/table/tbody/tr";
			List<WebElement> tableTaskEvents = driver.findElements(By.xpath(objectTableTaskEvents));
			int taskEventCount = tableTaskEvents.size();
			System.out.println(taskEventCount);

			String lastRow = tableTaskEvents.get(taskEventCount - 1).findElements(By.tagName("td")).get(1).getText();
			// String lastRow = driver.findElement(By.xpath(objectTableTaskEvents + "[" +
			// taskEventCount + "]/td[2]")).getText();

			if (lastRow.equals("ACK")) {
				reportStep("Ack is reflected in the Vclas tasks", "Pass", true);
				boolean bReturn = true;
				break;
			}
			iTemp = iTemp + 1;

		} while (!(iTemp == 30));

		if (iTemp == 30) {
			reportStep("Acknowledge is not reflected in the Vclas tasks", "Fail", true);
		}*/
		
	}
	public void trailerFlowDelivery(String type) {
int j=0;
driver.switchTo().window(browser[2]);	
		// retrieve data from MasWeb_Page
		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_Assignments");
		// Navigation to Shortage
				
					try {
						
						anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));
						Thread.sleep(5000);
						
						vclasAgn.Vclas_Shortage_Search(tdrow.get("Trailer_Id"), "", "LDJIT");
						String ObjectId= tdrow.get("Trailer_Id");
						String TaskId= tdrow.get(type + "_Task_1");   //"Full_Task_" + RacksNo
						String nextButton = prop.getProperty("Vclas_Home.Shortage.Next.Button");
						String objIdVerify = prop.getProperty("Vclas_Home.Shortage.Object_Id.Verify").replace("&Value", ObjectId)
								.replace("&1Value", TaskId);

						// Verifying if it is available in the screen
						if (verifyElementExistReturn(By.xpath(objIdVerify)) == true) {
							reportStep("ObjectId : " + ObjectId + " " + "TaskId: " + TaskId + " exists in the Shortage Screen ",
									"PASS", true);
						}else {
							// Verifying if it available in Next Screen
							while (verifyElementExistReturn(By.xpath(nextButton)) == true) {

								// Clicking the next button
								anyClick("Shortage Next Button", By.xpath(nextButton));

								if (verifyElementExistReturn(By.xpath(objIdVerify)) == true) {
									reportStep(ObjectId +TaskId+ " exist in the Shortage screen", "Pass", true);
									
								}
							}
						}
						System.out.println("TRL Type: "+"TRL_"+type.toUpperCase());
						vclasAgn.Vclas_Shortage_TypeVerify(tdrow.get("Trailer_Id"),tdrow.get(type + "_Task_1"),"TRL_"+type.toUpperCase(), "Planned");
						
							
					
						vclasAgn.Vclas_AssignmentList_RackSubmission_JISJIT("TRL_"+type.toUpperCase(), tdrow.get("Trailer_Id"));
						if (verifyElementExistReturn(
								By.xpath(prop.getProperty("Vclas_home.AssignmentList.ScanDialogBox"))) == true) {
							 reportStep("Assignment Scan dialog box is displayed successfully", "Info", true);
							 if (verifyElementExistReturn(
										By.xpath(prop.getProperty("Vclas.AssignmentList.OkButton.Click"))) == true) {
									anyClick("Ok button for the " + tdrow.get("Trailer_Id"),
											By.xpath(prop.getProperty("Vclas.AssignmentList.OkButton.Click")));
									reportStep(tdrow.get("Trailer_Id")+" Has Been Delivered From Assignment List Screen ","pass", true);
							 }else {
									reportStep("Ok button for the " + tdrow.get("Trailer_Id") + "is not enabled", "fail", true);
								}}else {
										reportStep("Scan dialogue box for the" + tdrow.get("Trailer_Id") + "is not displayed", "fail", true);
									}
						tdrow.clear();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		
	}
	
	public void updateLDJITTrailerEmpty() throws InterruptedException
    {
		//Switching to Masweb window 
				driver.manage().window().maximize();
				driver.switchTo().window(browser[1]);
				driver.manage().window().maximize();
		// retrieve data from MasWeb_Page
				// Load Test Data File
				tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
           //Navigate to LDJIT menu 
           anyClick("LDJIT  Menu", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu")));
           //Navigate to Update_trailer_empty 
           anyClick("update_JISJIT_Trailer_Empty", By.xpath(prop.getProperty("LDJIT_update_LDJIT_Trailer_Empty")));
           Thread.sleep(20000);
           System.out.println("placess:" +place);
           //select the gate 
           
           //anyClick("Gate DropDown", By.xpath(prop.getProperty("LDJIT_update_LDJIT_Trailer_Empty_Gate.DropDown")));
           selectDropDownValue("Gate DropDown", By.xpath(prop.getProperty("LDJIT_update_LDJIT_Trailer_Empty_Gate.DropDown")),"GM-TVS12      ");
          
           Thread.sleep(7000);
           //selectDropDownByIndex("Gate DropDown", By.xpath(prop.getProperty("LDJIT_update_LDJIT_Trailer_Empty_Gate.DropDown")),place);
           //verifying trailer id
           String verifyTrailer = driver.findElements(By.xpath(prop.getProperty("LDJIT_UpdateTrailerEmpty.Text") + "/tbody/tr[2]/td[2]")).get(0).getText();
           if(verifyTrailer.equalsIgnoreCase(tdrow.get("Trailer_Id"))) {
				reportStep(verifyTrailer + "trailer id is verified successfully", "PASS", false);
			}else {
				reportStep(verifyTrailer + "trailer id is not verified successfully", "PASS", false);
			}
           //update trailer as empty
           anyClick("update Trailer as empty button", By.xpath(prop.getProperty("LDJIT_update_LDJIT_Trailer_Empty.Button")));
           
        // Verifying The Message
			WebElement updateMessage = driver.findElement(By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.msg")));
			updateEmptyStatus = updateMessage.getText();

			if (updateEmptyStatus.equalsIgnoreCase(tdrow.get("Update_Empty_Message"))) {
				System.out.println(" Verification status : " + updateEmptyStatus);

				reportStep("Trailer Status updated as Empty - Message displayed " + updateEmptyStatus, "pass", true);
			}
    }
	public int Checkcode_LDJIT_Trailer() throws InterruptedException {
		
		
		int checkCode=0;
		ResultSet rsCheckCode = null;
		

		// MASWEB connection details
		String ClassName = prop.getProperty(Environment + ".MASWEB.MIMER.ClassName");
		String ConnectionString = prop.getProperty(Environment + ".MASWEB.MIMER.Connection.String");
		String UserName = prop.getProperty(Environment + ".MASWEB.MIMER.User.Name");
		String Password = prop.getProperty(Environment + ".MASWEB.MIMER.Password");
		// vclas deletion
		DB_Connectivity db = new DB_Connectivity();	
		
		try {
//To delete the particular record from resultset - Dummy ASN
		
		rsCheckCode = db.Connect_DB(ClassName, ConnectionString, UserName, Password,
				LDJIT_MASWEB_CHECKCODE.replace("#parkingPlace#", parkingPlace));

		if (rsCheckCode.next()) {

			checkCode = rsCheckCode.getInt(8);

			System.out.println("checkCode:" +checkCode);
		} 
		}catch (Exception e) {
			System.out.println("Pre-requisite failed - Exception: " + e);
		}

		
return checkCode;
	}

public void DB_DataInsertion_AfterGR() throws InterruptedException {
		
		int countAfterGR=0,countAfterGRFsnr=0;
		
		ResultSet rsCountAfterGr = null,rsCountAfterGrFsnr= null;
		

		// MASWEB connection details
		String ClassName = prop.getProperty(Environment + ".MASWEB.MIMER.ClassName");
		String ConnectionString = prop.getProperty(Environment + ".MASWEB.MIMER.Connection.String");
		String UserName = prop.getProperty(Environment + ".MASWEB.MIMER.User.Name");
		String Password = prop.getProperty(Environment + ".MASWEB.MIMER.Password");
		// vclas deletion
		DB_Connectivity db = new DB_Connectivity();	
		
		try {
//To check the record count in pmr861 and pmr862 after gr
		//pmr 862 table
		rsCountAfterGr = db.Connect_DB(ClassName, ConnectionString, UserName, Password,
				LDJIT_MASWEB_AFTERGR_COUNT.replace("#CONTNR#",tdrow.get("Trailer_Id")).replace("#POSITION#", parkingPlace).replace("#FLOW#", tdrow.get("Flow")));
		

		if (rsCountAfterGr.next()) {

			countAfterGR = rsCountAfterGr.getInt(1);

			System.out.println("countAfterGR:" +countAfterGR);
			
		} 
		//pmr 861 table
		rsCountAfterGrFsnr = db.Connect_DB(ClassName, ConnectionString, UserName, Password,
				LDJIT_MASWEB_AFTERGR_COUNT_FSNR.replace("#FLOW#", tdrow.get("Flow")).replace("#FSNR#", tdrow.get("ASN_Num")));
		

		if (rsCountAfterGrFsnr.next()) {

			countAfterGRFsnr = rsCountAfterGrFsnr.getInt(1);

			System.out.println("countAfterGRFsnr:" +countAfterGRFsnr);
			
		} 
		
		if(countAfterGR==countAfterGRFsnr) {
			System.out.println("No. of records are equal: "+countAfterGR);
			reportStep(" The functionality of inserting records into PMR862 after GR is done has been Verified :" + countAfterGR +"----TESTCASE ID :3200" , "pass", true);
		}
		else {
			reportStep(" The functionality of inserting records into PMR862 after GR is done has not been Verified . The count to be expected:" + countAfterGR
					+ "The Actual Type is:" +countAfterGRFsnr, "fail", true);
		}
		}catch (Exception e) {
			System.out.println("Pre-requisite failed - Exception: " + e);
		}

		

	}
}

