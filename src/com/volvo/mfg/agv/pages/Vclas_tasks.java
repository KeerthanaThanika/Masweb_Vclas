package com.volvo.mfg.agv.pages;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;

import com.relevantcodes.extentreports.ExtentTest;

import com.volvo.automation.commonutilities.CommonWrapperMethods;
import com.volvo.automation.commonutilities.ExcelUtils;
import com.volvo.mfg.StepDefinition.BaseTest;
import com.volvo.mfg.StepDefinition.DriverSuite;

import junit.framework.Assert;;

public class Vclas_tasks extends DriverSuite {

	String sheetName = "Vclas_tasks";
	public HashMap<String, String> tdrow;
	public int colTaskEvents, tomracksTaskId; 
	public static int j=0;
	public static String tableIndex = ""; 
	// Excel class object to access the function
	ExcelUtils excelUtils = new ExcelUtils();
	static  List<String> al = new ArrayList<String>();
static List<String> al1= new ArrayList<String>();
	public	String add1;
	// Navigate to the Vclas task
	public void Navigate_Vclas_Task() {

		// Navigate the Page
		anyClick("Vclas Menu", By.xpath(prop.getProperty("Masweb_Home.Vclas.Menu")));
		anyClick("Vclas Task", By.xpath(prop.getProperty("Masweb_Home.Vclas.Task.Menu")));

		// Verify the Page is displayed
		verifyElementExist("Vclas tasks Page", By.xpath(prop.getProperty("Vclas_tasks.TaskId.Text")));

	}

	public void Vclas_Task_Search() throws InterruptedException {

		// retrieve data from MasWeb_Page
		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);

		// Enter the Search Value
		// Enter task id
		if (!tdrow.get("TaskID").equals("")) {
			sendKeys("TaskID", By.xpath(prop.getProperty("Vclas_tasks.TaskId.Text")), tdrow.get("TaskID"));
		}

		// Enter Object
		if (!tdrow.get("Object").equals("")) {
			sendKeys("Object", By.xpath(prop.getProperty("Vclas_tasks.Object.Text")), tdrow.get("Object"));
		}

		// Enter Fpos
		if (!tdrow.get("Fpos").equals("")) {
			sendKeys("Fpos", By.xpath(prop.getProperty("Vclas_tasks.Fpos.Text")), tdrow.get("Fpos"));
		}

		// Enter Tpos
		if (!tdrow.get("Tpos").equals("")) {
			sendKeys("Tpos", By.xpath(prop.getProperty("Vclas_tasks.Tpos.Text")), tdrow.get("Tpos"));
		}

		// Enter PartNumber
		if (!tdrow.get("PartNumber").equals("")) {
			sendKeys("PartNumber", By.xpath(prop.getProperty("Vclas_tasks.Part.Number.Text")), tdrow.get("PartNumber"));
		}

		// Select Radio button
		if (!tdrow.get("Factory").equals("")) {
			selectRadioButtonByValue("Factory", By.xpath(prop.getProperty("Vclas_tasks.Factory.Radio")),
					tdrow.get("Factory"));
		}

		// Select Radio button
		if (!tdrow.get("Flow_Type").equals("")) {
			selectRadioButtonByValue("Flow_Type", By.xpath(prop.getProperty("Vclas_tasks.Flow.Type.Radio")),
					tdrow.get("Flow_Type"));
		}

		// Select Radio button
		if (!tdrow.get("Task_Type").equals("")) {
			selectRadioButtonByValue("Task_Type", By.xpath(prop.getProperty("Vclas_tasks.Task.Type.Radio")),
					tdrow.get("Task_Type"));
		}

		// Enter Created from time
		if (!tdrow.get("Created_From_Date").equals("")) {
			sendKeys("Created_From_Date", By.xpath(prop.getProperty("Vclas_tasks.Created.From.Text")),
					tdrow.get("Created_From_Date"));
		}

		// Enter Created to time
		if (!tdrow.get("Created_To_Date").equals("")) {
			sendKeys("Created_To_Date", By.xpath(prop.getProperty("Vclas_tasks.Created.To.Text")),
					tdrow.get("Created_To_Date"));
		}

		// Select the drop down entries
		if (!tdrow.get("Show_Entries").equals("")) {
			selectDropDownValue("Show_Entries", By.xpath(prop.getProperty("Vclas_tasks.Entries.Text")),
					tdrow.get("Show_Entries"));
		}

		int iTemp = 0;
		do {

			anyClick("Search Vclas task - Display Button", By.xpath(prop.getProperty("Vclas_tasks.Display.Button")));
			Thread.sleep(2000);
			if (driver.findElements(By.xpath(prop.getProperty("Vclas_tasks.RCPAS.Table") + "/tbody/tr")).size() > 0) {
				reportStep("Vclas_task-Table displayed successfully", "PASS", false);
				break;
			}
			iTemp = iTemp + 1;

		} while (!(iTemp == 20));

		if (iTemp == 20) {
			reportStep("Vclas_task-Table displayed successfully", "Fail", true);
		}

