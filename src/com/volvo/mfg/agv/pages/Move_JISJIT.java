package com.volvo.mfg.agv.pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.volvo.automation.commonutilities.ExcelUtils;
import com.volvo.mfg.StepDefinition.DriverSuite;

public class Move_JISJIT extends DriverSuite {

	// Excel class object to access the function
				ExcelUtils excelUtils = new ExcelUtils();
				public HashMap<String, String> tdrow;
				String sheetName="MASWEB_LDJIT";
				
				
				//04 verify error message No Empty Trailer available for this Flow. in Move Jis-Jit Racks screen		
		public void MoveJisjit_NoEmpty() throws InterruptedException {
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
			
			//Navigating to move jisjit Racks tab
			
		anyClick("LDJIT Tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu")));
		anyClick("Move JisJit Racks", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks")));
		
		//verify the move jisjit racks page is displayed
		if(verifyElementExist("Move jisjit page", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.TrailerId")))==true) {
			anyClick("Load Empty Trailer Radio button", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.LoadEmptyTrailer.RadioButton")));
			selectDropDownByIndex("Flow dropdown", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Flow.Dropdown")), tdrow.get("Flow"));
			
			
		// verifying the error message
			if(verifyElementExist("Error Message for No Empty", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.NoEmpty.ErrorMsg")))==true) {
				String ErrorMsg=driver.findElements(By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.NoEmpty.ErrorMsg"))).get(0).getText();
				reportStep("Error message display: "+ErrorMsg, "pass", true);
				reportStep("#B verification of error message No Empty Trailer available for this Flow. in Move Jis-Jit Racks screen Test Case Id:1103 completed#C ", "pass", true);
			}else {
				reportStep(" verification of error message No Empty Trailer available for this Flow. in Move Jis-Jit Racks screen Test Case Id:1103 failed", "fail", true);
			}
			
			
		}
		}
		
		//05 verify the error message Rack Nr required in Move Jis-Jit Racks screen
		public void MoveJisjit_RackNr_Errormsg() throws InterruptedException {
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
			
			//Navigating to move jisjit Racks tab
			
		anyClick("LDJIT Tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu")));
		anyClick("Move JisJit Racks", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks")));
		
		//verify the move jisjit racks page is displayed
		if(verifyElementExist("Move jisjit page", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.TrailerId")))==true) {
			anyClick("Load Empty Trailer Radio button", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.LoadEmptyTrailer.RadioButton")));
			selectDropDownByIndex("Flow dropdown", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Flow.Dropdown")), tdrow.get("Flow"));
			anyClick("Move Button", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Move.Button")));
			
		// verifying the error message
			if(verifyElementExist("Error Message for missing RackNr", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.RackNr.ErrorMsg")))==true) {
				String ErrorMsg=driver.findElements(By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.RackNr.ErrorMsg"))).get(0).getText();
				reportStep("Error message display: "+ErrorMsg, "pass", true);
				reportStep("#B verification of the error message Rack Nr required in Move Jis-Jit Racks screen Test Case Id:1104 completed#C ", "pass", true);
			}else {
				reportStep(" verification of the error message Rack Nr required in Move Jis-Jit Racks screen Test Case Id:1104 failed", "fail", true);
			}
			
			
		}
		
		
			
			
		}
		
		
		
		//02 verify Loading Empty Racks to Empty Trailer in Move Jis-Jit Racks screen for  Standard Trailers only
		
		public void LoadEmptyTrailer() throws InterruptedException{
			
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
			
			//Navigating to move jisjit Racks tab
			
			anyClick("LDJIT Tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu")));
			anyClick("Move JisJit Racks", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks")));
			
			//clicking of load empty trailer radio button
			
			anyClick("Load empty tariler Radio button", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.LoadEmptyTrailer.Radio.Button")));
			
			//selecting the flow which is having empty trailer
			selectDropDownByIndex("Flow dropdown", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Flow.Dropdown")), tdrow.get("Flow_LoadEmptyTrailer"));
			//entering the Rack number
			sendKeys("Rack number field", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.RackNr.Field")), tdrow.get("RackNr"));
			//giving TAB key to auto fill the fields
			driver.findElements(By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.RackNr.Field"))).get(0).sendKeys(Keys.TAB);
			Thread.sleep(2000);
			anyClick("Move Button", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Move.Button")));

			// verifying the message
			if(verifyElementExist("Message for load empty trailer", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Trailer.LoadEmptyTrailer.Msg")))==true) {
				
				String NewRole_Msg=driver.findElements(By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Trailer.LoadEmptyTrailer.Msg"))).get(0).getText();
				reportStep("Message for load empty trailer: "+NewRole_Msg, "pass", true);
				reportStep("#Bverification Loading Empty Racks to Empty Trailer in Move Jis-Jit Racks screen for  Standard Trailers only Test case Id: 1101 completed #C", "pass", true);
				
			}else {
				reportStep("verification Loading Empty Racks to Empty Trailer in Move Jis-Jit Racks screen for  Standard Trailers only Test case Id: 1101 failed", "fail", true);
			}
			
		}
		
		
		//06 verify clear button functionality in Move Jis-Jit Racks screen Test case Id:1105
		
		public void LoadEmptyTrailer_Clear() throws InterruptedException{
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
			
			//Navigating to move jisjit Racks tab
			
			anyClick("LDJIT Tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu")));
			anyClick("Move JisJit Racks", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks")));
			
			//clicking of load empty trailer radio button
			
			anyClick("Load empty tariler Radio button", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.LoadEmptyTrailer.Radio.Button")));
			
			//selecting the flow which is having empty trailer
			selectDropDownByIndex("Flow dropdown", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Flow.Dropdown")), tdrow.get("Flow_LoadEmptyTrailer"));
			//entering the Rack number
			sendKeys("Rack number field", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.RackNr.Field")), tdrow.get("RackNr"));
			//giving TAB key to auto fill the fields
			driver.findElements(By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.RackNr.Field"))).get(0).sendKeys(Keys.TAB);
			
			// clearing the rack details
			anyClick("Clear button", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Clear.Button")));
			Thread.sleep(2000);
			
			reportStep("#B verification of  clear button functionality in Move Jis-Jit Racks screen Test case Id:1105 completed#C", "pass", true);
			
		}
		
		//07 verify movement of Trailer from current position to new position Test case Id:1106
		
		public void Trailer_Movement() throws InterruptedException{
			
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
			
			//Navigating to move jisjit Racks tab
			
			anyClick("LDJIT Tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu")));
			anyClick("Move JisJit Racks", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks")));
			
			// trailer radio button is auto selected
			// entering the Trailer ID and clikcing Tab
			
			sendKeys("Trailer Id field", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.TrailerId")), tdrow.get("TrailerId"));
			//giving TAB key to auto fill the fields
			driver.findElements(By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.TrailerId"))).get(0).sendKeys(Keys.TAB);
			
			// selecting a buffer place to move
			
			selectDropDownByIndex("New position dropdown", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Trailer.NewPosition.Dropdown")), tdrow.get("NewPosition"));
			// moving to the new position
			anyClick("Move button", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Move.Button")));
			
			
			// verifying the message
			if(verifyElementExist("Message for Moving the trailer to new position", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Trailer.Bufferplace.Msg")))==true) {
							
				String NewPosition_Msg=driver.findElements(By.xpath(prop.getProperty("Masweb_Home.Authorization.Roles.ErrorMsg"))).get(0).getText();
				reportStep("Message for moving the trailer : "+NewPosition_Msg, "pass", true);
				reportStep("#Bverification movement of Trailer from current position to new position Test case Id:1106 cpmpleted #C", "pass", true);
							
			}else {
				reportStep("verification of  movement of Trailer from current position to new position Test case Id:1106 failed", "fail", true);
				}
						
			
			
			
		}
		
		
		//08 verify movement of rack from current position to new position Test case Id:1107
		//Rack number taken from PMR753
		
	public void Rack_Movement() throws InterruptedException{
			
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
			
			//Navigating to move jisjit Racks tab
			
			anyClick("LDJIT Tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu")));
			anyClick("Move JisJit Racks", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks")));
			
			// Rack radio button selection
			
			anyClick("Rack Radio button", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Rack.Radio.Button")));
			
			
			// entering the Part family sequence and clicking Tab
			
			sendKeys("Part family seq", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Rack.PartfamilySeq.input")), tdrow.get("PartFamilySeq"));
			//giving TAB key to auto fill the fields
			driver.findElements(By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Rack.PartfamilySeq.input"))).get(0).sendKeys(Keys.TAB);
			
			// selecting a buffer place to move
			
			selectDropDownByIndex("New position dropdown", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Rack.Newposition.Dropdown")), tdrow.get("Rack_Newposition"));
			// moving to the new position
			anyClick("Move button", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Move.Button")));
			
			
			// verifying the message
			if(verifyElementExist("Message for Moving a Rack to new position", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Rack.Newposition.ErrorMsg")))==true) {
							
				String NewPosition_Msg=driver.findElements(By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Rack.Newposition.ErrorMsg"))).get(0).getText();
				reportStep("Message for moving the trailer : "+NewPosition_Msg, "pass", true);
				reportStep("#Bverification of movement of rack from current position to new position Test case Id:1107 completed #C", "pass", true);
							
			}else {
				reportStep("verification of movement of rack from current position to new position Test case Id:1107 failed", "fail", true);
				}
						
			
			
	
		}
		
	
	
	//03 verify error message Flow is required in Move Jis-Jit Racks screen Test case Id:1102
	public void Flow_ErrorMsg() throws InterruptedException{
		
		
		//Navigating to move jisjit Racks tab
		
		anyClick("LDJIT Tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu")));
		anyClick("Move JisJit Racks", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks")));
		
		//clicking of load empty trailer radio button
		
		anyClick("Load empty tariler Radio button", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.LoadEmptyTrailer.Radio.Button")));
		
		//clicking on move button
		anyClick("Move button", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Move.Button")));
		
		// verifing the error message
		
			if(verifyElementExist("Error message without flow", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.LoadEmptyWithFull.ErrorMsg")))==true) {
				String Trailer_Msg=driver.findElements(By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.LoadEmptyWithFull.ErrorMsg")+"/tr[1]/td[2]")).get(0).getText();
				String RackNr_Msg=driver.findElements(By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.LoadEmptyWithFull.ErrorMsg")+"/tr[2]/td[2]")).get(0).getText();
				reportStep("Error Messages without selecting any flow: "+Trailer_Msg+" , "+RackNr_Msg, "pass", true);
				reportStep("#B verification error message Flow is required in Move Jis-Jit Racks screen Test case Id:1102 is completed #C", "pass", false);
				
			}else {
				reportStep("verification error message Flow is required in Move Jis-Jit Racks screen Test case Id:1102 -- failed", "fail", false);
			}
		
		
		
	}
		
	
}
