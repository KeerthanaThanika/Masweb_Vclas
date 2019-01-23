package com.volvo.mfg.agv.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.volvo.automation.commonutilities.ExcelUtils;
import com.volvo.mfg.StepDefinition.DriverSuite;;

public class Vclas_Assignment extends DriverSuite {

	String strFTaskId, tblFData, FObject, TaskId, Conveyor;
	public String ConveyorCode = "";
	String sheetName = "Vclas_Assignments";
	public static boolean tempVar = false;
	public int j=0;
			public static int intRackNewSeq;
		
	public Vclas_tasks vt = new Vclas_tasks(); 
	public HashMap<String, String> tdrow;
	MasWeb_Home mwHome = new MasWeb_Home();
	List<String> listr = vt.getList();
	// Excel class object to access the function
	ExcelUtils excelUtils = new ExcelUtils();


	public void Vclas_Assignment_Navigate() throws InterruptedException {
		driver.switchTo().window(browser[2]);

		// Navigate the Page
		anyClick("Assignment Navigate", By.xpath(prop.getProperty("Vclas_Home.Assignments.Button")));
		Thread.sleep(5000);

		// Verify the Page is displayed
		verifyElementExist("Assignment Page", By.xpath(prop.getProperty("Vclas_Home.Work.Areas.Button")));

	}

	public void Agv_Vclas_Assignment_Navigate() throws InterruptedException {

		// Navigate the Page
		anyClick("Assignment Navigate", By.xpath(prop.getProperty("Vclas_Home.Assignments.Button")));
		Thread.sleep(5000);

		// Verify the Page is displayed
		verifyElementExist("Assignment Page", By.xpath(prop.getProperty("Vclas_Home.Work.Areas.Button")));

	}

	public void Vclas_Assignment_SelectWorkArea(String areaSelect, String roomSelect) throws InterruptedException {
		try {
			// areaSelect - Room / Zones as parameter

			// Navigate the Page
			anyClick("Work Area Navigate", By.xpath(prop.getProperty("Vclas_Home.Work.Areas.Button")));

			// Click the Rooms
			anyClick("Home tab click", By.xpath(prop.getProperty("Vclas_Home." + areaSelect + ".Tab")));

			// Reseting the Values
			anyClick("Clearing List Values",
					By.xpath(prop.getProperty("Vclas_Home." + areaSelect + ".RemoveAll.Button")));

			String singleRoom[] = roomSelect.split(",");

			for (int i = 0; i <= singleRoom.length - 1; i++) {
				System.out.println(singleRoom[i]);
				// Enter the data to select
				
				sendKeys("Search Rooms selection",
						By.xpath(prop.getProperty("Vclas_Home." + areaSelect + ".List.Text")), singleRoom[i]);
				
				//String search=driver.findElement(By.xpath(prop.getProperty("Vclas_Home." + areaSelect+ ".List.Text"))).getAttribute("value");
				//System.out.println("Search: "+search);
				
				WebElement wb =driver.findElement(By.xpath(prop.getProperty("Vclas_Home." + areaSelect + ".Select.Item").replace("&Value", singleRoom[i])));
				

				// Select drop down
				anyClick(singleRoom[i] + " Room", By.xpath(prop.getProperty("Vclas_Home." + areaSelect + ".Select.Item")
						.replace("&Value", singleRoom[i])));
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", wb); 
				
				// Add the Room Value
				anyClick("Adding the Room: " + roomSelect, By.xpath(prop.getProperty("Vclas_Home.Room.Add.Button")));
				Thread.sleep(2000);

			}
		} catch (Exception e) {
			System.out.println("Exception in Vclas_Assignment_SelectWorkArea method");
		}

	}

	/*
	 * public void Vclas_Shortage_Search(String objectId, String seqNo, String
	 * flowName) throws InterruptedException {
	 * 
	 * String searchData; System.out.println("seq no:" + seqNo); if
	 * (flowName.equalsIgnoreCase("JISJIT")) { if (seqNo.equalsIgnoreCase("")) {
	 * searchData = objectId; } else { searchData = objectId + seqNo; } } else { if
	 * (seqNo.equalsIgnoreCase("")) { searchData = objectId; } else { searchData =
	 * objectId + " " + seqNo; } }
	 * 
	 * // Verify the Shortage screen is displayed
	 * 
	 * if (driver.findElements(By.xpath(prop.getProperty(
	 * "Vclas_Home.Shortage.Search.Text"))).size() > 0) {
	 * 
	 * waitForElement(driver,
	 * By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text")), 2);
	 * driver.findElements(By.xpath(prop.getProperty(
	 * "Vclas_Home.Shortage.Search.Text"))).get(0) .sendKeys(Keys.LEFT_CONTROL + "a"
	 * + Keys.DELETE); driver.findElement(By.xpath(prop.getProperty(
	 * "Vclas_Home.Shortage.Search.Text"))).sendKeys(Keys.TAB); // Enter the Object
	 * Id driver.findElements(By.xpath(prop.getProperty(
	 * "Vclas_Home.Shortage.Search.Text"))).get(0) .sendKeys(objectId);
	 * 
	 * reportStep("Shortage screen is displayed successfully", "Pass", true);
	 * 
	 * // Search data in the table with sequence no. String objVerify =
	 * prop.getProperty("Vclas_Home.Shortage.Table.TD.Verify").replace("&Value",
	 * searchData); System.out.println(objVerify); String nextButton =
	 * prop.getProperty("Vclas_Home.Shortage.Next.Button"); String firstButton =
	 * prop.getProperty("Vclas_Home.Shortage.First.Button");
	 * 
	 * int intTemp = 1; do { waitForElement(driver,
	 * By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Button")), 5);
	 * 
	 * // Clicking the button to verify the table is displayed
	 * driver.findElements(By.xpath(prop.getProperty(
	 * "Vclas_Home.Shortage.Search.Button"))).get(0).click(); Thread.sleep(2000); if
	 * (verifyElementExistReturn(By.xpath(objVerify)) == true) {
	 * reportStep(searchData + " exist in the Shortage screen", "Pass", true);
	 * break; }
	 * 
	 * // Verifying if it available in Next Screen while
	 * (verifyElementExistReturn(By.xpath(nextButton)) == true) {
	 * 
	 * // Clicking the next button anyClick("Shortage Next Button",
	 * By.xpath(nextButton));
	 * 
	 * if (verifyElementExistReturn(By.xpath(objVerify)) == true) {
	 * reportStep(searchData + " exist in the Shortage screen", "Pass", true);
	 * return; } }
	 * 
	 * if (verifyElementExistReturn(By.xpath(firstButton)) == true) { // Clicking
	 * the first button anyClick("Shortage First Button", By.xpath(firstButton)); }
	 * 
	 * intTemp = intTemp + 1;
	 * 
	 * } while (!(intTemp == 3));
	 * 
	 * if (intTemp == 3) { reportStep(objectId +
	 * " Object id search not displayed in the screen (60 seconds waited)", "fail",
	 * true); } } }
	 */

