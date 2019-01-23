package com.volvo.mfg.agv.pages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.ExtentTest;

import com.volvo.automation.commonutilities.CommonWrapperMethods;
import com.volvo.automation.commonutilities.DB_Connectivity;
import com.volvo.automation.commonutilities.ExcelUtils;
import com.volvo.automation.commonutilities.QueryInput;
import com.volvo.mfg.StepDefinition.BaseTest;
import com.volvo.mfg.StepDefinition.DriverSuite;;

public class V90_Pages extends DriverSuite implements QueryInput {

	String sheetName = "V90_Pages";
	public HashMap<String, String> tdrow;
	public String strBODYID, strDeliveryNote, strDeliveryId;

	// Excel class object to access the function
	ExcelUtils excelUtils = new ExcelUtils();

		public void ScanV90RFIDTag_Display() throws InterruptedException {

		// retrieve data from MasWeb_Page
		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);

		anyClick("Display button", By.xpath(prop.getProperty("V90.ScanV90_RFIDTag.Display.Button")));

		Thread.sleep(2000);

		// Verify the table is displayed
		verifyElementExist("Table Search Data", By.xpath(prop.getProperty("V90.ScanV90_RFIDTag.Search.Table")));
		
		String tableHeaderColumn = prop.getProperty("V90.ScanV90_RFIDTag.Search.Table") + "/thead/tr";

		String tableObject = prop.getProperty("V90.ScanV90_RFIDTag.Search.Table") + "/tbody/tr";

		List<WebElement> rows = driver.findElements(By.xpath(tableObject));

		int colLabel = htmlTableColumnNamePosition("Label", tableHeaderColumn);
		
        int row_count = rows.size();
		System.out.println(colLabel);
		System.out.println("edit" + row_count);

		for (int i = 0; i <= row_count; i++) {
			int rowInc = i + 1;
			String strTableData = prop.getProperty("V90.ScanV90_RFIDTag.Search.Table") + "/tbody/tr[" + rowInc + "]/td";
			List<WebElement> tableData = driver.findElements(By.xpath(strTableData));
			String strLabel = tableData.get(colLabel).getText();
			System.out.println("frst value :" + strLabel);
			//SPECIFIC BODY ID AND RFID FETCH
			
			
			//tableData.get(colEDIT).findElements(By.tagName("input")).get(0).click();
			break;

		}
		// Enter the RFID value
		if (!tdrow.get("RFID").equals("")) {
			sendKeys("RFID", By.xpath(prop.getProperty("V90.ScanV90_RFIDTag.RFID.Text")), tdrow.get("RFID"));
		}

		anyClick("Request info button", By.xpath(prop.getProperty("V90.ScanV90_RFIDTag.Request.Button")));

