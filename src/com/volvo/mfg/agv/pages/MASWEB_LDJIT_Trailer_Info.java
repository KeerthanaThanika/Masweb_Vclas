package com.volvo.mfg.agv.pages;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.volvo.automation.commonutilities.CommonWrapperMethods;
import com.volvo.automation.commonutilities.ExcelUtils;
import com.volvo.mfg.StepDefinition.DriverSuite;

public class MASWEB_LDJIT_Trailer_Info extends DriverSuite {
	
	public HashMap<String, String> tdrow;
	ExcelUtils excelUtils = new ExcelUtils();
	
	String strZonename="";
	String strTrailerType="";
	String strParkingPlace="";
	String strSupplier="";
	String strFlow="";
	String strParkZone="";
	
	public void Trailer_Info_Display() throws InterruptedException {
		
		String sheetName = "MASWEB_LDJIT_Trailer_Info";
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, "MASWEB_LDJIT_Trailer_Info");
		String strTrailerId= tdrow.get("Trailer_Id");
		String strCheckcode=tdrow.get("Checkcode");
		
		
		//clicking on LDJIT menu
		anyClick("LDJIT menu click", By.xpath(prop.getProperty("Masweb_Home.LDJIT.Menu")));
		
		//clicking on yard zone maintenance tab
		anyClick("Yard Zone maintenance click", By.xpath(prop.getProperty("MASWEB_LDJIT.Yard.Zone.Maintenance")));
		