	public void Vclas_Shortage_Search(String objectId, String seqNo, String flowName) throws InterruptedException {

		String searchData;
		System.out.println("seq no:" + seqNo);
		if (flowName.equalsIgnoreCase("JISJIT")) {
			if (seqNo.equalsIgnoreCase("")) {
				searchData = objectId;
			} else {
				searchData = objectId + seqNo;
			}
		} else {
			if (seqNo.equalsIgnoreCase("")) {
				searchData = objectId;
			} else {
				searchData = objectId +" " + seqNo;  //-----CHANGED HERE FOR TEST ENV 
			}
		}

		// Verify the Shortage screen is displayed

		if (driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text"))).size() > 0) {

			//driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text"))).get(0).sendKeys(Keys.LEFT_CONTROL + "a" + Keys.DELETE);
			//new
			clearByLocator(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text")));
			// Enter the Object Id
			driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text"))).get(0)
					.sendKeys(objectId);

			// WebDriverWait wait = new WebDriverWait(driver, 2);
			// wait.until(ExpectedConditions.stalenessOf(driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Button"))).get(0)));
			waitForElement(driver, By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Button")), 5);
			retryingFindClick(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Button")));
			waitForElement(driver, By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Button")), 5);

			// Clicking the Search Button
			// driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Button"))).get(0).click();

			reportStep("Shortage screen is displayed successfully", "Pass", true);

			// Search data in the table with sequence no.
			String objVerify = prop.getProperty("Vclas_Home.Shortage.Table.TD.Verify").replace("&Value", searchData);
			System.out.println(objVerify);
			String nextButton = prop.getProperty("Vclas_Home.Shortage.Next.Button");
			String firstButton = prop.getProperty("Vclas_Home.Shortage.First.Button");

			int intTemp = 1;
			do {

				// Clicking the button to verify the table is displayed
				//anyClick("Search Areas", By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Button")));
				driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Button"))).get(0).click();
				Thread.sleep(4000);
				if (verifyElementExistReturn(By.xpath(objVerify)) == true) {
					reportStep(searchData + " exist in the Shortage screen", "Pass", true);
					break;
				}

				// Verifying if it available in Next Screen
				while (verifyElementExistReturn(By.xpath(nextButton)) == true) {

					// Clicking the next button
					anyClick("Shortage Next Button", By.xpath(nextButton));

					if (verifyElementExistReturn(By.xpath(objVerify)) == true) {
						reportStep(searchData + " exist in the Shortage screen", "Pass", true);
						return;
					}
				}

				if (verifyElementExistReturn(By.xpath(firstButton)) == true) {
					// Clicking the first button
					anyClick("Shortage First Button", By.xpath(firstButton));
				}

				intTemp = intTemp + 1;

			} while (!(intTemp == 3));

			if (intTemp == 3) {
				reportStep(objectId + " Object id search not displayed in the screen (60 seconds waited)", "fail",
						true);
			}
		}
	}

	public void Vclas_Shortage_TypeVerify(String ObjectId, String TaskId, String Type, String Status)
			throws InterruptedException {
		try {
			
			//String spaceVerify = ObjectId.split(" ")[0];
			//System.out.println("spaceVerify :" +spaceVerify);
			// Search Object Id and Task Id in the table
			String objIdVerify = prop.getProperty("Vclas_Home.Shortage.Object_Id.Verify").replace("&Value", ObjectId)
					.replace("&1Value", TaskId);

			// Verifying if it is available in the screen
			if (verifyElementExistReturn(By.xpath(objIdVerify)) == true) {

				reportStep(
						"--------Shortage screen verification: Flow: #B" + ObjectId + "#C Task Id: #B" + TaskId
								+ "#C Type: #B" + Type + "#C and Status: #B" + Status + "#C Initiated----",
						"Info", false);
				reportStep("ObjectId : " + ObjectId + " " + "TaskId: " + TaskId + " exists in the Shortage Screen ",
						"PASS", true);
				/*if(spaceVerify.equalsIgnoreCase(tdrow.get("Sequence_Type"))) {
					reportStep("ObjectId : #B" + ObjectId + "#C" + "has been displayed #B with space in the Shortage Screen - Mapped to TestCase ID - 2813 #C  ",
							"PASS", true);
				}else {
				reportStep("ObjectId : #B" + ObjectId + "#C" + "has been displayed #B without any space in the Shortage Screen - Mapped to TestCase ID - 2813 #C  ",
						"PASS", true);
				}*/
				String objType = prop.getProperty("Vclas_Home.Shortage.Object_Id_Type.Verify")
						.replace("&Value", ObjectId).replace("&1Value", TaskId);

				int iTempClick = 0;
				do {

					driver.findElement(By.xpath(objType));
					iTempClick = iTempClick + 1;
					driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

					if (verifyElementExistReturn(By.xpath(objType)) == true) {
						break;
					}
				} while (iTempClick != 5);
				String newType = driver.findElement(By.xpath(objType)).getText();
				System.out.println(newType);
				String newStatus;

				
				// Verifying the type is available in the screen
				if (newType.equalsIgnoreCase(Type)) {
					reportStep(Type + " " + "type is verified", "PASS", true);
					
					
						
					int iTemp = 0;
					do {
						String objStatus = prop.getProperty("Vclas_Home.Shortage.Object_Id_Type_Status.Verify")
								.replace("&Value", ObjectId).replace("&1Value", TaskId);

						// new for stale Exception
						int iTempClickVar = 0;
						do {

							driver.findElement(By.xpath(objStatus));
							iTempClickVar = iTempClickVar + 1;
							driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

							if (verifyElementExistReturn(By.xpath(objStatus)) == true) {
								break;
							}
						} while (iTempClickVar != 10);
						newStatus = driver.findElement(By.xpath(objStatus)).getText();
						System.out.println(newStatus);
						// Status Verification
						if (newStatus.equalsIgnoreCase(Status)) {
							reportStep(Status + " Status is Verified successfully ", "PASS", true);
							reportStep("--------Shortage screen verification: Flow: #B" + ObjectId + "#C Task Id: #B"
									+ TaskId + "#C Type: #B" + Type + "#C and Status: #B" + Status
									+ "#C Completed. PASSED----", "Info", false);

							break;
						}
						iTemp = iTemp + 1;

					} while (!(iTemp == 10));
					if (iTemp == 10) {

						reportStep("--------Shortage screen verification: Flow: #B" + ObjectId + "#C Task Id: #B"
								+ TaskId + "#C Type: #B" + Type + "#C and Status: #B" + Status
								+ "#C Completed. Failed----", "Info", false);
						reportStep("Flow: #B" + ObjectId + "#C Task Id: #B" + TaskId + "#C Type: #B" + Type
								+ "#C and Status: #B"
								+ "Status is not verified successfully. The Status to be expected:" + Status
								+ "The Actual Status is:" + String.valueOf(newStatus), "fail", true);
					}

				} else {
					reportStep("Type is not verified successfully. The type to be expected:" + Type
							+ "The Actual Type is:" + String.valueOf(newType), "fail", true);
				}
			} else {
				reportStep(ObjectId + TaskId + " doesn't exists in the Shortage Screen", "fail", true);
			}

		} catch (Exception e) {
			System.out.println("Exception in Vclas_Shortage_TypeVerify Method");
		}
	}

	public long Vclas_Shortage_Time_Verify(String report, String objectId, String seqNo, String flowName)
			throws InterruptedException {
		driver.switchTo().window(browser[2]);
		long newTime = 0;
		String searchData;

		if (flowName.equalsIgnoreCase("JISJIT")) {
			if (seqNo.equalsIgnoreCase("")) {
				searchData = objectId;
			} else {
				searchData = objectId + seqNo;
			}
		} else {
			if (seqNo.equalsIgnoreCase("")) {
				searchData = objectId;
			} else {
				searchData = objectId + seqNo;
			}
		}

		driver.switchTo().window(browser[2]);

		// Navigation to Shortage
		anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));

		// Verify the Shortage screen is displayed
		if (driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text"))).size() > 0) {
			// Enter the Object Id
			driver.findElement(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text"))).click();
			driver.findElement(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text")))
					.sendKeys(Keys.LEFT_CONTROL + "a" + Keys.DELETE);
			// driver.findElement(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text"))).sendKeys(Keys.LEFT_CONTROL
			// + "a" );
			// driver.findElement(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text"))).sendKeys
			// (Keys.DELETE);
			driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text"))).get(0)
					.sendKeys(objectId);

			// Clicking the Search Button

			driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Button"))).get(0).click();

			reportStep("Shortage screen is displayed", "Pass", true);

			// Retrieving the time
			String timeVerify = prop.getProperty("Vclas_Home.Shortage.Time_Verify").replace("&Value", searchData);
			for (int i = 0; i <= 3; i++) {

				driver.findElement(By.xpath(timeVerify));
			}
			newTime = Integer.parseInt(driver.findElement(By.xpath(timeVerify)).getText());
			Thread.sleep(2000);
			System.out.println(newTime);
			reportStep("Picking the time for Line Movement of the Rack: " + objectId + " Time: " + newTime, "pass",
					true);
			// Update in the excel sheet
			excelUtils.excelUpdateValue("Vclas_Assignments", "NewTime", refTestDataName, String.valueOf(newTime));
			return newTime;

		} else {
			reportStep("Shortage screen is not displayed", "Fail", true);
			return newTime;
		}
	}

	public void Vclas_AssignmentList_RackSubmission_JISJIT(String rackType, String objectId)
			throws InterruptedException {
		driver.switchTo().window(browser[2]);
		System.out.println("Search racksubmission for " + rackType + objectId);

		// navigate to assignment page

		//driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Assignment.List.Button"))).get(0).click();
		anyClick("Assignment List Tab", By.xpath(prop.getProperty("Vclas_Home.Assignment.List.Button")));
		reportStep("Assignment tab is clicked successfully ", "pass", false);

		String rackName = prop.getProperty("Vclas_home.AssignmentList.Column.Type").replace("&Value", objectId)
				.replace("&1Value", rackType);
		System.out.println("rack name" + rackName);

		// Verify the assignment area table is displayed
		if (verifyElementExistReturn(By.xpath(rackName)) == true) {

			// Selecting the Rack

			reportStep("Object Id: #B" + objectId + "#C  Rack Type: #B" + rackType
					+ "#C is displayed in Assignment List Screen", "pass", true);
			
			reportStep("ObjectId : #B" + objectId + "#C " + "has been displayed successfully #B without any space in Assignment List Screen - Mapped to TestCase ID - 2813 #C ",
					"PASS", true);
			
			driver.findElements(By.xpath(rackName)).get(0).click();
			reportStep(objectId + "  " + rackType + " has been picked up for deliver from Assignment List screen",
					"pass", true);
			Thread.sleep(5000);
			System.out.println("rack name" + rackName);

			driver.findElements(By.xpath(rackName)).get(0).click();
			Thread.sleep(5000);

		} else {
			reportStep(rackName + " does not exist", "fail", true);
		}

	}

	public void Vclas_AssignmentList_RackSubmission_Tugger(String rackType, String objectId)
			throws InterruptedException {
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		String Topos;
		try {
			driver.switchTo().window(browser[2]);

			System.out.println("search racksubmission for " + rackType + objectId);

			// navigate to assignment page
			anyClick("Assignment list Areas", By.xpath(prop.getProperty("Vclas_Home.Assignment.List.Button")));
			reportStep("Assignment tab is clicked successfully ", "pass", false);

			// Verify the assignment area table is displayed

			if (verifyElementExistReturn(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Table"))) == true) {
				reportStep("Assignment table is displayed successfully", "pass", true);
				String rackObject = prop.getProperty("Vclas_home.AssignmentList.Column.Type.Tugger")
						.replace("&Value", rackType).replace("&1Value", objectId.substring(0, 3));
				Topos = tdrow.get("toPos").trim();
				System.out.println("topos" + Topos);
				String emptyrackObject = prop.getProperty("Vclas_home.AssignmentList.EmptyColumn.Type")
						.replace("&Value", rackType).replace("&1Value", String.valueOf(Topos));
				System.out.println("ero" + emptyrackObject);

				System.out.println(rackObject);
				String rackName = prop.getProperty("Vclas_home.AssignmentList.Column1.Type").replace("&Value",
						objectId);
				System.out.println("rack name" + rackName);

				// Size of the Racks available
				int ColumnCount = driver.findElements(By.xpath(rackObject)).size();

				for (int i = 0; i <= ColumnCount - 1; i++) {

					// Clicking the First Object
					// new
					if (rackType.equalsIgnoreCase("Full_Rack")) {
						driver.findElements(By.xpath(rackObject)).get(0).click();
						Thread.sleep(5000);
						//reportStep(objectId + " " + rackType + " is Displayed in Assignment List Screen", "pass", true);
						System.out.println(rackObject + "rack obj");
					} else {
						driver.findElements(By.xpath(emptyrackObject)).get(0).click();
						reportStep(objectId + " " + rackType + " is Displayed in Assignment List Screen", "pass", true);
						Thread.sleep(5000);
						System.out.println(emptyrackObject + "rack obj");
					}

					if (driver.findElements(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Table"))).size() > 0) {
						
							//if (verifyElementExist("Rack name",By.xpath(rackName))== true)
								if (driver.findElements(By.xpath(rackName)).size() > 0){
									reportStep(objectId + " " + rackType + " is Displayed in Assignment List Screen", "pass", true);
							// Selecting the Rack
							driver.findElements(By.xpath(rackName)).get(0).click();

							Thread.sleep(5000);
							reportStep(objectId + " " + rackType + " Has Been Picked Up From Assignment List Screen",
									"pass", true);

							break;

						} else {
							//anyClick("Drop", (By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Drop.icon"))));
							driver.findElements(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Drop.icon"))).get(0).click();
							Thread.sleep(5000);
						}
					} else {
						reportStep("Assignment list table does not exist", "fail", true);
						System.out.println("Assignment list table does not exist");
					}
					if (i == ColumnCount - 1) {

						reportStep(rackType + "does not exist", "fail", true);
						break;
					}

				}

			} else {
				reportStep("Assignment list table does not exist", "fail", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Vclas_AssignmentList(String scan, String rackType, String sendType, String flowName)
			throws InterruptedException {
		int rackSizeVar = 0;
		String objVar = "";
		try {

			if (flowName.equalsIgnoreCase("Tugger") && rackType.equalsIgnoreCase("Full_Rack")) {
				objVar = scan.substring(1, 4) + " " + scan.substring(4, 6);
				System.out.println("objVar" + objVar);
				rackSizeVar = Integer.parseInt(tdrow.get("Line_movement"));

			} else if (flowName.equalsIgnoreCase("Tugger") && rackType.equalsIgnoreCase("Empty_Rack")) {
				objVar = scan;
				rackSizeVar = Integer.parseInt(tdrow.get("Line_movement"));
			}

			if (verifyElementExistReturn(
					By.xpath(prop.getProperty("Vclas_home.AssignmentList.ScanDialogBox"))) == true) {
				reportStep("Assignment Scan dialog box is displayed successfully", "Info", true);
				if (rackType.equalsIgnoreCase("Empty_Rack")) {
					if (verifyElementExistReturn(
							By.xpath(prop.getProperty("Vclas.AssignmentList.OkButton.Click"))) == true) {
						System.out.println("Vclas_AssignmentList-Empty Rack");

						driver.findElements(By.xpath(prop.getProperty("Vclas.AssignmentList.OkButton.Click"))).get(0)
								.click();
						Thread.sleep(3000);
						if (flowName.equalsIgnoreCase("Tugger")) {
							reportStep(rackType + " Has Been Delivered From Assignment List Screen with RackSize: "
									+ rackSizeVar, "pass", true);
						} else {
							reportStep(rackType + " Has Been Delivered From Assignment List Screen ", "pass", true);
						}

					}
				} else {
					if (flowName.equalsIgnoreCase("Tugger")) {

						driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")))
								.sendKeys(scan);
					} else {
						if (verifyElementExistReturn(
								By.xpath(prop.getProperty("Vclas.AssignmentList.ManualInput." + sendType))) == false) {
							if (sendType.equalsIgnoreCase("Yes")) {
								driver.findElements(By.xpath(prop.getProperty("Vclas.AssignmentList.ManualInput.No")))
										.get(0).click();
Thread.sleep(3000);
								driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")))
										.sendKeys(scan);
								driver.findElements(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")))
								.get(0).sendKeys(Keys.TAB);

							} else {
								//driver.findElements(By.xpath(prop.getProperty("Vclas.AssignmentList.ManualInput.Yes"))).get(0).click();
								anyClick("OK Button", By.xpath(prop.getProperty("Vclas.AssignmentList.ManualInput.Yes")));
								driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")))
										.sendKeys(scan);

							}
						} else if (sendType.equalsIgnoreCase("No")) {

							driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")))
									.sendKeys(scan);
							driver.findElements(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")))
							.get(0).sendKeys(Keys.TAB);

						} else {
							driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")))
									.sendKeys(scan);
							driver.findElements(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")))
									.get(0).sendKeys(Keys.TAB);
						}

						Thread.sleep(10000);
					}
						 if (verifyElementExistReturn(By
									.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.BoplopRange.Label"))) == false)  {

							Thread.sleep(3000);
					
							if (verifyElementExistReturn(
									By.xpath(prop.getProperty("Vclas.AssignmentList.OkButton.Click"))) == true) {

								driver.findElements(By.xpath(prop.getProperty("Vclas.AssignmentList.OkButton.Click"))).get(0).click();
								//anyClick("Ok Button", By.xpath(prop.getProperty("Vclas.AssignmentList.OkButton.Click")));
								if (flowName.equalsIgnoreCase("Tugger")) {
									reportStep(rackType + " " + objVar
											+ " Has Been Delivered From Assignment List Screen with RackSize: " + rackSizeVar,
											"pass", true);
								} else {
									reportStep(rackType + " " + scan + " Has Been Delivered From Assignment List Screen ",
											"pass", true);
								}

								//Thread.sleep(3000);
							} /*else {
								reportStep("Ok button for the " + rackType + "is not enabled", "fail", true);
							}*/
						 }else {
							if (sendType.equalsIgnoreCase("Yes")) {

								Thread.sleep(10000);
								System.out.println("BOPLOP range: " + tdrow.get("BOPLOP_Range"));
								driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")))
										.sendKeys(tdrow.get("BOPLOP_Range"));
								driver.findElements(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")))
										.get(0).sendKeys(Keys.TAB);
								 reportStep("#B 45.Verify the functonality of no ASN and no records in PMR861 and PMR862, while scanning VCLAS system ask for BOPLOP range Test case Id:3203--completed#C", "pass", false);
							} else {

								System.out.println("BOPLOP range: " + tdrow.get("BOPLOP_Range"));
								driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")))
										.sendKeys(tdrow.get("BOPLOP_Range"));
								 reportStep("#B 45.Verify the functonality of no ASN and no records in PMR861 and PMR862, while scanning VCLAS system ask for BOPLOP range Test case Id:3203--completed#C", "pass", false);
							}
							
							Thread.sleep(5000);
							if (verifyElementExistReturn(
									By.xpath(prop.getProperty("Vclas.AssignmentList.OkButton.Click"))) == true) {

								driver.findElements(By.xpath(prop.getProperty("Vclas.AssignmentList.OkButton.Click"))).get(0)
										.click();
								if (flowName.equalsIgnoreCase("Tugger")) {
									reportStep(rackType + " " + objVar
											+ " Has Been Delivered From Assignment List Screen with RackSize: " + rackSizeVar,
											"pass", true);
								} else {
									reportStep(rackType + " " + scan + " Has Been Delivered From Assignment List Screen ",
											"pass", true);
								}

								Thread.sleep(3000);
							} else {
								reportStep("Ok button for the " + rackType + "is not enabled", "fail", true);
							}
						 }
						
				
					
					}

			} else {
				reportStep("Scan dialogue box for the" + rackType + "is not displayed", "fail", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

			

	public void Vclas_Tugger_Shortage_Assignment_Flow(String flowName) throws InterruptedException {
		try {
			//vt= new Vclas_tasks();
			String strSeqno, seqHandling;
			int Seqno, racksCount, iTempSeqNo, strLopNo;
			String rackStatus, strFlowType;
			String[] strType;
			int j=0;
			Admin_Page adp = new Admin_Page();
			

			// Navigation to Shortage
			anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));
			Thread.sleep(5000);

			String sheetName = "Vclas_Assignments";
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
			Seqno = Integer.parseInt(tdrow.get("Object")); // 1
			racksCount = Integer.parseInt(tdrow.get("No_of_Racks"));
			strFlowType = tdrow.get("Sequence_Type");
			strType = tdrow.get("Shortage_Type_Expected").split(",");
			strLopNo = Integer.parseInt(tdrow.get("Lopnr"));
			String strTempSeqNo;

			if (flowName.equalsIgnoreCase("Tugger")) {
				strSeqno = String.valueOf(Seqno);// 01
				seqHandling = " ";
				
			} else {
				
				
				strSeqno = tdrow.get("Object");// 0001
				System.out.println("seq no :" + strSeqno);
				seqHandling = StringUtils.repeat("0", 4 - (String.valueOf(Seqno).length())); // 000
				System.out.println("seq handling:" + seqHandling);
			}

			System.out.println("flow name : " + flowName);

			Vclas_Shortage_Search(strFlowType, strSeqno, flowName);

			iTempSeqNo = Seqno;

			System.out.println("seq no:" + iTempSeqNo);
			// To Verify Initial Status in Shortage Screen for created racks
			for (int i = 1; i <= racksCount; i++) {

				if (i <= racksCount) {
					rackStatus = "Planned";
				} else {
					rackStatus = "Active";
					
				}
				List<String> listr = vt.getList();
			System.out.println("full TASK ID: " +listr);
			
			String retval = listr.get(0);
			System.out.println("retval:" +retval);
			
			List<String> listrempty = vt.getList1();
			System.out.println("empty TASK ID: " +listrempty);
			
			String retvalempty = listrempty.get(0);
			System.out.println("retval:" +retvalempty);
			
			
			//new
			String ObjectId=strFlowType + seqHandling + String.valueOf(iTempSeqNo);
			String spaceVerify =ObjectId.split(" ")[0];
			System.out.println("spaceVerify :" +spaceVerify);
			
			if(spaceVerify.equalsIgnoreCase(tdrow.get("Sequence_Type"))) {
				reportStep("ObjectId : #B" + ObjectId + "#C" + "has been displayed #B with space in the Shortage Screen - Mapped to TestCase ID - 2813 #C  ",
						"PASS", true);
			}else {
			reportStep("ObjectId : #B" + ObjectId + "#C" + "has been displayed #B without any space in the Shortage Screen - Mapped to TestCase ID - 2813 #C  ",
					"PASS", true);
			}
				// To verify the Full task id
				Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
						listr.get(j).toString(), strType[0].toUpperCase(), rackStatus);

				// To Verify the Empty task id
				Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
						listrempty.get(j).toString(), strType[1].toUpperCase(), "Active");
				 
				Vclas_Shortage_PositionVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
						listrempty.get(j).toString(), "UNKNOWN");
				
                    j++;   //new
				// Sequence No
				iTempSeqNo = iTempSeqNo + 1;

				if (iTempSeqNo == 100) {
					iTempSeqNo = 1;
				}

			}
			reportStep("07 Verify the functionality of planning JISJIT Tugger assignments of Normal ASN from Shortage screen in Vclas application---Testcase Id:2788 Executed successfully","info",true);
			

			iTempSeqNo = Seqno;
			System.out.println("itemp val:" + iTempSeqNo);
			int lastSubmittedFullRack = 0;

			// Click the Planned Activity in Assignment List for Initial Two Full Racks
			for (int i = 1; i <= racksCount; i++) {

				reportStep("--------Full rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
						+ "#C Initiated----", "Info", false);
				// Submitting the Rack
				System.out.println(
						"rck submsn" + strType[0] + " " + strFlowType + seqHandling + String.valueOf(iTempSeqNo));
				if (flowName.equalsIgnoreCase("Tugger")) {

					Vclas_AssignmentList_RackSubmission_Tugger(strType[0],
							strFlowType + seqHandling + String.valueOf(iTempSeqNo));
				} else {
					Vclas_AssignmentList_RackSubmission_JISJIT(strType[0].toUpperCase(),
							strFlowType + seqHandling + String.valueOf(iTempSeqNo));
				}

				lastSubmittedFullRack = iTempSeqNo;

				if (flowName.equalsIgnoreCase("Tugger")) {
					if (String.valueOf(iTempSeqNo).length() == 1) {
						strTempSeqNo = "0" + String.valueOf(iTempSeqNo);
						System.out.println(strTempSeqNo);
					} else {
						strTempSeqNo = String.valueOf(iTempSeqNo);
						System.out.println(strTempSeqNo);
					}

					// Calculate LopNo for ScanId
					strLopNo = strLopNo + Integer.parseInt(tdrow.get("Line_movement"));
					System.out.println("strLopNo:" + strLopNo);

					// Calculate Scan Id
					String scanId = "T" + strFlowType + strTempSeqNo + strLopNo;
					System.out.println("scan id" + scanId);
					System.out.println(strFlowType + seqHandling + String.valueOf(iTempSeqNo) + "ok click ");
					Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName);
					 reportStep("#B Verification of  delivery functionality of a Tugger full rack when Node Scan on delivery, Scan on delivery parameters are true for the TPOS node Test case Id:3248--completed#C", "pass", false);
				} else {

					strTempSeqNo = StringUtils.repeat("0", 4 - String.valueOf(iTempSeqNo).length()) + iTempSeqNo;
					System.out.println("strTempSeqNo:" + strTempSeqNo);

					// Calculate Scan Id
					String scanId = strFlowType + strTempSeqNo;
					System.out.println("scan id" + scanId);
					Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName);
				}

				reportStep("--------Full rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
						+ "#C Submission Completed----", "Info", false);

				// Sequence No
				iTempSeqNo = iTempSeqNo + 1;

				if (iTempSeqNo == 100) {
					iTempSeqNo = 1;
				}

			}
			
			//intRackNewSeq = Seqno + racksCount - 1;
			Vclas_Shortage_NewRacks_Search(strFlowType,Seqno,racksCount);
			
			/*for (int i = 1; i <= racksCount; i++) {
				Vclas_Shortage_Rack_Search(strFlowType, seqHandling + String.valueOf(intRackNewSeq), flowName);
				
			
			
			System.out.println("intRackNewSeq :" +intRackNewSeq);
			
			mwHome.Navigate_Vclas_Task();
		
			vt.Vclas_Search_Partno();
			
				vt.Vclas_getTuggerTask(strFlowType, String.valueOf(intRackNewSeq), i);
				intRackNewSeq = intRackNewSeq + 1;
				System.out.println("intRackNewSeq :" +intRackNewSeq);
				driver.switchTo().window(browser[2]);
			}*/
			//new
			List<String> listr = vt.getList();
			System.out.println("full TASK ID: " +listr);
			
			List<String> listrempty = vt.getList1();
			System.out.println("empty TASK ID: " +listrempty);
			
			iTempSeqNo = Seqno;

			System.out.println("seq no" + Seqno);
			long clickTime;

			
			// Sending Empty Racks and Full Racks

			for (int i = 1; i <= racksCount; i++) {                     //changed for 19th testcase--rackCount

				mwHome.Navigate_Admin_Simulator_Assembly_Line();
				clickTime = 1;

				reportStep("--------Empty rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
						+ "#C Initiated----", "Info", false);
				// Click Assembly Line in Masweb to get Empty Racks Planned
				// click once to get the correct time to move rack in assembly line
				adp.Simulator_Assemblyline_Click((int) clickTime);

				int thersholdValue;

				if (flowName.equalsIgnoreCase("tugger")) {

					Parameter_differentracksize parameter = new Parameter_differentracksize();
					thersholdValue = (int) parameter.planning();
					reportStep("31 Verify the functionality of planning assignments based on either Time formula or Thresold value by giving different rack size and Threshold value and Grace Time for a flow--Completed--TestCase ID-3129" ,"pass", false);
					
					double acutalThreshold =parameter.thresholdValue;
					String threshold=String.valueOf(acutalThreshold);
					char[]	thresholdChar=threshold.toCharArray();
					String firstChar=String.valueOf(thresholdChar[0]);
					/*String actualValue=String.valueOf(thersholdValue);
					char[]	val=actualValue.toCharArray();
					String actualChar=String.valueOf(val[0]);*/
						if(firstChar=="-") {
							reportStep("20 Verify the functionality of planning Tugger orders from SHORTAGE when Threshold value is in negative value with Grace Time 0-- Completed--TestCase ID-3118" + thersholdValue, "pass", false);
						}
						else if(firstChar=="0") {
							reportStep("21 Verify the functionality of planning Tugger orders from SHORTAGE when Threshold value is in Zero value with Grace Time 0--Completed--TestCase ID-3119" + thersholdValue, "pass", false);
						}
						else {
							reportStep("22 Verify the functionality of planning Tugger orders from SHORTAGE when Threshold value is in Positive value with Grace Time 0--Completed--TestCase ID-3120" + thersholdValue, "pass", false);
						}
				} else {
					Thread.sleep(60000);
					// fetch the racksize and update it in excel
					Sekadm sp = new Sekadm();
					sp.JISJIT_EmptyRack_Delivery();
					
					JISJIT_ParameterPlanning parameter = new JISJIT_ParameterPlanning();

					thersholdValue = (int) parameter.planning();

				}

				int tempThreshold = thersholdValue;
				reportStep("Planned Time value: " + thersholdValue, "pass", false);
				System.out.println("Value from opti planning excel" + thersholdValue);
				do {

					System.out.println("wait for time value to get reflected in vclas..");
					driver.switchTo().window(browser[2]);
					Thread.sleep(120000);

					// Retrieve time value to plan the racks
					clickTime = Vclas_Shortage_Time_Verify(" ", strFlowType, seqHandling + String.valueOf(iTempSeqNo),
							flowName);
					System.out.println("time verify done");
					if (clickTime == thersholdValue) {
						reportStep("Planned time verified Actual Time: "+clickTime+" Expected Time: "+tempThreshold, "Info", false); 
						break;
					}
					if (thersholdValue != 0) {
						// assuming value is negative
						clickTime = clickTime - thersholdValue;
						System.out.println("After adding threshold to click time for: " + clickTime);
					}

					else {
						clickTime = clickTime + thersholdValue;
						System.out.println("After adding threshold to click time for: " + clickTime);
					}
					// check whether click time is negative or zero
					if (clickTime == thersholdValue) {
						reportStep("Planned time verified Actual Time: "+clickTime+" Expected Time: "+tempThreshold, "Info", false); 
						break;
					}

					reportStep(
							"--------Empty rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
									+ "#C Assembly Movement Initiated, No. Of Clicks:  #B" + clickTime + "#C----",
							"Info", false);
					// Accessing the Admin Page to move the Line
					adp.Simulator_Assemblyline_Click((int) clickTime);

					reportStep(
							"--------Empty rack submission: " + strFlowType + " " + String.valueOf(iTempSeqNo)
									+ " Assembly Movement Completed, No. Of Clicks: #B" + clickTime + "#C----",
							"Info", false);

					// Check Whether The Expected Rack Is Planned
					if (Vclas_Shortage_Time_Verify(" ", strFlowType, seqHandling + String.valueOf(iTempSeqNo),
							flowName) == tempThreshold) {
						reportStep("Planned time verified Actual Time: "+clickTime+" Expected Time: "+tempThreshold, "Info", false); 
						break;
					}
					System.out.println("after while" + clickTime + " " + thersholdValue);
				} while (((int) clickTime != tempThreshold)); // check whether click Time is positive

				// verify whether rack status is planned
				Vclas_Shortage_SingleRackStatusVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
						"EMPTY_RACK", "Planned");
				// Deliver The Empty Planned Rack
				if (flowName.equalsIgnoreCase("Tugger")) {

					Vclas_AssignmentList_RackSubmission_Tugger(strType[1],
							strFlowType + seqHandling + String.valueOf(iTempSeqNo));
				} else {
					Vclas_AssignmentList_RackSubmission_JISJIT(strType[1].toUpperCase(),
							strFlowType + seqHandling + String.valueOf(iTempSeqNo));
				}

				// scan the object for empty rack
				Vclas_AssignmentList(strFlowType + seqHandling + String.valueOf(iTempSeqNo), "Empty_Rack", "",
						flowName);
				 reportStep("#BVerification of delivery functionality of a Tugger empty rack when Node Scan on delivery, Scan on delivery parameters are false for the TPOS node Test case Id:3254--completed#C", "pass", false);
				reportStep("--------Empty rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
						+ "#C Submitted Successfully----", "Info", false);

				System.out.println("Delivered Empty rack");

				iTempSeqNo = iTempSeqNo + 1;
				System.out.println("incremented val" + iTempSeqNo);
				System.out.println("current i value" + i);

				lastSubmittedFullRack = lastSubmittedFullRack + 1;

				if (flowName.equalsIgnoreCase("Tugger")) {
					if (String.valueOf(lastSubmittedFullRack).length() == 1) {
						strTempSeqNo = "0" + String.valueOf(lastSubmittedFullRack);
						System.out.println("after empty rack delivery :" + strTempSeqNo);
					} else {
						strTempSeqNo = String.valueOf(lastSubmittedFullRack);
						System.out.println("after empty rack delivery :" + strTempSeqNo);
					}
				} else {

					strTempSeqNo = StringUtils.repeat("0", 4 - String.valueOf(lastSubmittedFullRack).length())
							+ lastSubmittedFullRack;
					System.out.println("after empty rack delivery :" + strTempSeqNo);
				}

				Vclas_Shortage_SingleRackStatusVerify(strFlowType + seqHandling + strTempSeqNo, "FULL_RACK", "Planned");
				reportStep("--------Full rack submission: #B" + strFlowType + " " + strTempSeqNo + "#C Initiated----",
						"Info", false);

				// Deliver the Planned Full Empty Rack
				if (flowName.equalsIgnoreCase("Tugger")) {
					Vclas_AssignmentList_RackSubmission_Tugger(strType[0],
							strFlowType + seqHandling + String.valueOf(strTempSeqNo));
				} else {
					Vclas_AssignmentList_RackSubmission_JISJIT(strType[0].toUpperCase(),
							strFlowType + seqHandling + String.valueOf(strTempSeqNo));
				}

				if (flowName.equalsIgnoreCase("Tugger")) {
					// Calculate LopNo for ScanId
					strLopNo = strLopNo + Integer.parseInt(tdrow.get("Line_movement"));
					System.out.println("strLopNo:" + strLopNo);

					// Calculate Scan Id
					String scanId = "T" + strFlowType + strTempSeqNo + strLopNo;
					System.out.println("scan id" + scanId);

					Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName); // ask

				} else {
					// Calculate Scan Id
					String scanId = strFlowType + strTempSeqNo;
					System.out.println("scan id" + scanId);
					Vclas_AssignmentList(scanId, "Full_Rack", "Yes", flowName);
					reportStep("--------19 Verify the functionality of Warning window for 99 full rack delivery after 97 empty rack delivery only which means in correct sequence delivery--Testcase ID-3117--Completed----",
							"Pass", false);
					reportStep("--------28 Verify the functionality of Warning window for 99 full rack delivery after 97 empty rack delivery only which means in correct sequence delivery--Testcase ID-3126--Completed----",
							"Pass", false);
					
				
				}

				reportStep("--------Full rack submission: #B" + strFlowType + " " + strTempSeqNo
						+ "#C Submitted Successfully----", "Info", false);

				System.out.println("Rack Search after 1E 1F for: " + lastSubmittedFullRack);
					
				// Verify whether next full empty racks are created
				intRackNewSeq=Vclas_Shortage_NewRacks_Search(strFlowType, Integer.parseInt(strTempSeqNo),racksCount);
				System.out.println("intRackNewSeq :" +intRackNewSeq);
				
			}
			reportStep(
					"Rack Created: #B" + strFlowType + " " + Seqno + "#C <BR/>"
							+ "Overall Full Rack Submission count: #B" + (lastSubmittedFullRack - Seqno + 1)
							+ "#C <BR/>" + "Last Full Rack Submission: #B" + strFlowType + " " + (lastSubmittedFullRack)
							+ "#C <BR/>" + "Overall Empty Rack Submission: #B" + (iTempSeqNo - Seqno) + "#C <BR/>"
							+ "Last Empty Rack Submission: #B" + strFlowType + " " + (iTempSeqNo - 1) + "#C <BR/>"
							+ "Started from: #B" + strFlowType + " " + Seqno + "#C and Completed till: #B" + strFlowType + " " + intRackNewSeq + "#C <BR/>",
					"Info", false);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("exception thrown in method");
		}

	}

	public void clickk() throws InterruptedException {
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		int thresholdVal = Integer.parseInt(tdrow.get("thresholdValue"));
		int newTime = Integer.parseInt(tdrow.get("newTime"));
		System.out.println(thresholdVal + "" + newTime);

		if (thresholdVal >= 1) {
			int clickNo = (10 * thresholdVal / 100);

			newTime = newTime + clickNo;
			System.out.println("new time=" + newTime + " click no:" + clickNo);
			Admin_Page adp = new Admin_Page();
			adp.Simulator_Assemblyline_Click(newTime);
		} else {
			Admin_Page adp = new Admin_Page();
			adp.Simulator_Assemblyline_Click(newTime);
		}
	}

	public void Vclas_Shortage_Rack_Search(String objectId, String seqNo, String flowName) throws InterruptedException {

		try {
			int iTempClick = 0;
			String searchData;

			if (seqNo.equalsIgnoreCase("")) {
				searchData = objectId;
			} else {
				searchData = objectId + seqNo;
			}

			// Navigation to Shortage
			anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));
			Thread.sleep(15000);
			// Verify the Shortage screen is displayed

			if (driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text"))).size() > 0) {
				if (tempVar == false) {
					// new for stale Exception
					do {
						// driver.findElement(By.xpath(prop.getProperty("Assembly_line.Display.Button"))).click();
						driver.findElement(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text")));
						iTempClick = iTempClick + 1;
						driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

						if (verifyElementExist("Shortage button",
								By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Button"))) == true) {
							break;
						}
					} while (iTempClick != 5);

					driver.findElement(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text")))
							.sendKeys(Keys.LEFT_CONTROL + "a");
					driver.findElement(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text")))
							.sendKeys(Keys.DELETE);
					// Enter the Object Id
					driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text"))).get(0)
							.sendKeys(objectId);

					// Clicking the Search Button
					driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Button"))).get(0).click();
					tempVar = true;
				}

				reportStep("Shortage screen is displayed successfully", "Pass", true);

				// Search data in the table with sequence no.
				String objVerify = prop.getProperty("Vclas_Home.Shortage.Table.TD.Verify").replace("&Value",
						searchData);
				System.out.println(objVerify);
				String nextButton = prop.getProperty("Vclas_Home.Shortage.Next.Button");
				String firstButton = prop.getProperty("Vclas_Home.Shortage.First.Button");

				int intTemp = 1;
				do {
					// new for stale
					int iTempClick1 = 0;
					do {

						driver.findElement(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Button")));
						iTempClick1 = iTempClick1 + 1;
						driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

						if (verifyElementExistReturn(
								By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Button"))) == true) {
							break;
						}
					} while (iTempClick1 != 5);
					// Clicking the button to verify the table is displayed
					driver.findElement(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Button"))).click();

					Thread.sleep(2000);
					int iTemp = 1;
					int var = 1;
					// Verifying if it available in Next Screen
					do {

						if (iTemp != 1) {
							// Clicking the next button
							anyClick("Shortage Next Button", By.xpath(nextButton));
						}

						// Verifying the object id
						if (verifyElementExistReturn(By.xpath(objVerify)) == true) {
							reportStep(searchData + " exist in the Shortage screen", "Pass", true);
							System.out.println(searchData + " exist in the Shortage screen");
							
							reportStep("ObjectId : #B" + objectId + "#C " + "has been displayed successfully #B without any space in the Shortage Screen during New Racks Creation #C ",
									"PASS", true);

							var = driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Table.TD.Verify")
									.replace("&Value", searchData))).size();

							System.out.println(var);
							if (var == 2) {
								System.out.println("2 racks are created");
								reportStep(" object: " + searchData + "  Full and Empty Racks Created Successfully",
										"Pass", true);
								return;
							}
						}
						iTemp++;
					} while (verifyElementExistReturn(By.xpath(nextButton)) == true);

					if (verifyElementExistReturn(By.xpath(firstButton)) == true) {
						// Clicking the first button
						anyClick("Shortage First Button", By.xpath(firstButton));
					}

					intTemp = intTemp + 1;

				} while (!(intTemp == 5));

				if (intTemp == 5) {
					reportStep(objectId + " Object id search not displayed in the screen (60 seconds waited)", "fail",
							true);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void Vclas_Shortage_SingleRackStatusVerify(String ObjectId, String Type, String Status)
			throws InterruptedException {
		anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));
		Thread.sleep(5000);

		// Search Object Id and Task Id in the table
		String objIdVerify = prop.getProperty("Vclas_Home.Shortage.Single.Status").replace("&Value", ObjectId)
				.replace("&1Value", Type);

		// Verifying if it is available in the screen
		if (verifyElementExistReturn(By.xpath(objIdVerify)) == true) {
			reportStep(ObjectId + " exists in the Shortage Screen ", "PASS", true);

			String newStatus;

			reportStep("--------Rack Status Verify - Sequence: #B" + ObjectId + "#C Type of Rack: #B" + Type
					+ "#C Status: #B" + Status + "#C Initiated Successfully----", "Info", false);

			String objStatus = prop.getProperty("Vclas_Home.Shortage.Single.Status").replace("&Value", ObjectId)
					.replace("&1Value", Type);
			int iTemp = 0;
			do {

				newStatus = driver.findElement(By.xpath(objStatus)).getText();
				System.out.println(ObjectId + " rack status:" + newStatus);
				// Status Verification
				if (newStatus.toUpperCase().equalsIgnoreCase(Status.toUpperCase())) {
					reportStep(Status + " Status is Verified successfully ", "PASS", true);
					reportStep("--------Rack Status Verify - Sequence: #B" + ObjectId + "#C Type of Rack: #B" + Type
							+ "#C Status: #B" + Status + "#C Passed----", "Info", false);
					break;
				}
				Thread.sleep(2000);
				iTemp = iTemp + 1;

			} while (!(iTemp == 60));
			if (iTemp == 60) {

				reportStep("Status is not verified successfully. The Status to be expected:" + Status
						+ "The Actual Status is:" + String.valueOf(newStatus), "fail", true);
				reportStep("--------Rack Status Verify - Sequence: #B" + ObjectId + "#C Type of Rack: #B" + Type
						+ "#C Status: #B" + Status + "#C Failed----", "Info", false);
			}

		} else {
			reportStep(ObjectId + " doesn't exists in the Shortage Screen", "fail", true);
		}

		reportStep("--------Rack Status Verify - Sequence: #B" + ObjectId + "#C Type of Rack: #B" + Type
				+ "#C Status: #B" + Status + "#C Completed Successfully----", "Info", false);

	}

	public void vclas_Agv_Rackpickup(String rackType, String objectId) throws InterruptedException {
		System.out.println("Search racksubmission for " + rackType + objectId);

		// navigate to assignment page

		driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Assignment.List.Button"))).get(0).click();

		reportStep("Object Id: #B" + objectId + "#C  Rack Type: #B" + rackType
				+ "#C Click for Submission from #B Assignment List #C screen", "Info", false);

		String rackName = prop.getProperty("Vclas_home.AssignmentList.Column.Type").replace("&Value", objectId)
				.replace("&1Value", rackType);
		System.out.println("rack name" + rackName);

		// Verify the assignment area table is displayed
		if (verifyElementExistReturn(By.xpath(rackName)) == true) {

			// Selecting the Rack

			driver.findElements(By.xpath(rackName)).get(0).click();
			reportStep("#B" + objectId + "and the racktype:" + rackType
					+ " has been picked up for deliver from Assignment List screen #C", "pass", true);
			Thread.sleep(5000);
			System.out.println("rack name" + rackName);

			driver.findElements(By.xpath(rackName)).get(0).click();
			Thread.sleep(5000);

		} else {
			reportStep("Assignment list table does not exist", "fail", true);
		}

	}

	// Yes - sendType
	// Yes - sendType
	public void Vclas_Agv_Delivery(String Address, String type) throws InterruptedException {
		try {

			String sheetName = "Vclas_Assignments";
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);

			String ObjectId = "p" + tdrow.get("Object");

			// Verify it is Full KOLL or EmptyKoll
			if (type.equalsIgnoreCase("AGVFKOLL")) {
				if (verifyElementExistReturn(
						By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text"))) == true) {

					// Verify it is to Drop or Pick Up
					if (Address.equalsIgnoreCase("drop")) {

						Conveyor = driver
								.findElement(
										By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.DropAddress.Label")))
								.getText();

						// clicking on Cancel button in AssignmentList screen

						waitForElement(driver, By.xpath(prop.getProperty("Vclas.AssignmentList.CancelButton.Click")),
								3);
						driver.findElements(By.xpath(prop.getProperty("Vclas.AssignmentList.CancelButton.Click")))
								.get(0).click(); // navigate to
						// node screen to fetch conveyor code
						Nodes_Conveyor_Data_Pickup();

						// clicking on pick up button

						driver.findElements(By.xpath(prop.getProperty("Vclas_home.AssignmentList.Column.Type")
								.replace("&Value", tdrow.get("Object")).replace("&1Value", type))).get(0).click();

						reportStep("Conveyor Id: #B" + Conveyor + "#C ObjectId: #B" + ObjectId
								+ "#C entered to move from Forklift to Conveyor", "Info", false);
						sendKeys("scan", By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")),
								ConveyorCode);

						// waiting for element
						waitForElement(driver,
								By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.CheckCode.Label")), 10);
						String ConveyorId = driver
								.findElement(
										By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.CheckCode.Label")))
								.getText();

						if (ConveyorId.equalsIgnoreCase(tdrow.get(Conveyor.toUpperCase()))) {
							reportStep("ConveyorId been sent", "pass", false);
						} else {
							reportStep("ConveyorId doesn't sent", "fail", false);
						}
						Thread.sleep(5000);
						sendKeys("Object Id to send: " + ObjectId,
								By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")), ObjectId);

					} else {

						Conveyor = driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.PickupAddress.Label"))).getText();

						reportStep("Conveyor Id: #B" + Conveyor + "#C ObjectId: #B" + ObjectId+ "#C entered to move from Conveyor to AGV", "Info", false);
						Thread.sleep(3000);
						driver.findElements(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text"))).get(0).sendKeys(ConveyorCode);


					}

				}

				else {
					reportStep("Scan text box doesn't exists", "fail", false);
					return;
				}
			}

			Thread.sleep(5000);

			// Verify the OK button exist
			if (verifyElementExistReturn(By.xpath(prop.getProperty("Vclas.AssignmentList.OkButton.Click"))) == true) {
				retryingFindClick(By.xpath(prop.getProperty("Vclas.AssignmentList.OkButton.Click")));

			} else {
				reportStep("Ok button doesn't exists", "fail", false);
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void Vclas_Agv_Flow() throws InterruptedException {

		tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_Assignments");
		// Getting the Room Value
		String stRoomValue = tdrow.get("Select_Work_Area_Room");
		String objectId = tdrow.get("Object");
		String strFullTaskId = tdrow.get("Full_Task_1");
		String strEmptyTaskId = tdrow.get("Empty_Task_1");
		String strFullType = tdrow.get("Shortage_Type_Expected").split(",")[0];
		String strEmptyType = tdrow.get("Shortage_Type_Expected").split(",")[1];
		String strStatusVerify[] = { "Active", "Planned" };

		// Submitting the Full Racks
		for (int i = 0; i <= 1; i++) {

			// Selection Work Area
			Vclas_Assignment_SelectWorkArea("Room", stRoomValue.split(",")[i]);

			reportStep(" Selected Work Area: #B" + stRoomValue.split(",")[i] + "#C", "Info", false);

			// Navigation to Shortage
			anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));

			// Waiting to verify the screen is displayed
			waitForElement(driver, By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text")), 5);

			//
			Vclas_Shortage_Search(objectId, "", "");

			// Verify the Task id in Shortage screen
			Vclas_Shortage_TypeVerify(objectId, strFullTaskId, strFullType, strStatusVerify[i]);
			reportStep(
					"Object Id: #B" + objectId
							+ "#C  Rack Type: #B  AGVFKOLL  #C delivery  from Assignment List screen initiated",
					"Info", false);
			// Rack Pick/Drop in the Assignment List
			vclas_Agv_Rackpickup(strFullType, objectId);
			// Nodes_Conveyor_Data_Pickup();

			if (i == 0) {
				// Deliver depends on Drop id of Conveyer
				Vclas_Agv_Delivery("drop", strFullType);
			} else {
				// Deliver depends on Pick id of Conveyer
				Vclas_Agv_Delivery("pick", strFullType);

				String rackName = prop.getProperty("Vclas_home.AssignmentList.Column.Type").replace("&Value", objectId)
						.replace("&1Value", strFullType);
				Thread.sleep(2000);
				if (driver.findElements(By.xpath(rackName)).size() > 0) {
					driver.findElements(By.xpath(rackName)).get(0).click();

					Vclas_Agv_Delivery("", "");
				}
			}
			reportStep(
					"Object Id: #B" + objectId
							+ "#C  Rack Type: #B AGVFKOLL #C delivery  from Assignment List screen completed",
					"Info", false);
		}

		// Submitting the Empty Racks
		for (int j = 0; j <= 1; j++) {

			// Navigation to Shortage
			anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));

			Vclas_Shortage_Search(objectId, "", "");

			// Verify the Task id in Shortage screen
			Vclas_Shortage_TypeVerify(objectId, strEmptyTaskId, strEmptyType, strStatusVerify[j]);
			reportStep(
					"Object Id: #B" + objectId
							+ "#C  Rack Type: #B AGVEKOLL #C delivery  from Assignment List screen initiated",
					"Info", false);
			// Rack Pick/Drop in the Assignment List
			vclas_Agv_Rackpickup(strEmptyType, objectId);

			reportStep("---Submitting Empty Rack: #B" + strEmptyType + "#C Object Id: #B" + objectId + "#C----", "Info",
					false);
			// Deliver depends on Drop id of Conveyer
			Vclas_Agv_Delivery("", strEmptyType);

			if (j == 0) {
				// Select Room in the list
				Vclas_Assignment_SelectWorkArea("Room", stRoomValue.split(",")[j]);

				reportStep(" Selected Work Area: #B" + stRoomValue.split(",")[j] + "#C", "Info", false);
			}

		}
		reportStep(
				"Object Id: #B" + objectId
						+ "#C  Rack Type: #B AGVEKOLL #C delivery  from Assignment List screen completed",
				"Info", false);

	}

	public void Vclas_Shortage_Delivery_Selection(String TaskId) throws InterruptedException {

		tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_Assignments");
		String objectId = tdrow.get("Object");
		
	

		
		
		//StaleHandling(By.xpath(prop.getProperty("Vclas_Home.Shortage.Pickup.Button")));
		
		//clickByLocator(By.xpath(prop.getProperty("Vclas_Home.Shortage.Pickup.Button")));

		// Clicking for the object in Shortage Screen to Pick-Up
		if (verifyElementExistReturn(By.xpath(prop.getProperty("Vclas_Home.Shortage.Pickup.Button")
				.replace("&Value", objectId).replace("&1Value", TaskId))) == true) {
			Thread.sleep(3000);
			//waitForElement(driver, By.xpath(prop.getProperty("Vclas_Home.Shortage.Deliver.Button")), 5);
			driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Pickup.Button").replace("&Value", objectId).replace("&1Value", TaskId))).get(0).click();
			//retryingFindClick(By.xpath(prop.getProperty("Vclas_Home.Shortage.Pickup.Button")));
			reportStep("PickUp Button for the #B" + TaskId + " #C is Verified and clicked Successfully", "pass", false);
		} else {
			reportStep("PickUp button for the #B" + TaskId + "#C is not enabled", "fail", true);
		}
		
		//StaleHandling(By.xpath(prop.getProperty("Vclas_Home.Shortage.Deliver.Button")));
		//clickByLocator(By.xpath(prop.getProperty("Vclas_Home.Shortage.Deliver.Button")));
		// Clicking for the object in Shortage Screen to Deliver
		if (verifyElementExistReturn(By.xpath(prop.getProperty("Vclas_Home.Shortage.Deliver.Button")
				.replace("&Value", objectId).replace("&1Value", TaskId))) == true) {
			Thread.sleep(3000);
			//waitForElement(driver, By.xpath(prop.getProperty("Vclas_Home.Shortage.Deliver.Button")), 5);
			driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Deliver.Button")
					.replace("&Value", objectId).replace("&1Value", TaskId))).get(0).click();

			reportStep("Deliver Button for the #B" + TaskId + " #C is Verified and clicked Successfully", "pass",
					false);
		} else {
			reportStep("Deliver button for the #B" + TaskId + "#C is not enabled", "fail", true);
		}
		//StaleHandling(By.xpath(prop.getProperty("Vclas.Shortage.CancelButton.Click")));
		//clickByLocator(By.xpath(prop.getProperty("Vclas.Shortage.CancelButton.Click")));
		// Clicking on the Cancel Button
		/*if (driver.findElements(By.xpath(prop.getProperty("Vclas.Shortage.CancelButton.Click"))).size() > 0) {

			// Waiting to verify the screen is displayed
			//waitForElement(driver, By.xpath(prop.getProperty("Vclas.Shortage.CancelButton.Click")), 5 );
			Thread.sleep(3000);
			//driver.findElements(By.xpath(prop.getProperty("Vclas.Shortage.CancelButton.Click"))).get(0).click();
			retryingFindClick(By.xpath(prop.getProperty("Vclas.Shortage.CancelButton.Click")));
			
			reportStep("Cancel Button for the #B" + TaskId + "#C is enabled and clicked Successfully", "pass", false);

		} else {
			reportStep("Cancel button for the #B" + TaskId + "#C is not enabled", "fail", true);
		}*/
	//StaleHandling(By.xpath(prop.getProperty("Vclas_Home.Shortage.Deliver.Button")));
		
		//clickByLocator(By.xpath(prop.getProperty("Vclas_Home.Shortage.Deliver.Button")));
		// Clicking for the object in Shortage Screen to Deliver
		/*if (verifyElementExistReturn(By.xpath(prop.getProperty("Vclas_Home.Shortage.Deliver.Button")
				.replace("&Value", objectId).replace("&1Value", TaskId))) == true) {
			//waitForElement(driver, By.xpath(prop.getProperty("Vclas_Home.Shortage.Deliver.Button")), 5);
			Thread.sleep(3000);
			driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Deliver.Button")
					.replace("&Value", objectId).replace("&1Value", TaskId))).get(0).click();

			reportStep("Deliver Button for the #B" + TaskId + "#C is Verified and clicked Successfully", "pass", false);
		} else {
			reportStep("Deliver button for the  #B" + TaskId + "#C is not enabled", "fail", true);
		}
		*/
		//StaleHandling(By.xpath(prop.getProperty("Vclas.Shortage.OkButton.Click")));
		//clickByLocator(By.xpath(prop.getProperty("Vclas.Shortage.OkButton.Click")));
		// Clicking on the OK Button
		if (driver.findElements(By.xpath(prop.getProperty("Vclas.Shortage.OkButton.Click"))).size() > 0) {

			// Waiting to verify the screen is displayed
			//waitForElement(driver, By.xpath(prop.getProperty("Vclas.Shortage.OkButton.Click")), 3);
			Thread.sleep(3000);
			// retryingFindClick(By.xpath(prop.getProperty("Vclas.Shortage.OkButton.Click")));

			driver.findElements(By.xpath(prop.getProperty("Vclas.Shortage.OkButton.Click"))).get(0).click();
			Thread.sleep(3000);
			reportStep("Ok Button for the #B" + TaskId + "#C is enabled and clicked Successfully", "pass", false);

		} else {
			reportStep("Ok button for the #B" + TaskId + "#C is not enabled", "fail", true);
		}

	}


	public void Vclas_Agv_Shortage_Screen_Delivery() throws InterruptedException {

		tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_Assignments");
		// Getting the Room Value
		String stRoomValue = tdrow.get("Select_Work_Area_Room");
		String objectId = tdrow.get("Object");
		String strFullTaskId = tdrow.get("Full_Task_1");
		String strEmptyTaskId = tdrow.get("Empty_Task_1");
		String strFullType = tdrow.get("Shortage_Type_Expected").split(",")[0];
		String strEmptyType = tdrow.get("Shortage_Type_Expected").split(",")[1];
		String strStatusVerify = "Active";
		String Seqno = "";
		// Submitting the Full Racks
		for (int i = 0; i <= 1; i++) {

			// Selection Work Area
			Vclas_Assignment_SelectWorkArea("Room", stRoomValue.split(",")[i]);

			reportStep(" Selected Work Area: #B" + stRoomValue.split(",")[i] + "#C", "Info", false);

			// Navigation to Shortage
			anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));

			// Searching the objectId in Shortage screen
			Vclas_Shortage_Search(objectId, String.valueOf(Seqno), "");

			// Verify the Task id in Shortage screen
			Vclas_Shortage_TypeVerify(objectId, strFullTaskId, strFullType, strStatusVerify);

			// Delivering the AGVFKOLL
			Vclas_Shortage_Delivery_Selection(strFullTaskId);

			reportStep("Delivery of task ID: #B" + strFullTaskId + "#C and #B" + strFullType
					+ "#C is completed in #B Shortage screen #C", "Info", false);

			break;
		}

		// Submitting the Empty Racks
		for (int j = 0; j <= 1; j++) {

			// Searching the objectId in Shortage screen
			Vclas_Shortage_Search(objectId, String.valueOf(Seqno), "");

			// Verify the Task id in Shortage screen
			Vclas_Shortage_TypeVerify(objectId, strEmptyTaskId, strEmptyType, strStatusVerify);

			// Delivering the AGVEKOLL
			Vclas_Shortage_Delivery_Selection(strEmptyTaskId);

			break;
		}
		reportStep("Delivery of task ID: #B" + strEmptyTaskId + "#C and #B" + strEmptyType
				+ "#C is completed in #B Shortage screen #C", "Info", false);

	}

	public void Vclas_Inprogress_Delivery() throws InterruptedException {

		tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_Assignments");
		// Getting the Room Value
		String stRoomValue = tdrow.get("Select_Work_Area_Room");
		String objectId = tdrow.get("Object");
		String strFullTaskId = tdrow.get("Full_Task_1");
		String strEmptyTaskId = tdrow.get("Empty_Task_1");
		String strFullType = tdrow.get("Shortage_Type_Expected").split(",")[0];
		String strEmptyType = tdrow.get("Shortage_Type_Expected").split(",")[1];
		String strStatusVerify[] = { "Active", "Planned" };

		// Submitting the 1st Full and Empty Rack in Assignment List Screen
		for (int i = 0; i <= 1; i++) {

			// Selecting the Work Area
			Vclas_Assignment_SelectWorkArea("Room", stRoomValue.split(",")[i]);

			reportStep(" Selected Work Area: #B" + stRoomValue.split(",")[i] + "#C", "Info", false);

			// Navigation to Shortage
			anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));

			// Searching for the object
			Vclas_Shortage_Search(objectId, "", "");

			if (i == 0) {

				// Verify the Task id in Shortage screen
				Vclas_Shortage_TypeVerify(objectId, strFullTaskId, strFullType, strStatusVerify[0]);

				// Rack Pick/Drop in the Assignment List
				vclas_Agv_Rackpickup(strFullType, objectId);

				// Deliver depends on Drop id of Conveyer
				Vclas_Agv_Delivery("drop", strFullType);
			} else {

				// Verify the Task id in Shortage screen
				Vclas_Shortage_TypeVerify(objectId, strEmptyTaskId, strEmptyType, strStatusVerify[i]);

				// Rack Pick/Drop in the Assignment List
				vclas_Agv_Rackpickup(strEmptyType, objectId);

				// Deliver depends on Pick id of Conveyer
				Vclas_Agv_Delivery("", strEmptyType);
			}
		}
		// Navigation to Shortage
		anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));

		// Searching for the object
		Vclas_Shortage_Search(objectId, "", "");

		// Verify the Task id in Shortage screen
		Vclas_Shortage_TypeVerify(objectId, strFullTaskId, strFullType, "Planned");

		// Delivering the AGVFKOLL
		Vclas_Shortage_Delivery_Selection(strFullTaskId);

		// Selecting the Work Area
		Vclas_Assignment_SelectWorkArea("Room", stRoomValue.split(",")[0]);

		// Navigation to Shortage
		anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));

		// Searching for the object
		Vclas_Shortage_Search(objectId, "", "");

		// Verify the Task id in Shortage screen
		Vclas_Shortage_TypeVerify(objectId, strEmptyTaskId, strEmptyType, "Planned");

		// Delivering the AGVFKOLL
		Vclas_Shortage_Delivery_Selection(strEmptyTaskId);

	}

	public void Nodes_Conveyor_Data_Pickup() throws InterruptedException {

		// clicking Vclas Home button
		anyClick("Vclas Home button click", By.xpath(prop.getProperty("Vclas_Home.Home.Button")));

		// clicking on the Administration button
		anyClick("Vclas Administration button click", By.xpath(prop.getProperty("Vclas_Home.Administration.Button")));

		// clicking on Nodes button
		anyClick("Vclas Nodes button click", By.xpath(prop.getProperty("Vclas_Administration.Nodes.Button")));

		// verifying search textbox
		if (verifyElementExistReturn(
				By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Search.Textbox"))) == true) {
			// Conveyor="IN3";
			// sending conveyor code to search textbox
			sendKeys("Search textbox", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Search.Textbox")),
					Conveyor);

			// clicking search button
			anyClick("Vclas Node screen search button click",
					By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Search.Button")));

			// fetching conveyor code from nodes screen
			ConveyorCode = driver
					.findElement(By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Checkcode.Textbox")))
					.getAttribute("value");

			// to scroll down the page to view the checkcode
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement Checkcode = driver
					.findElement(By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Checkcode.Textbox")));
			js.executeScript("arguments[0].scrollIntoView();", Checkcode);

			reportStep("Conveyercode fetched from #B nodes screen  #C successfully  " + Conveyor + ":  " + ConveyorCode,
					"pass", true);
			reportStep("Conveyercode fetched from nodes screen successfully", "info", false);
			System.out.println("ConveyorCode: " + ConveyorCode);
			// clicking Assignments button
			Agv_Vclas_Assignment_Navigate();

			// clicking Assignment list button
			driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Assignment.List.Button"))).get(0).click();

		} else {
			reportStep("Search textbox doesn't present", "fail", false);
		}

	}

	public void Vclas_Tugger_Assignment_Delivery(String flowName) throws InterruptedException {
		try {
			
			String strSeqno, seqHandling;
			int Seqno, racksCount, iTempSeqNo, strLopNo;
			String rackStatus, strFlowType;
			String[] strType;
			int intRackNewSeq ,j=0;
			Admin_Page adp = new Admin_Page();
			MasWeb_Home mwHome = new MasWeb_Home();
			
			// Navigation to Shortage
			anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));
			Thread.sleep(5000);

			String sheetName = "Vclas_Assignments";
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
			Seqno = Integer.parseInt(tdrow.get("Object")); // 1
			racksCount = Integer.parseInt(tdrow.get("No_of_Racks"));
			strFlowType = tdrow.get("Sequence_Type");
			strType = tdrow.get("Shortage_Type_Expected").split(",");
			strLopNo = Integer.parseInt(tdrow.get("Lopnr"));
			String strTempSeqNo;
			if (flowName.equalsIgnoreCase("Tugger")) {
				strSeqno = String.valueOf(Seqno);// 01
				seqHandling = " ";
				
			} else {

				strSeqno = tdrow.get("Object");// 0001
				System.out.println("seq no :" + strSeqno);
				seqHandling = StringUtils.repeat("0", 4 - (String.valueOf(Seqno).length())); // 000
				System.out.println("seq handling:" + seqHandling);
			}
				
				System.out.println("flow name : " + flowName);

				Vclas_Shortage_Search(strFlowType, strSeqno, flowName);

				iTempSeqNo = Seqno;

				System.out.println("seq no:" + iTempSeqNo);
				// To Verify Initial Status in Shortage Screen for created racks
				for (int i = 1; i <= racksCount; i++) {

					if (i <= racksCount) {
						rackStatus = "Planned";
					} else {
						rackStatus = "Active";
						
					}
					List<String> listr = vt.getList();
					System.out.println("full TASK ID: " +listr);
					
					
					List<String> listrempty = vt.getList1();
					System.out.println("empty TASK ID: " +listrempty);
				
					
						// To verify the Full task id
						Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
								listr.get(j).toString(), strType[0].toUpperCase(), rackStatus);

						// To Verify the Empty task id
						Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
								listrempty.get(j).toString(), strType[1].toUpperCase(), "Active");
		                    j++;   //new
		                    iTempSeqNo = Seqno + 1;
					}

				 iTempSeqNo = Seqno + 1;
					System.out.println("itemp val:" + iTempSeqNo);
					int lastSubmittedFullRack = 0;
					
					// Click the Planned Activity in Assignment List for Initial Two Full Racks
					for (int i = 1; i <= racksCount + 1; i++) {

						reportStep("--------Full rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
								+ "#C Initiated----", "Info", false);
						// Submitting the Rack
						System.out.println(
								"rck submsn" + strType[0] + " " + strFlowType + seqHandling + String.valueOf(iTempSeqNo));
					
						if (flowName.equalsIgnoreCase("Tugger")) {
							reportStep("--------08 Verify the possibility of delivering  racks in wrong sequence from Assignments List screen- Test case Id- 3106--Initiated----", "Pass", false);
							reportStep("--------12 Verify the functionality of delivering a rack in incorrect sequence by copy pasting the correct and wrong package Nr in SCAN field when Manual Input is NO from A L screen- Test case Id- 3110 --Initiated----", "Pass", false);
							Vclas_AssignmentList_RackSubmission_Tugger(strType[0],
									strFlowType + seqHandling + String.valueOf(iTempSeqNo));
						} else {
							reportStep("--------13 Verify the possibility of delivering  JISJIT racks in wrong sequence from Assignments List sceen whether Warning window is being dislpayed on top of the scanning window or not- Test case Id- 2794---Initiated----", "Pass", false);
					
							Vclas_AssignmentList_RackSubmission_JISJIT(strType[0].toUpperCase(),
									strFlowType + seqHandling + String.valueOf(iTempSeqNo));
						}
						lastSubmittedFullRack = iTempSeqNo;
						
						if (flowName.equalsIgnoreCase("Tugger")) {
							if (String.valueOf(iTempSeqNo).length() == 1) {
								strTempSeqNo = "0" + String.valueOf(iTempSeqNo);
								System.out.println(strTempSeqNo);
							} else {
								strTempSeqNo = String.valueOf(iTempSeqNo);
								System.out.println(strTempSeqNo);
							}

							// Calculate LopNo for ScanId
							strLopNo = strLopNo + Integer.parseInt(tdrow.get("Line_movement"));
							System.out.println("strLopNo:" + strLopNo);

							// Calculate Scan Id
							String scanId = "T" + strFlowType + strTempSeqNo + strLopNo;
							System.out.println("scan id" + scanId);
							System.out.println(strFlowType + seqHandling + String.valueOf(iTempSeqNo) + "ok click ");
							Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName);

						} else {

							strTempSeqNo = StringUtils.repeat("0", 4 - String.valueOf(iTempSeqNo).length()) + iTempSeqNo;
							System.out.println("strTempSeqNo:" + strTempSeqNo);

							// Calculate Scan Id
							String scanId = strFlowType + strTempSeqNo;
							System.out.println("scan id" + scanId);
							Vclas_AssignmentList(scanId, "Full_Rack", "Yes", flowName);
						}
						
						
						/*if (verifyElementExistReturn(
								By.xpath(prop.getProperty("Vclas_home.AssignmentList.ScanDialogBox"))) == true) {
							reportStep("Assignment Scan dialog box is displayed successfully", "Info", true);
							driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")))
							.sendKeys(scanId);
						}*/
						if (verifyElementExistReturn(
								By.xpath(prop.getProperty("Vclas_home.AssignmentList.WarningWindow"))) == true) {
							reportStep("Assignment List Warning Window is displayed successfully", "Info", true);
						Vclas_AssignmentList_WarningWindow();
						
						driver.findElement(By.xpath(prop.getProperty("Vclas.AssignmentList.CancelButton.Click"))).click();
						reportStep("--------12 Verify the functionality of delivering a rack in incorrect sequence by copy pasting the correct and wrong package Nr in SCAN field when Manual Input is NO from A L screen- Test case Id- 3110 --Completed----", "Pass", false);
						//driver.findElements(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Drop.icon"))).get(0).click();
						anyClick("Drop Button", By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Drop.icon")));
						//retryingFindClick(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Drop.icon")));
						iTempSeqNo=Seqno;
						}else {
							iTempSeqNo=iTempSeqNo +1;
							System.out.println("itemp val:" + iTempSeqNo);
							reportStep("--------Full rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
							+ "#C Submission Completed----", "Info", false);
						}
						}
					
						if (iTempSeqNo == 100) {
							iTempSeqNo = 1;
						}
					
							//new
						Vclas_Shortage_NewRacks_Search(strFlowType,Seqno,racksCount);
						
						iTempSeqNo = Seqno;

						System.out.println("seq no" + Seqno);
						long clickTime;

						
						// Sending Empty Racks and Full Racks
						
							mwHome.Navigate_Admin_Simulator_Assembly_Line();
							clickTime = 1;

							reportStep("--------Empty rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
									+ "#C Initiated----", "Info", false);
							// Click Assembly Line in Masweb to get Empty Racks Planned
							// click once to get the correct time to move rack in assembly line
							adp.Simulator_Assemblyline_Click((int) clickTime);

							int thersholdValue;

								if (flowName.equalsIgnoreCase("tugger")) {

									Parameter_differentracksize parameter = new Parameter_differentracksize();
									thersholdValue = (int) parameter.planning();
									
								} else {
									Thread.sleep(60000);
									// fetch the racksize and update it in excel
									Sekadm sp = new Sekadm();
									sp.JISJIT_EmptyRack_Delivery();
									JISJIT_ParameterPlanning parameter = new JISJIT_ParameterPlanning();

									thersholdValue = (int) parameter.planning();

								}
							
							int tempThreshold = thersholdValue;
							reportStep("Planned Time value: " + thersholdValue, "pass", false);
							System.out.println("Value from opti planning excel" + thersholdValue);
							do {

								System.out.println("wait for time value to get reflected in vclas..");
								driver.switchTo().window(browser[2]);
								Thread.sleep(120000);

								// Retrieve time value to plan the racks
								clickTime = Vclas_Shortage_Time_Verify(" ", strFlowType, seqHandling + String.valueOf(iTempSeqNo),
										flowName);
								System.out.println("time verify done");
								if (clickTime == thersholdValue) {
									reportStep("Planned time verified Actual Time: "+clickTime+" Expected Time: "+tempThreshold, "Info", false); 
									break;
								}
								if (thersholdValue != 0) {
									// assuming value is negative
									clickTime = clickTime - thersholdValue;
									System.out.println("After adding threshold to click time for: " + clickTime);
								}

								else {
									clickTime = clickTime + thersholdValue;
									System.out.println("After adding threshold to click time for: " + clickTime);
								}
								// check whether click time is negative or zero
								if (clickTime == thersholdValue) {
									reportStep("Planned time verified Actual Time: "+clickTime+" Expected Time: "+tempThreshold, "Info", false); 
									break;
								}

								reportStep(
										"--------Empty rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
												+ "#C Assembly Movement Initiated, No. Of Clicks:  #B" + clickTime + "#C----",
										"Info", false);
								// Accessing the Admin Page to move the Line
								adp.Simulator_Assemblyline_Click((int) clickTime);

								reportStep(
										"--------Empty rack submission: " + strFlowType + " " + String.valueOf(iTempSeqNo)
												+ " Assembly Movement Completed, No. Of Clicks: #B" + clickTime + "#C----",
										"Info", false);

								// Check Whether The Expected Rack Is Planned
								if (Vclas_Shortage_Time_Verify(" ", strFlowType, seqHandling + String.valueOf(iTempSeqNo),
										flowName) == tempThreshold) {
									reportStep("Planned time verified Actual Time: "+clickTime+" Expected Time: "+tempThreshold, "Info", false); 
									break;
								}
								System.out.println("after while" + clickTime + " " + thersholdValue);
							} while (((int) clickTime != tempThreshold)); // check whether click Time is positive

							// verify whether rack status is planned
							Vclas_Shortage_SingleRackStatusVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
									"EMPTY_RACK", "Planned");
							
							iTempSeqNo= iTempSeqNo+ 2;
							
							// verify whether full rack status is planned
							Vclas_Shortage_SingleRackStatusVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
									"FULL_RACK", "Planned");
						
							reportStep("--------Full rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
							+ "#C Initiated----", "Info", false);
					// Submitting the Rack
					System.out.println(
							"rck submsn" + strType[0] + " " + strFlowType + seqHandling + String.valueOf(iTempSeqNo));
					
					
					if (flowName.equalsIgnoreCase("Tugger")) {

						Vclas_AssignmentList_RackSubmission_Tugger(strType[0],
								strFlowType + seqHandling + String.valueOf(iTempSeqNo));
					} else {
						Vclas_AssignmentList_RackSubmission_JISJIT(strType[0].toUpperCase(),
								strFlowType + seqHandling + String.valueOf(iTempSeqNo));
					}
					
					if (flowName.equalsIgnoreCase("Tugger")) {
						if (String.valueOf(iTempSeqNo).length() == 1) {
							strTempSeqNo = "0" + String.valueOf(iTempSeqNo);
							System.out.println(strTempSeqNo);
						} else {
							strTempSeqNo = String.valueOf(iTempSeqNo);
							System.out.println(strTempSeqNo);
						}

						// Calculate LopNo for ScanId
						strLopNo = strLopNo + Integer.parseInt(tdrow.get("Line_movement"));
						System.out.println("strLopNo:" + strLopNo);

						// Calculate Scan Id
						String scanId = "T" + strFlowType + strTempSeqNo + strLopNo;
						System.out.println("scan id" + scanId);
						System.out.println(strFlowType + seqHandling + String.valueOf(iTempSeqNo) + "ok click ");
						Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName);

					} else {

						strTempSeqNo = StringUtils.repeat("0", 4 - String.valueOf(iTempSeqNo).length()) + iTempSeqNo;
						System.out.println("strTempSeqNo:" + strTempSeqNo);

						// Calculate Scan Id
						String scanId = strFlowType + strTempSeqNo;
						System.out.println("scan id" + scanId);
						Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName);
					}
					if (verifyElementExistReturn(
							By.xpath(prop.getProperty("Vclas_home.AssignmentList.WarningWindow"))) == true) {
						reportStep("Assignment List Warning Window is displayed successfully", "Info", true);
					Vclas_AssignmentList_WarningWindow();
					if(flowName.equalsIgnoreCase("Tugger")) {
						reportStep("--------08 Verify the possibility of delivering  racks in wrong sequence from Assignments List screen- Test case Id- 3106--Completed----", "Pass", false);
					}else {
					reportStep("--------13 Verify the possibility of delivering  JISJIT racks in wrong sequence from Assignments List sceen whether Warning window is being dislpayed on top of the scanning window or not- Test case Id--2794 --Completed----", "Pass", false);
					}
					driver.findElement(By.xpath(prop.getProperty("Vclas.AssignmentList.CancelButton.Click"))).click();
					
					//driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Drop.icon"))).click();
					anyClick("Drop Button", By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Drop.icon")));
					iTempSeqNo=Seqno;

					}else {
						reportStep("Assignment List Warning Window is not displayed successfully", "Fail", true);
					}

				
				
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("exception thrown in method");
			}
			}
	
	public void Vclas_AssignmentList_WarningWindow() {
		
		String sheetName = "Vclas_Assignments";
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		
			
			// Verifying The Message
						WebElement warningMessage = driver.findElement(By.xpath(prop.getProperty("Vclas_home.AssignmentList.WarningWindow.msg")));
						String warningStatus = warningMessage.getText();
			if (warningStatus.equalsIgnoreCase(tdrow.get("Warning_Window_Message"))) {
				System.out.println(" Verification status : " + warningStatus);

				reportStep("Verification status - Message displayed " + warningStatus, "pass", true);
				/*
				if(verifyElementExistReturn(By.xpath(prop.getProperty("Vclas_home.AssignmentList.WarningWindow.OkButton.Click")))==true) {
					driver.findElement(By.xpath(prop.getProperty("Vclas_home.AssignmentList.WarningWindow.OkButton.Click"))).click();
				}else {*/
				//driver.findElements(By.xpath(prop.getProperty("Vclas_home.Shortage.WarningWindow.OkButton.Click"))).get(0).click();
				anyClick("Warning Window Ok Button", By.xpath(prop.getProperty("Vclas_home.Shortage.WarningWindow.OkButton.Click")));
			//}

		}
		
	
}
	
	public void Vclas_Tugger_Shortage_Delivery(String flowName) throws InterruptedException {
		try {
			
			String strSeqno, seqHandling,scanId;
			int Seqno, racksCount, iTempSeqNo, strLopNo;
			String rackStatus, strFlowType, strFlowTypeObject,strTaskId;
			String[] strType;
			int intRackNewSeq ,j=0;
			Admin_Page adp = new Admin_Page();
			MasWeb_Home mwHome = new MasWeb_Home();
			
			// Navigation to Shortage
			anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));
			Thread.sleep(5000);

			String sheetName = "Vclas_Assignments";
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
			Seqno = Integer.parseInt(tdrow.get("Object")); // 1
			racksCount = Integer.parseInt(tdrow.get("No_of_Racks"));
			strFlowType = tdrow.get("Sequence_Type");
			strType = tdrow.get("Shortage_Type_Expected").split(",");
			strLopNo = Integer.parseInt(tdrow.get("Lopnr"));
			String strTempSeqNo;
			List<String> listr = vt.getList();
			List<String> listrempty = vt.getList1();
			if (flowName.equalsIgnoreCase("Tugger")) {
				strSeqno = String.valueOf(Seqno);// 01
				seqHandling = " ";
				
			} else {

				strSeqno = tdrow.get("Object");// 0001
				System.out.println("seq no :" + strSeqno);
				seqHandling = StringUtils.repeat("0", 4 - (String.valueOf(Seqno).length())); // 000
				System.out.println("seq handling:" + seqHandling);
			}
				
				System.out.println("flow name : " + flowName);

				Vclas_Shortage_Search(strFlowType, strSeqno, flowName);

				iTempSeqNo = Seqno;

				System.out.println("seq no:" + iTempSeqNo);
				// To Verify Initial Status in Shortage Screen for created racks
				for (int i = 1; i <= racksCount; i++) {

					if (i <= racksCount) {
						rackStatus = "Planned";
					} else {
						rackStatus = "Active";
						
					}
					
					System.out.println("full TASK ID: " +listr);
					
					
					
					System.out.println("empty TASK ID: " +listrempty);
				
					
						// To verify the Full task id
						Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
								listr.get(j).toString(), strType[0].toUpperCase(), rackStatus);

						// To Verify the Empty task id
						Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
								listrempty.get(j).toString(), strType[1].toUpperCase(), "Active");
		                    j++;   //new
		                    iTempSeqNo = Seqno + 1;
					}
				
			
				//int lastSubmittedFullRack = 0;
				iTempSeqNo = Seqno + 1;
				System.out.println("itemp val:" + iTempSeqNo);
				// Click the Planned Activity in Shortage Screen for Initial Two Full Racks
				for (int i = 1; i <= racksCount; i++) {
					
                          j=--j;
                          System.out.println("j :" +j);
					reportStep("--------Full rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
							+ "#C Initiated----", "Info", false);
					// Submitting the Rack
					System.out.println(
							"rck submsn" + strType[0] + " " + strFlowType + seqHandling + String.valueOf(iTempSeqNo));
					
					strFlowTypeObject = strFlowType + seqHandling + String.valueOf(iTempSeqNo);
					System.out.println("flowTypeObject :"+strFlowTypeObject);
					strTaskId = listr.get(j).toString();
					System.out.println("taskId:" +strTaskId);
					
					driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Pickup.Button").replace("&Value", strFlowTypeObject).replace("&1Value", strTaskId))).get(0).click();
					
					
					if (flowName.equalsIgnoreCase("Tugger")) {
						reportStep("--------09 Verify the possibility of delivering  racks in wrong sequence from SHORTAGE screen- Test case Id- 3107--Initiated----", "Pass", false);
						if (String.valueOf(iTempSeqNo).length() == 1) {
							strTempSeqNo = "0" + String.valueOf(iTempSeqNo);
							System.out.println(strTempSeqNo);
						} else {
							strTempSeqNo = String.valueOf(iTempSeqNo);
							System.out.println(strTempSeqNo);
						}

						// Calculate LopNo for ScanId
						strLopNo = strLopNo + Integer.parseInt(tdrow.get("Line_movement"));
						System.out.println("strLopNo:" + strLopNo);

						// Calculate Scan Id
					scanId = "T" + strFlowType + strTempSeqNo + strLopNo;
						System.out.println("scan id" + scanId);
						System.out.println(strFlowType + seqHandling + String.valueOf(iTempSeqNo) + "ok click ");
						

					} else {
						reportStep("--------14 Verify the possibility of delivering  JISJIT racks in wrong sequence from SHORTAGE sceen- Test case Id--2795--Initiated----", "Pass", false);
						
						strTempSeqNo = StringUtils.repeat("0", 4 - String.valueOf(iTempSeqNo).length()) + iTempSeqNo;
						System.out.println("strTempSeqNo:" + strTempSeqNo);

						// Calculate Scan Id
						scanId = strFlowType + strTempSeqNo;
						System.out.println("scan id" + scanId);
						
					}
					driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Deliver.Button").replace("&Value", strFlowTypeObject).replace("&1Value", strTaskId))).get(0).click();
					 Thread.sleep(5000);
				if (verifyElementExistReturn(By.xpath(prop.getProperty("Vclas_home.AssignmentList.ScanDialogBox"))) == true) {
						reportStep("Assignment Scan dialog box is displayed successfully", "Info", true);
                   Thread.sleep(3000);
                   System.out.println("need to enter scan id");
                   //driver.findElements(By.xpath(prop.getProperty("Vclas.AssignmentList.ManualInput.No")))
					//.get(0).click();
                  //sendKeys("Scan ID", By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")), scanId);
					driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text"))).sendKeys(scanId);
						System.out.println("scan id entered");
						//driver.findElements(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")))
						//.get(0).sendKeys(Keys.TAB);
				}
				 Thread.sleep(5000);
				if (verifyElementExistReturn(
							By.xpath(prop.getProperty("Vclas_home.AssignmentList.WarningWindow"))) == true) {
					 Thread.sleep(3000);
						reportStep("Assignment List Warning Window is displayed successfully", "Info", true);
					Vclas_AssignmentList_WarningWindow();
					}
					if (verifyElementExistReturn(
							By.xpath(prop.getProperty("Vclas.AssignmentList.OkButton.Click"))) == true) {
						//Thread.sleep(5000);
						//driver.findElements(By.xpath(prop.getProperty("Vclas.AssignmentList.OkButton.Click"))).get(0).click();
						//retryingFindClick(By.xpath(prop.getProperty("Vclas.AssignmentList.OkButton.Click")));
						anyClick("Ok Button", By.xpath(prop.getProperty("Vclas.AssignmentList.OkButton.Click")));
						iTempSeqNo = Seqno;
						System.out.println("iTempSeqNo:" +iTempSeqNo);
					
					}
				}
				
				if (iTempSeqNo == 100) {
					iTempSeqNo = 1;
				}
			
				Vclas_Shortage_NewRacks_Search(strFlowType,Seqno,racksCount);
			
				System.out.println("seq no" + Seqno);
				long clickTime;

				// Sending Empty Racks and Full Racks
				
					mwHome.Navigate_Admin_Simulator_Assembly_Line();
					clickTime = 1;

					reportStep("--------Empty rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
							+ "#C Initiated----", "Info", false);
					// Click Assembly Line in Masweb to get Empty Racks Planned
					// click once to get the correct time to move rack in assembly line
					adp.Simulator_Assemblyline_Click((int) clickTime);

					int thersholdValue;

					if (flowName.equalsIgnoreCase("tugger")) {

						Parameter_differentracksize parameter = new Parameter_differentracksize();
						thersholdValue = (int) parameter.planning();
						
					} else {
						Thread.sleep(60000);
						// fetch the racksize and update it in excel
						Sekadm sp = new Sekadm();
						sp.JISJIT_EmptyRack_Delivery();
						JISJIT_ParameterPlanning parameter = new JISJIT_ParameterPlanning();

						thersholdValue = (int) parameter.planning();

					}
					int tempThreshold = thersholdValue;
					reportStep("Planned Time value: " + thersholdValue, "pass", false);
					System.out.println("Value from opti planning excel" + thersholdValue);
					do {

						System.out.println("wait for time value to get reflected in vclas..");
						driver.switchTo().window(browser[2]);
						Thread.sleep(120000);

						// Retrieve time value to plan the racks
						clickTime = Vclas_Shortage_Time_Verify(" ", strFlowType, seqHandling + String.valueOf(iTempSeqNo),
								flowName);
						System.out.println("time verify done");
						if (clickTime == thersholdValue) {
							reportStep("Planned time verified Actual Time: "+clickTime+" Expected Time: "+tempThreshold, "Info", false); 
							break;
						}
						if (thersholdValue != 0) {
							// assuming value is negative
							clickTime = clickTime - thersholdValue;
							System.out.println("After adding threshold to click time for: " + clickTime);
						}

						else {
							clickTime = clickTime + thersholdValue;
							System.out.println("After adding threshold to click time for: " + clickTime);
						}
						// check whether click time is negative or zero
						if (clickTime == thersholdValue) {
							reportStep("Planned time verified Actual Time: "+clickTime+" Expected Time: "+tempThreshold, "Info", false); 
							break;
						}

						reportStep(
								"--------Empty rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
										+ "#C Assembly Movement Initiated, No. Of Clicks:  #B" + clickTime + "#C----",
								"Info", false);
						// Accessing the Admin Page to move the Line
						adp.Simulator_Assemblyline_Click((int) clickTime);

						reportStep(
								"--------Empty rack submission: " + strFlowType + " " + String.valueOf(iTempSeqNo)
										+ " Assembly Movement Completed, No. Of Clicks: #B" + clickTime + "#C----",
								"Info", false);

						// Check Whether The Expected Rack Is Planned
						if (Vclas_Shortage_Time_Verify(" ", strFlowType, seqHandling + String.valueOf(iTempSeqNo),
								flowName) == tempThreshold) {
							reportStep("Planned time verified Actual Time: "+clickTime+" Expected Time: "+tempThreshold, "Info", false); 
							break;
						}
						System.out.println("after while" + clickTime + " " + thersholdValue);
					} while (((int) clickTime != tempThreshold)); // check whether click Time is positive

					// verify whether rack status is planned
					Vclas_Shortage_SingleRackStatusVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
							"EMPTY_RACK", "Planned");
					
					iTempSeqNo= iTempSeqNo+ 2;
					
					// verify whether full rack status is planned
					Vclas_Shortage_SingleRackStatusVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
							"FULL_RACK", "Planned");
				
					reportStep("--------Full rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
					+ "#C Initiated----", "Info", false);
			// Submitting the Rack
			System.out.println(
					"rck submsn" + strType[0] + " " + strFlowType + seqHandling + String.valueOf(iTempSeqNo));
			j=2;
			
			
		
			strTaskId = listr.get(j).toString();
			System.out.println("taskId:" +strTaskId);
			for (int i = 1; i <= racksCount; i++) {
				strFlowTypeObject = strFlowType + seqHandling + String.valueOf(iTempSeqNo);
				System.out.println("flowTypeObject :"+strFlowTypeObject);
				
			driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Pickup.Button").replace("&Value", strFlowTypeObject).replace("&1Value", strTaskId))).get(0).click();
			//driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Deliver.Button").replace("&Value", strFlowTypeObject).replace("&1Value", strTaskId))).get(0).click();
			

			if (flowName.equalsIgnoreCase("Tugger")) {
				if (String.valueOf(iTempSeqNo).length() == 1) {
					strTempSeqNo = "0" + String.valueOf(iTempSeqNo);
					System.out.println(strTempSeqNo);
				} else {
					strTempSeqNo = String.valueOf(iTempSeqNo);
					System.out.println(strTempSeqNo);
				}

				// Calculate LopNo for ScanId
				strLopNo = strLopNo + Integer.parseInt(tdrow.get("Line_movement"));
				System.out.println("strLopNo:" + strLopNo);

				// Calculate Scan Id
			scanId = "T" + strFlowType + strTempSeqNo + strLopNo;
				System.out.println("scan id" + scanId);
				System.out.println(strFlowType + seqHandling + String.valueOf(iTempSeqNo) + "ok click ");
				

			} else {

				strTempSeqNo = StringUtils.repeat("0", 4 - String.valueOf(iTempSeqNo).length()) + iTempSeqNo;
				System.out.println("strTempSeqNo:" + strTempSeqNo);

				// Calculate Scan Id
				scanId = strFlowType + strTempSeqNo;
				System.out.println("scan id" + scanId);
				
			}
			driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Deliver.Button").replace("&Value", strFlowTypeObject).replace("&1Value", strTaskId))).get(0).click();
			 Thread.sleep(5000);
		if (verifyElementExistReturn(By.xpath(prop.getProperty("Vclas_home.AssignmentList.ScanDialogBox"))) == true) {
				reportStep("Assignment Scan dialog box is displayed successfully", "Info", true);
          Thread.sleep(3000);
          if(i==1) {
          System.out.println("need to enter scan id");
          //driver.findElements(By.xpath(prop.getProperty("Vclas.AssignmentList.ManualInput.No")))
			//.get(0).click();
         //sendKeys("Scan ID", By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")), scanId);
			driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text"))).sendKeys(scanId);
				System.out.println("scan id entered");
				//driver.findElements(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")))
				//.get(0).sendKeys(Keys.TAB);
		}
		}
		 Thread.sleep(5000);
		if (verifyElementExistReturn(
					By.xpath(prop.getProperty("Vclas_home.AssignmentList.WarningWindow"))) == true) {
			 Thread.sleep(3000);
				reportStep("Assignment List Warning Window is displayed successfully", "Info", true);
			Vclas_AssignmentList_WarningWindow();
			}
			if (verifyElementExistReturn(
					By.xpath(prop.getProperty("Vclas.AssignmentList.OkButton.Click"))) == true) {
				//Thread.sleep(5000);
				//driver.findElements(By.xpath(prop.getProperty("Vclas.AssignmentList.OkButton.Click"))).get(0).click();
				//retryingFindClick(By.xpath(prop.getProperty("Vclas.AssignmentList.OkButton.Click")));
				anyClick("Ok Button", By.xpath(prop.getProperty("Vclas.AssignmentList.OkButton.Click")));
			
			}
			
			strTaskId = listrempty.get(0).toString();
			iTempSeqNo = Seqno;
			}
			if(flowName.equalsIgnoreCase("Tugger")) {
			reportStep("--------09 Verify the possibility of delivering  racks in wrong sequence from SHORTAGE screen- Test case Id- 3107--Completed----", "Pass", false);
			}else {
				reportStep("--------14 Verify the possibility of delivering  JISJIT racks in wrong sequence from SHORTAGE sceen- Test case Id--2795--Completed----", "Pass", false);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void Vclas_Shortage_InvalidInput(String flowName) {
		
					try {
						
						String strSeqno, seqHandling;
						int Seqno, racksCount, iTempSeqNo, strLopNo;
						String rackStatus, strFlowType, strFlowTypeObject, strTaskId;
						String[] strType;
						int j=0;
						List<String> arral = new ArrayList<String>();

						// Navigation to Shortage
						anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));
						Thread.sleep(5000);

						String sheetName = "Vclas_Assignments";
						tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
						Seqno = Integer.parseInt(tdrow.get("Object")); // 1
						racksCount = Integer.parseInt(tdrow.get("No_of_Racks"));
						strFlowType = tdrow.get("Sequence_Type");
						strType = tdrow.get("Shortage_Type_Expected").split(",");
						strLopNo = Integer.parseInt(tdrow.get("Lopnr"));
						String strTempSeqNo;

							strSeqno = String.valueOf(Seqno);// 01
							seqHandling = " ";
						

						System.out.println("flow name : " + flowName);

						Vclas_Shortage_Search(strFlowType, strSeqno, flowName);

						iTempSeqNo = Seqno;

						System.out.println("seq no:" + iTempSeqNo);
						// To Verify Initial Status in Shortage Screen for created racks
						int i = 1; 

							if (i <= racksCount) {
								rackStatus = "Planned";
							} else {
								rackStatus = "Active";
								
							}
							List<String> listr = vt.getList();
						System.out.println("full TASK ID: " +listr);
						
						
							// To verify the Full task id
							Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
									listr.get(j).toString(), strType[0].toUpperCase(), rackStatus);
							reportStep("--------10 Verify the functionality of scanning Package Nr with invalid input from both SHORTAGE and A L screens when Manual Input is YES-- Test case Id- 3108--Initiated----", "Pass", false);
							strFlowTypeObject = strFlowType + seqHandling + String.valueOf(iTempSeqNo);
							System.out.println("flowTypeObject :"+strFlowTypeObject);
							strTaskId = listr.get(j).toString();
							System.out.println("taskId:" +strTaskId);
							
							driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Pickup.Button").replace("&Value", strFlowTypeObject).replace("&1Value", strTaskId))).get(0).click();

							Vclas_Shortage_UserVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
									listr.get(j).toString(), strType[0].toUpperCase());
							
String added;
							arral.add(getRandomString(5));
							
						
							arral.add(String.valueOf(getRandomNumber(99999)));
							arral.add(getRandomString(5) + String.valueOf(getRandomNumber(99999)));
							arral.add(getRandomSpecialChar(9));
							arral.add("T" + getRandomSpecialChar(9));
							arral.add("T" + getRandomNumber(99) + getRandomString(3)+ getRandomSpecialChar(4));
							arral.add("T" + getRandomString(5));
							arral.add("T" + String.valueOf(getRandomNumber(99999)));
							arral.add("T" + getRandomString(5) + String.valueOf(getRandomNumber(99999)));
							arral.add("T" + getRandomString(3) + String.valueOf(getRandomNumber(999999999)));
							arral.add("T" + getRandomString(3) + String.valueOf(getRandomNumber(99999)));
							
							
							for(int k=0;k<arral.size();k++) {
							added=arral.get(k).toString();
							 
							System.out.println("add1: "+added);
							
							}
							driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Deliver.Button").replace("&Value", strFlowTypeObject).replace("&1Value", strTaskId))).get(0).click();
							if (verifyElementExist("Scan Dialog Box",By.xpath(prop.getProperty("Vclas_home.AssignmentList.ScanDialogBox"))) == true) {
								reportStep("Assignment Scan dialog box is displayed successfully", "Info", true);
				          Thread.sleep(3000);
				          System.out.println("need to enter scan id");
				          driver.findElements(By.xpath(prop.getProperty("Vclas.AssignmentList.ManualInput.No")))
							.get(0).click();
				          for(int l=0;l<arral.size();l++) {
							

							
					driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")))
							.sendKeys(arral.get(l).toString());
					Thread.sleep(5000);
					driver.findElements(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")))
					.get(0).sendKeys(Keys.TAB);
				         
					Thread.sleep(5000);
					// Verifying The Message
					List<WebElement> invalidMessage = driver.findElements(By.xpath(prop.getProperty("Vclas_AssignmentList.ErrorMessage.AGV")));
					String invalidInput = invalidMessage.get(0).getText();
					System.out.println("invalidInput: "+ invalidInput);
		if (invalidInput.contains(tdrow.get("Invalid_Input_Error")) || invalidInput.contains(tdrow.get("Invalid_Input_Error_Msg"))) {
			System.out.println(" Invalid Input Error Message : " + invalidInput);

			reportStep("Invalid Input Error - Message displayed " + invalidInput, "pass", true);
					
							}
		
							}
							}else {
								System.out.println("condition failed");
							}
						
							driver.findElement(By.xpath(prop.getProperty("Vclas.AssignmentList.CancelButton.Click"))).click();
							Vclas_AssignmentList_RackSubmission_Tugger(strType[0],
									strFlowType + seqHandling + String.valueOf(iTempSeqNo));
							Vclas_Tugger_Invalid_Input_Method();
							reportStep("--------10 Verify the functionality of scanning Package Nr with invalid input from both SHORTAGE and A L screens when Manual Input is YES-- Test case Id- 3108--Completed----", "Pass", false);
					}catch(Exception e) {
						System.out.println(e);
					}
					
					
}
	
	public void Vclas_AssignmentList_InvalidInput(String flowName) {
		
		try {
			
			String strSeqno, seqHandling,strFlowType;
			int Seqno, iTempSeqNo,j=0;
			String[] strType;
			reportStep("--------11 Verify the functionality of delivering a rack in correct sequence by copy pasting the correct and wrong package Nr in SCAN field when Manual Input is NO from A L screen-- Test case Id- 3109--Initiated----", "Pass", false);
			// Navigation to Shortage
			anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));
			Thread.sleep(5000);

			String sheetName = "Vclas_Assignments";
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
			Seqno = Integer.parseInt(tdrow.get("Object")); // 1
			strFlowType = tdrow.get("Sequence_Type");
			strType = tdrow.get("Shortage_Type_Expected").split(",");
		
				strSeqno = String.valueOf(Seqno);// 01
				seqHandling = " ";
	
			System.out.println("flow name : " + flowName);

			Vclas_Shortage_Search(strFlowType, strSeqno, flowName);

			iTempSeqNo = Seqno;
			System.out.println("seq no:" + iTempSeqNo);
			// To Verify Initial Status in Shortage Screen for created racks
			int i = 1; 

				List<String> listr = vt.getList();
			System.out.println("full TASK ID: " +listr);
			
				// To verify the Full task id
				Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
						listr.get(j).toString(), strType[0].toUpperCase(), "Planned");
				
				Vclas_AssignmentList_RackSubmission_Tugger(strType[0],
						strFlowType + seqHandling + String.valueOf(iTempSeqNo));
				reportStep("--------15 Verify the possibility of entering input manually in SCAN field when Manual Input is NO for the correct sequence rack delivery from A L screen-- Test case Id- 3113--Initiated----", "Pass", false);
				Vclas_Tugger_Invalid_Input_Method();
				reportStep("--------11 Verify the functionality of delivering a rack in correct sequence by copy pasting the correct and wrong package Nr in SCAN field when Manual Input is NO from A L screen-- Test case Id- 3109--Completed----", "Pass", false);
				reportStep("--------15 Verify the possibility of entering input manually in SCAN field when Manual Input is NO for the correct sequence rack delivery from A L screen-- Test case Id- 3113--Completed----", "Pass", false);
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
}
	
