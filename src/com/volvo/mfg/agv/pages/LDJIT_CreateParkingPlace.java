package com.volvo.mfg.agv.pages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.volvo.automation.commonutilities.ExcelUtils;
import com.volvo.mfg.StepDefinition.DriverSuite;

public class LDJIT_CreateParkingPlace extends DriverSuite {

	// Excel class object to access the function
			ExcelUtils excelUtils = new ExcelUtils();
			public HashMap<String, String> tdrow;
			String sheetName="MASWEB_LDJIT";
			
			
	public void CreateParkingPlace_Display() throws InterruptedException {
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		//02 Verify Display button functionality in create Parking Place screen Test case Id:1041

		//navigating to LDJIT tab and create parking place tab
		anyClick("LDJIT tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu")));
		anyClick("Yard Zone maintenence tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu.YardZonaMaintenance")));
		anyClick("Create Parking Place tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateParkingPlace")));
		anyClick("Display button",By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateParkingPlace.DisplayButton")));
		if(verifyElementExist("Yard Zone Table ", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateparkingPlace.Table")))==true) {
			reportStep("Existing parking place under places/parking places assigning section is displayed successfully", "pass", true);
			reportStep("#B Verification of Display button functionality in create Parking Place screen Test case Id:1041 completed #C", "pass", false);
		}else {
			reportStep("Existing parking place under places/parking places assigning section is not displayed ", "fail", true);
			reportStep(" Verification of Display button functionality in create Parking Place screen Test case Id:1041 failed ", "fail", false);
		}
		//03 Verify Display  functionality based on fields selected in create Parking Place screen test case:1042
		selectDropDownByIndex("Supplier dropdown", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.Supplier.Dropdown")),tdrow.get("Supplier"));
		anyClick("Display button",By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateParkingPlace.DisplayButton")));
		//fetching the supplier value to verify that the displayed table is for the give criteria
		
	
		
		String Supplier = driver.findElements(By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateparkingPlace.Table")+"/tbody/tr[2]/td[2]")).get(0).getText(); 
		if(Supplier.equalsIgnoreCase(tdrow.get("Supplier"))) {
		reportStep("Selected supplier: "+tdrow.get("Supplier")+" diplayed supplier: "+Supplier+" Table displayed as per the given criteria", "pass", true);
		reportStep("#BVerification Display  functionality based on fields selected in create Parking Place screen test case:1042 completed#C", "pass", false);
		}else {
			reportStep("Selected supplier: "+tdrow.get("Supplier")+" diplayed supplier: "+Supplier+" Table is not displayed as per the given criteria", "fail", true);
			reportStep("Verification Display  functionality based on fields selected in create Parking Place screen test case:1042 failed ", "fail", false);
		}
		//04 verify error message This Check Code is already used Please choose a different Check Code. in Create Parking Place screen Test case:1043
		selectDropDownByIndex("Zone Dropdown", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.Zone.Dropdown")), tdrow.get("Zone"));
		
		String CheckCode = driver.findElements(By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateparkingPlace.Table")+"/tbody/tr[2]/td[5]")).get(0).getText();
		System.out.println("CheckCode: "+CheckCode);
		anyClick("Add Save parking Place button", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.AddSave.Button")));
		// sending sample values
		
		driver.findElements(By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateparkingPlace.Table")+"/tbody/tr[1]/td[4]/input")).get(0).sendKeys(tdrow.get("ParkingPlace"));
		driver.findElements(By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateparkingPlace.Table")+"/tbody/tr[1]/td[5]/input")).get(0).sendKeys(CheckCode);
		driver.findElements(By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateparkingPlace.Table")+"/tbody/tr[1]/td[6]/input")).get(0).sendKeys(tdrow.get("Description"));
		selectRadioButtonByValue("Active Radio button", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.Active.Radio.Button")), tdrow.get("Status"));
		Thread.sleep(2000);
		anyClick("Add Save parking Place button", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.AddSave.Button")));
		// verifying the error message
		if(verifyElementExist("Error message", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.ExistingCheckCode.ErrorMsg")))==true) {
			String ErrMsg=driver.findElement(By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.ExistingCheckCode.ErrorMsg"))).getText();
			reportStep("Error message displayed: "+ErrMsg, "pass", true);
			reportStep("#Bverification of error message This Check Code is already used Please choose a different Check Code. in Create Parking Place screen Test case:1043 completed#C", "pass",true);
			
		}else {
			
			reportStep("verification of error message This Check Code is already used Please choose a different Check Code. in Create Parking Place screen Test case:1043 failed", "fail",true);
			
		}
		
		
		
		
	}
			
	public void CreateParkingPlace_CheckCode() throws InterruptedException{
		
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		
		//navigating to LDJIT tab and create parking place tab
				anyClick("LDJIT tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu")));
				anyClick("Yard Zone maintenence tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu.YardZonaMaintenance")));
				anyClick("Create Parking Place tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateParkingPlace")));
		
		//05 verify error message Check Code is required. in Create Parking Place screen
								//selcting zone from dropdown
				selectDropDownByIndex("Zone Dropdown", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.Zone.Dropdown")), tdrow.get("Zone"));
				
				//clicking add/save button
				anyClick("Add Save parking Place button", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.AddSave.Button")));
				
				
				//entering values to parking place and description alone
				driver.findElements(By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateparkingPlace.Table")+"/tbody/tr[1]/td[4]/input")).get(0).sendKeys(tdrow.get("ParkingPlace"));
				
				driver.findElements(By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateparkingPlace.Table")+"/tbody/tr[1]/td[6]/input")).get(0).sendKeys(tdrow.get("Description"));
				selectRadioButtonByValue("Active Radio button", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.Active.Radio.Button")), tdrow.get("Status"));
				
				anyClick("Add Save parking Place button", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.AddSave.Button")));
				
				
				// verifying the error message
				if(verifyElementExist("Error message", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.WithoutCheckCode.ErrorMsg")))==true) {
					String ErrMsg=driver.findElement(By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.WithoutCheckCode.ErrorMsg"))).getText();
					reportStep("Error message displayed: "+ErrMsg, "pass", true);
					reportStep("#Bverification of  error message Check Code is required. in Create Parking Place screen Test case:1044 completed#C", "pass",true);
					
				}else {
					
					reportStep("verification of  error message Check Code is required. in Create Parking Place screen Test case:1044 failed", "fail",true);
					
				}
	}
			
			
	
	public void CreateParkingPlace_ParkingPlace() throws InterruptedException{
		
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		
		//navigating to LDJIT tab and create parking place tab
				anyClick("LDJIT tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu")));
				anyClick("Yard Zone maintenence tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu.YardZonaMaintenance")));
				anyClick("Create Parking Place tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateParkingPlace")));
		
				//06 verify error message Please add Trailer Areas for the selected zone in Create Parking Place screen
				
			
				//selcting zone from dropdown
				selectDropDownByIndex("Zone Dropdown", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.Zone.Dropdown")), tdrow.get("Zone"));
				
				//clicking add/save button
				anyClick("Add Save parking Place button", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.AddSave.Button")));
				
				
				//entering values to parking place and description alone
				
				driver.findElements(By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateparkingPlace.Table")+"/tbody/tr[1]/td[5]/input")).get(0).sendKeys(tdrow.get("ParkingPlaceCheckCode"));
				driver.findElements(By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateparkingPlace.Table")+"/tbody/tr[1]/td[6]/input")).get(0).sendKeys(tdrow.get("Description"));
				selectRadioButtonByValue("Active Radio button", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.Active.Radio.Button")), tdrow.get("Status"));
				
				anyClick("Add Save parking Place button", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.AddSave.Button")));
				
				
				// verifying the error message
				if(verifyElementExist("Error message", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.WithoutParkingPlace.ErrorMsg")))==true) {
					String ErrMsg=driver.findElement(By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.WithoutParkingPlace.ErrorMsg"))).getText();
					reportStep("Error message displayed: "+ErrMsg, "pass", true);
					reportStep("#B verification of error message Please add Trailer Areas for the selected zone in Create Parking Place screenTest case:1045 completed#C", "pass",true);
					
				}else {
					
					reportStep(" verification of error message Please add Trailer Areas for the selected zone in Create Parking Place screenTest case:1045 failed", "fail",true);

					
				}
	}
			



public void CreateParkingPlace_AddNew() throws InterruptedException{
		
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		
		//navigating to LDJIT tab and create parking place tab
				anyClick("LDJIT tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu")));
				anyClick("Yard Zone maintenence tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu.YardZonaMaintenance")));
				anyClick("Create Parking Place tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateParkingPlace")));
		
				//07 verify message  Trailer Area added successfully.  in Create Parking Place screen Test case Id:1046
				
			
				//selcting zone from dropdown
				selectDropDownByIndex("Zone Dropdown", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.Zone.Dropdown")), tdrow.get("Zone"));
				
				//clicking add/save button
				anyClick("Add Save parking Place button", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.AddSave.Button")));
				Integer CheckCode=getRandomNumber(3);
				//entering values to parking place and description alone
				driver.findElements(By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateparkingPlace.Table")+"/tbody/tr[1]/td[4]/input")).get(0).sendKeys(tdrow.get("ParkingPlace")+CheckCode.toString());
				driver.findElements(By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateparkingPlace.Table")+"/tbody/tr[1]/td[5]/input")).get(0).sendKeys(CheckCode.toString());
				driver.findElements(By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateparkingPlace.Table")+"/tbody/tr[1]/td[6]/input")).get(0).sendKeys(tdrow.get("Description"));
				selectRadioButtonByValue("Active Radio button", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.Active.Radio.Button")), tdrow.get("Status"));
				
				anyClick("Add Save parking Place button", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.AddSave.Button")));
				
				
				// verifying the error message
				if(verifyElementExist("Trailer Addition message", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.Create.Message")))==true) {
					String ErrMsg=driver.findElement(By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.Create.Message"))).getText();
					reportStep(" Message displayed: "+ErrMsg, "pass", true);
					reportStep("#B verification of message  Trailer Area added successfully.  in Create Parking Place screen Test case Id:1046 completed #C", "pass",true);
					
				}else {
					
					reportStep(" verification of message  Trailer Area added successfully.  in Create Parking Place screen Test case Id:1046 failed", "fail",true);

					
				}
	}
			//08 verify the Edit functionality in create parking place

public void CreateParkingPlace_Edit() throws InterruptedException{
	
	tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
	
	//navigating to LDJIT tab and create parking place tab
			anyClick("LDJIT tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu")));
			anyClick("Yard Zone maintenence tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu.YardZonaMaintenance")));
			anyClick("Create Parking Place tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateParkingPlace")));
	
			
			
		
			//selcting zone from dropdown
			selectDropDownByIndex("Zone Dropdown", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.Zone.Dropdown")), tdrow.get("Zone"));
			
			//Editing the created parking place
			driver.findElements(By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateparkingPlace.Table")+"/tbody/tr[2]/td[9]/input")).get(0).click();
			if(verifyElementExist("Parking Place CheckCode  field", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.Edit.CheckCode")))==true) {
				anyClick("Parking Place CheckCode field", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.Edit.CheckCode")));
				reportStep("Parking place checkcod field is editable", "pass", true);
				anyClick("Description field ", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.Edit.Description")));
				reportStep("Description field is editable","Pass",true);
				anyClick("Save button", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.Edit.Save.Button")));
				
			}else {
				reportStep("Edit button is not clicked ", "fail", true);
			}
			
			
			
			
			// verifying the error message
			if(verifyElementExist("Trailer Updation message", By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.ErrorMsg")))==true) {
				String ErrMsg=driver.findElement(By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.ErrorMsg"))).getText();
				reportStep(" Message displayed: "+ErrMsg, "pass", true);
				reportStep("#B verification of Edit functionality in create parking place Test case Id:1047 completed #C", "pass",true);
				
			}else {
				
				reportStep(" verification of Edit functionality in create parking place Test case Id:1047 failed", "fail",true);

				
			}
}
		


	
	
	
}
