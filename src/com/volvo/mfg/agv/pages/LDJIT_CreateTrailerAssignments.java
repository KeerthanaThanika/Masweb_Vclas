package com.volvo.mfg.agv.pages;

import java.util.HashMap;

import org.openqa.selenium.By;

import com.volvo.automation.commonutilities.ExcelUtils;
import com.volvo.mfg.StepDefinition.DriverSuite;

public class LDJIT_CreateTrailerAssignments extends DriverSuite {


	// Excel class object to access the function
			ExcelUtils excelUtils = new ExcelUtils();
			public HashMap<String, String> tdrow;
			String sheetName="MASWEB_LDJIT";
			//01 To verify error message Place is required  in Create Trailer Assignments screen	
		
			public void CreateTrailerAssignments_ParkingPlaceError() throws InterruptedException {
			
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
			
			//navigating to yard zone maintenance tab and create trailer assignments
			anyClick("LDJIT tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu")));
			anyClick("Yard Zone maintenence tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu.YardZonaMaintenance")));
			anyClick("Create Trailer Assignment menu", By.xpath(prop.getProperty("LDJIT_YardZone_Create_Trailers_Assignment")));
			
			//verifying the trailer assignment page 
			if(verifyElementExist("Trailer Assignment page", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateTrailerAssignments.form")))==true) {
				reportStep("Create Trailer Assignments page displayed as expected", "pass", true);
			
			}else {
				reportStep("Create Trailer Assignments page is not displayed", "fail", true);
			}
			
			// selecting only flow and verifying the error msg for parking place
			
			selectDropDownByIndex("Flow Name", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateTrailerAssignment.Flow.Dropdown")), tdrow.get("Flow"));
			anyClick("Bring Full Button", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateTrailerAssignments.BringFull.Button")));
			
			//verifying the error message for missing parking place
			
			if(verifyElementExist("Error Message for Missing parking place", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateTrailerAssignments.missingPlace.ErrorMsg")))==true) {
				String ErrorMsg= driver.findElements(By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateTrailerAssignments.missingPlace.ErrorMsg"))).get(0).getText();
				reportStep("Error Message for creating trailer without place: "+ErrorMsg, "Pass",true);
				reportStep("#B verification of error message Place is required  in Create Trailer Assignments screen Test Case Id:1048 completed #C", "pass", true);
			}else {
				
				reportStep("#B verification of error message Place is required  in Create Trailer Assignments screen Test Case Id:1048 completed #C", "pass", true);
			}
			
		}
	
			//02 To verify error message Flow is required in Create Trailer Assignments screen Test case Id:1049

			public void CreateTrailerAssignments_FlowError() throws InterruptedException {
				
				tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
				
				//navigating to yard zone maintenance tab and create trailer assignments
				anyClick("LDJIT tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu")));
				anyClick("Yard Zone maintenence tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu.YardZonaMaintenance")));
				anyClick("Create Trailer Assignment menu", By.xpath(prop.getProperty("LDJIT_YardZone_Create_Trailers_Assignment")));
				
				//verifying the trailer assignment page 
				if(verifyElementExist("Trailer Assignment page", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance..CreateTrailerAssignment.ParkingPlace.Dropdown")))==true) {
					reportStep("Create Trailer Assignments page displayed as expected", "pass", true);
				
				}else {
					reportStep("Create Trailer Assignments page is not displayed", "fail", true);
				}
				
				// selecting only place and verifying the error msg for missing flow
				
				selectDropDownByIndex("Parking Place", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance..CreateTrailerAssignment.ParkingPlace.Dropdown")), tdrow.get("Place"));
				anyClick("Bring Full Button", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateTrailerAssignments.BringFull.Button")));
				
				//verifying the error message for missing flow
				
				if(verifyElementExist("Error Message for Missing FLow", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateTrailerAssignments.missingFlow.ErrorMsg")))==true) {
					String ErrorMsg= driver.findElements(By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateTrailerAssignments.missingFlow.ErrorMsg"))).get(0).getText();
					reportStep("Error Message for creating trailer without flow: "+ErrorMsg, "Pass",true);
					reportStep("#B verification of error message Flow is required in Create Trailer Assignments screen Test case Id:1049 completed #C", "pass", true);
				}else {
					
					reportStep("verification of error message Flow is required in Create Trailer Assignments screen Test case Id:1049 failed", "fail", true);
				}
				
			}

			//03 To verify error message Trailer Id is not available for this Flow in Create Trailer Assignments screen
			
			public void CreateTrailerAssignments_TrailerId() throws InterruptedException {
				
				tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
				
				//navigating to yard zone maintenance tab and create trailer assignments
				anyClick("LDJIT tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu")));
				anyClick("Yard Zone maintenence tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu.YardZonaMaintenance")));
				anyClick("Create Trailer Assignment menu", By.xpath(prop.getProperty("LDJIT_YardZone_Create_Trailers_Assignment")));
				
				//verifying the trailer assignment page 
				if(verifyElementExist("Trailer Assignment page", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateTrailerAssignment.ParkingPlace.Dropdown")))==true) {
					reportStep("Create Trailer Assignments page displayed as expected", "pass", true);
				
				}else {
					reportStep("Create Trailer Assignments page is not displayed", "fail", true);
				}
				
				// selecting the flow and parking place
				
				selectDropDownByIndex("Parking Place", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateTrailerAssignment.ParkingPlace.Dropdown")), tdrow.get("Place"));
				selectDropDownByIndex("Flow Name", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateTrailerAssignment.Flow.Dropdown")), tdrow.get("Flow"));
				anyClick("Bring Full Button", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateTrailerAssignments.BringFull.Button")));
				
				//verifying the error message for trailer id not available for a particular flow
				
				if(verifyElementExist("Error Message for Trailer Id not available for this flow", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateTrailerAssignment.TrailerId.ErrorMsg")))==true) {
					String ErrorMsg= driver.findElements(By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateTrailerAssignment.TrailerId.ErrorMsg"))).get(0).getText();
					reportStep("Error Message for TrailerId not available: "+ErrorMsg, "Pass",true);
					reportStep("#B  verification of error message Trailer Id is not available for this Flow in Create Trailer Assignments screen Test case Id:1050 completed #C", "pass", true);
				}else {
					
					reportStep("verification of error message Trailer Id is not available for this Flow in Create Trailer Assignments screen Test case Id:1050 failed ", "fail", true);
				}
				
			}

			
			//04 To verify error message Active assignments exists at this place in Create Trailer Assignments screen Test case Id:1051
			//pre-condition user should have active assignments at the place
			
			public void ActiveAssignments_ErrorMsg() throws InterruptedException{
				
				tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
				
				//navigating to LDJIT and Create trailer assignments page
				//navigating to yard zone maintenance tab and create trailer assignments
				anyClick("LDJIT tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu")));
				anyClick("Yard Zone maintenence tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu.YardZonaMaintenance")));
				anyClick("Create Trailer Assignment menu", By.xpath(prop.getProperty("LDJIT_YardZone_Create_Trailers_Assignment")));
				
				// bring full assignments in the place where already active assignments present
				
				// selecting the flow and parking place
				
				selectDropDownByIndex("Parking Place", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateTrailerAssignment.ParkingPlace.Dropdown")), tdrow.get("Place_Active_Assignment"));
				selectDropDownByIndex("Flow Name", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateTrailerAssignment.Flow.Dropdown")), tdrow.get("Flow_Active_Assignment"));
				anyClick("Bring Full Button", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateTrailerAssignments.BringFull.Button")));
				
				//verifying the Error message for active assignments
				
				if(verifyElementExist("Active assignments Error Message", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateTrailerAssignment.ActiveAssignments.ErrorMsg")))==true) {
				String Active_ErrorMsg=driver.findElements(By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateTrailerAssignment.ActiveAssignments.ErrorMsg"))).get(0).getText();	
				reportStep("Active Assignment Error message: "+Active_ErrorMsg, "pass", true);
				reportStep("#B verification of  error message Active assignments exists at this place in Create Trailer Assignments screen Test case Id:1051 completed#C", "pass", true);
				}else {
					reportStep("verification of  error message Active assignments exists at this place in Create Trailer Assignments screen Test case Id:1051 failed", "fail", true);
				}
				
			}
			
			
			//07 To Exchange Empty Trailer with Full trailer in Create Trailer Assignments screen Test case Id:1054
			
			public void ExchangeEmptyWithFull() throws InterruptedException{
				
				tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
				
				//navigating to LDJIT and Create trailer assignments page
				//navigating to yard zone maintenance tab and create trailer assignments
				anyClick("LDJIT tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu")));
				anyClick("Yard Zone maintenence tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu.YardZonaMaintenance")));
				anyClick("Create Trailer Assignment menu", By.xpath(prop.getProperty("LDJIT_YardZone_Create_Trailers_Assignment")));
				
				
				
				// selecting the flow and parking place
				
				selectDropDownByIndex("Parking Place", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateTrailerAssignment.ParkingPlace.Dropdown")), tdrow.get("ExchangeEmpty_Place"));
				selectDropDownByIndex("Flow Name", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateTrailerAssignment.Flow.Dropdown")), tdrow.get("ExchangeEmpty_Flow"));
				anyClick("Bring Full Button", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateTrailerAssignments.ExchangeEmptyWithFull.Button")));
				
				//verifying the Exchange empty with full Message 
				
				if(verifyElementExist("Exchange empty with full Message", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateTrailerAssignment.ExchangeEmptyWithFull.Msg")))==true) {
				String Msg=driver.findElements(By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateTrailerAssignment.ExchangeEmptyWithFull.Msg"))).get(0).getText();	
				reportStep("Exchange empty with full message: "+Msg, "pass", true);
				reportStep("#B To Exchange Empty Trailer with Full trailer in Create Trailer Assignments screen Test case Id:1054 completed#C", "pass", true);
				}else {
					reportStep("To Exchange Empty Trailer with Full trailer in Create Trailer Assignments screen Test case Id:1054 failed", "fail", true);
				}
				
			}	
			
			
			


}
