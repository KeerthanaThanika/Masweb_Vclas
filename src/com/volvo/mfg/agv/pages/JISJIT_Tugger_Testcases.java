package com.volvo.mfg.agv.pages;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.volvo.automation.commonutilities.ExcelUtils;
import com.volvo.mfg.StepDefinition.DriverSuite;

public class JISJIT_Tugger_Testcases extends DriverSuite {
	public HashMap<String, String> tdrow;
	// Excel class object to access the function
		ExcelUtils excelUtils = new ExcelUtils();
		MasWeb_Home mwHome = new MasWeb_Home();
		Vclas_Assignment vclasAsgn = new Vclas_Assignment();
		public Vclas_tasks vt = new Vclas_tasks(); 
		List<String> listr = vt.getList();
		 List<String> listrempty = vt.getList1();
		 public static int intRackNewSeq;
	public void Vclas_JISJIT_Shortage_AL_InvalidInput(String flowName) throws InterruptedException {
		try {
			
			String strSeqno, seqHandling,strFlowTypeObject;
			int Seqno, racksCount, iTempSeqNo, strLopNo;
			String rackStatus, strFlowType, strTaskId;
			String[] strType;
			int intRackNewSeq ,j=0;
			Admin_Page adp = new Admin_Page();
			MasWeb_Home mwHome = new MasWeb_Home();
			
			reportStep("--------15 Verify the functionality of scanning Package Nr with invalid input from both SHORTAGE and A L screens when Manual Input is YES---Test case Id--2796--Initiated----", "Pass", false);
			
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
			

				strSeqno = tdrow.get("Object");// 0001
				System.out.println("seq no :" + strSeqno);
				seqHandling = StringUtils.repeat("0", 4 - (String.valueOf(Seqno).length())); // 000
				System.out.println("seq handling:" + seqHandling);
		
				System.out.println("flow name : " + flowName);

				vclasAsgn.Vclas_Shortage_Search(strFlowType, strSeqno, flowName);

				iTempSeqNo = Seqno;

				System.out.println("seq no:" + iTempSeqNo);
				// To Verify Initial Status in Shortage Screen for created racks
				int i = 1; 
					if (i <= racksCount) {
						rackStatus = "Planned";
					} else {
						rackStatus = "Active";
						
					}
					
					System.out.println("full TASK ID: " +listr);
					
						// To verify the Full task id
					vclasAsgn.Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
								listr.get(j).toString(), strType[0].toUpperCase(), rackStatus);

				
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
		Thread.sleep(3000);
		driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Deliver.Button").replace("&Value", strFlowTypeObject).replace("&1Value", strTaskId))).get(0).click();
		for(int k=1;k<=2;k++) {
		if (verifyElementExist("Scan Dialog Box",By.xpath(prop.getProperty("Vclas_home.AssignmentList.ScanDialogBox"))) == true) {
			reportStep("Assignment Scan dialog box is displayed successfully", "Info", true);
      Thread.sleep(3000);
      System.out.println("need to enter scan id");
     
      driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")))
		.sendKeys(getRandomString(3) + getRandomNumber(9999));
Thread.sleep(5000);
//Verifying The Message
List<WebElement> invalidMessage = driver.findElements(By.xpath(prop.getProperty("Vclas_AssignmentList.ErrorMessage.AGV")));
String invalidInput = invalidMessage.get(0).getText();
System.out.println("invalidInput: "+ invalidInput);
if (invalidInput.contains(tdrow.get("Invalid_Input_Error"))) {
System.out.println(" Invalid Input Error Message : " + invalidInput);

reportStep("Invalid Input Error - Message displayed " + invalidInput, "pass", true);
Thread.sleep(2000);

		}
driver.findElements(By.xpath(prop.getProperty("Vclas.Shortage.CancelButton.Click"))).get(0).click();
		
		}else {
			System.out.println("Scan dialog box does not appear");
		}
if(k==2) {
	break;
}
		// navigate to assignment page
		anyClick("Assignment list Areas", By.xpath(prop.getProperty("Vclas_Home.Assignment.List.Button")));
		reportStep("Assignment tab is clicked successfully ", "pass", false);
		String rackName = prop.getProperty("Vclas_home.AssignmentList.Column.Type").replace("&Value", strFlowType + seqHandling + String.valueOf(iTempSeqNo))
				.replace("&1Value", strType[0].toUpperCase());
		System.out.println("rack name" + rackName);
		// Verify the assignment area table is displayed
				if (verifyElementExistReturn(By.xpath(rackName)) == true) {
					driver.findElements(By.xpath(rackName)).get(0).click();
					Thread.sleep(5000);
					
				}
		}
		reportStep("--------15 Verify the functionality of scanning Package Nr with invalid input from both SHORTAGE and A L screens when Manual Input is YES---Test case Id--2796--Completed----", "Pass", false);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("exception thrown in method");
		}
}
	public void Vclas_JISJIT_Tugger_AssignmentList_CorrectSeq_InvalidInput(String flowName,String DeliveryPos) throws InterruptedException {
		try {
			reportStep("16 Verify the functionality of delivering a rack in correct sequence by copy pasting the correct and wrong package Nr in SCAN field when Manual Input is NO from A L screen--Initiated--Testcase Id-2797", "Info", true);
			String strSeqno, seqHandling,strFlowType,strTempSeqNo;
			int Seqno, iTempSeqNo,j=0;
			String[] strType;
			
			// Navigation to Shortage
			anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));
			Thread.sleep(5000);

			String sheetName = "Vclas_Assignments";
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
			Seqno = Integer.parseInt(tdrow.get("Object")); // 1
			strFlowType = tdrow.get("Sequence_Type");
			strType = tdrow.get("Shortage_Type_Expected").split(",");
			List<String> listr = vt.getList();
			strSeqno = tdrow.get("Object");// 0001
			System.out.println("seq no :" + strSeqno);
			seqHandling = StringUtils.repeat("0", 4 - (String.valueOf(Seqno).length())); // 000
			System.out.println("seq handling:" + seqHandling);
				System.out.println("flow name : " + flowName);

				vclasAsgn.Vclas_Shortage_Search(strFlowType, strSeqno, flowName);

				iTempSeqNo = Seqno;
				System.out.println("seq no:" + iTempSeqNo);
				// To Verify Initial Status in Shortage Screen for created racks
			int i = 1; 
						System.out.println("full TASK ID: " +listr);
				
						// To verify the Full task id
					vclasAsgn.Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
								listr.get(j).toString(), strType[0].toUpperCase(), "Planned");

					reportStep("--------Full rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
							+ "#C Initiated----", "Info", false);
					if(DeliveryPos.equalsIgnoreCase("AssignmentList")) {
					vclasAsgn.Vclas_AssignmentList_RackSubmission_JISJIT(strType[0].toUpperCase(),
							strFlowType + seqHandling + String.valueOf(iTempSeqNo));
					}else{
						reportStep("18 Verify the functionality of delivering a rack in correct sequence by copy pasting the correct and wrong package Nr in SCAN field when Manual Input is NO from SHORTAGE screen--Initiated--Testcase Id-2799", "Pass", true);
						driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Pickup.Button").replace("&Value", strFlowType +seqHandling + String.valueOf(iTempSeqNo)).replace("&1Value", listr.get(j).toString()))).get(0).click();
						Thread.sleep(2000);
						driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Deliver.Button").replace("&Value", strFlowType +seqHandling + String.valueOf(iTempSeqNo)).replace("&1Value", listr.get(j).toString()))).get(0).click();
					}
					strTempSeqNo = StringUtils.repeat("0", 4 - String.valueOf(iTempSeqNo).length()) + iTempSeqNo;
					System.out.println("strTempSeqNo:" + strTempSeqNo);

					// Calculate Scan Id
					String scanId = getRandomString(3) + getRandomNumber(999);
					System.out.println("scan id" + scanId);
					if (verifyElementExist("Scan Dialog Box",By.xpath(prop.getProperty("Vclas_home.AssignmentList.ScanDialogBox"))) == true) {
						reportStep("Assignment Scan dialog box is displayed successfully", "Info", true);
			      Thread.sleep(3000);
					driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")))
					.sendKeys(scanId);
					Thread.sleep(5000);
					//Verifying The Message
					List<WebElement> invalidMessage = driver.findElements(By.xpath(prop.getProperty("Vclas_AssignmentList.ErrorMessage.AGV")));
					String invalidInput = invalidMessage.get(0).getText();
					System.out.println("invalidInput: "+ invalidInput);
					if (invalidInput.contains(tdrow.get("Invalid_Input_Error"))) {
					System.out.println(" Invalid Input Error Message : " + invalidInput);
					reportStep("Invalid Input Error - Message displayed " + invalidInput, "pass", true);
					Thread.sleep(2000);
					}
					}
					scanId = strFlowType + strTempSeqNo;
					System.out.println("scan id" + scanId);
					driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")))
					.sendKeys(scanId);
					driver.findElements(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text")))
					.get(0).sendKeys(Keys.TAB);
					if (verifyElementExistReturn(
							By.xpath(prop.getProperty("Vclas.AssignmentList.OkButton.Click"))) == true) {

						driver.findElements(By.xpath(prop.getProperty("Vclas.AssignmentList.OkButton.Click"))).get(0).click();
					}
					if(DeliveryPos.equalsIgnoreCase("AssignmentList")) {
					reportStep("16 Verify the functionality of delivering a rack in correct sequence by copy pasting the correct and wrong package Nr in SCAN field when Manual Input is NO from A L screen--Completed--Testcase Id-2797", "Pass", true);
					}else {
						reportStep("18 Verify the functionality of delivering a rack in correct sequence by copy pasting the correct and wrong package Nr in SCAN field when Manual Input is NO from SHORTAGE screen--Completed--Testcase Id-2799", "Pass", true);
					}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("exception thrown in method");
		}
}
	
	public void Vclas_JISJIT_Tugger_Shortage_Assignment_Combo_Calloff(String flowName) throws InterruptedException {  //22-jisjit testcase  
		try {
			//vt= new Vclas_tasks();
			String strSeqno, seqHandling,scanId;
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

			vclasAsgn.Vclas_Shortage_Search(strFlowType, strSeqno, flowName);

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
			vclasAsgn.Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
						listr.get(j).toString(), strType[0].toUpperCase(), rackStatus);

				// To Verify the Empty task id
			vclasAsgn.Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
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

			
				reportStep("--------Full rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
						+ "#C Initiated----", "Info", false);
				// Submitting the Rack
				System.out.println(
						"rck submsn" + strType[0] + " " + strFlowType + seqHandling + String.valueOf(iTempSeqNo));
				if (flowName.equalsIgnoreCase("Tugger")) {

					vclasAsgn.Vclas_AssignmentList_RackSubmission_Tugger(strType[0],
							strFlowType + seqHandling + String.valueOf(iTempSeqNo));
				} else {
					vclasAsgn.Vclas_AssignmentList_RackSubmission_JISJIT(strType[0].toUpperCase(),
							strFlowType + seqHandling + String.valueOf(iTempSeqNo));
				}
				anyClick("Drop Button", By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Drop.icon")));
				
				// Navigation to Shortage
				anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));
				Thread.sleep(3000);
				// To verify the Full task id
				vclasAsgn.Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
							listr.get(0).toString(), strType[0].toUpperCase(), "Planned");
				
				if (flowName.equalsIgnoreCase("Tugger")) {

					vclasAsgn.Vclas_AssignmentList_RackSubmission_Tugger(strType[0],
							strFlowType + seqHandling + String.valueOf(iTempSeqNo));
				} else {
					vclasAsgn.Vclas_AssignmentList_RackSubmission_JISJIT(strType[0].toUpperCase(),
							strFlowType + seqHandling + String.valueOf(iTempSeqNo));
				}
				lastSubmittedFullRack = iTempSeqNo;