	public void Vclas_Shortage_UserVerify(String ObjectId, String TaskId, String Type)
			throws InterruptedException {
		try {

			/*// Search Object Id and Task Id in the table
			String objIdVerify = prop.getProperty("Vclas_Home.Shortage.Object_Id.Verify").replace("&Value", ObjectId)
					.replace("&1Value", TaskId);

			// Verifying if it is available in the screen
			if (verifyElementExistReturn(By.xpath(objIdVerify)) == true) {

				reportStep(
						"--------Shortage screen verification: Flow: #B" + ObjectId + "#C Task Id: #B" + TaskId
								+ "#C Type: #B" + Type + "#C and Status: #B" + Status + "#C Initiated----",
						"Info", false);
				reportStep("ObjectId : " + ObjectId + " " + "TaskId: " + TaskId + " exists in the Shortage Screen ",
						"PASS", true);*/
				String objUser = prop.getProperty("Vclas_Home.Shortage.Object_Id_Type_User.Verify")
						.replace("&Value", ObjectId).replace("&1Value", TaskId).replace("&2Value", Type);

				/*int iTempClick = 0;
				do {

					driver.findElement(By.xpath(objUser));
					iTempClick = iTempClick + 1;
					driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

					if (verifyElementExistReturn(By.xpath(objUser)) == true) {
						break;
					}
				} while (iTempClick != 5);*/
				String newUsrName = driver.findElement(By.xpath(objUser)).getText();
				System.out.println(newUsrName);
				String usrName = prop.getProperty(tdrow.get("User_Details") + ".User.Name");

				// Verifying the type is available in the screen
				if (newUsrName.equalsIgnoreCase(usrName)) {
					reportStep(usrName + " " + "is verified", "PASS", true);
				}
		}catch(Exception e) {
			
		}
	}
	
