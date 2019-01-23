package com.volvo.mfg.agv.pages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.volvo.automation.commonutilities.ExcelUtils;
import com.volvo.mfg.StepDefinition.DriverSuite;

public class Elanders extends DriverSuite {
	LDJIT obj = new LDJIT();
	public HashMap<String, String> tdrow;
	// Excel class object to access the function
	ExcelUtils excelUtils = new ExcelUtils();
	String sheetName = "Elanders";

	public void verifyTrailersInfo() throws InterruptedException {
		// retrieve data from MasWeb_Page
		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);

		obj.navigateLdjit();

		anyClick("Trailers Info Menu", By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Menu")));

		// Verify the Page is displayed
		verifyElementExist("Trailers Info Page", By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Page.Text")));
		anyClick("Display button", By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Display.Button")));
		if (driver.findElements(By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Display.Table") + "/tbody/tr"))
				.size() > 1) {
			reportStep("Trailer Info -Table displayed successfully", "PASS", false);
		}

		selectDropDownValue("Supplier dropdown",
				By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.Supplier.Dropdown")),
				tdrow.get("Supplier"));
		anyClick("Display button",
				By.xpath(prop.getProperty("Masweb_Home.LDJIT.YardZoneMaintenance.CreateParkingPlace.DisplayButton")));
		// fetching the supplier value to verify that the displayed table is for the
		// give criteria
		if (driver.findElements(By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Display.Table") + "/tbody/tr"))
				.size() >= 1) {
			reportStep("Selected supplier: " + tdrow.get("Supplier") + " Table displayed as per the given criteria",
					"pass", true);

		} else {
			reportStep(
					"Selected supplier: " + tdrow.get("Supplier") + " Table is not displayed as per the given criteria",
					"fail", true);
		}
		// clicking clear button
		anyClick("Clear button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Clear.Button")));
		// Clicking the park button
		anyClick("Click Park Button", By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Park.Button")));
		// Verify the Page is displayed
		verifyPageTitle(tdrow.get("Expected_Value"));
		// Clicking the park button
		anyClick("Back Button", By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Park.Back.Button")));
		// Verify the Page is displayed
		verifyElementExist("Trailers Info Page", By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Page.Text")));
		// Clicking the park button
		anyClick("Click Park Button", By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Park.Button")));
		// Verify the Page is displayed
		verifyPageTitle(tdrow.get("Expected_Value"));
		anyClick("Display button", By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Display.Button")));
		// Switching to Alert
		isAlertPresent("OK");
		String strTrailerId = getRandomString(4) + getRandomNumber(99999999);
		System.out.println("strTrailerId :" + strTrailerId);
		sendKeys("Trailer Id", By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.TrailerId.Text")), strTrailerId);
		anyClick("Display button", By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Display.Button")));
		// verify the message
		String Invalid_Trailer = driver
				.findElements(By.xpath(
						prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Park.Button.Form.Display.Message.Verify")))
				.get(0).getText();
		System.out.println("Invalid_Trailer Message:" + Invalid_Trailer);
		if (Invalid_Trailer.contains(tdrow.get("Invalid_Trailer_Error"))) {
			System.out.println(" Invalid Trailer Error Message : " + Invalid_Trailer);

			reportStep("Invalid Trailer Error - Message displayed " + Invalid_Trailer, "pass", true);
		}
		// clicking clear button
		anyClick("Clear button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Clear.Button")));
		String strAsnNum = getRandomString(6);
		System.out.println("strAsnNum :" + strAsnNum);
		sendKeys("ASN Number", By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.AsnNum.Text")), strAsnNum);

		// Switching to Alert
		isAlertPresent("OK");

		sendKeys("ASN Number", By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.AsnNum.Text")),
				String.valueOf(getRandomNumber(999999)));
		anyClick("Display button", By.xpath(prop.getProperty("LDJIT_YardZone_TrailersInfo.Display.Button")));
		// verify the message
		String Invalid_Asn = driver
				.findElements(By.xpath(
						prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Park.Button.Form.Display.Message.Verify")))
				.get(0).getText();
		System.out.println("Invalid_Trailer Message:" + Invalid_Asn);
		if (Invalid_Asn.contains(tdrow.get("Invalid_Asn_Error"))) {
			System.out.println(" Invalid ASN Error Message : " + Invalid_Asn);

			reportStep("Invalid ASN Error - Message displayed " + Invalid_Asn, "pass", true);
		}
		// clicking clear button
		anyClick("Clear button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Clear.Button")));

	}

	public void verifyMoveJisjit() throws InterruptedException {
		// retrieve data from MasWeb_Page
		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		// Navigating to move jisjit Racks tab

		anyClick("LDJIT Tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu")));
		anyClick("Move JisJit Racks", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks")));
		// Verify the Page is displayed
		verifyPageTitle(tdrow.get("Expected_Value"));
		anyClick("Trailer Button", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Trailer.Radio.Button")));
		// Verify the Page is displayed
		verifyElementExist("Move JISJIT Racks-Trailer Page",
				By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.TrailerId.Text")));
		anyClick("Rack Button", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Rack.Radio.Button")));
		// Verify the Page is displayed
		verifyElementExist("Move JISJIT Racks-Rack Page",
				By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Rack.PartfamilySeq.Text")));
		anyClick("Load Empty Trailer Button",
				By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.LoadEmptyTrailer.Radio.Button")));
		// Verify the Page is displayed
		verifyElementExist("Move JISJIT Racks-Load Empty Trailer Page",
				By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.LoadEmptyTrailer.Flow.Text")));
	}

	public void verifyMoveJisjit_Trailer() throws InterruptedException {
		// retrieve data from MasWeb_Page
		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		String Trailer_ErrorMsg;
		// Navigating to move jisjit Racks tab

		anyClick("LDJIT Tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu")));
		anyClick("Move JisJit Racks", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks")));
		// Verify the Page is displayed
		verifyPageTitle(tdrow.get("Expected_Value"));
		anyClick("Move button", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Move.Button")));
		// verify the message
		Trailer_ErrorMsg = driver
				.findElements(By.xpath(
						prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Park.Button.Form.Display.Message.Verify")))
				.get(0).getText();
		System.out.println("Trailer_Error Message:" + Trailer_ErrorMsg);
		if (Trailer_ErrorMsg.contains(tdrow.get("Trailer_Error_Message"))) {
			System.out.println("Trailer Error Message : " + Trailer_ErrorMsg);

			reportStep("Trailer Error - Message displayed " + Trailer_ErrorMsg, "pass", true);
		}
		anyClick("Clear button", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Clear.Button")));
		// selecting a buffer place to move

		selectDropDownByIndex("New position dropdown",
				By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Trailer.NewPosition.Dropdown")),
				tdrow.get("NewPosition"));
		// verify the message
		Trailer_ErrorMsg = driver
				.findElements(By.xpath(
						prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Park.Button.Form.Display.Message.Verify")))
				.get(0).getText();
		System.out.println("Trailer_Error Message:" + Trailer_ErrorMsg);
		if (Trailer_ErrorMsg.contains(tdrow.get("Trailer_Error_Message"))) {
			System.out.println("Trailer Error Message : " + Trailer_ErrorMsg);

			reportStep("Trailer Error - Message displayed " + Trailer_ErrorMsg, "pass", true);
		}
		// entering the Trailer ID

		sendKeys("Trailer Id field", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.TrailerId")),
				getRandomString(4) + String.valueOf(getRandomNumber(9999)));
		anyClick("Move button", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Move.Button")));
		// verify the message
		Trailer_ErrorMsg = driver
				.findElements(By.xpath(
						prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Park.Button.Form.Display.Message.Verify")))
				.get(0).getText();
		System.out.println("Trailer_Error Message:" + Trailer_ErrorMsg);
		if (Trailer_ErrorMsg.contains(tdrow.get("Invalid_Trailer_Error"))) {
			System.out.println("Trailer Error Message : " + Trailer_ErrorMsg);

			reportStep("Trailer Error - Message displayed " + Trailer_ErrorMsg, "pass", true);
		}
		anyClick("Clear button", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Clear.Button")));
		sendKeys("Trailer Id field", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.TrailerId")),tdrow.get("TrailerId"));
		
		// verify the Current Position being auto populated
				String Current_Pos = driver
						.findElements(By.xpath(
								prop.getProperty("Masweb_Home.MoveJisjitRacks.Trailer.CurrentPos.Value")))
						.get(0).getText();
				System.out.println("Current Position Value:" + Current_Pos);
				if (Current_Pos.contains(tdrow.get("Place"))) {
					System.out.println("Current Position Value: " + Current_Pos);

					reportStep("Current Position Value:- Message displayed " + Current_Pos, "pass", true);
				}
				// verify the Supplier being auto populated
				String Supplier = driver
						.findElements(By.xpath(
								prop.getProperty("Masweb_Home.MoveJisjitRacks.Trailer.Supplier.Value")))
						.get(0).getText();
				System.out.println("Supplier Value:" + Supplier);
				if (Supplier.contains(tdrow.get("Supplier"))) {
					System.out.println("Supplier Value: " + Supplier);

					reportStep("Supplier Value:- Message displayed " + Supplier, "pass", true);
				}
				anyClick("Move button", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Move.Button")));
				// selecting a buffer place to move

				selectDropDownByIndex("New position dropdown",
						By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Trailer.NewPosition.Dropdown")),
						tdrow.get("NewPosition"));
				// verify the message
				String Trailer_MovedMsg = driver
						.findElements(By.xpath(
								prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Park.Button.Form.Display.Message.Verify")))
						.get(0).getText();
				System.out.println("Trailer_Moved Message:" + Trailer_MovedMsg);
				if (Trailer_MovedMsg.contains(tdrow.get("Trailer_Moved_Message"))) {
					System.out.println("Trailer Moved Message : " + Trailer_MovedMsg);

					reportStep("Trailer Moved - Message displayed " + Trailer_MovedMsg, "pass", true);
				}
				anyClick("Clear button", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Clear.Button")));
	}
	
	public void verifyMoveJisjit_Rack() throws InterruptedException {
		// retrieve data from MasWeb_Page
		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		String ErrorMsg;
		// Navigating to move jisjit Racks tab

		anyClick("LDJIT Tab", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu")));
		anyClick("Move JisJit Racks", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks")));
		// Verify the Page is displayed
		verifyPageTitle(tdrow.get("Expected_Value"));
		anyClick("Rack Button", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Rack.Radio.Button")));
		// Verify the Page is displayed
		verifyElementExist("Move JISJIT Racks-Rack Page",
				By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Rack.PartfamilySeq.Text")));
		anyClick("Move button", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Move.Button")));
		/*// verifying the error message
					if(verifyElementExist("Error Message for missing RackNr", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.RackNr.ErrorMsg")))==true) {
						ErrorMsg=driver.findElements(By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.RackNr.ErrorMsg"))).get(0).getText();
						reportStep("RackNr Error message display: "+ErrorMsg, "pass", true);
					}
					// verifying the error message
					if(verifyElementExist("Error Message for missing Supplier", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Rack.Supplier.ErrorMsg")))==true) {
						ErrorMsg=driver.findElements(By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Rack.Supplier.ErrorMsg"))).get(0).getText();
						reportStep("Supplier Error message display: "+ErrorMsg, "pass", true);
					} 
					// verifying the error message
					if(verifyElementExist("Error Message for missing First Pre Sequence Number", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Rack.FirstPreSeq.ErrorMsg")))==true) {
						ErrorMsg=driver.findElements(By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Rack.FirstPreSeq.ErrorMsg"))).get(0).getText();
						reportStep("First Pre Sequence Error message display: "+ErrorMsg, "pass", true);
					} 
					// verifying the error message
					if(verifyElementExist("Error Message for missing Last Pre Sequence Number", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Rack.LastPreSeq.ErrorMsg")))==true) {
						ErrorMsg=driver.findElements(By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Rack.LastPreSeq.ErrorMsg"))).get(0).getText();
						reportStep("Last Pre Sequence Error message display: "+ErrorMsg, "pass", true);
					} 
					// verifying the error message
					if(verifyElementExist("Error Message for missing Old Position Details", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Rack.OldPosition.ErrorMsg")))==true) {
						ErrorMsg=driver.findElements(By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Rack.OldPosition.ErrorMsg"))).get(0).getText();
						reportStep("Old Position Details Error message display: "+ErrorMsg, "pass", true);
					} 
					// verifying the error message
					if(verifyElementExist("Error Message for missing New Position Details", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Rack.NewPosition.ErrorMsg")))==true) {
						ErrorMsg=driver.findElements(By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Rack.NewPosition.ErrorMsg"))).get(0).getText();
						reportStep("New Position Details Error message display: "+ErrorMsg, "pass", true);
					} */
		List<WebElement> tblSize = driver.findElements(By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Rack.ErrorMsgs")));
		for(int i=0;i<=tblSize.size();i++) {
			String errorMsgs=tblSize.get(0).getText();
		}
					anyClick("Clear button", By.xpath(prop.getProperty("Masweb_Home.MoveJisjitRacks.Clear.Button")));
	}
}