for(int k=1;k<=2;k++) {
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
					vclasAsgn.Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName);

				} else {

					strTempSeqNo = StringUtils.repeat("0", 4 - String.valueOf(iTempSeqNo).length()) + iTempSeqNo;
					System.out.println("strTempSeqNo:" + strTempSeqNo);

					// Calculate Scan Id
					scanId = "T" +strFlowType + strTempSeqNo;
					System.out.println("scan id" + scanId);
					vclasAsgn.Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName);
				}
				// Verifying The Message
				List<WebElement> invalidMessage = driver.findElements(By.xpath(prop.getProperty("Vclas_AssignmentList.ErrorMessage.AGV")));
				String invalidInput = invalidMessage.get(0).getText();
				System.out.println("invalidInput: "+ invalidInput);
				if (invalidInput.contains(tdrow.get("Invalid_Input_Error"))) {
				System.out.println(" Invalid Input Error Message : " + invalidInput);

				reportStep("Invalid Input Error - Message displayed " + invalidInput, "pass", true);
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
					vclasAsgn.Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName);

				} else {

					strTempSeqNo = StringUtils.repeat("0", 4 - String.valueOf(iTempSeqNo).length()) + iTempSeqNo;
					System.out.println("strTempSeqNo:" + strTempSeqNo);

					// Calculate Scan Id
					scanId = strFlowType + strTempSeqNo;
					System.out.println("scan id" + scanId);
					vclasAsgn.Vclas_AssignmentList(scanId, "Full_Rack", "Yes", flowName);
				}
				reportStep("--------Full rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
						+ "#C Submission Completed----", "Info", false);
