package com.volvo.mfg.agv.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.volvo.automation.commonutilities.ExcelUtils;
import com.volvo.mfg.StepDefinition.DriverSuite;

public class BlueBox extends DriverSuite {

	String sheetName = "BlueBox";
	public HashMap<String, String> tdrow;
	// Excel class object to access the function
	ExcelUtils excelUtils = new ExcelUtils();

	public void blueboxBest() throws InterruptedException {
		try {
			boolean temp;
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);

			// Enter the input x
			if (!tdrow.get("Input").equals("")) {

				sendKeys("Input", By.xpath(prop.getProperty("BlueBox.Best.input.Text")), tdrow.get("Input"));
			}
			// Enter the flow num
			if (!tdrow.get("FlowNum").equals("")) {

				sendKeys("FlowNum", By.xpath(prop.getProperty("Bluebox.Best.flownumber.Text")), tdrow.get("FlowNum"));
			}
			// Click send
			anyClick("Send", By.xpath(prop.getProperty("Bluebox.Best.Send.Button")));

			// verifying the message
			WebElement a = driver.findElement(By.xpath(prop.getProperty("Bluebox.Best.Send.Message")));
			String status = a.getText();
			System.out.println(" Verification status: " + a.getText());

			if (status.equalsIgnoreCase("message.best.ok")) {
				reportStep("message.best.ok", "pass", true);
			} else {
				reportStep("Flowno must be numeric", "Fail", true);
			}
			String colorobj = tdrow.get("Change_Color");
			System.out.println("actual value:" + colorobj);
			String screencolor = driver.findElement(By.xpath(prop.getProperty("Bluebox.Best.flownogreen.text")))
					.getAttribute("style");
			System.out.println("Expected Screen_Value:" + screencolor);

			// verify the color change
			temp = verifyStringCompare("Flow no color", tdrow.get("Change_Color"), driver
					.findElement(By.xpath(prop.getProperty("Bluebox.Best.flownogreen.text"))).getAttribute("style"));
			if (temp == false) {
				// throw new RuntimeException("Failed color");
				System.out.println("Color change failed to Verify");
			}

			// Enter the AreaCode
			if (!tdrow.get("AreaCode").equals("")) {

				sendKeys("AreaCode", By.xpath(prop.getProperty("BlueBox.Best.AreaCode.Text")), tdrow.get("AreaCode"));
			}

			// Click Search button
			anyClick("Search", By.xpath(prop.getProperty("Bluebox.Best.Search.Button")));

			Thread.sleep(2000);

			// get current date time with Date()
			Date yourDate = new Date();
			// Now format the date
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
			String dd = DATE_FORMAT.format(yourDate);
			System.out.println("Current date is : " + dd);

			// verifying the presence of date
			if (driver.getPageSource().contains(dd)) {

				System.out.println("Order created for Current date");
				reportStep("Order created for Current date", "pass", true);

			}

			else {

				System.out.println("Order not created for today's date");
				reportStep("Order not created for today's date", "fail", true);
			}

			// Click Clear
			anyClick("Clear", By.xpath(prop.getProperty("Bluebox.Best.Clear.Button")));

		}

		catch (Exception e) {
			// e.printStackTrace();
			System.out.println("Error in inputdata");
		}

	}

}
