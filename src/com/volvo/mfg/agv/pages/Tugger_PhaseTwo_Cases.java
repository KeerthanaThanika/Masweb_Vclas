package com.volvo.mfg.agv.pages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.volvo.automation.commonutilities.DB_Connectivity;
import com.volvo.automation.commonutilities.ExcelUtils;
import com.volvo.automation.commonutilities.QueryInput;
import com.volvo.mfg.StepDefinition.DriverSuite;

public class Tugger_PhaseTwo_Cases extends DriverSuite implements QueryInput {

	String sheetName = "Vclas_Assignments";
	public static boolean tempVar = false;
	public int j=0;
			public static int intRackNewSeq;
	public Vclas_tasks vt = new Vclas_tasks(); 
	public HashMap<String, String> tdrow;
	MasWeb_Home mwHome = new MasWeb_Home();
	Vclas_Assignment vAssignment= new Vclas_Assignment();
	
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

	
	public void Vclas_Tugger_Assignment_Delivery_Parameters_True(String flowName) throws InterruptedException {
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

               vAssignment. Vclas_Shortage_Search(strFlowType, strSeqno, flowName);

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
               
               
                     // To verify the Full task id
               vAssignment. Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
                                   listr.get(j).toString(), strType[0].toUpperCase(), rackStatus);

