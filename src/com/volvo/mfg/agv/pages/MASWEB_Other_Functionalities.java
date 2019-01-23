package com.volvo.mfg.agv.pages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.volvo.automation.commonutilities.ExcelUtils;
import com.volvo.mfg.StepDefinition.DriverSuite;

public class MASWEB_Other_Functionalities extends DriverSuite {

	// Excel class object to access the function
		ExcelUtils excelUtils = new ExcelUtils();
		public HashMap<String, String> tdrow;
		
		public void Favourites_Tab() {
			String sheetName = "MASWEB_Other_Functionalities";
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
			
			//clicking on favorites tab
			anyClick("Favorites menu click", By.xpath(prop.getProperty("Masweb_Home.Favorites.Menu")));
			
			//Verifying back to MAS system dropdown 
			verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_Favorites.MasSystem.Dropdown")));
			reportStep("Back to MAS system Menu displayed as expected", "pass", false);
			
			//verifying hantera dropdown
			verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_Favorites.Hantera.Dropdown")));
			reportStep("Hantera Vclas uppdrag Menu displayed as expected", "pass", false);
			
			//Clicking on back to MAS system dropdown
			anyClick("Back to MAS system dropdown click", By.xpath(prop.getProperty("MASWEB_Favorites.MasSystem.Dropdown")));
			
			//WebMAS application car image verify
			verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_Favorites.WebMAS.Car.Image")));
			reportStep("WebMAS appliction car image displayed", "pass", true);
			
			//Clicking on functions menu in WebMAS appliction
			anyClick("Clicking Functions menu in WebMAS Application", By.xpath(prop.getProperty("MASWEB_Favorites.WebMAS.Functions.Menu")));
			
			//Selecting MASWEB menu from dropdown
			anyClick("MASWEB Menu click", By.xpath(prop.getProperty("MASWEB_Favorites.WebMAS.MASWB.Dropdown")));
			
			//Selecting Masweb start page menu
			anyClick("MASWEB start page Menu click", By.xpath(prop.getProperty("MASWEB_Favorites.WebMAS.MASWB.Start.Page.Menu")));
			
			//clicking on favorites tab
			anyClick("Favorites menu click", By.xpath(prop.getProperty("Masweb_Home.Favorites.Menu")));
			
			//Clicking hantera dropdown
			anyClick("Clicking on Hantera Vclas uppdrag Menu", By.xpath(prop.getProperty("MASWEB_Favorites.Hantera.Dropdown")));
			
			//Verifying screen display
			verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_Favorites.Hantera.Page.Verify")));
			reportStep("Task Screen displayed successfully", "pass", true);
			reportStep("#B Verified the functionality of Favorites tab TestCase ID:3207 #C", "Pass", false);
			
		}
		
