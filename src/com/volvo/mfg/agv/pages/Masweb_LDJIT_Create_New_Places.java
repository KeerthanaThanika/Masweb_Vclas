package com.volvo.mfg.agv.pages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.volvo.automation.commonutilities.ExcelUtils;
import com.volvo.mfg.StepDefinition.DriverSuite;

public class Masweb_LDJIT_Create_New_Places extends DriverSuite {
	
	public HashMap<String, String> tdrow;
	ExcelUtils excelUtils = new ExcelUtils();
	String strSupplier = "";
	String strFlow = "";
	String strPlace = "";
	String strPlaceType="";
	String strFactory="";
	String strCheckcode = "";
	String strDescription = "";
	String strCreatePlaceType = "B10";
	String strStatus="";
	
	
	public void MASWEB_LDJIT_Create_New_Places() {
		//clicking on LDJIT menu
		anyClick("LDJIT menu click", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu")));
				
		//verifying the LDJIT drop-down
		verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Move.JISJIT.Racks")));
		verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Yard.Zone.Maintenance")));
		verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Dummy.ASN.Creation")));
		verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Scan.Rack.Label")));
		verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Update.JISJIT.Trailer.Empty")));
				
		reportStep("Verified LDJIT dropdown", "pass", true);
				
		//clicking on yard zone maintenance tab
		anyClick("Yard Zone maintenance click", By.xpath(prop.getProperty("MASWEB_LDJIT.Yard.Zone.Maintenance")));
				
		//verifying yard zone maintenance drop-down
		verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Yard.Create.Parking.Place")));
		verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Yard.Trailer.Info")));
		verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Yard.Create.New.Places")));
		verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Yard.Create.Trailer.Assignments")));
				
		reportStep("Verified yard zone maintenance dropdown", "pass", true);
				
		//clicking on create new place option
		anyClick("Create ne places click", By.xpath(prop.getProperty("MASWEB_LDJIT.Yard.Create.New.Places")));
				
		//verify page display
		verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Yard.Create.New.Places.Page.Verify")));
		reportStep("Verified create new places page", "pass", true);
				
	}
	
	public void Create_and_Edit_New_Places() throws InterruptedException {
		
		String strFetchFromDate="";
		String strCreateFromDate="";
		String strCreateToDate="";
		
		
		String sheetName = "MASWEB_LDJIT";
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		
		//selecting create new places menu
		MASWEB_LDJIT_Create_New_Places();
		
		//clicking on display button in create new places page
		anyClick("Display button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Display.Button")));
		Thread.sleep(4000);
		System.out.println("******");
		
