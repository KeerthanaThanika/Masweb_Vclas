package com.volvo.mfg.agv.pages;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.util.SystemOutLogger;
import org.apache.xmlbeans.impl.tool.InstanceValidator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.volvo.automation.commonutilities.DB_Connectivity;
import com.volvo.automation.commonutilities.ExcelUtils;
import com.volvo.automation.commonutilities.QueryInput;
import com.volvo.mfg.StepDefinition.DriverSuite;


public class V432 extends DriverSuite implements QueryInput{

	String sheetName="V432";
	public HashMap<String,String> tdrow;
	
	//Excel class object to access the function
	ExcelUtils excelUtils = new ExcelUtils();
	public String tableIndex="";
	
	public void Scan_RFID(int ScanNos) throws InterruptedException{
		//Load Test Data File
		tdrow= excelUtils.testCaseRetrieve(refTestDataName,sheetName);
		String RFID= tdrow.get("RFID");
		
		// verifying the V60 scan RFID page is loaded 
		
		if(verifyElementExist("V60 Scan RFID page", By.xpath(prop.getProperty("V60_ScanRFID_RepairStation.Text")))==true) {
			reportStep("#B Verification of newly added tab V432-V60 Test Case Id:3929--completed#C", "pass", true);
		}else {
			reportStep("Verification of newly added tab V432-V60 Test Case Id:3929--failed", "fail", true);
		}
				
		for(int i=1;i<=ScanNos	;i++) {
			// Entering RFID in the text field
			sendKeys("RFID Text field", By.xpath(prop.getProperty("V60_ScanRFID_RepairStation.Text")),RFID.split(",")[i-1] );
			//clicking on Request body Id to scan RFID
			anyClick("Request Body Id button", By.xpath(prop.getProperty("V60_ScanRFID_RepairStation.RequestBodyInformation.Button")));
			//verifying the message
			String ScanningMessage=driver.findElement(By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.ScanRFID.Message"))).getText();
			
			if(verifyElementExist("RFID Scanning message", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.ScanRFID.Message")))==true) {
			reportStep("RFID scanned: "+RFID.split(",")[i-1]+" and verified message: "+ScanningMessage, "pass", true);	
			} else {
				reportStep("RFID Scan failed", "fail", true);
			}
			//clearing the field
			anyClick("Clear Button", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.Clear")));
			
			
		}
		
	}
	
	
	// Vclas task to retrieve the assigned task
		public void Vclas_getV432Task(String Partno, String Object, int RacksNo, String flow) throws InterruptedException {
			//try {

				// Load Test Data
				tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
				Vclas_tasks vclastsk= new Vclas_tasks();

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

						for (int i = 2; i <= tableRows.size(); i++) {

							String objectTableData = prop.getProperty("Vclas_tasks.RCPAS.Table") + "[" + tableIndex+ "]/tbody/tr[" + i + "]/td[" + (colTask + 1) + "]";
							String objectTableTaskInfo = prop.getProperty("Vclas_tasks.RCPAS.Table") + "[" + tableIndex+ "]/tbody/tr[" + i + "]/td[" + (colTaskInformation + 1) + "]/table/tbody/tr";
							String objectTableTaskEvents = prop.getProperty("Vclas_tasks.RCPAS.Table") + "[" + tableIndex+ "]/tbody/tr[" + i + "]/td[" + (colTaskEvents + 1) + "]/table/tbody/tr";

							// To get the task Id from Vclas_task
							String objTaskTable = objectTableData + "/table/tbody/tr[1]/td[1]";

							String objTaskRow = objectTableData + "/table/tbody/tr[1]/td[2]/table/tbody/tr";

							System.out.println(" " + objTaskTable);
							System.out.println(" " + objTaskRow);
							// Row taskInfo

							List<WebElement> tableTaskRows = driver.findElements(By.xpath(objTaskRow));

							if (vclastsk.getObjectId(objectTableTaskInfo, "Partno.").equalsIgnoreCase(Partno)
									&& vclastsk.getObjectId(objectTableTaskInfo, "Object.").equalsIgnoreCase(Object)) {

								reportStep("----VCLAS Task creation Verify - Sequence No: #B" + Partno + "#C Rack No.: #B"
										+ Object + "#C Initiated----", "Info", false);
								racksTaskId = driver.findElement(By.xpath(objTaskTable)).getText();

							System.out.println("TaskId: "+racksTaskId);
								
							}
							


							if ((!racksTaskId.equals(""))) {

						
								System.out.println("Entered into excel update");

								excelUtils.excelUpdateValue("V432", "TaskId_" + RacksNo, refTestDataName,racksTaskId);
								System.out.println("Completed excel update value: "+racksTaskId);
								if (vclastsk.Vclas_VerifyTaskEvents_Ack(racksTaskId, prop.getProperty("Vclas_tasks.RCPAS.Table"),
										colTaskEvents) == true) {
									reportStep("Sequence No: #B" + Partno + "#C Rack No.: #B" + Object + "#C task id: #B" + racksTaskId
											+ "#C tasks #BACKNOWLEDGED#C successfully", "PASS", false);
									reportStep("Sequence No: #B" + Partno + "#C Rack No.: #B" + Object + "#C  task id: #B" + racksTaskId
											+ "#C tasks #BACKNOWLEDGED#C successfully", "Info", false);
									
								} else {
									reportStep("Sequence No: " + Partno + " Rack No.:" + Object + " Full task id: " + racksTaskId
											+ " tasks ACKNOWLEDGED successfully", "Fail", false);
								}
								
					
							break;
							}
						}
						if ( racksTaskId.equals("")) {
							reportStep( "Task id: " + racksTaskId
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

					//
					
				} while (!(iTemp == 30));

				if (iTemp == 30) {
					reportStep("Object " + tdrow.get("Object") + " not reflected in the Vclas tasks", "Fail", true);
				}


				reportStep("----VCLAS Task creation Verify - Sequence No: #B" + Partno + "#C Rack No.: #B" + Object
						+ "#C Completed----", "Info", false);

				// Clear the excel object
				//tdrow.clear();
/*			} catch (Exception e) {
				System.out.println("Exception thrown in Vclas_getV432Task method"+ e);
			}
*/		}
	public void Vclas_getV32TaskIds() throws InterruptedException {

		sheetName="Vclas_tasks";
		// Load Test Data
		tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		//int rackCount = Integer.parseInt(tdrow.get("No_of_Racks"));
 		Vclas_tasks vclastsk= new Vclas_tasks();

		//System.out.println("rack cnt in vclas:" + rackCount);

		String objValue = tdrow.get("Object");
		String tableHeaderColumn = prop.getProperty("Vclas_tasks.RCPAS.Table") + "/tbody/tr[1]";
		int colTaskEvents = htmlTableColumnNamePosition("Task events", tableHeaderColumn);

		for (int i = 0; i <=3; i++) {
			anyClick("Clear button", By.xpath(prop.getProperty("Vclas_tasks.Clear.Button")));
			vclastsk.Vclas_Search_Partno();
			String objectId= "S"+objValue.split(",")[i];
			System.out.println("objectId loop start: "+objectId);
			int rackNo=i+1;
			System.out.println("rackno: "+rackNo);
			Vclas_getV432Task(tdrow.get("PartNumber"),objectId, rackNo , " ");

		}
		reportStep("#BVerification of status for scanned or regsitered bodies from the VCLAS Tasks Screen in Masweb application Test case Id:3936--completed#C", "pass", false);

	}


	
	// V432 delivery flow in vclas application
	public void V432_Delivery_AL() throws InterruptedException{
		// Load Test Data
				tdrow = excelUtils.testCaseRetrieve(refTestDataName, "V432");
				
				 Vclas_Assignment vclasAgn = new Vclas_Assignment();
			
				String objectId = tdrow.get("BodyId");
				
			
				String strTaskType = tdrow.get("Shortage_Type_Expected");
				String strTaskStatus=tdrow.get("Shortage_Status");
			

				// Submitting the Racks
				for (int i = 0; i <= 3; i++) {
					
					// Navigation to Shortage
					anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));

					// Waiting to verify the screen is displayed
					waitForElement(driver, By.xpath(prop.getProperty("Vclas_Home.Shortage.Search.Text")),5);

					//
					Thread.sleep(6000);
					String ScanId=objectId.split(",")[i];
					String TaskId= tdrow.get("TaskId_"+(i+1));
					System.out.println("ScanId: "+ScanId);
					System.out.println("TaskId: "+TaskId);
					vclasAgn.Vclas_Shortage_Search(ScanId, "", "");

					// Verify the Task id in Shortage screen
					vclasAgn.Vclas_Shortage_TypeVerify(ScanId, TaskId, strTaskType, strTaskStatus);
 					reportStep(
							"Object Id: #B" + ScanId
									+ "#C  Rack Type: #B  V432  #C delivery  from Assignment List screen initiated",
							"Info", false);
					// Rack Pick/Drop in the Assignment List
					vclasAgn.vclas_Agv_Rackpickup(strTaskType, ScanId);
					
					if(verifyElementExist("Scanning window", By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")))==true){
						
						//entering scan id
						String ScanObjectId="S"+objectId.split(",")[i];
						SendType_Selection_Scan("No",ScanObjectId);
						// Verify the OK button exist
						if (verifyElementExistReturn(By.xpath(prop.getProperty("Vclas.AssignmentList.OkButton.Click"))) == true) {
							retryingFindClick(By.xpath(prop.getProperty("Vclas.AssignmentList.OkButton.Click")));
							Thread.sleep(3000);

						} else {
							reportStep("Ok button doesn't exists", "fail", false);
						}

						
					}

				}
				reportStep("Object Id: #B" + objectId+ "#C  Rack Type: #B V432 #C delivery  from Assignment List screen completed",
						"Info", false);

		
		
		
		
	}
	
	
	public void SendType_Selection_Scan(String sendType, String scan) throws InterruptedException {
		 Vclas_Assignment vclasAgn = new Vclas_Assignment();
		if (verifyElementExistReturn(By.xpath(prop.getProperty("Vclas.AssignmentList.ManualInput." + sendType))) == false) {
			if (sendType.equalsIgnoreCase("Yes")) {
				// driver.findElements(By.xpath(prop.getProperty("Vclas.AssignmentList.ManualInput.No"))).get(0).click();
				Thread.sleep(2000);
				vclasAgn.clickByLocator(By.xpath(prop.getProperty("Vclas.AssignmentList.ManualInput.No")));
				driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text"))).sendKeys(scan);
				driver.findElements(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text"))).get(0)
						.sendKeys(Keys.TAB);

			} else {
				driver.findElements(By.xpath(prop.getProperty("Vclas.AssignmentList.ManualInput.Yes"))).get(0).click();
				driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text"))).sendKeys(scan);

			}
		} else if (sendType.equalsIgnoreCase("No")) {

			driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text"))).sendKeys(scan);

		} else {
			Thread.sleep(2000);
			driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text"))).sendKeys(scan);
			driver.findElements(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text"))).get(0)
					.sendKeys(Keys.TAB);
		}

	}

	
	//verification of Inactive
	public boolean V432_Vclas_VerifyTaskEvents_Inactive(String Status) throws InterruptedException {

        // driver.manage().timeouts().implicitlyWait(Default_Wait_4_Page,
        // TimeUnit.SECONDS);

        // Load Test Data
		Vclas_tasks vclastsk= new Vclas_tasks();
        tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_tasks");
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

        tdrow = excelUtils.testCaseRetrieve(refTestDataName, "V432");

	      for(int i=1;i<=4;i++) {
	
	      		vclastsk.  Vclas_VerifyTaskEvents_Status(tdrow.get("TaskId_" + i), colTask);
	
	               if (!Status.equalsIgnoreCase("")) {
	            	   vclastsk. Vclas_VerifyTaskInformation_Completed(tdrow.get("TaskId_" + i),
	                                   prop.getProperty("Vclas_tasks.RCPAS.Table"), colTaskStatusInfo, Status);
	            	  
	               }
	
	        
	      }
	      return bReturn;
	}

	public void V432_Bundle_Delivery() throws InterruptedException{
		
        tdrow = excelUtils.testCaseRetrieve(refTestDataName, "V432");
        String BodyID=tdrow.get("BodyId");
        anyClick("Clear Button", By.xpath(prop.getProperty("Deliver_V60_Tasks.Clear.Button")));
        for(int i=0;i<=3;i++) {
        	String strBodyId="S"+ BodyID.split(",")[i];
        	System.out.println("strBodyId: "+strBodyId);
        	sendKeys("BodyId field", By.xpath(prop.getProperty("Deliver_V60_Tasks.BodyId.Text")),strBodyId );
        	//adding the BodyId
        	anyClick("Add Button", By.xpath(prop.getProperty("Deliver_V60_Tasks.Add.Button")));
        	//clearing the field
        	//anyClick("Clear Button", By.xpath(prop.getProperty("Deliver_V60_Tasks.Clear.Button")));
        }
        //Giving Name to the TrailerId
        sendKeys("TrailerId Name field", By.xpath(prop.getProperty("Deliver_V60_Tasks.TrailerId.Text")), tdrow.get("TrailerIdName"));
        //Giving return empty trailer as yes
        anyClick("Yes Radio Button", By.xpath(prop.getProperty("Deliver_V60_Tasks.EmptyTrailerReturn.Radio.Yes")));
        //delivering the Bundle
        anyClick("Deliver Button", By.xpath(prop.getProperty("Deliver_V60_Tasks.Deliver.Button")));
        //Handling the alert box
        isAlertPresent("OK");
        //verification of delivery message
      //verifying the message
		String ScanningMessage=driver.findElement(By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.ScanRFID.Message"))).getText();
        if(verifyElementExist("Bundle delivery message", By.xpath(prop.getProperty("V60_ScanRFID_NormalStation.ScanRFID.Message")))==true) {
			reportStep("Bundle Delivery verified message: "+ScanningMessage, "pass", true);	
			} else {
				reportStep("Bundle delivery failed", "fail", true);
			}
        
        //fetching delivery note Id
        String deliveryNoteId=driver.findElements(By.xpath(prop.getProperty("Deliver_V60_Tasks.DeliveryNoteId"))).get(0).getText();
        System.out.println("Delivery Note Id: "+deliveryNoteId);
        excelUtils.excelUpdateValue("V432", "DeliveryNoteId", refTestDataName,deliveryNoteId);
        
		
		
	}
	
	public void V60_Packaging_Note_Search() throws InterruptedException{
		 tdrow = excelUtils.testCaseRetrieve(refTestDataName, "V432");
		//entering trailer id name 
		 sendKeys("TrailerId name field", By.xpath(prop.getProperty("V60_Package_Note.TrailerId.Text")), tdrow.get("TrailerIdName").toUpperCase());
		//clicking of display
		 anyClick("Display button", By.xpath(prop.getProperty("V60_Package_Note.Display.Button")));
		 
		 //verification delivery note id
		 if(verifyElementExist("Delivery Note id", By.xpath(prop.getProperty("V60_Package_Note.DeliveryNoteId.Text").replace("&Value", tdrow.get("DeliveryNoteId"))))==true) {
			 reportStep("Trailer Id is displayed successfully", "pass", true);
		 }else {
			 reportStep("Trailer Id is not displayed successfully", "fail", true);
		 }
		
	}
	
	
	// V432 pre-requiste method
	public void V432_Pre_requiste() throws InterruptedException,SQLException{
		
		DB_Connectivity db = new DB_Connectivity();
		 tdrow = excelUtils.testCaseRetrieve(refTestDataName, "V432");
		 String RFID=tdrow.get("RFID");
		 String BodyId=tdrow.get("BodyId");
		// MASWEB connection details
			String	ClassName = prop.getProperty(Environment + ".MASWEB.MIMER.ClassName");
			String	ConnectionString = prop.getProperty(Environment + ".MASWEB.MIMER.Connection.String");
			String	UserName = prop.getProperty(Environment + ".MASWEB.MIMER.User.Name");
			String	Password = prop.getProperty(Environment + ".MASWEB.MIMER.Password");
			//pmr840
		db.Update_DB(ClassName, ConnectionString, UserName, Password, PMR840_Deletion.replace("#RFID1#", RFID.split(",")[0]).replace("#RFID2#", RFID.split(",")[1]).replace("#RFID3#", RFID.split(",")[2]).replace("#RFID4#", RFID.split(",")[3]));
		System.out.println("Deletion made in PMR840");
		reportStep("Deletion made in PMR840", "pass", false);
		
		//pmr752
		db.Update_DB(ClassName, ConnectionString, UserName, Password, PMR752_Deletion.replace("#BodyId1#", BodyId.split(",")[0]).replace("#BodyId2#", BodyId.split(",")[1]).replace("#BodyId3#", BodyId.split(",")[2]).replace("#BodyId4#", BodyId.split(",")[3]));
		System.out.println("Deletion made on PMR752");
		reportStep("Deletion made in PMR752", "pass", false);
		
		//pmr842
		db.Update_DB(ClassName, ConnectionString, UserName, Password, PMR752_Deletion.replace("#BodyId1#", BodyId.split(",")[0]).replace("#BodyId2#", BodyId.split(",")[1]).replace("#BodyId3#", BodyId.split(",")[2]).replace("#BodyId4#", BodyId.split(",")[3]));
		System.out.println("Deletion made in PMR842");
		reportStep("Deletion made in PMR842", "pass", false);
		
	}
	
	//Edit V60 delivery note
	public void Edit_V60_DeliveryNote() throws InterruptedException{
		
		 tdrow = excelUtils.testCaseRetrieve(refTestDataName, "V432");
		 String DeliveryNoteId=tdrow.get("DeliveryNoteId");
		 
		 //entering the deliery note id
		 sendKeys("Delivery note Id field", By.xpath(prop.getProperty("Edit_V60_DeliveryNote.DeliveryNoteId.Text")), DeliveryNoteId);
		 //clicking on display button
		 anyClick("Display button",By.xpath(prop.getProperty("Edit_V60_DeliveryNote.Display.Button")));
		 
		 //clicking on edit button of deliverynote id
		 driver.findElement(By.xpath(prop.getProperty("Edit_V60_DeliveryNote.Edit.Button").replace("&Value", DeliveryNoteId))).click();
		 //clicking on clear button
		 anyClick("Clear Button",By.xpath(prop.getProperty("Edit_V60_DeliveryNote.Update.Clear.Button")));
		 //clicking on save button
		 anyClick("Save button", By.xpath(prop.getProperty("Edit_V60_DeliveryNote.Update.Save.Button")));
		 //verifying the error message 
		 if(verifyElementExist("InValid Trailer ID error message", By.xpath(prop.getProperty("Edit_V60_DeliveryNote.Update.ValidTrailerId.ErrorMsg")))==true) {
			 String InvalidTrailerIdErrMsg=driver.findElement(By.xpath(prop.getProperty("Edit_V60_DeliveryNote.Update.ValidTrailerId.ErrorMsg"))).getText();
			 reportStep("Invalid Trailer ID error message: "+InvalidTrailerIdErrMsg+ " verified successfully", "pass", true);
		 }else {
			 reportStep("Inavlid Trailer ID error message is not verified", "fail", true);
		 }
		 
		 //clicking on return button
		 anyClick("Return Button", By.xpath(prop.getProperty("Edit_V60_DeliveryNote.Update.Return.Button")));
		 //clicking on display
		 anyClick("Display button",By.xpath(prop.getProperty("Edit_V60_DeliveryNote.Display.Button")));
		 //again clicking on edit button
		 driver.findElement(By.xpath(prop.getProperty("Edit_V60_DeliveryNote.Edit.Button").replace("&Value", DeliveryNoteId))).click();
		 
		 //editing the fields
		 sendKeys("New TrailerId name", By.xpath(prop.getProperty("Edit_V60_DeliveryNote.TrailerId.field")), tdrow.get("Edit_TrailerId_Name"));
		 //clicking on save button
		 anyClick("Save button", By.xpath(prop.getProperty("Edit_V60_DeliveryNote.Update.Save.Button")));
		 //clicking on Resend button
		 anyClick("Resend Button", By.xpath(prop.getProperty("Edit_V60_DeliveryNote.Resend.Button")));
		 //handling the alert
		 isAlertPresent("Cancel");
		 //again clicking resend and accepting the alert
		 anyClick("Resend Button", By.xpath(prop.getProperty("Edit_V60_DeliveryNote.Resend.Button")));
		 //handling the alert
		 isAlertPresent("Ok");
		 //verifying the messgae for sent delivery note
		 if(verifyElementExist("Resend success message", By.xpath(prop.getProperty("Edit_V60_DeliveryNote.Resend.Message")))==true) {
			 String ResendSuccessMsg=driver.findElement(By.xpath(prop.getProperty("Edit_V60_DeliveryNote.Resend.Message"))).getText();
			 reportStep("Resend success message: "+ResendSuccessMsg+" verified succesfully", "pass", true);
		 }else {
			 reportStep("Resend message is not verified successfully", "fail", true);
		 }
		 
		 //clicking on clear field
		 anyClick("Clear button",By.xpath(prop.getProperty("Edit_V60_DeliveryNote.Clear.Button")));
		 //clicking on display button
		 anyClick("Display button",By.xpath(prop.getProperty("Edit_V60_DeliveryNote.Display.Button")));
		 if(verifyElementExist("Delivery Note Id", By.xpath(prop.getProperty("Edit_V60_DeliveryNote.Table.Value").replace("&Value", DeliveryNoteId)))==true) {
			 reportStep("Delivery Note Id displayed ", "pass", true);
		 }else {
			 reportStep("Delivery note ID is not displayed", "fail", true);
		 }
		 int InvalidDeliveryNoteId=getRandomNumber(9);
		 sendKeys("Delivery Note Id field", By.xpath(prop.getProperty("Edit_V60_DeliveryNote.DeliveryNoteId.Text")), String.valueOf(InvalidDeliveryNoteId));
		 //clicking on display
		anyClick("Display button",By.xpath(prop.getProperty("Edit_V60_DeliveryNote.Display.Button")));
		if(verifyElementExist("Invalid Delivery Note ID error msg", By.xpath(prop.getProperty("Edit_V60_DeliveryNote.Invalid.DeliveryNoteId.ErrorMsg")))==true) {
			String InvalidErrorMsg=driver.findElement(By.xpath(prop.getProperty("Edit_V60_DeliveryNote.Invalid.DeliveryNoteId.ErrorMsg"))).getText();
			reportStep("Invalid Delivery Note Id error msg: "+InvalidErrorMsg+" is verified successfully", "pass", true);
			reportStep("#B Verification of Edit V60 Delivery Note screen for the above created delivery note Test case Id:3942--completed #C", "pass", false);
		}else {
			reportStep("Invalid Delivery Note Id error msg is not verified", "fail", true);
			reportStep("#B Verification of Edit V60 Delivery Note screen for the above created delivery note Test case Id:3942--failed #C", "fail", false);
		}
	}
	
	
}
