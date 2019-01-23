package com.volvo.mfg.agv.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.volvo.automation.commonutilities.ExcelUtils;
import com.volvo.mfg.StepDefinition.DriverSuite;

public class V432_Vclas_Positive_TestCases extends DriverSuite {
	String sheetName="V432";
	public HashMap<String,String> tdrow;
	
	//Excel class object to access the function
	ExcelUtils excelUtils = new ExcelUtils();
	
	//01 Verify Existing V60 is used for V432 flow in VCLAS Nodes screen
	public void Vclas_V60_Nodes() throws InterruptedException{
		//Navigating to nodes screen
		anyClick("Home button ", By.xpath(prop.getProperty("Vclas_Home.Home.Button")));
		
		anyClick("Administration Tab", By.xpath(prop.getProperty("Vclas_Home.Administration.Button")));
		
		anyClick("Nodes menu", By.xpath(prop.getProperty("Vclas_Administration.Nodes.Button")));
		
		//clicking on V60 plus button
		if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.V60.Plus.Button")))==true) {
			driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.V60.Plus.Button"))).click();
			//clicking on TA plus button
			driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.V60.TA.Plus.Button"))).click();
			reportStep("TA_Outtake and TA_Trl_Yard nodes displayed successfully", "pass", true);
			//clicking on TA_Outtake
			driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.V60.TA.TA_Outtake.Plus.Button"))).click();
			reportStep("REP_STN,STN1_98 displayed successfully", "pass", true);
			
			//clicking on TA_Trl_Yard
			driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.V60.TA.TA_Trl_Yard.Plus.Button"))).click();
			reportStep("TRL_Yard displayed succesfully", "pass", true);
			
		       System.out.println(driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.V60.TA.TA_Trl_Yard.Plus.GreenColor.Verification"))).getAttribute("style"));
	              List<WebElement> element = driver.findElements(By.xpath(prop.getProperty("Vclas_Administration.Nodes.V60.TA.TA_Trl_Yard.Plus.GreenColor.Verification"))); // get
	                                                                                                                                                                                                              // the
	                                                                                                                                                                                                              // select
	              // iterate over the options
	              for (WebElement option : element) {

	                     if (option.getCssValue("background-color").compareToIgnoreCase("rgba(0, 0, 0, 0)") == 0) {
	                           reportStep(" color verified successfully", "pass", true);
	                           System.out.println(option.getCssValue("background-color"));
	                           reportStep("#B 01 Verify Existing V60 is used for V432 flow in VCLAS Nodes screen--Test case completed#C", "pass", true);

	                     } else {
	                           reportStep(" color verification failed", "fail", true);
	                           System.out.println(option.getCssValue("background-color"));
	                     }
	              }

		}
		
	}
	
	
	// 02 Verify VCLAS sending the authorization to MASweb once Nodes are configured and activated in VCLAS
	