		//selecting supplier dropdown
		strSupplier = tdrow.get("Supplier");
		System.out.println("strSupplier: "+strSupplier);
		anyClick("Supplier dropdown click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Supplier.Dropdown")));
		selectDropDownValue("Selecting supplier dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Supplier.Dropdown")), strSupplier);
		Thread.sleep(2000);
		
		//clicking on display button in create new places page
		anyClick("Display button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Display.Button")));
		Thread.sleep(4000);
		reportStep("#B Supplier selected successfully #C", "pass", false);
		
		//selecting flow drop-down
		strFlow = tdrow.get("Flow");
		System.out.println("strFlow: "+strFlow);
		anyClick("Flow dropdown click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Flow.Dropdown")));
		selectDropDownValue("Selecting Flow dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Flow.Dropdown")), strFlow);
		Thread.sleep(2000);
		
		//clicking on display button in create new places page
		anyClick("Display button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Display.Button")));
		reportStep("#B Flow selected successfully #C", "pass", false);
		
		//entering the place
		strPlace = tdrow.get("Place");
		sendKeys("Place Textbox", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Place.Textbox")), strPlace);
		
		//clicking on display button in create new places page
		anyClick("Display button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Display.Button")));
		reportStep("#B Place selected successfully #C", "pass", false);
		
		//selecting place type drop-down
		strPlaceType = tdrow.get("Place_Type");
		System.out.println("strPlaceType: "+strPlaceType);
		new Select (driver.findElement(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.PlaceType.Dropdown")))).selectByVisibleText(strPlaceType);
		
		//clicking on display button in create new places page
		anyClick("Display button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Display.Button")));
		reportStep("#B Place Type selected successfully #C", "pass", false);
		reportStep("#B Verified display button functionality in create new place screen Testcase id:1003 #C", "Pass", false);
		
		//clicking on clear button
		anyClick("Clear button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Clear.Button")));
		reportStep("#B Verified clear button functionality in create new place screen Testcase id:1004 #C", "Pass", false);
		
		//clicking on create button
		anyClick("Create button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Button")));
		
		//Entering place name
		strFlow = "BUF";//tdrow.get("Flow");
		sendKeys("Place", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Place.Textbox")), strFlow);
		System.out.println("strFlow: "+strFlow);
		
		//selecting place type drop-down
		anyClick("placetype click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.PlaceType.Dropdown")));
		selectDropDownValue("Place Type dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.PlaceType.Dropdown")), strCreatePlaceType);
		//new Select (driver.findElement(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.PlaceType.Dropdown")))).selectByVisibleText(strCreatePlaceType);
		Thread.sleep(4000);
		System.out.println("strPlaceType: "+strCreatePlaceType);
		
		//selecting supplier drop-down
		strSupplier = tdrow.get("Supplier");
		anyClick("Supplier dropdown click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Supplier.Dropdown")));
		selectDropDownValue("Selecting Supplier dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Supplier.Dropdown")), strSupplier);
		System.out.println("strSupplier: "+strSupplier);
		
		//selecting flow drop-down
		strFlow=tdrow.get("Flow");
		anyClick("Supplier dropdown click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Flow.Dropdown")));
		selectDropDownValue("Selecting Supplier dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Flow.Dropdown")), strFlow);
		System.out.println("strFlow: "+strFlow);
		
		//Entering Factory type 
		if(verifyElementExistReturn(By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Factory.Dropdown")))==true) {
		strFactory = tdrow.get("Factory");
		selectDropDownValue("Factory Type", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Factory.Dropdown")), strFactory);
		System.out.println("strFactory:" +strFactory);
		reportStep("#B Factory: #C" +strFactory, "pass", true);
		}else {
			reportStep("Factory already selected", "pass", false);
		}
		
		//clicking on status radio button
		anyClick("Status Radio button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Status.Radiobutton")));
		
		
		//Entering Check code
		if(verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Checkcode.Textbox")))==true) {
		strCheckcode = tdrow.get("Checkcode");
		sendKeys("Checkcode Textbox", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Checkcode.Textbox")), strCheckcode);
		System.out.println("strCheckcode:" +strCheckcode);
		
		}else {
			reportStep("Checkcode already entered", "pass", false);
		}
		
		//Entering Description
		if(verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Description.Textbox")))==true) {
		strDescription = tdrow.get("Description");
		sendKeys("Description", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Description.Textbox")), strDescription);
		System.out.println("strDescription:" +strDescription);
		reportStep("#B Description: #C" +strDescription, "pass", true);
		}else {
			reportStep("Description already entered", "pass", false);
		}
		
		// Enter From Date to select
		if (!tdrow.get("From_Date").equals("")) {

						// Converting date to DD/MMM/YYYY
						String date = dtDateConversion(tdrow.get("From_Date"));
						// Click the calendar
						anyClick("From Date", By.xpath(prop.getProperty("Admin.Manage_Alarms.From_Date.Text")));

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
	 strCreateFromDate=driver.findElements(By.xpath(prop.getProperty("Admin.Manage_Alarms.From_Date.Text"))).get(0).getText();
					}

		// Enter To Date to select
		if (!tdrow.get("To_Date").equals("")) {
						// Converting date to DD/MMM/YYYY
						String date = dtDateConversion(tdrow.get("To_Date"));
						// Click the calendar
						anyClick("To Date", By.xpath(prop.getProperty("Admin.Manage_Alarms.To_Date.Text")));
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
		strCreateToDate=driver.findElements(By.xpath(prop.getProperty("Admin.Manage_Alarms.To_Date.Text"))).get(0).getText();
					}
		
		//clicking on save button
		anyClick("Save button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Save.Button")));
		
		//Verify Error Message
		String strErrorMsg = driver.findElements(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Error.Message"))).get(0).getText();
		System.out.println("#B Error Message: #C" +strErrorMsg);
		reportStep("Error Message:" +strErrorMsg, "pass", true);
		reportStep("#B Verified the error message Test case id:1005 #C", "Pass", false);
		
		
		//selecting create new places menu
		MASWEB_LDJIT_Create_New_Places();
		
		//clicking on display button in create new places page
		anyClick("Display button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Display.Button")));
		Thread.sleep(2000);
		reportStep("#B New Place created #C", "pass", true);
		
		//verify table display
		if(verifyElementExist("Table search",By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Table.Verify")))==true) {
			
			// Retrieving the create from date and clicking edit
			String tableHeaderRow = prop.getProperty("MASWEB_LDJIT.Create.New.Places.Table.Verify") + "/tbody/tr[1]";
			String tableObject = prop.getProperty("MASWEB_LDJIT.Create.New.Places.Table.Verify") + "/tbody/tr[2]";
			List<WebElement> rows = driver.findElements(By.xpath(tableObject));
			int colCreateFromDate = htmlTableColumnNamePosition("Created From Date", tableHeaderRow);
			int colCreateToDate = htmlTableColumnNamePosition("Created To Date", tableHeaderRow);
			int colEdit=htmlTableColumnNamePosition("Edit", tableHeaderRow);
			int row_count = rows.size();
			
			
			for (int i = 1; i <= row_count; i++) {
				int rowInc = i + 1;
				String strTableData = prop.getProperty("MASWEB_LDJIT.Create.New.Places.Table.Verify") + "/tbody/tr[" + rowInc + "]/td";
				List<WebElement> tableData = driver.findElements(By.xpath(strTableData));
				strFetchFromDate = tableData.get(colCreateFromDate).getText();
				
				if(strCreateFromDate.equals("strFetchFromDate")) {
					
					//clicking on edit button
					tableData.get(colEdit).click();
					verifyElementExist("edit page ", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Table.Edit.Page.Display")));
					break;
				}
				break;
			}

		}else {
			reportStep("Table not displayed", "fail", true);
		}
		
		//verify page display after edit button click
		if(verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Table.Edit.Page.Display")))==true) {
			//Entering Factory type 
			if(verifyElementExistReturn(By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Factory.Dropdown")))==true) {
			strFactory = "B";
			selectDropDownValue("Factory Type", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Factory.Dropdown")), strFactory);
			System.out.println("strFactory:" +strFactory);
			reportStep("#B Updated Factory: #C" +strFactory, "pass", true);
			}else {
						reportStep("Factory already selected", "pass", false);
				}
					
					
			//Entering Description
			if(verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Description.Textbox")))==true) {
			strDescription = "Test Update";
			sendKeys("Description", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Description.Textbox")), strDescription);
			System.out.println("strDescription:" +strDescription);
			reportStep("#B Updated Description Field: #C" +strFactory, "pass", true);
			}else {
						reportStep("Description already entered", "pass", false);
				}
					
			//clicking on save button
			anyClick("Save button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Save.Button")));
			Thread.sleep(3000);
			reportStep("#B Values updated successfully #C", "pass", true);
			
		}else {
			reportStep("Page not displayed after edit button click", "fail", true);
		}
		
		
				
		//Verify Error Message
		String strMessage = driver.findElements(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Error.Message"))).get(0).getText();
		System.out.println("#B Error Message: #C" +strMessage);
		reportStep("Error Message:" +strMessage, "pass", true);
		reportStep("#B Verified edit functionality in create new places screen Testcase id:1016 #C", "Pass", false);
		
	
		
	}
	
	public void Place_Error_Message() throws InterruptedException {
		
		String sheetName = "MASWEB_LDJIT";
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		
		//selecting create new places menu
		MASWEB_LDJIT_Create_New_Places();
		
		//clicking on create button
		anyClick("Create button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Button")));
				
		//selecting place type dropdown
		anyClick("placetype click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.PlaceType.Dropdown")));
		selectDropDownValue("Place Type dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.PlaceType.Dropdown")), strCreatePlaceType);
		//new Select (driver.findElement(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.PlaceType.Dropdown")))).selectByVisibleText(strCreatePlaceType);
		Thread.sleep(4000);
		System.out.println("strPlaceType: "+strCreatePlaceType);
				
		//selecting supplier dropdown
		strSupplier = tdrow.get("Supplier");
		anyClick("Supplier dropdown click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Supplier.Dropdown")));
		selectDropDownValue("Selecting Supplier dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Supplier.Dropdown")), strSupplier);
		System.out.println("strSupplier: "+strSupplier);
				
		//selecting flow dropdown
		strFlow = tdrow.get("Flow");
		anyClick("Flow dropdown click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Flow.Dropdown")));
		selectDropDownValue("Flow Supplier dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Flow.Dropdown")), strFlow);
		Thread.sleep(2000);
		System.out.println("strFlow: "+strFlow);
				
		//Entering Factory type 
		if(verifyElementExistReturn(By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Factory.Dropdown")))==true) {
		
		strFactory = tdrow.get("Factory");
		selectDropDownValue("Factory Type", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Factory.Dropdown")), strFactory);
		Thread.sleep(2000);
		System.out.println("strFactory:" +strFactory);
		
		}else {
			reportStep("Factory already selected", "pass", false);
		}
				
		//clicking on status radio button
		anyClick("Status Radio button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Status.Radiobutton")));
				
		//Entering Check code
		if(verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Checkcode.Textbox")))==true) {
		strCheckcode = tdrow.get("Checkcode");
		sendKeys("Checkcode Textbox", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Checkcode.Textbox")), strCheckcode);
		System.out.println("strCheckcode:" +strCheckcode);
		}else {
			reportStep("Checkcode already entered", "pass", false);
		}
				
		//Entering Description
		if(verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Description.Textbox")))==true) {
		strDescription = tdrow.get("Description");
		sendKeys("Description", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Description.Textbox")), strDescription);
		System.out.println("strDescription:" +strDescription);
		}else {
			reportStep("Description already entered", "pass", false);
		}
				
		// Enter From Date to select
		if (!tdrow.get("From_Date").equals("")) {

				// Converting date to DD/MMM/YYYY
				String date = dtDateConversion(tdrow.get("From_Date"));
				// Click the calendar
				anyClick("From Date", By.xpath(prop.getProperty("Admin.Manage_Alarms.From_Date.Text")));

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

				// Enter To Date to select
				if (!tdrow.get("To_Date").equals("")) {
						// Converting date to DD/MMM/YYYY
						String date = dtDateConversion(tdrow.get("To_Date"));
						// Click the calendar
						anyClick("To Date", By.xpath(prop.getProperty("Admin.Manage_Alarms.To_Date.Text")));
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
				
				//clicking on save button
				anyClick("Save button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Save.Button")));
				
				//Fetching the place error message
				String strErrorMsg = driver.findElements(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Error.Message"))).get(0).getText();
				System.out.println("#B Error Message: #C" +strErrorMsg);
				reportStep("#B Error Message: #C" +strErrorMsg, "pass", true);
				reportStep("#B Verified the Place error message Testcase id:1006 #C", "Pass", false);
		
	}

	public void Place_Type_Error_Message() throws InterruptedException {
		
		String sheetName = "MASWEB_LDJIT";
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		
		//selecting create new places menu
		MASWEB_LDJIT_Create_New_Places();
		
		//clicking on create button
		anyClick("Create button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Button")));
				
		//Entering place name
		strFlow = tdrow.get("Flow");
		sendKeys("Place", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Place.Textbox")), strFlow);
		System.out.println("strFlow: "+strFlow);
				
		//selecting supplier drop-down
		strSupplier = tdrow.get("Supplier");
		anyClick("Supplier dropdown click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Supplier.Dropdown")));
		selectDropDownValue("Selecting Supplier dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Supplier.Dropdown")), strSupplier);
		System.out.println("strSupplier: "+strSupplier);
				
		//selecting flow drop-down
		strFlow = tdrow.get("Flow");
		anyClick("Flow dropdown click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Flow.Dropdown")));
		selectDropDownValue("Flow Supplier dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Flow.Dropdown")), strFlow);
		Thread.sleep(2000);
		System.out.println("strFlow: "+strFlow);
				
		//Entering Factory type 
		if(verifyElementExistReturn(By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Factory.Dropdown")))==true) {
		
		strFactory = tdrow.get("Factory");
		selectDropDownValue("Factory Type", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Factory.Dropdown")), strFactory);
		//sendKeys("Factory Type", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Factory.Dropdown")), strFactory);
		Thread.sleep(2000);
		System.out.println("strFactory:" +strFactory);
		
		}else {
			reportStep("Factory already selected", "pass", false);
		}
				
		//clicking on status radio button
		anyClick("Status Radio button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Status.Radiobutton")));
				
		//Entering Check code
		if(verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Checkcode.Textbox")))==true) {
		strCheckcode = tdrow.get("Checkcode");
		sendKeys("Checkcode Textbox", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Checkcode.Textbox")), strCheckcode);
		System.out.println("strCheckcode:" +strCheckcode);
		}else {
			reportStep("Checkcode already entered", "pass", false);
		}
				
		//Entering Description
		if(verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Description.Textbox")))==true) {
		strDescription = tdrow.get("Description");
		sendKeys("Description", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Description.Textbox")), strDescription);
		System.out.println("strDescription:" +strDescription);
		}else {
			reportStep("Description already entered", "pass", false);
		}
				
		// Enter From Date to select
		if (!tdrow.get("From_Date").equals("")) {

				// Converting date to DD/MMM/YYYY
				String date = dtDateConversion(tdrow.get("From_Date"));
				// Click the calendar
				anyClick("From Date", By.xpath(prop.getProperty("Admin.Manage_Alarms.From_Date.Text")));

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

				// Enter To Date to select
				if (!tdrow.get("To_Date").equals("")) {
						// Converting date to DD/MMM/YYYY
						String date = dtDateConversion(tdrow.get("To_Date"));
						// Click the calendar
						anyClick("To Date", By.xpath(prop.getProperty("Admin.Manage_Alarms.To_Date.Text")));
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
				
				//clicking on save button
				anyClick("Save button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Save.Button")));
				
				//Fetching the place type error message
				String strErrorMsg = driver.findElements(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Error.Message"))).get(0).getText();
				System.out.println("#B Error Message: #C" +strErrorMsg);
				reportStep("#B Error Message: #C" +strErrorMsg, "pass", true);
				reportStep("#B Verified the Place Type error message Testcase id:1007 #C", "Pass", false);
		
	}
	
	public void Supplier_Error_Message() throws InterruptedException {
		
		String sheetName = "MASWEB_LDJIT";
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		
		//selecting create new places menu
		MASWEB_LDJIT_Create_New_Places();
		
		//clicking on create button
		anyClick("Create button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Button")));
				
		//Entering place name
		strFlow = tdrow.get("Flow");
		sendKeys("Place", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Place.Textbox")), strFlow);
		System.out.println("strFlow: "+strFlow);
				
		//selecting place type drop-down
		anyClick("placetype click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.PlaceType.Dropdown")));
		selectDropDownValue("Place Type dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.PlaceType.Dropdown")), strCreatePlaceType);
		//new Select (driver.findElement(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.PlaceType.Dropdown")))).selectByVisibleText(strCreatePlaceType);
		Thread.sleep(4000);
		System.out.println("strPlaceType: "+strCreatePlaceType);
				
		//selecting flow drop-down
		strFlow = tdrow.get("Flow");
		anyClick("Flow dropdown click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Flow.Dropdown")));
		selectDropDownValue("Flow Supplier dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Flow.Dropdown")), strFlow);
		Thread.sleep(2000);
		reportStep("Flow is not selected,because Supplier is not selected", "pass", false);
		System.out.println("strFlow: "+strFlow);
				
		//Entering Factory type 
		if(verifyElementExistReturn(By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Factory.Dropdown")))==true) {
		
		strFactory = tdrow.get("Factory");
		selectDropDownValue("Factory Type", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Factory.Dropdown")), strFactory);
		Thread.sleep(2000);
		System.out.println("strFactory:" +strFactory);
		
		}else {
			reportStep("Factory already selected", "pass", false);
		}
				
		//clicking on status radio button
		anyClick("Status Radio button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Status.Radiobutton")));
				
		//Entering Checkcode
		if(verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Checkcode.Textbox")))==true) {
		strCheckcode = tdrow.get("Checkcode");
		sendKeys("Checkcode Textbox", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Checkcode.Textbox")), strCheckcode);
		System.out.println("strCheckcode:" +strCheckcode);
		}else {
			reportStep("Checkcode already entered", "pass", false);
		}
				
		//Entering Description
		if(verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Description.Textbox")))==true) {
		strDescription = tdrow.get("Description");
		sendKeys("Description", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Description.Textbox")), strDescription);
		System.out.println("strDescription:" +strDescription);
		}else {
			reportStep("Description already entered", "pass", false);
		}
				
		// Enter From Date to select
		if (!tdrow.get("From_Date").equals("")) {

				// Converting date to DD/MMM/YYYY
				String date = dtDateConversion(tdrow.get("From_Date"));
				// Click the calendar
				anyClick("From Date", By.xpath(prop.getProperty("Admin.Manage_Alarms.From_Date.Text")));

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

				// Enter To Date to select
				if (!tdrow.get("To_Date").equals("")) {
						// Converting date to DD/MMM/YYYY
						String date = dtDateConversion(tdrow.get("To_Date"));
						// Click the calendar
						anyClick("To Date", By.xpath(prop.getProperty("Admin.Manage_Alarms.To_Date.Text")));
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
				
				//clicking on save button
				anyClick("Save button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Save.Button")));
				
				//Fetching the place type error message
				String strSupplierErrorMsg = driver.findElements(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Error.Message"))).get(0).getText();
				String strFlowErrorMsg = driver.findElements(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Error.Message"))).get(0).getText();
				System.out.println("#B Supplier Error Message: #C" +strSupplierErrorMsg);
				System.out.println("#B Flow Error Message: #C" +strFlowErrorMsg);
				reportStep("#B Error Message: #C" +strSupplierErrorMsg, "pass", true);
				reportStep("#B Verified the Supplier error message Testcase id:1008 #C", "Pass", false);
		
	}
	
public void Flow_Error_Message() throws InterruptedException {
		
		String sheetName = "MASWEB_LDJIT";
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		
		//selecting create new places menu
		MASWEB_LDJIT_Create_New_Places();
		
		//clicking on create button
		anyClick("Create button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Button")));
				
		//Entering place name
		strFlow = "AEB";
		sendKeys("Place", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Place.Textbox")), strFlow);
		System.out.println("strFlow: "+strFlow);
				
		//selecting place type drop-down
		anyClick("placetype click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.PlaceType.Dropdown")));
		selectDropDownValue("Place Type dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.PlaceType.Dropdown")), strCreatePlaceType);
		//new Select (driver.findElement(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.PlaceType.Dropdown")))).selectByVisibleText(strCreatePlaceType);
		Thread.sleep(4000);
		System.out.println("strPlaceType: "+strCreatePlaceType);
				
		//selecting supplier drop-down
		strSupplier = tdrow.get("Supplier");
		anyClick("Supplier dropdown click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Supplier.Dropdown")));
		selectDropDownValue("Selecting Supplier dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Supplier.Dropdown")), strSupplier);
		System.out.println("strSupplier: "+strSupplier);
		
		//Entering Factory type 
		if(verifyElementExistReturn(By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Factory.Dropdown")))==true) {
		
		strFactory = tdrow.get("Factory");
		selectDropDownValue("Factory Type", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Factory.Dropdown")), strFactory);
		Thread.sleep(2000);
		System.out.println("strFactory:" +strFactory);
		
		}else {
			reportStep("Factory already selected", "pass", false);
		}
				
		//clicking on status radio button
		anyClick("Status Radio button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Status.Radiobutton")));
				
		//Entering Check code
		if(verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Checkcode.Textbox")))==true) {
		strCheckcode = tdrow.get("Checkcode");
		sendKeys("Checkcode Textbox", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Checkcode.Textbox")), strCheckcode);
		System.out.println("strCheckcode:" +strCheckcode);
		}else {
			reportStep("Checkcode already entered", "pass", false);
		}
				
		//Entering Description
		if(verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Description.Textbox")))==true) {
		strDescription = tdrow.get("Description");
		sendKeys("Description", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Description.Textbox")), strDescription);
		System.out.println("strDescription:" +strDescription);
		}else {
			reportStep("Description already entered", "pass", false);
		}
				
		// Enter From Date to select
		if (!tdrow.get("From_Date").equals("")) {

				// Converting date to DD/MMM/YYYY
				String date = dtDateConversion(tdrow.get("From_Date"));
				// Click the calendar
				anyClick("From Date", By.xpath(prop.getProperty("Admin.Manage_Alarms.From_Date.Text")));

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

				// Enter To Date to select
				if (!tdrow.get("To_Date").equals("")) {
						// Converting date to DD/MMM/YYYY
						String date = dtDateConversion(tdrow.get("To_Date"));
						// Click the calendar
						anyClick("To Date", By.xpath(prop.getProperty("Admin.Manage_Alarms.To_Date.Text")));
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
				
				//selecting flow drop-down
				strFlow="Select Flow";
				anyClick("Flow dropdown click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Flow.Dropdown")));
				retryingFindClick(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Flow.Select.Flow.Option.Dropdown")));
				//new Select (driver.findElement(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Flow.Select.Flow.Option.Dropdown")))).selectByVisibleText(strFlow);
				//anyClick("select flow option click", By.xpath("MASWEB_LDJIT.Create.New.Places.Create.Flow.Select.Flow.Option.Dropdown"));
				Thread.sleep(2000);
				reportStep("Flow is selected", "pass", false);
				System.out.println("strFlow: "+strFlow);
				
				//clicking on save button
				anyClick("Save button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Save.Button")));
				
				//Fetching the place type error message
				String strFlowErrorMsg = driver.findElements(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Error.Message"))).get(0).getText();
				System.out.println("#B Flow Error Message: #C" +strFlowErrorMsg);
				reportStep("#B Error Message: #C" +strFlowErrorMsg, "pass", true);
				reportStep("#B Verified the Flow error message Testcase id:1009 #C", "Pass", false);
		
	}


public void Factory_Error_Message() throws InterruptedException {
	
	String sheetName = "MASWEB_LDJIT";
	tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
	
	//selecting create new places menu
	MASWEB_LDJIT_Create_New_Places();
	
	//clicking on create button
	anyClick("Create button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Button")));
			
	//Entering place name
	strFlow = tdrow.get("Flow");
	sendKeys("Place", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Place.Textbox")), strFlow);
	System.out.println("strFlow: "+strFlow);
			
	//selecting place type drop-down
	anyClick("placetype click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.PlaceType.Dropdown")));
	selectDropDownValue("Place Type dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.PlaceType.Dropdown")), strCreatePlaceType);
	//new Select (driver.findElement(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.PlaceType.Dropdown")))).selectByVisibleText(strCreatePlaceType);
	Thread.sleep(4000);
	System.out.println("strPlaceType: "+strCreatePlaceType);
	
	//selecting supplier drop-down
	strSupplier = tdrow.get("Supplier");
	anyClick("Supplier dropdown click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Supplier.Dropdown")));
	selectDropDownValue("Selecting Supplier dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Supplier.Dropdown")), strSupplier);
	System.out.println("strSupplier: "+strSupplier);
			
	//selecting flow drop-down
	strFlow = tdrow.get("Flow");
	anyClick("Flow dropdown click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Flow.Dropdown")));
	selectDropDownValue("Flow Supplier dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Flow.Dropdown")), strFlow);
	Thread.sleep(2000);
	reportStep("Flow is not selected,because Supplier is not selected", "pass", false);
	System.out.println("strFlow: "+strFlow);
			
	//clicking on status radio button
	anyClick("Status Radio button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Status.Radiobutton")));
			
	//Entering Check code
	if(verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Checkcode.Textbox")))==true) {
	strCheckcode = tdrow.get("Checkcode");
	sendKeys("Checkcode Textbox", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Checkcode.Textbox")), strCheckcode);
	System.out.println("strCheckcode:" +strCheckcode);
	}else {
		reportStep("Checkcode already entered", "pass", false);
	}
			
	//Entering Description
	if(verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Description.Textbox")))==true) {
	strDescription = tdrow.get("Description");
	sendKeys("Description", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Description.Textbox")), strDescription);
	System.out.println("strDescription:" +strDescription);
	}else {
		reportStep("Description already entered", "pass", false);
	}
			
	// Enter From Date to select
	if (!tdrow.get("From_Date").equals("")) {

			// Converting date to DD/MMM/YYYY
			String date = dtDateConversion(tdrow.get("From_Date"));
			// Click the calendar
			anyClick("From Date", By.xpath(prop.getProperty("Admin.Manage_Alarms.From_Date.Text")));

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

			// Enter To Date to select
			if (!tdrow.get("To_Date").equals("")) {
					// Converting date to DD/MMM/YYYY
					String date = dtDateConversion(tdrow.get("To_Date"));
					// Click the calendar
					anyClick("To Date", By.xpath(prop.getProperty("Admin.Manage_Alarms.To_Date.Text")));
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
			
			//clicking on save button
			anyClick("Save button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Save.Button")));
			
			//Fetching the place type error message
			String strErrorMsg = driver.findElements(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Error.Message"))).get(0).getText();
			System.out.println("#B Factory Error Message: #C" +strErrorMsg);
			reportStep("#B Error Message: #C" +strErrorMsg, "pass", true);
			reportStep("#B Verified the Factory error message Testcase id:1010 #C", "Pass", false);
	
}

public void Status_Error_Message() throws InterruptedException {
	
	String sheetName = "MASWEB_LDJIT";
	tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
	
	//selecting create new places menu
	MASWEB_LDJIT_Create_New_Places();
	
	//clicking on create button
	anyClick("Create button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Button")));
	
	//Entering place name
	strFlow = tdrow.get("Flow");
	sendKeys("Place", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Place.Textbox")), strFlow);
	System.out.println("strFlow: "+strFlow);
			
	//selecting place type drop-down
	anyClick("placetype click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.PlaceType.Dropdown")));
	selectDropDownValue("Place Type dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.PlaceType.Dropdown")), strCreatePlaceType);
	//new Select (driver.findElement(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.PlaceType.Dropdown")))).selectByVisibleText(strCreatePlaceType);
	Thread.sleep(4000);
	System.out.println("strPlaceType: "+strCreatePlaceType);
			
	//selecting supplier drop-down
	strSupplier = tdrow.get("Supplier");
	anyClick("Supplier dropdown click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Supplier.Dropdown")));
	selectDropDownValue("Selecting Supplier dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Supplier.Dropdown")), strSupplier);
	System.out.println("strSupplier: "+strSupplier);
			
	//selecting flow drop-down
	strFlow = tdrow.get("Flow");
	anyClick("Flow dropdown click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Flow.Dropdown")));
	selectDropDownValue("Flow Supplier dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Flow.Dropdown")), strFlow);
	Thread.sleep(2000);
	System.out.println("strFlow: "+strFlow);
			
	//Entering Factory type 
	if(verifyElementExistReturn(By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Factory.Dropdown")))==true) {
	
	strFactory = tdrow.get("Factory");
	selectDropDownValue("Factory Type", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Factory.Dropdown")), strFactory);
	Thread.sleep(2000);
	System.out.println("strFactory:" +strFactory);
	
	}else {
		reportStep("Factory already selected", "pass", false);
	}
			
	//Entering Check code
	if(verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Checkcode.Textbox")))==true) {
	strCheckcode = tdrow.get("Checkcode");
	sendKeys("Checkcode Textbox", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Checkcode.Textbox")), strCheckcode);
	System.out.println("strCheckcode:" +strCheckcode);
	}else {
		reportStep("Checkcode already entered", "pass", false);
	}
			
	//Entering Description
	if(verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Description.Textbox")))==true) {
	strDescription = tdrow.get("Description");
	sendKeys("Description", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Description.Textbox")), strDescription);
	System.out.println("strDescription:" +strDescription);
	}else {
		reportStep("Description already entered", "pass", false);
	}
			
	// Enter From Date to select
	if (!tdrow.get("From_Date").equals("")) {

			// Converting date to DD/MMM/YYYY
			String date = dtDateConversion(tdrow.get("From_Date"));
			// Click the calendar
			anyClick("From Date", By.xpath(prop.getProperty("Admin.Manage_Alarms.From_Date.Text")));

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

			// Enter To Date to select
			if (!tdrow.get("To_Date").equals("")) {
					// Converting date to DD/MMM/YYYY
					String date = dtDateConversion(tdrow.get("To_Date"));
					// Click the calendar
					anyClick("To Date", By.xpath(prop.getProperty("Admin.Manage_Alarms.To_Date.Text")));
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
			
			//clicking on save button
			anyClick("Save button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Save.Button")));
			
			//Fetching the place error message
			String strErrorMsg = driver.findElements(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Error.Message"))).get(0).getText();
			System.out.println("#B Error Message: #C" +strErrorMsg);
			reportStep("#B Error Message: #C" +strErrorMsg, "pass", true);
			reportStep("#B Verified the Status error message Testcase id:1011 #C", "Pass", false);
	
}

public void Description_Error_Message() throws InterruptedException {
	
	String sheetName = "MASWEB_LDJIT";
	tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
	
	//selecting create new places menu
	MASWEB_LDJIT_Create_New_Places();
	
	//clicking on create button
	anyClick("Create button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Button")));
	
	//Entering place name
	strFlow = tdrow.get("Flow");
	sendKeys("Place", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Place.Textbox")), strFlow);
	System.out.println("strFlow: "+strFlow);
			
	//selecting place type drop-down
	anyClick("placetype click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.PlaceType.Dropdown")));
	selectDropDownValue("Place Type dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.PlaceType.Dropdown")), strCreatePlaceType);
	//new Select (driver.findElement(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.PlaceType.Dropdown")))).selectByVisibleText(strCreatePlaceType);
	Thread.sleep(4000);
	System.out.println("strPlaceType: "+strCreatePlaceType);
			
	//selecting supplier drop-down
	strSupplier = tdrow.get("Supplier");
	anyClick("Supplier dropdown click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Supplier.Dropdown")));
	selectDropDownValue("Selecting Supplier dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Supplier.Dropdown")), strSupplier);
	System.out.println("strSupplier: "+strSupplier);
			
	//selecting flow drop-down
	strFlow = tdrow.get("Flow");
	anyClick("Flow dropdown click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Flow.Dropdown")));
	selectDropDownValue("Flow Supplier dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Flow.Dropdown")), strFlow);
	Thread.sleep(2000);
	System.out.println("strFlow: "+strFlow);
			
	//Entering Factory type 
	if(verifyElementExistReturn(By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Factory.Dropdown")))==true) {
	
	strFactory = tdrow.get("Factory");
	selectDropDownValue("Factory Type", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Factory.Dropdown")), strFactory);
	Thread.sleep(2000);
	System.out.println("strFactory:" +strFactory);
	
	}else {
		reportStep("Factory already selected", "pass", false);
	}
	
	//clicking on status radio button
	anyClick("Status Radio button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Status.Radiobutton")));
	
	//Entering Check code
	if(verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Checkcode.Textbox")))==true) {
	strCheckcode = tdrow.get("Checkcode");
	sendKeys("Checkcode Textbox", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Checkcode.Textbox")), strCheckcode);
	System.out.println("strCheckcode:" +strCheckcode);
	}else {
		reportStep("Checkcode already entered", "pass", false);
	}
			
			
	// Enter From Date to select
	if (!tdrow.get("From_Date").equals("")) {

			// Converting date to DD/MMM/YYYY
			String date = dtDateConversion(tdrow.get("From_Date"));
			// Click the calendar
			anyClick("From Date", By.xpath(prop.getProperty("Admin.Manage_Alarms.From_Date.Text")));

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

			// Enter To Date to select
			if (!tdrow.get("To_Date").equals("")) {
					// Converting date to DD/MMM/YYYY
					String date = dtDateConversion(tdrow.get("To_Date"));
					// Click the calendar
					anyClick("To Date", By.xpath(prop.getProperty("Admin.Manage_Alarms.To_Date.Text")));
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
			
			//clicking on save button
			anyClick("Save button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Save.Button")));
			
			//Fetching the place error message
			String strErrorMsg = driver.findElements(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Error.Message"))).get(0).getText();
			System.out.println("#B Error Message: #C" +strErrorMsg);
			reportStep("#B Error Message: #C" +strErrorMsg, "pass", true);
			reportStep("#B Verified the Description error message Testcase id:1012 #C", "Pass", false);
	
}

public void Created_From_Date_Error_Message() throws InterruptedException {
	
	String sheetName = "MASWEB_LDJIT";
	tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
	
	//selecting create new places menu
	MASWEB_LDJIT_Create_New_Places();
	
	//clicking on create button
	anyClick("Create button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Button")));
	
	//Entering place name
	strFlow = tdrow.get("Flow");
	sendKeys("Place", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Place.Textbox")), strFlow);
	System.out.println("strFlow: "+strFlow);
			
	//selecting place type drop-down
	anyClick("placetype click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.PlaceType.Dropdown")));
	selectDropDownValue("Place Type dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.PlaceType.Dropdown")), strCreatePlaceType);
	//new Select (driver.findElement(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.PlaceType.Dropdown")))).selectByVisibleText(strCreatePlaceType);
	Thread.sleep(4000);
	System.out.println("strPlaceType: "+strCreatePlaceType);
			
	//selecting supplier drop-down
	strSupplier = tdrow.get("Supplier");
	anyClick("Supplier dropdown click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Supplier.Dropdown")));
	selectDropDownValue("Selecting Supplier dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Supplier.Dropdown")), strSupplier);
	System.out.println("strSupplier: "+strSupplier);
			
	//selecting flow drop-down
	strFlow = tdrow.get("Flow");
	anyClick("Flow dropdown click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Flow.Dropdown")));
	selectDropDownValue("Flow Supplier dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Flow.Dropdown")), strFlow);
	Thread.sleep(2000);
	System.out.println("strFlow: "+strFlow);
			
	//Entering Factory type 
	if(verifyElementExistReturn(By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Factory.Dropdown")))==true) {
	
	strFactory = tdrow.get("Factory");
	selectDropDownValue("Factory Type", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Factory.Dropdown")), strFactory);
	Thread.sleep(2000);
	System.out.println("strFactory:" +strFactory);
	
	}else {
		reportStep("Factory already selected", "pass", false);
	}
	
	//clicking on status radio button
	anyClick("Status Radio button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Status.Radiobutton")));
	
	//Entering Check code
	if(verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Checkcode.Textbox")))==true) {
	strCheckcode = tdrow.get("Checkcode");
	sendKeys("Checkcode Textbox", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Checkcode.Textbox")), strCheckcode);
	System.out.println("strCheckcode:" +strCheckcode);
	}else {
		reportStep("Checkcode already entered", "pass", false);
	}
	
	//Entering Description
		if(verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Description.Textbox")))==true) {
		strDescription = tdrow.get("Description");
		sendKeys("Description", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Description.Textbox")), strDescription);
		System.out.println("strDescription:" +strDescription);
		}else {
			reportStep("Description already entered", "pass", false);
		}
	

			// Enter To Date to select
			if (!tdrow.get("To_Date").equals("")) {
					// Converting date to DD/MMM/YYYY
					String date = dtDateConversion(tdrow.get("To_Date"));
					// Click the calendar
					anyClick("To Date", By.xpath(prop.getProperty("Admin.Manage_Alarms.To_Date.Text")));
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
			
			//clicking on save button
			anyClick("Save button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Save.Button")));
			
			//Fetching the place error message
			String strErrorMsg = driver.findElements(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Error.Message"))).get(0).getText();
			System.out.println("#B Error Message: #C" +strErrorMsg);
			reportStep("#B Error Message: #C" +strErrorMsg, "pass", true);
			reportStep("#B Verified the Created From Date error message Testcase id:1013 #C", "Pass", false);
	
}

public void Created_To_Date_Error_Message() throws InterruptedException {
	
	String sheetName = "MASWEB_LDJIT";
	tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
	
	//selecting create new places menu
	MASWEB_LDJIT_Create_New_Places();
	
	//clicking on create button
	anyClick("Create button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Button")));
	
	//Entering place name
	strFlow = tdrow.get("Flow");
	sendKeys("Place", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Place.Textbox")), strFlow);
	System.out.println("strFlow: "+strFlow);
			
	//selecting place type drop-down
	anyClick("placetype click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.PlaceType.Dropdown")));
	selectDropDownValue("Place Type dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.PlaceType.Dropdown")), strCreatePlaceType);
	//new Select (driver.findElement(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.PlaceType.Dropdown")))).selectByVisibleText(strCreatePlaceType);
	Thread.sleep(4000);
	System.out.println("strPlaceType: "+strCreatePlaceType);
			
	//selecting supplier drop-down
	strSupplier = tdrow.get("Supplier");
	anyClick("Supplier dropdown click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Supplier.Dropdown")));
	selectDropDownValue("Selecting Supplier dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Supplier.Dropdown")), strSupplier);
	System.out.println("strSupplier: "+strSupplier);
			
	//selecting flow drop-down
	strFlow = tdrow.get("Flow");
	anyClick("Flow dropdown click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Flow.Dropdown")));
	selectDropDownValue("Flow Supplier dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Flow.Dropdown")), strFlow);
	Thread.sleep(2000);
	System.out.println("strFlow: "+strFlow);
			
	//Entering Factory type 
	if(verifyElementExistReturn(By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Factory.Dropdown")))==true) {
	
	strFactory = tdrow.get("Factory");
	selectDropDownValue("Factory Type", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Factory.Dropdown")), strFactory);
	Thread.sleep(2000);
	System.out.println("strFactory:" +strFactory);
	
	}else {
		reportStep("Factory already selected", "pass", false);
	}
	
	//clicking on status radio button
	anyClick("Status Radio button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Status.Radiobutton")));
	
	//Entering Check code
	if(verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Checkcode.Textbox")))==true) {
	strCheckcode = tdrow.get("Checkcode");
	sendKeys("Checkcode Textbox", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Checkcode.Textbox")), strCheckcode);
	System.out.println("strCheckcode:" +strCheckcode);
	}else {
		reportStep("Checkcode already entered", "pass", false);
	}
	
	//Entering Description
		if(verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Description.Textbox")))==true) {
		strDescription = tdrow.get("Description");
		sendKeys("Description", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Description.Textbox")), strDescription);
		System.out.println("strDescription:" +strDescription);
		}else {
			reportStep("Description already entered", "pass", false);
		}
	

		// Enter From Date to select
		if (!tdrow.get("From_Date").equals("")) {

				// Converting date to DD/MMM/YYYY
				String date = dtDateConversion(tdrow.get("From_Date"));
				// Click the calendar
				anyClick("From Date", By.xpath(prop.getProperty("Admin.Manage_Alarms.From_Date.Text")));

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
			
			//clicking on save button
			anyClick("Save button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Save.Button")));
			
			//Fetching the place error message
			String strErrorMsg = driver.findElements(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Error.Message"))).get(0).getText();
			System.out.println("#B Error Message: #C" +strErrorMsg);
			reportStep("#B Error Message: #C" +strErrorMsg, "pass", true);
			reportStep("#B Verified the Created To Date error message Testcase id:1014 #C", "Pass", false);
	
}

public void Create_Place() throws InterruptedException {
	
	//selecting create new places menu
	MASWEB_LDJIT_Create_New_Places();
	
	//clicking on create button
	anyClick("Create button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Button")));
			
	//Entering place name
	strFlow = tdrow.get("Flow");
	sendKeys("Place", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Place.Textbox")), strFlow);
	System.out.println("strFlow: "+strFlow);
			
	//selecting place type drop-down
	anyClick("placetype click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.PlaceType.Dropdown")));
	selectDropDownValue("Place Type dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.PlaceType.Dropdown")), strCreatePlaceType);
	//new Select (driver.findElement(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.PlaceType.Dropdown")))).selectByVisibleText(strCreatePlaceType);
	Thread.sleep(4000);
	System.out.println("strPlaceType: "+strCreatePlaceType);
			
	//selecting supplier drop-down
	strSupplier = tdrow.get("Supplier");
	anyClick("Supplier dropdown click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Supplier.Dropdown")));
	selectDropDownValue("Selecting Supplier dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Supplier.Dropdown")), strSupplier);
	System.out.println("strSupplier: "+strSupplier);
			
	//selecting flow drop-down
	anyClick("Supplier dropdown click", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Flow.Dropdown")));
	selectDropDownValue("Selecting Supplier dropdown", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Flow.Dropdown")), strFlow);
	System.out.println("strFlow: "+strFlow);
			
	//Entering Factory type 
	if(verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Factory.Dropdown")))==true) {
	strFactory = tdrow.get("Factory");
	selectDropDownValue("Factory Type", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Factory.Dropdown")), strFactory);
	System.out.println("strFactory:" +strFactory);
	}else {
				reportStep("Factory already selected", "pass", false);
		}
			
	//clicking on status radio button
	anyClick("Status Radio button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Status.Radiobutton")));
			
	//Entering Check code
	if(verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Checkcode.Textbox")))==true) {
	strCheckcode = tdrow.get("Checkcode");
	sendKeys("Checkcode Textbox", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Checkcode.Textbox")), strCheckcode);
	System.out.println("strCheckcode:" +strCheckcode);
	}else {
				reportStep("Checkcode already entered", "pass", false);
		}
			
	//Entering Description
	if(verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Description.Textbox")))==true) {
	strDescription = tdrow.get("Description");
	sendKeys("Description", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Description.Textbox")), strDescription);
	System.out.println("strDescription:" +strDescription);
	}else {
				reportStep("Description already entered", "pass", false);
		}
			
	// Enter From Date to select
	if (!tdrow.get("From_Date").equals("")) {

			// Converting date to DD/MMM/YYYY
			String date = dtDateConversion(tdrow.get("From_Date"));
			// Click the calendar
			anyClick("From Date", By.xpath(prop.getProperty("Admin.Manage_Alarms.From_Date.Text")));

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

	// Enter To Date to select
	if (!tdrow.get("To_Date").equals("")) {
			
		// Converting date to DD/MMM/YYYY
		String date = dtDateConversion(tdrow.get("To_Date"));
		// Click the calendar
		anyClick("To Date", By.xpath(prop.getProperty("Admin.Manage_Alarms.To_Date.Text")));
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
			
	//clicking on save button
	anyClick("Save button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Save.Button")));
			
	//Verify Error Message
	String strMessage = driver.findElements(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Error.Message"))).get(0).getText();
	System.out.println("#B Error Message: #C" +strMessage);
	reportStep("Error Message:" +strMessage, "pass", true);
	reportStep("#B Verified the message Testcase id:1015 #C", "Pass", false);
	
}
}