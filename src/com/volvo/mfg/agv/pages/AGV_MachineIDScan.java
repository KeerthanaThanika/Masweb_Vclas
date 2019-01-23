package com.volvo.mfg.agv.pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import com.volvo.automation.commonutilities.CommonWrapperMethods;
import com.volvo.automation.commonutilities.ExcelUtils;
import com.volvo.mfg.StepDefinition.BaseTest;
import com.volvo.mfg.StepDefinition.DriverSuite;;

public class AGV_MachineIDScan extends DriverSuite {
	
	String sheetName="AGV_MachineIDScan";
	public HashMap<String,String> tdrow;
	
	//Excel class object to access the function
	ExcelUtils excelUtils = new ExcelUtils();
	
	/*public LoginPage(WebDriver driver) {
		this.driver = driver;
	}*/
	
public void MachineID_Scan_Submit(String FID) {
		
		boolean temp;
		//retrieve data from MasWeb_Page
		//Load Test Data File
		tdrow= excelUtils.testCaseRetrieve(refTestDataName,sheetName);
		
		//Send the value
		sendKeys("Machine ID field", By.xpath(prop.getProperty("AGV_MachineID.Scan.Text")), FID);
		
		//Click the Page
		anyClick("Machine ID Submit", By.xpath(prop.getProperty("AGV_MachineID.PushButton.Form")));
		
		//Verify the Page Color is displayed
		temp = verifyStringCompare("Machine scan Page Color",tdrow.get("Change_Color").replace(" ", ""), driver.findElement(By.xpath(prop.getProperty("AGV_MachineID.PageColor.Div"))).getAttribute("style").replace(" ", ""));
		
		if (temp == false)
		{
			throw new RuntimeException("Failed - Machine ID Submission");
		}
		
		//Clearing the memory
		tdrow.clear();
	}

	
	
	 
	 
	
}