		public void Help_Tab() {
			
			//clicking on help menu
			anyClick("Help menu click", By.xpath(prop.getProperty("Masweb_Home.Help.Menu")));
			
			//verifying dropdown
			verifyElementExistReturn(By.xpath(prop.getProperty("Masweb_Home.Help.MASWEB.Menu.Page")));
			verifyElementExistReturn(By.xpath(prop.getProperty("Masweb_Home.Help.MASWEB.Realsenotes.Menu")));
			verifyElementExistReturn(By.xpath(prop.getProperty("Masweb_Home.Help.About.MASWEB.Menu")));
			verifyElementExistReturn(By.xpath(prop.getProperty("Masweb_Home.Help.MASWEB.Homepage.Menu")));
			
			//Clicking on MASWEB menu page
			anyClick("MASWEB menu page click", By.xpath(prop.getProperty("Masweb_Home.Help.MASWEB.Menu.Page")));
			
			//Verify the page display
			verifyElementExistReturn(By.xpath(prop.getProperty("Masweb_Home.Help.MASWEB.Homepage.Verify")));
			reportStep("Verified the MASWEB menu page display", "pass", true);
			
			//clicking on help menu
			anyClick("Help menu click", By.xpath(prop.getProperty("Masweb_Home.Help.Menu")));
			
			//Clicking on MASWEB release notes menu page
			anyClick("MASWEB Releasenotes menu page verify", By.xpath(prop.getProperty("Masweb_Home.Help.MASWEB.Realsenotes.Menu")));
			
			//verifying MASWEB release notes page displayed
			verifyElementExistReturn(By.xpath(prop.getProperty("Masweb_Home.Help.MASWEB.Realsenotes.Page.Verify")));
			reportStep("Verified the MASWEB Releasenotes page display", "pass", true);
			
			//clicking on help menu
			anyClick("Help menu click", By.xpath(prop.getProperty("Masweb_Home.Help.Menu")));
			
			//Clicking on About MASWEB menu
			anyClick("About MASWEBPage click", By.xpath(prop.getProperty("Masweb_Home.Help.About.MASWEB.Menu")));
			
			//Verify the About MASWEB page display
			verifyElementExistReturn(By.xpath(prop.getProperty("Masweb_Home.Help.About.MASWEB.Page.Verify")));
			reportStep("Verified About MASWEB menu page display", "pass", true);
			reportStep("#B Verified the functionality of Favorites tab TestCase ID:3212 #C", "Pass", false);
			
			
		}
		
	
	
	
	public void ReportsTab() throws InterruptedException {
		
		String sheetName = "MASWEB_Other_Functionalities";
		// retrieve data from MasWeb_Page
		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		
		//selecting a value from dropdown to display
		//selectDropDownValue("Dropdown", By.xpath(prop.getProperty("Masweb_Home.ReportsInMasweb.DropDown")),"25");
	
		anyClick("Dropdown button", By.xpath(prop.getProperty("Masweb_Home.ReportsInMasweb.DropDown")));
		anyClick("Dropdown value", By.xpath(prop.getProperty("Masweb_Home.ReportsInMasweb.DropDown.Value")));
		anyClick("Display button",By.xpath(prop.getProperty("Masweb_Home.ReportsInMasweb.Display_Button")));
		if(verifyElementExist("Reports Tab Table for selected option", By.xpath(prop.getProperty("Masweb_Home.Reports.Table")))==true) {
			verifyElementExist("Export to excel option", By.xpath(prop.getProperty("Masweb_Home.Reports.ExcelExport.Button")));
			anyClick("Export to excel button", By.xpath(prop.getProperty("Masweb_Home.Reports.ExcelExport.Button")));
			reportStep("Reports Table displayed for selected option with export to excel button ", "pass", true);
		}
		//navigating to reports create and edit tab
		anyClick("Reports Tab", By.xpath(prop.getProperty("Masweb_Home.Reports.Menu")));
		anyClick("Reports Tab edit and create tab", By.xpath(prop.getProperty("Masweb_Home.Reports.CreateAndEdit")));
		
		//clicking on edit button
		anyClick("Edit button", By.xpath(prop.getProperty("Masweb_Home.Reports.Edit.Button")));
		reportStep("Edit button is clicked successfully", "pass", true);
		anyClick("Return button", By.xpath(prop.getProperty("Masweb_Home.Reports.Return.Button")));
		reportStep("Return button is clicked successfully", "pass", true);
		anyClick("Create button is clicked successfully", By.xpath(prop.getProperty("Masweb_Home.Reports.Create.Button")));
		sendKeys("Report Name text box", By.xpath(prop.getProperty("Masweb_Home.Reports.ReportName")), "Test");
		anyClick("Save button", By.xpath(prop.getProperty("Masweb_Home.Reports.Save.Button")));
		//have to give values and save
		
		// new table
		String tableHeaderRow = prop.getProperty("Masweb_Home.Reports.Table") + "/thead/tr";
		String tableObject = prop.getProperty("Masweb_Home.Reports.Table") + "/tbody/tr";
		List<WebElement> rows = driver.findElements(By.xpath(tableObject));
		int colEDIT = htmlTableColumnNamePosition("Edit", tableHeaderRow);
		int colReportName = htmlTableColumnNamePosition("Report name", tableHeaderRow);
		int row_count = rows.size();

		for (int i = 0; i <= row_count; i++) {
			int rowInc = i + 1;
			String strTableData = prop.getProperty("Masweb_Home.Reports.Table") + "/tbody/tr[" + rowInc + "]/td";
			List<WebElement> tableData = driver.findElements(By.xpath(strTableData));
			String strReportName = tableData.get(colReportName).getText();
			System.out.println("strReportName: "+strReportName);
			if(strReportName.equalsIgnoreCase("Test")==true) {
			tableData.get(colEDIT).click();
			anyClick("Remove button",By.xpath(prop.getProperty("Masweb_Home.Reports.Remove.Button")));
			// handling the Alert
			isAlertPresent("ok");
			reportStep(" Reportter name to be Removed: " + strReportName, "Pass", true);
			break;

			}
			
			
		}
		
		
				
		
	}
	