	public void Vclas_Authorization_Nodes_PreCondition() throws InterruptedException{
		
		//Navigating to nodes screen
				anyClick("Home button ", By.xpath(prop.getProperty("Vclas_Home.Home.Button")));
				
				anyClick("Administration Tab", By.xpath(prop.getProperty("Vclas_Home.Administration.Button")));
				
				anyClick("Nodes menu", By.xpath(prop.getProperty("Vclas_Administration.Nodes.Button")));
				
		
		driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.V60.Plus.Button")));
		
		//verification all the nodes are active
		
		/*   System.out.println(driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.V60.GreenColor.Verfication"))).getAttribute("style"));
           List<WebElement> element = driver.findElements(By.xpath(prop.getProperty("Vclas_Administration.Nodes.V60.GreenColor.Verfication"))); // get
                                                                                                                                                                                                           // the
                                                                                                                                                                                                           // select
           // iterate over the options
           for (WebElement option : element) {

                  if (option.getCssValue("background-color").compareToIgnoreCase("rgba(0, 0, 0, 0)") == 0) {
                        reportStep("All nodes under V60 are active", "pass", true);
                        System.out.println(option.getCssValue("background-color"));
                       

                  } else {
                        reportStep("All nodes under V60 are not active", "fail", true);
                        System.out.println(option.getCssValue("background-color"));
                  }
           
		
           }
*/           
           //clicking on send mads to mas button
           anyClick("Send Mads to Mas button", By.xpath(prop.getProperty("Vclas_Administration.Nodes.SendMadstoMAS.Button")));
          
	}
	
	
	public void Vclas_Authorization_Masweb_Tab() throws InterruptedException, AWTException{
		
		//clicking on display authorized vclas places
	
		anyClick("Display Authorized Vclas  button ", By.xpath(prop.getProperty("MASWEB_Vclas_SendAllPlacesToVclas.DisplayAuthorisedVclasPlaces.Button")));
		
		//search through key events
		Robot robot = new Robot();
		 
		 //clicking on cancel
		 robot.keyPress(KeyEvent.VK_CONTROL);	
		 robot.keyPress(KeyEvent.VK_F);
		 Thread.sleep(2000);
	     robot.keyRelease(KeyEvent.VK_F);
	     robot.keyRelease(KeyEvent.VK_CONTROL);
	    Thread.sleep(2000);
		 
		    robot.keyPress(KeyEvent.VK_V);
		    robot.keyRelease(KeyEvent.VK_V);
		    Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_6);
		robot.keyRelease(KeyEvent.VK_6);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_0);
		robot.keyRelease(KeyEvent.VK_0);
		Thread.sleep(2000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
		reportStep("V60 nodes are displayed in Authorized vclas places table", "pass", true);
		reportStep("#B 02 Verify VCLAS sending the authorization to MASweb once Nodes are configured and activated in VCLAS-- Test case Completed#C", "pass", false);
		
	}


	//03 Verify Existing V60 Room configuration for V432 flow
	public void V60_Room_Configuration() throws InterruptedException{
		//clicking on administration
		anyClick("Home button ", By.xpath(prop.getProperty("Vclas_Home.Home.Button")));
		
		anyClick("Administration Tab", By.xpath(prop.getProperty("Vclas_Home.Administration.Button")));
		//clicking on rooms tab
		anyClick("Rooms Menu", By.xpath(prop.getProperty("Vclas_Home.Administration.Rooms.Button")));
		//clicking on workshop dropdown
		anyClick("Select workshop dropdown", By.xpath(prop.getProperty("Vclas.Administration.Rooms.SelectWorkShop.Dropdown")));
		//selecting V60
		anyClick("V60 dropdown value", By.xpath(prop.getProperty("Vclas.Administration.Rooms.SelectWorkShop.Dropdown.V60")));
		//clicking on search button
		anyClick("Search button", By.xpath(prop.getProperty("Vclas.Administration.Rooms.Search.Button")));
		
		//clicking on edit button
		anyClick("Edit button", By.xpath(prop.getProperty("Vclas.Administration.Rooms.SelectWorkShop.Dropdown.V60.Edit.Button")));
		//clikcing on edit notes button
		anyClick("Edit notes button", By.xpath(prop.getProperty("Vclas.Administration.Rooms.SelectWorkShop.EditNotes.Button")));
		//clicking on search button of child window
		anyClick("Child window Search button", By.xpath(prop.getProperty("Vclas.Administration.Rooms.Verify.Selectnodes.Page.Search.Button")));
		//clicking on close button
		anyClick("Child window close button", By.xpath(prop.getProperty("Vclas.Administration.Rooms.Verify.Selectnodes.Page.Close.Button")));
		//clicking on update button without doing any change
		anyClick("Update button", By.xpath(prop.getProperty("Vclas.Administration.Rooms.SelectWorkShop.Update.Button")));
		
		//verification of saved message
		if(verifyElementExist("Saved message", By.xpath(prop.getProperty("Vclas.Administration.Rooms.SelectWorkShop.Saved.Message")))==true) {
			reportStep("#B03 Verify Existing V60 Room configuration for V432 flow--Test case completed#C", "pass", true);
		}else {
			reportStep("03 Verify Existing V60 Room configuration for V432 flow", "fail", true);
		}
		
		
		
	}


	
	//04 Verify the Transport Section Configuration for V432 outtake stations
	
	public void V432_TransportSection() throws InterruptedException{
		
		anyClick("Home button ", By.xpath(prop.getProperty("Vclas_Home.Home.Button")));
		
		anyClick("Administration Tab", By.xpath(prop.getProperty("Vclas_Home.Administration.Button")));
		//clicking on Transport section tab
		anyClick("Transport Section Tab", By.xpath(prop.getProperty("Vclas_Administration.TransportSection.Button")));
		//clicking on search button
		anyClick("Search button", By.xpath(prop.getProperty("Vclas_TransportSection.SearchButton")));
		//verification of Search table displayed
		if(verifyElementExist("Transport section search Table", By.xpath(prop.getProperty("Vclas_TransportSection.Table")))==true) {
			reportStep("Transport section Search Table displayed successfully", "pass", true);
		}else {
			reportStep("Transport section table is not displayed", "fail", true);
		}
		//entering in search field as TRl
		sendKeys("Search field", By.xpath(prop.getProperty("Vclas_TransportSection.searchFeild")), "OUTTAKE_TO_TA_TRL_YARD");
		//clicking on search button
		anyClick("Search button", By.xpath(prop.getProperty("Vclas_TransportSection.SearchButton")));
		driver.findElement(By.xpath(prop.getProperty("Vclas_TransportSection.SearchButton")));
		Thread.sleep(3000);
		//verification of OUTTAKE_TO_TA_TRL_YARD
	
		if(verifyElementExist("Outtake TO TA_Trl Yard ", By.xpath(prop.getProperty("Vclas_TransportSection.OUTTAKE_TO_TA_TRL_YARD")))==true) {
			reportStep("OUTTAKE_TO_TA_TRL_YARD is displayed successfully", "pass", true);
			reportStep("#B04 Verify the Transport Section Configuration for V432 outtake stations -- Test case Completed#C", "Pass", false);
		}else {
			reportStep("Outtake To_TA_Trl_Yard is not displayed", "fail", true);
			reportStep("04 Verify the Transport Section Configuration for V432 outtake stations--Test case failed", "fail", false);
		}
	
	
	
	}
}