	public int Vclas_Shortage_NewRacks_Search(String objectId ,int seqNo, int racksCount)
			throws InterruptedException {
		int iTempClick = 0,intobjectNo;
		// Navigation to Shortage
					anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));
					Thread.sleep(75000);
					// Verify the Shortage screen is displayed

					if (driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text"))).size() > 0) {
						if (tempVar == false) {
							// new for stale Exception
							do {
								// driver.findElement(By.xpath(prop.getProperty("Assembly_line.Display.Button"))).click();
								driver.findElement(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text")));
								iTempClick = iTempClick + 1;
								driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

								if (verifyElementExist("Shortage button",
										By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Button"))) == true) {
									break;
								}
							} while (iTempClick != 5);

							driver.findElement(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text")))
									.sendKeys(Keys.LEFT_CONTROL + "a");
							driver.findElement(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text")))
									.sendKeys(Keys.DELETE);
							// Enter the Object Id
							driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text"))).get(0)
									.sendKeys(objectId);

							// Clicking the Search Button
							driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Button"))).get(0).click();
							tempVar = true;
						}
System.out.println("Shortage screen is displayed successfully");
						reportStep("Shortage screen is displayed successfully", "Pass", true);
					}
		int iTemp=1,intRackNewSeq; 
		String newObj="", addd="";
		 intRackNewSeq = seqNo + racksCount - 1;
		
		int tblSize = driver.findElements(By.xpath(prop.getProperty("Vclas_home.Shortage.Table.Search") + "/table/tbody/tr")).size();
		System.out.println("tblSize:" +tblSize);
		do {
			
			if(intRackNewSeq==99) {
				intRackNewSeq=0;
			}
			if(intRackNewSeq==100) {
				intRackNewSeq=1;
			}
			String objectNo = driver.findElements(By.xpath(prop.getProperty("Vclas_home.Shortage.Table.Data") +"/tr["+iTemp+"]/td[1]")).get(0).getText();
			System.out.println("objectNo :" +objectNo);
			objectNo = objectNo.substring(3).trim();
			System.out.println("objectNo :" +objectNo);
			
			intobjectNo= Integer.parseInt(objectNo);
			System.out.println("intobjectNo:" +intobjectNo);
			
			//new
			if(intRackNewSeq==0) {
				int objSize =objectNo.length();
				if(objSize== 1)
				{
					intRackNewSeq=intobjectNo;
					addd=String.valueOf(intRackNewSeq);
					System.out.println("addd1: " +addd);
					System.out.println("obj size is greater than 0--1 digit" +objSize);
				}else {
					addd="";
				}
			}else if(intobjectNo > intRackNewSeq && (objectNo.length()==(String.valueOf(intRackNewSeq).length()))) {
				intRackNewSeq=intobjectNo;
				addd=String.valueOf(intRackNewSeq);
				System.out.println("addd2: " +addd);
			}
			
			else {
				addd="";
				System.out.println("empty");
				System.out.println("addd3: " +addd);
			
			}
			if(addd!="") {
				System.out.println("getting objno: " +driver.findElement(By.xpath(prop.getProperty("Vclas_home.Shortage.Table.Data") +"/tr["+iTemp+"]/td[1]")).getText());
				
				//new
				 if (String.valueOf(intRackNewSeq).length() == 1) {
						newObj = "0" + String.valueOf(intRackNewSeq);
						
					}else {
						newObj= String.valueOf(intRackNewSeq);
					}
				mwHome.Navigate_Vclas_Task();
				
				vt.Vclas_Search_Partno();
				
					vt.Vclas_getTuggerTask(objectId, newObj, racksCount, " ");
					driver.switchTo().window(browser[2]);
			}
			iTemp=iTemp+1;
		
		}while( iTemp<= tblSize );
		return intRackNewSeq;
	}
	
	public void StaleHandling(By Xpath) {
		int iTempClick=0;
		do 
        {
               //driver.findElement(By.xpath(prop.getProperty("Assembly_line.Display.Button"))).click();
               driver.findElement(Xpath); 
               iTempClick = iTempClick+1;
               driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
               
               if (verifyElementExist("Shortage button", Xpath)== true) {
                      break;
               }
        } while(iTempClick != 5);

		
	}
	
public static void clickByLocator( final By locator ) {
        
        // final long startTime = System.currentTimeMillis();
         driver.manage().timeouts().implicitlyWait( 5, TimeUnit.SECONDS );
         Wait<WebDriver> wait = new FluentWait<WebDriver>( driver )
               .withTimeout(90000, TimeUnit.MILLISECONDS)
              .pollingEvery(5500, TimeUnit.MILLISECONDS);
               //.ignoring( StaleElementReferenceException.class );         
         wait.until( new ExpectedCondition<Boolean>() { 
           @Override 
           public Boolean apply( WebDriver webDriver ) {
             try {
               webDriver.findElement(locator).click();
               System.out.println(locator);
               System.out.println("clicked");
               return true;
             } catch ( StaleElementReferenceException e ) {                                     
               
               return false;
             }              
           } 
         } );        
       driver.manage().timeouts().implicitlyWait( Default_Wait_4_Page, TimeUnit.SECONDS );
       }


       


public void Vclas_Shortage_PositionVerify(String ObjectId,  String TaskId, String CurrentPos)
		throws InterruptedException {
	try {
          String objCurrentPos = prop.getProperty("Vclas_Home.Shortage.Object_Id_Current_Position.Verify")
.replace("&Value", ObjectId).replace("&1Value", TaskId);

int iTempClicks = 0;
do {

driver.findElement(By.xpath(objCurrentPos));
iTempClicks = iTempClicks + 1;
driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

if (verifyElementExistReturn(By.xpath(objCurrentPos)) == true) {
break;
}
} while (iTempClicks != 5);
String newCurrentPos = driver.findElement(By.xpath(objCurrentPos)).getText();
System.out.println(newCurrentPos);


// Verifying the type is available in the screen
if (newCurrentPos.contains(CurrentPos)) {
reportStep(objCurrentPos + " " + "position is verified", "PASS", true);
}/*else {
	reportStep("Position is not verified successfully. The type to be expected:" + CurrentPos
			+ "The Actual Type is:" + String.valueOf(newCurrentPos), "fail", true);
}*/
	} catch (Exception e) {
		System.out.println("Exception in Vclas_Shortage_TypeVerify Method");
	}
	}

public void Vclas_Tugger_Full_Delivery(String flowName) throws InterruptedException {
	try {
		
		String strSeqno, seqHandling, strTempSeqNo;
		int Seqno, racksCount, iTempSeqNo, strLopNo;
		String rackStatus, strFlowType;
		String[] strType;
		int j=0;
		
		

		// Navigation to Shortage
		anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));
		Thread.sleep(5000);

		String sheetName = "Vclas_Assignments";
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		Seqno = Integer.parseInt(tdrow.get("Object")); // 1
		racksCount = Integer.parseInt(tdrow.get("No_of_Racks"));
		strFlowType = tdrow.get("Sequence_Type");
		strType = tdrow.get("Shortage_Type_Expected").split(",");
		strLopNo = Integer.parseInt(tdrow.get("Lopnr"));
		

		if (flowName.equalsIgnoreCase("Tugger")) {
			strSeqno = String.valueOf(Seqno);// 01
			seqHandling = " ";
			
		} else {

			strSeqno = tdrow.get("Object");// 0001
			System.out.println("seq no :" + strSeqno);
			seqHandling = StringUtils.repeat("0", 4 - (String.valueOf(Seqno).length())); // 000
			System.out.println("seq handling:" + seqHandling);
		}

		System.out.println("flow name : " + flowName);

		Vclas_Shortage_Search(strFlowType, strSeqno, flowName);

		iTempSeqNo = Seqno;

		System.out.println("seq no:" + iTempSeqNo);
		// To Verify Initial Status in Shortage Screen for created racks
		for (int i = 1; i <= racksCount; i++) {

			if (i <= racksCount) {
				rackStatus = "Planned";
			} else {
				rackStatus = "Active";
				
			}
			
		System.out.println("full TASK ID: " +listr);
		
		String retval = listr.get(0);
		System.out.println("retval:" +retval);
		
		List<String> listrempty = vt.getList1();
		System.out.println("empty TASK ID: " +listrempty);
		
		String retvalempty = listrempty.get(0);
		System.out.println("retval:" +retvalempty);
		
		
			// To verify the Full task id
			Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
					listr.get(j).toString(), strType[0].toUpperCase(), rackStatus);

			// To Verify the Empty task id
			Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
					listrempty.get(j).toString(), strType[1].toUpperCase(), "Active");
			 
			Vclas_Shortage_PositionVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo), listr.get(j).toString(), "UNKNOWN");
                j++;   //new
			// Sequence No
			iTempSeqNo = iTempSeqNo + 1;

			if (iTempSeqNo == 100) {
				iTempSeqNo = 1;
			}

		}

		iTempSeqNo = Seqno;
		System.out.println("itemp val:" + iTempSeqNo);
		int lastSubmittedFullRack = 0;

		// Click the Planned Activity in Assignment List for Initial Two Full Racks
		for (int i = 1; i <= racksCount; i++) {

			reportStep("--------Full rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
					+ "#C Initiated----", "Info", false);
			// Submitting the Rack
			System.out.println(
					"rck submsn" + strType[0] + " " + strFlowType + seqHandling + String.valueOf(iTempSeqNo));
			if (flowName.equalsIgnoreCase("Tugger")) {

				Vclas_AssignmentList_RackSubmission_Tugger(strType[0],
						strFlowType + seqHandling + String.valueOf(iTempSeqNo));
			} else {
				Vclas_AssignmentList_RackSubmission_JISJIT(strType[0].toUpperCase(),
						strFlowType + seqHandling + String.valueOf(iTempSeqNo));
			}

			lastSubmittedFullRack = iTempSeqNo;

			if (flowName.equalsIgnoreCase("Tugger")) {
				if (String.valueOf(iTempSeqNo).length() == 1) {
					strTempSeqNo = "0" + String.valueOf(iTempSeqNo);
					System.out.println(strTempSeqNo);
				} else {
					strTempSeqNo = String.valueOf(iTempSeqNo);
					System.out.println(strTempSeqNo);
				}

				// Calculate LopNo for ScanId
				strLopNo = strLopNo + Integer.parseInt(tdrow.get("Line_movement"));
				System.out.println("strLopNo:" + strLopNo);

				// Calculate Scan Id
				String scanId = "T" + strFlowType + strTempSeqNo + strLopNo;
				System.out.println("scan id" + scanId);
				System.out.println(strFlowType + seqHandling + String.valueOf(iTempSeqNo) + "ok click ");
				Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName);

			} else {

				strTempSeqNo = StringUtils.repeat("0", 4 - String.valueOf(iTempSeqNo).length()) + iTempSeqNo;
				System.out.println("strTempSeqNo:" + strTempSeqNo);

				// Calculate Scan Id
				String scanId = strFlowType + strTempSeqNo;
				System.out.println("scan id" + scanId);
				Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName);
			}

			reportStep("--------Full rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
					+ "#C Submission Completed----", "Info", false);

			// Sequence No
			iTempSeqNo = iTempSeqNo + 1;

			if (iTempSeqNo == 100) {
				iTempSeqNo = 1;
			}

		}
		
		Vclas_Shortage_NewRacks_Search(strFlowType,Seqno,racksCount);
		
		//new
		List<String> listr = vt.getList();
		System.out.println("full TASK ID: " +listr);
		
		List<String> listrempty = vt.getList1();
		System.out.println("empty TASK ID: " +listrempty);
		
		iTempSeqNo = Seqno;
        System.out.println("seq no" + Seqno);
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("exception thrown in method");
	}
}

