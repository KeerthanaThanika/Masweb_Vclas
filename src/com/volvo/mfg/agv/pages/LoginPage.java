//**************************************************************************************************
//@File Name: LoginPage
//@Description: To login to the application
//@Input Parameters: UserName,Password
//@Output Parameters: Nil
//@Created by: Senthil Kumar Sivanandam
//@Date Created: 18-Dec-2017
//@Last Modified: 
//**************************************************************************************************
package com.volvo.mfg.agv.pages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.volvo.automation.commonutilities.ExcelUtils;
import com.volvo.mfg.StepDefinition.DriverSuite;;

public class LoginPage extends DriverSuite {
	Set<String> allWindows;
	Iterator<String> it;
	String sheetName = "Login_Page";
	public HashMap<String, String> tdrow;
	ExcelUtils excelUtils = new ExcelUtils();

	public void LogintoPage(String testname) throws InterruptedException {
		// call excel util and get user credentials
		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(testname, sheetName);
		// launchUrl(URL, "");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if (driver.findElements(By.xpath(prop.getProperty("Masweb_Home.Login.UserName").replace("&Value",
				prop.getProperty(tdrow.get("User_Details") + ".User.Name")))).size() <= 0) {
			driver.manage().timeouts().implicitlyWait(Default_Wait_4_Page, TimeUnit.SECONDS);

			// Launching the Application URL

			launchUrl(URL, "", 1);

			if (Environment.equalsIgnoreCase("Test") && Browser.equalsIgnoreCase("chrome")) {
				/*if(driver.findElements(By.xpath(prop.getProperty("Loginpage.User.Masweb.Link"))).size()> 0 ){
					anyClick("masweb link", By.xpath(prop.getProperty("Loginpage.User.Masweb.Link")));
				}
				*/

			}
			// for IE
			else if (Browser.equalsIgnoreCase("ie")) {
				anyClick("masweb link", By.xpath(prop.getProperty("Loginpage.User.Masweb.Link")));

			}

			String usrName = prop.getProperty(tdrow.get("User_Details") + ".User.Name");
			String pwd = prop.getProperty(tdrow.get("User_Details") + ".User.Password");

			if (verifyAuthenticationPopup(usrName, decryptPassword(pwd)) == true) {
				// Entering the credentials and click submit
				sendKeys("UserName", By.xpath(prop.getProperty("LoginPage.User.Name")), usrName);
				sendKeysPassword("Password", By.xpath(prop.getProperty("LoginPage.User.Password")), pwd);
				anyClick("Submit", By.xpath(prop.getProperty("LoginPage.Login.Button")));
				reportStep("Verify the functionality of logging into Masweb application", "pass", false);
			}

			// Waiting to page load
			Thread.sleep(5000);

			// Verify Page displayed
			verifyPageTitle(tdrow.get("Expected_Value"));
		} else {
			reportStep(prop.getProperty(tdrow.get("User_Details") + ".User.Name") + " User already logged in", "Info",
					false);
		}

		// Clearing the Memory
		tdrow.clear();

	}

	public void LogintoVclas(String testname) throws InterruptedException {
		// call excel util and get user creds
		// Load Test Data File

		tdrow = excelUtils.testCaseRetrieve(testname, sheetName);

		// Launching the Application URL
		String urlVclas = prop.getProperty(Environment + ".URL.Vclas");

		Set<String> allWindows = driver.getWindowHandles();

		Iterator<String> it = allWindows.iterator();
		it.next();
		launchUrl(urlVclas, "", 2);
		it = allWindows.iterator();
		windowsTemp1 = it.next();
		System.out.println("windowsTemp1: " + windowsTemp1);

		// launchUrl(urlVclas, "");

		driver.manage().timeouts().implicitlyWait(Default_Wait_4_Page, TimeUnit.SECONDS);
		if (driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Home.Label"))).size() <= 0) {
			driver.manage().timeouts().implicitlyWait(Default_Wait_4_Page, TimeUnit.SECONDS);

			String usrName = prop.getProperty(tdrow.get("User_Details") + ".User.Name");
			String pwd = prop.getProperty(tdrow.get("User_Details") + ".User.Password");

			// Entering the credentials and click submit
			sendKeys("UserName", By.xpath(prop.getProperty("LoginPage.User.Name")), usrName);
			sendKeysPassword("Password", By.xpath(prop.getProperty("LoginPage.User.Password")), pwd);
			anyClick("Submit", By.xpath(prop.getProperty("LoginPage.Login.Button")));

			Thread.sleep(2000);

			// Verify Page displayed
			verifyElementExist("Vclas page displayed", By.xpath(prop.getProperty("Vclas_Home.Home.Label")));
			reportStep("Verify the functionality of logging into VCLAS application", "pass", false);

		} else {
			reportStep("User already logged in", "Info", false);
		}
		// new
		WebElement languageButton = driver.findElement(By.xpath(prop.getProperty("Vclas_Home.Change.Language.Click")));
		if (displayElement(driver, languageButton) == true) {

			anyClick("Clicking the Language Button", By.xpath(prop.getProperty("Vclas_Home.Change.Language.Click")));

			anyClick("Selecting the DropDown", By.xpath(prop.getProperty("Vclas_Home.Language.Click")));

			selectDropDownByIndex("Language Select", By.xpath(prop.getProperty("Vclas_Home.Select.Language.DropDown")),
					"English");
		}
		// Clearing the Memory
		tdrow.clear();

	}

	public void Agv_LogintoMasweb(String testname) throws InterruptedException {

		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(testname, sheetName);

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if (driver.findElements(By.xpath(prop.getProperty("Masweb_Home.Login.UserName").replace("&Value",
				prop.getProperty(tdrow.get("User_Details") + ".User.Name")))).size() <= 0) {
			driver.manage().timeouts().implicitlyWait(Default_Wait_4_Page, TimeUnit.SECONDS);

			// Launching the Application URL
			Agv_launchUrl(URL, "");

			if (Environment.equalsIgnoreCase("Test") && Browser.equalsIgnoreCase("IE")) {
				anyClick("Continue to this link", By.xpath(prop.getProperty("Loginpage.User.Recommended.Link")));
			}

			String usrName = prop.getProperty(tdrow.get("User_Details") + ".User.Name");
			String pwd = prop.getProperty(tdrow.get("User_Details") + ".User.Password");

			if (verifyAuthenticationPopup(usrName, decryptPassword(pwd)) == true) {
				// Entering the credentials and click submit
				sendKeys("UserName", By.xpath(prop.getProperty("LoginPage.User.Name")), usrName);
				sendKeysPassword("Password", By.xpath(prop.getProperty("LoginPage.User.Password")), pwd);
				anyClick("Submit", By.xpath(prop.getProperty("LoginPage.Login.Button")));
				reportStep("Verify the functionality of logging into Masweb application", "pass", false);
				logger.info("user logged in successfully");
			}

			// Waiting to page load
			Thread.sleep(5000);

			// Verify Page displayed
			verifyPageTitle(tdrow.get("Expected_Value"));
		} else {
			reportStep(prop.getProperty(tdrow.get("User_Details") + ".User.Name") + " User already logged in", "Info",
					false);
		}

		// Clearing the Memory
		tdrow.clear();

	}

	public void Agv_LogintoVclas(String testname) throws InterruptedException {

		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(testname, sheetName);

		// Launching the Application URL
		urlVclas = prop.getProperty(Environment + ".URL.Vclas");
		Agv_launchUrl(urlVclas, "");

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if (driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Home.Label"))).size() <= 0) {
			driver.manage().timeouts().implicitlyWait(Default_Wait_4_Page, TimeUnit.SECONDS);

			String usrName = prop.getProperty(tdrow.get("User_Details") + ".User.Name");
			String pwd = prop.getProperty(tdrow.get("User_Details") + ".User.Password");

			// Entering the credentials and click submit
			sendKeys("UserName", By.xpath(prop.getProperty("LoginPage.User.Name")), usrName);
			sendKeysPassword("Password", By.xpath(prop.getProperty("LoginPage.User.Password")), pwd);
			anyClick("Submit", By.xpath(prop.getProperty("LoginPage.Login.Button")));

			Thread.sleep(2000);

			// Verify Page displayed
			verifyElementExist("Vclas page displayed", By.xpath(prop.getProperty("Vclas_Home.Home.Label")));
			reportStep("#B Verified the functionality of logging into VCLAS application TestCase ID:2904 #C", "pass", false);
		} else {
			reportStep("User already logged in", "Info", false);
		}

		// WebElement
		// languageButton=driver.findElement(By.xpath(prop.getProperty("Vclas_Home.Change.Language.Click")));
		if (driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Change.Language.Click"))).size() > 0) {

			driver.manage().timeouts().implicitlyWait(Default_Wait_4_Page, TimeUnit.SECONDS);
			anyClick("Clicking the Language Button", By.xpath(prop.getProperty("Vclas_Home.Change.Language.Click")));

			anyClick("Selecting the DropDown", By.xpath(prop.getProperty("Vclas_Home.Language.Click")));

			selectDropDownByIndex("Language Select", By.xpath(prop.getProperty("Vclas_Home.Select.Language.DropDown")),
					"English");
			reportStep("#B Verified the functionality of Language settings for VCLAS application by using Installningar tab TestCase ID:2905 #C", "pass", false);

		} else {
			reportStep(" language was already changed", "Info", false);
		}

		// Clearing the Memory
		tdrow.clear();

	}

}
