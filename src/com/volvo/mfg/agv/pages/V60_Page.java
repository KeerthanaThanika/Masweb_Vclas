package com.volvo.mfg.agv.pages;

import java.util.HashMap;

import org.openqa.selenium.By;

import com.volvo.automation.commonutilities.ExcelUtils;
import com.volvo.mfg.StepDefinition.DriverSuite;

public class V60_Page extends DriverSuite {
	
public HashMap<String, String> tdrow;
		
		// Excel class object to access the function
			ExcelUtils excelUtils = new ExcelUtils();

			// retrieve data from MasWeb_Page
			// Load Test Data File
	public void V60_NormalStation_Display() throws InterruptedException {
				String sheetName = "V60_NormalStation";
				// retrieve data from MasWeb_Page
				// Load Test Data File
	   tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
	 //Select Text box
	   if (!tdrow.get("RFID").equals(""))
		{
			sendKeys("RFID", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.Text")), tdrow.get("RFID"));
		}
	   if (!tdrow.get("Station").equals(""))
		{
			sendKeys("Station", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.Station.Dropdown")), tdrow.get("Station"));
		}
	   if (!tdrow.get("Number_of_Labels").equals(""))
		{
			sendKeys("Number_of_Labels", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.NumberofLabels.Dropdown")), tdrow.get("Number_of_Labels"));
		}
		
	   anyClick("Request Body Information",By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.RequestBodyInformation.Button")));
       
	   anyClick("Display",By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.Display")));
       
       //Verify the table is displayed 
      	verifyElementExist("Search table Data", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.Table")+"/tbody/tr[1]"));
	 	
      	anyClick("Clear",By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.Clear")));	
      	
	}
	
	public void V60_RepairStation_Display() throws InterruptedException {
		String sheetName = "V60_RepairStation";
		// retrieve data from MasWeb_Page
		// Load Test Data File
tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
//Select Text box
if (!tdrow.get("RFID").equals(""))
{
	sendKeys("RFID", By.xpath(prop.getProperty("V60_ScanRFID_RepairStation.Text")), tdrow.get("RFID"));
}
if (!tdrow.get("Station").equals(""))
{
	sendKeys("Station", By.xpath(prop.getProperty("V60_ScanRFID_RepairStation.Station.Dropdown")), tdrow.get("Station"));
}
if (!tdrow.get("Number_of_Labels").equals(""))
{
	sendKeys("Number_of_Labels", By.xpath(prop.getProperty("V60_ScanRFID_RepairStation.NumberofLabels.Dropdown")), tdrow.get("Number_of_Labels"));
}

anyClick("Request Body Information",By.xpath(prop.getProperty("V60_ScanRFID_RepairStation.RequestBodyInformation.Button")));

anyClick("Display",By.xpath(prop.getProperty("Admin_ShowClose.Display.Button")));

//Verify the table is displayed 
	verifyElementExist("Search table Data", By.xpath(prop.getProperty("V60_ScanRFID_RepairStation.Table")+"/tbody/tr[1]"));
	
	anyClick("Clear",By.xpath(prop.getProperty("V60_ScanRFID_RepairStation.Clear")));	
	
}
}
	
	

