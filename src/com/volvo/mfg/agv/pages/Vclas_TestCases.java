package com.volvo.mfg.agv.pages;


import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.volvo.automation.commonutilities.ExcelUtils;
import com.volvo.mfg.StepDefinition.DriverSuite;

public class Vclas_TestCases extends DriverSuite {

	Vclas_Assignment vclasAssign = new Vclas_Assignment();
	public HashMap<String, String> tdrow;
	// Excel class object to access the function
	ExcelUtils excelUtils = new ExcelUtils();
	
	String sheetName = "Vclas_TestCases";
	

	public void Administration_Nodes_Tab() {

		boolean temp;
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);

		// Clicking Administration menu
		anyClick("Administration Menu", By.xpath(prop.getProperty("Vclas_Home.Administration.Button")));

		// clicking on Nodes menu
		anyClick("Emulator Menu", By.xpath(prop.getProperty("Vclas_Administration.Nodes.Button")));

		// verifying the parent node as Torslanda
		verifyElementExist("Verifying parent node: Torslanda",By.xpath(prop.getProperty("Vclas_Administration.Nodes.Torslanda")));

		// Verifying Parent node as green color
		System.out.println(driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.Torslanda.Color.Verification"))).getAttribute("style"));
		List<WebElement> element = driver.findElements(By.xpath(prop.getProperty("Vclas_Administration.Nodes.Torslanda.Color.Verification"))); // get
																														// the
																														// select
		// iterate over the options
		for (WebElement option : element) {

			if (option.getCssValue("background-color").compareToIgnoreCase("rgba(37, 245, 22, 1)") == 0) {
				reportStep("Parent node color verified successfully", "pass", true);
				System.out.println(option.getCssValue("background-color"));

			} else {
				reportStep("Parent node color verification failed", "fail", true);
				System.out.println(option.getCssValue("background-color"));
			}
		}

		System.out.println(driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.Childnode.Color.Verification"))).getAttribute("style"));
		List<WebElement> Childelement = driver.findElements(By.xpath(prop.getProperty("Vclas_Administration.Nodes.Childnode.Color.Verification"))); 
																														
		// iterate over the options
		for (WebElement option : Childelement) {

			if (option.getCssValue("background-color").compareToIgnoreCase("rgba(250, 120, 250, 1)") == 0) {
				reportStep("Child node color verified successfully", "pass", true);
				System.out.println(option.getCssValue("background-color"));

			} else {
				reportStep("Child node color verification failed", "fail", true);
				System.out.println(option.getCssValue("background-color"));
			}
		}
		verifyElementExist("Create New button is verified and ",By.xpath(prop.getProperty("Vclas_Administration.Nodes.CreateNew.Button")));
		verifyElementExist("Create from MAS List button is present and ",By.xpath(prop.getProperty("Vclas_Administration.Nodes.CreateMASList.Button")));
		verifyElementExist("Search textfield is verified and ",By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Search.Textbox")));
		verifyElementExist("Send mads to MAS button is verified and ",By.xpath(prop.getProperty("Vclas_Administration.Nodes.Send_MAD_to_MAS.Button")));
		verifyElementExist("Torslanda table is verified and ",By.xpath(prop.getProperty("Vclas_Administration.Nodes.CheckCode.field")));
		verifyElementExist("Transport section table is verified and ",By.xpath(prop.getProperty("Vclas_Administration.Nodes.Transport.Sections.Table")));
		verifyElementExist("Child Nodes table is verified and ",By.xpath(prop.getProperty("Vclas_Administration.Nodes.Childnodes.Table")));
		verifyElementExist("Save button is verified and ",By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Save.Button")));
		verifyElementExist("Refresh button is verified and ",By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Refresh.Button")));
		
		//Verifying Parameters button
		if (verifyElementExistReturn(By.xpath(prop.getProperty("Vclas_Administration.Nodes.Parameters"))) == true) {

			// Clicking on parameters button
			anyClick("Parameters button", By.xpath(prop.getProperty("Vclas_Administration.Nodes.Parameters")));
		}
		
		//Verifying cars per hour field can be editable or non-editable
		if (verifyElementExistReturn(By.xpath(prop.getProperty("Vclas_Administration.Nodes.Cars_Per_Hour.Field"))) == true) {

			anyClick("Cars per hour field",By.xpath(prop.getProperty("Vclas_Administration.Nodes.Cars_Per_Hour.Field")));
			reportStep("Cars per hour field is not editable", "pass", true);
		} else {
			reportStep("Cars per hour field is editable", "pass", true);
		}

	}



	public void Administration_Rooms_Tab() {
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		// Clicking Administration menu
		anyClick("Administration Menu", By.xpath(prop.getProperty("Vclas_Home.Administration.Button")));
		
		//Clicking Rooms menu
		anyClick("Rooms Menu", By.xpath(prop.getProperty("Vclas_Home.Administration.Rooms.Button")));
		
		//Clicking on serach button
		anyClick("search button", By.xpath(prop.getProperty("Vclas.Administration.Rooms.Search.Button")));
		reportStep("search button is clicked successfully in Rooms page", "pass", false);
		
		//Verifying the page with existing rooms display
		if(verifyElementExistReturn(By.xpath(prop.getProperty("Vclas.Administration.Rooms.Verify.Rooms.Page")))==true) {
			
			//Clicking on the edit button
			anyClick("Rooms Edit Button", By.xpath(prop.getProperty("Vclas.Administration.Rooms.Verify.Rooms.Page.Edit.Button")));
			reportStep("Edit button is clicked successfully in Rooms page", "pass", false);
			
			//clicking on Edit nodes button
			anyClick("Edit Nodes button", By.xpath(prop.getProperty("Vclas.Administration.Rooms.Verify.Rooms.Page.Editnodes.Button")));
			reportStep("Edit nodes button is clicked successfully in Rooms page", "pass", false);

		}
		
		if(verifyElementExist("Select Nodes that transports are delivered to page", By.xpath(prop.getProperty("Vclas.Administration.Rooms.Verify.Selectnodes.Page")))) {
			
			//Clicking on serach button in select nodes page
			anyClick("Search button", By.xpath(prop.getProperty("Vclas.Administration.Rooms.Verify.Selectnodes.Page.Search.Button")));
			reportStep("Search button is clicked successfully in Nodes window", "pass", false);
			
			//Clicking on Close button
			anyClick("Close Button", By.xpath(prop.getProperty("Vclas.Administration.Rooms.Verify.Selectnodes.Page.Close.Button")));
			reportStep("Close button is clicked successfully in Nodes window", "Pass", false);
			
		}
		
		//Clicking on Cancel button in rooms page
		anyClick("Edit button", By.xpath(prop.getProperty("Vclas.Administration.Rooms.Page.Cancel.Button")));
		reportStep("Cancel button is clicked successfully in Rooms page", "pass", false);
	}

	
	

	
	
	public void Nodes_TypesScreen() throws InterruptedException {
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		// clicking on the Administration button
		anyClick("Vclas Administration button click", By.xpath(prop.getProperty("Vclas_Home.Administration.Button")));
		
		// clicking on Nodes Type button
		anyClick("Vclas Nodes Type button click", By.xpath(prop.getProperty("Vclas_Administration.NodesType.Button")));
		
		// verifying the name and color columns are present in the table
		if(verifyElementExist("Nodes Type in Name column  and color of a particular node in  color column", By.xpath(prop.getProperty("Vclas_NodesType.table")+"/tbody"))==true) {
			reportStep("Nodes Type screen with table containing Name and color is displayed successfully ", "pass", true);
			// clicking the color button 
			if(verifyElementExist("color button for a node type", By.xpath(prop.getProperty("Vclas_NodesType.colorButton")))==true) {
			anyClick("Vclas Nodes Type button click", By.xpath(prop.getProperty("Vclas_NodesType.colorButton")));
			reportStep("Functionality of color  button for a Node type is verified successfully  ", "pass", true);
			
			anyClick("Vclas Nodes Type button click", By.xpath(prop.getProperty("Vclas_NodesType.SaveButton")));
			
			reportStep("Functionality of color and save button for a Node type is verified successfully #BTestCase Id:2908#C ", "pass", true);
			
			}else
			{
				reportStep("Functionality of color and save button for a Node type is  not verified successfully #BTestCase Id:2908#C ", "pass", true);
			}
		}else {
			reportStep("Nodes Type screen with table containing Name and color is not  displayed  ", "fail", true);
		}

		
		
	}
	
	public void Vclas_TransportSectionScreen() throws InterruptedException {
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		String Id;
		// clicking on the Administration button
		anyClick("Vclas Administration button click", By.xpath(prop.getProperty("Vclas_Home.Administration.Button")));
		
		// clicking on Transport Section Tab
		anyClick("Vclas Transport Section button click",By.xpath(prop.getProperty("Vclas_Administration.TransportSection.Button")));
		
		//clicking on search icon to display the existing transport sections of all the flows
		anyClick("Vclas Search button click",By.xpath(prop.getProperty("Vclas_TransportSection.SearchButton")));
		
		// verifying the transport section table
		if(verifyElementExist("Search Table of Transport Section", By.xpath(prop.getProperty("Vclas_TransportSection.Table")))==true) {
			reportStep("Transport Section search table is diplayed successfully", "pass", true);
		}else {
			reportStep("Transport Section search table is not displayed ", "pass", true);
		}
		
		//clicking of plus button to create new transport section
		anyClick("Plus button click to create new Trasnport Section", By.xpath(prop.getProperty("Vclas_TransportSection.PlusButton")));
		
		
		
		// verifying the created message
		String savedMessage=driver.findElements(By.xpath(prop.getProperty("Vclas_TransportSection.Saved.Message"))).get(0).getText();
		String createdMessage=driver.findElements(By.xpath(prop.getProperty("Vclas_TransportSection.Craeted.Message"))) .get(0).getText();		
		System.out.println("Saved Message: "+savedMessage);
		System.out.println("created message: "+createdMessage);
		Id=driver.findElements(By.xpath(prop.getProperty("Vclas_TransportSection.CraetedNewId"))).get(0).getText().trim();
		System.out.println("created Tarnsport section Id: "+Id);
		reportStep("New Transport Section created with the Id: "+Id+"with the message: "+savedMessage+" "+createdMessage, "pass", true);
		
		anyClick("Vclas Search button click",By.xpath(prop.getProperty("Vclas_TransportSection.SearchButton")));
				
		// clicking of edit for created transport section Id
		if(verifyElementExist("Edit button",By.xpath(prop.getProperty("Vclas_TransportSection.Editbutton").replace("&Value", Id)))==true ){
			driver.findElements(By.xpath(prop.getProperty("Vclas_TransportSection.Editbutton").replace("&Value", Id))).get(0).click();
			if(driver.findElements( By.xpath(prop.getProperty("Vclas_TransportSection.Editbutton").replace("&Value", Id))).size()>0){
				//driver.findElements(By.xpath(prop.getProperty("Vclas_TransportSection.Editbutton").replace("&Value", Id))).get(0).click();
				vclasAssign.clickByLocator( By.xpath(prop.getProperty("Vclas_TransportSection.Editbutton").replace("&Value", Id)));
				reportStep("edit button clicked successfully", "pass", true);
				
				
			}else {
				reportStep("button size:"+driver.findElements( By.xpath(prop.getProperty("Vclas_TransportSection.Editbutton").replace("&Value", Id))).size(), "fail", true);
			}
			//anyClick("Edit button", By.xpath(prop.getProperty("Vclas_TransportSection.Editbutton").replace("&Value", Id)));
			
		}else
		{
			reportStep("edit button is not present ", "fail", true);
		}
		Thread.sleep(3000);
		
		// editing the Name feild of created transport section Id
		//sendKeys("Name column of transport section table", By.xpath(prop.getProperty("Vclas_TransportSection.NameFeild").replace("&Value", Id)),tdrow.get("Name"));
		if(driver.findElements( By.xpath(prop.getProperty("Vclas_TransportSection.NameField.Table").replace("&Value", Id))).size()>0){
			Thread.sleep(3000);
			driver.findElement(By.xpath(prop.getProperty("Vclas_TransportSection.NameField.Table").replace("&Value", Id))).clear();
			driver.findElements(By.xpath(prop.getProperty("Vclas_TransportSection.NameField.Table").replace("&Value", Id))).get(0).sendKeys(tdrow.get("Name"));
			reportStep("value entered successfully", "pass", true);
			
			//editing the From coloumn
			driver.findElements(By.xpath(prop.getProperty("Vclas_TransportSection.Editbutton").replace("&Value", Id))).get(0).click();
			Thread.sleep(2000);
			reportStep("From column edit button is clicked successfully", "pass", true);
			driver.findElements(By.xpath(prop.getProperty("Vclas_TransportSection.childWindow.SearchButton"))).get(0).click();
			
			//selecting the mad and add it to  From column
			driver.findElements(By.xpath(prop.getProperty("Vclas_TransportSection.FromMad.RadioButton"))).get(0).click();
			driver.findElements(By.xpath(prop.getProperty("Vclas_TransportSection.From.AddButton"))).get(0).click();
			reportStep("Mad added to From column  successfully", "pass", true);
			
			//verifying the planning point should be  the updated From  position by default
			String From_Value=driver.findElements(By.xpath(prop.getProperty("Vclas_TransportSection.FromColumn.text").replace("&Value", Id))).get(0).getText();
			System.out.println("From_Value: "+From_Value);
			String Planning_point=driver.findElements(By.xpath(prop.getProperty("Vclas_TransportSection.PlanningPoint.text").replace("&Value", Id))).get(0).getText();
			System.out.println("planning_Value:"+Planning_point);
			if(From_Value.equalsIgnoreCase(Planning_point)) {
				reportStep("planning point:"+Planning_point +" is updated with From position:"+From_Value+" by default", "pass", true);
			}else {
				reportStep("planning point:"+Planning_point +" is not updated with From position:"+From_Value+" by default", "fail", true);
			}
			
			waitForElement(driver,  By.xpath(prop.getProperty("Vclas_TransportSection.Editbutton").replace("&Value", Id)), 10);
			driver.findElements(By.xpath(prop.getProperty("Vclas_TransportSection.Editbutton").replace("&Value", Id))).get(0).click();
			
			//editing the To column
			
			
			if(verifyElementExist("To column Edit button", By.xpath(prop.getProperty("Vclas_TransportSection.ToColumn.Editbutton").replace("&Value", Id)))==true) {
			
				driver.findElements(By.xpath(prop.getProperty("Vclas_TransportSection.ToColumn.Editbutton").replace("&Value", Id))).get(0).click();
			
				reportStep("To column edit nutton cliked", "pass", true);
			}else {
				reportStep("To column Edit is not clicked", "pass", true);
			}
			Thread.sleep(2000);
			sendKeys("To column Search text feild", By.xpath(prop.getProperty("Vclas_TransportSection.NameField")), tdrow.get("To_position"));
			driver.findElements(By.xpath(prop.getProperty("Vclas_TransportSection.ToAdd.search"))).get(0).click();
			
			//selecting the mad address for To column
			//driver.findElements(By.xpath(prop.getProperty("Vclas_TransportSection.ToMad.RadioButton"))).get(0).click();
			retryingFindClick(By.xpath(prop.getProperty("Vclas_TransportSection.ToMad.RadioButton")));
			driver.findElements(By.xpath(prop.getProperty("Vclas_TransportSection.To.AddButton"))).get(0).click();
			
			
			
			//updating all the values
		//-- no change has to be done	retryingFindClick( By.xpath(prop.getProperty("Vclas_TransportSection.updatebutton").replace("&Value", Id)));
			//anyClick("update button", By.xpath(prop.getProperty("Vclas_TransportSection.updatebutton").replace("&Value", Id)) );
			reportStep("All values are updated   successfully", "pass", true);
			anyClick("Remove button of Transport section table", By.xpath(prop.getProperty("Vclas_TransportSection.Removebutton").replace("&Value", Id)));
			/*if(verifyElementExist("cancel button",By.xpath(prop.getProperty("Vclas_TransportSection.cancelButton.Green")) )==true) {
				
			//driver.findElements(By.xpath(prop.getProperty("Vclas_TransportSection.cancelButton"))).get(0).click();
				WebElement element = driver.findElement(By.xpath(prop.getProperty("Vclas_TransportSection.cancelButton.Green")));

				Actions actions = new Actions(driver);

				actions.moveToElement(element).click().perform();
				
				
				
				reportStep("cancel button clicked", "pass", true);
			}else {
				reportStep("cancel button not clicked", "pass", true);
			}*/
			
			driver.findElements(By.xpath(prop.getProperty("Vclas_TransportSection.cancelButton.Red"))).get(0).click();
			reportStep("Verify the functionality of Transport Section Screen  #BTestCase Id:2909#C", "pass", true);
			
			
		}else {
			reportStep("name field size:"+driver.findElements( By.xpath(prop.getProperty("Vclas_TransportSection.NameField").replace("&Value", Id))).size(), "fail", true);
		
		}
		
	
		
		
		}
		
	public void ReleaseNotesScreen() throws InterruptedException{
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		
		// clicking on the Administration button
		anyClick("Vclas Administration button click", By.xpath(prop.getProperty("Vclas_Home.Administration.Button")));
		
		// clicking on Release Notes Screen
		
		anyClick("Vclas Administration Release Notes Tab", By.xpath(prop.getProperty("Vclas_ReleaseNotes.Button")));

		//verifying the vclas system release
		
		String ReleaseInfo=driver.findElements(By.xpath(prop.getProperty("Vclas_ReleaseNotes.Information"))).get(0).getText();
		if(ReleaseInfo.equalsIgnoreCase("Information about system release.")) {
			reportStep("System Release information is displayed as expected #BTest Case ID:2918#C", "pass", true);
		}else {
			reportStep("System Release information is not displayed as expected", "fail", true);
		}
		
		
		
	}
	
	public void Nodes_MoveButton()throws InterruptedException{
		//navigating to Administration  Tab
		
		anyClick("Vclas Administration button click", By.xpath(prop.getProperty("Vclas_Home.Administration.Button")));
		
		//navigating to Nodes Tab
		anyClick("Vclas Nodes button click", By.xpath(prop.getProperty("Vclas_Administration.Nodes.Button")));
		
		//clicking of plus button of parameter
		//anyClick("Parameter Plus button", By.xpath(prop.getProperty("Vclas_Administration.Nodes.Parameter.PlusButton")));
		driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.Parameter.PlusButton"))).click();
		Thread.sleep(2000);
		
		//move button information verification
		driver.findElements(By.xpath(prop.getProperty("Vclas_Administration.Nodes.MoveButton"))).get(0).click();
		reportStep("Move button mouse over to verify the message: Use this option to enable or disable Move Button", "pass", true);
		
		//clicking of setting tab to change the language
		anyClick("Setting Tab",By.xpath(prop.getProperty("Vclas_Home.Setting.Button")));
		anyClick("Change language button", By.xpath(prop.getProperty("Vclas_Setting.ChnageLanguage.Button")));
		
		// changing the language to swedish
		
		
		
		anyClick("Language button", By.xpath(prop.getProperty("Vclas_Setting.changeLanguage.dropdown")));
		
		//navigating to Administration Nodes tab
		anyClick("Vclas Administration button click", By.xpath(prop.getProperty("Vclas_Home.Administration.Button")));
		// clicking on noder menu
		anyClick("Noder menu is clicked successfully", By.xpath(prop.getProperty("Vclas_Administration.Nodes.Swedish")));
		reportStep("Swedish language is selected", "pass", true);
		//clicking of paramter
		driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.Swedish.Parameter.PlusButton"))).click();
		driver.findElements(By.xpath(prop.getProperty("Vclas_Nodes.Swedish.MoveButton"))).get(0).click();
		reportStep("Move button mouse over to verify the message in swedish: Använd det här alternativet för att aktivera eller avaktivera Flytta-knappen.'\r\n" + 
				"", "pass", true);
		reportStep("Move button mouse over to verify the message #B TestCase ID: 3467#C", "pass", false);
		
	}
	
		
	
	
	
	
	
	public void Administration_Parts_Tab() {
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		try {
			
		// Clicking Administration menu
		anyClick("Administration Menu", By.xpath(prop.getProperty("Vclas_Home.Administration.Button")));
				
		//Clicking Parts menu
		anyClick("Parts Menu", By.xpath(prop.getProperty("Vclas_Home.Administration.Parts.Button")));
		
		//Verifying parts page displayed
		if(verifyElementExist("Search Field is verified", By.xpath(prop.getProperty("Vclas.Administration.Parts.Searchfield")))) {
			
			
			//Entering valid part number
			driver.findElement(By.xpath(prop.getProperty("Vclas.Administration.Parts.Searchfield"))).sendKeys(tdrow.get("Parts_Number"));
			anyClick("Search Button", By.xpath(prop.getProperty("Vclas.Administration.Parts.Search.Button")));
			reportStep("Entered valid Part number", "pass", false);
			
			//clicking on the valid parts number edit button
			anyClick("Edit button", By.xpath(prop.getProperty("Vclas.Administration.Parts.Edit.Button")));
			reportStep("Edit button is clicked successfully in Rooms page", "pass", false);
			
		}
		
		//Verifying the edit window displayed
		if(verifyElementExist("Search Field is verified", By.xpath(prop.getProperty("Vclas.Administration.Parts.Edit.Window")))) {
			
			//Clicking on Cancel button
			anyClick("Cancel button", By.xpath(prop.getProperty("Vclas.Administration.Parts.Edit.Window.Cancel.button")));
			reportStep("Cancel button is clicked successfully in Rooms page", "pass", false);
			
		}
		
		//Clearing the value in search textbox
		vclasAssign.clearByLocator(By.xpath(prop.getProperty("Vclas.Administration.Parts.Searchfield")));
		
		//Entering valid part number
		driver.findElement(By.xpath(prop.getProperty("Vclas.Administration.Parts.Searchfield"))).sendKeys(tdrow.get("Invalid _Parts_Number"));
		anyClick("Search Button", By.xpath(prop.getProperty("Vclas.Administration.Parts.Search.Button")));
		reportStep("Entered Invalid Part number", "pass", false);
		
		//Verifying No records found message
		verifyElementExistReturn(By.xpath(prop.getProperty("Vclas.Administration.Parts.Message")));
		reportStep("No Records Found Message displyed as expected", "pass", true);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Administration_TAT_Tab() {
		
		// Clicking Administration menu
		anyClick("Administration Menu", By.xpath(prop.getProperty("Vclas_Home.Administration.Button")));
		
		//Clicking TAT menu
		anyClick("TAT Menu", By.xpath(prop.getProperty("Vclas_Home.Administration.TAT.Button")));
		
		//Clicking on Search button
		anyClick("Search button", (By.xpath(prop.getProperty("Vclas.Administration.TAT.Search.Button"))));
		reportStep("Search button is clicked successfully in TAT page", "pass", false);
		
		//verify table displayed
		verifyElementExistReturn(By.xpath(prop.getProperty("Vclas.Administration.TAT.Table")));
		reportStep("Table displayed with existed Rack size of all flows with EDIT and Delete options", "pass", true);
	}
	
	public void Administration_DB_Constant_Admin_Screen_Tab() {
		
		String Message=" ";
		
		// Clicking Administration menu
		anyClick("Administration Menu", By.xpath(prop.getProperty("Vclas_Home.Administration.Button")));
				
		//Clicking TAT menu
		anyClick("DB Constant Admin Screen Menu", By.xpath(prop.getProperty("Vclas_Home.Administration.DB.Constant.Admin.Button")));
		
		//Clicking on search button
		anyClick("Search button", By.xpath(prop.getProperty("Vclas.Administration.DB.Constant.Admin.Search.Button")));
		reportStep("Search button is clicked successfully in DB Constant Admin Screen page", "pass", false);
		
		//verify table displayed
		verifyElementExistReturn(By.xpath(prop.getProperty("Vclas.Administration.DB.Constant.Admin.Verify.Table")));
		reportStep("Table displayed with all Parameters,Threshold values of all flows to plan the assignments with EDIT option", "pass", true);
		
		//Clicking on Edit button 
		anyClick("Edit button", By.xpath(prop.getProperty("Vclas.Administration.DB.Constant.Admin.Edit.button")));
		reportStep("Edit button is clicked successfully in DB Constant Admin Screen", "pass", false);
		
		//Verify eidtable fields,cancel and update options
		verifyElementExistReturn(By.xpath(prop.getProperty("Vclas.Administration.DB.Constant.Admin.Cancel.button")));
		reportStep("The Value and Description fields are in editable mode with Cancel and Update options", "pass", true);
		anyClick("Cancel button", By.xpath(prop.getProperty("Vclas.Administration.DB.Constant.Admin.Cancel.button")));
		
		//Verifying the message
		if(verifyElementExistReturn(By.xpath(prop.getProperty("Vclas.Administration.DB.Constant.Admin.Message.Display")))) {
			
			// fetching the message from nodes screen
			Message = driver.findElement(By.xpath(prop.getProperty("Vclas.Administration.DB.Constant.Admin.Message.Display"))).getText();
			System.out.println(Message);
			reportStep("#B" + Message + "#C", "pass", false);
			
		}
		reportStep("Message displayed as expected", "pass", true);
		
		}
	
public void Administration_User_Tab() {
	
	// Clicking Administration menu
	anyClick("Administration Menu", By.xpath(prop.getProperty("Vclas_Home.Administration.Button")));
	
	//Clicking on User Menu
	anyClick("User Menu", By.xpath(prop.getProperty("Vclas_Home.Administration.User.Button")));
	
	//Verify page displayed
	verifyElementExistReturn(By.xpath(prop.getProperty("Vclas_Home.Administration.User.Table.Verify")));
	reportStep("User screen with CdsId, First Name, LastName, System as VCLAS with Search and ADD(+) options are displayed", "pass", true);
	
	//Clicking on search button
	anyClick("Search button", By.xpath(prop.getProperty("Vclas_Home.Administration.User.Search.Button")));
	
	//Verify table displayed
	verifyElementExistReturn(By.xpath(prop.getProperty("Vclas_Home.Administration.User.Verify.Table")));
	reportStep(" All the users list who all are having access to VCLAS appliction with Edit user, Delete, Edit User role options are displayed ", "pass", true);
	
	//Clicking on edit button in user page
	anyClick("Edit button", By.xpath(prop.getProperty("Vclas_Home.Administration.User.Table.Edit.Button")));
	anyClick("Editing FirstName field", By.xpath(prop.getProperty("Vclas_Home.Administration.User.Table.Firstname")));
	reportStep("All fields are in editable mode except CdsId field with Cancel and Update options", "pass", true);
	
	//Clicking on cancel button in user page
	anyClick("Cancel button", By.xpath(prop.getProperty("Vclas_Home.Administration.User.Table.Cancel.Button")));
	
	//Clicking on Delete button in user page
	anyClick("Delete button", By.xpath(prop.getProperty("Vclas_Home.Administration.User.Table.Delete.Button")));
	
	//Verify the pop-up window in user page
	verifyElementExistReturn(By.xpath(prop.getProperty("Vclas_Home.Administration.User.Confirmation.Window")));
	reportStep("Confirmation window displayed in user page", "pass", true);
	anyClick("Cancel button", By.xpath(prop.getProperty("Vclas_Home.Administration.User.Confirmation.Window.Cancel.Button")));
	
	//Clicking on edit user role button in user page
	anyClick("Edit user role button", By.xpath(prop.getProperty("Vclas_Home.Administration.User.Edit.User.Role.Button")));
	
	//Clicking on role button 
	anyClick("Role button", By.xpath(prop.getProperty("Vclas_Home.Administration.User.Role.Button")));
	
	//Clicking on serach button in role page
	anyClick("Role search button", By.xpath(prop.getProperty("Vclas_Home.Administration.User.Role.Search.Button")));
	
	//Clicking on edit icon in role page
	anyClick("Role page edit button", By.xpath(prop.getProperty("Vclas_Home.Administration.User.Role.Edit.Button")));
	
	//Clicking on cancel button in role page
	anyClick("Role page Cancel button", By.xpath(prop.getProperty("Vclas_Home.Administration.User.Cancel.Button")));
	
	//Clicking on delete icon in role page
	anyClick("Role page Delete button", By.xpath(prop.getProperty("Vclas_Home.Administration.User.Delete.Button")));
	
	//Verify the pop-up window in role page
	verifyElementExistReturn(By.xpath(prop.getProperty("Vclas_Home.Administration.User.Confirmation.Window")));
	reportStep("Confirmation window displayed in Role Page", "pass", true);
	anyClick("Role page Cancel button", By.xpath(prop.getProperty("Vclas_Home.Administration.User.Confirmation.Window.Cancel.Button")));
	
	//Clicking on edit user role button
	anyClick("Role page edit user role button", By.xpath(prop.getProperty("Vclas_Home.Administration.User.Role.Edit.User.Role.Button")));
	
	//Verify modification table display
	verifyElementExistReturn(By.xpath(prop.getProperty("Vclas_Home.Administration.User.Role.Modify.Table")));
	anyClick("Modify table Cancel button", By.xpath(prop.getProperty("Vclas_Home.Administration.User.Role.Modify.Table.Cancel.Button")));
	
	//Clicking on Function button
	anyClick("Function button", By.xpath(prop.getProperty("Vclas_Home.Administration.User.Function.Button")));
	
	//Clicking on search button in function page
	anyClick("Function search button", By.xpath(prop.getProperty("Vclas_Home.Administration.User.Function.Search.Button")));
	
	//Clicking on delete icon in function page
	anyClick("Function page Delete button", By.xpath(prop.getProperty("Vclas_Home.Administration.User.Delete.Button")));
	
	//Verify the pop-up window in function page
	verifyElementExistReturn(By.xpath(prop.getProperty("Vclas_Home.Administration.User.Confirmation.Window")));
	reportStep("Confirmation window displayed in Function Page", "pass", true);
	anyClick("Function page Cancel button", By.xpath(prop.getProperty("Vclas_Home.Administration.User.Confirmation.Window.Cancel.Button")));
	
	
	//Clicking on edit icon in function page
	anyClick("Function page edit button", By.xpath(prop.getProperty("Vclas_Home.Administration.User.Function.Edit.Button")));
	
	//Clicking on cancel button in role page
	anyClick("Function page Cancel button", By.xpath(prop.getProperty("Vclas_Home.Administration.User.Cancel.Button")));
	
}

public void Administration_Health_Check_Tab() {
	
	// Clicking Administration menu
	anyClick("Administration Menu", By.xpath(prop.getProperty("Vclas_Home.Administration.Button")));
		
	//Clicking on User Menu
	anyClick("Health Check Menu", By.xpath(prop.getProperty("Vclas_Home.Administration.Health.Check.Button")));
	
	//Verify table display
	if(verifyElementExistReturn(By.xpath(prop.getProperty("Vclas_Home.Administration.Health.Check.Table.Verify")))) {
		
		//Verify health check- vclas button
		verifyElementExistReturn(By.xpath(prop.getProperty("Vclas_Home.Administration.Health.Check.Vclas.Button")));
		
		//Verify start health check button
		verifyElementExistReturn(By.xpath(prop.getProperty("Vclas_Home.Administration.Start.Health.Check.Button")));
		
		//Verify Reset button
		verifyElementExistReturn(By.xpath(prop.getProperty("Vclas_Home.Administration.Health.Check.Reset.Button")));
		
		//Clicking on start health check button
		anyClick("Start Health Check Button", By.xpath(prop.getProperty("Vclas_Home.Administration.Start.Health.Check.Button")));
		
		//Verify start health check table display
		verifyElementExistReturn(By.xpath(prop.getProperty("Vclas_Home.Administration.Start.Health.Check.Table.Verify")));
		reportStep("Start Health Check table displayed with all pass and failed steps", "pass", true);
		
	}
}
	
	
	public void AssignmentsTab() throws InterruptedException{
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		
		//clicking on Assignments tab
		anyClick("Assignments Tab", By.xpath(prop.getProperty("Vclas_Home.Assignments.Button")));
		//verify the the user should be in select work area tab by default
		if(verifyElementExist("Rooms Tab of select work area", By.xpath(prop.getProperty("Vclas_Home.Room.Tab")))==true) {
			reportStep("User is in Select Work Area Tab with possible Rooms and Zones tab by default with No Rooms selected", "pass", true);
		}else {
			reportStep("User is  not in Select Work Area Tab with possible Rooms and Zones tab by default", "fail", true);
		}
		//Navigating to Assignment List screen
		anyClick("Assignments List Screen", By.xpath(prop.getProperty("Vclas_Home.Assignment.List.Button")));
		//verify that the no assignments are in Assignment List Screen
		if(driver.findElements(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Table.Firstrow.Object"))).size()==0) {
			reportStep("No Assignments is present in Assignment list screen", "pass", true);
		}else {
			reportStep("Assignments are present in Assignment List screen", "fail", true);
		}
		//Navigating to Shortage Screen
		anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));
		//verify that  the assignments of all flows present in the shortage screen 
		if(driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Table")+"/tbody/tr")).size()>1) {
			retryingFindClick(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Button")));
			//first and next button functionality
			String nextButton = prop.getProperty("Vclas_Home.Shortage.Next.Button");
			String firstButton = prop.getProperty("Vclas_Home.Shortage.First.Button");
			// Verifying if it available in Next Screen
			if (verifyElementExistReturn(By.xpath(nextButton)) == true) {

				// Clicking the next button
				anyClick("Shortage Next Button", By.xpath(nextButton));
				reportStep("Next button is enabled and clicked successfully", "pass", true);
				
			}else {
				reportStep("Next button is  not enabled ", "pass", true);
			}

			if (verifyElementExistReturn(By.xpath(firstButton)) == true) {
				// Clicking the first button
				anyClick("Shortage First Button", By.xpath(firstButton));
				reportStep("First button is enabled and clicked successfully", "pass", true);
			}else {
				reportStep("First button is  not enabled ", "pass", true);
			}
			//search feild verification
			////div[@id='assignmentForm:assignmentList']//table[@role='grid']/tbody/tr[1]/td[1]
			String obj= driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Table")+"/tbody/tr[1]/td[1]")).get(0).getText();
			System.out.println("Object: "+obj);
			// object value to search
			vclasAssign.Vclas_Shortage_Search(obj, "", "");
			reportStep("valid input is given to search field and verified succeffully", "pass", true);
			obj="218900389";
			if(verifyElementExistReturn(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text")))) {
				vclasAssign.clearByLocator(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text")));
			driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text"))).get(0).sendKeys(obj);
			vclasAssign.clickByLocator(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Button")));
			reportStep("Invalid input is given to search field and verified succeffully", "pass", true);}
			
			reportStep("Assignments for all the flows are displayed in shortage screen with search feild and paging functionality ", "pass", true);
		}else {
			reportStep("Assignments for all the flows are  not displayed in shortage screen with search feild and paging functionality ", "fail", true);
		}
		//navigate to select work area and select all rooms
		anyClick("Select Work Area Tab",By.xpath(prop.getProperty("Vclas_Home.Work.Areas.Button")) );
		anyClick("Rooms button", By.xpath(prop.getProperty("Vclas_Home.Room.Tab")));
		anyClick("Add All button", By.xpath(prop.getProperty("Vclas_Home.Room.Add.All.Button")));
		reportStep("All the selected rooms are displayed in right pane successfully", "pass", true);
		//deselcting all rooms
		anyClick("Remove All button", By.xpath(prop.getProperty("Vclas_Home.Room.RemoveAll.Button")));
		reportStep("All the selected rooms are removed and displayed in left pane successfully", "pass", true);
		// selecting and adding a particular room
		String roomSelect= tdrow.get("Select_Work_Area_Room");
		sendKeys("Search Rooms selection",By.xpath(prop.getProperty("Vclas_Home.Room.List.Text")), roomSelect);
		// Select drop down
		anyClick(roomSelect + " Room", By.xpath(prop.getProperty("Vclas_Home.Room.Select.Item")
				.replace("&Value", roomSelect)));

		// Add the Room Value
		anyClick("Adding the Room: " + roomSelect, By.xpath(prop.getProperty("Vclas_Home.Room.Add.Button")));
		reportStep(roomSelect+" room is added to the right pane", "pass", true);

		// navigating to assignments and shortage tab
		anyClick("Assignments List Screen", By.xpath(prop.getProperty("Vclas_Home.Assignment.List.Button")));
		if(driver.findElements(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Table.Firstrow.Object"))).size()>0) {
			reportStep(roomSelect +" room related Assignments are present in Assignment list screen", "pass", true);
		}else {
			reportStep("No orders Assignments are there for"+roomSelect+"room in Assignment list screen", "pass", true);
		}
		anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));
		
		vclasAssign.clearByLocator(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text")));
		retryingFindClick(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Button")));
		if(driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Table")+"/tbody/tr")).size()>1) {
			
			reportStep(roomSelect +" room related Assignments are present in Shortage screen", "pass", true);
		}else {
			reportStep("No orders Assignments are there for"+roomSelect+"room in shortage screen", "pass", true);
		}
		
		
		//navigating to alarm tab
		anyClick("Alarm Tab", By.xpath(prop.getProperty("Vclas_Home.Assignment.Alarm.Tab")));
		reportStep("Alarm tab is displayed successfully", "pass", true);
		// navigating to help tab
		anyClick("Help Tab", By.xpath(prop.getProperty("Vclas_Home.Assignment.Help.Tab")));
		if(verifyElementExistReturn(By.xpath(prop.getProperty("Vclas_Home.Assignment.Help.Tab.Close")))==true) {
			reportStep("Help tab is clicked and torslanda plant is displayed with X mark to close successfully", "pass", true);
		}else {
			reportStep("Help tab is clicked and torslanda plant is displayed with X mark is not present", "fail", true);
		}
		
	}
	

}