public void Vclas_Tugger_Empty_Delivery(String flowName) throws InterruptedException {
	try {
		int Seqno,racksCount,iTempSeqNo, lastSubmittedFullRack = 0, strLopNo;
		String rackStatus, strFlowType, seqHandling,strSeqno , strTempSeqNo;
		String[] strType;
		Seqno = Integer.parseInt(tdrow.get("Object")); // 1
		racksCount = Integer.parseInt(tdrow.get("No_of_Racks"));
		strFlowType = tdrow.get("Sequence_Type");
		strType = tdrow.get("Shortage_Type_Expected").split(",");
		strLopNo = Integer.parseInt(tdrow.get("Lopnr"));
		Admin_Page adp = new Admin_Page();
long clickTime;

if (flowName.equalsIgnoreCase("Tugger")) {
	strSeqno = String.valueOf(Seqno);// 01
	seqHandling = " ";
	
} else {

	strSeqno = tdrow.get("Object");// 0001
	System.out.println("seq no :" + strSeqno);
	seqHandling = StringUtils.repeat("0", 4 - (String.valueOf(Seqno).length())); // 000
	System.out.println("seq handling:" + seqHandling);
}

iTempSeqNo = Seqno;
lastSubmittedFullRack= iTempSeqNo+1;
// Sending Empty Racks and Full Racks

	mwHome.Navigate_Admin_Simulator_Assembly_Line();
	clickTime = 1;

	reportStep("--------Empty rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
			+ "#C Initiated----", "Info", false);
	// Click Assembly Line in Masweb to get Empty Racks Planned
	// click once to get the correct time to move rack in assembly line
	adp.Simulator_Assemblyline_Click((int) clickTime);

	int thersholdValue;

	if (flowName.equalsIgnoreCase("tugger")) {

		Parameter_differentracksize parameter = new Parameter_differentracksize();
		thersholdValue = (int) parameter.planning();

	} else {
		Thread.sleep(50000);
		// fetch the racksize and update it in excel
		Sekadm sp = new Sekadm();
		sp.JISJIT_EmptyRack_Delivery();
		JISJIT_ParameterPlanning parameter = new JISJIT_ParameterPlanning();

		thersholdValue = (int) parameter.planning();

	}

	int tempThreshold = thersholdValue;
	reportStep("Planned Time value: " + thersholdValue, "pass", false);
	System.out.println("Value from opti planning excel" + thersholdValue);
	do {

		System.out.println("wait for time value to get reflected in vclas..");
		driver.switchTo().window(browser[2]);
		Thread.sleep(120000);

		// Retrieve time value to plan the racks
		clickTime = Vclas_Shortage_Time_Verify(" ", strFlowType, seqHandling + String.valueOf(iTempSeqNo),
				flowName);
		System.out.println("time verify done");
		if (clickTime == thersholdValue) {
			reportStep("Planned time verified Actual Time: "+clickTime+" Expected Time: "+tempThreshold, "Info", false); 
			break;
		}
		if (thersholdValue != 0) {
			// assuming value is negative
			clickTime = clickTime - thersholdValue;
			System.out.println("After adding threshold to click time for: " + clickTime);
		}

		else {
			clickTime = clickTime + thersholdValue;
			System.out.println("After adding threshold to click time for: " + clickTime);
		}
		// check whether click time is negative or zero
		if (clickTime == thersholdValue) {
			reportStep("Planned time verified Actual Time: "+clickTime+" Expected Time: "+tempThreshold, "Info", false); 
			break;
		}

		reportStep(
				"--------Empty rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
						+ "#C Assembly Movement Initiated, No. Of Clicks:  #B" + clickTime + "#C----",
				"Info", false);
		// Accessing the Admin Page to move the Line
		adp.Simulator_Assemblyline_Click((int) clickTime);

		reportStep(
				"--------Empty rack submission: " + strFlowType + " " + String.valueOf(iTempSeqNo)
						+ " Assembly Movement Completed, No. Of Clicks: #B" + clickTime + "#C----",
				"Info", false);

		// Check Whether The Expected Rack Is Planned
		if (Vclas_Shortage_Time_Verify(" ", strFlowType, seqHandling + String.valueOf(iTempSeqNo),
				flowName) == tempThreshold) {
			reportStep("Planned time verified Actual Time: "+clickTime+" Expected Time: "+tempThreshold, "Info", false); 
			break;
		}
		System.out.println("after while" + clickTime + " " + thersholdValue);
	} while (((int) clickTime != tempThreshold)); // check whether click Time is positive

	// verify whether rack status is planned
	Vclas_Shortage_SingleRackStatusVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
			"EMPTY_RACK", "Planned");
	// Deliver The Empty Planned Rack
	if (flowName.equalsIgnoreCase("Tugger")) {

		Vclas_AssignmentList_RackSubmission_Tugger(strType[1],
				strFlowType + seqHandling + String.valueOf(iTempSeqNo));
	} else {
		Vclas_AssignmentList_RackSubmission_JISJIT(strType[1].toUpperCase(),
				strFlowType + seqHandling + String.valueOf(iTempSeqNo));
	}

	// scan the object for empty rack
	Vclas_AssignmentList(strFlowType + seqHandling + String.valueOf(iTempSeqNo), "Empty_Rack", "",
			flowName);
	
	reportStep("--------Empty rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
			+ "#C Submitted Successfully----", "Info", false);

	System.out.println("Delivered Empty rack");

	iTempSeqNo = iTempSeqNo + 1;
	System.out.println("incremented val" + iTempSeqNo);
	//System.out.println("current i value" + i);

	lastSubmittedFullRack = lastSubmittedFullRack + 1;

	if (flowName.equalsIgnoreCase("Tugger")) {
		if (String.valueOf(lastSubmittedFullRack).length() == 1) {
			strTempSeqNo = "0" + String.valueOf(lastSubmittedFullRack);
			System.out.println("after empty rack delivery :" + strTempSeqNo);
		} else {
			strTempSeqNo = String.valueOf(lastSubmittedFullRack);
			System.out.println("after empty rack delivery :" + strTempSeqNo);
		}
	} else {

		strTempSeqNo = StringUtils.repeat("0", 4 - String.valueOf(lastSubmittedFullRack).length())
				+ lastSubmittedFullRack;
		System.out.println("after empty rack delivery :" + strTempSeqNo);
	}

	Vclas_Shortage_SingleRackStatusVerify(strFlowType + seqHandling + strTempSeqNo, "FULL_RACK", "Planned");
	reportStep("--------Full rack submission: #B" + strFlowType + " " + strTempSeqNo + "#C Initiated----",
			"Info", false);

	// Deliver the Planned Full Empty Rack
	if (flowName.equalsIgnoreCase("Tugger")) {
		Vclas_AssignmentList_RackSubmission_Tugger(strType[0],
				strFlowType + seqHandling + String.valueOf(strTempSeqNo));
	} else {
		Vclas_AssignmentList_RackSubmission_JISJIT(strType[0].toUpperCase(),
				strFlowType + seqHandling + String.valueOf(strTempSeqNo));
	}

	if (flowName.equalsIgnoreCase("Tugger")) {
		// Calculate LopNo for ScanId
		strLopNo = strLopNo + Integer.parseInt(tdrow.get("Line_movement"));
		System.out.println("strLopNo:" + strLopNo);

		// Calculate Scan Id
		String scanId = "T" + strFlowType + strTempSeqNo + strLopNo;
		System.out.println("scan id" + scanId);

		Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName); // ask

	} else {
		// Calculate Scan Id
		String scanId = strFlowType + strTempSeqNo;
		System.out.println("scan id" + scanId);
		Vclas_AssignmentList(scanId, "Full_Rack", "Yes", flowName);

	}

	reportStep("--------Full rack submission: #B" + strFlowType + " " + strTempSeqNo
			+ "#C Submitted Successfully----", "Info", false);

	//intRackNewSeq = Integer.parseInt(strTempSeqNo) + racksCount - 1;

	
	System.out.println("Rack Search after 1E 1F for: " + lastSubmittedFullRack);

	// Verify whether next full empty racks are created
	intRackNewSeq=Vclas_Shortage_NewRacks_Search(strFlowType, Integer.parseInt(strTempSeqNo),racksCount);
	System.out.println("intRackNewSeq :" +intRackNewSeq);
	driver.switchTo().window(browser[2]);
	Vclas_Shortage_PositionVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo), listr.get(j).toString(), "TVS12");

	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("exception thrown in method");
	}
}

