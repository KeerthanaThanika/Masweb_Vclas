package com.volvo.mfg.agv.pages;

import java.util.HashMap;

import com.volvo.automation.commonutilities.ExcelUtils;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;

import com.volvo.automation.commonutilities.CommonWrapperMethods;
import com.volvo.mfg.StepDefinition.BaseTest;
import com.volvo.mfg.StepDefinition.DriverSuite;;

public class Admin_Page extends DriverSuite {

	public HashMap<String, String> tdrow;
	public String strFSNR, strCntrlNo, strMaterialRec, strPackageNo;
	public String strTaskId = "";
	public String ctrlParam, ctrlParamVal, ctrlParamNewVal;
	public int count;
	public String strHandID, strDesc, strStatus, strLastHandID, strColValue;
	public int colHandID, colDesc, colStatus, colEdit, rcount;
	public int sTempAssemblyFind=0;
	// Excel class object to access the function
	ExcelUtils excelUtils = new ExcelUtils();

	// retrieve data from MasWeb_Page
	// Load Test Data File
	public void ScannedGoods_Display() throws InterruptedException {
		String sheetName = "Admin_ScannedGoods";
		// retrieve data from MasWeb_Page
		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);

		// Enter the Control Number value
		if (!tdrow.get("Control_Number").equals("")) {
			sendKeys("Control Number", By.xpath(prop.getProperty("Admin.Scanned_goods.controlno.Text")),
					tdrow.get("Control_Number"));
		}

		// Enter the FSNR value
		if (!tdrow.get("Fsnr").equals("")) {
			sendKeys("Fsnr", By.xpath(prop.getProperty("Admin.Scanned_goods.FSNR.Text")), tdrow.get("Fsnr"));
		}

		anyClick("Display button", By.xpath(prop.getProperty("Admin.Scanned_goods.Display.Button")));

		Thread.sleep(2000);

		// Verify the table is displayed
		verifyElementExist("Table Search Data",
				By.xpath(prop.getProperty("Admin.Scanned_goods.Search.Table") + "/tbody/tr[1]"));

		String tableHeaderColumn = prop.getProperty("Admin.Scanned_goods.Search.Table") + "/thead/tr";

		String tableObject = prop.getProperty("Admin.Scanned_goods.Search.Table") + "/tbody/tr";

		List<WebElement> rows = driver.findElements(By.xpath(tableObject));

		int colFSNR = htmlTableColumnNamePosition("Fsnr", tableHeaderColumn);
		int colCntrlNo = htmlTableColumnNamePosition("Control number", tableHeaderColumn);
		int colMaterialRec = htmlTableColumnNamePosition("Material reception", tableHeaderColumn);
		int colPackageNo = htmlTableColumnNamePosition("Package No", tableHeaderColumn);
		int colEDIT = htmlTableColumnNamePosition("Edit", tableHeaderColumn);

		int row_count = rows.size();
		System.out.println(colEDIT);
		System.out.println("edit" + row_count);

		for (int i = 0; i <= row_count; i++) {
			int rowInc = i + 1;
			String strTableData = prop.getProperty("Admin.Scanned_goods.Search.Table") + "/tbody/tr[" + rowInc + "]/td";
			List<WebElement> tableData = driver.findElements(By.xpath(strTableData));
			strFSNR = tableData.get(colFSNR).getText();
			strCntrlNo = tableData.get(colCntrlNo).getText();
			strMaterialRec = tableData.get(colMaterialRec).getText();
			strPackageNo = tableData.get(colPackageNo).getText();
			System.out.println("frst value :" + strFSNR);
			System.out.println(" " + colEDIT);
			tableData.get(colEDIT).findElements(By.tagName("input")).get(0).click();
			break;

		}

		// Verify the Page is displayed
		verifyElementExist("Page Search", By.xpath(prop.getProperty("Admin.Scanned_goods.Verify.EditPage")));

		// Verify the table is displayed
		verifyElementExist("Table Search Data",
				By.xpath(prop.getProperty("Admin.Scanned_goods.Remove.Table") + "/tbody/tr[1]"));

		String editTableHeaderColumn = prop.getProperty("Admin.Scanned_goods.Remove.Table") + "//table/tbody/tr[1]";

		String editTableObject = prop.getProperty("Admin.Scanned_goods.Remove.Table") + "//table/tbody/tr[2]";

		List<WebElement> editrows = driver.findElements(By.xpath(editTableObject));

		int colRemove = htmlTableColumnNamePosition("Remove", editTableHeaderColumn);

		int edit_row_count = editrows.size();

		for (int i = 0; i <= edit_row_count; i++) {
			int rowInc = i + 2;
			String strEditTableData = prop.getProperty("Admin.Scanned_goods.Remove.Table") + "//table/tbody/tr["
					+ rowInc + "]/td";
			List<WebElement> editTableData = driver.findElements(By.xpath(strEditTableData));
			editTableData.get(colRemove).findElements(By.tagName("input")).get(0).click();
			break;
		}