if(k==2) {
	break;
}
				// Sequence No
				iTempSeqNo = iTempSeqNo + 1;
				
				// Navigation to Shortage
				anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));
				Thread.sleep(5000);
				driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Pickup.Button").replace("&Value", strFlowType + seqHandling + String.valueOf(iTempSeqNo)).replace("&1Value", listr.get(1).toString()))).get(0).click();
				Thread.sleep(3000);
				driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Deliver.Button").replace("&Value", strFlowType + seqHandling + String.valueOf(iTempSeqNo)).replace("&1Value", listr.get(1).toString()))).get(0).click();
}
vclasAsgn.Vclas_Shortage_NewRacks_Search(strFlowType,Seqno,racksCount);	
iTempSeqNo = Seqno;

System.out.println("seq no" + Seqno);
long clickTime;


// Sending Empty Racks and Full Racks
for (int i = 1; i <= 6; i++) {                     //changed for 19th testcase--rackCount
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
		clickTime = vclasAsgn.Vclas_Shortage_Time_Verify(" ", strFlowType, seqHandling + String.valueOf(iTempSeqNo),
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
		if (vclasAsgn.Vclas_Shortage_Time_Verify(" ", strFlowType, seqHandling + String.valueOf(iTempSeqNo),
				flowName) == tempThreshold) {
			reportStep("Planned time verified Actual Time: "+clickTime+" Expected Time: "+tempThreshold, "Info", false); 
			break;
		}
		System.out.println("after while" + clickTime + " " + thersholdValue);
	} while (((int) clickTime != tempThreshold)); // check whether click Time is positive

	// verify whether rack status is planned
	vclasAsgn.Vclas_Shortage_SingleRackStatusVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
			"EMPTY_RACK", "Planned");
	iTempSeqNo= iTempSeqNo+ 2;
	
	// verify whether full rack status is planned
	vclasAsgn.Vclas_Shortage_SingleRackStatusVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
			"FULL_RACK", "Planned");

	reportStep("--------Full rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
	+ "#C Initiated----", "Info", false);