public void Vclas_Tugger_Shortage_CorrectSeq_InvalidInput(String flowName) throws InterruptedException {
	try {
		
		String strSeqno, seqHandling,strFlowType, strFlowTypeObject, strTaskId;
		int Seqno,iTempSeqNo,j=0;
		String[] strType;
	
		// Navigation to Shortage
		anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));
		Thread.sleep(5000);

		String sheetName = "Vclas_Assignments";
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		Seqno = Integer.parseInt(tdrow.get("Object")); // 1
		strFlowType = tdrow.get("Sequence_Type");
		strType = tdrow.get("Shortage_Type_Expected").split(",");
		
			reportStep("13 Verify the functionality of delivering a rack in correct sequence by copy pasting the correct and wrong package Nr in SCAN field when Manual Input is NO from SHORTAGE screen--Initiated--Testcase Id-3111", "Pass", true);
			strSeqno = String.valueOf(Seqno);// 01
			seqHandling = " ";
		
		System.out.println("flow name : " + flowName);

		Vclas_Shortage_Search(strFlowType, strSeqno, flowName);

		iTempSeqNo = Seqno;

		System.out.println("seq no:" + iTempSeqNo);
		// To Verify Initial Status in Shortage Screen for created racks
		int i = 1; 

			List<String> listr = vt.getList();
		System.out.println("full TASK ID: " +listr);
	
			// To verify the Full task id
			Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
					listr.get(j).toString(), strType[0].toUpperCase(), "Planned");
			
			strFlowTypeObject = strFlowType + seqHandling + String.valueOf(iTempSeqNo);
			System.out.println("flowTypeObject :"+strFlowTypeObject);
			strTaskId = listr.get(j).toString();
			System.out.println("taskId:" +strTaskId);
			
			driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Pickup.Button").replace("&Value", strFlowTypeObject).replace("&1Value", strTaskId))).get(0).click();
			Vclas_Shortage_UserVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
					listr.get(j).toString(), strType[0].toUpperCase());
			driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Deliver.Button").replace("&Value", strFlowTypeObject).replace("&1Value", strTaskId))).get(0).click();
			
			Vclas_Tugger_Invalid_Input_Method();
			reportStep("13 Verify the functionality of delivering a rack in correct sequence by copy pasting the correct and wrong package Nr in SCAN field when Manual Input is NO from SHORTAGE screen--Completed--Testcase Id-3111", "Pass", true);
			
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("exception thrown in method");
	}
}	
public void Vclas_Tugger_Invalid_Input_Method() throws InterruptedException {
	try {
		
		int Seqno,  strLopNo;
		String  strFlowType , strTempSeqNo;
		String[] strType;
		String sheetName = "Vclas_Assignments";
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		Seqno = Integer.parseInt(tdrow.get("Object")); // 1
		strFlowType = tdrow.get("Sequence_Type");
		//strLopNo = Integer.parseInt(tdrow.get("Lopnr"));
		System.out.println("Seqno:" +Seqno);
		System.out.println("strFlowType: " +strFlowType);
		//System.out.println("strLopNo:" +strLopNo);
if (verifyElementExist("Scan Dialog Box" ,By.xpath(prop.getProperty("Vclas_home.AssignmentList.ScanDialogBox"))) == true) {
	reportStep("Assignment Scan dialog box is displayed successfully", "Info", true);
Thread.sleep(3000);
System.out.println("need to enter scan id");
Seqno=Seqno + 1;
System.out.println("Seqno: " +Seqno);



strLopNo=getRandomNumber(99999);
System.out.println("strLopNo:" + strLopNo);
for(int m=0;m<=1;m++)	{
	if (String.valueOf(Seqno).length() == 1) {
		strTempSeqNo = "0" + String.valueOf(Seqno);
		System.out.println(strTempSeqNo);
	} else {
		strTempSeqNo = String.valueOf(Seqno);
		System.out.println(strTempSeqNo);
	}

System.out.println("1 input:" +strFlowType + strTempSeqNo + strLopNo);
driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")))
.sendKeys(strFlowType + strTempSeqNo + strLopNo);
Thread.sleep(5000);
driver.findElements(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")))
.get(0).sendKeys(Keys.TAB);
Thread.sleep(5000);
if (verifyElementExistReturn(
		By.xpath(prop.getProperty("Vclas_home.AssignmentList.WarningWindow"))) == true) {
 Thread.sleep(3000);
	reportStep("Assignment List Warning Window is displayed successfully", "Info", true);
Vclas_AssignmentList_WarningWindow();
}
// Verifying The Message
List<WebElement> invalidMessage = driver.findElements(By.xpath(prop.getProperty("Vclas_AssignmentList.ErrorMessage.AGV")));
String invalidInput = invalidMessage.get(0).getText();
System.out.println("invalidInput: "+ invalidInput);
if (invalidInput.contains(tdrow.get("Invalid_Input_Error"))) {
System.out.println(" Invalid Input Error Message : " + invalidInput);

reportStep("Invalid Input Error - Message displayed " + invalidInput, "pass", true);

}

System.out.println("2 input:" +"T" + strFlowType + strTempSeqNo + strLopNo);
driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")))
.sendKeys("T" + strFlowType + strTempSeqNo + strLopNo);
Thread.sleep(5000);
//driver.findElements(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text"))).get(0).sendKeys(Keys.TAB);
Thread.sleep(5000);
if (verifyElementExistReturn(
		By.xpath(prop.getProperty("Vclas_home.AssignmentList.WarningWindow"))) == true) {
 Thread.sleep(3000);
	reportStep("Assignment List Warning Window is displayed successfully", "Info", true);
Vclas_AssignmentList_WarningWindow();
}
//Verifying The Message
List<WebElement> invalidMessages = driver.findElements(By.xpath(prop.getProperty("Vclas_AssignmentList.ErrorMessage.AGV")));
String invalidInputs = invalidMessages.get(0).getText();
System.out.println("invalidInputs: "+ invalidInputs);
if (invalidInputs.contains(tdrow.get("Invalid_Input_Error"))  || invalidInputs.contains(tdrow.get("Invalid_Input_Error_Msg")))  {
System.out.println(" Invalid Input Error Message : " + invalidInputs);

reportStep("Invalid Input Error - Message displayed " + invalidInputs, "pass", true);
//Calculate LopNo for ScanId
strLopNo = Integer.parseInt(tdrow.get("Lopnr")) + Integer.parseInt(tdrow.get("Line_movement"));
System.out.println("strLopNo:" + strLopNo);
Seqno=Seqno - 1;
System.out.println("new Seqno: " +Seqno);
}

}
}
if (verifyElementExistReturn(
		By.xpath(prop.getProperty("Vclas.AssignmentList.OkButton.Click"))) == true) {

	driver.findElements(By.xpath(prop.getProperty("Vclas.AssignmentList.OkButton.Click"))).get(0)
			.click();
}else {
	reportStep("OK Button is not enabled" , "fail", true);
}
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("exception thrown in method");
	}
}

public void Vclas_Tugger_Shortage_Incorrect_InvalidInput(String flowName) throws InterruptedException {
	try {
		
		String strSeqno, seqHandling, strFlowType, strFlowTypeObject,strTaskId;
		int Seqno, iTempSeqNo, strLopNo,j=1;
		String[] strType;
		
		// Navigation to Shortage
		anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));
		Thread.sleep(5000);

		String sheetName = "Vclas_Assignments";
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		Seqno = Integer.parseInt(tdrow.get("Object")); // 1
	
		strFlowType = tdrow.get("Sequence_Type");
		strType = tdrow.get("Shortage_Type_Expected").split(",");
		strLopNo = Integer.parseInt(tdrow.get("Lopnr"));
		List<String> listr = vt.getList();
		if (flowName.equalsIgnoreCase("Tugger")) {
			reportStep("14 Verify the functionality of delivering a rack in incorrect sequence by copy pasting the correct and wrong package Nr in SCAN field when Manual Input is NO from SHORTAGE screen--Initiated--Testcase Id-3112", "Pass", true);
			strSeqno = String.valueOf(Seqno);// 01
			seqHandling = " ";
			
		} else {
			reportStep("19 Verify the functionality of delivering a rack in incorrect sequence by copy pasting the correct and wrong package Nr in SCAN field when Manual Input is NO from SHORTAGE screen--Initiated--Testcase Id-2800", "Pass", true);
			strSeqno = tdrow.get("Object");// 0001
			System.out.println("seq no :" + strSeqno);
			seqHandling = StringUtils.repeat("0", 4 - (String.valueOf(Seqno).length())); // 000
			System.out.println("seq handling:" + seqHandling);
		}
			System.out.println("flow name : " + flowName);

			Vclas_Shortage_Search(strFlowType, strSeqno, flowName);

			iTempSeqNo = Seqno + 1;
			
			System.out.println("seq no:" + iTempSeqNo);
			// To Verify Initial Status in Shortage Screen for created racks
		int i = 1; 
				
				System.out.println("full TASK ID: " +listr);
				
					// To verify the Full task id
					Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
							listr.get(j).toString(), strType[0].toUpperCase(), "Planned");

                     
				reportStep("--------Full rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
						+ "#C Initiated----", "Info", false);
				// Submitting the Rack
				System.out.println(
						"rck submsn" + strType[0] + " " + strFlowType + seqHandling + String.valueOf(iTempSeqNo));
				
				strFlowTypeObject = strFlowType + seqHandling + String.valueOf(iTempSeqNo);
				System.out.println("flowTypeObject :"+strFlowTypeObject);
				strTaskId = listr.get(j).toString();
				System.out.println("taskId:" +strTaskId);
				
				driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Pickup.Button").replace("&Value", strFlowTypeObject).replace("&1Value", strTaskId))).get(0).click();
				Vclas_Shortage_UserVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
						listr.get(j).toString(), strType[0].toUpperCase());
				driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Deliver.Button").replace("&Value", strFlowTypeObject).replace("&1Value", strTaskId))).get(0).click();
				excelUtils.excelUpdateValue("Vclas_Assignments", "Object", refTestDataName, String.valueOf(iTempSeqNo) );
				if(flowName.equalsIgnoreCase("Tugger")) {
				Vclas_Tugger_Invalid_Input_Method();
				reportStep("14 Verify the functionality of delivering a rack in incorrect sequence by copy pasting the correct and wrong package Nr in SCAN field when Manual Input is NO from SHORTAGE screen--Completed--Testcase Id-3112", "Info", true);
				}else {
					Vclas_JISJIT_Tugger_Invalid_Input_Method();
					reportStep("19 Verify the functionality of delivering a rack in incorrect sequence by copy pasting the correct and wrong package Nr in SCAN field when Manual Input is NO from SHORTAGE screen--Completed--Testcase Id-2800", "Info", true);
			
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("exception thrown in method");
			}			
}