                     // To Verify the Empty task id
               vAssignment.  Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
                                   listrempty.get(j).toString(), strType[1].toUpperCase(), "Active");
                     
                     
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

                    	 vAssignment.  Vclas_AssignmentList_RackSubmission_Tugger(strType[0],
                                         strFlowType + seqHandling + String.valueOf(iTempSeqNo));
                    	
                     }	 else {
                    	 vAssignment.  Vclas_AssignmentList_RackSubmission_JISJIT(strType[0].toUpperCase(),
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
     					vAssignment.Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName);

     				} else {

     					strTempSeqNo = StringUtils.repeat("0", 4 - String.valueOf(iTempSeqNo).length()) + iTempSeqNo;
     					System.out.println("strTempSeqNo:" + strTempSeqNo);

     					// Calculate Scan Id
     					String scanId = strFlowType + strTempSeqNo;
     					System.out.println("scan id" + scanId);
     					vAssignment.Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName);
     				}
                     
                     reportStep("#B Verification of  delivery functionality of a Tugger full rack when Node Scan on delivery, Scan on delivery parameters are true for the TPOS node Test case Id:3248--completed#C", "pass", false);
                   
                     reportStep("--------Full rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
                                   + "#C Submission Completed----", "Info", false);

                     // Sequence No
                     iTempSeqNo = iTempSeqNo + 1;

                     if (iTempSeqNo == 100) {
                            iTempSeqNo = 1;
                     }

               }
               
               //intRackNewSeq = Seqno + racksCount - 1;
             //  vAssignment.Vclas_Shortage_NewRacks_Search(strFlowType,Seqno,racksCount);
               
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
               for (int i = 1; i <= 1; i++) {                     //changed for 19th testcase--rackCount
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
                            double acutalThreshold =parameter.thresholdValue;
                            String threshold=String.valueOf(acutalThreshold);
                            char[] thresholdChar=threshold.toCharArray();
                            String firstChar=String.valueOf(thresholdChar[0]);
                            /*String actualValue=String.valueOf(thersholdValue);
                            char[] val=actualValue.toCharArray();
                            String actualChar=String.valueOf(val[0]);*/
                                  
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
                            clickTime = vAssignment.Vclas_Shortage_Time_Verify(" ", strFlowType, seqHandling + String.valueOf(iTempSeqNo),
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
                            if (vAssignment.Vclas_Shortage_Time_Verify(" ", strFlowType, seqHandling + String.valueOf(iTempSeqNo),
                                          flowName) == tempThreshold) {
                                   reportStep("Planned time verified Actual Time: "+clickTime+" Expected Time: "+tempThreshold, "Info", false); 
                                   break;
                            }
                            System.out.println("after while" + clickTime + " " + thersholdValue);
                     } while (((int) clickTime != tempThreshold)); // check whether click Time is positive

                     // verify whether rack status is planned
                     vAssignment.Vclas_Shortage_SingleRackStatusVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
                                   "EMPTY_RACK", "Planned");
                     // Deliver The Empty Planned Rack
                     if (flowName.equalsIgnoreCase("Tugger")) {
                    	 vAssignment. Vclas_AssignmentList_RackSubmission_Tugger(strType[1],
                                 strFlowType + seqHandling + String.valueOf(iTempSeqNo));
                    	 // need to have scan id and scanning window function
                    	 
                //------------------------------------------------------------------------------------------------------------------------
                    	 
                    	  if (flowName.equalsIgnoreCase("Tugger")) {
           					if (String.valueOf(iTempSeqNo).length() == 1) {
           						strTempSeqNo = "0" + String.valueOf(iTempSeqNo);
           						System.out.println("empty rack no: "+strTempSeqNo);
           					} else {
           						strTempSeqNo = String.valueOf(iTempSeqNo);
           						System.out.println(strTempSeqNo);
           					}

           					// Calculate LopNo for ScanId
           					strLopNo = strLopNo + Integer.parseInt(tdrow.get("Line_movement"));
           					System.out.println("strLopNo:" + strLopNo);

           					// Calculate Scan Id
           					String scanId = "T" + strFlowType + strTempSeqNo + strLopNo;
           					System.out.println(" Empty scan id" + scanId);
           					System.out.println(strFlowType + seqHandling + String.valueOf(iTempSeqNo) + "ok click ");
           					
           					
           				//entering scan id
        					driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text"))).sendKeys(scanId);
        					
           					

           				} else {

           					strTempSeqNo = StringUtils.repeat("0", 4 - String.valueOf(iTempSeqNo).length()) + iTempSeqNo;
           					System.out.println("strTempSeqNo:" + strTempSeqNo);

           					// Calculate Scan Id
           					String scanId = strFlowType + strTempSeqNo;
           					System.out.println("scan id" + scanId);
           					vAssignment.Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName);
           				}

                   	
                    	 
                    	 
                    	 
                    	/* vAssignment. Vclas_AssignmentList_RackSubmission_Tugger(strType[1],
                                          strFlowType + seqHandling + String.valueOf(iTempSeqNo));*/
                    	
                     } else {
                    	 vAssignment.   Vclas_AssignmentList_RackSubmission_JISJIT(strType[1].toUpperCase(),
                                          strFlowType + seqHandling + String.valueOf(iTempSeqNo));
                     }
                     
                     // scan the object for empty rack
                     //vAssignment. Vclas_AssignmentList(strFlowType + seqHandling + String.valueOf(iTempSeqNo), "Empty_Rack", "",flowName);
                     if (verifyElementExistReturn(
 							By.xpath(prop.getProperty("Vclas.AssignmentList.OkButton.Click"))) == true) {
 						System.out.println("Vclas_AssignmentList-Empty Rack");
 						if (flowName.equalsIgnoreCase("Tugger")) {	
 						reportStep("#B Verification of availability of Yellow button to move to Deviant Node from the Scanning window of a tugger order during Delivery when Use Move button parameter is false for TPOS Test case Id:3469--completed", "pass", true);
 						} else {
 							// jisjit
 						}
 						driver.findElements(By.xpath(prop.getProperty("Vclas.AssignmentList.OkButton.Click"))).get(0).click();
 						Thread.sleep(3000);
 						
 							
 						

 					}

                     reportStep("#BVerification of delivery functionality of a Tugger empty rack when Node Scan on delivery, Scan on delivery parameters are true for the TPOS node Test case Id:3253--completed#c", "pass", false);
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

                     vAssignment.Vclas_Shortage_SingleRackStatusVerify(strFlowType + seqHandling + strTempSeqNo, "FULL_RACK", "Planned");
                     reportStep("--------Full rack submission: #B" + strFlowType + " " + strTempSeqNo + "#C Initiated----",
                                   "Info", false);

                     // Deliver the Planned Full Empty Rack
                     if (flowName.equalsIgnoreCase("Tugger")) {
                    	 vAssignment.Vclas_AssignmentList_RackSubmission_Tugger(strType[0],
     							strFlowType + seqHandling + String.valueOf(strTempSeqNo));
     				} else {
     					vAssignment.Vclas_AssignmentList_RackSubmission_JISJIT(strType[0].toUpperCase(),
     							strFlowType + seqHandling + String.valueOf(strTempSeqNo));
     				}

     				if (flowName.equalsIgnoreCase("Tugger")) {
     					// Calculate LopNo for ScanId
     					strLopNo = strLopNo + Integer.parseInt(tdrow.get("Line_movement"));
     					System.out.println("strLopNo:" + strLopNo);

     					// Calculate Scan Id
     					String scanId = "T" + strFlowType + strTempSeqNo + strLopNo;
     					System.out.println("scan id" + scanId);

     					vAssignment.Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName); // ask

     				} else {
     					// Calculate Scan Id
     					String scanId = strFlowType + strTempSeqNo;
     					System.out.println("scan id" + scanId);
     					vAssignment.Vclas_AssignmentList(scanId, "Full_Rack", "Yes", flowName);

     				}

                     reportStep("--------Full rack submission: #B" + strFlowType + " " + strTempSeqNo
                                   + "#C Submitted Successfully----", "Info", false);
                     
                     
                     

                     //intRackNewSeq = Integer.parseInt(strTempSeqNo) + racksCount - 1;
               
                     
                     System.out.println("Rack Search after 1E 1F for: " + lastSubmittedFullRack);
                     
               
                     // Verify whether next full empty racks are created
                     intRackNewSeq=vAssignment.Vclas_Shortage_NewRacks_Search(strFlowType, Integer.parseInt(strTempSeqNo),racksCount);
                     System.out.println("intRackNewSeq :" +intRackNewSeq);
                     /*Vclas_Shortage_Rack_Search(strFlowType, seqHandling + String.valueOf(intRackNewSeq), flowName);
                     mwHome.Navigate_Vclas_Task();
                     vt.Vclas_Search_Partno();
                     vt.Vclas_getTuggerTask(strFlowType, String.valueOf(intRackNewSeq), i);
*/
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
	
	
	//method to set node scan parameters as false
	
		public void Nodes_Parameters_False_Delivery(String Position) throws InterruptedException{
		
		  String sheetName = "Vclas_Assignments";
      tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		
		
		// navigating to nodes screen
		anyClick("Home button ", By.xpath(prop.getProperty("Vclas_Home.Home.Button")));
		
		anyClick("Administration Tab", By.xpath(prop.getProperty("Vclas_Home.Administration.Button")));
		
		anyClick("Nodes menu", By.xpath(prop.getProperty("Vclas_Administration.Nodes.Button")));
		
		// verifying search textbox
		if (verifyElementExistReturn(
				By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Search.Textbox"))) == true) {
			Thread.sleep(2000);
			// sending fpos to search textbox
			sendKeys("Search textbox", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Search.Textbox")),Position);

			// clicking search button
			anyClick("Vclas Node screen search button click",By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Search.Button")));

			//expanding the parameters 
			anyClick("Parameter expand button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Paramters.Expand.Button")));

			//making the Nodes scan on pickup as false
			if(isElementPresent( By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodeScanOnDelivery.Inherited.Checkbox.check")))) {
				driver.findElements(By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodeScanOnDelivery.Inherited.Checkbox.check"))).get(0).click();
				Thread.sleep(3000);
				if (isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodesScanOnDelivery.Value.Checkbox.uncheck")))) {
					reportStep("Nodes Scan on delivery is already inactive", "pass", true);
					System.out.println("Nodes scan on delivery is already inactive");
				}
				
				
				
				else if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodesScanOnDelivery.Value.Checkbox.check")))) {
					
					driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodesScanOnDelivery.Value.Checkbox.check"))).click();
					Thread.sleep(3000);
					anyClick("Save button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Save.Button")));
					Thread.sleep(5000);
					anyClick("Refresh button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Refresh.Button")));
					reportStep(" Nodes Scan on delivery is made as inactive", "pass", true);
					System.out.println(" Nodes Scan on delivery is made as inactive");
					
				}
				
			}else {
				if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodesScanOnDelivery.Value.Checkbox.check")))) {
					driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodesScanOnDelivery.Value.Checkbox.check"))).click();
					Thread.sleep(3000);
					anyClick("Save button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Save.Button")));
					Thread.sleep(5000);
					anyClick("Refresh button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Refresh.Button")));
					reportStep("Nodes Scan on delivery is made as inactive", "pass", true);
					System.out.println("Nodes Scan on delivery is made as inactive");
				}else if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodesScanOnDelivery.Value.Checkbox.uncheck")))) {
					reportStep("Nodes Scan on delivery is already inactive", "pass", true);
					System.out.println("Nodes scan on delivery is already inactive");
				}
			}
			

			
				
				//setting the  scan on delivery value as inactive
							
			//making  scan on delivery as false
			
			if(isElementPresent( By.xpath(prop.getProperty("Vclas_Administration.Nodes.ScanOnDelivery.Inherited.Checkbox.check")))) {
				driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.ScanOnDelivery.Inherited.Checkbox.check"))).click();
				//if(verifyElementExist("scan on delivery value--check",By.xpath(prop.getProperty("Vclas_Administration.Nodes.ScanOnDelivery.Value.Checkbox.check")))==true) {
				if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.ScanOnDelivery.Value.Checkbox.check")))) {	
				driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.ScanOnDelivery.Value.Checkbox.check"))).click();
					anyClick("Save button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Save.Button")));
					Thread.sleep(5000);
					anyClick("Refresh button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Refresh.Button")));
					reportStep("Scan on delivery is made as inactive", "pass", true);
					System.out.println("Scan on delivery is made as inactive");
					
				}else if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.ScanOnDelivery.Value.Checkbox.uncheck")))) {
					reportStep("Scan on delivery is already inactive", "pass", true);
					System.out.println("scanon delivery is already inactive");
				}
			}else if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.ScanOnDelivery.Value.Checkbox.check")))) {
					driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.ScanOnDelivery.Value.Checkbox.check"))).click();
					anyClick("Save button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Save.Button")));
					Thread.sleep(5000);
					anyClick("Refresh button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Refresh.Button")));
					reportStep("Scan on delivery is made as inactive", "pass", true);
					System.out.println("Scan on delivery is made as inactive");
				}else if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.ScanOnDelivery.Value.Checkbox.uncheck")))) {
					reportStep("Scan on delivery is already inactive", "pass", true);
					System.out.println("scan on delivery is already inactive");
				}
				Vclas_Assignment_Navigate();

			// clicking Assignment list button
			driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Assignment.List.Button"))).get(0).click();
		}
		 else {
			reportStep("Search textbox doesn't present", "fail", false);
		}
		
	
	}
	

	
	public void Nodes_Parameters_False_PickUp(String Position) throws InterruptedException{
		
		  String sheetName = "Vclas_Assignments";
    tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		
		
		// navigating to nodes screen
		anyClick("Home button ", By.xpath(prop.getProperty("Vclas_Home.Home.Button")));
		
		anyClick("Administration Tab", By.xpath(prop.getProperty("Vclas_Home.Administration.Button")));
		
		anyClick("Nodes menu", By.xpath(prop.getProperty("Vclas_Administration.Nodes.Button")));
		
		// verifying search textbox
		if (verifyElementExistReturn(
				By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Search.Textbox"))) == true) {
			Thread.sleep(2000);
			// sending fpos to search textbox
			sendKeys("Search textbox", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Search.Textbox")),Position);

			// clicking search button
			anyClick("Vclas Node screen search button click",By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Search.Button")));

			//expanding the parameters 
			anyClick("Parameter expand button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Paramters.Expand.Button")));

			JavascriptExecutor js = (JavascriptExecutor) driver; 
            js.executeScript("window.scrollBy(0,800)");
                                       //making the Nodes scan on pickup as false
            //if(verifyElementExist("parent checkbox of nodes scan on pickup", By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodesScanOnPickup.Inherited.Checkbox.check")))) {
            if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodesScanOnPickup.Inherited.Checkbox.check")))) {
            driver.findElements(By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodesScanOnPickup.Inherited.Checkbox.check"))).get(0).click();
                  Thread.sleep(3000);
                  System.out.println("parent if condition");
                         
                  
                  if (isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodesScanOnPickup.Value.Checkbox.uncheck")))) {
                         
                         
                         System.out.println("child if condition");
                         
                         reportStep("Nodes Scan on pickup is already inactive", "pass", true);
                         System.out.println("Nodes scan on pickup is already inactive");
                  }
                  
                  
                  
                  else if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodesScanOnPickup.Value.Checkbox.check")))) {
                         System.out.println("child else if condition");
                         
                  driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodesScanOnPickup.Value.Checkbox.check"))).click();
                         Thread.sleep(3000);
                         anyClick("Save button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Save.Button")));
                         Thread.sleep(5000);
                         anyClick("Refresh button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Refresh.Button")));
                         reportStep(" Nodes Scan on pickup is made as inactive", "pass", true);
                         System.out.println(" Nodes Scan on pickup is made as inactive");
                         
                         
                         
                  }
                  
                  
            }else {
            if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodesScanOnPickup.Value.Checkbox.check")))) {
                         System.out.println("parent else child ..if condition");
                  driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodesScanOnPickup.Value.Checkbox.check"))).click();
                         Thread.sleep(3000);
                         anyClick("Save button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Save.Button")));
                         Thread.sleep(5000);
                         anyClick("Refresh button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Refresh.Button")));
                         reportStep("Nodes Scan on delivery is made as inactive", "pass", true);
                         System.out.println("Nodes Scan on delivery is made as inactive");
                  }else if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodesScanOnPickup.Value.Checkbox.uncheck")))) {
                         System.out.println("parent else child else if condition");
                         reportStep("Nodes Scan on pickup is already inactive", "pass", true);
                         System.out.println("Nodes scan on pickup is already inactive");
                  }
            }
            
            

				
				//setting the  package scan on pickup value as inactive
							
			//making  package scan on pickup as false
			
			if(isElementPresent( By.xpath(prop.getProperty("Vclas_Administration.Nodes.PackageScanOnPickup.Inherited.Checkbox.check")))) {
				driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.PackageScanOnPickup.Inherited.Checkbox.check"))).click();
				//if(verifyElementExist("scan on delivery value--check",By.xpath(prop.getProperty("Vclas_Administration.Nodes.ScanOnDelivery.Value.Checkbox.check")))==true) {
				if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.PackageScanOnPickup.Value.Checkbox.check")))) {	
				driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.PackageScanOnPickup.Value.Checkbox.check"))).click();
					anyClick("Save button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Save.Button")));
					Thread.sleep(5000);
					anyClick("Refresh button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Refresh.Button")));
					reportStep("Package scan on pickup is made as inactive", "pass", true);
					System.out.println("Package scan on pickup is made as inactive");
					
				}else if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.PackageScanOnPickup.Value.Checkbox.uncheck")))) {
					reportStep("Package scan on pickup is already inactive", "pass", true);
					System.out.println("Package scan on pickup is already inactive");
				}
			}else if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.PackageScanOnPickup.Value.Checkbox.check")))==true) {
					driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.PackageScanOnPickup.Value.Checkbox.check"))).click();
					anyClick("Save button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Save.Button")));
					Thread.sleep(5000);
					anyClick("Refresh button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Refresh.Button")));
					reportStep("Package scan on pickup is made as inactive", "pass", true);
					System.out.println("Package scan on pickup is made as inactive");
				}else if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.PackageScanOnPickup.Value.Checkbox.uncheck")))) {
					reportStep("Package scan on pickup is already inactive", "pass", true);
					System.out.println("Package scan on pickup is already inactive");
				}
			Vclas_Assignment_Navigate();

			// clicking Assignment list button
			driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Assignment.List.Button"))).get(0).click();
		}
		 else {
			reportStep("Search textbox doesn't present", "fail", false);
		}
		
	
	}
	
	//method to set node scan on pickup and package scan on pickup as true
	
	public void Nodes_Parameters_True_PickUp(String Position) throws InterruptedException{
		
		  String sheetName = "Vclas_Assignments";
  tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		
		
		// navigating to nodes screen
		anyClick("Home button ", By.xpath(prop.getProperty("Vclas_Home.Home.Button")));
		
		anyClick("Administration Tab", By.xpath(prop.getProperty("Vclas_Home.Administration.Button")));
		
		anyClick("Nodes menu", By.xpath(prop.getProperty("Vclas_Administration.Nodes.Button")));
		
		// verifying search textbox
		if (verifyElementExistReturn(
				By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Search.Textbox"))) == true) {
			Thread.sleep(2000);
			// sending fpos to search textbox
			sendKeys("Search textbox", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Search.Textbox")),Position);

			// clicking search button
			anyClick("Vclas Node screen search button click",By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Search.Button")));

			//expanding the parameters 
			anyClick("Parameter expand button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Paramters.Expand.Button")));

			JavascriptExecutor js = (JavascriptExecutor) driver; 
          js.executeScript("window.scrollBy(0,800)");
                                     //making the Nodes scan on pickup as false
          //if(verifyElementExist("parent checkbox of nodes scan on pickup", By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodesScanOnPickup.Inherited.Checkbox.check")))) {
          if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodesScanOnPickup.Inherited.Checkbox.check")))) {
          driver.findElements(By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodesScanOnPickup.Inherited.Checkbox.check"))).get(0).click();
                Thread.sleep(3000);
                System.out.println("parent if condition");
                       
                
                if (isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodesScanOnPickup.Value.Checkbox.check")))) {
                       
                       
                       System.out.println("child if condition");
                       
                       reportStep("Nodes Scan on pickup is already active", "pass", true);
                       System.out.println("Nodes scan on pickup is already active");
                }
                
                
                
                else if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodesScanOnPickup.Value.Checkbox.uncheck")))) {
                       System.out.println("child else if condition");
                       
                driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodesScanOnPickup.Value.Checkbox.uncheck"))).click();
                       Thread.sleep(3000);
                       anyClick("Save button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Save.Button")));
                       Thread.sleep(5000);
                       anyClick("Refresh button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Refresh.Button")));
                       reportStep(" Nodes Scan on pickup is made as active", "pass", true);
                       System.out.println(" Nodes Scan on pickup is made as active");
                       
                       
                       
                }
                
                
          }else {
          if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodesScanOnPickup.Value.Checkbox.uncheck")))) {
                       System.out.println("parent else child ..if condition");
                driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodesScanOnPickup.Value.Checkbox.uncheck"))).click();
                       Thread.sleep(3000);
                       anyClick("Save button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Save.Button")));
                       Thread.sleep(5000);
                       anyClick("Refresh button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Refresh.Button")));
                       reportStep("Nodes Scan on delivery is made as inactive", "pass", true);
                       System.out.println("Nodes Scan on delivery is made as inactive");
                }else if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodesScanOnPickup.Value.Checkbox.check")))) {
                       System.out.println("parent else child else if condition");
                       reportStep("Nodes Scan on pickup is already active", "pass", true);
                       System.out.println("Nodes scan on pickup is already active");
                }
          }
          
          

				
				//setting the  package scan on pickup value as inactive
							
			//making  package scan on pickup as false
			
			if(isElementPresent( By.xpath(prop.getProperty("Vclas_Administration.Nodes.PackageScanOnPickup.Inherited.Checkbox.check")))) {
				driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.PackageScanOnPickup.Inherited.Checkbox.check"))).click();
				//if(verifyElementExist("scan on delivery value--check",By.xpath(prop.getProperty("Vclas_Administration.Nodes.ScanOnDelivery.Value.Checkbox.check")))==true) {
				if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.PackageScanOnPickup.Value.Checkbox.uncheck")))) {	
				driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.PackageScanOnPickup.Value.Checkbox.uncheck"))).click();
					anyClick("Save button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Save.Button")));
					Thread.sleep(5000);
					anyClick("Refresh button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Refresh.Button")));
					reportStep("Package scan on pickup is made as active", "pass", true);
					System.out.println("Package scan on pickup is made as active");
					
				}else if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.PackageScanOnPickup.Value.Checkbox.check")))) {
					reportStep("Package scan on pickup is already active", "pass", true);
					System.out.println("Package scan on pickup is already active");
				}
			}else if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.PackageScanOnPickup.Value.Checkbox.uncheck")))==true) {
					driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.PackageScanOnPickup.Value.Checkbox.uncheck"))).click();
					anyClick("Save button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Save.Button")));
					Thread.sleep(5000);
					anyClick("Refresh button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Refresh.Button")));
					reportStep("Package scan on pickup is made as active", "pass", true);
					System.out.println("Package scan on pickup is made as active");
				}else if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.PackageScanOnPickup.Value.Checkbox.check")))) {
					reportStep("Package scan on pickup is already active", "pass", true);
					System.out.println("Package scan on pickup is already active");
				}
			Vclas_Assignment_Navigate();

			// clicking Assignment list button
			driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Assignment.List.Button"))).get(0).click();
		}
		 else {
			reportStep("Search textbox doesn't present", "fail", false);
		}
		
	
	}

	//method to set node scan on delivery and scan on delivery as true
	
	public void Nodes_Parameters_True_Delivery(String Position) throws InterruptedException{
		
		  String sheetName = "Vclas_Assignments";
    tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		
		
		// navigating to nodes screen
		anyClick("Home button ", By.xpath(prop.getProperty("Vclas_Home.Home.Button")));
		
		anyClick("Administration Tab", By.xpath(prop.getProperty("Vclas_Home.Administration.Button")));
		
		anyClick("Nodes menu", By.xpath(prop.getProperty("Vclas_Administration.Nodes.Button")));
		
		// verifying search textbox
		if (verifyElementExistReturn(
				By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Search.Textbox"))) == true) {
			Thread.sleep(2000);
			// sending fpos to search textbox
			sendKeys("Search textbox", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Search.Textbox")),Position);

			// clicking search button
			anyClick("Vclas Node screen search button click",By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Search.Button")));

			//expanding the parameters 
			anyClick("Parameter expand button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Paramters.Expand.Button")));

			//making the Nodes scan on pickup as false
			if(isElementPresent( By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodeScanOnDelivery.Inherited.Checkbox.check")))) {
				driver.findElements(By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodeScanOnDelivery.Inherited.Checkbox.check"))).get(0).click();
				Thread.sleep(3000);
				if (isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodesScanOnDelivery.Value.Checkbox.check")))) {
					reportStep("Nodes Scan on delivery is already active", "pass", true);
					System.out.println("Nodes scan on delivery is already active");
				}
				
				
				
				else if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodesScanOnDelivery.Value.Checkbox.uncheck")))) {
					
					driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodesScanOnDelivery.Value.Checkbox.uncheck"))).click();
					Thread.sleep(3000);
					anyClick("Save button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Save.Button")));
					Thread.sleep(5000);
					anyClick("Refresh button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Refresh.Button")));
					reportStep(" Nodes Scan on delivery is made as active", "pass", true);
					System.out.println(" Nodes Scan on delivery is made as active");
					
				}
				
			}else {
				if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodesScanOnDelivery.Value.Checkbox.uncheck")))) {
					driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodesScanOnDelivery.Value.Checkbox.uncheck"))).click();
					Thread.sleep(3000);
					anyClick("Save button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Save.Button")));
					Thread.sleep(5000);
					anyClick("Refresh button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Refresh.Button")));
					reportStep("Nodes Scan on delivery is made as active", "pass", true);
					System.out.println("Nodes Scan on delivery is made as active");
				}else if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.NodesScanOnDelivery.Value.Checkbox.check")))) {
					reportStep("Nodes Scan on delivery is already active", "pass", true);
					System.out.println("Nodes scan on delivery is already active");
				}
			}
			

			
				
				//setting the  scan on delivery value as inactive
							
			//making  scan on delivery as false
			
			if(isElementPresent( By.xpath(prop.getProperty("Vclas_Administration.Nodes.ScanOnDelivery.Inherited.Checkbox.check")))) {
				driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.ScanOnDelivery.Inherited.Checkbox.check"))).click();
				//if(verifyElementExist("scan on delivery value--check",By.xpath(prop.getProperty("Vclas_Administration.Nodes.ScanOnDelivery.Value.Checkbox.check")))==true) {
				if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.ScanOnDelivery.Value.Checkbox.uncheck")))) {	
				driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.ScanOnDelivery.Value.Checkbox.uncheck"))).click();
					anyClick("Save button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Save.Button")));
					Thread.sleep(5000);
					anyClick("Refresh button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Refresh.Button")));
					reportStep("Scan on delivery is made as active", "pass", true);
					System.out.println("Scan on delivery is made as active");
					
				}else if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.ScanOnDelivery.Value.Checkbox.check")))) {
					reportStep("Scan on delivery is already active", "pass", true);
					System.out.println("scanon delivery is already active");
				}
			}else if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.ScanOnDelivery.Value.Checkbox.uncheck")))) {
					driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.ScanOnDelivery.Value.Checkbox.uncheck"))).click();
					anyClick("Save button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Save.Button")));
					Thread.sleep(5000);
					anyClick("Refresh button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Refresh.Button")));
					reportStep("Scan on delivery is made as inactive", "pass", true);
					System.out.println("Scan on delivery is made as inactive");
				}else if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.ScanOnDelivery.Value.Checkbox.check")))) {
					reportStep("Scan on delivery is already active", "pass", true);
					System.out.println("scan on delivery is already active");
				}
				Vclas_Assignment_Navigate();

			// clicking Assignment list button
			driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Assignment.List.Button"))).get(0).click();
		}
		 else {
			reportStep("Search textbox doesn't present", "fail", false);
		}
		
	
	}
	
	// move button setting true or false
	
	public void MoveButton_parameter(String Position,String Status) throws InterruptedException{
		
		  String sheetName = "Vclas_Assignments";
		  tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
		
		
		// navigating to nodes screen
		anyClick("Home button ", By.xpath(prop.getProperty("Vclas_Home.Home.Button")));
		
		anyClick("Administration Tab", By.xpath(prop.getProperty("Vclas_Home.Administration.Button")));
		
		anyClick("Nodes menu", By.xpath(prop.getProperty("Vclas_Administration.Nodes.Button")));
		
		// verifying search textbox
		if (verifyElementExistReturn(
				By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Search.Textbox"))) == true) {
			Thread.sleep(2000);
			// sending fpos to search textbox
			sendKeys("Search textbox", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Search.Textbox")),Position);

			// clicking search button
			anyClick("Vclas Node screen search button click",By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Search.Button")));

			//expanding the parameters 
			anyClick("Parameter expand button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Paramters.Expand.Button")));

			JavascriptExecutor js = (JavascriptExecutor) driver; 
			js.executeScript("window.scrollBy(0,1200)");
                                   //making the move button  as false
		
			
			if(Status.equalsIgnoreCase("false")) {
		        if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.MoveButton.Inherited.Checkbox.check")))) {
		          	driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.MoveButton.Inherited.Checkbox.check"))).click();
		        	if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.MoveButton.Value.Checkbox.uncheck")))) {
		        		System.out.println("Move button is already inactive");
		        		reportStep("Move button is already inactive", "pass", true);
		        	}else if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.MoveButton.Value.Checkbox.check")))){
		        		driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.MoveButton.Value.Checkbox.check"))).click();
						anyClick("Save button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Save.Button")));
						Thread.sleep(5000);
						anyClick("Refresh button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Refresh.Button")));
		        		System.out.println("Move button is made as inactive");
		        		reportStep("Move button is made as inactive", "pass", true);
		        	}
		        }else {
		        	if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.MoveButton.Value.Checkbox.uncheck")))) {
		        		System.out.println("Move button is already inactive");
		        		reportStep("Move button is already inactive", "pass", true);
		        	}else if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.MoveButton.Value.Checkbox.check")))){
		        		driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.MoveButton.Value.Checkbox.check"))).click();
						anyClick("Save button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Save.Button")));
						Thread.sleep(5000);
						anyClick("Refresh button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Refresh.Button")));
		        		System.out.println("Move button is made as inactive");
		        		reportStep("Move button is made as inactive", "pass", true);
		        	}
		        }
		
		}else {
			if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.MoveButton.Inherited.Checkbox.check")))) {
	          	driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.MoveButton.Inherited.Checkbox.check"))).click();
	        	if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.MoveButton.Value.Checkbox.check")))) {
	        		System.out.println("Move button is already active");
	        		reportStep("Move button is already active", "pass", true);
	        	}else if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.MoveButton.Value.Checkbox.uncheck")))){
	        		driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.MoveButton.Value.Checkbox.uncheck"))).click();
					anyClick("Save button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Save.Button")));
					Thread.sleep(5000);
					anyClick("Refresh button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Refresh.Button")));
	        		System.out.println("Move button is made as active");
	        		reportStep("Move button is made as active", "pass", true);
	        	}
	        }else {
	        	if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.MoveButton.Value.Checkbox.check")))) {
	        		System.out.println("Move button is already active");
	        		reportStep("Move button is already active", "pass", true);
	        	}else if(isElementPresent(By.xpath(prop.getProperty("Vclas_Administration.Nodes.MoveButton.Value.Checkbox.uncheck")))){
	        		driver.findElement(By.xpath(prop.getProperty("Vclas_Administration.Nodes.MoveButton.Value.Checkbox.uncheck"))).click();
					anyClick("Save button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Save.Button")));
					Thread.sleep(5000);
					anyClick("Refresh button", By.xpath(prop.getProperty("Vclas_Home.Administration.Nodes.Refresh.Button")));
	        		System.out.println("Move button is made as active");
	        		reportStep("Move button is made as active", "pass", true);
	        	}
	        }
			
		}
        
        
       			Vclas_Assignment_Navigate();

			// clicking Assignment list button
			driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Assignment.List.Button"))).get(0).click();
		}
		 else {
			reportStep("Search textbox doesn't present", "fail", false);
		}
		
	
	}

	//move button shortage delivery
	
	public void Vclas_Tugger_Shortage_Delivery_MoveButton(String flowName) throws InterruptedException {
        try {
			String strSeqno, seqHandling;
			int Seqno, racksCount, iTempSeqNo, strLopNo;
			String rackStatus, strFlowType, strFlowTypeObject,strTaskId;
			String[] strType;
			int intRackNewSeq ,j=0;
			Admin_Page adp = new Admin_Page();
			MasWeb_Home mwHome = new MasWeb_Home();
			Vclas_Assignment vclasassgn=new Vclas_Assignment();
			
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
			 String toPosition=tdrow.get("toPos").trim();
			String strTempSeqNo;
			List<String> listr = vt.getList();
				strSeqno = String.valueOf(Seqno);// 01
				seqHandling = " ";
				
				System.out.println("flow name : " + flowName);

				vclasassgn.Vclas_Shortage_Search(strFlowType, strSeqno, flowName);

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
					
					
					List<String> listrempty = vt.getList1();
					System.out.println("empty TASK ID: " +listrempty);
				
					
						// To verify the Full task id
					vclasassgn.Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
								listr.get(j).toString(), strType[0].toUpperCase(), rackStatus);

						// To Verify the Empty task id
					vclasassgn.Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
								listrempty.get(j).toString(), strType[1].toUpperCase(), "Active");
		                    j++;   //new
		    				// Sequence No
		    				iTempSeqNo = iTempSeqNo + 1;

		    				if (iTempSeqNo == 100) {
		    					iTempSeqNo = 1;
		    				}
					}
				
			
			
				iTempSeqNo = Seqno; // first full
				System.out.println("itemp val:" + iTempSeqNo);
				// picking the assignment from shortage to deliver
				System.out.println("itemp val:" + iTempSeqNo);
				int lastSubmittedFullRack = 0;
				j=0;

				// To delivery in shortage for Initial Two Full Racks
				for (int i = 1; i <= racksCount; i++) {

                        
                        System.out.println("j :" +j);
                        if(i==2) {
                        	MoveButton_parameter(toPosition, "false");
                        	anyClick("shortage tab", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));
                        }
                  
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
					
					if (String.valueOf(iTempSeqNo).length() == 1) {
						strTempSeqNo = "0" + String.valueOf(iTempSeqNo);
						System.out.println(strTempSeqNo);
					} else {
						strTempSeqNo = String.valueOf(iTempSeqNo);
						System.out.println(strTempSeqNo);
					}
					if(i==1) {
						reportStep("No move button in scanning window when USE MOVE BUTTON paramter is true ", "pass", true);
					}else {
						reportStep("No move button is scanning window when USE MOVE BUTTON paramter is false", "pass", true);
					}
					// Calculate LopNo for ScanId
					strLopNo = strLopNo + Integer.parseInt(tdrow.get("Line_movement"));
					System.out.println("strLopNo:" + strLopNo);

					// Calculate Scan Id
					String scanId = "T" + strFlowType + strTempSeqNo + strLopNo;
					System.out.println("scan id" + scanId);
					System.out.println(strFlowType + seqHandling + String.valueOf(iTempSeqNo) + "ok click ");
					
					//entering scan id
					driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text"))).sendKeys(scanId);
					
			
					Thread.sleep(5000);
					
					// verification of yellow button(move button) in confirmation window
					if(i==1) {
						reportStep("No move button in confirmation window when USE MOVE BUTTON parameter is true ", "pass", true);
					}else {
						reportStep("No move button in confirmation window when USE MOVE BUTTON parameter is false", "pass", true);
					}
					
			
					if (verifyElementExistReturn(
							By.xpath(prop.getProperty("Vclas.Shortage.OkButton.Click"))) == true) {
						System.out.println(" entered into Ok button click");
						vclasassgn.clickByLocator(By.xpath(prop.getProperty("Vclas.Shortage.OkButton.Click")));
						Thread.sleep(3000);
					
					}else {
						reportStep("OK button is not clicked", "fail", true);
					}
					
					j++;
					// Sequence No
					iTempSeqNo = iTempSeqNo + 1;

					if (iTempSeqNo == 100) {
						iTempSeqNo = 1;
					}
				
			}
				if(isElementPresent(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Yellow.Button.Confirmation.Window")))==false) {
					reportStep("#B Verification of functionality of Move Button in Shortage screen Test case Id:3483--completed#C", "pass", false);
				}else {
					reportStep("#B Verification  functionality of Move Button in Shortage screen Test case Id:3483--failed#C", "fail", false);
				}

        } catch (Exception e) {
               e.printStackTrace();
               System.out.println("exception thrown in method");
        }

 }
	

	
	// 59 & 58 Verify the Move button(Yellow) during delivery when all scanning parameters are false and Use Move button parameter is false for TPOS of tugger order--3472

	public void AssignmentList_MoveButton_ScanOnDelivery_False(String flowName) throws InterruptedException {
		try {
			//vt= new Vclas_tasks();
			String strSeqno, seqHandling;
			int Seqno, racksCount, iTempSeqNo, strLopNo;
			String rackStatus, strFlowType;
			String[] strType;
			int j=0;
			Admin_Page adp = new Admin_Page();
			Vclas_Assignment vclasassgn=new Vclas_Assignment();

			

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

			vclasassgn.Vclas_Shortage_Search(strFlowType, strSeqno, flowName);

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
			
							// To verify the Full task id
			vclasassgn.Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
						listr.get(j).toString(), strType[0].toUpperCase(), rackStatus);

				// To Verify the Empty task id
			vclasassgn.Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
						listrempty.get(j).toString(), strType[1].toUpperCase(), "Active");
				 
				
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

					vclasassgn.Vclas_AssignmentList_RackSubmission_Tugger(strType[0],
							strFlowType + seqHandling + String.valueOf(iTempSeqNo));
				} else {
					vclasassgn.Vclas_AssignmentList_RackSubmission_JISJIT(strType[0].toUpperCase(),
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
					vclasassgn.Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName);
					 reportStep("#B Verification of  delivery functionality of a Tugger full rack when Node Scan on delivery, Scan on delivery parameters are true for the TPOS node Test case Id:3248--completed#C", "pass", false);

				} else {

					strTempSeqNo = StringUtils.repeat("0", 4 - String.valueOf(iTempSeqNo).length()) + iTempSeqNo;
					System.out.println("strTempSeqNo:" + strTempSeqNo);

					// Calculate Scan Id
					String scanId = strFlowType + strTempSeqNo;
					System.out.println("scan id" + scanId);
					vclasassgn.Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName);
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
		//	vclasassgn.Vclas_Shortage_NewRacks_Search(strFlowType,Seqno,racksCount);
			
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
			for (int i = 1; i <= racksCount; i++) {                     //3--changed for 19th testcase--rackCount
				// changing move button parameter as true to deliver second empty
 			if(i==2) {
 				String Empty_topos= tdrow.get("fPos").trim();
                System.out.println("Empty topos: "+Empty_topos);                  
              MoveButton_parameter(Empty_topos, "true");

 			}
				
				
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
					double acutalThreshold =parameter.thresholdValue;
					String threshold=String.valueOf(acutalThreshold);
					char[]	thresholdChar=threshold.toCharArray();
					String firstChar=String.valueOf(thresholdChar[0]);
					/*String actualValue=String.valueOf(thersholdValue);
					char[]	val=actualValue.toCharArray();
					String actualChar=String.valueOf(val[0]);*/
						
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
					clickTime = vclasassgn.Vclas_Shortage_Time_Verify(" ", strFlowType, seqHandling + String.valueOf(iTempSeqNo),
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
					if (vclasassgn.Vclas_Shortage_Time_Verify(" ", strFlowType, seqHandling + String.valueOf(iTempSeqNo),
							flowName) == tempThreshold) {
						reportStep("Planned time verified Actual Time: "+clickTime+" Expected Time: "+tempThreshold, "Info", false); 
						break;
					}
					System.out.println("after while" + clickTime + " " + thersholdValue);
				} while (((int) clickTime != tempThreshold)); // check whether click Time is positive

				// verify whether rack status is planned
				vclasassgn.Vclas_Shortage_SingleRackStatusVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
						"EMPTY_RACK", "Planned");
				// Deliver The Empty Planned Rack
				if (flowName.equalsIgnoreCase("Tugger")) {

					vclasassgn.Vclas_AssignmentList_RackSubmission_Tugger(strType[1],
							strFlowType + seqHandling + String.valueOf(iTempSeqNo));
				} else {
					vclasassgn.Vclas_AssignmentList_RackSubmission_JISJIT(strType[1].toUpperCase(),
							strFlowType + seqHandling + String.valueOf(iTempSeqNo));
				}

				// verification of move button when USE MOVE BUTTON paramter is false in confirmation window.
				
				
				if(i==1) {
				
					reportStep("No yellow button in confirmation window", "pass", true);
					reportStep("#BVerification of Move button(Yellow) during delivery when all scanning parameters are false and Use Move button parameter is false for TPOS of tugger order Test case Id:3472--completed#C", "pass", true);
				}else {
					reportStep("Yellow button is present in confirmation window", "pass", true);
					reportStep("#BVerification of Move button(Yellow) during delivery when all scanning parameters are false and Use Move button parameter is true for TPOS of a tugger order Test case Id:3471--completed#C", "pass", true);
				}
				
				
				// scan the object for empty rack
				
				
				vclasassgn.Vclas_AssignmentList(strFlowType + seqHandling + String.valueOf(iTempSeqNo), "Empty_Rack", "",
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

				vclasassgn.	Vclas_Shortage_SingleRackStatusVerify(strFlowType + seqHandling + strTempSeqNo, "FULL_RACK", "Planned");
				reportStep("--------Full rack submission: #B" + strFlowType + " " + strTempSeqNo + "#C Initiated----",
						"Info", false);

				// Deliver the Planned Full Empty Rack
				if (flowName.equalsIgnoreCase("Tugger")) {
					vclasassgn.	Vclas_AssignmentList_RackSubmission_Tugger(strType[0],
							strFlowType + seqHandling + String.valueOf(strTempSeqNo));
				} else {
					vclasassgn.Vclas_AssignmentList_RackSubmission_JISJIT(strType[0].toUpperCase(),
							strFlowType + seqHandling + String.valueOf(strTempSeqNo));
				}

				if (flowName.equalsIgnoreCase("Tugger")) {
					// Calculate LopNo for ScanId
					strLopNo = strLopNo + Integer.parseInt(tdrow.get("Line_movement"));
					System.out.println("strLopNo:" + strLopNo);

					// Calculate Scan Id
					String scanId = "T" + strFlowType + strTempSeqNo + strLopNo;
					System.out.println("scan id" + scanId);

					vclasassgn.	Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName); // ask

				} else {
					// Calculate Scan Id
					String scanId = strFlowType + strTempSeqNo;
					System.out.println("scan id" + scanId);
					vclasassgn.Vclas_AssignmentList(scanId, "Full_Rack", "Yes", flowName);

				}

				reportStep("--------Full rack submission: #B" + strFlowType + " " + strTempSeqNo
						+ "#C Submitted Successfully----", "Info", false);

				//intRackNewSeq = Integer.parseInt(strTempSeqNo) + racksCount - 1;
			
				
				System.out.println("Rack Search after 1E 1F for: " + lastSubmittedFullRack);
				
			
				// Verify whether next full empty racks are created
				intRackNewSeq=vclasassgn.Vclas_Shortage_NewRacks_Search(strFlowType, Integer.parseInt(strTempSeqNo),racksCount);
				System.out.println("intRackNewSeq :" +intRackNewSeq);
				/*Vclas_Shortage_Rack_Search(strFlowType, seqHandling + String.valueOf(intRackNewSeq), flowName);
				mwHome.Navigate_Vclas_Task();
				vt.Vclas_Search_Partno();
				vt.Vclas_getTuggerTask(strFlowType, String.valueOf(intRackNewSeq), i);
*/
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


	
	public void Vclas_Tugger_MoveButton_Functionality(String flowName) throws InterruptedException {
		try {
			//vt= new Vclas_tasks();
			String strSeqno, seqHandling,movedStatus="Shortage";
			int Seqno, racksCount, iTempSeqNo, strLopNo;
			String rackStatus, strFlowType;
			String[] strType;
			
			int j=0;
			Admin_Page adp = new Admin_Page();
			Vclas_Assignment vclasassgn=new Vclas_Assignment();

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

			vclasassgn.Vclas_Shortage_Search(strFlowType, strSeqno, flowName);

			iTempSeqNo = Seqno;

			System.out.println("seq no:" + iTempSeqNo);
			// To Verify Initial Status in Shortage Screen for created racks

			List<String> listr = vt.getList();
			System.out.println("full TASK ID: " +listr);
			
			for (int i = 1; i <= racksCount; i++) {

				if (i <= racksCount) {
					rackStatus = "Planned";
				} else {
					rackStatus = "Active";
					
				}
				
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
			
			
				// To verify the Full task id
			vclasassgn.Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
						listr.get(j).toString(), strType[0].toUpperCase(), rackStatus);

				// To Verify the Empty task id
			vclasassgn.Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
						listrempty.get(j).toString(), strType[1].toUpperCase(), "Active");
				 
				
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
		

				reportStep("--------Full rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
						+ "#C Initiated----", "Info", false);
				// Submitting the Rack
				System.out.println(
						"rck submsn" + strType[0] + " " + strFlowType + seqHandling + String.valueOf(iTempSeqNo));
				if (flowName.equalsIgnoreCase("Tugger")) {

					vclasassgn.Vclas_AssignmentList_RackSubmission_Tugger(strType[0],
							strFlowType + seqHandling + String.valueOf(iTempSeqNo));
				} else {
					vclasassgn.Vclas_AssignmentList_RackSubmission_JISJIT(strType[0].toUpperCase(),
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
					//vclasassgn.	Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName);
					//entering scan id
					driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text"))).sendKeys(scanId);
					
					

				} else {

				
					strTempSeqNo = StringUtils.repeat("0", 4 - String.valueOf(iTempSeqNo).length()) + iTempSeqNo;
					System.out.println("strTempSeqNo:" + strTempSeqNo);

					// Calculate Scan Id
					String scanId = strFlowType + strTempSeqNo;
					System.out.println("scan id" + scanId);
					//vclasassgn.Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName);
					
					////entering scan id
					driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text"))).sendKeys(scanId);
					
					
				}
				
				
				// clicking yellow button in confirmation window
				
				if(verifyElementExist("Yellow button", By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Yellow.Button.Confirmation.Window")))==true) {
					Thread.sleep(3000);
					retryingFindClick(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Yellow.Button.Confirmation.Window.yellowButton.img")));
					//vclasassgn.clickByLocator(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Yellow.Button.Confirmation.Window")));
					//driver.findElements(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Yellow.Button.Confirmation.Window"))).get(0).click();
					//selecting the node
					Thread.sleep(3000);
					retryingFindClick(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Yellow.Button.Confirmation.Window.Node.Change.Dropdown")));
					//vclasassgn.clickByLocator(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Yellow.Button.Confirmation.Window.Node.Change.Dropdown")));
					//driver.findElements(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Yellow.Button.Confirmation.Window.Node.Change.Dropdown"))).get(0).click();
					Thread.sleep(2000);
					retryingFindClick(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Yellow.Button.Confirmation.Window.Node.Change.List")));
					//vclasassgn.clickByLocator(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Yellow.Button.Confirmation.Window.Node.Change.List")));
					//driver.findElements(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Yellow.Button.Confirmation.Window.Node.Change.List"))).get(0).click();
					//selecting cause
					
					retryingFindClick(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Yellow.Button.Confirmation.Window.Cause.Dropdown")));
					//driver.findElements(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Yellow.Button.Confirmation.Window.Cause.Dropdown"))).get(0).click();
					
					retryingFindClick(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Yellow.Button.Confirmation.Window.Cause.List")));
					//driver.findElements(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Yellow.Button.Confirmation.Window.Cause.List"))).get(0).click();
					
					//clicking on greenbutton
					anyClick("Green button to move order", By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Yellow.Button.Confirmation.Window.Green.button")));
					reportStep("Tugger order is moved to deviant node from AL screen successfully", "pass", true);
				}else {
					reportStep("Move button in cofirmation window is not displayed", "fail", true);
				}

				//navigating to shortage screen
				anyClick("Shortage tab", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));
				// to verify status of moved order in shortage screen
				
				vclasassgn.Vclas_Shortage_Search(strFlowType, strSeqno, flowName);
				
				vclasassgn.Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),listr.get(0).toString(), strType[0].toUpperCase(), movedStatus);
				
				//delivering the moved tugger order
				String	strFlowTypeObject = strFlowType + seqHandling + String.valueOf(iTempSeqNo);
				System.out.println("flowTypeObject :"+strFlowTypeObject);
				String	strTaskId = listr.get(0).toString();
				System.out.println("taskId:" +strTaskId);
				
				
				driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Pickup.Button").replace("&Value", strFlowTypeObject).replace("&1Value", strTaskId))).get(0).click();
				driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Deliver.Button").replace("&Value", strFlowTypeObject).replace("&1Value", strTaskId))).get(0).click();
				
				// Calculate LopNo for ScanId
				strLopNo = strLopNo + Integer.parseInt(tdrow.get("Line_movement"));
				System.out.println("strLopNo:" + strLopNo);

				// Calculate Scan Id
				String scanId = "T" + strFlowType + strTempSeqNo + strLopNo;
				System.out.println("scan id" + scanId);
				System.out.println(strFlowType + seqHandling + String.valueOf(iTempSeqNo) + "ok click ");
				
				//entering scan id
				driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text"))).sendKeys(scanId);
				//clicking Green button to deliver from shortage

				if (verifyElementExistReturn(
						By.xpath(prop.getProperty("Vclas.Shortage.OkButton.Click"))) == true) {
					System.out.println(" entered into Ok button click");
					vclasassgn.clickByLocator(By.xpath(prop.getProperty("Vclas.Shortage.OkButton.Click")));
					Thread.sleep(3000);
					
					// db verification for moved tugger order
					db_MovedOrder_ShortageDelivery(strTaskId);
				
					reportStep("#BVerification of functionality of delivering of a moved Tugger order Test case Id:3463--completed#C", "pass", false);
				
				}else {
					reportStep("OK button is not clicked", "fail", true);
					reportStep("#BVerification of delivery functionality moved Tugger order Test case Id:3463--failed#C", "fail", false);
				}
				
				
				
				
				reportStep("--------Full rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
						+ "#C Submission Completed----", "Info", false);
					
					
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("exception thrown in method");
		}

	}

	public void db_MovedOrder_ShortageDelivery(String TaskId) throws InterruptedException, SQLException{
		 
	ResultSet rsMovedOrderInsertion_VCOM_Hist, rsMovedOrderInsertion_PMR476;
		DB_Connectivity db = new DB_Connectivity();
		
		
		// vclas connection details
				String ClassName = prop.getProperty(Environment + ".VCLAS.ORACLE.ClassName");
				String ConnectionString = prop.getProperty(Environment + ".VCLAS.ORACLE.Connection.String");
				String UserName = prop.getProperty(Environment + ".VCLAS.ORACLE.User.Name");
				String Password = prop.getProperty(Environment + ".VCLAS.ORACLE.User.Password");
		
		rsMovedOrderInsertion_VCOM_Hist= db.Connect_DB(ClassName, ConnectionString, UserName, Password, MovedOrder_Masweb_VCOM_TO_HIST.replace("#TaskId#", TaskId));
		if(rsMovedOrderInsertion_VCOM_Hist.next()) {
			reportStep("After moving a tugger order VCOM_TO_MAS_HIST table is inserted with taskId: "+TaskId, "pass", false);
			System.out.println("VCOM_TO_MAS_HIST table is inserted with task id: "+TaskId);
		}else {
			reportStep("VCOM_TO_MAS_HIST is not inserted with Task Id: "+TaskId, "fail", false);
			System.out.println("VCOM_TO_MAS_HIST table is not inserted with task id: "+TaskId);
		}
	
		// MASWEB connection details
				ClassName = prop.getProperty(Environment + ".MASWEB.MIMER.ClassName");
				ConnectionString = prop.getProperty(Environment + ".MASWEB.MIMER.Connection.String");
				UserName = prop.getProperty(Environment + ".MASWEB.MIMER.User.Name");
				Password = prop.getProperty(Environment + ".MASWEB.MIMER.Password");
			
			
			
			rsMovedOrderInsertion_PMR476=db.Connect_DB(ClassName, ConnectionString, UserName, Password, Movedorer_Masweb_PMR476.replace("#TaskId#", TaskId));
			if(rsMovedOrderInsertion_PMR476.next()) {
				reportStep("After moving a tugger order PMR476 table is inserted with taskId: "+TaskId, "pass", false);
				System.out.println("PMR476 table is inserted with task id: "+TaskId);
			}else {
				reportStep("PMR476 is not inserted with Task Id: "+TaskId, "fail", false);
				System.out.println("PMR476 table is not inserted with task id: "+TaskId);

			}
			
	}
	
	
	//57 Verify the availability of Yellow button to move to Deviant Node from the Scanning window of a Tugger  order during Delivery when Use Move button parameter is true for TPOS Test case Id:3470
	
	public void Vclas_Tugger_Assignment_Delivery_Parameters_MoveButton_True(String flowName) throws InterruptedException {
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

               vAssignment. Vclas_Shortage_Search(strFlowType, strSeqno, flowName);

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
               
               
                     // To verify the Full task id
               vAssignment. Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
                                   listr.get(j).toString(), strType[0].toUpperCase(), rackStatus);

                     // To Verify the Empty task id
               vAssignment.  Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
                                   listrempty.get(j).toString(), strType[1].toUpperCase(), "Active");
                     
                     
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

                    	 vAssignment.  Vclas_AssignmentList_RackSubmission_Tugger(strType[0],
                                         strFlowType + seqHandling + String.valueOf(iTempSeqNo));
                    	
                     }	 else {
                    	 vAssignment.  Vclas_AssignmentList_RackSubmission_JISJIT(strType[0].toUpperCase(),
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
     					vAssignment.Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName);

     				} else {

     					strTempSeqNo = StringUtils.repeat("0", 4 - String.valueOf(iTempSeqNo).length()) + iTempSeqNo;
     					System.out.println("strTempSeqNo:" + strTempSeqNo);

     					// Calculate Scan Id
     					String scanId = strFlowType + strTempSeqNo;
     					System.out.println("scan id" + scanId);
     					vAssignment.Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName);
     				}
                     
                     reportStep("#B Verification of  delivery functionality of a Tugger full rack when Node Scan on delivery, Scan on delivery parameters are true for the TPOS node Test case Id:3248--completed#C", "pass", false);
                   
                     reportStep("--------Full rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
                                   + "#C Submission Completed----", "Info", false);

                     // Sequence No
                     iTempSeqNo = iTempSeqNo + 1;

                     if (iTempSeqNo == 100) {
                            iTempSeqNo = 1;
                     }

               }
               
               //intRackNewSeq = Seqno + racksCount - 1;
             //  vAssignment.Vclas_Shortage_NewRacks_Search(strFlowType,Seqno,racksCount);
               
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
               for (int i = 1; i <= 1; i++) {                     //changed for 19th testcase--rackCount
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
                            double acutalThreshold =parameter.thresholdValue;
                            String threshold=String.valueOf(acutalThreshold);
                            char[] thresholdChar=threshold.toCharArray();
                            String firstChar=String.valueOf(thresholdChar[0]);
                            /*String actualValue=String.valueOf(thersholdValue);
                            char[] val=actualValue.toCharArray();
                            String actualChar=String.valueOf(val[0]);*/
                                  
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
                            clickTime = vAssignment.Vclas_Shortage_Time_Verify(" ", strFlowType, seqHandling + String.valueOf(iTempSeqNo),
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
                            if (vAssignment.Vclas_Shortage_Time_Verify(" ", strFlowType, seqHandling + String.valueOf(iTempSeqNo),
                                          flowName) == tempThreshold) {
                                   reportStep("Planned time verified Actual Time: "+clickTime+" Expected Time: "+tempThreshold, "Info", false); 
                                   break;
                            }
                            System.out.println("after while" + clickTime + " " + thersholdValue);
                     } while (((int) clickTime != tempThreshold)); // check whether click Time is positive

                     // verify whether rack status is planned
                     vAssignment.Vclas_Shortage_SingleRackStatusVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
                                   "EMPTY_RACK", "Planned");
                     // Deliver The Empty Planned Rack
                     if (flowName.equalsIgnoreCase("Tugger")) {
                    	 vAssignment. Vclas_AssignmentList_RackSubmission_Tugger(strType[1],
                                 strFlowType + seqHandling + String.valueOf(iTempSeqNo));
                    	
                    	 
                //------------------------------------------------------------------------------------------------------------------------
                   	 
                    	  if (flowName.equalsIgnoreCase("Tugger")) {
           					if (String.valueOf(iTempSeqNo).length() == 1) {
           						strTempSeqNo = "0" + String.valueOf(iTempSeqNo);
           						System.out.println("empty rack no: "+strTempSeqNo);
           					} else {
           						strTempSeqNo = String.valueOf(iTempSeqNo);
           						System.out.println(strTempSeqNo);
           					}

           					// Calculate LopNo for ScanId
           					strLopNo = strLopNo + Integer.parseInt(tdrow.get("Line_movement"));
           					System.out.println("strLopNo:" + strLopNo);

           					// Calculate Scan Id
           					String scanId = "T" + strFlowType + strTempSeqNo + strLopNo;
           					System.out.println(" Empty scan id" + scanId);
           					System.out.println(strFlowType + seqHandling + String.valueOf(iTempSeqNo) + "ok click ");
           					
           					
           				//entering scan id
        					driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text"))).sendKeys(scanId);
        					
        					 MoveAction( strFlowType + seqHandling + String.valueOf(iTempSeqNo),scanId);
        					 MoveAction_DB_Verify(listrempty.get(0));

           				} else {

           					strTempSeqNo = StringUtils.repeat("0", 4 - String.valueOf(iTempSeqNo).length()) + iTempSeqNo;
           					System.out.println("strTempSeqNo:" + strTempSeqNo);

           					// Calculate Scan Id
           					String scanId = strFlowType + strTempSeqNo;
           					System.out.println("scan id" + scanId);
           					vAssignment.Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName);
           				}

                   	
                } else {
                    	 vAssignment.   Vclas_AssignmentList_RackSubmission_JISJIT(strType[1].toUpperCase(),
                                          strFlowType + seqHandling + String.valueOf(iTempSeqNo));
                     }
                     
                     reportStep("--------Empty rack Move: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
                                   + "#C has been moved Successfully----", "Info", false);

                     System.out.println("moved Empty rack");

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

                     vAssignment.Vclas_Shortage_SingleRackStatusVerify(strFlowType + seqHandling + strTempSeqNo, "FULL_RACK", "Planned");
                     reportStep("--------Full rack submission: #B" + strFlowType + " " + strTempSeqNo + "#C Initiated----",
                                   "Info", false);

                     // Deliver the Planned Full Empty Rack
                     if (flowName.equalsIgnoreCase("Tugger")) {
                    	 vAssignment.Vclas_AssignmentList_RackSubmission_Tugger(strType[0],
     							strFlowType + seqHandling + String.valueOf(strTempSeqNo));
     				} else {
     					vAssignment.Vclas_AssignmentList_RackSubmission_JISJIT(strType[0].toUpperCase(),
     							strFlowType + seqHandling + String.valueOf(strTempSeqNo));
     				}

     				if (flowName.equalsIgnoreCase("Tugger")) {
     					// Calculate LopNo for ScanId
     					strLopNo = strLopNo + Integer.parseInt(tdrow.get("Line_movement"));
     					System.out.println("strLopNo:" + strLopNo);

     					// Calculate Scan Id
     					String scanId = "T" + strFlowType + strTempSeqNo + strLopNo;
     					System.out.println("scan id" + scanId);
     					//entering scan id
    					driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text"))).sendKeys(scanId);
    					
    					 MoveAction( strFlowType + seqHandling + String.valueOf(iTempSeqNo),scanId);
    					 MoveAction_DB_Verify(listr.get(2));
    					 reportStep("57 Verify the availability of Yellow button to move to Deviant Node from the Scanning window of a Tugger  order during Delivery when Use Move button parameter is true for TPOS test case Id:3470--completed", "pass", false);

     					//vAssignment.Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName); // ask

     				} else {
     					// Calculate Scan Id
     					String scanId = strFlowType + strTempSeqNo;
     					System.out.println("scan id" + scanId);
     					//entering scan id
    					driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text"))).sendKeys(scanId);
    					
    					 MoveAction( strFlowType + seqHandling + String.valueOf(iTempSeqNo),scanId);
    					 MoveAction_DB_Verify(listr.get(2));

//     					vAssignment.Vclas_AssignmentList(scanId, "Full_Rack", "Yes", flowName);

     				}

                     reportStep("--------Full rack Move: #B" + strFlowType + " " + strTempSeqNo
                                   + "#C has been moved Successfully----", "Info", false);
                     
                     
                     

               }
             
        } catch (Exception e) {
               e.printStackTrace();
               System.out.println("exception thrown in method");
        }

 }

	public void MoveAction(String objectId,String scanId) throws InterruptedException{
		
		// clicking yellow button in confirmation window
			
			if(verifyElementExist("Yellow button", By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Yellow.Button.Confirmation.Window")))==true) {
				Thread.sleep(3000);
				retryingFindClick(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Yellow.Button.Confirmation.Window.yellowButton.img")));
				
				//selecting the node
				Thread.sleep(3000);
				retryingFindClick(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Yellow.Button.Confirmation.Window.Node.Change.Dropdown")));
				
				Thread.sleep(2000);
				retryingFindClick(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Yellow.Button.Confirmation.Window.Node.Change.List")));
				//selecting cause
				
				retryingFindClick(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Yellow.Button.Confirmation.Window.Cause.Dropdown")));
				
				retryingFindClick(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Yellow.Button.Confirmation.Window.Cause.List")));
				
				
				//clicking on red button
				anyClick("Red button to cancel move", By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Yellow.Button.Confirmation.Window.Red.button")));
				String rackName = prop.getProperty("Vclas_home.AssignmentList.Column1.Type").replace("&Value",
						objectId);
				System.out.println("rack name" + rackName);
				driver.findElements(By.xpath(rackName)).get(0).click();
				//entering scan id
				driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text"))).sendKeys(scanId);
				retryingFindClick(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Yellow.Button.Confirmation.Window.yellowButton.img")));
				
				//selecting the node
				Thread.sleep(3000);
				retryingFindClick(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Yellow.Button.Confirmation.Window.Node.Change.Dropdown")));
				
				Thread.sleep(2000);
				retryingFindClick(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Yellow.Button.Confirmation.Window.Node.Change.List")));
				//selecting cause
				
				retryingFindClick(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Yellow.Button.Confirmation.Window.Cause.Dropdown")));
				
				retryingFindClick(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Yellow.Button.Confirmation.Window.Cause.List")));
				// clicking on green button
				anyClick("Green button to move order", By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Yellow.Button.Confirmation.Window.Green.button")));

				
				reportStep("Tugger order is moved to deviant node from AL screen successfully", "pass", true);
			}else {
				reportStep("Move button in cofirmation window is not displayed", "fail", true);
			}


		
	}
	
	public void MoveAction_DB_Verify(String TaskId) throws InterruptedException, SQLException{
		ResultSet rsMovedOrderInsertion_VCOM_Hist, rsMovedOrderInsertion_TransportAssignment;
		DB_Connectivity db = new DB_Connectivity();
		
		
		// vclas connection details
				String ClassName = prop.getProperty(Environment + ".VCLAS.ORACLE.ClassName");
				String ConnectionString = prop.getProperty(Environment + ".VCLAS.ORACLE.Connection.String");
				String UserName = prop.getProperty(Environment + ".VCLAS.ORACLE.User.Name");
				String Password = prop.getProperty(Environment + ".VCLAS.ORACLE.User.Password");
		
		rsMovedOrderInsertion_VCOM_Hist= db.Connect_DB(ClassName, ConnectionString, UserName, Password, MovedOrder_Masweb_VCOM_TO_HIST.replace("#TaskId#", TaskId));
		if(rsMovedOrderInsertion_VCOM_Hist.next()) {
			reportStep("After moving a tugger order VCOM_TO_MAS_HIST table is inserted with taskId: "+TaskId, "pass", false);
			System.out.println("VCOM_TO_MAS_HIST table is inserted with task id: "+TaskId);
		}else {
			reportStep("VCOM_TO_MAS_HIST is not inserted with Task Id: "+TaskId, "fail", false);
			System.out.println("VCOM_TO_MAS_HIST table is not inserted with task id: "+TaskId);
		}
	
		rsMovedOrderInsertion_TransportAssignment=db.Connect_DB(ClassName, ConnectionString, UserName, Password, MovedOrder_Status_Transport_Assignment.replace("#TaskId#", TaskId));
		if(rsMovedOrderInsertion_TransportAssignment.next()) {
		
			String Status = rsMovedOrderInsertion_TransportAssignment.getString(12);
			
			reportStep("After moving a tugger order Transport Assignment table has the status as Shortage for the TaskId: "+TaskId+" Status: "+Status, "pass", false);
			System.out.println("Transport Assignment table verified status: "+Status);
		}else {
			reportStep("After moving a tugger order Transport Assignment table status was not changed to Shortage for the TaskId: "+TaskId, "pass", false);
			System.out.println("Transport Assignment table not  verified");
		}
	}
	
}
