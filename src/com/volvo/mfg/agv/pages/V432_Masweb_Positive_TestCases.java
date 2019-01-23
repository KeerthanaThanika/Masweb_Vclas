package com.volvo.mfg.agv.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Sleeper;

import com.volvo.automation.commonutilities.ExcelUtils;
import com.volvo.mfg.StepDefinition.DriverSuite;

public class V432_Masweb_Positive_TestCases extends DriverSuite {
	
	String sheetName="V432";
	public HashMap<String,String> tdrow;
	
	//Excel class object to access the function
	ExcelUtils excelUtils = new ExcelUtils();
	
	public void V60_Register_Default_Values_OuttakeStation() throws InterruptedException{
		tdrow= excelUtils.testCaseRetrieve(refTestDataName,sheetName);
		
		//clicking on display button
		anyClick("Display Button", By.xpath(prop.getProperty("V60_Register_Default_Values.Display.Button")));
		
		//verifying the details are displayed or no resultd found error message
		if(isElementPresent(By.xpath(prop.getProperty("V60_Register_Default_Values.NoResults.ErrorMsg")))==true) {
			reportStep("No results found message is verified", "pass", true);
		}else if(isElementPresent(By.xpath(prop.getProperty("V60_Register_Default_Values.Table")))) {
			reportStep("Outtake Stations Table displayed ", "pass", true);
		}else {
			reportStep("Error in displaying the outtake stations Table", "fail", true);
		}
		
		//entering some values to all search fields and clear
		String outtakeStation=getRandomString(6);
		String fPos=getRandomString(4);
		String toPos=getRandomString(4);
		// entering values
		sendKeys("Outtake Station field", By.xpath(prop.getProperty("V60_Register_Default_Values.OuttakeStation.Text")), outtakeStation);
		sendKeys("Fpos field", By.xpath(prop.getProperty("V60_Register_Default_Values.Fpos.Text")), fPos);
		sendKeys("ToPos field", By.xpath(prop.getProperty("V60_Register_Default_Values.Tpos.Text")), toPos);
		
		//clicking on clear button
		anyClick("Clear Button", By.xpath(prop.getProperty("V60_Register_Default_Values.Clear.Button")));
		
		//clicking on create button to create for Normal station
		anyClick("Create Button",By.xpath(prop.getProperty("V60_Register_Default_Values.Create.Button")));
		if(verifyElementExist("Create form--Normal", By.xpath(prop.getProperty("V60_Register_Default_Values.Create.OuttakeStation.Text")))==true) {
			// entering all the values
			sendKeys(" Normal Outtake Station value", By.xpath(prop.getProperty("V60_Register_Default_Values.Create.OuttakeStation.Text")), tdrow.get("Normal_Station"));
			sendKeys("Normal Fpos value--Normal", By.xpath(prop.getProperty("V60_Register_Default_Values.Create.Fpos.Text")), tdrow.get("Normal_Fpos"));
			sendKeys("normal Topos value--Normal", By.xpath(prop.getProperty("V60_Register_Default_Values.Create.Topos.Text")), tdrow.get("Topos"));
			selectDropDownValue("Normal Printer values", By.xpath(prop.getProperty("V60_Register_Default_Values.Create.Printer.Dropdown")), tdrow.get("Normal_Printer"));
			selectDropDownValue("Normal Default Label values", By.xpath(prop.getProperty("V60_Register_Default_Values.Create.DefaultNoOfLabels.Dropdown")), tdrow.get("Normal_Label"));
			
			//clicking on save button
			anyClick("Save Button", By.xpath(prop.getProperty("V60_Register_Default_Values.Create.Save.Button")));
			
			
		}
		//clicking on create button to create for repair station
		
		if(verifyElementExist("Create form--Repair", By.xpath(prop.getProperty("V60_Register_Default_Values.Create.OuttakeStation.Text")))==true) {
			// entering all the values
			sendKeys(" Repair Outtake Station value", By.xpath(prop.getProperty("V60_Register_Default_Values.Create.OuttakeStation.Text")), tdrow.get("Repair_Station"));
			sendKeys("Repair Fpos value", By.xpath(prop.getProperty("V60_Register_Default_Values.Create.Fpos.Text")), tdrow.get("Repair_Fpos"));
			sendKeys("Repair Topos value", By.xpath(prop.getProperty("V60_Register_Default_Values.Create.Topos.Text")), tdrow.get("Topos"));
			selectDropDownValue("Repair Printer values", By.xpath(prop.getProperty("V60_Register_Default_Values.Create.Printer.Dropdown")), tdrow.get("Repair_Printer"));
			selectDropDownValue("Repair Default Label values", By.xpath(prop.getProperty("V60_Register_Default_Values.Create.DefaultNoOfLabels.Dropdown")), tdrow.get("Repair_Label"));
			
			//clicking on save button
			anyClick("Save Button", By.xpath(prop.getProperty("V60_Register_Default_Values.Create.Save.Button")));
			
			
		}
	
		//clicking on edit button of one of the station
		anyClick("Edit Button", By.xpath(prop.getProperty("V60_Register_Default_Values.Table.Edit.Button").replace("&Value", "REPAIR STN")));
		String UneditableFpos=driver.findElement(By.xpath(prop.getProperty("V60_Register_Default_Values.Create.Fpos.Text"))).getText();
		
		//Clearing the fields 
		anyClick("Clear button", By.xpath(prop.getProperty("V60_Register_Default_Values.Create.Clear.Button")));
		if(verifyElementExist("Uneditable Fpos after clear", By.xpath(prop.getProperty("V60_Register_Default_Values.Create.Fpos.Text")))==true) {
			reportStep("Fpos field is Uneditable ", "pass", true);
		}else {
			reportStep("Fpos field is editable", "fail", true);
		}
		
		//clicking on save button
		anyClick("Save Button", By.xpath(prop.getProperty("V60_Register_Default_Values.Create.Save.Button")));
		//error message verification
		if(verifyElementExist("Please enter a valid outtake station name error message", By.xpath(prop.getProperty("V60_Register_Default_Values.ValidOutStation.ErrorMsg")))==true) {
			String ErrorMsg=driver.findElement(By.xpath(prop.getProperty("V60_Register_Default_Values.ValidOutStation.ErrorMsg"))).getText();
			reportStep("Error message: "+ErrorMsg, "pass", true);
			
		}else {
			reportStep("Error message verification failed", "fail", true);
		}
		
		//validating error msg for printer id
		sendKeys("Outtake station field", By.xpath(prop.getProperty("V60_Register_Default_Values.Create.OuttakeStation.Text")), tdrow.get("Repair_Station"));
		
		//clicking on save button
		anyClick("Save Button", By.xpath(prop.getProperty("V60_Register_Default_Values.Create.Save.Button")));
				//error message verification
		if(verifyElementExist(" error message--printer ID", By.xpath(prop.getProperty("V60_Register_Default_Values.PrinterId.ErrorMsg")))==true) {
		String ErrorMsg=driver.findElement(By.xpath(prop.getProperty("V60_Register_Default_Values.PrinterId.ErrorMsg"))).getText();
		reportStep("Error message: "+ErrorMsg, "pass", true);
					
		}else {
		reportStep("Error message verification failed", "fail", true);
			}
		
		//validating error msg for default number of labels
		sendKeys("Printer Id dropdown", By.xpath(prop.getProperty("V60_Register_Default_Values.Create.Printer.Dropdown")), tdrow.get("Repair_Printer"));
		//clicking on save button
		anyClick("Save Button", By.xpath(prop.getProperty("V60_Register_Default_Values.Create.Save.Button")));
						//error message verification
		if(verifyElementExist("error message--Label", By.xpath(prop.getProperty("V60_Register_Default_Values.Labels.ErrorMsg")))==true) {
		String ErrorMsg=driver.findElement(By.xpath(prop.getProperty("V60_Register_Default_Values.Labels.ErrorMsg"))).getText();
		reportStep("Error message: "+ErrorMsg, "pass", true);
							
		}else {
		reportStep("Error message verification failed", "fail", true);
			}
				
		//verifying error message for valid topos
		
		//-- make it as hold as it is done only in initital stage
		
	}
	
	
	//12 Verify the delivery of registered Normal and Repair V60 bodies from Deliver V60 Tasks screen to Ghent Test case Id:3940
	