	// Authorization Tab Test case Id:3209
	
	public void Authorization_TransactionTab() throws InterruptedException{
		
		//Authorisation and Transaction Tab
		anyClick("Authorization Tab",By.xpath(prop.getProperty("Masweb_Home.Authorization.Tab")));
		anyClick("Authorization - Transaction Tab", By.xpath(prop.getProperty("Masweb_Home.Authorization.Transaction.Tab")));
		
		//verifying the Authorization Transaztion tab is displayed or not
		if(verifyElementExist("Authorization Transaction tab", By.xpath(prop.getProperty("Masweb_Home.Authorization.NotSearchable.Button")))==true) {
			
			anyClick("Not searchable checkbox", By.xpath(prop.getProperty("Masweb_Home.Authorization.Transaction.NotSearchable.Checkbox")));
			
			anyClick("Not searchable button", By.xpath(prop.getProperty("Masweb_Home.Authorization.Transaction.NotSearchable.Button")));
			
			//verifying the not searchable error message
			if(verifyElementExist("Not Searchable Error message", By.xpath(prop.getProperty("Masweb_Home.Authorization.Transaction.NotSearchable.ErrorMsg")))==true) {
			String NotSearchable_ErrorMsg=driver.findElements(By.xpath(prop.getProperty("Masweb_Home.Authorization.Transaction.NotSearchable.ErrorMsg"))).get(0).getText();
			reportStep("Not searchable Error message: "+NotSearchable_ErrorMsg, "pass", true);
			}else {
				reportStep("Not searchable error message is not present", "fail", true);
			}
			
			anyClick("Searchable Checkbox", By.xpath(prop.getProperty("Masweb_Home.Authorization.Transaction.Searchable.Checkbox")));
			anyClick("Searchable button", By.xpath(prop.getProperty("Masweb_Home.Authorization.Transaction.Searchable.Button")));
			
			//verifying the searchable error message
			if(verifyElementExist("Searchable Error message", By.xpath(prop.getProperty("Masweb_Home.Authorization.Transaction.Searchable.ErrorMsg")))==true) {
				String Searchable_ErrorMsg=driver.findElements(By.xpath(prop.getProperty("Masweb_Home.Authorization.Transaction.Searchable.ErrorMsg"))).get(0).getText();
				reportStep("Searchable Error message: "+Searchable_ErrorMsg, "pass", true);
			}else {
				reportStep("Searchable error message is not present", "fail", true);
			}
			
			//clicking clear button
			anyClick("Clear button", By.xpath(prop.getProperty("Masweb_Home.Authorization.Clear.Button")));
			//Clicking on display button
			anyClick("Display button", By.xpath(prop.getProperty("Masweb_Home.Authorization.Display.Button")));
			
			//verifying for the particular record for Not searchable field
			
			String NotSearchable_InputValue=driver.findElements(By.xpath(prop.getProperty("Masweb_Home.Authorization.Transaction.NotSearchable.InputValue"))).get(0).getText();
			sendKeys("Role Input field", By.xpath(prop.getProperty("Masweb_Home.Authorization.Transaction.Role.InputField")), NotSearchable_InputValue);
			anyClick("Display button", By.xpath(prop.getProperty("Masweb_Home.Authorization.Display.Button")));
			
			// verification of role displayed 
			String RoleDsiplayed=driver.findElements(By.xpath(prop.getProperty("Masweb_Home.Authorization.Transaction.Role.InputField"))).get(0).getAttribute("value");
			if(RoleDsiplayed.equalsIgnoreCase(NotSearchable_InputValue)) {
				reportStep("Displayed only the Record for the Not searchable Role: "+NotSearchable_InputValue, "pass", true);
			}else {
				reportStep("Displayed only the Record for the Not searchable Role: "+RoleDsiplayed+ " Give Input: "+NotSearchable_InputValue, "fail", true);
			}
			
			//clearing the field and displaying the whole table
			
			//clicking clear button
			anyClick("Clear button", By.xpath(prop.getProperty("Masweb_Home.Authorization.Clear.Button")));
			//Clicking on display button
			anyClick("Display button", By.xpath(prop.getProperty("Masweb_Home.Authorization.Display.Button")));
			
			
			// verifying for the particular record for searchable field
			
			String Searchable_InputValue= driver.findElements(By.xpath(prop.getProperty("Masweb_Home.Authorization.Transaction.Searchable.InputValue"))).get(0).getText();
			sendKeys("Role Input field", By.xpath(prop.getProperty("Masweb_Home.Authorization.Transaction.Role.InputField")), Searchable_InputValue);
			
			//verifying the role displayed
			
			 RoleDsiplayed=driver.findElements(By.xpath(prop.getProperty("Masweb_Home.Authorization.Transaction.Role.InputField"))).get(0).getAttribute("value");
			if(RoleDsiplayed.equalsIgnoreCase(Searchable_InputValue)) {
				reportStep("Displayed only the Record for the Not searchable Role: "+Searchable_InputValue, "pass", true);
			}else {
				reportStep("Displayed only the Record for the Not searchable Role: "+RoleDsiplayed+ " Give Input: "+Searchable_InputValue, "fail", true);
			}
			
			//verifying the edit option for the particular role
			
			anyClick("Edit button", By.xpath(prop.getProperty("Masweb_Home.Authorization.Transaction.Role.Edit.Button")));
			
			//verifying the edit page 
			if(verifyElementExist("Edit page", By.xpath(prop.getProperty("Masweb_Home.Authorization.Return.Button")))==true) {
				reportStep("Edit for the selected Role displayed successfully", "pass", true);
				
				// returning to the page without No change
				anyClick("Return button", By.xpath(prop.getProperty("Masweb_Home.Authorization.Return.Button")));
				
				// verifying the message No change is saved
				if(verifyElementExist("No Change message", By.xpath(prop.getProperty("Masweb_Home.Authorization.Transaction.Edit.NoChange.ErrorMsg")))==true) {
					
					String NoChange_Msg=driver.findElements(By.xpath(prop.getProperty("Masweb_Home.Authorization.Transaction.Edit.NoChange.ErrorMsg"))).get(0).getText();
					reportStep("Message for No change is saved: "+NoChange_Msg, "pass", true);
					
				}else {
					reportStep("Message for No change is saved is not verified", "fail", true);
				}
				
			}
			
			
			//clearing and displaying the whole table
			//clicking clear button
			anyClick("Clear button", By.xpath(prop.getProperty("Masweb_Home.Authorization.Clear.Button")));
			//Clicking on display button
			anyClick("Display button", By.xpath(prop.getProperty("Masweb_Home.Authorization.Display.Button")));
			
			
			}
		
	}
	
	
	// Authorization Roles Tab functionality
	
