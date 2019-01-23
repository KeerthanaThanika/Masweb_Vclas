package com.volvo.mfg.agv.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.volvo.automation.commonutilities.ExcelUtils;
import com.volvo.mfg.StepDefinition.DriverSuite;

public class Production_ControlCodes extends DriverSuite {

	String sheetName="Production";
	public HashMap<String,String> tdrow;
	public String strPartnumber= "";
	public int randomInt;
	public String Partnumber,PartNo, Control_code,Location_code,Location;
	
	//Excel class object to access the function
	ExcelUtils excelUtils = new ExcelUtils();
	
	public void Production_ControlCodes_Display() throws InterruptedException {
		String sheetName="Production_ControlCodes";
		//retrieve data from MasWeb_Page
		//Load Test Data File
		tdrow=excelUtils.testCaseRetrieve(refTestDataName,sheetName);
		//Enter the Partnumber value
		
		if (!tdrow.get("Partnumber").equals(""))
		{
			sendKeys("Partnumber", By.xpath(prop.getProperty("Production_Control_codes.Partnumber")), tdrow.get("Partnumber"));
		}
		
		anyClick("Display", By.xpath(prop.getProperty("Production_Control_codes.Display_Button")));
		Thread.sleep(2000);
		
		//Verify the table is displayed 
		verifyElementExist("Table", By.xpath(prop.getProperty("Production_Control_codes.Display_Table")+"/tbody/tr[1]"));
	
		//Clearing the Memory
		tdrow.clear();
	}

	public void Production_Controlcodes_Remove() throws InterruptedException {
		String sheetName="Production_ControlCodes";
		
		// getting table header row
		String tableHeaderRow = prop.getProperty("Production_Control_codes.Display_Table") +"/thead/tr";
		String tableObject = prop.getProperty("Production_Control_codes.Display_Table") +"/tbody/tr";
        List<WebElement> rows=driver.findElements(By.xpath(tableObject));
        
        int colPARTNUMBER = htmlTableColumnNamePosition("Partnumber",tableHeaderRow);
        int colCONTROL_CODE = htmlTableColumnNamePosition("Control code",tableHeaderRow);
        int colEDIT=htmlTableColumnNamePosition("EDIT", tableHeaderRow);
        int row_count = rows.size();
        for(int i=0;i<=row_count;i++)
           	{
           		int rowInc = i + 1;
           		String strTableData = prop.getProperty("Production_Control_codes.Display_Table") +"/tbody/tr["+rowInc+"]/td";
           		List<WebElement> tableData = driver.findElements(By.xpath(strTableData));
           		
           		//retriving partnumber and controlcode
           		Partnumber=tableData.get(colPARTNUMBER).getText();
           		Control_code=tableData.get(colCONTROL_CODE).getText();
           		
           		//clicking the edit 
           		tableData.get(colEDIT).click();
           		verifyElementExist("edit page ", By.xpath(prop.getProperty("Production_Control_codes.Create.Save_Button")));
           		reportStep("Partnumber to be removed: "+Partnumber, "Pass", true);
           		System.out.println("partnumber:"+Partnumber);
           		System.out.println("control code:"+Control_code);
           		break;
           	}

		anyClick("Remove", By.xpath(prop.getProperty("Production_Control_codes.Create.Remove_Button")));
		// handling alert message
		isAlertPresent("ok");

		// getting alert message

		String alertMessage = prop.getProperty("Production_Control_codes.Message_Table") + "/tbody/tr[1]";

		List<WebElement> ErrorMsg = driver.findElements(By.xpath(alertMessage));

		// getting the error message displayed
		String status = ErrorMsg.get(0).getText();

		System.out.println(" status: " + status);
		// verifying the alert message
		if (status.equalsIgnoreCase("Deletions made")) {
			reportStep("Partnumber removed  successfully", "pass", true);
		} else {
			reportStep("partnumber removing failed", "fail", true);
		}
        
	}
	public void Production_ControlCodes_Create() throws InterruptedException {
		
		String sheetName = "Production_ControlCodes";
		// clicking create button to create new record
		anyClick("Create", By.xpath(prop.getProperty("Production_Control_codes.Create_Button")));

		// verifying the create page is displayed or not
		verifyElementExist("Create page", By.xpath(prop.getProperty("Production_Control_codes.Create.Partnumber")));

		// Entering the Partnumber value
		sendKeys("Partnumber", By.xpath(prop.getProperty("Production_Control_codes.Create.Partnumber")), Partnumber);
		sendKeys("Control code", By.xpath(prop.getProperty("Production_Control_codes.Create.Controlcode")),
				Control_code);

		anyClick("Save", By.xpath(prop.getProperty("Production_Control_codes.Create.Save_Button")));

		String alertMessage = prop.getProperty("Production_Control_codes.Message_Table") + "/tbody";

		List<WebElement> ErrorMsg = driver.findElements(By.xpath(alertMessage));
		// getting the error message displayed
		String status = ErrorMsg.get(0).getText();

		// verifying the alert message
		if (status.equalsIgnoreCase("Control Code for partno created. Press return to get back.")) {

			reportStep("New partnumber is created", "pass", true);

		} else {
			reportStep("partnumber already exist", "fail", true);
		}
		anyClick("Return", By.xpath(prop.getProperty("Production_Control_codes.Create.Return_Button")));

	}
	
