/*package com.volvo.mfg.agv.pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import com.volvo.automation.commonutilities.DB_Connectivity;
import com.volvo.automation.commonutilities.ExcelUtils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;

import com.volvo.mfg.StepDefinition.DriverSuite;;

public class Multiple_Tugger extends DriverSuite {
	public HashMap<String, String> tdrow;

	HashMap<String, String> testData = null;

	InputStream testFile = null;
	OutputStream outFile = null;
	XSSFWorkbook testWorkBook = null;
	DataFormatter formatter = new DataFormatter();
	String sheetName = "Multiple_Tugger";

	// Excel class object to access the function

	// retrieve data from MasWeb_Page
	// Load Test Data File
	public void MultipleRacksCreation() throws IOException, InterruptedException {
		int flagLoop = 0;
		
		try
		{
			
			do 
			{
				//Clearing the Supplier values in DB
				Sekadm sp =new Sekadm();
				sp.Prerequiste_tugger();
				
				// MasWeb page
				MasWeb_Home mwHome = new MasWeb_Home();
				mwHome.Navigate_Vclas_CreateNewRack();
				
				//Creating the Rack
				if (sp.sekadmCreate() == true)
				{
					break;
				}
				
				flagLoop = flagLoop + 1;
			}while (flagLoop != 3);
			
			if (flagLoop == 3)
			{
				reportStep("Creation of record failed maximum times", "Fail", false);
				throw new RuntimeException("FAILED");
			}
	  }

		catch (Exception e)
		{
			e.printStackTrace();
			System.err.println("Error in Test Data Sheet access - Exception");
			
	
	
		}
	
	}
	public void Multiple_Tugger_Flow()
	{
		try {

			int Seqno, racksCount, iTempSeqNo, strLopNo;
			String rackStatus, strFlowType;
			String[] strType;
			int intRackNewSeq;
			Admin_Page adp = new Admin_Page();
			MasWeb_Home mwHome = new MasWeb_Home();

			// Navigation to Shortage
			anyClick("Shortage Screen", By.xpath(prop.getProperty("Vclas_Home.Shortage.Button")));
			Thread.sleep(5000);

			String sheetName = "Vclas_Assignments";
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, sheetName);
			Seqno = Integer.parseInt(tdrow.get("Object"));
			
			racksCount = Integer.parseInt(tdrow.get("No_of_Racks"));
			strFlowType = tdrow.get("Sequence_Type");
			strType = tdrow.get("Shortage_Type_Expected").split(",");
			strLopNo = Integer.parseInt(tdrow.get("Lopnr"));
			String strTempSeqNo;
			Vclas_Assignment va =new Vclas_Assignment();

			// Search the shortage screen
			 va.Vclas_Shortage_Search(strFlowType, String.valueOf(Seqno));

			iTempSeqNo = Seqno;
			// 48,mfh

			// To Verify Initial Status in Shortage Screen for created racks
			for (int i = 1; i <= racksCount; i++) {

				if (i <= racksCount && i != 3) {
					rackStatus = "Planned";
				} else {
					rackStatus = "Active";
				}

				
				  // To verify the Full task id 
				va.Vclas_Shortage_TypeVerify(strFlowType + " " +
				 String.valueOf(iTempSeqNo), tdrow.get("Full_Task_" + i),
				  strType[0].toUpperCase(), rackStatus);
				  
				// To Verify the Empty task id 
				va.Vclas_Shortage_TypeVerify(strFlowType + " " +
				  String.valueOf(iTempSeqNo), tdrow.get("Empty_Task_" + i),
				  strType[1].toUpperCase(), "Active");
				

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

				if (i <= racksCount && i != 3) {
					
					reportStep("--------Full rack submission: #B"+ strFlowType + " " + String.valueOf(iTempSeqNo)+"#C Initiated----", "Info", false);
					// Submitting the Rack
					System.out
							.println("rck submsn" + strType[0] + " " + strFlowType + " " + String.valueOf(iTempSeqNo));
					va.Vclas_AssignmentList_RackSubmission(strType[0], strFlowType + " " + String.valueOf(iTempSeqNo));

					lastSubmittedFullRack = iTempSeqNo;

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
					// Scan the Goods
					va.Vclas_AssignmentList(scanId, "Full_Rack");
					
					reportStep("--------Full rack submission: #B"+ strFlowType + " " + String.valueOf(iTempSeqNo)+"#C Submission Completed----", "Info", false);

					// Sequence No
					iTempSeqNo = iTempSeqNo + 1;

					if (iTempSeqNo == 100) {
						iTempSeqNo = 1;
					}

				}

			}
	}
		 catch (Exception e) {
				e.printStackTrace();
				System.out.println("exception thrown in method");
			}
}
}
*/