	public void Authorization_RolesTab() throws InterruptedException{
		
		
		String sheetName = "MASWEB_Other_Functionalities";
		// retrieve data from MasWeb_Page
		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		//navigating to Authorization and Roles tab
		anyClick("Authorization Tab",By.xpath(prop.getProperty("Masweb_Home.Authorization.Tab")));
		anyClick("Authorization Roles Tab", By.xpath(prop.getProperty("Masweb_Home.Authorization.Roles.Tab")));
		
		
		//verifying the Roles is displayed or not
		if(verifyElementExist("Authorization-Roles Tab Table", By.xpath(prop.getProperty("Masweb_Home.Authorization.Roles.Table")))== true) {
			reportStep("Authorization-Roles tab is displayed as expected", "pass", true);
			
			//editing the values and return
			anyClick("Edit Button", By.xpath(prop.getProperty("Masweb_Home.Authorization.Roles.Table")+"/tbody/tr[1]/td[6]/input"));
			anyClick("Return button", By.xpath(prop.getProperty("Masweb_Home.Authorization.Return.Button")));
			
			// verifying the message no change saved
			
			if(verifyElementExist("No Change message", By.xpath(prop.getProperty("Masweb_Home.Authorization.Roles.ErrorMsg")))==true) {
				
				String NoChange_Msg=driver.findElements(By.xpath(prop.getProperty("Masweb_Home.Authorization.Roles.ErrorMsg"))).get(0).getText();
				reportStep("Message for No change is saved: "+NoChange_Msg, "pass", true);
				
			}else {
				reportStep("Message for No change is saved is not verified", "fail", true);
			}
			
			//clicking clear button
			anyClick("Clear button", By.xpath(prop.getProperty("Masweb_Home.Authorization.Clear.Button")));
			//Clicking on display button
			anyClick("Display button", By.xpath(prop.getProperty("Masweb_Home.Authorization.Display.Button")));
			
			
			// create functionality
			anyClick("Create button", By.xpath(prop.getProperty("Masweb_Home.Authorization.Create.Button")));
			
			// verifying the Role is required error message
			if(verifyElementExist("Role is required Error message", By.xpath(prop.getProperty("Masweb_Home.Authorization.Roles.ErrorMsg")))==true) {
				String RoleErrorMsg=driver.findElements(By.xpath(prop.getProperty("Masweb_Home.Authorization.Roles.ErrorMsg"))).get(0).getText();
				
				reportStep("Role is require error message: "+RoleErrorMsg, "pass", true);
			}
			
			//creating already existing Role
			String ExistingRole = driver.findElements(By.xpath(prop.getProperty("Masweb_Home.Authorization.Roles.Table")+"/tbody/tr[1]/td[1]")).get(0).getText();
			sendKeys("Role field", By.xpath(prop.getProperty("Masweb_Home.Authorization.Roles.InputField")), ExistingRole);
			anyClick("Create button", By.xpath(prop.getProperty("Masweb_Home.Authorization.Create.Button")));
			
			// verifying the Role already exist error message
			if(verifyElementExist("Roles already excist message", By.xpath(prop.getProperty("Masweb_Home.Authorization.Roles.ErrorMsg")))==true) {
				
				String RoleAlreadyExist_Msg=driver.findElements(By.xpath(prop.getProperty("Masweb_Home.Authorization.Roles.ErrorMsg"))).get(0).getText();
				reportStep("Message for Roles already exist: "+RoleAlreadyExist_Msg, "pass", true);
				
			}else {
				reportStep("Message for Roles already exist is not verified", "fail", true);
			}
			
			
			//creating a New role
			sendKeys("Role field", By.xpath(prop.getProperty("Masweb_Home.Authorization.Roles.InputField")),tdrow.get("Role") );
			anyClick("Create button", By.xpath(prop.getProperty("Masweb_Home.Authorization.Create.Button")));
			
			//sending all the details
			sendKeys("Description field", By.xpath(prop.getProperty("Masweb_Home.Authorization.Roles.Description.Input")),tdrow.get("Description") );
			
			sendKeys("Term field", By.xpath(prop.getProperty("Masweb_Home.Authorization.Roles.Term.Input")),tdrow.get("Term") );
			
			sendKeys("Href Field", By.xpath(prop.getProperty("Masweb_Home.Authorization.Roles.Href.Input")), tdrow.get("Href"));
			
			sendKeys("Categori field", By.xpath(prop.getProperty("Masweb_Home.Authorization.Roles.Categori.Input")), tdrow.get("Catogori"));
			
			//saving the new role
			anyClick("Save button",By.xpath(prop.getProperty("Masweb_Home.Authorization.Save.Button")));
			
			//verifying the saved message
			if(verifyElementExist("Roles already excist message", By.xpath(prop.getProperty("Masweb_Home.Authorization.Roles.ErrorMsg")))==true) {
				
				String NewRole_Msg=driver.findElements(By.xpath(prop.getProperty("Masweb_Home.Authorization.Roles.ErrorMsg"))).get(0).getText();
				reportStep("Message for New role saved: "+NewRole_Msg, "pass", true);
				
			}else {
				reportStep("Message for new role saved is not verified", "fail", true);
			}
		
			//clicking clear button
			anyClick("Clear button", By.xpath(prop.getProperty("Masweb_Home.Authorization.Clear.Button")));
			//Clicking on display button
			anyClick("Display button", By.xpath(prop.getProperty("Masweb_Home.Authorization.Display.Button")));
			
			

		}
		
	}

	
	
}
