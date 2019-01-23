package com.volvo.mfg.agv.pages;
/* updated 2-Mar-2018 test page update 14:00*/
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;

import com.volvo.automation.commonutilities.CommonWrapperMethods;
import com.volvo.automation.commonutilities.ExcelUtils;
import com.volvo.mfg.StepDefinition.BaseTest;
import com.volvo.mfg.StepDefinition.DriverSuite;;

public class Test extends DriverSuite {
	
	String sheetName="AGV_RackChanger";
	public HashMap<String,String> tdrow;
	
	//Excel class object to access the function
	ExcelUtils excelUtils = new ExcelUtils();
	
	/*public LoginPage(WebDriver driver) {
		this.driver = driver;
	}*/
	
	public void proxySettings() {
		
		Proxy pxy = new Proxy();
		pxy.setHttpProxy("")
		.setFtpProxy("")
		.setSslProxy("");
		DesiredCapabilities dsg = new DesiredCapabilities();
		dsg.setCapability(CapabilityType.PROXY, pxy);
		
		
		//Driver wait
		WebDriverWait wait = new WebDriverWait(driver, 10);
		//WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(by));
		
		
	}
	
}