		String alertMessage = prop.getProperty("Admin.Scanned_goods.Remove.Table.Message") + "/tbody";

		List<WebElement> ErrorMsg = driver.findElements(By.xpath(alertMessage));
		String status = ErrorMsg.get(0).getText();

		System.out.println("Remove Table Verification status: " + status);
		if (!status.equalsIgnoreCase("Deletions made")) {
			reportStep("Table column not removed", "fail", true);
		} else {
			reportStep("Table column removed successfully", "pass", true);
		}

		anyClick("Return button", By.xpath(prop.getProperty("Admin.Scanned_goods.Remove.Table.Return")));

		// Verify the Page is displayed
		verifyElementExist("Page Search", By.xpath(prop.getProperty("Admin.Scanned_goods.Search.Table")));

		// Clearing the Memory
		tdrow.clear();

	}
	
	public void Clear_Value() throws InterruptedException {
		
		driver.findElement(By.xpath(prop.getProperty("Admin.Scanned_goods.controlno.Text"))).clear();
		anyClick("Display button", By.xpath(prop.getProperty("Admin.Scanned_goods.Display.Button")));
		Deletion_Verification();
	}

	public String Deletion_Verification() throws InterruptedException {
		String strNewPackageNo = "";
		String sheetName = "Admin_ScannedGoods";
		try {

			String tableHeaderColumn = prop.getProperty("Admin.Scanned_goods.Search.Table") + "/thead/tr";
			// Retrieving the xpath of the displayed table
			String tableObject = prop.getProperty("Admin.Scanned_goods.Search.Table") + "/tbody/tr";

			List<WebElement> rows = driver.findElements(By.xpath(tableObject));
			int colPackageNo = htmlTableColumnNamePosition("Package No", tableHeaderColumn);
			// getting no. of the rows
			int row_count = rows.size();

			for (int i = 0; i <= row_count; i++) {
				int rowInc = i + 1;
				String strTableData = prop.getProperty("Admin.Scanned_goods.Search.Table") + "/tbody/tr[" + rowInc
						+ "]/td";
				List<WebElement> tableData = driver.findElements(By.xpath(strTableData));
				// Retrieving the new task ID available
				strNewPackageNo = tableData.get(colPackageNo).getText();
				reportStep("New available PackageNo: " + strNewPackageNo, "Pass", true);
				break;
			}

			// verifying the closed task ID
			if (strNewPackageNo.equals(strPackageNo)) {
				reportStep("Table updation failed", "fail", true);
			} else {
				reportStep("Table updated successfully", "pass", true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return strNewPackageNo;
	}


	// Display Content
	public void ControlParameters_Display() throws InterruptedException {
		String sheetName = "Admin_ControlParameter";
		try {
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
			if (!tdrow.get("Control_Parameter").equals("")) {
				ctrlParam = tdrow.get("Control_Parameter");
			}

			sendKeys("Control Parameter", By.xpath(prop.getProperty("Admin.Control_parameter.ControlParameter.Text")),
					ctrlParam);

			anyClick("Display", By.xpath(prop.getProperty("Admin.Control_parameter.Display.Button")));

			Thread.sleep(2000);

			// Verify the table is displayed
			verifyElementExist("Control Parameter Table Search Data",
					By.xpath(prop.getProperty("Admin.Control_parameter.Search.Table")));
			// verifying first column data
			WebElement firstcol = driver
					.findElement(By.xpath(prop.getProperty("Admin.Control_parameter.Display.NewValue.Text")));
			String screen_ctrlParameter = firstcol.getText();
			// System.out.println(screen_ctrlParameter);

			reportStep("New  Control Parameter: " + screen_ctrlParameter, "pass", true);
			System.out.println("old Control Parameter:" + ctrlParam);
			System.out.println("First col data " + screen_ctrlParameter);

			if (screen_ctrlParameter.equals(ctrlParam)) {
				reportStep("Updated Element verified", "pass", true);

			} else {
				reportStep("Updated Element Verification Failed", "Fail", true);

			}
			// Verifying the second data element
			WebElement secondcol = driver
					.findElement(By.xpath(prop.getProperty("Admin.Control_parameter.Display.Value")));
			String screen_val = secondcol.getAttribute("value");

			reportStep("New Control Parameter Value: " + screen_val, "pass", true);

			System.out.println("New Control Parameter Value Typed:" + ctrlParamVal);

			System.out.println("Second col data:" + screen_val);

			if (screen_val.equals(ctrlParamVal)) {
				reportStep("Updated Element verified", "pass", true);

			} else {
				reportStep("Updated Element Verification Failed", "Fail", true);

			}

			// Clearing the Memory
			tdrow.clear();
		} catch (Exception e) {
			System.out.println("Failed to display");
		}

	}

	public int getRandomNumber(int range) {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(range);
		return randomInt;
	}

	// Create New Item
	public void ControlParameters_Create() throws InterruptedException {
		String sheetName = "Admin_ControlParameter";
		try {
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
			// Click the Create button
			anyClick("Create", By.xpath(prop.getProperty("Admin.Control_parameter.Create.Button")));

			Thread.sleep(2000);

			// Verify the Page is displayed
			verifyElementExist("Create Control Parameter page",
					By.xpath(prop.getProperty("Admin.Control_parameter.Create.ControlParameter.Text")));
			// Clearing the Memory
			tdrow.clear();
		} catch (Exception e) {
			System.out.println("Create Page  Verification Failed");

		}
	}

	// Create Control Parameter using Random Number and excel
	public void ControlParameters_CreateNew(String button) throws InterruptedException {
		String sheetName = "Admin_ControlParameter";
		try {
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);

			if (button == "Save") {
				ctrlParam = "SEL";
				ctrlParamVal = "100";
				ctrlParam = ctrlParam + getRandomNumber(999);

				// Enter the Control Parameter
				if (!tdrow.get("Create_Control_Parameter").equals("")) {
					sendKeys("Create Control Parameter",
							By.xpath(prop.getProperty("Admin.Control_parameter.Create.ControlParameter.Text")),
							tdrow.get("Create_Control_Parameter"));
				} else {

					sendKeys("Create Control Parameter",
							By.xpath(prop.getProperty("Admin.Control_parameter.Create.ControlParameter.Text")),
							ctrlParam);
				}

				// Enter the Value
				if (!tdrow.get("Value").equals("")) {
					sendKeys("Value", By.xpath(prop.getProperty("Admin.Control_parameter.Create.Value.Text")),
							tdrow.get("Value"));
					ctrlParamVal = tdrow.get("Value");
				} else {
					sendKeys("Value", By.xpath(prop.getProperty("Admin.Control_parameter.Create.Value.Text")),
							ctrlParamVal);
				}

				// Click the Save button
				anyClick("Save", By.xpath(prop.getProperty("Admin.Control_parameter.Create.Save.Button")));

				// verifying the message
				WebElement a = driver.findElement(By.xpath(prop.getProperty("Admin.Control_parameter.Create.Message")));
				String status = a.getText();
				System.out.println(" Verification status: " + a.getText());

				if (status.equalsIgnoreCase("Control Parameter created. Press return to get back.")) {
					reportStep("Control Parameter Created Successfully", "pass", true);
				} else {
					reportStep("One item with same identity already exists!", "Fail", true);
				}

			}

			else if (button == "Return") {
				anyClick("Return", By.xpath(prop.getProperty("Admin.Control_parameter.Create.Return.Button")));
				Thread.sleep(2000);
				verifyElementExist("Control Parameter page",
						By.xpath(prop.getProperty("Admin.Control_parameter.ControlParameter.Text")));

			}

			else {

				reportStep("Return to page failed!", "Fail", true);

			}

		} catch (Exception e) {

			System.out.println("Control Parameter:One item with same identity already exists!");
		}
		// Clearing the Memory
		tdrow.clear();

	}

	// Edit Control Parameter
		public void ControlParameters_Edit(String EditOption) throws InterruptedException {
			String sheetName = "Admin_ControlParameter";
			ctrlParamNewVal = "200";
			try {
				tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
				if (EditOption == "Save") {

					// driver.findElement(By.xpath(prop.getProperty("Admin.Control_parameter.Display.Value.Text"))).clear();

					if (!tdrow.get("NewValue").equals("")) {
						ctrlParamNewVal = tdrow.get("NewValue");
					}

					sendKeys("Existing Value: " + ctrlParamVal,
							By.xpath(prop.getProperty("Admin.Control_parameter.Display.Value.Text")), ctrlParamNewVal);

					// Return button is clicked
					anyClick("Return", By.xpath(prop.getProperty("Admin.Control_parameter.Display.Return.Button")));

					// VErify the Old Value: ctrlParamVal

					WebElement oldvalue = driver
							.findElement(By.xpath(prop.getProperty("Admin.Control_parameter.Display.Value")));
					String screen_ctrlParamval = oldvalue.getAttribute("value");

					// System.out.println(ctrlParamVal);
					System.out.println("typed value:" + ctrlParamVal);
					System.out.println("Screen value" + screen_ctrlParamval);
					// reportStep("New available TaskID: " + ctrlParamNewVal, "pass", true);

					if (screen_ctrlParamval.equals(ctrlParamVal)) {
						reportStep("Edit Element verified", "pass", true);

					} else {
						reportStep("Edit Element Verification Failed", "Fail", true);

					}

					// Send New value
					sendKeys("Create Control Parameter",
							By.xpath(prop.getProperty("Admin.Control_parameter.Display.Value.Text")), ctrlParamNewVal);
					
					sendKeys("Create Control Parameter",
							By.xpath(prop.getProperty("Admin.Control_parameter.ControlParameter.Text")),
							ctrlParam);
					anyClick("Display", By.xpath(prop.getProperty("Admin.Control_parameter.Display.Button")));
					sendKeys("Create Control Parameter",
							By.xpath(prop.getProperty("Admin.Control_parameter.Display.Value.Text")), ctrlParamNewVal);

					

					anyClick("Save", By.xpath(prop.getProperty("Admin.Control_parameter.Display.Save.Button")));
					Thread.sleep(5000);

					ctrlParamVal = ctrlParamNewVal;

					// verifying the message
					WebElement a = driver.findElement(By.xpath(prop.getProperty("Admin.Control_parameter.Update.Message")));
					String status = a.getText();
					System.out.println(" Verification status: " + a.getText());

					if (status.equalsIgnoreCase("Update done")) {
						reportStep(" Control Parameter Updated Successfully", "pass", true);
					} else {
						reportStep("Control Parameter Update Failed", "Fail", true);
					}
					// Clearing the Memory
					tdrow.clear();

				}
			} catch (Exception e) {
				System.out.println("Control parameter Update Failed");
			}

			if (EditOption == "Remove")
				try {

					anyClick("Remove", By.xpath(prop.getProperty("Admin.Control_parameter.Display.Remove.Button")));
					isAlertPresent("ok");

					// verifying the message
					WebElement a = driver.findElement(By.xpath(prop.getProperty("Admin.Control_parameter.Delete.Message")));
					String status = a.getText();
					System.out.println(" Verification status: " + a.getText());

					if (status.equalsIgnoreCase("Deletions made")) {
						reportStep(" Control Parameter Deleted Successfully", "pass", true);
					} else {
						reportStep("Control Parameter Failed To Delete", "Fail", true);
					}

				}

				catch (Exception e) {
					System.out.println("Control parameter Remove Failed");
				}

		}

	// Clear SearchField
	public void ControlParameters_Clear() throws InterruptedException {

		anyClick("Clear", By.xpath(prop.getProperty("Admin.Control_parameter.Clear.Button")));
		// waitForElement(driver,
		// By.xpath(prop.getProperty("Admin.Control_parameter.Clear.Button")), 10);
		Thread.sleep(2000);

		// Verify table empty
		// clear the memory
		ctrlParam = "";
		ctrlParamVal = "";
		ctrlParamNewVal = "";

	}

	// Add New Row
	public void TestPrinter_Add() throws InterruptedException {

		// retrieve data from MasWeb_Page
		// Load Test Data File
		String sheetName = "Admin_TestPrinter";
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);

		// Clear Fpos TextBox Before Writing
		// driver.findElement(By.xpath(prop.getProperty("Admin.Test_Printer.Fpos.Text"))).clear();

		// Clear To_Position TextBox Before Writing
		// driver.findElement(By.xpath(prop.getProperty("Admin.Test_Printer.Toposition.Text"))).clear();

		for (int i = 0; i <= 3; i++) {
			anyClick("Add", By.xpath(prop.getProperty("Admin.Test_Printer.Add.button")));
		}

		for (int i = 0; i <= 3; i++) {

			// Enter Type to select
			if (!tdrow.get("Type").equals("")) {
				selectDropDownValue("Type dropdown", By.xpath(
						prop.getProperty("Admin.Test_Printer.Type.Dropdown").replace("&Value", String.valueOf(i))),
						tdrow.get("Type"));
			}

			// Enter the Fpos
			if (!tdrow.get("Fpos").equals("")) {

				sendKeys("Fposs",
						By.xpath(prop.getProperty("Admin.Test_Printer.Fpos.Text").replace("&Value", String.valueOf(i))),
						tdrow.get("Fpos"));

			}

			// Enter the To position
			if (!tdrow.get("To_Position").equals("")) {
				sendKeys("To position", By.xpath(
						prop.getProperty("Admin.Test_Printer.Toposition.Text").replace("&Value", String.valueOf(i))),
						tdrow.get("To_Position"));
			}
			// Enter the Host Name
			if (!tdrow.get("Host_Name").equals("")) {
				sendKeys("Host Name",
						By.xpath(prop.getProperty("Admin.Test_Printer.Host.Text").replace("&Value", String.valueOf(i))),
						tdrow.get("Host_Name"));
			}
			// Enter the Queue
			if (!tdrow.get("Queue").equals("")) {
				sendKeys("Queue",
						By.xpath(
								prop.getProperty("Admin.Test_Printer.Queue.Text").replace("&Value", String.valueOf(i))),
						tdrow.get("Queue"));
			}

		}
		// Verifying the row added
		String tableObject = prop.getProperty("Admin.Test_Printer.Table") + "/tbody/tr";

		List<WebElement> tableBodyRow = driver.findElements(By.xpath(tableObject));

		int rowCount = tableBodyRow.size();
		System.out.println(rowCount);

		if (rowCount == 6) {
			System.out.println("Verification status:Row added Successfully");
			reportStep("Verification Status: " + rowCount + " Row added successfully " + "", "pass", true);
		} else {
			reportStep(rowCount + " Row Failed to add " + "", "Fail", true);
		}
		Thread.sleep(2000);

		// Clearing the Memory
		tdrow.clear();

	}

	// Remove Row
	public void TestPrinter_Remove() throws InterruptedException {

		anyClick("Remove", By.xpath(prop.getProperty("Admin.Test_Printer.Remove.button")));

		Thread.sleep(2000);
		// Verifying the row deleted
		String tableObject = prop.getProperty("Admin.Test_Printer.Table") + "/tbody/tr";

		List<WebElement> tableBodyRow = driver.findElements(By.xpath(tableObject));

		int rowCount = tableBodyRow.size();

		if (rowCount == 5) {
			System.out.println("Verification status:Row Removed Successfully");
			reportStep("Verification Status: 1 Row Removed successfully " + "", "pass", true);
		} else {
			reportStep("Failed to remove " + "", "Fail", true);
		}

	}

	// Display Content
	public void TestPrinter_Print() throws InterruptedException {

		anyClick("Print", By.xpath(prop.getProperty("Admin.Test_Printer.Print.button")));
		Thread.sleep(2000);
		// Verify the table is displayed
		verifyElementExist("Test Printer Table Data", By.xpath(prop.getProperty("Admin.Test_Printer.Table")));

	}

	// Clear SerachField
	public void TestPrinter_Clear() throws InterruptedException {

		anyClick("Clear", By.xpath(prop.getProperty("Admin.Test_Printer.Clear.button")));

		Thread.sleep(2000);
		// Verify the table is displayed
		verifyElementExist("Test Printer Clear ", By.xpath(prop.getProperty("Admin.Test_Printer.Table")));

	}

	public void Managealarms(String Click_Button) throws InterruptedException {
		try {
			// retrieve data from MasWeb_Page
			// Load Test Data File
			String sheetName = "Admin_ManageAlarms";
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);

			// Enter Plant to select
			if (!tdrow.get("ManageAlarms_Plant").equals("")) {
				selectDropDownValue("Plant dropdown", By.xpath(prop.getProperty("Admin.Manage_Alarms.Plant.Dropdown")),
						tdrow.get("ManageAlarms_Plant"));
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

			// Enter Status to select
			if (!tdrow.get("Status").equals("")) {
				selectDropDownValue("Status dropdown",
						By.xpath(prop.getProperty("Admin.Manage_Alarms.Status.Dropdown")), tdrow.get("Status"));
			}

			if (Click_Button.equals("Display")) {

				anyClick("Display", By.xpath(prop.getProperty("Admin.Manage_Alarms.Display.Button")));

			} else
				anyClick("Clear", By.xpath(prop.getProperty("Admin.Manage_Alarms.Clear.Button")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Managealarmsids_Create() throws InterruptedException {
		try {
			String sheetName = "Admin_ManageAlarms";
			// retrieve data from MasWeb_Page
			// Load Test Data File
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
			String tableHeaderColumn = prop.getProperty("Admin.Manage_alarm_ids.Create.Table") + "/thead/tr";

			int colHandID = htmlTableColumnNamePosition("Hand ID", tableHeaderColumn);
			int colDesc = htmlTableColumnNamePosition("Description", tableHeaderColumn);
			int colStatus = htmlTableColumnNamePosition("Status", tableHeaderColumn);
			int colEdit = htmlTableColumnNamePosition("Edit", tableHeaderColumn);

			String tableData = prop.getProperty("Admin.Manage_alarm_ids.Create.Table") + "/tbody/tr";

			List<WebElement> rowsFetch = driver.findElements(By.xpath(tableData));

			int count = rowsFetch.size();
			System.out.println(" " + count);

			String dataFetch = prop.getProperty("Admin.Manage_alarm_ids.Create.Table") + "/tbody/tr[" + count + "]/td";
			List<WebElement> fetchList = driver.findElements(By.xpath(dataFetch));

			String strColValue = fetchList.get(colHandID).getText();

			System.out.println(" " + strColValue);

			int sum = Integer.parseInt(strColValue) + 1;
			System.out.println(sum);

			anyClick("Create", By.xpath(prop.getProperty("Admin.Manage_alarm_ids.Create.Button")));

			// Verify the Page is displayed
			verifyElementExist("Create page", By.xpath(prop.getProperty("Admin.Manage_alarm_ids.Create.Search.Page")));

			anyClick("Hand ID", By.xpath(prop.getProperty("Admin.Manage_alarm_ids.Create.Hand_Id.Text")));

			sendKeys("Hand ID", By.xpath(prop.getProperty("Admin.Manage_alarm_ids.Create.Hand_Id.Text")),
					String.valueOf(sum));

			// Enter ManageAlarmsids_Status to select
			if (!tdrow.get("ManageAlarmID_Status").equals("")) {
				selectDropDownByIndex("Status dropdown",
						By.xpath(prop.getProperty("Admin.Manage_alarm_ids.Create.Status.Dropdown")),
						tdrow.get("ManageAlarmID_Status"));
			} else {
				reportStep("Enter valid prior code", "fail", true);
			}

			// Enter Messages to select
			if (!tdrow.get("Messages").equals("")) {
				sendKeys("Messages", By.xpath(prop.getProperty("Admin.Manage_alarm_ids.Create.Messages.Text")),
						tdrow.get("Messages"));
			} else {
				reportStep("Enter valid alarm id description", "fail", true);

			}

			anyClick("Create", By.xpath(prop.getProperty("Admin.Manage_alarm_ids.Create_Page.Create.Button")));

			rcount = count + 1;
			String editedRow = prop.getProperty("Admin.Manage_alarm_ids.Create.Table") + "/tbody/tr[" + rcount + "]/td";
			List<WebElement> editedHandID = driver.findElements(By.xpath(editedRow));
			// System.out.println(" " +editedHandID);

			strHandID = editedHandID.get(colHandID).getText();
			strDesc = editedHandID.get(colDesc).getText();
			strStatus = editedHandID.get(colStatus).getText();
			System.out.println(strHandID);
			System.out.println(strDesc);
			System.out.println(strStatus);

			System.out.println(sum);

			if (Integer.parseInt(strHandID) == sum) {
				reportStep("Created Hand ID: " + sum + " is displayed", "Pass", false);
				if (strDesc.equals(tdrow.get("Messages")))

				{
					reportStep("Created Description: " + strDesc + " is displayed", "Pass", false);
					if (strStatus.equals(tdrow.get("ManageAlarmID_Status"))) {
						reportStep("Created Messages: " + strStatus + " is displayed", "Pass", false);
						System.out.println(colEdit);
						editedHandID.get(colEdit).findElements(By.tagName("input")).get(0).click();
					} else {
						reportStep("Created Description: " + (tdrow.get("ManageAlarmID_Status")) + " Actual displayed: "
								+ strStatus, "Fail", true);
					}
				}

				else {
					reportStep("Created Description: " + (tdrow.get("Messages")) + " Actual displayed: " + strDesc,
							"Fail", true);

				}
			} else {
				reportStep("Created Hand ID: " + sum + " Actual displayed: " + strHandID, "Fail", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Managealarmsids_Remove() throws InterruptedException {
		try {

			anyClick("Remove", By.xpath(prop.getProperty("Admin.Manage_alarm_ids.Create_Page.Remove.Button")));
			String tableData = prop.getProperty("Admin.Manage_alarm_ids.Create.Table") + "/tbody/tr";

			List<WebElement> rowsFetch = driver.findElements(By.xpath(tableData));

			int count = rowsFetch.size();
			System.out.println(" " + count);
			String dataFetch = prop.getProperty("Admin.Manage_alarm_ids.Create.Table") + "/tbody/tr[" + count + "]/td";
			List<WebElement> fetchList = driver.findElements(By.xpath(dataFetch));

			String strColValue = fetchList.get(colHandID).getText();

			String lastRow = prop.getProperty("Admin.Manage_alarm_ids.Create.Table") + "/tbody/tr[" + count + "]/td";
			List<WebElement> lastRowValue = driver.findElements(By.xpath(lastRow));

			strLastHandID = lastRowValue.get(colHandID).getText();
			System.out.println(" " + strLastHandID);
			System.out.println(" " + strColValue);

			if (strLastHandID.equals(strColValue)) {
				reportStep("Removing edited row is verified successfully", "pass", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String ShowClose_Tasks_display() throws InterruptedException {
		String TaskID = "";
		String sheetName = "Admin_ShowTask";

		try {
			// retrieve data from MasWeb_Page
			// Load Test Data File
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
			String tableHeaderRow = prop.getProperty("Admin_ShowClose.Search.Table") + "/thead/tr";

			// Enter the Tasks
			if (!tdrow.get("TaskID_ShowTask").equals("")) {
				sendKeys("Tasks", By.xpath(prop.getProperty("Admin_ShowClose.Tasks.Text")),
						tdrow.get("TaskID_ShowTask"));
			}

			anyClick("Display", By.xpath(prop.getProperty("Admin_ShowClose.Display.Button")));
			Thread.sleep(2000);
			// Verify the table is displayed

			verifyElementExist("Search table Data",
					By.xpath(prop.getProperty("Admin_ShowClose.Search.Table") + "/tbody/tr[1]"));

			// Retriving the task id and clicking edit

			String tableObject = prop.getProperty("Admin_ShowClose.Search.Table") + "/tbody/tr";
			List<WebElement> rows = driver.findElements(By.xpath(tableObject));
			int colEDIT = htmlTableColumnNamePosition("EDIT", tableHeaderRow);
			int colTaskID = htmlTableColumnNamePosition("Task id", tableHeaderRow);
			int row_count = rows.size();

			for (int i = 0; i <= row_count; i++) {
				int rowInc = i + 1;
				String strTableData = prop.getProperty("Admin_ShowClose.Search.Table") + "/tbody/tr[" + rowInc + "]/td";
				List<WebElement> tableData = driver.findElements(By.xpath(strTableData));
				strTaskId = tableData.get(colTaskID).getText();
				tableData.get(colEDIT).click();
				verifyElementExist("edit page ", By.xpath(prop.getProperty("Admin_ShowClose.Edit.Close_Task.Button")));
				reportStep("TaskID need to be closed: " + strTaskId, "Pass", true);
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return strTaskId;

	}

	public void ShowClose_Edit_Return() throws InterruptedException {
		String sheetName = "Admin_ShowTask";
		try {
			// retrieve data from MasWeb_Page
			// Load Test Data File
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);

			// Enter the Tasks
			if (!tdrow.get("Package_Number").equals("")) {
				sendKeys("Package_Number", By.xpath(prop.getProperty("Admin_ShowClose.Edit.Package_Number")),
						tdrow.get("Package_Number"));
			}
			// clicking of return button
			anyClick("Return", By.xpath(prop.getProperty("Admin_ShowClose.Edit.Return.Button")));
			verifyElementExist("ShowClose Task",
					By.xpath(prop.getProperty("Admin_ShowClose.Search.Table") + "/tbody/tr[1]"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ShowClose_Tasks_clear() throws InterruptedException {
		String sheetName = "Admin_ShowTask";
		try {
			// retrieve data from MasWeb_Page
			// Load Test Data File
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);

			// Enter the Tasks
			if (!tdrow.get("TaskID_ShowTask").equals("")) {
				sendKeys("Tasks", By.xpath(prop.getProperty("Admin_ShowClose.Tasks.Text")),
						tdrow.get("TaskID_ShowTask"));
			}
			// clicking of clear button
			anyClick("Clear", By.xpath(prop.getProperty("Admin_ShowClose.Clear.Button")));
			verifyElementExist(" table Data cleared", By.xpath(prop.getProperty("Admin_ShowClose.Search.Table")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ShowClose_Edit_Close_Task() throws InterruptedException {
		String message = "";
		String sheetName = "Admin_ShowTask";
		try {

			// retrieve data from MasWeb_Page
			// Load Test Data File
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);

			// Enter the Tasks
			if (!tdrow.get("Package_Number").equals("")) {
				sendKeys("Package_Number", By.xpath(prop.getProperty("Admin_ShowClose.Edit.Package_Number")),
						tdrow.get("Package_Number"));
			}
			// clicking of close task button
			anyClick("Close_Task", By.xpath(prop.getProperty("Admin_ShowClose.Edit.Close_Task.Button")));
			// handling the Alert
			isAlertPresent("ok");
			// Retrieve the Xpath of error message
			String alertMessage = prop.getProperty("Admin_ShowClose.Edit.Close_Task.Messages") + "/tbody/tr[1]";

			List<WebElement> ErrorMsg = driver.findElements(By.xpath(alertMessage));
			// getting the error message displayed
			String status = ErrorMsg.get(0).getText();

			// verifying the alert message
			if (!status.equalsIgnoreCase("update failed")) {
				reportStep("Closed task ID  successfully", "pass", true);
			} else {
				reportStep("Closing of task ID failed", "fail", true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String CloseTask_Verification() throws InterruptedException {
		String strNewTaskID = "";
		String sheetName = "Admin_ShowTask";
		try {

			String tableHeaderRow = prop.getProperty("Admin_ShowClose.Search.Table") + "/thead/tr";
			anyClick("Return", By.xpath(prop.getProperty("Admin_ShowClose.Edit.Return.Button")));
			verifyElementExist("ShowClose Task",
					By.xpath(prop.getProperty("Admin_ShowClose.Search.Table") + "/tbody/tr[1]"));

			// Retrieving the xpath of the displayed table

			String tableObject = prop.getProperty("Admin_ShowClose.Search.Table") + "/tbody/tr";
			List<WebElement> rows = driver.findElements(By.xpath(tableObject));
			int colTaskID = htmlTableColumnNamePosition("Task id", tableHeaderRow);

			// getting no. of the rows
			int row_count = rows.size();

			for (int i = 0; i <= row_count; i++) {
				int rowInc = i + 1;
				String strNewTableData = prop.getProperty("Admin_ShowClose.Search.Table") + "/tbody/tr[" + rowInc
						+ "]/td";
				List<WebElement> tableData = driver.findElements(By.xpath(strNewTableData));

				// Retrieving the new task ID available

				strNewTaskID = tableData.get(colTaskID).getText();
				reportStep("New available TaskID: " + strNewTaskID, "Pass", true);
				break;
			}

			// verifying the closed task ID

			if (strNewTaskID.equals(strTaskId)) {
				reportStep("Task closing failed", "fail", true);
			} else {
				reportStep("Task closed successfully", "pass", true);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strNewTaskID;
	}
	
	
	public void Simulator_Assemblyline_Click(int clickno) throws InterruptedException {
		try {

			driver.switchTo().window(browser[1]);
			//driver.switchTo().window(browser[1]);
			String sheetName = "Admin_Simulator";
			// retrieve data from MasWeb_Page
			// Load Test Data File
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
			String tableData = prop.getProperty("Assembly_line.Search.Table") + "/tbody/tr";
			
			String expVal = null;
			int finalValue = 0;
			int iTempClick = 0;
			
			do 
			{
				//driver.findElement(By.xpath(prop.getProperty("Assembly_line.Display.Button"))).click();
				anyClick("Display", By.xpath(prop.getProperty("Assembly_line.Display.Button")));
				iTempClick = iTempClick+1;
				driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
				System.out.println(driver.findElements(By.xpath(tableData)).size());
				if (driver.findElements(By.xpath(tableData)).size() > 2) {
					break;
				}
			} while(iTempClick != 10);
			driver.manage().timeouts().implicitlyWait(Default_Wait_4_Page,TimeUnit.SECONDS);
			 //while(iTempClick != 10);
			
			// To locate table and table header column
			String tableHeaderColumn = prop.getProperty("Assembly_line.Search.Table") + "/thead/tr";
			
			// Get the column position of each column
			int colSeq = htmlTableColumnNamePosition("Sequence Type", tableHeaderColumn);
			int colCallOff = htmlTableColumnNamePosition("Call off", tableHeaderColumn);
			int colTom = htmlTableColumnNamePosition("T.o.m. löpnr", tableHeaderColumn);

			// Row Fetch
			List<WebElement> rowsFetch = driver.findElements(By.xpath(tableData));
			// calculate no of rows In table.
			int count = rowsFetch.size();
			System.out.println("Table Size " + count);
			String oneVal = tdrow.get("Sequence");
			System.out.println("Search Sequence type :" + oneVal);
			// Loop will execute till the last row of table.
			for (int i = sTempAssemblyFind; i <= rowsFetch.size() - 1; i++) {
				
				// Fetch the Row data
				List<WebElement> colData = rowsFetch.get(i).findElements(By.tagName("td"));

				System.out.println("match actual data:" + colData.get(colSeq).getText());
				// If the colData match with the description, it will initiate one more inner
				// loop to click the button
				if (colData.get(colSeq).getText().equalsIgnoreCase(oneVal)) {
					
					sTempAssemblyFind = i;
					System.out.println("sequence type matched");
					final int index = i + 1;
					
					// storing the lopnr value from the cell in to the string variable PrevVal
					String prevVal = driver.findElements(By.xpath(
							prop.getProperty("Assembly_line.Table.Data").replace("&Value", String.valueOf(index))))
							.get(colTom).getText();

					System.out.println("Before Clicking T.o.m. löpnr :" + prevVal);

					int guessCount = 1;
					do {
						// Locate Button Element
						WebElement button = driver.findElements(By.xpath(
								prop.getProperty("Assembly_line.Table.Data").replace("&Value", String.valueOf(index))))
								.get(colCallOff);
						// wait until the element is clickable
						WebDriverWait wait = new WebDriverWait(driver, 30);
						button = wait.until(ExpectedConditions.elementToBeClickable(button));
						// click the button
						button.click();

						guessCount++;
						// storing the updated lopnr value from the cell in to the string variable
						// expVal
						expVal = driver.findElements(By.xpath(
								prop.getProperty("Assembly_line.Table.Data").replace("&Value", String.valueOf(index))))
								.get(colTom).getText();
						// calculate final value to verify no .of times clicked
						finalValue = Integer.parseInt(prevVal) + clickno;

					} while (guessCount <= clickno);

					break;
				}

			}

			System.out.println("current T.o.m. löpnr after clicking:" + expVal);
			System.out.println("expected:" + finalValue);
			// verifying the no. of times clicked
			if (expVal.equals(String.valueOf(finalValue))) {
				System.out.println("click verified");
				reportStep(clickno + " no. of times clicked in Assembly Line Successfully", "pass", true);

			}

			else {
				System.out.println("click not verified");
				reportStep(clickno + " no. of times clicked Expected: " + expVal + " Actual: "
						+ String.valueOf(finalValue), "Fail", true);
			}

		} catch (Exception e) {

			System.out.println("Failed to click- Exception thrown");
			reportStep("Failed to click - Exception thrown", "Fail", true);

		}

	}
	
}