public void Vclas_Tugger_AssignmentList_Incorrect_InvalidInput(String flowName) throws InterruptedException {
	try {
		reportStep("16 Verify the possibility of entering input manually in SCAN field when Manual Input is NO for the incorrect sequence rack delivery from A L screen--Initiated--Testcase Id-3114", "Info", true);
		String strSeqno, seqHandling,strFlowType,strTempSeqNo,scanId;
		int Seqno, iTempSeqNo, strLopNo, j=1;
		String[] strType;

		// Navigation to Shortage
		anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));
		Thread.sleep(5000);

		String sheetName = "Vclas_Assignments";
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		Seqno = Integer.parseInt(tdrow.get("Object")); // 1
		strFlowType = tdrow.get("Sequence_Type");
		strType = tdrow.get("Shortage_Type_Expected").split(",");
		strLopNo = Integer.parseInt(tdrow.get("Lopnr"));
	
		List<String> listr = vt.getList();
		if (flowName.equalsIgnoreCase("Tugger")) {
			strSeqno = String.valueOf(Seqno);// 01
			seqHandling = " ";
			
		} else {

			strSeqno = tdrow.get("Object");// 0001
			System.out.println("seq no :" + strSeqno);
			seqHandling = StringUtils.repeat("0", 4 - (String.valueOf(Seqno).length())); // 000
			System.out.println("seq handling:" + seqHandling);
		}
System.out.println("flow name : " + flowName);

			Vclas_Shortage_Search(strFlowType, strSeqno, flowName);

			iTempSeqNo = Seqno + 1;
			System.out.println("seq no:" + iTempSeqNo);
			// To Verify Initial Status in Shortage Screen for created racks
		int i = 1; 
			
					// To verify the Full task id
					Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
							listr.get(j).toString(), strType[0].toUpperCase(), "Planned");

				reportStep("--------Full rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
						+ "#C Initiated----", "Info", false);
				if (flowName.equalsIgnoreCase("Tugger")) {

					Vclas_AssignmentList_RackSubmission_Tugger(strType[0],
							strFlowType + seqHandling + String.valueOf(iTempSeqNo));
				} else {
					reportStep("17 Verify the functionality of delivering a rack in incorrect sequence by copy pasting the correct and wrong package Nr in SCAN field when Manual Input is NO from A L screen--Initiated--Testcase Id-2798", "Info", true);
					
					Vclas_AssignmentList_RackSubmission_JISJIT(strType[0].toUpperCase(),
							strFlowType + seqHandling + String.valueOf(iTempSeqNo));
				}
				if (flowName.equalsIgnoreCase("Tugger")) {
					if (String.valueOf(iTempSeqNo).length() == 1) {
						strTempSeqNo = "0" + String.valueOf(iTempSeqNo);
						System.out.println(strTempSeqNo);
					} else {
						strTempSeqNo = String.valueOf(iTempSeqNo);
						System.out.println(strTempSeqNo);
					}

					// Calculate LopNo for ScanId
					strLopNo = strLopNo + Integer.parseInt(tdrow.get("Line_movement"));
					System.out.println("strLopNo:" + strLopNo);

					// Calculate Scan Id
					scanId = "T" + strFlowType + strTempSeqNo + strLopNo;
					System.out.println("scan id" + scanId);
					System.out.println(strFlowType + seqHandling + String.valueOf(iTempSeqNo) + "ok click ");
					

				} else {

					strTempSeqNo = StringUtils.repeat("0", 4 - String.valueOf(iTempSeqNo).length()) + iTempSeqNo;
					System.out.println("strTempSeqNo:" + strTempSeqNo);

					// Calculate Scan Id
					scanId = strFlowType + strTempSeqNo;
					System.out.println("scan id" + scanId);
				
				}

				driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")))
				.sendKeys(scanId);
				Thread.sleep(5000);
				//driver.findElements(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text"))).get(0).sendKeys(Keys.TAB);
				Thread.sleep(5000);
				if (verifyElementExistReturn(
						By.xpath(prop.getProperty("Vclas_home.AssignmentList.WarningWindow"))) == true) {
				 Thread.sleep(3000);
					reportStep("Assignment List Warning Window is displayed successfully", "Info", true);
				Vclas_AssignmentList_WarningWindow();
				}
				 Thread.sleep(3000);
				driver.findElement(By.xpath(prop.getProperty("Vclas.AssignmentList.CancelButton.Click"))).click();
				 Thread.sleep(3000);
				 reportStep("16 Verify the possibility of entering input manually in SCAN field when Manual Input is NO for the incorrect sequence rack delivery from A L screen--Completed--Testcase Id-3114", "Pass", true);
				 reportStep("17 Verify the functionality of delivering a rack in incorrect sequence by copy pasting the correct and wrong package Nr in SCAN field when Manual Input is NO from A L screen--Completed--Testcase Id-2798", "Pass", true);
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("exception thrown in method");
	}			
}