// Submitting the Rack
System.out.println(
	"rck submsn" + strType[0] + " " + strFlowType + seqHandling + String.valueOf(iTempSeqNo));




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
if(!(i%2==0)) {
if (flowName.equalsIgnoreCase("Tugger")) {

	vclasAsgn.Vclas_AssignmentList_RackSubmission_Tugger(strType[0],
		strFlowType + seqHandling + String.valueOf(iTempSeqNo));
	vclasAsgn.Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName);
} else {
	vclasAsgn.Vclas_AssignmentList_RackSubmission_JISJIT(strType[0].toUpperCase(),
		strFlowType + seqHandling + String.valueOf(iTempSeqNo));
	vclasAsgn.Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName);
}
if (verifyElementExistReturn(
	By.xpath(prop.getProperty("Vclas_home.AssignmentList.WarningWindow"))) == true) {
reportStep("Assignment List Warning Window is displayed successfully", "Info", true);
vclasAsgn.Vclas_AssignmentList_WarningWindow();
}
driver.findElement(By.xpath(prop.getProperty("Vclas.AssignmentList.CancelButton.Click"))).click();

//driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Drop.icon"))).click();
anyClick("Drop Button", By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Drop.icon")));
iTempSeqNo=Seqno;
if (flowName.equalsIgnoreCase("Tugger")) {

	vclasAsgn.Vclas_AssignmentList_RackSubmission_Tugger(strType[0],
		strFlowType + seqHandling + String.valueOf(iTempSeqNo));
} else {
	vclasAsgn.Vclas_AssignmentList_RackSubmission_JISJIT(strType[0].toUpperCase(),
		strFlowType + seqHandling + String.valueOf(iTempSeqNo));
}
vclasAsgn.Vclas_AssignmentList(strFlowType + seqHandling + String.valueOf(iTempSeqNo), "Empty_Rack", "",
		flowName);
iTempSeqNo= iTempSeqNo+ 2;
if (flowName.equalsIgnoreCase("Tugger")) {

	vclasAsgn.Vclas_AssignmentList_RackSubmission_Tugger(strType[0],
		strFlowType + seqHandling + String.valueOf(iTempSeqNo));
} else {
	vclasAsgn.Vclas_AssignmentList_RackSubmission_JISJIT(strType[0].toUpperCase(),
		strFlowType + seqHandling + String.valueOf(iTempSeqNo));
}
vclasAsgn.Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName);
vclasAsgn.Vclas_Shortage_NewRacks_Search(strFlowType, Integer.parseInt(strTempSeqNo),racksCount);
iTempSeqNo= iTempSeqNo- 1;
}
else if(i%2==0) {
	int l=3;
	driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Pickup.Button").replace("&Value", strFlowType + seqHandling + String.valueOf(iTempSeqNo)).replace("&1Value", listr.get(l).toString()))).get(0).click();
	Thread.sleep(2000);
	driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Deliver.Button").replace("&Value", strFlowType + seqHandling + String.valueOf(iTempSeqNo)).replace("&1Value", listr.get(l).toString()))).get(0).click();
	 Thread.sleep(2000);
	if (verifyElementExistReturn(By.xpath(prop.getProperty("Vclas_home.AssignmentList.ScanDialogBox"))) == true) {
				reportStep("Assignment Scan dialog box is displayed successfully", "Info", true);
        Thread.sleep(3000);
        System.out.println("need to enter scan id");
       driver.findElement(By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Scan.Text"))).sendKeys(scanId);
				System.out.println("scan id entered");
				}
		Thread.sleep(2000);
if (verifyElementExistReturn(
			By.xpath(prop.getProperty("Vclas_home.AssignmentList.WarningWindow"))) == true) {
	 Thread.sleep(3000);
		reportStep("Assignment List Warning Window is displayed successfully", "Info", true);
		vclasAsgn.Vclas_AssignmentList_WarningWindow();
	}
driver.findElement(By.xpath(prop.getProperty("Vclas.AssignmentList.CancelButton.Click"))).click();
//navigate to assignment page
			anyClick("Assignment list Areas", By.xpath(prop.getProperty("Vclas_Home.Assignment.List.Button")));
			reportStep("Assignment tab is clicked successfully ", "pass", false);
			anyClick("Drop Button", By.xpath(prop.getProperty("Vclas_Home.AssignmentList.Drop.icon")));
			iTempSeqNo= iTempSeqNo- 2;
			if (flowName.equalsIgnoreCase("Tugger")) {

				vclasAsgn.Vclas_AssignmentList_RackSubmission_Tugger(strType[0],
					strFlowType + seqHandling + String.valueOf(iTempSeqNo));
				
			} else {
				vclasAsgn.Vclas_AssignmentList_RackSubmission_JISJIT(strType[0].toUpperCase(),
					strFlowType + seqHandling + String.valueOf(iTempSeqNo));
				}
			vclasAsgn.Vclas_AssignmentList(strFlowType + seqHandling + String.valueOf(iTempSeqNo), "Empty_Rack", "",
					flowName);
			iTempSeqNo= iTempSeqNo+ 2;
			if (flowName.equalsIgnoreCase("Tugger")) {

				vclasAsgn.Vclas_AssignmentList_RackSubmission_Tugger(strType[0],
					strFlowType + seqHandling + String.valueOf(iTempSeqNo));
			} else {
				vclasAsgn.Vclas_AssignmentList_RackSubmission_JISJIT(strType[0].toUpperCase(),
					strFlowType + seqHandling + String.valueOf(iTempSeqNo));
			}
			vclasAsgn.Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName);
			vclasAsgn.Vclas_Shortage_NewRacks_Search(strFlowType, Integer.parseInt(strTempSeqNo),racksCount);
			iTempSeqNo= iTempSeqNo- 1;
}else {
	iTempSeqNo= iTempSeqNo- 2;
	if (flowName.equalsIgnoreCase("Tugger")) {

		vclasAsgn.Vclas_AssignmentList_RackSubmission_Tugger(strType[1],
				strFlowType + seqHandling + String.valueOf(iTempSeqNo));
	} else {
		vclasAsgn.Vclas_AssignmentList_RackSubmission_JISJIT(strType[1].toUpperCase(),
				strFlowType + seqHandling + String.valueOf(iTempSeqNo));
	}

	// scan the object for empty rack
	vclasAsgn.Vclas_AssignmentList(strFlowType + seqHandling + String.valueOf(iTempSeqNo), "Empty_Rack", "",
			flowName);
	
	reportStep("--------Empty rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
			+ "#C Submitted Successfully----", "Info", false);

	System.out.println("Delivered Empty rack");

	iTempSeqNo = iTempSeqNo + 2;
	System.out.println("incremented val" + iTempSeqNo);
	System.out.println("current i value" + i);

	lastSubmittedFullRack = lastSubmittedFullRack + 1;

}
}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("exception thrown in method");
		}
}
	public void Vclas_Tugger_Shortage_Assignment_ComboFlow(String flowName) throws InterruptedException {    //27-jisjit testcase
		try {
			//vt= new Vclas_tasks();
			String strSeqno, seqHandling,scanId;
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

			vclasAsgn.Vclas_Shortage_Search(strFlowType, strSeqno, flowName);

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
			vclasAsgn.Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
						listr.get(j).toString(), strType[0].toUpperCase(), rackStatus);

				// To Verify the Empty task id
			vclasAsgn.Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
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

					vclasAsgn.Vclas_AssignmentList_RackSubmission_Tugger(strType[0],
							strFlowType + seqHandling + String.valueOf(iTempSeqNo));
				} else {
					vclasAsgn.Vclas_AssignmentList_RackSubmission_JISJIT(strType[0].toUpperCase(),
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
					scanId = "T" + strFlowType + strTempSeqNo + strLopNo;
					System.out.println("scan id" + scanId);
					System.out.println(strFlowType + seqHandling + String.valueOf(iTempSeqNo) + "ok click ");
					vclasAsgn.Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName);

				} else {

					strTempSeqNo = StringUtils.repeat("0", 4 - String.valueOf(iTempSeqNo).length()) + iTempSeqNo;
					System.out.println("strTempSeqNo:" + strTempSeqNo);

					// Calculate Scan Id
					scanId = strFlowType + strTempSeqNo;
					System.out.println("scan id" + scanId);
					vclasAsgn.Vclas_AssignmentList(scanId, "Full_Rack", "Yes", flowName);
				}

				reportStep("--------Full rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
						+ "#C Submission Completed----", "Info", false);

				// Sequence No
				iTempSeqNo = iTempSeqNo + 1;

				if (iTempSeqNo == 100) {
					iTempSeqNo = 1;
				}
				j=j-1;
				// Navigation to Shortage
				anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));
				Thread.sleep(5000);
				driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Pickup.Button").replace("&Value", strFlowType + seqHandling + String.valueOf(iTempSeqNo)).replace("&1Value", listr.get(j).toString()))).get(0).click();
				Thread.sleep(2000);
				driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Deliver.Button").replace("&Value", strFlowType + seqHandling + String.valueOf(iTempSeqNo)).replace("&1Value", listr.get(j).toString()))).get(0).click();
				 Thread.sleep(2000);
				 vclasAsgn.Vclas_AssignmentList(scanId, "Full_Rack", "Yes", flowName);
				 vclasAsgn.Vclas_Shortage_NewRacks_Search(strFlowType,Seqno,racksCount);
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
							clickTime = vclasAsgn.Vclas_Shortage_Time_Verify(" ", strFlowType, seqHandling + String.valueOf(iTempSeqNo),
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
							if (vclasAsgn.Vclas_Shortage_Time_Verify(" ", strFlowType, seqHandling + String.valueOf(iTempSeqNo),
									flowName) == tempThreshold) {
								reportStep("Planned time verified Actual Time: "+clickTime+" Expected Time: "+tempThreshold, "Info", false); 
								break;
							}
							System.out.println("after while" + clickTime + " " + thersholdValue);
						} while (((int) clickTime != tempThreshold)); // check whether click Time is positive

						// verify whether rack status is planned
						vclasAsgn.Vclas_Shortage_SingleRackStatusVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
								"EMPTY_RACK", "Planned");
						// Deliver The Empty Planned Rack
						if (flowName.equalsIgnoreCase("Tugger")) {

							vclasAsgn.Vclas_AssignmentList_RackSubmission_Tugger(strType[1],
									strFlowType + seqHandling + String.valueOf(iTempSeqNo));
						} else {
							vclasAsgn.Vclas_AssignmentList_RackSubmission_JISJIT(strType[1].toUpperCase(),
									strFlowType + seqHandling + String.valueOf(iTempSeqNo));
						}

						// scan the object for empty rack
						vclasAsgn.Vclas_AssignmentList(strFlowType + seqHandling + String.valueOf(iTempSeqNo), "Empty_Rack", "",
								flowName);
						
						reportStep("--------Empty rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
								+ "#C Submitted Successfully----", "Info", false);

						System.out.println("Delivered Empty rack");
					}
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("exception thrown in method");
			}
	}
	
	public void Vclas_JISJIT_Tugger_Shortage_CallOff(String flowName) throws InterruptedException {
		try {
			//vt= new Vclas_tasks();
			String strSeqno, seqHandling,balance1;
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

			vclasAsgn.Vclas_Shortage_Search(strFlowType, strSeqno, flowName);

			iTempSeqNo = Seqno;

			System.out.println("seq no:" + iTempSeqNo);
			// To Verify Initial Status in Shortage Screen for created racks
			for (int i = 1; i <= racksCount; i++) {

				if (i <= racksCount) {
					rackStatus = "Planned";
				} else {
					rackStatus = "Active";
					
				}
				
				// To verify the Full task id
				vclasAsgn.Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
						listr.get(j).toString(), strType[0].toUpperCase(), rackStatus);

				// To Verify the Empty task id
				vclasAsgn.Vclas_Shortage_TypeVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
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

					vclasAsgn.Vclas_AssignmentList_RackSubmission_Tugger(strType[0],
							strFlowType + seqHandling + String.valueOf(iTempSeqNo));
				} else {
					vclasAsgn.Vclas_AssignmentList_RackSubmission_JISJIT(strType[0].toUpperCase(),
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
					vclasAsgn.Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName);

				} else {

					strTempSeqNo = StringUtils.repeat("0", 4 - String.valueOf(iTempSeqNo).length()) + iTempSeqNo;
					System.out.println("strTempSeqNo:" + strTempSeqNo);

					// Calculate Scan Id
					String scanId = strFlowType + strTempSeqNo;
					System.out.println("scan id" + scanId);
					vclasAsgn.Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName);
				}

				reportStep("--------Full rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
						+ "#C Submission Completed----", "Info", false);

				// Sequence No
				iTempSeqNo = iTempSeqNo + 1;

				if (iTempSeqNo == 100) {
					iTempSeqNo = 1;
				}

			}
			
			vclasAsgn.Vclas_Shortage_NewRacks_Search(strFlowType,Seqno,racksCount);
			
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
					clickTime = vclasAsgn.Vclas_Shortage_Time_Verify(" ", strFlowType, seqHandling + String.valueOf(iTempSeqNo),
							flowName);
					System.out.println("clickTime:" +clickTime);
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
					if (vclasAsgn.Vclas_Shortage_Time_Verify(" ", strFlowType, seqHandling + String.valueOf(iTempSeqNo),
							flowName) == tempThreshold) {
						reportStep("Planned time verified Actual Time: "+clickTime+" Expected Time: "+tempThreshold, "Info", false); 
						break;
					}
					System.out.println("after while" + clickTime + " " + thersholdValue);
				} while (((int) clickTime != tempThreshold)); // check whether click Time is positive

				// verify whether rack status is planned
				vclasAsgn.Vclas_Shortage_SingleRackStatusVerify(strFlowType + seqHandling + String.valueOf(iTempSeqNo),
						"EMPTY_RACK", "Planned");
				// Deliver The Empty Planned Rack
				if (flowName.equalsIgnoreCase("Tugger")) {

					vclasAsgn.Vclas_AssignmentList_RackSubmission_Tugger(strType[1],
							strFlowType + seqHandling + String.valueOf(iTempSeqNo));
				} else {
					vclasAsgn.Vclas_AssignmentList_RackSubmission_JISJIT(strType[1].toUpperCase(),
							strFlowType + seqHandling + String.valueOf(iTempSeqNo));
				}

				// scan the object for empty rack
				vclasAsgn.Vclas_AssignmentList(strFlowType + seqHandling + String.valueOf(iTempSeqNo), "Empty_Rack", "",
						flowName);
				
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

				vclasAsgn.Vclas_Shortage_SingleRackStatusVerify(strFlowType + seqHandling + strTempSeqNo, "FULL_RACK", "Planned");
				reportStep("--------Full rack submission: #B" + strFlowType + " " + strTempSeqNo + "#C Initiated----",
						"Info", false);

				// Deliver the Planned Full Empty Rack
				if (flowName.equalsIgnoreCase("Tugger")) {
					vclasAsgn.Vclas_AssignmentList_RackSubmission_Tugger(strType[0],
							strFlowType + seqHandling + String.valueOf(strTempSeqNo));
				} else {
					vclasAsgn.Vclas_AssignmentList_RackSubmission_JISJIT(strType[0].toUpperCase(),
							strFlowType + seqHandling + String.valueOf(strTempSeqNo));
				}

				if (flowName.equalsIgnoreCase("Tugger")) {
					// Calculate LopNo for ScanId
					strLopNo = strLopNo + Integer.parseInt(tdrow.get("Line_movement"));
					System.out.println("strLopNo:" + strLopNo);

					// Calculate Scan Id
					String scanId = "T" + strFlowType + strTempSeqNo + strLopNo;
					System.out.println("scan id" + scanId);

					vclasAsgn.Vclas_AssignmentList(scanId, "Full_Rack", "No", flowName); // ask

				} else {
					// Calculate Scan Id
					String scanId = strFlowType + strTempSeqNo;
					System.out.println("scan id" + scanId);
					vclasAsgn.Vclas_AssignmentList(scanId, "Full_Rack", "Yes", flowName);
					
				}

				reportStep("--------Full rack submission: #B" + strFlowType + " " + strTempSeqNo
						+ "#C Submitted Successfully----", "Info", false);

				System.out.println("Rack Search after 1E 1F for: " + lastSubmittedFullRack);
					
				// Verify whether next full empty racks are created
				intRackNewSeq=vclasAsgn.Vclas_Shortage_NewRacks_Search(strFlowType, Integer.parseInt(strTempSeqNo),racksCount);
				System.out.println("intRackNewSeq :" +intRackNewSeq);
				
			}
			mwHome.Navigate_Admin_Simulator_Assembly_Line();
			clickTime = 1;

			reportStep("--------Empty rack submission: #B" + strFlowType + " " + String.valueOf(iTempSeqNo)
					+ "#C Initiated----", "Info", false);
			// Click Assembly Line in Masweb to get Empty Racks Planned
			// click once to get the correct time to move rack in assembly line
			adp.Simulator_Assemblyline_Click((int) clickTime);
			System.out.println("wait for time value to get reflected in vclas..");
			driver.switchTo().window(browser[2]);
			Thread.sleep(120000);

			// Retrieve time value to plan the racks
			clickTime = vclasAsgn.Vclas_Shortage_Time_Verify(" ", strFlowType, seqHandling + String.valueOf(iTempSeqNo),
					flowName);
			System.out.println("clickTime:" +clickTime);
			System.out.println("time verify done");
				balance1=driver.findElements(By.xpath(prop.getProperty("Vclas_Home.Shortage.Table") + "/tbody/tr[1]/td[5]")).get(0).getText();
				System.out.println("balance 1:" +balance1);
				if(balance1.length()==2) {
					System.out.println("Balance is of two digits");
				}else {
					System.out.println("Balance is not of two digits--Error");
				}
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("exception thrown in method");
			}
	}
}