	public void Deliver_V60_TaskScreen() throws InterruptedException{
		tdrow= excelUtils.testCaseRetrieve(refTestDataName,sheetName);
		
		//clicking on ADD/Remove button
		anyClick("Add button", By.xpath(prop.getProperty("Deliver_V60_Tasks.Add.Button")));
		//verifying the error prefix with S
		if(verifyElementExist("Error msg for prefix with S", By.xpath(prop.getProperty("Deliver_V60_Tasks.ErrorMsg")))==true) {
			String ErrorMsg=driver.findElement(By.xpath(prop.getProperty("Deliver_V60_Tasks.ErrorMsg"))).getText();
			reportStep("Error message for Prefix with S: "+ErrorMsg, "pass", true);
			
		}else {
			reportStep("Error message verification failed--with prefix S", "fail", true);
		}
		
		//clicking on delivey button
		anyClick("Deliver button", By.xpath(prop.getProperty("Deliver_V60_Tasks.Deliver.Button")));
		//verfying the error message
		if(verifyElementExist("Error msg for scan atleast ont bodyId with prefix S", By.xpath(prop.getProperty("Deliver_V60_Tasks.ErrorMsg.ScanAtleastOneBodyId")))==true) {
			String ErrorMsg=driver.findElement(By.xpath(prop.getProperty("Deliver_V60_Tasks.ErrorMsg.ScanAtleastOneBodyId"))).getText();
			reportStep("Error message for Scan atleast one bodyId Prefix with S: "+ErrorMsg, "pass", true);
			
		}else {
			reportStep("Error message verification failed--scan atleast one body Id", "fail", true);
		}
		
		//entering not registered body Id
		String NotRegisteredId=toString().valueOf(getRandomNumber(7));
		sendKeys("Body Id field", By.xpath(prop.getProperty("Deliver_V60_Tasks.BodyId.Text")), "S"+NotRegisteredId);
		//clicking on Add button
		anyClick("Add button", By.xpath(prop.getProperty("Deliver_V60_Tasks.Add.Button")));
		//veifying the error message
		if(verifyElementExist("Error msg for body Id is not registered", By.xpath(prop.getProperty("Deliver_V60_Tasks.ErrorMsg")))==true) {
			String ErrorMsg=driver.findElement(By.xpath(prop.getProperty("Deliver_V60_Tasks.ErrorMsg"))).getText();
			reportStep("Error message for body Id is not registered: "+ErrorMsg, "pass", true);
			
		}else {
			reportStep("Error message verification failed--body Id is not registered", "fail", true);
		}
		String BodyId=tdrow.get("BodyId").split(",")[0];
		//entering a registered body without prefix S
		sendKeys("Body Id field", By.xpath(prop.getProperty("Deliver_V60_Tasks.BodyId.Text")),BodyId);
		//clicking on Add button
		anyClick("Add button", By.xpath(prop.getProperty("Deliver_V60_Tasks.Add.Button")));
				
		//verifying error msg for without prefix S
		if(verifyElementExist("Error msg for prefix with S", By.xpath(prop.getProperty("Deliver_V60_Tasks.ErrorMsg")))==true) {
			String ErrorMsg=driver.findElement(By.xpath(prop.getProperty("Deliver_V60_Tasks.ErrorMsg"))).getText();
			reportStep("Error message for Prefix with S: "+ErrorMsg, "pass", true);
			
		}else {
			reportStep("Error message verification failed--with prefix S", "fail", true);
		}

		
		//removing body Id before adding it
		sendKeys("Body Id field", By.xpath(prop.getProperty("Deliver_V60_Tasks.BodyId.Text")),"S"+BodyId );
		//clicking on remove button
		anyClick("Remove button button", By.xpath(prop.getProperty("Deliver_V60_Tasks.Remove.Button")));
		//verifying the message for No Bodies to remove in bundle
		if(verifyElementExist("Error msg for No bodies to remove in bundle", By.xpath(prop.getProperty("Deliver_V60_Tasks.ErrorMsg")))==true) {
			String ErrorMsg=driver.findElement(By.xpath(prop.getProperty("Deliver_V60_Tasks.ErrorMsg"))).getText();
			reportStep("Error message for No bodies to remove in bundle: "+ErrorMsg, "pass", true);
			
		}else {
			reportStep("Error message verification failed--No bodies in bundle", "fail", true);
		}

		//Adding a registered body id
		sendKeys("Body Id field", By.xpath(prop.getProperty("Deliver_V60_Tasks.BodyId.Text")),"S"+BodyId );
		//clicking on add button
		anyClick("Add button", By.xpath(prop.getProperty("Deliver_V60_Tasks.Add.Button")));
		
		BodyId=tdrow.get("BodyId").split(",")[1];

		//removing the body ID which is not in the bundle
		sendKeys("Body Id field", By.xpath(prop.getProperty("Deliver_V60_Tasks.BodyId.Text")),"S"+BodyId );
		//clicking on remove button
		anyClick("Remove button button", By.xpath(prop.getProperty("Deliver_V60_Tasks.Remove.Button")));
		//verfying the error message for body id is not in bundle
		if(verifyElementExist("Error msg for Given body id is not in bundle", By.xpath(prop.getProperty("Deliver_V60_Tasks.ErrorMsg")))==true) {
			String ErrorMsg=driver.findElement(By.xpath(prop.getProperty("Deliver_V60_Tasks.ErrorMsg"))).getText();
			reportStep("Error message for Given body id is not in bundle: "+ErrorMsg, "pass", true);
			
		}else {
			reportStep("Error message verification failed--Given body is not in bundle", "fail", true);
		}
		
		//adding a body id with prefix small s
		BodyId=tdrow.get("BodyId").split(",")[2];
		sendKeys("Body Id field", By.xpath(prop.getProperty("Deliver_V60_Tasks.BodyId.Text")),"s"+BodyId );	
		
		//clicking on add button
		anyClick("Add button", By.xpath(prop.getProperty("Deliver_V60_Tasks.Add.Button")));
		BodyId=tdrow.get("BodyId").split(",")[3];

		//adding one more body registered body id
		sendKeys("Body Id field", By.xpath(prop.getProperty("Deliver_V60_Tasks.BodyId.Text")),"s"+BodyId );	
		
		//clicking on add button
		anyClick("Add button", By.xpath(prop.getProperty("Deliver_V60_Tasks.Add.Button")));
	
		
		//removing registered body id and verifying trailer id field remains same
		sendKeys("Body Id field", By.xpath(prop.getProperty("Deliver_V60_Tasks.BodyId.Text")),"S"+BodyId );
		sendKeys("Trailer Id field", By.xpath(prop.getProperty("Deliver_V60_Tasks.TrailerId.Text")),tdrow.get("Edit_TrailerId_Name") );
		//clicking of remove button
		anyClick("Remove button button", By.xpath(prop.getProperty("Deliver_V60_Tasks.Remove.Button")));
		//verifying message for body id remove
		if(verifyElementExist(" Message for body id removed in bundle", By.xpath(prop.getProperty("Deliver_V60_Tasks.ErrorMsg")))==true) {
			String ErrorMsg=driver.findElement(By.xpath(prop.getProperty("Deliver_V60_Tasks.ErrorMsg"))).getText();
			reportStep(" Message for body id removed in bundle: "+ErrorMsg, "pass", true);
			
		}else {
			reportStep(" message verification failed--body id removed in bundle", "fail", true);
		}
		
		//verification trailer id remains same in the field
		String trailerId= driver.findElement(By.xpath(prop.getProperty("Deliver_V60_Tasks.TrailerId.Text"))).getAttribute("value");
		System.out.println("Traile Id name: "+trailerId);
		if(trailerId.equalsIgnoreCase(tdrow.get("Edit_TrailerId_Name"))==true) {
			reportStep("Trailer Id remains same in the field", "pass", true);
		}else {
			reportStep("Trailer Id not present", "fail", true);
		}
		
		//entering already added trailer id
		BodyId=tdrow.get("BodyId").split(",")[2];
		sendKeys("Body Id field", By.xpath(prop.getProperty("Deliver_V60_Tasks.BodyId.Text")),"s"+BodyId );	
		//clicking on add button
		anyClick("Add button", By.xpath(prop.getProperty("Deliver_V60_Tasks.Add.Button")));
		//verifying the error message
		if(verifyElementExist("Error message for already registered body id", By.xpath(prop.getProperty("Deliver_V60_Tasks.ErrorMsg")))==true) {
			String ErrorMsg=driver.findElement(By.xpath(prop.getProperty("Deliver_V60_Tasks.ErrorMsg"))).getText();
			reportStep("Error message for already registered body id: "+ErrorMsg, "pass", true);
			
		}else {
			reportStep("Error message verification failed--already registered body id", "fail", true);
		}

		
		driver.findElement(By.xpath(prop.getProperty("Deliver_V60_Tasks.BodyId.Text"))).clear();
		
		//selecting trailer return radio buton as yes
		  anyClick("Yes Radio Button", By.xpath(prop.getProperty("Deliver_V60_Tasks.EmptyTrailerReturn.Radio.Yes")));
		//entering trailer name to deliver
		 // sendKeys("Trailer Id field", By.xpath(prop.getProperty("Deliver_V60_Tasks.TrailerId.Text")),tdrow.get("Edit_TrailerId_Name") );
		//clicking on deliver button
		  anyClick("Deliver button", By.xpath(prop.getProperty("Deliver_V60_Tasks.Deliver.Button")));
		  //handling the alert
		  isAlertPresent("Cancel");
		  
		//clicking on deliver button
		  anyClick("Deliver button", By.xpath(prop.getProperty("Deliver_V60_Tasks.Deliver.Button")));
		  //handling the alert
		  isAlertPresent("Ok");
		  //entering some values in the input field and clearing it
		  sendKeys("Trailer Id field", By.xpath(prop.getProperty("Deliver_V60_Tasks.TrailerId.Text")),tdrow.get("Edit_TrailerId_Name") );
		  //clicking on clear button
		  anyClick("Clear button", By.xpath(prop.getProperty("Deliver_V60_Tasks.Clear.Button")));
		  reportStep("#BVerification of delivery of registered Normal and Repair V60 bodies from Deliver V60 Tasks screen to Ghent Test case Id:3940--completed#C", "pass", false);
			
		
	}
	
	
	