	public void Production_Plockan_Display_Edit() throws InterruptedException {
		String sheetName="Production_Plockan";
		// retrieve data from MasWeb_Page
		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		// Enter the Partnumber value
		if (!tdrow.get("PartNo").equals("")) {
			sendKeys("Partnumber", By.xpath(prop.getProperty("Production_Plockan.PartNo")),
					tdrow.get("PartNo"));
		}

		anyClick("Display", By.xpath(prop.getProperty("production_Plockan.Display_Button")));
		Thread.sleep(2000);

		// Verify the table is displayed
		verifyElementExist("Table",
				By.xpath(prop.getProperty("Production_Plockan.Display_Table") + "/tbody/tr[1]"));

		// Clearing the Memory
		tdrow.clear();
	
		// getting table header row
		String tableHeaderRow = prop.getProperty("Production_Plockan.Display_Table") + "/thead/tr";
		String tableObject = prop.getProperty("Production_Plockan.Display_Table") + "/tbody/tr";
		List<WebElement> rows = driver.findElements(By.xpath(tableObject));

		int colPARTNO = htmlTableColumnNamePosition("PartNo", tableHeaderRow);
		//int colCONTROL_CODE = htmlTableColumnNamePosition("Control code", tableHeaderRow);
		int colEDIT = htmlTableColumnNamePosition("EDIT", tableHeaderRow);
		int row_count = rows.size();
		for (int i = 0; i <= row_count; i++) {
			int rowInc = i + 1;
			String strTableData = prop.getProperty("Production_Plockan.Display_Table") + "/tbody/tr[" + rowInc+ "]/td";
			List<WebElement> tableData = driver.findElements(By.xpath(strTableData));

			// Retrieving partNo 
			PartNo = tableData.get(colPARTNO).getText();
			// clicking the edit
			tableData.get(colEDIT).findElements(By.tagName("input")).get(0).click();
			verifyElementExist("edit page ", By.xpath(prop.getProperty("Production_Plockan.Create.Save_Button")));
			reportStep("Partnumber to be removed: " + PartNo, "Pass", true);
			System.out.println("partnumber:" + PartNo);
			break;
		}
	}

