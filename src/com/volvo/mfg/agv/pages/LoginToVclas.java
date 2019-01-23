package com.volvo.mfg.agv.pages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.volvo.automation.commonutilities.CommonWrapperMethods;
import com.volvo.automation.commonutilities.ExcelUtils;

public class LoginToVclas extends CommonWrapperMethods
{
	Set<String> allWindows;
	Iterator<String> it;
	public HashMap<String, String> tdrow;
	ExcelUtils excelUtils = new ExcelUtils();
	public void LogintoVclas(String testname) throws InterruptedException {
		// call excel util and get user creds
		// Load Test Data File

		

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

			String usrName = prop.getProperty(("user") );
			String pwd = prop.getProperty(("password"));

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

}