	//13 Verify the V60 Packaging note for the delivered bodies from Deliver V60 tasks screen Test case Id:3941
	
	public void V60_PackagingNote_Screen_print() throws InterruptedException, AWTException{
		tdrow= excelUtils.testCaseRetrieve(refTestDataName,sheetName);
		
		//click on display button
		anyClick("Display", By.xpath(prop.getProperty("V60_Package_Note.Display.Button")));
		
		//verify the message only 15 days are displayed
		
		if(verifyElementExist("Message for only 15 days records are displayed", By.xpath(prop.getProperty("V60_Package_Note.Message")))==true) {
			String ErrorMsg=driver.findElement(By.xpath(prop.getProperty("V60_Package_Note.Message"))).getText();
			reportStep("Message for only 15 days records are displayed: "+ErrorMsg, "pass", true);
			
		}else {
			reportStep("Message for only 15 days records are displayed--not displayed", "fail", true);
		}

		//clicking on print button

		anyClick("Print  button", By.xpath(prop.getProperty("V60_Package_Note.Print.button")));
		
		//window handles
		 Set<String>winid=driver.getWindowHandles();
		 Iterator<String> it=winid.iterator();
		 String mainWindow=it.next();
		 System.out.println("mainWindow: "+mainWindow);
		 String cWindow=it.next();
		 System.out.println("cWindow: "+cWindow);
		
		 driver.switchTo().window(cWindow); 
		 System.out.println("Switched to cWindow: "+cWindow);		 
		 
		 Robot robot = new Robot();
		 
		 //clicking on cancel
		 robot.keyPress(KeyEvent.VK_TAB);	
		 Thread.sleep(2000);
	     robot.keyRelease(KeyEvent.VK_TAB);
	    Thread.sleep(2000);
		 
		    robot.keyPress(KeyEvent.VK_ENTER);
		    Thread.sleep(2000);
		    robot.keyRelease(KeyEvent.VK_ENTER);
		driver.switchTo().window(mainWindow);
		//clicking on clear button
		anyClick("Clear button", By.xpath(prop.getProperty("V60_Package_Note.Clear.Button")));
		//entereing delivered trailer id name
				
		//entering invalid trailer id name and veridying the message
		sendKeys("Trailer id field", By.xpath(prop.getProperty("V60_Package_Note.TrailerId.Text")), getRandomString(5));	
		//clicking on display button
		anyClick("Display", By.xpath(prop.getProperty("V60_Package_Note.Display.Button")));
		//verifying error message
				
		if(verifyElementExist("Error message for trailer id not found", By.xpath(prop.getProperty("V60_Package_Note.ErrorMessage")))==true) {
			String ErrorMsg=driver.findElement(By.xpath(prop.getProperty("V60_Package_Note.ErrorMessage"))).getText();
			reportStep("Error message for trailer id not found: "+ErrorMsg, "pass", true);
			
		}else {
			reportStep("Error message for trailer id not found-not displayed", "fail", true);
		}

		 
				    reportStep("#BVerification of V60 Packaging note for the delivered bodies from Deliver V60 tasks screen Test case Id:3941--completed#C", "pass", false);
	}

	
	//15  Verify the functionality of V60 Packaging Note screen
	
	
		public void V60_PackagingNote_Screen() throws InterruptedException, AWTException{
			tdrow= excelUtils.testCaseRetrieve(refTestDataName,sheetName);
			
			//click on display button
			anyClick("Display", By.xpath(prop.getProperty("V60_Package_Note.Display.Button")));
			
			//verify the message only 15 days are displayed
			
			if(verifyElementExist("Message for only 15 days records are displayed", By.xpath(prop.getProperty("V60_Package_Note.Message")))==true) {
				String ErrorMsg=driver.findElement(By.xpath(prop.getProperty("V60_Package_Note.Message"))).getText();
				reportStep("Message for only 15 days records are displayed: "+ErrorMsg, "pass", true);
				
			}else {
				reportStep("Message for only 15 days records are displayed--not displayed", "fail", true);
			}

			//clicking on clear button
			anyClick("Clear button", By.xpath(prop.getProperty("V60_Package_Note.Clear.Button")));
			//entereing delivered trailer id name
			
			 sendKeys("Trailer Id field", By.xpath(prop.getProperty("V60_Package_Note.TrailerId.Text")),tdrow.get("TrailerIdName") );
			//click on display button
			anyClick("Display", By.xpath(prop.getProperty("V60_Package_Note.Display.Button")));
			reportStep("Trailer id is displayed as expected", "pass", true);

			anyClick("Clear button", By.xpath(prop.getProperty("V60_Package_Note.Clear.Button")));
			
			//entering invalid trailer id name and veridying the message
			sendKeys("Trailer id field", By.xpath(prop.getProperty("V60_Package_Note.TrailerId.Text")), getRandomString(5));	
			//clicking on display button
			anyClick("Display", By.xpath(prop.getProperty("V60_Package_Note.Display.Button")));
			//verifying error message
			

			if(verifyElementExist("Error message for trailer id not found", By.xpath(prop.getProperty("V60_Package_Note.ErrorMessage")))==true) {
				String ErrorMsg=driver.findElement(By.xpath(prop.getProperty("V60_Package_Note.ErrorMessage"))).getText();
				reportStep("Message for only 15 days records are displayed: "+ErrorMsg, "pass", true);
				
			}else {
				reportStep("Message for only 15 days records are displayed--not displayed", "fail", true);
			}
			
			
			//clicking on clear button
			anyClick("Clear field", By.xpath(prop.getProperty("V60_Package_Note.Clear.Button")));
			//clicking on from date
			anyClick("From date", By.xpath(prop.getProperty("V60_Package_Note.FromDate")));
			reportStep("From date displayed as expected", "pass", true);
			//clicking on to date
			anyClick("To date", By.xpath(prop.getProperty("V60_Package_Note.ToDate")));
			reportStep("To date displayed as expected", "pass", true);
			//clearing the fields
			anyClick("Clear button", By.xpath(prop.getProperty("V60_Package_Note.Clear.Button")));
			
			
			
			//selcting date
			// Enter From Date to select
			if (!tdrow.get("From_Date").equals("")) {

				// Converting date to DD/MMM/YYYY
				String date = dtDateConversion(tdrow.get("From_Date"));
				// Click the calendar
				anyClick("From Date", By.xpath(prop.getProperty("V60_Package_Note.FromDate")));

				// Select the date
				dtCalendarDateSelection("Calendar_ManageAlarms_From", date);
				// Select the time
				// Enter From Time to select
				if (!tdrow.get("From_Time").equals("")) {

					// Converting date to DD/MMM/YYYY
					String time = tsTimeConversion(tdrow.get("From_Time"));
					// Click the calendar

					// Select the time
					tsCalendarTimeSelection("Calendar_Time_ManageAlarms_From", time);

				}

			}
			//enter TO date to select
			// Enter To Date to select
			if (!tdrow.get("To_Date").equals("")) {
				// Converting date to DD/MMM/YYYY
				String date = dtDateConversion(tdrow.get("To_Date"));
				// Click the calendar
				anyClick("To Date", By.xpath(prop.getProperty("V60_Package_Note.ToDate")));
				// Select the date
				dtCalendarDateSelection("Calendar_ManageAlarms_To", date);
				// Enter From Time to select
				if (!tdrow.get("To_Time").equals("")) {

					// Converting date to DD/MMM/YYYY
					String time = tsTimeConversion(tdrow.get("To_Time"));
					// Click the calendar

					// Select the time
					tsCalendarTimeSelection("Calendar_Time_ManageAlarms_To", time);

				}

			}

			//clicking on display button
			anyClick("Display button", By.xpath(prop.getProperty("V60_Package_Note.Display.Button")));
			//validating the error message for invalid date
			if(verifyElementExist("Inavlid date error message", By.xpath(prop.getProperty("V60_Package_Note.ErrorMessage")))==true) {
				String errorMsg=driver.findElement(By.xpath(prop.getProperty("V60_Package_Note.ErrorMessage"))).getText();
				reportStep("Inavlid date error message: "+errorMsg, "pass", true);
			}else {
				reportStep("Invalid date error message is not validated", "fail", true);
			}
			
			reportStep("#B Verification of functionality of V60 Packaging Note screen Test case Id:3943--completed#C", "pass", false);
			
	}

		
		//16 Verify the functionality of V60 Normal and Repair screens Test case Id:3944
		public void V60_Normal_Repair_Screens() throws InterruptedException{

			tdrow= excelUtils.testCaseRetrieve(refTestDataName,sheetName);
			//clicking on clear button
			anyClick("Clear Button", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.Clear")));
			//clicking on display button
			anyClick("Display button", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.Display")));
			//verifying only 15 days records are displayed
			if(verifyElementExist("Message for only 15 days records are displayed", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.ErrorMessage")))==true) {
				String Message=driver.findElement(By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.ErrorMessage"))).getText();
				reportStep("Message for only 15 days records displayed :"+Message, "pass", true);
			}else {
				reportStep("Message for only 15 days records displayed is not verified", "fail", true);
			}
			//verification for empty RFID
			//clicking on request print body information button
			anyClick("Request body information button", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.RequestBodyInformation.Button")));
			//verifying error message
			if(verifyElementExist("Error message for RFID is required", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.ErrorMessage")))==true) {
				String Message=driver.findElement(By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.ErrorMessage"))).getText();
				reportStep("Error message for RFID is required :"+Message, "pass", true);
			}else {
				reportStep("Error message for RFID is required is not verified", "fail", true);
			}
			//verification for empty printer id
			sendKeys("RFID field", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.Text")), tdrow.get("RFID").split(",")[0]);
			selectDropDownValue("Printer dropdown", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.Station.Dropdown")), "");
			//clicking in display button
			anyClick("Display button", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.Display")));
			//validating error message for station is required
			if(verifyElementExist("Error message for Station is required", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.ErrorMessage")))==true) {
				String Message=driver.findElement(By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.ErrorMessage"))).getText();
				reportStep("Error message for Station is required :"+Message, "pass", true);
			}else {
				reportStep("Error message for Station is required is not verified", "fail", true);
			}
			
			//validating error for empty no. of labels
			selectDropDownValue("Printer dropdown", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.Station.Dropdown")), tdrow.get("Normal_Printer"));
			//selecting null value in no.of labels dropdown
			selectDropDownValue("No.of Labels dropdown", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.NumberofLabels.Dropdown")), "");
			//clicking on request body id button
			anyClick("Request body information button", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.RequestBodyInformation.Button")));
			//verfying the message
			if(verifyElementExist("Error message for No. of prints is required", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.ErrorMessage")))==true) {
				String Message=driver.findElement(By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.ErrorMessage"))).getText();
				reportStep("Error message for No. of prints is required :"+Message, "pass", true);
			}else {
				reportStep("Error message for No. of prints is required is not verified", "fail", true);
			}
			//Reprinting
			sendKeys("Not registered RFID", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.Text")), tdrow.get("RFID_NotRegistered"));
			//clicking on reprint button
			anyClick("Reprint button", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.Reprint")));
			//verifying the message
			if(verifyElementExist("Error message for No data found", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.ErrorMessage")))==true) {
				String Message=driver.findElement(By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.ErrorMessage"))).getText();
				reportStep("Error message for No data found :"+Message, "pass", true);
			}else {
				reportStep("Error message for No data found is not verified", "fail", true);
			}

		
			//reprint with empty printer id'
			selectDropDownValue("Printer dropdown", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.Station.Dropdown")), "");
			//clicking on reprint button
			anyClick("Reprint button", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.Reprint")));
			//verifying the error message station is required
			if(verifyElementExist("Error message for Station is required", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.ErrorMessage")))==true) {
				String Message=driver.findElement(By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.ErrorMessage"))).getText();
				reportStep("Error message for station is required"+Message, "pass", true);
			}else {
				reportStep("Error message for station is required is not verified", "fail", true);
			}
			//reprint with empty labels
			selectDropDownValue("Printer dropdown", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.Station.Dropdown")), tdrow.get("Normal_Printer"));
			//selecting null value in no.of labels dropdown
			selectDropDownValue("No.of Labels dropdown", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.NumberofLabels.Dropdown")), "");
			//clicking on reprint
			anyClick("Reprint button", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.Reprint")));
			//verifing error message
			if(verifyElementExist("Error message for No. of prints is required", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.ErrorMessage")))==true) {
				String Message=driver.findElement(By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.ErrorMessage"))).getText();
				reportStep("Error message for No. of preints is required"+Message, "pass", true);
			}else {
				reportStep("Error message for No. of prints is required is not verified", "fail", true);
			}
		
			//cliking on reprint with all the details
			sendKeys("RFID field", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.Text")), tdrow.get("RFID").split(",")[0]);
			selectDropDownValue("Printer dropdown", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.Station.Dropdown")), tdrow.get("Normal_Printer"));
			selectDropDownValue("No.of Labels dropdown", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.NumberofLabels.Dropdown")), tdrow.get("Normal_Label"));
			//clicking on reprint
			anyClick("Reprint button", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.Reprint")));
			//reprint verification
			String ReprintStatus=driver.findElement(By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.Table")+"/tbody/tr/td[4]")).getText();
			String ReprintDate=driver.findElement(By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.Table")+"/tbody/tr/td[3]")).getText();
			reportStep("Reprinted Status: "+ReprintStatus+" Date: "+ReprintDate, "pass", true);
			
			//InValid RFID scan and alarm function
			
			
			//
			
			reportStep("#BVerification of functionality of V60 Normal and Repair screens Test case Id:3944--completed#C", "pass", false);
			
			
		}
	
		
		
			//17 Verify the Calender field from all V60 screens Test case Id:3945
		
	public void Calender_Function() throws InterruptedException{
		tdrow= excelUtils.testCaseRetrieve(refTestDataName,sheetName);
		
		//selecting from date
		//click on from date
		if (!tdrow.get("From_Date_Screen").equals("")) {

			// Converting date to DD/MMM/YYYY
			String date = dtDateConversion(tdrow.get("From_Date_Screen"));
			// Click the calendar
			anyClick("From Date", By.xpath(prop.getProperty("V60_Package_Note.FromDate")));

			// Select the date
			dtCalendarDateSelection("Calendar_ManageAlarms_From", date);
			// Select the time
			// Enter From Time to select
			if (!tdrow.get("From_Time").equals("")) {

				// Converting date to DD/MMM/YYYY
				String time = tsTimeConversion(tdrow.get("From_Time"));
				// Click the calendar

				// Select the time
				tsCalendarTimeSelection("Calendar_Time_ManageAlarms_From", time);

			}

		}
		//enter TO date to select
		// Enter To Date to select
		if (!tdrow.get("To_Date_Screen").equals("")) {
			// Converting date to DD/MMM/YYYY
			String date = dtDateConversion(tdrow.get("To_Date_Screen"));
			// Click the calendar
			anyClick("To Date", By.xpath(prop.getProperty("V60_Package_Note.ToDate")));
			// Select the date
			dtCalendarDateSelection("Calendar_ManageAlarms_To", date);
			// Enter From Time to select
			if (!tdrow.get("To_Time").equals("")) {

				// Converting date to DD/MMM/YYYY
				String time = tsTimeConversion(tdrow.get("To_Time"));
				// Click the calendar

				// Select the time
				tsCalendarTimeSelection("Calendar_Time_ManageAlarms_To", time);

			}

		}
		//clicking on display button
		anyClick("Display button", By.xpath(prop.getProperty("V60_Package_Note.Display.Button")));
		
	}
		

//19 Verify the maximum no. Of bodies adding in a Trailer from Deliver V60 Tasks screen
	
	public void MaxNoOfBodies() throws InterruptedException{
		
		 tdrow = excelUtils.testCaseRetrieve(refTestDataName, "V432");
	        String BodyID=tdrow.get("BodyId");
	        anyClick("Clear Button", By.xpath(prop.getProperty("Deliver_V60_Tasks.Clear.Button")));
	        for(int i=0;i<=4;i++) {
	        	String strBodyId="S"+ BodyID.split(",")[i];
	        	System.out.println("strBodyId: "+strBodyId);
	        	sendKeys("BodyId field", By.xpath(prop.getProperty("Deliver_V60_Tasks.BodyId.Text")),strBodyId );
	        	//adding the BodyId
	        	anyClick("Add Button", By.xpath(prop.getProperty("Deliver_V60_Tasks.Add.Button")));
	        	
	        }
	    //verifying the error message for 5th body id
	        if(verifyElementExist("Only 4 body ids are allowed error message", By.xpath(prop.getProperty("Deliver_V60_Tasks.MaxNo.ErrorMsg")))==true) {
	        	String errorMsg= driver.findElement(By.xpath(prop.getProperty("Deliver_V60_Tasks.MaxNo.ErrorMsg"))).getText();
	        	reportStep("Error message for adding more than 4 body ids: "+errorMsg, "pass", true);
	        	reportStep("#B19 Verify the maximum no. Of bodies adding in a Trailer from Deliver V60 Tasks screen --Test case completed#C", "pass", false);
	        	
	   
	        }else {
	        	reportStep("Error message  verification for adding more than 4 body id --failed: ", "fail", true);
	        	reportStep("19 Verify the maximum no. Of bodies adding in a Trailer from Deliver V60 Tasks screen --Test case failed", "fail", false);
	        }
		
	}
	
	//10 Verify the Deliver V60 Tasks screen availability when VCLAS V60 nodes are Up
	//V60 node active and inactive method
	public void V60_Nodes_Up_Down(String Status) throws InterruptedException{
		
		//navigating to nodes screen
		Vclas_Assignment vclasAsgn = new Vclas_Assignment();
				anyClick("Home button ", By.xpath(prop.getProperty("Vclas_Home.Home.Button")));
				
				anyClick("Administration Tab", By.xpath(prop.getProperty("Vclas_Home.Administration.Button")));
				
				anyClick("Nodes menu", By.xpath(prop.getProperty("Vclas_Administration.Nodes.Button")));
	// entering into search field
				if(verifyElementExist("Search Text box", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Search.Textbox")))) {
					
					// sending fpos to search textbox
					sendKeys("Search textbox", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Search.Textbox")),"V60");

					// clicking search button
					anyClick("Vclas Node screen search button click",By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Search.Button")));

					
					JavascriptExecutor js = (JavascriptExecutor) driver; 
					js.executeScript("window.scrollBy(0,1000)");

					if(Status.equalsIgnoreCase("Active")) {
						//making v60 node as active
						if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.V60.Active")))==true) {
							reportStep("V60 node is already active", "pass", true);
							System.out.println("V60 node is already active");
						}else {
							driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.V60.Inactive"))).click();
							//clicking on save button
							anyClick("Save button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Save.Button")));
							Thread.sleep(1000);
							
			        		
							//handling of alert box
							driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.V60.Alert.GreenButton"))).click();
							Thread.sleep(1000);
							//need to handle one more alert if present
							if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.V60.Alert.GreenButton")))) {
							driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.V60.Alert.GreenButton"))).click();
							}
							anyClick("Refresh button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Refresh.Button")));
							//clicking on send to MAS button
							anyClick("Send Mads to MAS button", By.xpath(prop.getProperty("Vclas_Administration.Nodes.SendMadstoMAS.Button")));
							reportStep("V60 node is made as active", "pass", true);
							System.out.println("V60 node is made as active");
						}
						
						
					}else if(Status.equalsIgnoreCase("Inactive")) {
						//making V60 node as inactive
						if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.V60.Inactive")))==true) {
							reportStep("V60 node is already Inactive", "pass", true);
							System.out.println("V60 node is already inactive");
						}else {
							driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.V60.Active"))).click();
							//clicking on save button
							anyClick("Save button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Save.Button")));
							Thread.sleep(1000);
						
			        		
							//handling of alert box
							driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.V60.Alert.GreenButton"))).click();
							Thread.sleep(1000);
							//need to handle one more alert if present
							if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.V60.Alert.GreenButton")))==true) {
							driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.V60.Alert.GreenButton"))).click();
							}
							anyClick("Refresh button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Refresh.Button")));
							//clicking on send to MAS button
							anyClick("Send Mads to MAS button", By.xpath(prop.getProperty("Vclas_Administration.Nodes.SendMadstoMAS.Button")));
							reportStep("V60 node is made as Inactive", "pass", true);
							System.out.println("V60 node is made as inactive");
						}
					}
					
					vclasAsgn.Vclas_Assignment_Navigate();

					// clicking Assignment list button
					driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Assignment.List.Button"))).get(0).click();

					
					
			}
		
	}
	
	public void Delivery_V60_Tab_Open(String Status) throws InterruptedException{
		if(verifyElementExist("Deliver V60 tab", By.xpath(prop.getProperty("V90.DeliverV90_Tasks.BodyId.Text")))==true) {
			if(Status.equalsIgnoreCase("Active")) {
				reportStep("Delivery V60 Tab is open when V60 nodes are up in VCLAS ", "pass", true);
				reportStep("#B10 Verify the Deliver V60 Tasks screen availibility when VCLAS V60 nodes are Up--Tect case completed#C", "pass", false);
			}else {
				reportStep("Delivery V60 Tab is open when V60 nodes are down in VCLAS ", "pass", true);
				reportStep("#B11 Verify the Deliver V60 Tasks screen availibility when VCLAS V60 nodes are Down--Test case completed", "pass", false);
			}
		}
		//*[@id="form:tree"]/table/tbody/tr/td[2]/div/table[7]/tbody/tr/td[2]/div/span[1]
		
	}

	//03 Verify the created default values are reflecting or not in both Scanning screens 
    
    public void V432_OuttakeStation_Screen() throws InterruptedException, AWTException{
           tdrow= excelUtils.testCaseRetrieve(refTestDataName,sheetName);

           //clicking on V60 Register default values for outtake station button
           //anyClick("Display button", By.xpath(prop.getProperty("V60_Register_Default_Values.Menu")));
           
           //Clicking on V60 Outtake station display button
           anyClick("Display button", By.xpath(prop.getProperty("V60_Register_Default_Values.Display.Button")));
           
           //Click and edit printer id and labels for Repair station
           anyClick("Repair Edit Button", By.xpath(prop.getProperty("V60_Register_Default_Values.Table.Edit.Button").replace("&Value", "REPAIR STN")));
           
           
           //Change the Printer and label id for Repair station
           selectDropDownValue("Repair Printer values", By.xpath(prop.getProperty("V60_Register_Default_Values.Create.Printer.Dropdown")), tdrow.get("Repair_Printer"));
           selectDropDownValue("Repair Default Label values", By.xpath(prop.getProperty("V60_Register_Default_Values.Create.DefaultNoOfLabels.Dropdown")), tdrow.get("Repair_Label"));
           
           
           //clicking on save button
           anyClick("Save Button", By.xpath(prop.getProperty("V60_Register_Default_Values.Edit.Save.Button")));
           
           //Clicking on Clear button
           anyClick("Clear Button", By.xpath(prop.getProperty("V60_Register_Default_Values.Clear.Button")));
           
           //Clicking on V60 Outtake station display button
           anyClick("Display button", By.xpath(prop.getProperty("V60_Register_Default_Values.Display.Button")));
           
           //Click and edit printer id and labels for Normal station
           anyClick("Repair Edit Button", By.xpath(prop.getProperty("V60_Register_Default_Values.Table.Edit.Button").replace("&Value", "NORMAL STN")));
           
           //Change the Printer and label id for Normal station
           selectDropDownValue("Normal Printer values", By.xpath(prop.getProperty("V60_Register_Default_Values.Create.Printer.Dropdown")), tdrow.get("Normal_Printer"));
           selectDropDownValue("Normal Default Label values", By.xpath(prop.getProperty("V60_Register_Default_Values.Create.DefaultNoOfLabels.Dropdown")), tdrow.get("Normal_Label"));
           
           //clicking on save button
           anyClick("Save Button", By.xpath(prop.getProperty("V60_Register_Default_Values.Edit.Save.Button")));
           
    }
    
    
    public void V432_scan_Repair() throws InterruptedException, AWTException{
           tdrow= excelUtils.testCaseRetrieve(refTestDataName,sheetName);

           //To Compare the Printer id and label in Repair screen
           String PrinterId=driver.findElement(By.xpath(prop.getProperty("V60_ScanRFID_RepairStation.Station.Dropdown"))).getAttribute("value");
           
           if(PrinterId.equalsIgnoreCase(tdrow.get("Repair_Printer"))) {
                 reportStep("Repair Station Printer ID is showing successfully", "pass", true);
                 
                 String LabelId=driver.findElement(By.xpath(prop.getProperty("V60_ScanRFID_RepairStation.NumberofLabels.Dropdown"))).getAttribute("value");
                 
           if(LabelId.equalsIgnoreCase(tdrow.get("Repair_Label"))) {
                        reportStep("Repair Label ID is showing successfully", "pass", true);
                        
                 
    }
    
           }
    
           }
    
    public void V432_scan_Normal() throws InterruptedException, AWTException{
           tdrow= excelUtils.testCaseRetrieve(refTestDataName,sheetName);

           //To Compare the Printer id and label in Repair screen
           String PrinterId=driver.findElement(By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.Station.Dropdown"))).getAttribute("value");
           
           if(PrinterId.equalsIgnoreCase(tdrow.get("Normal_Printer"))) {
                 reportStep("Normal Station Printer ID is showing successfully", "pass", true);
                 
                 String LabelId=driver.findElement(By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.NumberofLabels.Dropdown"))).getAttribute("value");
                 
           if(LabelId.equalsIgnoreCase(tdrow.get("Normal_Label"))) {
                        reportStep("Normal Label ID is showing successfully", "pass", true);
                        
                 
    }
    
           }
    
    }
    
    
    
    



           
//25 Verify the functionality of scanning a Normal V60 body in Repair screen

    public void V432_normalscan_repair() throws InterruptedException, AWTException {
           tdrow= excelUtils.testCaseRetrieve(refTestDataName,sheetName);
           String RFID= tdrow.get("RFID");

           
                 // Entering RFID in the text field
                 sendKeys("RFID Text field", By.xpath(prop.getProperty("V60_ScanRFID_RepairStation.Text")),RFID.split(",")[0] );
                 
                 //clicking on Request body Id to scan RFID
                 anyClick("Request Body Id button", By.xpath(prop.getProperty("V60_ScanRFID_RepairStation.RequestBodyInformation.Button")));
                 
                 //verifying the message
                 String ScanningMessage=driver.findElement(By.xpath(prop.getProperty("V60_ScanRFID_RepairStation.ScanRFID.Message"))).getText();
                 
                 if(verifyElementExist("RFID Scanning message", By.xpath(prop.getProperty("V60_ScanRFID_RepairStation.ScanRFID.Message")))==true) {
                 reportStep("RFID scanned: "+RFID.split(",")[0]+" and verified message: "+ScanningMessage, "pass", true); 
                 } else {
                        reportStep("RFID is scanned through repair station", "fail", true);
                 }
                 //clearing the field
                 anyClick("Clear Button", By.xpath(prop.getProperty("V60_ScanRFID_RepairStation.Clear")));
                 
           
           
           
    }
    
    
           
           
//26 Verify the functionality of scanning a Repair V60 body in Normal screen

    public void V432_repairscan_normal() throws InterruptedException, AWTException {
           tdrow= excelUtils.testCaseRetrieve(refTestDataName,sheetName);
           String RFID= tdrow.get("RFID");
           
    
           
                        
                 // Entering RFID in the text field
                 sendKeys("RFID Text field", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.Text")),RFID.split(",")[0] );
                 
                 //clicking on Request body Id to scan RFID
                 anyClick("Request Body Id button", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.RequestBodyInformation.Button")));
                 
                 //verifying the message
                 String ScanningMessage=driver.findElement(By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.ScanRFID.Message"))).getText();
                 
                 if(verifyElementExist("RFID Scanning message", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.ScanRFID.Message")))==true) {
                 reportStep("RFID scanned: "+RFID.split(",")[0]+" and verified message: "+ScanningMessage, "pass", true); 
                 } else {
                        reportStep("RFID is scanned through Normal station", "fail", true);
                 }
                 //clearing the field
                 anyClick("Clear Button", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.Clear")));
                 
           
           
           
    }
    
    //21 Verify the functionality of Re printing for a registered V60 RFID from Normal outtake stations screens

                 public void V432_Reprint_Normalbodyid() throws InterruptedException, AWTException {
                        tdrow= excelUtils.testCaseRetrieve(refTestDataName,sheetName);
                        String RFID= tdrow.get("RFID");
                        
                 
                        
                                      
                               // Entering RFID in the text field
                               sendKeys("RFID Text field", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.Text")),RFID.split(",")[0] );
                               
                               //Change the Printer and label id for Normal station
                               selectDropDownValue("Printer ID values", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.Station.Dropdown")), tdrow.get("Normal_Printer"));
                               selectDropDownValue("Number of lables values", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.NumberofLabels.Dropdown")), tdrow.get("Normal_Label"));
                               
                               
                               //clicking on Reprint button for Reprinting the RFID
                               anyClick("Reprint button", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.Reprint")));
                               
                               
                               //verifying the status message for  Reprinted scanned RFID
                               String ScanningMessage=driver.findElement(By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.StatusColumn"))).getText();
                               
                               if(verifyElementExist("RE-PRINTED Date and time message", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.StatusColumn")))==true) {
                               reportStep(" RFID is Reprinted in Status & Time column : "+RFID.split(",")[0]+" and verified message: "+ScanningMessage, "pass", true);    
                               } else {
                                      reportStep("RFID is not reprinted through Normal station", "fail", true);
                               }
                               
                               
                               //verifying the Reprint column message
                               String ScanningMessage1 =driver.findElement(By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.ReprintColumn"))).getText();
                               
                               if(verifyElementExist("Reprint Column", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.ReprintColumn")))==true) {
                               reportStep("RFID Reprinted YES in Reprint column : "+RFID.split(",")[0]+" and verified message: "+ScanningMessage1, "pass", true);   
                               } else {
                                      reportStep("Reprint column is showing NO through Normal station", "fail", true);
                               }
                               
                               //clearing the field
                               anyClick("Clear Button", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.Clear")));
                               
                        
                        
                        
                 }
    
    
                 //22 Verify the functionality of Re printing for a registered V60 RFID from Repair outtake stations screens

                 public void V432_Reprint_Repairbodyid() throws InterruptedException, AWTException {
                        tdrow= excelUtils.testCaseRetrieve(refTestDataName,sheetName);
                        String RFID= tdrow.get("RFID");
                        
                 
                        
                                      
                               // Entering RFID in the text field
                               sendKeys("RFID Text field", By.xpath(prop.getProperty("V60_ScanRFID_RepairStation.Text")),RFID.split(",")[0] );
                               
                               //Change the Printer and label id for Normal station
                               selectDropDownValue("Printer ID values", By.xpath(prop.getProperty("V60_ScanRFID_RepairStation.Station.Dropdown")), tdrow.get("Repair_Printer"));
                               selectDropDownValue("Number of lables values", By.xpath(prop.getProperty("V60_ScanRFID_RepairStation.NumberofLabels.Dropdown")), tdrow.get("Repair_Label"));
                               
                               
                               //clicking on Reprint button for Reprinting the RFID
                               anyClick("Reprint button", By.xpath(prop.getProperty("V60_ScanRFID_RepairStation.Reprint")));
                               
                               
                               //verifying the status message for  Reprinted scanned RFID
                               String ScanningMessage=driver.findElement(By.xpath(prop.getProperty("V60_ScanRFID_RepairStation.StatusColumn"))).getText();
                               
                               if(verifyElementExist("RE-PRINTED Date and time message", By.xpath(prop.getProperty("V60_ScanRFID_RepairStation.StatusColumn")))==true) {
                               reportStep(" RFID is Reprinted in Status & Time column : "+RFID.split(",")[0]+" and verified message: "+ScanningMessage, "pass", true);    
                               } else {
                                      reportStep("RFID is not reprinted through Repair station", "fail", true);
                               }
                               
                               
                               //verifying the Reprint column message
                               String ScanningMessage1 =driver.findElement(By.xpath(prop.getProperty("V60_ScanRFID_RepairStation.ReprintColumn"))).getText();
                               
                               if(verifyElementExist("Reprint Column", By.xpath(prop.getProperty("V60_ScanRFID_RepairStation.ReprintColumn")))==true) {
                               reportStep("RFID Reprinted YES in Reprint column : "+RFID.split(",")[0]+" and verified message: "+ScanningMessage1, "pass", true);   
                               } else {
                                      reportStep("Reprint column is showing NO through Repair station", "fail", true);
                               }
                               
                               //clearing the field
                               anyClick("Clear Button", By.xpath(prop.getProperty("V60_ScanRFID_RepairStation.Clear")));
                               
                        
                        
                        
                 }      



}