		// Verify the table is displayed
		verifyElementExist("Table Search Data", By.xpath(prop.getProperty("V90.ScanV90_RFIDTag.Search.Table")));
		
	}
		

	public void DeliverV90Tasks_Deliver() throws InterruptedException {

		// retrieve data from MasWeb_Page
		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);

		// Enter the Body ID value
		if (!tdrow.get("Body_Id").equals("")) {
			sendKeys("Body ID", By.xpath(prop.getProperty("V90.DeliverV90_Tasks.BodyId.Text")),
					"S" + (tdrow.get("Body_Id")));
		}

		// Enter the Trailer ID value
		if (!tdrow.get("Trailer_Id").equals("")) {
			sendKeys("Trailer ID", By.xpath(prop.getProperty("V90.DeliverV90_Tasks.TrailerId.Text")),
					tdrow.get("Trailer_Id"));
		}

		anyClick("Add button", By.xpath(prop.getProperty("V90.DeliverV90_Tasks.Add.Button")));

		// Verify the table is displayed
		verifyElementExist("Table Search Data", By.xpath(prop.getProperty("V90.DeliverV90_Tasks.Search.Table")));

		anyClick("Deliver button", By.xpath(prop.getProperty("V90.DeliverV90_Tasks.Deliver.Button")));

		isAlertPresent("OK");

		Thread.sleep(2000);

		// Verify the Message is displayed
		verifyElementExist("Message Verify", By.xpath(prop.getProperty("V90.DeliverV90_Tasks.Message.Verify")));

		String tableHeaderColumn = prop.getProperty("V90.DeliverV90_Tasks.Search.Table") + "/tbody/tr[1]";

		String strDeliveryNote = driver
				.findElement(By.xpath(prop.getProperty("V90.DeliverV90_Tasks.Search.Table") + "/tbody/tr[2]/td[3]"))
				.getText();

		excelUtils.excelUpdateValue("V90_Pages", "Delivery_Note_Id", refTestDataName, strDeliveryNote);

		System.out.println("Delivery note ID" + strDeliveryNote);

		// Clearing the Memory
		tdrow.clear();

	}

	public void V90PackagingNote_Display(String strChange) throws InterruptedException {

		// retrieve data from MasWeb_Page
		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);

		if (strChange == "NormalTrailer") {

			// Enter the Trailer ID value
			if (!tdrow.get("Trailer_Id").equals("")) {
				sendKeys("Trailer ID", By.xpath(prop.getProperty("V90.V90Packaging_Note.TrailerId.Text")),
						tdrow.get("Trailer_Id"));
			}
		} else {
			// Enter the New Trailer ID value
			if (!tdrow.get("Change_Trailer_Id").equals("")) {
				sendKeys("New Trailer ID", By.xpath(prop.getProperty("V90.V90Packaging_Note.TrailerId.Text")),
						tdrow.get("Change_Trailer_Id"));
			}

			// Enter the New Body ID value
			if (!tdrow.get("Add_Body_Id").equals("")) {
				sendKeys("New Body ID", By.xpath(prop.getProperty("V90.V90Packaging_Note.TrailerId.Text")),
						tdrow.get("Add_Body_Id"));
			}

		}

		anyClick("Display button", By.xpath(prop.getProperty("V90.V90Outtake_Stations.Display.Button")));

		Thread.sleep(2000);

		// Verify the table is displayed
		verifyElementExist("Table Search Data", By.xpath(prop.getProperty("V90.V90Packaging_Note.Search.Table")));

		String tableHeader = prop.getProperty("V90.V90Packaging_Note.Search.Table") + "/tbody/tr[1]";
		String tableObj = prop.getProperty("V90.V90Packaging_Note.Search.Table") + "/tbody/tr";
		List<WebElement> tableData = driver.findElements(By.xpath(tableObj));

		int colAction = htmlTableColumnNamePosition("Action", tableHeader);
		int rowCount = tableData.size();
		System.out.println("no. of rows" +rowCount);
		for (int i = 2; i <= rowCount; i++) {
			String strDeliveryId = driver.findElement(By.xpath(prop.getProperty("V90.V90Packaging_Note.Search.Table") + "/tbody/tr[" + i + "]/td[2]")).getText();
			String strBodyId = driver.findElement(By.xpath(prop.getProperty("V90.V90Packaging_Note.Search.Table") + "/tbody/tr[" + i + "]/td[4]")).getText();
			
			System.out.println(strDeliveryId);
			System.out.println(strBodyId);

			if (strDeliveryId.equalsIgnoreCase(tdrow.get("Delivery_Note_Id"))) {
				reportStep("Delivery Note Id " + strDeliveryId + " is verified successfully", "Pass", false);
				if (strBodyId.equalsIgnoreCase(tdrow.get("Body_Id"))) {
					reportStep("Body Id " + strBodyId + " is verified successfully", "Pass", false);
				} else {
					//reportStep("Created Body Id : " + (tdrow.get("Body_Id")) + " Actual displayed: " + strBodyId,"Fail", true);
				}
			} else {
				reportStep("Created Delivery Note Id : " + strDeliveryNote + " Actual displayed: " + strDeliveryId,
						"Fail", true);
			}
			break;
		}

		// Verify the Page is displayed
		// verifyElementExist("Page Search",
		// By.xpath(prop.getProperty("Admin.Scanned_goods.Verify.EditPage")));

	}

	public void V90OuttakeStation_Display() throws InterruptedException {

		// retrieve data from MasWeb_Page
		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);

		// Enter the Trailer ID value
		if (!tdrow.get("Outtake_Station").equals("")) {
			sendKeys("Out take station", By.xpath(prop.getProperty("V90.V90Outtake_Stations.Out_take_station.Text")),
					tdrow.get("Outtake_Station"));
		}

		// Enter Printer Id to select
		if (!tdrow.get("Printer_Id").equals("")) {
			selectDropDownValue("Printer Id dropdown",
					By.xpath(prop.getProperty("V90.V90Outtake_Stations.Printer_Id.Dropdown")), tdrow.get("Printer_Id"));
		}

		// Enter No. Of Labels to select
		if (!tdrow.get("No_Of_Labels").equals("")) {
			selectDropDownValue("No. Of Labels dropdown",
					By.xpath(prop.getProperty("V90.V90Outtake_Stations.No_Of_Labels.Dropdown")),
					tdrow.get("No_Of_Labels"));
		}

		anyClick("Display button", By.xpath(prop.getProperty("V90.V90Packaging_Note.Display.Button")));

		Thread.sleep(2000);

		// Verify the table is displayed
		verifyElementExist("Table Search Data", By.xpath(prop.getProperty("V90.V90Packaging_Note.Search.Table")));

		String tableHeader = prop.getProperty("V90.V90Packaging_Note.Search.Table") + "/tbody/tr[1]";

		String tableObj = prop.getProperty("V90.V90Packaging_Note.Search.Table") + "/tbody/tr";

		List<WebElement> listOfRows = driver.findElements(By.xpath(tableObj));

		int colAction = htmlTableColumnNamePosition("Action", tableHeader);

		int num_of_rows = listOfRows.size();
		System.out.println(colAction);
		System.out.println(" " + num_of_rows);

		for (int i = 1; i <= num_of_rows; i++) {
			int rowInc = i + 1;
			String strTableData = prop.getProperty("V90.V90Packaging_Note.Search.Table") + "/tbody/tr[" + rowInc
					+ "]/td";
			List<WebElement> tableData = driver.findElements(By.xpath(strTableData));

			tableData.get(colAction).click();

			break;

		}

		// Verify the Page is displayed
		// verifyElementExist("Page Search",
		// By.xpath(prop.getProperty("Admin.Scanned_goods.Verify.EditPage")));

	}

	public void EditV90DeliveryNote_Display() throws InterruptedException {

		// retrieve data from MasWeb_Page
		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);

		// Enter the Delivery Note ID value
		if (!tdrow.get("Delivery_Note_Id").equals("")) {
			sendKeys("Delivery Note Id", By.xpath(prop.getProperty("V90.EditV90_DeliveryNote.Delivery_Note_Id.Text")),
					tdrow.get("Delivery_Note_Id"));
		}
		anyClick("Display button", By.xpath(prop.getProperty("V90.EditV90_DeliveryNote.Display.Button")));

		Thread.sleep(2000);

		// Verify the table is displayed
		verifyElementExist("Table Search Data", By.xpath(prop.getProperty("V90.EditV90_DeliveryNote.Search.Table")));

		String tableHeader = prop.getProperty("V90.EditV90_DeliveryNote.Search.Table") + "/thead/tr";
		int colDeliveryNote = htmlTableColumnNamePosition("Delivery Note Id", tableHeader);
		int colAction = htmlTableColumnNamePosition("Action", tableHeader);
		driver.findElement(By.xpath(prop.getProperty("V90.EditV90_DeliveryNote.Search.Table") + "/tbody/tr/td[4]")).findElements(By.tagName("input")).get(0).click();

		// Verify the Page is displayed
		verifyElementExist("Edit page", By.xpath(prop.getProperty("V90.EditV90_DeliveryNote.Edit.Search.Page")));

		// Enter the new Trailer ID value
		if (!tdrow.get("Change_Trailer_Id").equals("")) {
			sendKeys("New Trailer Id", By.xpath(prop.getProperty("V90.EditV90_DeliveryNote.Edit_Page.TrailerId.Text")),
					tdrow.get("Change_Trailer_Id"));
		}
		anyClick("Save button", By.xpath(prop.getProperty("V90.EditV90_DeliveryNote.Edit_Page.Save.Button")));

		// Verify the Message is displayed
		verifyElementExist("Message Verify", By.xpath(prop.getProperty("V90.EditV90_DeliveryNote.Message.Verify")));

		anyClick("Resend button", By.xpath(prop.getProperty("V90.EditV90_DeliveryNote.Resend.Button")));

		isAlertPresent("OK");
		// Verify the Message is displayed
		verifyElementExist("Message Verify", By.xpath(prop.getProperty("V90.EditV90_DeliveryNote.Message.Verify")));

	}
	
	public void Prerequisite_V90() throws InterruptedException{
		
		DB_Connectivity db = new DB_Connectivity();
		
		 //MASWEB connection details
		
		String ClassName= prop.getProperty(Environment+".MASWEB.MIMER.ClassName");
		String ConnectionString = prop.getProperty(Environment+".MASWEB.MIMER.Connection.String");
		String UserName= prop.getProperty(Environment+".MASWEB.MIMER.User.Name");
		String Password=prop.getProperty(Environment+".MASWEB.MIMER.Password");
		
		// MASWEB Selection and Deletion
		
				// selecting the RFID 
				//db.Connect_DB(ClassName, ConnectionString, UserName, Password,V90_MASWEB_SelectRFID.replace("#FLOW#","5423455"));
				
				// deleting the flow in masweb
				db.Update_DB(ClassName, ConnectionString, UserName, Password, V90_MASWEB_DeleteRFID.replace("#FLOW#","3727049"));
				
				// selecting the RFID 
				//db.Connect_DB(ClassName, ConnectionString, UserName, Password,V90_MASWEB_SelectBodyId.replace("#FLOW#","8481027"));
				
				// deleting the flow in masweb
				db.Update_DB(ClassName, ConnectionString, UserName, Password, V90_MASWEB_DeleteBodyId.replace("#FLOW#","8453377"));
			reportStep("Success", "pass", true);
	}

}