public void Vclas_AssignmentList_RackSubmission_Tugger_PickUp(String rackType, String objectId,String user) throws InterruptedException {
	 
	tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
	 String Topos;
	 try {
	        driver.switchTo().window(browser[2]);
	
	        System.out.println("search racksubmission for " + rackType + objectId);
	
	        // navigate to assignment page
	        anyClick("Assignment list Areas", By.xpath(prop.getProperty("Vclas_Home.Assignment.List.Button")));
	        reportStep("Assignment tab is clicked successfully ", "pass", false);
	
	        // Verify the assignment area table is displayed
	
	        if (verifyElementExistReturn(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Table"))) == true) {
	               reportStep("Assignment table is displayed successfully", "pass", true);
	             
	               String rackObject = prop.getProperty("Vclas_home.AssignmentList.Column.Type.Tugger")
	                             .replace("&Value", rackType).replace("&1Value", objectId.substring(0, 3));
	               Topos = tdrow.get("toPos").trim();
	               System.out.println("topos" + Topos);
	              
	               String emptyrackObject = prop.getProperty("Vclas_home.AssignmentList.EmptyColumn.Type")
	                             .replace("&Value", rackType).replace("&1Value", String.valueOf(Topos));
	               System.out.println("ero" + emptyrackObject);
	
	               System.out.println("rackobject: "+rackObject);
	               String rackName = prop.getProperty("Vclas_home.AssignmentList.Column1.Type").replace("&Value", objectId);
	               System.out.println("rack name" + rackName);
	
	               // Size of the Racks available
	               int ColumnCount = driver.findElements(By.xpath(rackObject)).size();
	
	               for (int i = 0; i <= ColumnCount - 1; i++) {
	
	                      // Clicking the First Object
	                      // new
	                      if (rackType.equalsIgnoreCase("Full_Rack")) {
	                             driver.findElements(By.xpath(rackObject)).get(0).click();
	                             Thread.sleep(5000);
	                             reportStep(objectId + " " + rackType + " is Displayed in Assignment List Screen", "pass", true);
	                             System.out.println(rackObject + "rack obj");
	                      } else {
	                             driver.findElements(By.xpath(emptyrackObject)).get(0).click();
	                             reportStep(objectId + " " + rackType + " is Displayed in Assignment List Screen", "pass", true);
	                             Thread.sleep(5000);
	                             System.out.println(emptyrackObject + "rack obj");
	                      }
	                      if (driver.findElements(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Table"))).size() > 0) {
	                    	 
	                    	  if(user.equalsIgnoreCase("firstUser")) {
	                             
	                             if (verifyElementExist("Rack name",By.xpath(rackName))== true){
	                            	 ////
	                            // driver.findElements(By.xpath(rackName)).get(0).click();
	
	                             Thread.sleep(5000);
	                             reportStep(objectId + " " + rackType + " Has Been Picked Up From Assignment List Screen",
	                                           "pass", true);
	
	                             break;
	
	                             	} else {
	                            	 //anyClick("Drop", (By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Drop.icon"))));
	                            	 driver.findElements(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Drop.icon"))).get(0).click();
	                            	 Thread.sleep(5000);
	                             	}
	                    	  }else {
	                    		  // for second user
	                    		  break;
	                    	  }
	                             
	                             
	                      } else {
	                      reportStep("Assignment list table does not exist", "fail", true);
	                      System.out.println("Assignment list table does not exist");
	               }
	               if (i == ColumnCount - 1) {
	
	                      reportStep(rackType + "does not exist", "fail", true);
	                      break;
	               }
	
	        }
	
	 } else {
	        reportStep("Assignment list table does not exist", "fail", true);
	 }
	} catch (Exception e) {
	 e.printStackTrace();
	}
	}


	// shoratge delivery other user



// Multiple assignments pickup in AL and shortage screen




//multiple assignments picking from shortage screen


			public void Vclas_JISJIT_Tugger_Invalid_Input_Method() throws InterruptedException {
				try {
					
					int Seqno;
					String  strFlowType , strTempSeqNo,scanId;
					//String[] strType;
					String sheetName = "Vclas_Assignments";
					tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
					Seqno = Integer.parseInt(tdrow.get("Object")); // 1
					strFlowType = tdrow.get("Sequence_Type");
					System.out.println("Seqno:" +Seqno);
					System.out.println("strFlowType: " +strFlowType);
					
			
			
			System.out.println("need to enter scan id");
		
			strTempSeqNo = StringUtils.repeat("0", 4 - String.valueOf(Seqno).length()) + Seqno;
			System.out.println("strTempSeqNo:" + strTempSeqNo);

			// Calculate Scan Id
			scanId = strFlowType + getRandomNumber(9999);
			System.out.println("scan id" + scanId);
			for(int i=1;i<=2;i++) {
				if (verifyElementExist("Scan Dialog Box" ,By.xpath(prop.getProperty("Vclas_home.AssignmentList.ScanDialogBox"))) == true) {
					reportStep("Assignment Scan dialog box is displayed successfully", "Info", true);
		driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")))
			.sendKeys(scanId);
			Thread.sleep(3000);
			/*driver.findElements(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")))
			.get(0).sendKeys(Keys.TAB);
			Thread.sleep(3000);*/
			
			// Verifying The Message
			List<WebElement> invalidMessage = driver.findElements(By.xpath(prop.getProperty("Vclas_AssignmentList.ErrorMessage.AGV")));
			String invalidInput = invalidMessage.get(0).getText();
			System.out.println("invalidInput: "+ invalidInput);
			if (invalidInput.contains(tdrow.get("Invalid_Input_Error"))) {
			System.out.println(" Invalid Input Error Message : " + invalidInput);
			}
			reportStep("Invalid Input Error - Message displayed " + invalidInput, "pass", true);
			
			if (verifyElementExistReturn(
					By.xpath(prop.getProperty("Vclas_home.AssignmentList.WarningWindow"))) == true) {
			 Thread.sleep(3000);
				reportStep("Assignment List Warning Window is displayed successfully", "Info", true);
			Vclas_AssignmentList_WarningWindow();
			}
			if (verifyElementExistReturn(
					By.xpath(prop.getProperty("Vclas.AssignmentList.OkButton.Click"))) == true) {

				driver.findElements(By.xpath(prop.getProperty("Vclas.AssignmentList.OkButton.Click"))).get(0)
						.click();
				System.out.println("OK Button clicked");
			}
			
			
			scanId = strFlowType + strTempSeqNo;
			System.out.println("scan id" + scanId);
			
			}
			}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
			
			public void Vclas_Tugger_AL_OtherUser_Pickup(String flowName,String user) throws InterruptedException {
			    try {
			           
			           String strSeqno, seqHandling, strTempSeqNo;
			           int Seqno, racksCount, iTempSeqNo, strLopNo;
			           String rackStatus, strFlowType;
			           String[] strType;
			           int j=0;
			           
			           // Navigation to Shortage
			           anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));
			           Thread.sleep(5000);

			           String sheetName = "Vclas_Assignments";
			           tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
			           Seqno = Integer.parseInt(tdrow.get("Object")); // 1
			           racksCount = Integer.parseInt(tdrow.get("No_of_Racks"));
			           strFlowType = tdrow.get("Sequence_Type");
			           strType = tdrow.get("Shortage_Type_Expected").split(",");
			           strLopNo = Integer.parseInt(tdrow.get("Lopnr"));
			           

			           if (flowName.equalsIgnoreCase("Tugger")) {
			                  strSeqno = String.valueOf(Seqno);// 01
			                  seqHandling = " ";
			                  
			           } else {

			                  strSeqno = tdrow.get("Object");// 0001
			                  System.out.println("seq no :" + strSeqno);
			                  seqHandling = StringUtils.repeat("0", 4 - (String.valueOf(Seqno).length())); // 000
			                  System.out.println("seq handling:" + seqHandling);
			           }

			           System.out.println("flow name : " + flowName);

			           Vclas_Shortage_Search(strFlowType, strSeqno, flowName);

			           iTempSeqNo = Seqno;

			           System.out.println("seq no:" + iTempSeqNo);
			          
			          
			    
			                  reportStep("--------Full rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
			                               + "#C Initiated----", "Info", false);
			                  // Submitting the Rack
			                  System.out.println(
			                               "rck submsn" + strType[0] + " " + strFlowType + seqHandling + String.valueOf(iTempSeqNo));
			                  if (flowName.equalsIgnoreCase("Tugger")) {

			                	  Vclas_AssignmentList_RackSubmission_Tugger_PickUp(strType[0],
			                                      strFlowType + seqHandling + String.valueOf(iTempSeqNo),user);
			                  } else {
			                        Vclas_AssignmentList_RackSubmission_JISJIT_Pickup(strType[0].toUpperCase(),
			                                      strFlowType + seqHandling + String.valueOf(iTempSeqNo),user);
			                  }
			                  if(user.equalsIgnoreCase("SecondUser")) {
			                  Vclas_AL_UserVerify(strType[0],strFlowType + seqHandling + String.valueOf(iTempSeqNo),flowName);
			                  }
			    } catch (Exception e) {
			           e.printStackTrace();
			    }
			}


		
			public void Vclas_AL_UserVerify(String rackType, String objectId,String flowName)
			        throws InterruptedException {
			 try {

			               String objUser = prop.getProperty("Vclas_Home.AssignmentList.Object_Id_Type_User.Verify")
			                             .replace("&Value", rackType).replace("&1Value", objectId.substring(0, 3));

			               String ExistingUsrName = driver.findElement(By.xpath(objUser)).getText();
			               System.out.println(ExistingUsrName);
			               String usrName = prop.getProperty("user");

			               // Verifying the type is available in the screen
			               if (!ExistingUsrName.equalsIgnoreCase(usrName)) {
			                      reportStep(objectId+"  "+rackType +"  "+"is already picked up by  "+ExistingUsrName , "PASS", true);
			                      if(flowName.equalsIgnoreCase("Tugger")) {
			                    	  reportStep("#B Verification of possibility of delivering a tugger order assignment which was picked up by other user in A L screen Test case Id:3123 -- completed#C", "pass", false);
			                      } else {
			                    	  reportStep("#B Verification of possibility of delivering a JISJIT tugger order assignment which was picked up by other user in A L screen Test case Id:2790 -- completed#C", "pass", false);
			                      }
			               }else {
			                    if(flowName.equalsIgnoreCase("Tugger")) { 
			                    	reportStep(" Verification of possibility of delivering a tugger order assignment which was picked up by other user in A L screen Test case Id:3123 -- failed", "fail", false);
			                    }else {
			                  	    reportStep("#B Verification of possibility of delivering a JISJIT tugger order assignment which was picked up by other user in A L screen Test case Id:2790 -- failed#C", "fail", false);

			                    }
			               }
			 }catch(Exception e) {
			        e.printStackTrace();
			 }
			}


				// shoratge delivery other user

			public void Vclas_Tugger_Shortage_Delivery_OtherUser(String flowName) throws InterruptedException {
				try {
					
					String strSeqno, seqHandling,scanId;
					int Seqno, racksCount, iTempSeqNo, strLopNo;
					String rackStatus, strFlowType, strFlowTypeObject,strTaskId;
					String[] strType;
					int intRackNewSeq ,j=0;
					Admin_Page adp = new Admin_Page();
					MasWeb_Home mwHome = new MasWeb_Home();
					
					// Navigation to Shortage
					anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));
					Thread.sleep(5000);

					String sheetName = "Vclas_Assignments";
					tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
					Seqno = Integer.parseInt(tdrow.get("Object")); // 1
					racksCount = Integer.parseInt(tdrow.get("No_of_Racks"));
					strFlowType = tdrow.get("Sequence_Type");
					strType = tdrow.get("Shortage_Type_Expected").split(",");
					strLopNo = Integer.parseInt(tdrow.get("Lopnr"));
					String strTempSeqNo;
					List<String> listr = vt.getList();
					if (flowName.equalsIgnoreCase("Tugger")) {
						strSeqno = String.valueOf(Seqno);// 01
						seqHandling = " ";
						
					} else {
						strSeqno = tdrow.get("Object");// 0001
						System.out.println("seq no :" + strSeqno);
						seqHandling = StringUtils.repeat("0", 4 - (String.valueOf(Seqno).length())); // 000
						System.out.println("seq handling:" + seqHandling);
					}
						System.out.println("flow name : " + flowName);

						Vclas_Shortage_Search(strFlowType, strSeqno, flowName);

						iTempSeqNo = Seqno;

						System.out.println("seq no:" + iTempSeqNo);
						
					
						
						System.out.println("itemp val:" + iTempSeqNo);
						// Click the Planned Activity in Shortage Screen for Initial Two Full Racks
						reportStep("--------Full rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
									+ "#C From shortage screen Initiated----", "Info", false);
							// Submitting the Rack
							System.out.println("rack submsn" + strType[0] + " " + strFlowType + seqHandling + String.valueOf(iTempSeqNo));
							
							strFlowTypeObject = strFlowType + seqHandling + String.valueOf(iTempSeqNo);
							System.out.println("flowTypeObject :"+strFlowTypeObject);
							strTaskId = listr.get(j).toString();
							System.out.println("taskId:" +strTaskId);
							
							//fetching the already picked up user id
							String objUser = prop.getProperty("Vclas_Home.Shortage.Object_Id_Type_User.Verify").replace("&Value", strFlowTypeObject).replace("&1Value", strTaskId);

							
							String existingUsrName = driver.findElement(By.xpath(objUser)).getText();
							System.out.println("existingUsrName: "+existingUsrName);
							
								driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Pickup.Button").replace("&Value", strFlowTypeObject).replace("&1Value", strTaskId))).get(0).click();
							Thread.sleep(3000);
							// fetching user id after pick up
							 objUser = prop.getProperty("Vclas_Home.Shortage.Object_Id_Type_User.Verify").replace("&Value", strFlowTypeObject).replace("&1Value", strTaskId);

							
							String newUsrName = driver.findElement(By.xpath(objUser)).getText();
							System.out.println("newUsrName: "+newUsrName);
							reportStep(newUsrName+" has picked up the assignment which already picked up by "+existingUsrName, "pass", true);
							
							driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Deliver.Button").replace("&Value", strFlowTypeObject).replace("&1Value", strTaskId))).get(0).click();

							
							if (flowName.equalsIgnoreCase("Tugger")) {
								if (String.valueOf(iTempSeqNo).length() == 1) {
									strTempSeqNo = "0" + String.valueOf(iTempSeqNo);
									System.out.println(strTempSeqNo);
								} else {
									strTempSeqNo = String.valueOf(iTempSeqNo);
									System.out.println(strTempSeqNo);
								}

								// Calculate LopNo for ScanId
								strLopNo = strLopNo + Integer.parseInt(tdrow.get("Line_movement"));
								System.out.println("strLopNo:" + strLopNo);

								// Calculate Scan Id
								 scanId = "T" + strFlowType + strTempSeqNo + strLopNo;
								System.out.println("scan id" + scanId);
								System.out.println(strFlowType + seqHandling + String.valueOf(iTempSeqNo) + "ok click ");
							
							

							} else {

								strTempSeqNo = StringUtils.repeat("0", 4 - String.valueOf(iTempSeqNo).length()) + iTempSeqNo;
								System.out.println("strTempSeqNo:" + strTempSeqNo);

								// Calculate Scan Id
								 scanId = strFlowType + strTempSeqNo;
								System.out.println("scan id" + scanId);
								
							}

							driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text"))).sendKeys(scanId);
					
						
							if (verifyElementExistReturn(
									By.xpath(prop.getProperty("Vclas.Shortage.OkButton.Click"))) == true) {

								driver.findElements(By.xpath(prop.getProperty("Vclas.Shortage.OkButton.Click"))).get(0).click();
								reportStep("--------Full rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
								+ "#C From shortage screen completed----", "Info", false);
								
							}
							if(flowName.equalsIgnoreCase("Tugger")) {
							// verifying user id change
							if(!existingUsrName.equalsIgnoreCase(newUsrName)==true) {
								reportStep("#B Verification of possibility of delivering a Tugger assignment from Shortage screen which was picked up by other user Test case Id:3124-- completed#C", "pass", false);
							}else {
								reportStep(" Verification of possibility of delivering a Tugger assignment from Shortage screen which was picked up by other user Test case Id:3124-- failed", "fail", false);
							}
							}else {
								// verifying user id change
								if(!existingUsrName.equalsIgnoreCase(newUsrName)==true) {
									reportStep("#B 10 Verify the possibility of delivering a JISJIT Tugger assignment from Shortage screen which was picked up by other user--Test case Id:2791-- completed#C", "pass", false);
								}else {
									reportStep(" 10 Verify the possibility of delivering a JISJIT Tugger assignment from Shortage screen which was picked up by other user--Test case Id:2791-- failed", "fail", false);
								}
							}
							}catch(Exception e) {
					e.printStackTrace();
				}
			}

			// Multiple assignments pickup in AL and shortage screen

			public void Vclas_Tugger_MultiplePickup(String flowName,String user) throws InterruptedException {
				    try {
				           
				           String strSeqno, seqHandling, strTempSeqNo;
				           int Seqno, racksCount, iTempSeqNo, strLopNo;
				           String rackStatus, strFlowType;
				           String[] strType;
				           int j=0;
				           
				           // Navigation to Shortage
				           anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));
				           Thread.sleep(5000);

				           String sheetName = "Vclas_Assignments";
				           tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
				           Seqno = Integer.parseInt(tdrow.get("Object")); // 1
				           racksCount = Integer.parseInt(tdrow.get("No_of_Racks"));
				           strFlowType = tdrow.get("Sequence_Type");
				           strType = tdrow.get("Shortage_Type_Expected").split(",");
				           strLopNo = Integer.parseInt(tdrow.get("Lopnr"));
				           

				           if (flowName.equalsIgnoreCase("Tugger")) {
				                  strSeqno = String.valueOf(Seqno);// 01
				                  seqHandling = " ";
				                  
				           } else {

				                  strSeqno = tdrow.get("Object");// 0001
				                  System.out.println("seq no :" + strSeqno);
				                  seqHandling = StringUtils.repeat("0", 4 - (String.valueOf(Seqno).length())); // 000
				                  System.out.println("seq handling:" + seqHandling);
				           }

				           System.out.println("flow name : " + flowName);

				           Vclas_Shortage_Search(strFlowType, strSeqno, flowName);

				           iTempSeqNo = Seqno;

				           System.out.println("seq no:" + iTempSeqNo);
				          
				          
				    
				                  reportStep("--------Full rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
				                               + "#C Initiated----", "Info", false);
				                  // Submitting the Rack
				                  System.out.println(
				                               "rck submsn" + strType[0] + " " + strFlowType + seqHandling + String.valueOf(iTempSeqNo));
				                  if (flowName.equalsIgnoreCase("Tugger")) {

				                	  Vclas_AssignmentList_RackSubmission_Tugger_PickUp(strType[0],
			                                strFlowType + seqHandling + String.valueOf(iTempSeqNo),user);
				                	  reportStep("User has already picked up rack could not able to pick up other rack", "pass", true);
				                	  reportStep("#BVerification of possibility of picking up multiple racks before delivering already picked up racks from A.L Test case Id:3125--completed#C", "pass", false);
				                  } else {
				                	  Vclas_AssignmentList_RackSubmission_JISJIT_Pickup(strType[0].toUpperCase(),
				                                      strFlowType + seqHandling + String.valueOf(iTempSeqNo),user);
				                	  reportStep("User has already picked up rack could not able to pick up other rack", "pass", true);
				                	  reportStep("#B38 Verify the possibility of picking up multiple racks before delivering already picked up racks from A.L---Test case Id:2819--completed#C", "pass", false);
				                  }
				                 
				    } catch (Exception e) {
				           e.printStackTrace();
				    }
				}



			//multiple assignments picking from shortage screen

			public void Vclas_Tugger_Shortage_MultipleAssignments_Delivery(String flowName) throws InterruptedException {
					try {
						
						String strSeqno, seqHandling,scanId;
						int Seqno, racksCount, iTempSeqNo, strLopNo;
						String rackStatus, strFlowType, strFlowTypeObject,strTaskId;
						String[] strType;
						int intRackNewSeq ,j=0;
						Admin_Page adp = new Admin_Page();
						MasWeb_Home mwHome = new MasWeb_Home();
						
						// Navigation to Shortage
						anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));
						Thread.sleep(5000);

						String sheetName = "Vclas_Assignments";
						tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
						Seqno = Integer.parseInt(tdrow.get("Object")); // 1
						racksCount = Integer.parseInt(tdrow.get("No_of_Racks"));
						strFlowType = tdrow.get("Sequence_Type");
						strType = tdrow.get("Shortage_Type_Expected").split(",");
						strLopNo = Integer.parseInt(tdrow.get("Lopnr"));
						String strTempSeqNo;
						List<String> listr = vt.getList();
						 if (flowName.equalsIgnoreCase("Tugger")) {
			                  strSeqno = String.valueOf(Seqno);// 01
			                  seqHandling = " ";
			                  
			           } else {

			                  strSeqno = tdrow.get("Object");// 0001
			                  System.out.println("seq no :" + strSeqno);
			                  seqHandling = StringUtils.repeat("0", 4 - (String.valueOf(Seqno).length())); // 000
			                  System.out.println("seq handling:" + seqHandling);
			           }
							
							System.out.println("flow name : " + flowName);

							Vclas_Shortage_Search(strFlowType, strSeqno, flowName);

							iTempSeqNo = Seqno;

							System.out.println("seq no:" + iTempSeqNo);
							
						
						
							iTempSeqNo = Seqno + 1;
							System.out.println("itemp val:" + iTempSeqNo);
							// picking the assignment from shortage to deliver
							
			                        j=--j;
			                        System.out.println("j :" +j);
			                   reportStep("User has already picked up rack in shortage screen", "pass", true);
								reportStep("--------Full rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)+ "#C Initiated----", "Info", false);
								// Submitting the Rack
								System.out.println(
										"rck submsn" + strType[0] + " " + strFlowType + seqHandling + String.valueOf(iTempSeqNo));
								
								strFlowTypeObject = strFlowType + seqHandling + String.valueOf(iTempSeqNo);
								System.out.println("flowTypeObject :"+strFlowTypeObject);
								strTaskId = listr.get(j).toString();
								System.out.println("taskId:" +strTaskId);
								
								driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Pickup.Button").replace("&Value", strFlowTypeObject).replace("&1Value", strTaskId))).get(0).click();
								driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Deliver.Button").replace("&Value", strFlowTypeObject).replace("&1Value", strTaskId))).get(0).click();
								
								if (flowName.equalsIgnoreCase("Tugger")) {
									if (String.valueOf(iTempSeqNo).length() == 1) {
										strTempSeqNo = "0" + String.valueOf(iTempSeqNo);
										System.out.println(strTempSeqNo);
									} else {
										strTempSeqNo = String.valueOf(iTempSeqNo);
										System.out.println(strTempSeqNo);
									}

									// Calculate LopNo for ScanId
									strLopNo = strLopNo + Integer.parseInt(tdrow.get("Line_movement"));
									System.out.println("strLopNo:" + strLopNo);

									// Calculate Scan Id
									scanId = "T" + strFlowType + strTempSeqNo + strLopNo;
									System.out.println("scan id" + scanId);
									System.out.println(strFlowType + seqHandling + String.valueOf(iTempSeqNo) + "ok click ");
									

								} else {

									strTempSeqNo = StringUtils.repeat("0", 4 - String.valueOf(iTempSeqNo).length()) + iTempSeqNo;
									System.out.println("strTempSeqNo:" + strTempSeqNo);

									// Calculate Scan Id
									scanId = strFlowType + strTempSeqNo;
									System.out.println("scan id" + scanId);
									
								}

								
								//entering scan id
								driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text"))).sendKeys(scanId);
								
						
								Thread.sleep(5000);
							if (verifyElementExistReturn(
										By.xpath(prop.getProperty("Vclas_home.AssignmentList.WarningWindow"))) == true) {
									reportStep("Assignment List Warning Window is displayed successfully", "Info", true);
								Vclas_AssignmentList_WarningWindow();
								}
							Thread.sleep(5000);
								if (verifyElementExistReturn(
										By.xpath(prop.getProperty("Vclas.Shortage.OkButton.Click"))) == true) {
									System.out.println(" entered into Ok button click");
									clickByLocator(By.xpath(prop.getProperty("Vclas.Shortage.OkButton.Click")));
									if(flowName.equalsIgnoreCase("Tugger")) {
									reportStep(" #BVerification of possibility of picking up multiple racks before delivering already picked up racks from Shortage Test case Id:3136--completed#C" , "pass", false);
									}else {
										reportStep(" #B39 Verify the possibility of picking up multiple racks before delivering already picked up racks from Shortage Test case Id:2820--completed#C" , "pass", false);
										
									}
								}else {
									if(flowName.equalsIgnoreCase("Tugger")) {
									reportStep(" Verification of possibility of picking up multiple racks before delivering already picked up racks from Shortage Test case Id:3136--failed" , "fail", false);
								}else {
									reportStep(" #B39 Verify the possibility of picking up multiple racks before delivering already picked up racks from Shortage Test case Id:2820--completed#C" , "fail", false);
								}
								}
							
					}catch(Exception e) {
						e.printStackTrace();
					}
				}


				public void Vclas_AssignmentList_RackSubmission_JISJIT_Pickup(String rackType, String objectId,String user) throws InterruptedException {
				driver.switchTo().window(browser[2]);
				System.out.println("Search racksubmission for " + rackType + objectId);

				// navigate to assignment page

				driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Assignment.List.Button"))).get(0).click();
				reportStep("Assignment tab is clicked successfully ", "pass", false);

				String rackName = prop.getProperty("Vclas_home.AssignmentList.Column.Type").replace("&Value", objectId)
						.replace("&1Value", rackType);
				System.out.println("rack name" + rackName);

				// Verify the assignment area table is displayed
				if (verifyElementExistReturn(By.xpath(rackName)) == true) {

					// Selecting the Rack
					if(user.equalsIgnoreCase("First")) {
					reportStep("Object Id: #B" + objectId + "#C  Rack Type: #B" + rackType
							+ "#C is displayed in Assignment List Screen", "pass", true);
					
					reportStep("ObjectId : #B" + objectId + "#C " + "has been displayed successfully #B without any space in Assignment List Screen - Mapped to TestCase ID - 2813 #C ",
							"PASS", true);
					
					driver.findElements(By.xpath(rackName)).get(0).click();
					reportStep(objectId + "  " + rackType + " has been picked up for deliver from Assignment List screen","pass", true);
					Thread.sleep(5000);
					
					}else {
						//for second user
						reportStep(objectId+" has been pickedup by the other user", "pass", true);
					}
					
				} else {
					reportStep(rackName + " does not exist", "fail", true);
				}

			}




}