	public void Production_Plockan_Change_NewPlockan() {
		String sheetName="Production_Plockan";
		// retrieve data from MasWeb_Page
				// Load Test Data File
				tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
	
		// Enter the Partnumber value
		if (!tdrow.get("New Plockan").equals("")) {
			sendKeys("New Plockan", By.xpath(prop.getProperty("Production_Plockan.Edit.NewPlockan")),
					tdrow.get("New Plockan"));
		}
		anyClick("Save", By.xpath(prop.getProperty("Production_Plockan.Edit.Save_Button")));

		// getting alert message

		String alertMessage = prop.getProperty("Production_Plockan.Message_Table") + "/tbody/tr[1]";

		List<WebElement> ErrorMsg = driver.findElements(By.xpath(alertMessage));

		// getting the error message displayed
		String status = ErrorMsg.get(0).getText();

		System.out.println(" status: " + status);
		// verifying the alert message
		if (status.equalsIgnoreCase("The Part Number has been updated in Mas!")) {
			reportStep("New plockan was updated successfully", "pass", true);
		} else {
			reportStep("New plockan updation failed", "fail", true);
		}

	}
	public void Production_Plockan_Remove() {
		String sheetName="Production_Plockan";
		anyClick("Remove", By.xpath(prop.getProperty("Production_Plockan.Create.Remove_Button")));

		// getting alert message

		String alertMessage = prop.getProperty("Production_Plockan.Message_Table") + "/tbody/tr[1]";

		List<WebElement> ErrorMsg = driver.findElements(By.xpath(alertMessage));

		// getting the error message displayed
		String status = ErrorMsg.get(0).getText();

		System.out.println(" status: " + status);
		// verifying the alert message
		if (status.equalsIgnoreCase("The Part Number was removed as Picking Part Number in Mas!")) {
			reportStep("PartNo removed  successfully", "pass", true);
		} else {
			reportStep("partNo removing failed", "fail", true);
		}

	}
	public void Production_Plockan_Create() {
		String sheetName="Production_Plockan";
		    
		anyClick("Create", By.xpath(prop.getProperty("Production_Plockan.Create_Button")));
		// verify the create page is displayed 
		verifyElementExist("Create page ", By.xpath(prop.getProperty("Production_Plockan.Create.Save_Button")));
		 // enter the PartNo
		sendKeys("Partnumber", By.xpath(prop.getProperty("Production_Plockan.Create.PartNo")),PartNo);
		anyClick("Save", By.xpath(prop.getProperty("Production_Plockan.Create.Save_Button")));
		

		// getting alert message

		String alertMessage = prop.getProperty("Production_Plockan.Message_Table") + "/tbody/tr[1]";

		List<WebElement> ErrorMsg = driver.findElements(By.xpath(alertMessage));

		// getting the error message displayed
		String status = ErrorMsg.get(0).getText();

		System.out.println(" status: " + status);
		
		// verifying the alert message
		if (status.equalsIgnoreCase("The Part Number is now a Pick Part Number. Press return to get back!")) {
			reportStep("PartNo created  successfully", "pass", true);
		} else {
			reportStep("partNo craetion failed", "fail", true);
		}
		anyClick("Return", By.xpath(prop.getProperty("Production_Plockan.Create.Return_Button")));
	}

	public void Production_Consumption_Display() throws InterruptedException {
		String sheetName="Production_Consumption";
		// retrieve data from MasWeb_Page
		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		// Enter the Partnumber value
		if (!tdrow.get("Partnumber").equals("")) {
			sendKeys("Partnumber", By.xpath(prop.getProperty("Production_Consumption.Partnumber")),
					tdrow.get("Partnumber"));
		}

		anyClick("All today radio button", By.xpath(prop.getProperty("Production_Consumption.AllToday_Radio")));
		anyClick("Display", By.xpath(prop.getProperty("Production_Consumption.Display_Button")));
		Thread.sleep(2000);
	
		
		// Verify the table is displayed
		verifyElementExist("Table",By.xpath(prop.getProperty("Production_Consumption.Display_Table") + "/tbody/tr[1]"));
		//anyClick("Export to excel", By.xpath(prop.getProperty("Production_Consumption.Export_Button")));
		//reportStep("Data exported to excel successfully", "pass", true);

		// Clearing the Memory
		tdrow.clear();
	}
	
	public void Production_LocationCode_Display() throws InterruptedException {
		String sheetName="Production_LocationCodes";
		// retrieve data from MasWeb_Page
		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		// Enter the Partnumber value
		if (!tdrow.get("Location code").equals("")) {
			sendKeys("Location code", By.xpath(prop.getProperty("Production_Location_Codes.LocationCode")),
					tdrow.get("Location code"));
		}

		anyClick("Display", By.xpath(prop.getProperty("Production_Location_Codes.Display_Button")));
		Thread.sleep(2000);
		
		// Verify the table is displayed
		verifyElementExist("Table",By.xpath(prop.getProperty("Production_Location_Codes.Display_Table") + "/tbody/tr[1]"));
		
		
		// Clearing the Memory
		tdrow.clear();
	}
	