		//clicking on Trailer Info menu
		anyClick("Trailer Info Menu click", By.xpath(prop.getProperty("MASWEB_LDJIT.Yard.Trailer.Info")));
		
		
		//verify page Trailer Info display
		if(verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Trailer.Info.Page.Verify")))==true) {
			
			//clicking on Display button
			anyClick("Display Button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Display.Button")));
			Thread.sleep(2000);
			reportStep("#B Verified display functionality in Trailers Info screen TestCase id:1066 #C", "pass", false);
			
		}else {
			reportStep("Page not displayed", "fail", false);
		}
		
		
		//entering Trailer ID
		sendKeys("TrailerID Textbox", By.xpath(prop.getProperty("MASWEB_LDJIT.Trailer.Id.Textbox")), strTrailerId.split(",")[0]);
		
		//entering Zone name
		strZonename = tdrow.get("Zone_Name");
		sendKeys("Zone Name Textbox", By.xpath(prop.getProperty("MASWEB_LDJIT.Zonename.Textbox")), strZonename);
		
		//selecting Trailer Type drop-down
		strTrailerType = tdrow.get("Trailer_Type");
		System.out.println("strTrailerType: "+strTrailerType);
		new Select (driver.findElement(By.xpath(prop.getProperty("MASWEB_LDJIT.Trailer.Type.Dropdown")))).selectByVisibleText(strTrailerType);
		reportStep("Trailer drop-down:" +strTrailerType, "pass", false);
		
		//entering Parking Place
		strParkingPlace = tdrow.get("Parking_Place");
		sendKeys("Parking Place Textbox", By.xpath(prop.getProperty("MASWEB_LDJIT.Parking.Place.Textbox")), strParkingPlace);
		
		//selecting Supplier drop-down
		strSupplier = tdrow.get("Supplier");
		System.out.println("strSupplier: "+strSupplier);
		new Select (driver.findElement(By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Supplier.Dropdown")))).selectByVisibleText(strSupplier);
		reportStep("Supplier drop-down:" +strSupplier, "pass", false);
		
		//entering Flow 
		strFlow = tdrow.get("Flow");
		sendKeys("Flow Textbox", By.cssSelector(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Flow.Dropdown")), strFlow);
		reportStep("#B Verified display functionality in Trailers Info screen based on fields selected TestCase id:1067 #C", "pass", false);
		
		//clicking clear button
		anyClick("Clear button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Clear.Button")));
		reportStep("#B Verified clear functionality in Trailers Info screen TestCase id:1068 #C", "pass", false);
		
		//clicking park button
		anyClick("Park button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Park.button")));
		
		//verify park page display
		if(verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.Verify")))==true){
			
			//clicking on Yard parking radio button
			anyClick("Yard Parking radio button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Yard.Trailer.Info.Yard.Radio.Button")));
			
			//clicking display button
			anyClick("Display Button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Display.Button")));
			
			//verify the alert message
			String trailerAlertMessage=driver.switchTo().alert().getText();
			System.out.println("AlertMessage:" +trailerAlertMessage);
			reportStep("#B Trailer Id alert window: #C" +trailerAlertMessage, "pass", false);
			
			//clicking on OK in alert window
			driver.switchTo().alert().accept();
			reportStep("#B Verified Trailer Id is required error message in standard parking screen TestCase id:1069 #C", "pass", false);
			
			//Entering Trailer Id
			sendKeys("TrailerID Textbox", By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.TrailerID.Textbox")), strTrailerId.split(",")[1]);
			
			//clicking display button
			anyClick("Display Button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Display.Button")));
		}else {
			reportStep("Park page is not displayed", "fail", false);
		}
		
		//clicking on edit button
		anyClick("Edit button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Edit.Button")));
				
		//verify the edit page display
		if(verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Edit.Button.Page.Verify")))==true){
					
		  //Entering values in trailer id text box
		 String invalidTrailerId=tdrow.get("Invalid_Trailer_Id");
		 sendKeys("Trailer ID", By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Edit.Button.Page.TrailerId.Textbox")), invalidTrailerId);
		 System.out.println("Invalid TrailerId:" +invalidTrailerId);
					
		 //clicking on save button
		 anyClick("Save button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Edit.Button.Page.Save.Button")));
					
		 //verify the message
		 String Message=driver.findElements(By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Park.Button.Form.Display.Message.Verify"))).get(0).getText();
		 System.out.println("Message:" +Message);
		 reportStep("#B Message: #C" +Message, "pass", false);
		 reportStep("#B Verified edit functionality in Trailers Info screen for standard parking TestCase id:1073 #C", "pass", false);
					
				}
		//clicking on edit button
		anyClick("Edit button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Edit.Button")));
				
		//Reverting back to valid trailer id
		//Entering values in trailer id text box
		sendKeys("Trailer ID", By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Edit.Button.Page.TrailerId.Textbox")), strTrailerId.split(",")[1]);
		System.out.println("TrailerId:" +strTrailerId);
				
		//clicking on save button
		anyClick("Save button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Edit.Button.Page.Save.Button")));
		
		if(verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Verify")))==true) {
			
			//clicking on park button in the table
			anyClick("Park button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Park.Button")));
			
		}
		
		//verify form display
		if(verifyElementExist("Form Display", By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Park.Button.Form.Display")))==true) {
		
		//clicking on save button
		anyClick("Save button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Park.Button.Form.Display.Save.Button")));
		
		//verify the alert message
		String checkcodeAlertMessage=driver.switchTo().alert().getText();
		System.out.println("AlertMessage:" +checkcodeAlertMessage);
		reportStep("#B Check code alert window: #C" +checkcodeAlertMessage, "pass", false);
		
		//clicking on OK in alert window
		driver.switchTo().alert().accept();
		reportStep("#B Verified Check Code is required error  message in standard parking screen TestCase id:1070 #C", "pass", false);
		
		//Entering invalid check code
		sendKeys("Entering Check code", By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Park.Button.Form.Display.Checkcode.Textbox")), strCheckcode.split(",")[0]);
		System.out.println("Invalid Check code:" +strCheckcode);
		
		//clicking on save button
		anyClick("Save button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Park.Button.Form.Display.Save.Button")));
				
		//verify the alert message
		String invalidCheckcodeAlertMessage=driver.switchTo().alert().getText();
		System.out.println("AlertMessage:" +invalidCheckcodeAlertMessage);
		reportStep("#B Check code alert window: #C" +invalidCheckcodeAlertMessage, "pass", false);
				
		//clicking on OK in alert window
		driver.switchTo().alert().accept();
		reportStep("#B Verified  error message Please enter the correct check Code. in standard parking screen TestCase id:1071 #C", "pass", false);
		
		//selecting Parking place drop-down
		strParkZone = tdrow.get("Park_Zone");
		System.out.println("strParkZone: "+strParkZone);
		new Select (driver.findElement(By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Park.Button.Form.Display.Parking.Place.Dropdown")))).selectByVisibleText(strParkZone);
		
		
		//Entering valid check code
		sendKeys("Entering Checkcode", By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Park.Button.Form.Display.Checkcode.Textbox")), strCheckcode.split(",")[1]);
		
		//clicking on save button
		anyClick("Save button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Park.Button.Form.Display.Save.Button")));
		
		//verify the message
		String Message=driver.findElements(By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Park.Button.Form.Display.Message.Verify"))).get(0).getText();
		System.out.println("Message:" +Message);
		reportStep("Message:" +Message, "pass", false);
		reportStep("#B Verified Trailer information created successfully message in Trailers Info screen for standard parking TestCase id:1072 #C", "pass", false);
		}
		
		//clicking on Direct parking radio button
		anyClick("Direct Parking radio button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Yard.Trailer.Info.Direct.Radio.Button")));
		
		Vclas_Assignment vclasassgn = new Vclas_Assignment();
		//clearing the value in Trailer id text box
		vclasassgn.clearByLocator(By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.TrailerID.Textbox")));
		
		//clicking on display button
		anyClick("Direct parking display button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Display.Button")));
		
		//verify the message
		//verify the alert message
		String TrailerIDMessage=driver.switchTo().alert().getText();
		System.out.println("AlertMessage:" +TrailerIDMessage);
						
		//clicking on OK in alert window
		driver.switchTo().alert().accept();
		reportStep("#B Trailer Id alert window in Direct Parking: #C" +TrailerIDMessage, "pass", false);
		reportStep("#B Verified Trailer Id is required. error message in parking screen for premium parking TestCase id:1074 #C", "pass", false);
		
		//Entering the value in Trailer id text box
		sendKeys("Trailer ID", By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.TrailerID.Textbox")), strTrailerId.split(",")[1]);
		System.out.println("TrailerId:" +strTrailerId);
		
		//clicking on display button
		anyClick("Direct parking display button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Display.Button")));
		
		if(verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Verify")))==true) {
		
			//clicking on park button
			anyClick("Park button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Park.Button")));
			
		}
		if(verifyElementExistReturn(By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Park.Button.Form.Display")))==true) {
			
			//Selecting gate/buffer
			String gateBufferDropdown=tdrow.get("Gate_Buffer_Dropdown");
			System.out.println("gateBufferDropdown: "+gateBufferDropdown);
			//selectRadioButtonByValue("Gate/Buffer dropdown", By.cssSelector(prop.getProperty("")), strValue)
			new Select (driver.findElement(By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.Direct.Parking.Park.Form.Gate/Buffer.Dropdown")))).selectByVisibleText(gateBufferDropdown);
			reportStep("Gate/Buffer Dropdown:" +gateBufferDropdown, "pass", false);
			
			//clicking on save button
			anyClick("Save button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Park.Button.Form.Display.Save.Button")));
			
			//verify the message
			//verify the alert message
			String Message=driver.switchTo().alert().getText();
			System.out.println("AlertMessage:" +Message);
							
			//clicking on OK in alert window
			driver.switchTo().alert().accept();
			reportStep("#B Message: #C" +Message, "pass", true);
			reportStep("#B Verified Check Code is required error  message in parking screen TestCase id:1075 #C", "pass", false);
			
			//Entering the check code
			sendKeys("Enter the Check code:", By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Park.Button.Form.Display.Checkcode.Textbox")), strCheckcode.split(",")[0]);
			System.out.println("Invalid Check code:" +strCheckcode);
			
			//clicking on save button
			anyClick("Save button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Park.Button.Form.Display.Save.Button")));
			
			//verify the message
			//verify the alert message
			String directParkingCheckcodeMessage=driver.switchTo().alert().getText();
			System.out.println("AlertMessage:" +directParkingCheckcodeMessage);
							
			//clicking on OK in alert window
			driver.switchTo().alert().accept();
			reportStep("#B Message: #C" +directParkingCheckcodeMessage, "pass", false);
			reportStep("#B Verified error message Please enter the correct check Code in parking screen for premium parking TestCase id:1076 #C", "pass", false);
			
			//Entering the check code
			sendKeys("Enter the Check code:", By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Park.Button.Form.Display.Checkcode.Textbox")), strCheckcode.split(",")[2]);
			System.out.println("Invalid Check code:" +strCheckcode);
			
			//clicking on save button
			anyClick("Save button click", By.xpath(prop.getProperty("MASWEB_LDJIT.Park.Page.Table.Park.Button.Form.Display.Save.Button")));
			
			//verify the message
			String directParkingValidCheckcodeMessage=driver.findElements(By.xpath(prop.getProperty("MASWEB_LDJIT.Create.New.Places.Create.Error.Message"))).get(0).getText();
			System.out.println("Message:" +directParkingValidCheckcodeMessage);
			reportStep("Message:" +directParkingValidCheckcodeMessage, "pass", false);
			reportStep("#B Verified Trailer information created successfully message in Parking screen for premium parking TestCase id:1077 #C", "pass", false);
			
		}
		
	}

}