		// Clearing the Memory
		tdrow.clear();

	}


	// Vclas task to retrieve the assigned task
	public void Vclas_getAGVTask() throws InterruptedException {

 		// Load Test Data Filex
 		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);

 		String tableHeaderColumn = prop.getProperty("Vclas_tasks.RCPAS.Table") + "/tbody/tr[1]";
 		int colTask = htmlTableColumnNamePosition("Taskid", tableHeaderColumn);
 		int colTaskInformation = htmlTableColumnNamePosition("Task information", tableHeaderColumn);
 		int colTaskEvents = htmlTableColumnNamePosition("Task events", tableHeaderColumn);
 		String fullTaskId = "", emptyTaskId = "", strObjectValue = "";
 		int iTemp = 0;
 		System.out.println("");

 		reportStep("-------- VCLAS Task creation Verify Initiated --------", "Info", false);
 		do {
 			// nullify the Default wait time.
 			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
 			if (driver
 					.findElements(By
 							.xpath(prop.getProperty("Vclas_tasks.AGV.Data.Table").replace("&Value", tdrow.get("FID"))))
 					.size() > 0) {
 				// Reset to Default timeout
 				driver.manage().timeouts().implicitlyWait(Default_Wait_4_Page, TimeUnit.SECONDS);
 				List<WebElement> tableRows = driver
 						.findElements(By.xpath(prop.getProperty("Vclas_tasks.RCPAS.Table") + "/tbody/tr"));
 				int tableSize = driver.findElements(By.xpath(prop.getProperty("Vclas_tasks.RCPAS.Table"))).size();
 				System.out.println(tableSize);
 				if (tableSize == 2) {
 					tableIndex = "2";
 				} else {
 					tableIndex = "1";
 				}

 				for (int i = 2; i <= tableRows.size(); i++) {

 					String objectTableData = prop.getProperty("Vclas_tasks.RCPAS.Table") + "[" + tableIndex
 							+ "]/tbody/tr[" + i + "]/td[" + (colTask + 1) + "]";
 					String objectTableTaskInfo = prop.getProperty("Vclas_tasks.RCPAS.Table") + "[" + tableIndex
 							+ "]/tbody/tr[" + i + "]/td[" + (colTaskInformation + 1) + "]/table/tbody/tr";

 					// To get the task Id from Vclas_task
 					String objTaskTable = objectTableData + "/table/tbody/tr[1]/td[1]"; // Take the value of Task ID
 																						
 																						
 					String objTaskRow = objectTableData + "/table/tbody/tr[1]/td[2]/table/tbody/tr";

 					List<WebElement> tableTaskRows = driver.findElements(By.xpath(objTaskRow));

 					for (int k = 0; k <= tableTaskRows.size() - 1; k++) {

 						if (tableTaskRows.get(k).findElements(By.tagName("td")).get(0).getText().toLowerCase()
 								.contains(tdrow.get("FID"))) {
 							if (tableTaskRows.get(0).findElements(By.tagName("td")).get(0)
 									.findElements(By.tagName("strong")).get(0).getText().toLowerCase()
 									.contains("full")) {
 								System.out.println();
 								fullTaskId = driver.findElement(By.xpath(objTaskTable)).getText();
 								strObjectValue = getObjectId(objectTableTaskInfo, "object");
 								reportStep("ObjectId: " + strObjectValue + " Full task id: " + fullTaskId
 										+ " full rack created second successfully", "Info", false);
 								break;
 							} else if (tableTaskRows.get(0).findElements(By.tagName("td")).get(0)
 									.findElements(By.tagName("strong")).get(0).getText().toLowerCase()
 									.contains("empty")) {
 								emptyTaskId = driver.findElement(By.xpath(objTaskTable)).getText();
 								reportStep("ObjectId: " + strObjectValue + " Empty task id: " + emptyTaskId
 										+ " Empty Rack Created First successfully", "Info", false);
 								break;
 							} else if (k == tableTaskRows.size() - 1) {
 								reportStep("Not task generated for FID: " + tdrow.get("FID"), "Fail", true);
 								reportStep("Empty task id: " + emptyTaskId + " Full task id: " + fullTaskId
 										+ " tasks not genereated", "Fail", false);
 								break;
 							}

 						}
 					}
 					if ((!emptyTaskId.equals("")) && (!fullTaskId.equals(""))) {
 						reportStep("Empty task id: " + emptyTaskId + " Full task id: " + fullTaskId
 								+ " tasks genereated successfully", "Pass", false);
 						excelUtils.excelUpdateValue("Vclas_Assignments", "Full_Task_1", refTestDataName, fullTaskId);
 						excelUtils.excelUpdateValue("Vclas_Assignments", "Empty_Task_1", refTestDataName, emptyTaskId);
 						excelUtils.excelUpdateValue("Vclas_Assignments", "Object", refTestDataName, strObjectValue);
 						break;
 					}
 				}
 				if (emptyTaskId.equals("") || fullTaskId.equals("")) {
 					reportStep("Empty task id: " + emptyTaskId + " Full task id: " + fullTaskId
 							+ " tasks not genereated successfully", "Fail", true);
 					return;
 				} else {
 					if (Vclas_VerifyTaskEvents_Ack(emptyTaskId, prop.getProperty("Vclas_tasks.RCPAS.Table"),
 							colTaskEvents) == true) {
 						reportStep("AGV Object Id.: #B" + strObjectValue + "#C Empty task id: #B" + emptyTaskId
 								+ "#C tasks ACKNOWLEDGED successfully", "Pass", false);
 						reportStep("AGV Object Id.: #B" + strObjectValue + "#C Empty task id: #B" + emptyTaskId
 								+ "#C tasks ACKNOWLEDGED successfully", "Info", false);
 					} else {
 						reportStep("AGV Object Id.:" + strObjectValue + " Empty task id: " + emptyTaskId
 								+ " tasks ACKNOWLEDGED successfully", "Fail", true);
 					}

 					if (Vclas_VerifyTaskEvents_Ack(fullTaskId, prop.getProperty("Vclas_tasks.RCPAS.Table"),
 							colTaskEvents) == true) {
 						reportStep("AGV Object Id.: #B" + strObjectValue + "#C Full task id: #B" + fullTaskId
 								+ "#C tasks ACKNOWLEDGED successfully", "Pass", false);
 						reportStep("AGV Object Id.: #B" + strObjectValue + "#C Full task id: #B" + fullTaskId
 								+ "#C tasks ACKNOWLEDGED successfully", "Info", false);
 					} else {
 						reportStep("AGV Object Id.:" + strObjectValue + " Full task id: " + fullTaskId
 								+ " tasks ACKNOWLEDGED successfully", "Fail", false);
 					}
 					reportStep("-------- VCLAS Task creation Verify Completed --------", "Info", false);

 					return;
 				}

 			} else {
 				anyClick("Loop to Search Vclas task Submitted Machine Id- Display Button",
 						By.xpath(prop.getProperty("Vclas_tasks.Display.Button")));
 				Thread.sleep(2000);
 				iTemp = iTemp + 1;
 			}
 		} while (!(iTemp == 30));

 		if (iTemp == 30) {
 			reportStep("FID: " + tdrow.get("FID") + " not reflected in the Vclas tasks", "Fail", true);
 			reportStep("-------- VCLAS Task creation Verify Failed --------", "Info", false);
 			//assertEquals("",tdrow.get("FID"));
 			
 		}

 		// Clear the excel object
 		tdrow.clear();

 	}


	public String getObjectId(String tableObj, String compValue) {
		String sReturn = "";

		List<WebElement> tableTaskRows = driver.findElements(By.xpath(tableObj));

		for (int i = 0; i <= tableTaskRows.size() - 1; i++) {

			if (tableTaskRows.get(i).findElements(By.tagName("td")).get(0).getText().toLowerCase()
					.contains(compValue.toLowerCase())) {

				sReturn = tableTaskRows.get(i).findElements(By.tagName("td")).get(1).getText().trim();
				System.out.println(compValue + " Object Value: " + sReturn);
				break;
			}
		}

		return sReturn;
	}

	public void Vclas_getRacksTaskId(String flowName) throws InterruptedException {

		// Load Test Data
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		int rackCount = Integer.parseInt(tdrow.get("No_of_Racks"));

		System.out.println("rack cnt in vclas:" + rackCount);

		String objValue = tdrow.get("Object");
		
		
		for (int i = 1; i <= rackCount; i++) {
			//anyClick("Clear button", By.xpath(prop.getProperty("Vclas_tasks.Clear.Button")));
			Vclas_Search_Partno();
			if(flowName.equalsIgnoreCase("JISJIT")) {
				String seqHandle = StringUtils.repeat("0", 4 - (String.valueOf(objValue).length()));
				objValue=seqHandle + objValue;
				 switch (objValue) {
		            case "0001":  
		            reportStep("27 Verify the functionality of Rack number displaying in Masweb when user starts with 0001 for JISJIT tugger in Shortage and A L screens--Testcase ID-2808--Completed", "Info", false);
		                     break;
		            case "0095":
		            reportStep("28 Verify the functionality of Rack number displaying in Masweb when user starts with 0095 for JISJIT tugger in Shortage and A L screens--Testcase ID-2809--Completed", "Info", false);
		                     break;
		            case "0995":
		            reportStep("29 Verify the functionality of Rack number displaying in Masweb when user starts with 0995 for JISJIT tugger in Shortage and A L screens--Testcase ID-2810--Completed", "Info", false);
		                     break;
		                     default:
		                    	 break;
				
				 }
				
				
			}else {
			if (objValue.length() == 1) {
				objValue = "0" + objValue;
			}
			}
			Vclas_getTuggerTask(tdrow.get("PartNumber"), objValue, i , " ");

			objValue = String.valueOf(Integer.parseInt(objValue) + 1);
		}

	}

	public void Vclas_Search_Partno() throws InterruptedException {
		// retrieve data from MasWeb_Page
		// Load Test Data File
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);

		// Enter PartNumber
		if (!tdrow.get("PartNumber").equals("")) {
			sendKeys("PartNumber", By.xpath(prop.getProperty("Vclas_tasks.Part.Number.Text")), tdrow.get("PartNumber"));
		}

		// Select Radio button
		if (!tdrow.get("Factory").equals("")) {
			selectRadioButtonByValue("Factory", By.xpath(prop.getProperty("Vclas_tasks.Factory.Radio")),
					tdrow.get("Factory"));
		}

		// Select Radio button
		if (!tdrow.get("Flow_Type").equals("")) {
			selectRadioButtonByValue("Flow_Type", By.xpath(prop.getProperty("Vclas_tasks.Flow.Type.Radio")),
					tdrow.get("Flow_Type"));
		}

		// Select Radio button
		if (!tdrow.get("Task_Type").equals("")) {
			selectRadioButtonByValue("Task_Type", By.xpath(prop.getProperty("Vclas_tasks.Task.Type.Radio")),
					tdrow.get("Task_Type"));
		}

		int iTemp = 0;

		do {

			anyClick("Search Vclas task - Display Button", By.xpath(prop.getProperty("Vclas_tasks.Display.Button")));
			reportStep("Display button is clicked successfully", "PASS", true);
			Thread.sleep(2000);
			if (driver.findElements(By.xpath(prop.getProperty("Vclas_tasks.RCPAS.Table") + "/tbody/tr")).size() > 1) {
				reportStep("Vclas_task-Table displayed successfully", "PASS", false);
				break;
			}
			iTemp = iTemp + 1;

		} while (!(iTemp == 20));

		if (iTemp == 20) {
			reportStep("Vclas_task-Table displayed successfully", "Fail", true);
		}

	}

	// Vclas task to retrieve the assigned task
	public void Vclas_getTuggerTask(String Partno, String Object, int RacksNo, String flow) throws InterruptedException {
		try {

			// Load Test Data
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);

			String tableHeaderColumn = prop.getProperty("Vclas_tasks.RCPAS.Table") + "/tbody/tr[1]";

			int colTask = htmlTableColumnNamePosition("Taskid", tableHeaderColumn);
			int colTaskInformation = htmlTableColumnNamePosition("Task information", tableHeaderColumn);
			int colTaskEvents = htmlTableColumnNamePosition("Task events", tableHeaderColumn);
			String racksTaskId = "", tomracksTaskId = "";
			int iTemp = 0;

			do {
				// nullify the Default wait time.
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

				if (driver.findElements(By.xpath(prop.getProperty("Vclas_tasks.Tugger.Data.Table")
						.replace("&Value", Partno).replace("&1Value", Object))).size() >= 2) {
					// Reset to Default timeout
					driver.manage().timeouts().implicitlyWait(Default_Wait_4_Page, TimeUnit.SECONDS);
					List<WebElement> tableRows = driver
							.findElements(By.xpath(prop.getProperty("Vclas_tasks.RCPAS.Table") + "/tbody/tr"));
					int tableSize = driver.findElements(By.xpath(prop.getProperty("Vclas_tasks.RCPAS.Table"))).size();
					System.out.println(tableSize);
					if (tableSize == 2) {
						tableIndex = "2";
					} else {
						tableIndex = "1";
					}

					for (int i = 2; i <= tableRows.size(); i++) {

						String objectTableData = prop.getProperty("Vclas_tasks.RCPAS.Table") + "[" + tableIndex
								+ "]/tbody/tr[" + i + "]/td[" + (colTask + 1) + "]";
						String objectTableTaskInfo = prop.getProperty("Vclas_tasks.RCPAS.Table") + "[" + tableIndex
								+ "]/tbody/tr[" + i + "]/td[" + (colTaskInformation + 1) + "]/table/tbody/tr";
						String objectTableTaskEvents = prop.getProperty("Vclas_tasks.RCPAS.Table") + "[" + tableIndex
								+ "]/tbody/tr[" + i + "]/td[" + (colTaskEvents + 1) + "]/table/tbody/tr";

						// To get the task Id from Vclas_task
						String objTaskTable = objectTableData + "/table/tbody/tr[1]/td[1]";

						String objTaskRow = objectTableData + "/table/tbody/tr[1]/td[2]/table/tbody/tr";

						System.out.println(" " + objTaskTable);
						System.out.println(" " + objTaskRow);
						// Row taskInfo

						List<WebElement> tableTaskRows = driver.findElements(By.xpath(objTaskRow));

						if (getObjectId(objectTableTaskInfo, "Partno.").equalsIgnoreCase(Partno)
								&& getObjectId(objectTableTaskInfo, "Object.").equalsIgnoreCase(Object)) {
							
							reportStep("----VCLAS Task creation Verify - Sequence No: #B" + Partno + "#C Rack No.: #B"
									+ Object + "#C Initiated----", "Info", false);

							String fullRack = tableTaskRows.get(0).findElements(By.tagName("td")).get(0)
									.findElements(By.tagName("strong")).get(0).getText().substring(0, 5);
							if (fullRack.toLowerCase().contains("racks")) {

								
								racksTaskId = driver.findElement(By.xpath(objTaskTable)).getText();
								reportStep(
										"Sequence No: #B" + Partno + "#C Rack No.: #B" + Object + "#C Full task id: #B"
												+ racksTaskId + "#C Full Rack Created #BLater#C successfully",
										"Pass", false);
								reportStep(
										"Sequence No: #B" + Partno + "#C Rack No.: #B" + Object + "#C Full task id: #B"
												+ racksTaskId + "#C Full Rack Created #BLater#C successfully",
										"Info", false);
								if(flow.equalsIgnoreCase("Trailer"))
								{
									LDJIT ld = new LDJIT();
									String fPos = driver.findElements(By.xpath(objTaskRow+"["+2+"]")).get(0).getText();
									System.out.println("fPos:" +fPos);
							String[] fPosi= fPos.split(":");
							System.out.println("fPosi:" +fPosi[1].trim());
							System.out.println("ld.place :" +ld.place);
							if(fPosi[1].trim().equalsIgnoreCase(ld.place)) {
								reportStep("Current Position of Sequence No: #B" + Partno + "#C Rack No.: #B" + Object + "#C Has been #BChanged#C successfully to" +ld.place,"Pass", false);
							}else {
								reportStep("Current Position of Sequence No: #B" + Partno + "#C Rack No.: #B" + Object + "#C Has not been #BChanged#C successfully to" +ld.place,"Pass", false);
							}
								}

							} else if (tableTaskRows.get(0).findElements(By.tagName("td")).get(0)
									.findElements(By.tagName("strong")).get(0).getText().toLowerCase()
									.contains("tomracks")) {

								tomracksTaskId = driver.findElement(By.xpath(objTaskTable)).getText();
								reportStep(
										"Sequence No: #B" + Partno + "#C Rack No.: #B" + Object + "#C Empty task id: #B"
												+ tomracksTaskId + "#C Empty Rack Created #BFirst#C successfully",
										"Pass", false);
								reportStep(
										"Sequence No: #B" + Partno + "#C Rack No.: #B" + Object + "#C Empty task id: #B"
												+ tomracksTaskId + "#C Empty Rack Created #BFirst#C successfully",
										"Info", false);

							}
							// New block for trailer Id fetch
							else if (tableTaskRows.get(0).findElements(By.tagName("td")).get(0)
									.findElements(By.tagName("strong")).get(0).getText().toLowerCase()
									.contains("trailer full task")) {

								racksTaskId = driver.findElement(By.xpath(objTaskTable)).getText();
								reportStep(
										"Sequence No: #B" + Partno + "#C Rack No.: #B" + Object + "#C Empty task id: #B"
												+ tomracksTaskId + "#C Empty Rack Created #BFirst#C successfully",
										"Pass", false);
								reportStep(
										"Sequence No: #B" + Partno + "#C Rack No.: #B" + Object + "#C Empty task id: #B"
												+ tomracksTaskId + "#C Empty Rack Created #BFirst#C successfully",
										"Info", false);

							} else if (tableTaskRows.get(0).findElements(By.tagName("td")).get(0)
									.findElements(By.tagName("strong")).get(0).getText().toLowerCase()
									.contains("Trailer Empty task")) {

								tomracksTaskId = driver.findElement(By.xpath(objTaskTable)).getText();
								reportStep(
										"Sequence No: #B" + Partno + "#C Rack No.: #B" + Object + "#C Empty task id: #B"
												+ tomracksTaskId + "#C Empty Rack Created #BFirst#C successfully",
										"Pass", false);
								reportStep(
										"Sequence No: #B" + Partno + "#C Rack No.: #B" + Object + "#C Empty task id: #B"
												+ tomracksTaskId + "#C Empty Rack Created #BFirst#C successfully",
										"Info", false);

							}
							System.out.println(racksTaskId);
							System.out.println(tomracksTaskId);
						}
						


						if ((!racksTaskId.equals("")) && (!tomracksTaskId.equals(""))) {

							reportStep("Sequence No: #B" + Partno + "#C Rack No.: #B" + Object + " #C Empty task id: #B"
									+ tomracksTaskId + "#C Full task id:  #B" + racksTaskId
									+ "#C tasks genereated successfully", "Pass", false);

						/*	excelUtils.excelUpdateValue("Vclas_Assignments", "Full_Task_" + RacksNo, refTestDataName,
									racksTaskId);
							excelUtils.excelUpdateValue("Vclas_Assignments", "Empty_Task_" + RacksNo, refTestDataName,
									tomracksTaskId);*/
							
							//new
						
						al.add(racksTaskId);
					add1=al.get(j).toString();
				 
						System.out.println("add1: "+add1);
						
						al1.add(tomracksTaskId);
						String add2=al1.get(j).toString();
						System.out.println("add2: "+add2);
						j++;
						System.out.println("j:" +j);
						break;
						}
					}
					if (tomracksTaskId.equals("") || racksTaskId.equals("")) {
						reportStep("Empty task id: " + tomracksTaskId + " Full task id: " + racksTaskId
								+ " tasks not genereated successfully", "Fail", true);
						break;
					} else {

						break;
					}
				} else {
					anyClick("Loop to Search Vclas task Submitted Machine Id- Display Button",
							By.xpath(prop.getProperty("Vclas_tasks.Display.Button")));
					Thread.sleep(2000);
					iTemp = iTemp + 1;
				}

			} while (!(iTemp == 30));

			if (iTemp == 30) {
				reportStep("Object " + tdrow.get("Object") + " not reflected in the Vclas tasks", "Fail", true);
			}
			if ((!racksTaskId.equals("")) && (!tomracksTaskId.equals(""))) {
			if (Vclas_VerifyTaskEvents_Ack(tomracksTaskId, prop.getProperty("Vclas_tasks.RCPAS.Table"),
					colTaskEvents) == true) {
				reportStep("Sequence No: #B" + Partno + "#C Rack No.: #B" + Object + "#C Empty task id: #B"
						+ tomracksTaskId + "#C tasks #BACKNOWLEDGED#C successfully", "Pass", false);
				reportStep("Sequence No: #B" + Partno + "#C Rack No.: #B" + Object + "#C Empty task id: #B"
						+ tomracksTaskId + "#C tasks #BACKNOWLEDGED#C successfully", "Info", false);
			} else {
				reportStep("Sequence No: " + Partno + " Rack No.:" + Object + " Empty task id: " + tomracksTaskId
						+ " tasks ACKNOWLEDGED successfully", "Fail", true);
			}

			if (Vclas_VerifyTaskEvents_Ack(racksTaskId, prop.getProperty("Vclas_tasks.RCPAS.Table"),
					colTaskEvents) == true) {
				reportStep("Sequence No: #B" + Partno + "#C Rack No.: #B" + Object + "#C Full task id: #B" + racksTaskId
						+ "#C tasks #BACKNOWLEDGED#C successfully", "PASS", false);
				reportStep("Sequence No: #B" + Partno + "#C Rack No.: #B" + Object + "#C Full task id: #B" + racksTaskId
						+ "#C tasks #BACKNOWLEDGED#C successfully", "Info", false);
			} else {
				reportStep("Sequence No: " + Partno + " Rack No.:" + Object + " Full task id: " + racksTaskId
						+ " tasks ACKNOWLEDGED successfully", "Fail", false);
			}
			}
			reportStep("----VCLAS Task creation Verify - Sequence No: #B" + Partno + "#C Rack No.: #B" + Object
					+ "#C Completed----", "Info", false);

			// Clear the excel object
			tdrow.clear();
		} catch (Exception e) {
			System.out.println("Exception thrown in Vclas_getTuggerTask method"+ e);
		}
	}
	
	 public static List<String> getList() {
	       return al;
	   }
	 public static List<String> getList1() {
	       return al1;
	   }
	 
	public boolean Vclas_VerifyTaskEvents_Ack(String taskId, String tableObj, int colPosition)
			throws InterruptedException {
		boolean bReturn = false;
		int iTemp = 0;

		anyClick("Clear button", By.xpath(prop.getProperty("Vclas_tasks.Clear.Button")));

		Thread.sleep(2000);

		sendKeys("Task Id", By.xpath(prop.getProperty("Vclas_tasks.TaskId.Text")), taskId);

		do {

			anyClick("Display button", By.xpath(prop.getProperty("Vclas_tasks.Display.Button")));

			Thread.sleep(2000);

			String objectTableTaskEvents = tableObj + "/tbody/tr[2]/td[" + (colPosition + 1) + "]/table/tbody/tr";
			List<WebElement> tableTaskEvents = driver.findElements(By.xpath(objectTableTaskEvents));
			int taskEventCount = tableTaskEvents.size();
			System.out.println(taskEventCount);

			String lastRow = tableTaskEvents.get(taskEventCount - 1).findElements(By.tagName("td")).get(1).getText();

			if (lastRow.equals("ACK")) {
				reportStep("Ack is reflected in the Vclas tasks", "Pass", true);
				bReturn = true;
				break;
			}
			iTemp = iTemp + 1;
			anyClick("clear button", By.xpath(prop.getProperty("Vclas_tasks.Clear.Button")));

		} while (!(iTemp == 30));

		if (iTemp == 30) {
			reportStep("Acknowledge is not reflected in the Vclas tasks", "Fail", true);
		}

		Vclas_TasksEvents_Timeverify(taskId, tableObj, colPosition);

		//anyClick("Clear button", By.xpath(prop.getProperty("Vclas_tasks.Clear.Button")));

		return bReturn;
	}

	public boolean Vclas_VerifyTaskEvents_Inactive(String Status) throws InterruptedException {

		driver.switchTo().window(browser[1]);

		// Load Test Data
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		boolean bReturn = false;
		//int tableIndex=1;
int k=0;
		String tableHeaderColumn = prop.getProperty("Vclas_tasks.RCPAS.Table") + "[" + tableIndex + "]/tbody/tr[1]";
		int colTaskStatusInfo = htmlTableColumnNamePosition("Task status information", tableHeaderColumn);
		int colTask = htmlTableColumnNamePosition("Taskid", tableHeaderColumn);
		int rackCount = Integer.parseInt(tdrow.get("No_of_Racks"));

		// Select Radio button
		if (!tdrow.get("Flow_Type").equals("")) {
			selectRadioButtonByValue("Flow_Type", By.xpath(prop.getProperty("Vclas_tasks.Flow.Type.Radio")),
					tdrow.get("Flow_Type"));
		}

		// Select Radio button
		selectRadioButtonByValue("Task_Type", By.xpath(prop.getProperty("Vclas_tasks.Task.Type.Radio")), "inaktiva");

		tdrow.clear();

		//tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_Assignments");
		//List<String> listr = vt.getList();--new
       System.out.println("inactiv1: "+al.get(k).toString());
		System.out.println("al size:" +al.size());
		for (int i = 1; i <= al.size(); i++) {

			if(Vclas_VerifyTaskEvents_Status(al.get(k).toString(), colTask)==false) {
				break;
			}

			if (!Status.equalsIgnoreCase("")) {
				Vclas_VerifyTaskInformation_Completed(al.get(k).toString(),
						prop.getProperty("Vclas_tasks.RCPAS.Table"), colTaskStatusInfo, Status);
			}
			k++;
		}
		k=0;
		for (int i = 1; i <= al1.size(); i++) {
			if(Vclas_VerifyTaskEvents_Status(al1.get(k).toString(), colTask)==false) {
				break;
			}

			if (!Status.equalsIgnoreCase("")) {
				Vclas_VerifyTaskInformation_Completed(al1.get(k).toString(),
						prop.getProperty("Vclas_tasks.RCPAS.Table"), colTaskStatusInfo, Status);
			}
k++;
		}

		return bReturn;
	}


	public boolean Agv_Vclas_VerifyTaskEvents_Inactive(String Status) throws InterruptedException {

        // driver.manage().timeouts().implicitlyWait(Default_Wait_4_Page,
        // TimeUnit.SECONDS);

        // Load Test Data

        tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
        boolean bReturn = false;
        tableIndex="1";
        String tableHeaderColumn = prop.getProperty("Vclas_tasks.RCPAS.Table") + "[" + tableIndex + "]/tbody/tr[1]";  
        int colTaskStatusInfo = htmlTableColumnNamePosition("Task status information", tableHeaderColumn);
        int colTask = htmlTableColumnNamePosition("Taskid", tableHeaderColumn);
       

        // Select Radio button
        if (!tdrow.get("Flow_Type").equals("")) {
               selectRadioButtonByValue("Flow_Type", By.xpath(prop.getProperty("Vclas_tasks.Flow.Type.Radio")),
                            tdrow.get("Flow_Type"));
        }

        // Select Radio button
        selectRadioButtonByValue("Task_Type", By.xpath(prop.getProperty("Vclas_tasks.Task.Type.Radio")), "inaktiva");

        tdrow.clear();

        tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_Assignments");

      int i=1;

               Vclas_VerifyTaskEvents_Status(tdrow.get("Full_Task_" + i), colTask);

               if (!Status.equalsIgnoreCase("")) {
                     Vclas_VerifyTaskInformation_Completed(tdrow.get("Full_Task_" + i),
                                   prop.getProperty("Vclas_tasks.RCPAS.Table"), colTaskStatusInfo, Status);
               }

               Vclas_VerifyTaskEvents_Status(tdrow.get("Empty_Task_" + i), colTask);

               if (!Status.equalsIgnoreCase("")) {
                     Vclas_VerifyTaskInformation_Completed(tdrow.get("Empty_Task_" + i),
                                   prop.getProperty("Vclas_tasks.RCPAS.Table"), colTaskStatusInfo, Status);
               }
       

        return bReturn;
 }

	
	 public boolean Vclas_VerifyTaskEvents_Status(String taskId, int colPosition) throws InterruptedException {
	        boolean bReturn = false;
	        int tableIndex = 1;
	        reportStep("----VCLAS Task Inactive status verify - TaskID: " + taskId + "----", "Info", false);

	        anyClick("Clear button", By.xpath(prop.getProperty("Vclas_tasks.Clear.Button")));

	        Thread.sleep(2000);

	        sendKeys("Task Id", By.xpath(prop.getProperty("Vclas_tasks.TaskId.Text")), taskId);

	        anyClick("Display button", By.xpath(prop.getProperty("Vclas_tasks.Display.Button")));

	        // Verify the table is displayed
	        if(verifyElementExistReturn(By.xpath(prop.getProperty("Vclas_tasks.RCPAS.Table") + "[" + tableIndex + "]/tbody/tr[2]"))==true) {
	        	bReturn=true;
	        String fetchTaskId = driver.findElement(By.xpath(prop.getProperty("Vclas_tasks.RCPAS.Table") + "[" + tableIndex
	                     + "]/tbody/tr[2]/td[1]/table/tbody/tr[1]/td[1]")).getText();
	        System.out.println(fetchTaskId);

	        if (fetchTaskId.equals(taskId)) {
	               reportStep("Task Id: " + fetchTaskId + " is verified as Inactive successfully", "Pass", true);

	               String objectTableData = prop.getProperty("Vclas_tasks.RCPAS.Table") + "[" + tableIndex
	                            + "]/tbody/tr[2]/td[" + (colPosition + 1) + "]";
	               String objTaskRow = objectTableData + "/table/tbody/tr[1]/td[2]/table/tbody/tr";
	               List<WebElement> tableTaskRows = driver.findElements(By.xpath(objTaskRow));

	               String fullRack = tableTaskRows.get(0).findElements(By.tagName("td")).get(0)
	                            .findElements(By.tagName("strong")).get(0).getText().substring(0, 5);
	               if (fullRack.toLowerCase().contains("racks")) {
	                     reportStep("Task ID " + fetchTaskId + " is Full Rack" + fullRack + " verified successfully", "Pass",
	                                   false);
	               } else if (tableTaskRows.get(0).findElements(By.tagName("td")).get(0).findElements(By.tagName("strong"))
	                            .get(0).getText().toLowerCase().contains("tomracks")) {
	                     reportStep("Task ID " + fetchTaskId + " is empty Rack verified successfully", "Pass", true);
	               }
	               //bReturn=true;
	        } else {
	               reportStep("Task Id: " + fetchTaskId + " is not present in the table as inactive", "fail", false);
	               }
	        }
	        return bReturn;
	 }

	 public boolean Vclas_VerifyTaskInformation_Completed(String taskId, String tableObj, int colPosition, String Status)
	               throws InterruptedException {
	        boolean bReturn = false;
	        int iTemp = 0;
	        String strongValue = "";
	        tableIndex="1";

	        String objectTableTaskInformation = tableObj + "[" + tableIndex + "]/tbody/tr[2]/td[" + (colPosition + 1)
	                     + "]/table/tbody/tr";

	        List<WebElement> tableTaskInformation = driver.findElements(By.xpath(objectTableTaskInformation));

	        int taskInfoCount = tableTaskInformation.size();
	        System.out.println(taskInfoCount);

	        for (WebElement taskCount : tableTaskInformation) {

	               List<WebElement> strong = taskCount.findElements(By.tagName("strong"));

	               int taskStrongCount = strong.size();

	               for (WebElement strongCount : strong) {

	                     strongValue = strongCount.getText();

	                     if (strongValue.equalsIgnoreCase(Status)) {
	                            iTemp = 1;
	                            System.out.println("Value Present " + strongValue);
	                            reportStep("Status:" + strongValue + "is verified successfully in vclas tasks", "pass", true);

	                            break;

	                     }

	               }
	               if (iTemp == 1) {
	                     break;
	               }
	        }
	        if (iTemp == 0) {
	               reportStep("Status:" + strongValue + "is not present in vclas tasks", "fail", true);
	        }
	        return bReturn;
	 }



		public boolean Vclas_TasksEvents_Timeverify(String taskId, String tableObj, int colPosition) {
			boolean bReturn = false;

			String NewTime = "", SentTime = "", AckTime = "", tasksEvents;
			int NewSentDiff, SentAckDiff;
			tableIndex="1";

			String objectTableTaskEvents = tableObj + "[" + tableIndex + "]/tbody/tr[2]/td[" + (colPosition + 1)
					+ "]/table/tbody/tr";
			List<WebElement> tableTaskEvents = driver.findElements(By.xpath(objectTableTaskEvents));
			int taskEventCount = tableTaskEvents.size();
			System.out.println(taskEventCount);

			for (int i = 0; i <= taskEventCount - 1; i++) {
				tasksEvents = tableTaskEvents.get(i).findElements(By.tagName("td")).get(1).getText();
				System.out.println(tasksEvents);
				if (tasksEvents.equalsIgnoreCase("New")) {
					NewTime = tableTaskEvents.get(i).findElements(By.tagName("td")).get(0).getText().substring(10, 14);
					System.out.println(NewTime);
				} else if (tasksEvents.equalsIgnoreCase("Sent")) {
					SentTime = tableTaskEvents.get(i).findElements(By.tagName("td")).get(0).getText().substring(10, 14);
					System.out.println(SentTime);
				} else if (tasksEvents.equalsIgnoreCase("Ack")) {
					AckTime = tableTaskEvents.get(i).findElements(By.tagName("td")).get(0).getText().substring(10, 14);
					System.out.println(AckTime);
				}
			}

			NewSentDiff = Integer.parseInt(SentTime) - Integer.parseInt(NewTime);
			SentAckDiff = Integer.parseInt(AckTime) - Integer.parseInt(SentTime);

			System.out.println("newsent diff:" + NewSentDiff + " sent ack diff" + SentAckDiff);

			if (SentAckDiff < 60) {
				reportStep("Time difference between Sent and Acknowledgement: #B " + SentAckDiff + "#C Seconds", "pass",
						true);
			} else {
				reportStep("Time difference between Sent and Acknowledgement is more than 60 seconds: #B " + SentAckDiff
						+ "#C Seconds", "fail", true);
			}

			return bReturn;
		}

	       public void Vclas_getAGVTask_Inactive_MID() throws InterruptedException {

               // Load Test Data Filex
               tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);

               String tableHeaderColumn = prop.getProperty("Vclas_tasks.RCPAS.Table") + "/tbody/tr[1]";
               int colTask = htmlTableColumnNamePosition("Taskid", tableHeaderColumn);
               int colTaskInformation = htmlTableColumnNamePosition("Task information", tableHeaderColumn);
               int colTaskEvents = htmlTableColumnNamePosition("Task events", tableHeaderColumn);
               String fullTaskId = "", emptyTaskId = "", strObjectValue = "";
               
               System.out.println("");

               reportStep("-------- VCLAS Task creation Verify Initiated --------", "Info", false);
        
                     // nullify the Default wait time.
                     driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
                     if (driver
                                   .findElements(By
                                                 .xpath(prop.getProperty("Vclas_tasks.AGV.Data.Table").replace("&Value", tdrow.get("FID"))))
                                   .size() <= 0) {
                            reportStep("AGV orders are  not created for Inactive MID -- verified TestCase ID:2882", "pass", true);
                            reportStep("Verification of possibility of creating  AGV orders for Inactive Machine ID #B TestCase ID:2882 #C","info", false);
                            
                            
                     } else {
                            reportStep("AGV order creation  for Inactive MID verification failed--TestCase ID:2882 ", "fail", true);
                     }
               

               // Clear the excel object
               tdrow.clear();

        }

           public boolean Agv_Vclas_VerifyTaskEvents_Active(String Status,String TaskId,String Type) throws InterruptedException {

               // driver.manage().timeouts().implicitlyWait(Default_Wait_4_Page,
               // TimeUnit.SECONDS);

               // Load Test Data

               tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
               boolean bReturn = false;
                tableIndex="1";
               String tableHeaderColumn = prop.getProperty("Vclas_tasks.RCPAS.Table") + "[" + tableIndex + "]/tbody/tr[1]";
               int colTaskStatusInfo = htmlTableColumnNamePosition("Task status information", tableHeaderColumn);
               int colTask = htmlTableColumnNamePosition("Taskid", tableHeaderColumn);
               int rackCount = Integer.parseInt(tdrow.get("No_of_Racks"));

               // Select Radio button
               if (!tdrow.get("Flow_Type").equals("")) {
                      selectRadioButtonByValue("Flow_Type", By.xpath(prop.getProperty("Vclas_tasks.Flow.Type.Radio")),
                                   tdrow.get("Flow_Type"));
               }

               // Select Radio button
               selectRadioButtonByValue("Task_Type", By.xpath(prop.getProperty("Vclas_tasks.Task.Type.Radio")), "aktiva");

               tdrow.clear();

               tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_Assignments");

              

                     Vclas_VerifyTaskEvents_ActiveStatus(TaskId, colTask);

                      if (!Status.equalsIgnoreCase("")) {
                            Vclas_VerifyTaskInformation_Completed(TaskId,
                                          prop.getProperty("Vclas_tasks.RCPAS.Table"), colTaskStatusInfo, Status);
                            reportStep("#B Staus:begun #C is verified for "+Type , "pass", false);
                      }




                    /*  Vclas_VerifyTaskEvents_ActiveStatus(tdrow.get("Empty_Task_" + i), colTask);

                      if (!Status.equalsIgnoreCase("")) {
                            Vclas_VerifyTaskInformation_Completed(tdrow.get("Empty_Task_" + i),
                                          prop.getProperty("Vclas_tasks.RCPAS.Table"), colTaskStatusInfo, Status);
                      }*/
             

               return bReturn;
       }

           public boolean Vclas_VerifyTaskEvents_ActiveStatus(String taskId, int colPosition) throws InterruptedException {
               boolean bReturn = false;

               reportStep("----VCLAS Task Active status verify - TaskID: " + taskId + "----", "Info", false);

               anyClick("Clear button", By.xpath(prop.getProperty("Vclas_tasks.Clear.Button")));

               Thread.sleep(2000);

               sendKeys("Task Id", By.xpath(prop.getProperty("Vclas_tasks.TaskId.Text")), taskId);

               anyClick("Display button", By.xpath(prop.getProperty("Vclas_tasks.Display.Button")));

               // Verify the table is displayed
               verifyElementExist("Table Search Data",
                            By.xpath(prop.getProperty("Vclas_tasks.RCPAS.Table") + "[" + tableIndex + "]/tbody/tr[2]"));

               String fetchTaskId = driver.findElement(By.xpath(prop.getProperty("Vclas_tasks.RCPAS.Table") + "[" + tableIndex
                            + "]/tbody/tr[2]/td[1]/table/tbody/tr[1]/td[1]")).getText();
               System.out.println(fetchTaskId);

               if (fetchTaskId.equals(taskId)) {
                      reportStep("Task Id: " + fetchTaskId + " is verified as Active successfully", "Pass", true);

                      String objectTableData = prop.getProperty("Vclas_tasks.RCPAS.Table") + "[" + tableIndex
                                   + "]/tbody/tr[2]/td[" + (colPosition + 1) + "]";
                      String objTaskRow = objectTableData + "/table/tbody/tr[1]/td[2]/table/tbody/tr";
                      List<WebElement> tableTaskRows = driver.findElements(By.xpath(objTaskRow));

                      String fullRack = tableTaskRows.get(0).findElements(By.tagName("td")).get(0)
                                   .findElements(By.tagName("strong")).get(0).getText().substring(0, 5);
                      if (fullRack.toLowerCase().contains("racks")) {
                            reportStep("Task ID " + fetchTaskId + " is Full Rack" + fullRack + " verified successfully", "Pass",
                                          false);
                      } else if (tableTaskRows.get(0).findElements(By.tagName("td")).get(0).findElements(By.tagName("strong"))
                                   .get(0).getText().toLowerCase().contains("tomracks")) {
                            reportStep("Task ID " + fetchTaskId + " is empty Rack verified successfully", "Pass", true);
                      }
               } else {
                      reportStep("Task Id: " + fetchTaskId + " is not present in the table as Active", "fail", false);
               }

               return bReturn;
       }

           public void Vclas_getTrailerTask(String Partno, String Object, int RacksNo) throws InterruptedException {
       		try {

       			// Load Test Data
       			tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);

       			String tableHeaderColumn = prop.getProperty("Vclas_tasks.RCPAS.Table") + "/tbody/tr[1]";

       			int colTask = htmlTableColumnNamePosition("Taskid", tableHeaderColumn);
       			int colTaskInformation = htmlTableColumnNamePosition("Task information", tableHeaderColumn);
       			int colTaskEvents = htmlTableColumnNamePosition("Task events", tableHeaderColumn);
       			String racksTaskId = "", tomracksTaskId = "";
       			int iTemp = 0;

       			do {
       				// nullify the Default wait time.
       				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

       				if (driver.findElements(By.xpath(prop.getProperty("Vclas_tasks.Tugger.Data.Table")
       						.replace("&Value", Partno).replace("&1Value", Object))).size() >= 1) {
       					// Reset to Default timeout
       					driver.manage().timeouts().implicitlyWait(Default_Wait_4_Page, TimeUnit.SECONDS);
       					List<WebElement> tableRows = driver
       							.findElements(By.xpath(prop.getProperty("Vclas_tasks.RCPAS.Table") + "/tbody/tr"));
       					int tableSize = driver.findElements(By.xpath(prop.getProperty("Vclas_tasks.RCPAS.Table"))).size();
       					System.out.println(tableSize);
       					if (tableSize == 2) {
       						tableIndex = "2";
       					} else {
       						tableIndex = "1";
       					}

       					//for (int i = 2; i <= tableRows.size(); i++) {

       						String objectTableData = prop.getProperty("Vclas_tasks.RCPAS.Table") + "[" + tableIndex
       								+ "]/tbody/tr[2]/td[" + (colTask + 1) + "]";
       						String objectTableTaskInfo = prop.getProperty("Vclas_tasks.RCPAS.Table") + "[" + tableIndex
       								+ "]/tbody/tr[2]/td[" + (colTaskInformation + 1) + "]/table/tbody/tr";
       						String objectTableTaskEvents = prop.getProperty("Vclas_tasks.RCPAS.Table") + "[" + tableIndex
       								+ "]/tbody/tr[2]/td[" + (colTaskEvents + 1) + "]/table/tbody/tr";

       						// To get the task Id from Vclas_task
       						String objTaskTable = objectTableData + "/table/tbody/tr[1]/td[1]";

       						String objTaskRow = objectTableData + "/table/tbody/tr[1]/td[2]/table/tbody/tr";

       						System.out.println(" " + objTaskTable);
       						System.out.println(" " + objTaskRow);
       						// Row taskInfo

       						List<WebElement> tableTaskRows = driver.findElements(By.xpath(objTaskRow));

       						if (getObjectId(objectTableTaskInfo, "Partno.").equalsIgnoreCase(Partno)
       								&& getObjectId(objectTableTaskInfo, "Object.").equalsIgnoreCase(Object)) {

       							reportStep("----VCLAS Task creation Verify - Sequence No: #B" + Partno + "#C Rack No.: #B"
       									+ Object + "#C Initiated----", "Info", false);

       						
       							// New block for trailer Id fetch
       							if (tableTaskRows.get(0).findElements(By.tagName("td")).get(0)
       									.findElements(By.tagName("strong")).get(0).getText().toLowerCase()
       									.contains("trailer full task")) {

       								racksTaskId = driver.findElement(By.xpath(objTaskTable)).getText();
       								reportStep(
       										"Sequence No: #B" + Partno + "#C Rack No.: #B" + Object + "#C Full task id: #B"
       												+ racksTaskId + "#C Full Trailer Task Created #BFirst#C successfully",
       										"Pass", false);
       								reportStep(
       										"Sequence No: #B" + Partno + "#C Rack No.: #B" + Object + "#C Full task id: #B"
       												+ racksTaskId + "#C Full Trailer Task Created #BFirst#C successfully",
       										"Info", false);
       								excelUtils.excelUpdateValue("Vclas_Assignments", "Full_Task_1", refTestDataName,
       										racksTaskId);
       								if (Vclas_VerifyTaskEvents_Ack(racksTaskId, prop.getProperty("Vclas_tasks.RCPAS.Table"),
       										colTaskEvents) == true) {
       									reportStep("Sequence No: #B" + Partno + "#C Rack No.: #B" + Object + "#C Full task id: #B" + racksTaskId
       											+ "#C tasks #BACKNOWLEDGED#C successfully", "PASS", false);
       									reportStep("Sequence No: #B" + Partno + "#C Rack No.: #B" + Object + "#C Full task id: #B" + racksTaskId
       											+ "#C tasks #BACKNOWLEDGED#C successfully", "Info", false);
       									break;
       								} else {
       									reportStep("Sequence No: " + Partno + " Rack No.:" + Object + " Full task id: " + racksTaskId
       											+ " tasks ACKNOWLEDGED successfully", "Fail", false);
       									break;
       								}

       							} else if (tableTaskRows.get(0).findElements(By.tagName("td")).get(0)
       									.findElements(By.tagName("strong")).get(0).getText().toLowerCase()
       									.contains("trailer empty task")) {

       								tomracksTaskId = driver.findElement(By.xpath(objTaskTable)).getText();
       								reportStep(
       										"Sequence No: #B" + Partno + "#C Rack No.: #B" + Object + "#C Empty task id: #B"
       												+ tomracksTaskId + "#C Empty Rack Created #BFirst#C successfully",
       										"Pass", false);
       								reportStep(
       										"Sequence No: #B" + Partno + "#C Rack No.: #B" + Object + "#C Empty task id: #B"
       												+ tomracksTaskId + "#C Empty Rack Created #BFirst#C successfully",
       										"Info", false);
       								excelUtils.excelUpdateValue("Vclas_Assignments", "Empty_Task_1", refTestDataName,
       										tomracksTaskId);
       								if (Vclas_VerifyTaskEvents_Ack(tomracksTaskId, prop.getProperty("Vclas_tasks.RCPAS.Table"),
       										colTaskEvents) == true) {
       									reportStep("Sequence No: #B" + Partno + "#C Rack No.: #B" + Object + "#C Empty task id: #B"
       											+ tomracksTaskId + "#C tasks #BACKNOWLEDGED#C successfully", "Pass", false);
       									reportStep("Sequence No: #B" + Partno + "#C Rack No.: #B" + Object + "#C Empty task id: #B"
       											+ tomracksTaskId + "#C tasks #BACKNOWLEDGED#C successfully", "Info", false);
       									break;
       								} else {
       									reportStep("Sequence No: " + Partno + " Rack No.:" + Object + " Empty task id: " + tomracksTaskId
       											+ " tasks ACKNOWLEDGED successfully", "Fail", true);
       									break;
       								}

       							
       							}
       							System.out.println(racksTaskId);
       							System.out.println(tomracksTaskId);
       							
       						}
       						if (tomracksTaskId.equals("") || racksTaskId.equals("")) {
       							reportStep("Empty task id: " + tomracksTaskId + " Full task id: " + racksTaskId
       									+ " tasks not genereated successfully", "Fail", true);
       							break;
       						} else {

       							break;
       						}
       							
       							
       							
       						
       			
       					} else {
					anyClick("Loop to Search Vclas task Submitted Machine Id- Display Button",
							By.xpath(prop.getProperty("Vclas_tasks.Display.Button")));
					Thread.sleep(2000);
					iTemp = iTemp + 1;
				}

			} while (!(iTemp == 30));

			if (iTemp == 30) {
				reportStep("Object " + tdrow.get("Object") + " not reflected in the Vclas tasks", "Fail", true);
			}
			
			
			

			reportStep("----VCLAS Task creation Verify - Sequence No: #B" + Partno + "#C Rack No.: #B" + Object
					+ "#C Completed----", "Info", false);

			// Clear the excel object
			tdrow.clear();
		} catch (Exception e) {
			System.out.println("Exception thrown in Vclas_getTuggerTask method"+ e);
		}
			
       		}
           public void Vclas_getPositionVerify(String Partno, String Object, int RacksNo) throws InterruptedException {
       		try {
       			String tableHeaderColumn = prop.getProperty("Vclas_tasks.RCPAS.Table") + "/tbody/tr[1]";
       			int colTask = htmlTableColumnNamePosition("Taskid", tableHeaderColumn);
       			int colTaskInformation = htmlTableColumnNamePosition("Task information", tableHeaderColumn);
       			
       			List<WebElement> tableRows = driver
						.findElements(By.xpath(prop.getProperty("Vclas_tasks.RCPAS.Table") + "/tbody/tr"));
       			int tableSize = driver.findElements(By.xpath(prop.getProperty("Vclas_tasks.RCPAS.Table"))).size();
				System.out.println(tableSize);
				if (tableSize == 2) {
					tableIndex = "2";
				} else {
					tableIndex = "1";
				}
       			for (int i = 2; i <= tableRows.size(); i++) {
           String objectTableData = prop.getProperty("Vclas_tasks.RCPAS.Table") + "[" + tableIndex
					+ "]/tbody/tr[" + i + "]/td[" + (colTask + 1) + "]";
       	String objectTableTaskInfo = prop.getProperty("Vclas_tasks.RCPAS.Table") + "[" + tableIndex
				+ "]/tbody/tr[" + i + "]/td[" + (colTaskInformation + 1) + "]/table/tbody/tr";
        // To get the task Id from Vclas_task
			String objTaskTable = objectTableData + "/table/tbody/tr[1]/td[1]";
			String objTaskRow = objectTableData + "/table/tbody/tr[1]/td[2]/table/tbody/tr";

			System.out.println(" " + objTaskTable);
			System.out.println(" " + objTaskRow);
			// Row taskInfo

			List<WebElement> tableTaskRows = driver.findElements(By.xpath(objTaskRow));
			

			if (getObjectId(objectTableTaskInfo, "Partno.").equalsIgnoreCase(Partno)
					&& getObjectId(objectTableTaskInfo, "Object.").equalsIgnoreCase(Object)) {

				reportStep("----VCLAS Task creation Verify - Sequence No: #B" + Partno + "#C Rack No.: #B"
						+ Object + "#C Initiated----", "Info", false);

				String fullRack = tableTaskRows.get(0).findElements(By.tagName("td")).get(0)
						.findElements(By.tagName("strong")).get(0).getText().substring(0, 5);
				if (fullRack.toLowerCase().contains("racks")) {
				
					String fPos = driver.findElements(By.xpath(objTaskRow+"["+2+"]")).get(0).getText();
					System.out.println("fPos:" +fPos);
			String[] fPosi= fPos.split(":");
			System.out.println("fPosi:" +fPosi[1]);
					//racksTaskId = driver.findElement(By.xpath(objTaskTable)).getText();
//tableTaskRows.get(0).findElements(By.tagName("td")).get(0)
       			}
			}
       			}
       		} catch (Exception e) {
    			System.out.println("Exception thrown in Vclas_getTuggerTask method"+ e);
    		}
           }
           }