	public void Production_LocationCodes_Remove() throws InterruptedException {
		String sheetName="Production_LocationCodes";
		
		// getting table header row
		String tableHeaderRow = prop.getProperty("Production_Location_Codes.Display_Table") +"/thead/tr";
		String tableObject = prop.getProperty("Production_Location_Codes.Display_Table") +"/tbody/tr";
        List<WebElement> rows=driver.findElements(By.xpath(tableObject));
        
        int colLOCATIONCODE = htmlTableColumnNamePosition("Location code",tableHeaderRow);
        int colLOCATION = htmlTableColumnNamePosition("Location",tableHeaderRow);
        int colEDIT=htmlTableColumnNamePosition("EDIT", tableHeaderRow);
        int row_count = rows.size();
        for(int i=0;i<=row_count;i++)
           	{
           		int rowInc = i + 1;
           		String strTableData = prop.getProperty("Production_Location_Codes.Display_Table") +"/tbody/tr["+rowInc+"]/td";
           		List<WebElement> tableData = driver.findElements(By.xpath(strTableData));
           		
           		//retriving partnumber and controlcode
           		Location_code=tableData.get(colLOCATIONCODE).getText();
           		Location=tableData.get(colLOCATION).getText();
           		
           		//clicking the edit 
           		tableData.get(colEDIT).click();
           		verifyElementExist("edit page ", By.xpath(prop.getProperty("Production_Location_Codes.Edit.Save_Button")));
           		reportStep("Location code to be removed: "+Location_code, "Pass", true);
           		System.out.println("Location code:"+Location_code);
           		System.out.println("Location:"+Location);
           		break;
           	}

		anyClick("Remove", By.xpath(prop.getProperty("Production_Location_Codes.Edit.Remove_Button")));
		// handling alert message
		isAlertPresent("ok");

		// getting alert message

		String alertMessage = prop.getProperty("Production_Location_Codes.Edit.ErrorMessage_Table") + "/tbody/tr[1]";

		List<WebElement> ErrorMsg = driver.findElements(By.xpath(alertMessage));

		// getting the error message displayed
		String status = ErrorMsg.get(0).getText();

		System.out.println(" status: " + status);
		// verifying the alert message
		if (status.equalsIgnoreCase("Deletions made")) {
			reportStep("Location Code removed  successfully", "pass", true);
		} else {
			reportStep("Location Code removing failed", "fail", true);
		}
        
	}

	

	public void Production_LocationCode_Create() throws InterruptedException {
		String sheetName="Production_LocationCodes";
		 //String LocationCode="TEST";
		// String Location="AGV";
		// retrieve data from MasWeb_Page
		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		anyClick("Create", By.xpath(prop.getProperty("Production_Location_Codes.Create_Button")));
		
		//LocationCode=LocationCode+getRandomNumber(99);
		//Location=Location+getRandomNumber(99);
		
		//Enter the location code
		sendKeys("Location code", By.xpath(prop.getProperty("Production_Location_Codes.Create.Location_Code")),Location_code);
		// Enter the location 
		sendKeys("Location ", By.xpath(prop.getProperty("Production_Location_Codes.Craete.Location")),Location);
			
		anyClick("Save", By.xpath(prop.getProperty("Production_Location_Codes.Create.Save_Button")));
		

		// getting alert message

		String alertMessage = prop.getProperty("Production_Location_Codes.Create.ErrorMessage_Table") + "/tbody/tr[1]";

		List<WebElement> ErrorMsg = driver.findElements(By.xpath(alertMessage));

		// getting the error message displayed
		String status = ErrorMsg.get(0).getText();

		System.out.println(" status: " + status);
		
		if (status.equalsIgnoreCase("Location code for location created. Press return to get back!")) {
			reportStep("Location Code created  successfully", "pass", true);
		} else {
			reportStep("Location code craetion failed", "fail", true);
		}
		anyClick("Return", By.xpath(prop.getProperty("Production_Location_Codes.Create.Return_Button")));
		
		// Clearing the Memory
		tdrow.clear();
	}
	


}
