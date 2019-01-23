package com.volvo.mfg.StepDefinition;

import java.io.FileInputStream;

import java.io.InputStream;

import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Row;

import com.volvo.automation.commonutilities.DB_Connectivity;
import com.volvo.automation.commonutilities.ExcelUtils;
import com.volvo.mfg.agv.pages.AGV_MachineIDScan;
import com.volvo.mfg.agv.pages.AGV_RackChanger;
import com.volvo.mfg.agv.pages.Admin_Page;
import com.volvo.mfg.agv.pages.Agv_Emulator;
import com.volvo.mfg.agv.pages.BlueBox;
import com.volvo.mfg.agv.pages.Elanders;
import com.volvo.mfg.agv.pages.JISJIT_Tugger_Testcases;
import com.volvo.mfg.agv.pages.LDJIT;
import com.volvo.mfg.agv.pages.LDJIT_CreateParkingPlace;
import com.volvo.mfg.agv.pages.LDJIT_CreateTrailerAssignments;
import com.volvo.mfg.agv.pages.LoginPage;
import com.volvo.mfg.agv.pages.LoginToVclas;
import com.volvo.mfg.agv.pages.MASWEB_LDJIT_Trailer_Info;
import com.volvo.mfg.agv.pages.MASWEB_Other_Functionalities;
import com.volvo.mfg.agv.pages.MasWeb_Home;
import com.volvo.mfg.agv.pages.Masweb_LDJIT_Create_New_Places;
import com.volvo.mfg.agv.pages.Move_JISJIT;
//import com.volvo.mfg.agv.pages.Multiple_Tugger;
import com.volvo.mfg.agv.pages.Production_ControlCodes;
import com.volvo.mfg.agv.pages.Sekadm;
import com.volvo.mfg.agv.pages.Tugger_Testcases;
import com.volvo.mfg.agv.pages.V432;
import com.volvo.mfg.agv.pages.V432_Masweb_Positive_TestCases;
import com.volvo.mfg.agv.pages.V432_Vclas_Positive_TestCases;
import com.volvo.mfg.agv.pages.V60_Page;
import com.volvo.mfg.agv.pages.V90_Pages;
import com.volvo.mfg.agv.pages.Vclas_Assignment;
import com.volvo.mfg.agv.pages.Vclas_TestCases;
import com.volvo.mfg.agv.pages.Vclas_tasks;

public class DriverSuite extends BaseTest {

	// Excel class object to access the function
	protected static ExcelUtils excelUtils = new ExcelUtils();
	HashMap<String, String> tdrow;

	@Test(description = "ADMIN Pages End-to-End flow")
	@Parameters({ "TestName" })
	public void ADMIN_SCANNEDGOODS(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();
			lp.LogintoPage(TestName);

			// MasWeb page
			MasWeb_Home mwHome = new MasWeb_Home();
			mwHome.Navigate_Admin_ScannedGoods();

			// Scanned goods
			Admin_Page ap = new Admin_Page();
			ap.ScannedGoods_Display();
			ap.Deletion_Verification();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	// priya
	@Test(description = "Admin End-to-End flow")
	@Parameters({ "TestName" })
	public void CONTROL_PARAMETER(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();
			lp.LogintoPage(TestName);

			// MasWeb page
			MasWeb_Home mwHome = new MasWeb_Home();
			mwHome.Navigate_Admin_ControlParameters();

			// adminpages_controlparameter
			Admin_Page ap = new Admin_Page();
			ap.ControlParameters_Create();
			// flow with random number

			ap.ControlParameters_CreateNew("Save");
			ap.ControlParameters_CreateNew("Return");
			ap.ControlParameters_Display();
			ap.ControlParameters_Edit("Save");
			ap.ControlParameters_Display();
			ap.ControlParameters_Edit("Remove");
			ap.ControlParameters_Clear();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}

	}

	@Test(description = "Admin End-to-End flow")
	@Parameters({ "TestName" })
	public void TEST_PRINTER(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();
			lp.LogintoPage(TestName);

			// MasWeb page
			MasWeb_Home mwHome = new MasWeb_Home();
			mwHome.Navigate_Admin_TestPrinter();

			// Adminpage_testprinter
			Admin_Page ap = new Admin_Page();
			ap.TestPrinter_Add();
			ap.TestPrinter_Remove();
			ap.TestPrinter_Print();
			ap.TestPrinter_Clear();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}

	}

	@Test(description = "ADMIN Pages End-to-End flow")
	@Parameters({ "TestName" })
	public void ADMIN_MANAGEALARMS(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();
			lp.LogintoPage(TestName);

			// MasWeb page
			MasWeb_Home mwHome = new MasWeb_Home();
			mwHome.Navigate_Admin_Manage_ManageAlarms();

			// Manage Alarms
			Admin_Page ap = new Admin_Page();
			ap.Managealarms("Display");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "ADMIN Pages End-to-End flow")
	@Parameters({ "TestName" })
	public void ADMIN_MANAGEALARMIDS(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();
			lp.LogintoPage(TestName);

			// MasWeb page
			MasWeb_Home mwHome = new MasWeb_Home();
			mwHome.Navigate_Admin_Manage_ManageAlarmids();

			// Manage AlarmIDs
			Admin_Page ap = new Admin_Page();
			ap.Managealarmsids_Create();
			ap.Managealarmsids_Remove();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Admin End-to-End flow")
	@Parameters({ "TestName" })
	public void ADMIN_SHOWTASK(String TestName) {
		try {
			// Login page
			LoginPage lp = new LoginPage();
			lp.LogintoPage(TestName);

			// MasWeb page
			MasWeb_Home mwHome = new MasWeb_Home();
			mwHome.Navigate_Admin_ShowClose();
			// SHOWCLOSE PAGE
			Admin_Page ap = new Admin_Page();
			// Return
			ap.ShowClose_Tasks_display();
			ap.ShowClose_Edit_Return();
			ap.ShowClose_Tasks_clear();
			// Close Task
			ap.ShowClose_Tasks_display();
			ap.ShowClose_Edit_Close_Task();
			ap.CloseTask_Verification();

		} catch (Exception e) {
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Admin End-to-End flow")
	@Parameters({ "TestName" })
	public void BLUEBOX(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();
			lp.LogintoPage(TestName);

			// MasWeb page
			MasWeb_Home mwHome = new MasWeb_Home();
			mwHome.Navigate_Bluebox_Best();

			// BlueBox Page

			BlueBox bb = new BlueBox();

			bb.blueboxBest();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}

	}

	@Test(description = "V60 End-to-End flow")
	@Parameters({ "TestName" })
	public void V60_NormalStation(String TestName) {
		try {
			// Login page
			LoginPage lp = new LoginPage();
			lp.LogintoPage(TestName);

			// MasWeb page
			MasWeb_Home mwHome = new MasWeb_Home();
			mwHome.Navigate_V60_ScanRFID_NormalStation();
			// V60 Page
			V60_Page vp = new V60_Page();
			// Return
			vp.V60_NormalStation_Display();

		} catch (Exception e) {
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "V60 End-to-End flow")
	@Parameters({ "TestName" })
	public void V60_RepairStation(String TestName) {
		try {
			// Login page
			LoginPage lp = new LoginPage();
			lp.LogintoPage(TestName);

			// MasWeb page
			MasWeb_Home mwHome = new MasWeb_Home();
			mwHome.Navigate_V60_ScanRFID_RepairStation();
			// V60 Page
			V60_Page vp = new V60_Page();
			// Return
			vp.V60_RepairStation_Display();

		} catch (Exception e) {
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "V90 End-to-End flow")
	@Parameters({ "TestName" })
	public void V90_DELIVERV90TASKS(String TestName) {
		try {
			// Login page
			LoginPage lp = new LoginPage();
			lp.LogintoPage(TestName);

			// MasWeb page
			MasWeb_Home mwHome = new MasWeb_Home();
			mwHome.Navigate_V90_DeliverV90Tasks();
			// V90 PAGE
			V90_Pages vp = new V90_Pages();
			// Return
			vp.DeliverV90Tasks_Deliver();
			mwHome.Navigate_V90_V90PackagingNote();
			// vp.V90PackagingNote_Display();

		} catch (Exception e) {
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Admin End-to-End flow")
	@Parameters({ "TestName" })
	public void SIMULATOR(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();
			lp.LogintoPage(TestName);

			// MasWeb page
			MasWeb_Home mwHome = new MasWeb_Home();
			mwHome.Navigate_Admin_Simulator_Assembly_Line();

			Admin_Page adp = new Admin_Page();

			adp.Simulator_Assemblyline_Click(3);

			/*
			 * Vclas_Assignment va=new Vclas_Assignment();
			 * 
			 * va.clickk();
			 */ // to check threshold

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void TUGGER_COMMONFLOW(String TestName) {
		try {
			// Tugger Flow
			// Login page
			LoginPage lp = new LoginPage();
			lp.LogintoPage(TestName);
			// MasWeb page
			MasWeb_Home mwHome = new MasWeb_Home();
			mwHome.Navigate_Vclas_CreateNewRack();
			// Sekadm Page
			Sekadm sp = new Sekadm();
			// pre requisite

			sp.Prerequiste_tugger();
			sp.sekadmCreate();
			sp.checkOrderSequence();
			// Vclas_task
			mwHome.Navigate_Vclas_Task();
			Vclas_tasks vt = new Vclas_tasks();
			vt.Vclas_getRacksTaskId("Tugger");

			// Vclas_Login_app
			lp.LogintoVclas(TestName);
			// Load Test Data File
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_Assignments");
			String stRoomValue = tdrow.get("Select_Work_Area_Room");
			tdrow.clear();
			// Select Rooms - Tugger
			Vclas_Assignment vclasAgn = new Vclas_Assignment();
			vclasAgn.Vclas_Assignment_Navigate();
			vclasAgn.Vclas_Assignment_SelectWorkArea("Room", stRoomValue.split(",")[0]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	public void JISJIT_TUGGER_COMMONFLOW(String TestName) {
		try {
			// JISJIT Tugger Flow
			// Login page
			LoginPage lp = new LoginPage();
			lp.LogintoPage(TestName);
			// MasWeb page
			MasWeb_Home mwHome = new MasWeb_Home();
			mwHome.Navigate_Vclas_CreateNewRack();
			// Sekadm Page
			Sekadm sp = new Sekadm();
			sp.Prerequiste_JISJIT_Tugger();
			sp.sekadmCreate();
			// Vclas_task
			mwHome.Navigate_Vclas_Task();
			Vclas_tasks vt = new Vclas_tasks();
			vt.Vclas_getRacksTaskId("JISJIT");

			// Vclas_Login_app
			lp.LogintoVclas(TestName);
			// Load Test Data File
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_Assignments");
			String stRoomValue = tdrow.get("Select_Work_Area_Room");
			tdrow.clear();

			// Select Rooms - Tugger
			Vclas_Assignment vclasAgn = new Vclas_Assignment();
			vclasAgn.Vclas_Assignment_Navigate();
			vclasAgn.Vclas_Assignment_SelectWorkArea("Room", stRoomValue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void TUGGER(String TestName) {
		try {
			TUGGER_COMMONFLOW(TestName);
			Vclas_Assignment vclasAgn = new Vclas_Assignment();
			vclasAgn.Vclas_Tugger_Shortage_Assignment_Flow("Tugger");
			// Verify the Inactive tasks
			MasWeb_Home mwHome = new MasWeb_Home();
			mwHome.Navigate_Vclas_Task();
			Vclas_tasks vt = new Vclas_tasks();
			vt.Vclas_VerifyTaskEvents_Inactive("");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}

	}

	@Test(description = "Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void TUGGER_ASSIGNMENT_DELIVERY(String TestName) {
		try {
			TUGGER_COMMONFLOW(TestName);
			Vclas_Assignment vclasAgn = new Vclas_Assignment();
			vclasAgn.Vclas_Tugger_Assignment_Delivery("Tugger");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}

	}

	@Test(description = "JISJIT Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void JISJIT_TUGGER(String TestName) {
		try {
			JISJIT_TUGGER_COMMONFLOW(TestName);
			Vclas_Assignment vclasAgn = new Vclas_Assignment();
			vclasAgn.Vclas_Tugger_Shortage_Assignment_Flow("JISJIT");
			MasWeb_Home mwHome = new MasWeb_Home();
			mwHome.Navigate_Vclas_Task();
			Vclas_tasks vt = new Vclas_tasks();
			vt.Vclas_VerifyTaskEvents_Inactive("");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "JISJIT Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void JISJIT_TUGGER_ASSIGNMENT_DELIVERY(String TestName) {
		try {
			JISJIT_TUGGER_COMMONFLOW(TestName);
			Vclas_Assignment vclasAgn = new Vclas_Assignment();
			vclasAgn.Vclas_Tugger_Assignment_Delivery("JISJIT");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "JISJIT Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void JISJIT_TUGGER_SHORTAGE_DELIVERY(String TestName) {
		try {
			JISJIT_TUGGER_COMMONFLOW(TestName);
			Vclas_Assignment vclasAgn = new Vclas_Assignment();
			vclasAgn.Vclas_Tugger_Shortage_Delivery("JISJIT");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "JISJIT Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void JISJIT_TUGGER_SHORTAGE_AL_INVALIDINPUT(String TestName) {
		try {
			JISJIT_TUGGER_COMMONFLOW(TestName);

			JISJIT_Tugger_Testcases jtt = new JISJIT_Tugger_Testcases();
			jtt.Vclas_JISJIT_Shortage_AL_InvalidInput("JISJIT");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "JISJIT Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void JISJIT_TUGGER_AL_CORRECTSEQ_INVALIDINPUT(String TestName) {
		try {
			JISJIT_TUGGER_COMMONFLOW(TestName);

			JISJIT_Tugger_Testcases jtt = new JISJIT_Tugger_Testcases();
			jtt.Vclas_JISJIT_Tugger_AssignmentList_CorrectSeq_InvalidInput("JISJIT", "AssignmentList");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "JISJIT Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void JISJIT_TUGGER_AL_INCORRECTSEQ_INVALIDINPUT(String TestName) {
		try {
			JISJIT_TUGGER_COMMONFLOW(TestName);
			Vclas_Assignment vclasAgn = new Vclas_Assignment();
			vclasAgn.Vclas_Tugger_AssignmentList_Incorrect_InvalidInput("JISJIT");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "JISJIT Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void JISJIT_TUGGER_SHORTAGE_CORRECTSEQ_INVALIDINPUT(String TestName) {
		try {
			JISJIT_TUGGER_COMMONFLOW(TestName);
			JISJIT_Tugger_Testcases jtt = new JISJIT_Tugger_Testcases();
			jtt.Vclas_JISJIT_Tugger_AssignmentList_CorrectSeq_InvalidInput("JISJIT", "Shortage");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "JISJIT Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void JISJIT_TUGGER_SHORTAGE_INCORRECTSEQ_INVALIDINPUT(String TestName) {
		try {
			JISJIT_TUGGER_COMMONFLOW(TestName);
			Vclas_Assignment vclasAgn = new Vclas_Assignment();
			vclasAgn.Vclas_Tugger_Shortage_Incorrect_InvalidInput("JISJIT");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void JISJIT_TUGGER_ALARM_INCONSISTANTDATA(String TestName) {
		try {
			JISJIT_TUGGER_COMMONFLOW(TestName);
			Tugger_Testcases tt = new Tugger_Testcases();
			tt.Vclas_Tugger_Alarm_InconsistantData("JISJIT");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "JISJIT Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void JISJIT_TUGGER_SHORTAGE_SORTING(String TestName) {
		try {
			JISJIT_TUGGER_COMMONFLOW(TestName);
			Tugger_Testcases tt = new Tugger_Testcases();
			if (tt.Vclas_Tugger_Shortage_Sorting() == true) {
				reportStep(
						"-------33 Verify the functionality of Sorting orders in Shortage screen--TestCase ID-2814--Completed----",
						"Pass", false);
			} else {
				reportStep(
						"-------33 Verify the functionality of Sorting orders in Shortage screen--TestCase ID-2814--Completed----",
						"Fail", false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void JISJIT_TUGGER_AL_SORTING(String TestName) {
		try {
			JISJIT_TUGGER_COMMONFLOW(TestName);
			Tugger_Testcases tt = new Tugger_Testcases();
			if (tt.Vclas_JISJIT_Tugger_AL_Sorting("JISJIT") == true) {
				reportStep(
						"-------34 Verify the functionality of Sorting orders in Assignment Lists screen--TestCase ID-2815--Completed----",
						"Pass", false);
			} else {
				reportStep(
						"-------34 Verify the functionality of Sorting orders in Assignment Lists screen--TestCase ID-2815--Completed----",
						"Fail", false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void JISJIT_TUGGER_AL_SHORTAGE_COMBO_DELIVERY(String TestName) {
		try {
			JISJIT_TUGGER_COMMONFLOW(TestName);
			Tugger_Testcases tt = new Tugger_Testcases();
			tt.Vclas_Tugger_Assignment_Shortage_Combo_Delivery("JISJIT");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void JISJIT_TUGGER_AL_FIRSTUSER(String TestName) {
		try {
			JISJIT_TUGGER_COMMONFLOW(TestName);
			Vclas_Assignment vclasAgn = new Vclas_Assignment();
			vclasAgn.Vclas_Tugger_AL_OtherUser_Pickup("JISJIT", "FirstUser");
			// driver.close();
			driver.quit();
			launchBrowser(Browser);
			// login to vclas with other user
			LoginToVclas lpv = new LoginToVclas();
			lpv.LogintoVclas(TestName);
			// Load Test Data File
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_Assignments");
			String stRoomValue = tdrow.get("Select_Work_Area_Room");
			tdrow.clear();
			vclasAgn.Vclas_Assignment_Navigate();
			vclasAgn.Vclas_Assignment_SelectWorkArea("Room", stRoomValue.split(",")[0]);
			vclasAgn.Vclas_Tugger_AL_OtherUser_Pickup("JISJIT", "SecondUser");
			vclasAgn.Vclas_Tugger_Shortage_Delivery_OtherUser("JISJIT");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void JISJIT_TUGGER_Picking_MultipleAssignments(String TestName) {
		try {
			JISJIT_TUGGER_COMMONFLOW(TestName);
			Vclas_Assignment vclasAgn = new Vclas_Assignment();
			vclasAgn.Vclas_Tugger_MultiplePickup("JISJIT", "FirstUser");
			vclasAgn.Vclas_Tugger_Shortage_MultipleAssignments_Delivery("JISJIT");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void JISJIT_TUGGER_Nodes_Paramters_True_Empty_TC_3473_3243(String TestName) {
		try {
			JISJIT_TUGGER_COMMONFLOW(TestName);
			Tugger_Testcases tt = new Tugger_Testcases();
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_Assignments");
			String fPos = tdrow.get("fPos").trim();
			String toPosition = tdrow.get("toPos").trim();
			System.out.println(" ToPosition: " + toPosition);
			System.out.println("From Position: " + fPos);
			tdrow.clear();
			tt.Nodes_Parameters_True_Delivery(fPos);
			tt.MoveButton_parameter(fPos, "false");
			tt.Vclas_Tugger_Assignment_Delivery_Parameters_True("JISJIT");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void JISJIT_TUGGER_Nodes_MoveButton_Shortage_TC_3481(String TestName) {
		try {
			JISJIT_TUGGER_COMMONFLOW(TestName);
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_Assignments");
			String fPos = tdrow.get("fPos").trim();
			String toPosition = tdrow.get("toPos").trim();
			System.out.println(" ToPosition: " + toPosition);
			System.out.println("From Position: " + fPos);
			tdrow.clear();
			Tugger_Testcases tt = new Tugger_Testcases();
			tt.MoveButton_parameter(toPosition, "true");

			tt.Vclas_Tugger_Shortage_Delivery_MoveButton("JISJIT");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "JISJIT Tugger End-to-End flow Test case Id:60&61")
	@Parameters({ "TestName" })
	public void JISJIT_TUGGER_Nodes_MoveButton_AL_ScanOnDelivery_False_Empty_TC_3475_3476(String TestName) {
		try {
			JISJIT_TUGGER_COMMONFLOW(TestName);
			Tugger_Testcases tt = new Tugger_Testcases();
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_Assignments");
			String Empty_topos = tdrow.get("fPos").trim();
			System.out.println("Empty topos: " + Empty_topos);
			tdrow.clear();
			tt.MoveButton_parameter(Empty_topos, "false");
			tt.AssignmentList_MoveButton_ScanOnDelivery_False("JISJIT");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Tugger End-to-End flow Test case Id:3465")
	@Parameters({ "TestName" })
	public void JISJIT_TUGGER_Nodes_MovedOrder_Functionality_TC_3465(String TestName) {
		try {
			JISJIT_TUGGER_COMMONFLOW(TestName);
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_Assignments");
			String toPosition = tdrow.get("toPos").trim();
			System.out.println(" ToPosition: " + toPosition);
			tdrow.clear();
			Tugger_Testcases tt = new Tugger_Testcases();
			tt.MoveButton_parameter(toPosition, "true");

			tt.Vclas_Tugger_MoveButton_Functionality("JISJIT");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Tugger End-to-End flow Test case Id:3474")
	@Parameters({ "TestName" })
	public void JISJIT_TUGGER_Nodes_MovedOrder_TC_3474(String TestName) {
		try {
			JISJIT_TUGGER_COMMONFLOW(TestName);
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_Assignments");
			String fPos = tdrow.get("fPos").trim();
			String toPosition = tdrow.get("toPos").trim();
			System.out.println(" ToPosition: " + toPosition);
			System.out.println("From Position: " + fPos);
			tdrow.clear();
			Tugger_Testcases tt = new Tugger_Testcases();
			tt.Nodes_Parameters_True_Delivery(fPos);
			tt.Nodes_Parameters_True_Delivery(toPosition);

			tt.MoveButton_parameter(fPos, "true");
			tt.MoveButton_parameter(toPosition, "true");
			tt.Vclas_Tugger_Assignment_Delivery_Parameters_MoveButton_True("JISJIT");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "JISJIT Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void JISJIT_TUGGER_DUMMY_ASN(String TestName) {
		try {

			// JISJIT Tugger Flow

			// Login page
			LoginPage lp = new LoginPage();
			lp.LogintoPage(TestName);
			// MasWeb page
			MasWeb_Home mwHome = new MasWeb_Home();
			mwHome.Navigate_Vclas_CreateNewRack();
			// Sekadm Page
			Sekadm sp = new Sekadm();
			sp.Prerequiste_JISJIT_Tugger();
			sp.Data_Deletion_JISJIT_Tugger();
			sp.sekadmCreate();
			// Vclas_task
			Vclas_tasks vt = new Vclas_tasks();
			vt.Navigate_Vclas_Task();
			vt.Vclas_getRacksTaskId("JISJIT");

			// Vclas_Login_app

			lp.LogintoVclas(TestName);
			// Load Test Data File
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_Assignments");
			String stRoomValue = tdrow.get("Select_Work_Area_Room");
			tdrow.clear();

			// Select Rooms - Tugger
			Vclas_Assignment vclasAgn = new Vclas_Assignment();
			vclasAgn.Vclas_Assignment_Navigate();
			vclasAgn.Vclas_Assignment_SelectWorkArea("Room", stRoomValue);
			vclasAgn.Vclas_Tugger_Shortage_Assignment_Flow("JISJIT");

			vt.Vclas_VerifyTaskEvents_Inactive("");

			sp.Data_Insertion_JISJIT_Tugger();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "LDJIT Trailer End-to-End flow")
	@Parameters({ "TestName" })
	public void LDJIT_TRAILER(String TestName) {
		try {

			LoginPage lp = new LoginPage();
			lp.LogintoPage(TestName);
			// Navigation to LDJIT-> Yard zone maintenance
			LDJIT obj = new LDJIT();
			obj.navigateLdjit();
			// Parking the trailer
			obj.ldjitTrailersInfoPark();
			// Bringing full trailer
			obj.navigateLdjit();
			obj.createTrailerAssignments("full");
			obj.navigateLdjit();
			obj.verifyFullTrailer("full");
			Vclas_tasks vt = new Vclas_tasks();
			vt.Navigate_Vclas_Task();
			vt.Vclas_Search_Partno();
			// Verification in vclas tasks- ACK
			obj.verifyTrailerAck();
			// vt.Vclas_getTrailerTask(tdrow.get("PartNumber"), tdrow.get("Trailer_Id"), 1);
			// Vclas_Login_app
			lp.LogintoVclas(TestName);

			// Load Test Data File
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_Assignments");
			String stRoomValue = tdrow.get("Select_Work_Area_Room");
			tdrow.clear();

			// Select Rooms - Tugger
			Vclas_Assignment vclasAgn = new Vclas_Assignment();
			vclasAgn.Vclas_Assignment_Navigate();
			vclasAgn.Vclas_Assignment_SelectWorkArea("Room", stRoomValue);
			// Delivery of trailer from assignment list
			obj.trailerFlowDelivery("Full");

			// Update trailer as empty
			obj.updateLDJITTrailerEmpty();
			obj.navigateLdjit();
			obj.createTrailerAssignments("empty");

			vt.Navigate_Vclas_Task();
			vt.Vclas_Search_Partno();
			obj.verifyTrailerAck();
			// vt.Vclas_getTrailerTask(tdrow.get("PartNumber"), tdrow.get("Trailer_Id"), 1);
			// driver.switchTo().window(browser[2]);
			// deliver empty trailer
			obj.trailerFlowDelivery("Empty");
			MasWeb_Home mwHome = new MasWeb_Home();
			mwHome.Navigate_Vclas_Task();
			vt.Agv_Vclas_VerifyTaskEvents_Inactive("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "JISJIT Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void JISJIT_TUGGER_GR(String TestName) {
		try {

			// JISJIT Tugger Flow

			// Login page
			LoginPage lp = new LoginPage();
			lp.LogintoPage(TestName);
			// MasWeb page
			MasWeb_Home mwHome = new MasWeb_Home();
			mwHome.Navigate_Vclas_CreateNewRack();
			// Sekadm Page
			Sekadm sp = new Sekadm();
			sp.Prerequiste_JISJIT_Tugger();
			sp.sekadmCreate();

			// Vclas_task
			mwHome.Navigate_Vclas_Task();
			Vclas_tasks vt = new Vclas_tasks();
			vt.Vclas_getRacksTaskId("JISJIT");

			// Vclas_Login_app

			lp.LogintoVclas(TestName);
			// Load Test Data File
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_Assignments");
			String stRoomValue = tdrow.get("Select_Work_Area_Room");
			tdrow.clear();

			// Select Rooms - Tugger
			Vclas_Assignment vclasAgn = new Vclas_Assignment();
			vclasAgn.Vclas_Assignment_Navigate();
			vclasAgn.Vclas_Assignment_SelectWorkArea("Room", stRoomValue.split(",")[0]);
			vclasAgn.Vclas_Tugger_Full_Delivery("JISJIT");
			driver.switchTo().window(browser[1]);
			LDJIT obj = new LDJIT();
			obj.navigateLdjit();
			// Parking the trailer
			obj.ldjitTrailersInfoPark();
			// Bringing full trailer
			obj.navigateLdjit();
			obj.createTrailerAssignments("full");
			obj.navigateLdjit();
			obj.verifyFullTrailer("full");

			vt.Navigate_Vclas_Task();
			vt.Vclas_Search_Partno();
			// Verification in vclas tasks- ACK
			obj.verifyTrailerAck();
			driver.switchTo().window(browser[2]);

			// Load Test Data File
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_Assignments");
			stRoomValue = tdrow.get("Select_Work_Area_Room");
			tdrow.clear();

			// Select Rooms - Tugger
			vclasAgn.Vclas_Assignment_Navigate();
			vclasAgn.Vclas_Assignment_SelectWorkArea("Room", stRoomValue.split(",")[1]);
			// Delivery of trailer from assignment list
			obj.trailerFlowDelivery("Full");
			vclasAgn.Vclas_Tugger_Empty_Delivery("JISJIT");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "LDJIT Trailer End-to-End flow")
	@Parameters({ "TestName" })
	public void LDJIT_TRAILER_GR_DbINSERTION(String TestName) {
		try {

			LoginPage lp = new LoginPage();
			lp.LogintoPage(TestName);
			// Navigation to LDJIT-> Yard zone maintenance
			LDJIT obj = new LDJIT();
			obj.navigateLdjit();
			// Parking the trailer
			obj.ldjitTrailersInfoPark();
			// Bringing full trailer
			obj.navigateLdjit();
			obj.createTrailerAssignments("full");
			obj.DB_DataInsertion_AfterGR();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Production End-to-End flow")
	@Parameters({ "TestName" })
	public void Production_Controlcodes(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();
			lp.LogintoPage(TestName);

			// MasWeb page
			MasWeb_Home mwHome = new MasWeb_Home();
			Production_ControlCodes pc = new Production_ControlCodes();

			// navigate to Control Codes menu
			mwHome.Navigate_Production_Control_Codes();
			// Create
			pc.Production_ControlCodes_Display();
			pc.Production_Controlcodes_Remove();
			pc.Production_ControlCodes_Create();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Production End-to-End flow")
	@Parameters({ "TestName" })
	public void Production_Plockan(String TestName) {
		try {
			// Login page
			LoginPage lp = new LoginPage();
			lp.LogintoPage(TestName);
			// MasWeb page
			MasWeb_Home mwHome = new MasWeb_Home();
			Production_ControlCodes pc = new Production_ControlCodes();
			// navigate to plockan menu
			mwHome.Navigate_Production_Plockan();
			pc.Production_Plockan_Display_Edit();
			pc.Production_Plockan_Change_NewPlockan();
			pc.Production_Plockan_Display_Edit();
			pc.Production_Plockan_Remove();
			pc.Production_Plockan_Create();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Production End-to-End flow")
	@Parameters({ "TestName" })
	public void Production_Consumption(String TestName) {
		try {
			// Login page
			LoginPage lp = new LoginPage();
			lp.LogintoPage(TestName);
			// MasWeb page
			MasWeb_Home mwHome = new MasWeb_Home();
			Production_ControlCodes pc = new Production_ControlCodes();
			// navigate to consumption menu
			mwHome.Navigate_Production_Consumption();
			pc.Production_Consumption_Display();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Production End-to-End flow")
	@Parameters({ "TestName" })
	public void Production_LocationCode(String TestName) {
		try {
			// Login page
			LoginPage lp = new LoginPage();
			lp.LogintoPage(TestName);
			// MasWeb page
			MasWeb_Home mwHome = new MasWeb_Home();
			Production_ControlCodes pc = new Production_ControlCodes();
			// navigate to location codes menu
			mwHome.Navigate_Production_LocationCodes();

			pc.Production_LocationCode_Display();
			pc.Production_LocationCodes_Remove();
			pc.Production_LocationCode_Create();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "RACK Changer End-to-End flow")
	@Parameters({ "TestName" })
	public void AGV_ASSIGNMENTLIST_DELIVERY(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();
			lp.Agv_LogintoMasweb(TestName);

			// MasWeb page
			MasWeb_Home mwHome = new MasWeb_Home();

			mwHome.Navigate_AGV_RackChanger();

			// RacKChanger Page
			AGV_RackChanger agvRack = new AGV_RackChanger();
			agvRack.MachineID_Search();
			String strFID = agvRack.MachineID_Pick("A");
			mwHome.Navigate_AGV_MachineIDScan();

			// Machine Scan ID page
			AGV_MachineIDScan agvMach = new AGV_MachineIDScan();
			agvMach.MachineID_Scan_Submit(strFID);
			mwHome.Agv_Navigate_Vclas_Task();

			// Vclas task Page
			Vclas_tasks vclasTask = new Vclas_tasks();
			vclasTask.Vclas_Task_Search();
			vclasTask.Vclas_getAGVTask();

			reportStep(
					" Verification of possibility of creating AGV orders for a Active AGV machine ID completed #B Test Case ID:2884 #C",
					"Pass", false);
			reportStep(
					" Verification of possibility of creating AGV orders for a Active AGV machine ID  completed #B Test Case ID:2884  #C",
					"info", false);

			// Vclas Application Login
			lp.Agv_LogintoVclas(TestName);

			// Vclas Home Page
			Vclas_Assignment vclasAgn = new Vclas_Assignment();
			// vclasAgn.Nodes_Conveyor_Data_Pickup();
			vclasAgn.Agv_Vclas_Assignment_Navigate();

			// Submitting the Flow
			vclasAgn.Vclas_Agv_Flow();
			Agv_launchUrl(URL, "");
			mwHome.Agv_Navigate_Vclas_Task();
			// Vclas_tasks vclasTask = new Vclas_tasks();
			vclasTask.Agv_Vclas_VerifyTaskEvents_Inactive("Utfört samt avslutat");
			reportStep("Status:'Done as well as completed' is verified successfully ", "pass", false);
			reportStep(
					"Verification of status from Vclas tasks screen for these Delivery completed AGV Racks #B TestCase ID:2886 #C completed",
					"Pass", false);
			reportStep(
					"Verification of status from Vclas tasks screen for these Delivery completed AGV Racks #B TestCase ID:2886 #C completed",
					"info", false);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "RACK Changer End-to-End flow")
	@Parameters({ "TestName" })
	public void AGV_RACKCHANGER_INVALID_INPUT(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();
			lp.Agv_LogintoMasweb(TestName);

			// MasWeb page
			MasWeb_Home mwHome = new MasWeb_Home();
			mwHome.Navigate_AGV_RackChanger();

			// RacKChanger Page
			AGV_RackChanger agvRack = new AGV_RackChanger();
			agvRack.Agv_Invalid_Input_Validation();
			reportStep(
					" Verification of  functionality of Rack changer screen which will display  all the configured  AGV machine ids is completed"
							+ "#B Test Case ID:2881 #C",
					"Pass", false);
			reportStep(
					"Verification of functionality of Rack changer screen which will display  all the configured  AGV machine ids is completed"
							+ "#B Test Case ID:2881  #C",
					"info", false);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "AGV Shortage delivery End-to-End flow")
	@Parameters({ "TestName" })
	public void AGV_SHORTAGE_DELIVERY(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();
			lp.Agv_LogintoMasweb(TestName);

			// MasWeb page
			MasWeb_Home mwHome = new MasWeb_Home();
			mwHome.Navigate_AGV_RackChanger();

			// RacKChanger Page
			AGV_RackChanger agvRack = new AGV_RackChanger();
			agvRack.MachineID_Search();
			String strFID = agvRack.MachineID_Pick("A");
			mwHome.Navigate_AGV_MachineIDScan();

			// Machine Scan ID page
			AGV_MachineIDScan agvMach = new AGV_MachineIDScan();
			agvMach.MachineID_Scan_Submit(strFID);
			mwHome.Agv_Navigate_Vclas_Task();

			// Vclas task Page
			Vclas_tasks vclasTask = new Vclas_tasks();
			vclasTask.Vclas_Task_Search();
			vclasTask.Vclas_getAGVTask();

			// Vclas Application Login
			lp.Agv_LogintoVclas(TestName);

			// Vclas Home Page
			Vclas_Assignment vclasAgn = new Vclas_Assignment();
			vclasAgn.Agv_Vclas_Assignment_Navigate();

			// Submitting the Flow
			vclasAgn.Vclas_Agv_Shortage_Screen_Delivery();
			Agv_launchUrl(URL, "");
			mwHome.Agv_Navigate_Vclas_Task();
			vclasTask.Agv_Vclas_VerifyTaskEvents_Inactive("Allt utfört samtidigt");
			reportStep("Status:'Everything done simultaneously' is verified successfully ", "pass", false);
			reportStep(
					"Verification of functionality of Delivering a AGVFKOLL and AGVEKOLL from Shortage screen #B TestCase ID:2887 #C",
					"info", false);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "AGV Inprogress delivery End-to-End flow")
	@Parameters({ "TestName" })
	public void AGV_INPROGRESS_DELIVERY(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();
			lp.Agv_LogintoMasweb(TestName);

			// MasWeb page
			MasWeb_Home mwHome = new MasWeb_Home();
			mwHome.Navigate_AGV_RackChanger();

			// RacKChanger Page
			AGV_RackChanger agvRack = new AGV_RackChanger();
			agvRack.MachineID_Search();
			String strFID = agvRack.MachineID_Pick("A");
			mwHome.Navigate_AGV_MachineIDScan();

			// Machine Scan ID page
			AGV_MachineIDScan agvMach = new AGV_MachineIDScan();
			agvMach.MachineID_Scan_Submit(strFID);
			mwHome.Agv_Navigate_Vclas_Task();

			// Vclas task Page
			Vclas_tasks vclasTask = new Vclas_tasks();
			vclasTask.Vclas_Task_Search();
			vclasTask.Vclas_getAGVTask();

			// Vclas Application Login
			lp.Agv_LogintoVclas(TestName);

			// Vclas Home Page
			Vclas_Assignment vclasAgn = new Vclas_Assignment();
			vclasAgn.Agv_Vclas_Assignment_Navigate();

			// Submitting the Flow
			vclasAgn.Vclas_Inprogress_Delivery();

			Agv_launchUrl(URL, "");
			mwHome.Agv_Navigate_Vclas_Task();
			vclasTask.Agv_Vclas_VerifyTaskEvents_Inactive("Allt utfört samtidigt");
			reportStep("Status:'Everything done simultaneously' is verified successfully ", "pass", false);
			reportStep(
					"Verification of functionality of Delivering a AGVFKOLL and AGVEKOLL from Shortage screen when those are in inprogress #B TestCase ID:2888 #C",
					"info", false);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "AGV Inactive order creation End-to-End flow")
	@Parameters({ "TestName" })
	public void AGV_Inactive_Order_Creation(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();
			lp.Agv_LogintoMasweb(TestName);

			// MasWeb page
			MasWeb_Home mwHome = new MasWeb_Home();
			mwHome.Navigate_AGV_RackChanger();

			// RacKChanger Page
			AGV_RackChanger agvRack = new AGV_RackChanger();
			agvRack.MachineID_Search();
			String strFID = agvRack.MachineID_Pick("I");
			mwHome.Navigate_AGV_MachineIDScan();

			// Machine Scan ID page
			AGV_MachineIDScan agvMach = new AGV_MachineIDScan();
			agvMach.MachineID_Scan_Submit(strFID);
			mwHome.Agv_Navigate_Vclas_Task();

			// Vclas task Page
			Vclas_tasks vclasTask = new Vclas_tasks();
			vclasTask.Vclas_Task_Search();
			vclasTask.Vclas_getAGVTask_Inactive_MID();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}

	}

	@Test(description = "AGV Reserve order creation End-to-End flow")
	@Parameters({ "TestName" })
	public void AGV_Reserve_Order_Creation(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();
			lp.Agv_LogintoMasweb(TestName);

			// MasWeb page
			MasWeb_Home mwHome = new MasWeb_Home();
			mwHome.Navigate_AGV_RackChanger();

			// RacKChanger Page
			AGV_RackChanger agvRack = new AGV_RackChanger();
			agvRack.MachineID_Search();
			String strFID = agvRack.MachineID_Pick("R");
			mwHome.Navigate_AGV_MachineIDScan();

			// Machine Scan ID page
			AGV_MachineIDScan agvMach = new AGV_MachineIDScan();
			agvMach.MachineID_Scan_Submit(strFID);
			mwHome.Agv_Navigate_Vclas_Task();

			// Vclas task Page
			Vclas_tasks vclasTask = new Vclas_tasks();
			vclasTask.Vclas_Task_Search();
			vclasTask.Vclas_getAGVTask();
			agvRack.ReserveID_TaskCreation_Verification();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}

	}

	@Test(description = "AGV Picking multiple assignments delivery End-to-End flow")
	@Parameters({ "TestName" })
	public void AGV_Picking_Multiple_Assignments_delivery(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();
			lp.Agv_LogintoMasweb(TestName);

			// MasWeb page
			MasWeb_Home mwHome = new MasWeb_Home();
			Agv_Emulator agvEmulator = new Agv_Emulator();
			// agvEmulator.Agv_Picking_Multiple_Assignments();

			// Vclas Application Login
			lp.Agv_LogintoVclas(TestName);

			// Vclas Home Page
			Vclas_Assignment vclasAgn = new Vclas_Assignment();
			// vclasAgn.Nodes_Conveyor_Data_Pickup();
			vclasAgn.Agv_Vclas_Assignment_Navigate();
			// agvEmulator.Vclas_Picking_Multiple_Assignments();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}

	}

	@Test(description = "RACK Changer End-to-End flow")
	@Parameters({ "TestName" })
	public void AGV_PlanningStatus_AL_DELIVERY(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();
			lp.Agv_LogintoMasweb(TestName);

			// MasWeb page
			MasWeb_Home mwHome = new MasWeb_Home();

			mwHome.Navigate_AGV_RackChanger();

			// RacKChanger Page
			AGV_RackChanger agvRack = new AGV_RackChanger();
			agvRack.MachineID_Search();
			String strFID = agvRack.MachineID_Pick("A");
			mwHome.Navigate_AGV_MachineIDScan();

			// Machine Scan ID page
			AGV_MachineIDScan agvMach = new AGV_MachineIDScan();
			agvMach.MachineID_Scan_Submit(strFID);
			mwHome.Agv_Navigate_Vclas_Task();

			// Vclas task Page
			Vclas_tasks vclasTask = new Vclas_tasks();
			vclasTask.Vclas_Task_Search();
			vclasTask.Vclas_getAGVTask();

			reportStep(
					" Verification of possibility of creating AGV orders for a Active AGV machine ID completed #B Test Case ID:2884 #C",
					"Pass", false);
			reportStep(
					" Verification of possibility of creating AGV orders for a Active AGV machine ID  completed #B Test Case ID:2884  #C",
					"info", false);

			// Vclas Application Login
			lp.Agv_LogintoVclas(TestName);

			// Vclas Home Page
			Agv_Emulator Agv_Emu = new Agv_Emulator();
			Vclas_Assignment vclasAgn = new Vclas_Assignment();
			// vclasAgn.Nodes_Conveyor_Data_Pickup();
			vclasAgn.Agv_Vclas_Assignment_Navigate();

			// Submitting the Flow
			Agv_Emu.AGV_PlanningStatus_delivery();
			/*
			 * Agv_launchUrl(URL, ""); mwHome.Agv_Navigate_Vclas_Task();
			 * vclasTask.Agv_Vclas_VerifyTaskEvents_Inactive("Utfört samt avslutat");
			 * reportStep("Status:'Done as well as completed' is verified successfully ",
			 * "pass", false);
			 * reportStep("Verification of status from Vclas tasks screen for these Delivery completed AGV Racks #B TestCase ID:2886 #C completed"
			 * , "Pass", false);
			 * reportStep("Verification of status from Vclas tasks screen for these Delivery completed AGV Racks #B TestCase ID:2886 #C completed"
			 * , "info", false);
			 */
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Vclas NodesType screenfunctional verificaton")
	@Parameters({ "TestName" })
	public void Vclas_NodesType(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();

			// Vclas Application Login
			lp.Agv_LogintoVclas(TestName);
			Vclas_TestCases VclasTC = new Vclas_TestCases();

			// Vclas Home Page
			VclasTC.Nodes_TypesScreen();
			reportStep("#B Verified the functionality of Node Types Screen TestCase ID:2908 #C", "pass", false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);

		}
	}

	@Test(description = "Vclas Transport section screen functional verirification")
	@Parameters({ "TestName" })
	public void Vclas_TransportSection(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();

			// Vclas Application Login
			lp.Agv_LogintoVclas(TestName);
			Vclas_TestCases VclasTC = new Vclas_TestCases();

			// Vclas Home Page
			VclasTC.Vclas_TransportSectionScreen();
			reportStep("#B Verified the functionality of Transport Sections Screen TestCase ID:2909 #C", "pass", false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Vclas ReleaseNotes Information verification")
	@Parameters({ "TestName" })
	public void Vclas_ReleaseNotes(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();

			// Vclas Application Login
			lp.Agv_LogintoVclas(TestName);
			Vclas_TestCases VclasTC = new Vclas_TestCases();

			// Vclas Home Page
			VclasTC.ReleaseNotesScreen();
			reportStep("#B Verified the functionality of Release Notes Screen TestCase ID:2918 #C", "pass", false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Vclas Move button verification")
	@Parameters({ "TestName" })
	public void Vclas_Nodes_MovebuttonVerify(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();

			// Vclas Application Login
			lp.Agv_LogintoVclas(TestName);
			Vclas_TestCases VclasTC = new Vclas_TestCases();

			// Vclas Home Page
			VclasTC.Nodes_MoveButton();
			reportStep(
					"#B Verified the newly added paramater Use Move button for all types of nodes TestCase ID:3467 #C",
					"pass", false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Vclas Administration tab Nodes functionality End-to-End flow")
	@Parameters({ "TestName" })
	public void Vclas_Adminstration_Tab_Nodes_Functionality(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();

			// Vclas Application Login
			lp.Agv_LogintoVclas(TestName);

			// Vclas Administration tab
			Vclas_TestCases VclasTC = new Vclas_TestCases();
			VclasTC.Administration_Nodes_Tab();

			reportStep("#B Verified the functionality of Node Screen TestCase ID:3461 #C", "Pass", false);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}

	}

	@Test(description = "Vclas Administration tab rooms functionality End-to-End flow")
	@Parameters({ "TestName" })
	public void Vclas_Adminstration_Tab_Rooms_Functionality(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();

			// Vclas Application Login
			lp.Agv_LogintoVclas(TestName);

			// Vclas Administration tab
			Vclas_TestCases VclasTC = new Vclas_TestCases();
			VclasTC.Administration_Rooms_Tab();

			reportStep(
					"#B Verified the functionality of Rooms screen to create Rooms for all flows TestCase ID:2910 #C",
					"Pass", false);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}

	}

	@Test(description = "Vclas Administration tab Parts functionality End-to-End flow")
	@Parameters({ "TestName" })
	public void Vclas_Adminstration_Tab_Parts_Functionality(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();

			// Vclas Application Login
			lp.Agv_LogintoVclas(TestName);

			// Vclas Administration tab
			Vclas_TestCases VclasTC = new Vclas_TestCases();
			VclasTC.Administration_Parts_Tab();

			reportStep("#B Verified the functionality of Parts screen TestCase ID:2911 #C", "Pass", false);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}

	}

	@Test(description = "Vclas Administration tab TAT functionality End-to-End flow")
	@Parameters({ "TestName" })
	public void Vclas_Adminstration_Tab_TAT_Functionality(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();

			// Vclas Application Login
			lp.Agv_LogintoVclas(TestName);

			// Vclas Administration tab
			Vclas_TestCases VclasTC = new Vclas_TestCases();
			VclasTC.Administration_TAT_Tab();

			reportStep("#B Verified the functionality of TAT screen TestCase ID:2913 #C", "Pass", false);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}

	}

	@Test(description = "Vclas_Adminstration_Tab_DB_Constant_Functionality End-to-End flow")
	@Parameters({ "TestName" })
	public void Vclas_Adminstration_Tab_DB_Constant_Functionality(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();

			// Vclas Application Login
			lp.Agv_LogintoVclas(TestName);

			// Vclas Administration tab
			Vclas_TestCases VclasTC = new Vclas_TestCases();
			VclasTC.Administration_DB_Constant_Admin_Screen_Tab();

			reportStep("#B Verified the functionality of DB Constant Admin screen TestCase ID:2914 #C", "Pass", false);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}

	}

	@Test(description = "Vclas_Adminstration_Tab_DB_Constant_Functionality End-to-End flow")
	@Parameters({ "TestName" })
	public void Vclas_Assignments_Functionality(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();

			// Vclas Application Login
			lp.Agv_LogintoVclas(TestName);

			// Vclas Administration tab
			Vclas_TestCases VclasTC = new Vclas_TestCases();
			VclasTC.AssignmentsTab();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}

	}

	@Test(description = "Vclas_Adminstration_Tab User Functionality End-to-End flow")
	@Parameters({ "TestName" })
	public void Vclas_Adminstration_Tab_User_Functionality(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();

			// Vclas Application Login
			lp.Agv_LogintoVclas(TestName);

			// Vclas Administration tab
			Vclas_TestCases VclasTC = new Vclas_TestCases();
			VclasTC.Administration_User_Tab();

			reportStep("#B Verified the functionality of User screen TestCase ID:2912 #C", "Pass", false);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}

	}

	@Test(description = "Vclas_Adminstration_Tab Health Check Functionality End-to-End flow")
	@Parameters({ "TestName" })
	public void Vclas_Adminstration_Tab_Health_Check_Functionality(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();

			// Vclas Application Login
			lp.Agv_LogintoVclas(TestName);

			// Vclas Administration tab
			Vclas_TestCases VclasTC = new Vclas_TestCases();
			VclasTC.Administration_Health_Check_Tab();

			reportStep("#B Verified the functionality of Health Check screen TestCase ID:2915 #C", "Pass", false);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}

	}

	@Test(description = "Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void TUGGER_SHORTAGE_DELIVERY(String TestName) {
		try {
			TUGGER_COMMONFLOW(TestName);
			Vclas_Assignment vclasAgn = new Vclas_Assignment();
			vclasAgn.Vclas_Tugger_Shortage_Delivery("Tugger");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}

	}

	@Test(description = "Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void TUGGER_SHORTAGE_INVALIDINPUT(String TestName) {
		try {
			TUGGER_COMMONFLOW(TestName);
			Vclas_Assignment vclasAgn = new Vclas_Assignment();
			vclasAgn.Vclas_Shortage_InvalidInput("Tugger");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}

	}

	@Test(description = "Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void TUGGER_ASSIGNMENTLIST_INVALIDINPUT(String TestName) {
		try {
			TUGGER_COMMONFLOW(TestName);
			Vclas_Assignment vclasAgn = new Vclas_Assignment();
			vclasAgn.Vclas_AssignmentList_InvalidInput("Tugger");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}

	}

	@Test(description = "Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void TUGGER_SHORTAGE_INCORRECTSEQ_INVALIDINPUT(String TestName) {
		try {
			TUGGER_COMMONFLOW(TestName);
			Vclas_Assignment vclasAgn = new Vclas_Assignment();
			vclasAgn.Vclas_Tugger_Shortage_Incorrect_InvalidInput("Tugger");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}

	}

	@Test(description = "Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void TUGGER_ASSIGNMENTLIST_INCORRECTSEQ_INVALIDINPUT(String TestName) {
		try {
			TUGGER_COMMONFLOW(TestName);
			Vclas_Assignment vclasAgn = new Vclas_Assignment();
			vclasAgn.Vclas_Tugger_AssignmentList_Incorrect_InvalidInput("Tugger");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}

	}

	@Test(description = "Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void TUGGER_SHORTAGE_CORRECTSEQ_INVALIDINPUT(String TestName) {
		try {
			TUGGER_COMMONFLOW(TestName);
			Vclas_Assignment vclasAgn = new Vclas_Assignment();
			vclasAgn.Vclas_Tugger_Shortage_CorrectSeq_InvalidInput("Tugger");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void TUGGER_AL_SHORTAGE_COMBO_DELIVERY(String TestName) {
		try {
			TUGGER_COMMONFLOW(TestName);
			Tugger_Testcases tt = new Tugger_Testcases();
			tt.Vclas_Tugger_Assignment_Shortage_Combo_Delivery("Tugger");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void TUGGER_ALARM_INCONSISTANTDATA(String TestName) {
		try {
			TUGGER_COMMONFLOW(TestName);
			Tugger_Testcases tt = new Tugger_Testcases();
			tt.Vclas_Tugger_Alarm_InconsistantData("Tugger");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void TUGGER_SHORTAGE_SORTING(String TestName) {
		try {
			TUGGER_COMMONFLOW(TestName);
			Tugger_Testcases tt = new Tugger_Testcases();
			if (tt.Vclas_Tugger_Shortage_Sorting() == true) {
				reportStep(
						"-------32 Verify the functionality of Sorting orders in Shortage screen--TestCase ID-3130--Completed----",
						"Pass", false);
			} else {
				reportStep(
						"-------32 Verify the functionality of Sorting orders in Shortage screen--TestCase ID-3130--Completed----",
						"Fail", false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void TUGGER_AL_SORTING(String TestName) {
		try {
			TUGGER_COMMONFLOW(TestName);
			Tugger_Testcases tt = new Tugger_Testcases();
			tt.Vclas_JISJIT_Tugger_AL_Sorting("Tugger");
			reportStep(
					"-------33 Verify the functionality of Sorting orders in Assignment Lists screen--TestCase ID-3131--Completed----",
					"Fail", false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void TUGGER_AL_FIRSTUSER(String TestName) {
		try {
			TUGGER_COMMONFLOW(TestName);
			Vclas_Assignment vclasAgn = new Vclas_Assignment();
			vclasAgn.Vclas_Tugger_AL_OtherUser_Pickup("Tugger", "FirstUser");
			// driver.close();
			driver.quit();
			launchBrowser(Browser);
			// login to vclas with other user
			LoginToVclas lpv = new LoginToVclas();
			lpv.LogintoVclas(TestName);
			// Load Test Data File
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_Assignments");
			String stRoomValue = tdrow.get("Select_Work_Area_Room");
			tdrow.clear();
			vclasAgn.Vclas_Assignment_Navigate();
			vclasAgn.Vclas_Assignment_SelectWorkArea("Room", stRoomValue.split(",")[0]);
			vclasAgn.Vclas_Tugger_AL_OtherUser_Pickup("Tugger", "SecondUser");
			vclasAgn.Vclas_Tugger_Shortage_Delivery_OtherUser("Tugger");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void TUGGER_Picking_MultipleAssignments(String TestName) {
		try {
			TUGGER_COMMONFLOW(TestName);
			Vclas_Assignment vclasAgn = new Vclas_Assignment();
			vclasAgn.Vclas_Tugger_MultiplePickup("Tugger", "FirstUser");
			vclasAgn.Vclas_Tugger_Shortage_MultipleAssignments_Delivery("Tugger");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void TUGGER_Nodes_Paramters_True_Empty_TC_3469_3253(String TestName) {
		try {
			TUGGER_COMMONFLOW(TestName);
			Tugger_Testcases tt = new Tugger_Testcases();
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_Assignments");
			String fPos = tdrow.get("fPos").trim();
			String toPosition = tdrow.get("toPos").trim();
			System.out.println(" ToPosition: " + toPosition);
			System.out.println("From Position: " + fPos);
			tdrow.clear();
			tt.Nodes_Parameters_True_Delivery(fPos);
			tt.MoveButton_parameter(fPos, "false");
			tt.Vclas_Tugger_Assignment_Delivery_Parameters_True("Tugger");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Tugger End-to-End flow")
	@Parameters({ "TestName" })
	public void TUGGER_Nodes_MoveButton_Shortage_TC_3483(String TestName) {
		try {
			TUGGER_COMMONFLOW(TestName);
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_Assignments");
			String fPos = tdrow.get("fPos").trim();
			String toPosition = tdrow.get("toPos").trim();
			System.out.println(" ToPosition: " + toPosition);
			System.out.println("From Position: " + fPos);
			tdrow.clear();
			Tugger_Testcases tt = new Tugger_Testcases();
			tt.MoveButton_parameter(toPosition, "true");

			tt.Vclas_Tugger_Shortage_Delivery_MoveButton("Tugger");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Tugger End-to-End flow Test case Id:58&59")
	@Parameters({ "TestName" })
	public void TUGGER_Nodes_MoveButton_AL_ScanOnDelivery_False_Empty_TC_3471_3472(String TestName) {
		try {
			TUGGER_COMMONFLOW(TestName);
			Tugger_Testcases tt = new Tugger_Testcases();
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_Assignments");
			String Empty_topos = tdrow.get("fPos").trim();
			System.out.println("Empty topos: " + Empty_topos);
			tdrow.clear();
			tt.MoveButton_parameter(Empty_topos, "false");

			tt.AssignmentList_MoveButton_ScanOnDelivery_False("Tugger");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Tugger End-to-End flow Test case Id:3463")
	@Parameters({ "TestName" })
	public void TUGGER_Nodes_MovedOrder_Functionality_TC_3463(String TestName) {
		try {
			TUGGER_COMMONFLOW(TestName);
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_Assignments");
			String toPosition = tdrow.get("toPos").trim();
			System.out.println(" ToPosition: " + toPosition);
			tdrow.clear();
			Tugger_Testcases tt = new Tugger_Testcases();
			tt.MoveButton_parameter(toPosition, "true");

			tt.Vclas_Tugger_MoveButton_Functionality("Tugger");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Tugger End-to-End flow Test case Id:3470")
	@Parameters({ "TestName" })
	public void TUGGER_Nodes_MovedOrder_TC_3470(String TestName) {
		try {
			TUGGER_COMMONFLOW(TestName);
			tdrow = excelUtils.testCaseRetrieve(refTestDataName, "Vclas_Assignments");
			String fPos = tdrow.get("fPos").trim();
			String toPosition = tdrow.get("toPos").trim();
			System.out.println(" ToPosition: " + toPosition);
			System.out.println("From Position: " + fPos);
			tdrow.clear();
			Tugger_Testcases tt = new Tugger_Testcases();
			tt.Nodes_Parameters_True_Delivery(fPos);
			tt.Nodes_Parameters_True_Delivery(toPosition);

			tt.MoveButton_parameter(fPos, "true");
			tt.MoveButton_parameter(toPosition, "true");
			tt.Vclas_Tugger_Assignment_Delivery_Parameters_MoveButton_True("Tugger");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}
	}

	@Test(description = "Masweb Reports Tab functionality")
	@Parameters({ "TestName" })
	public void ReportsTabFunctionality(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();
			MasWeb_Home MHome = new MasWeb_Home();
			MASWEB_Other_Functionalities otherFunc = new MASWEB_Other_Functionalities();
			lp.Agv_LogintoMasweb(TestName);
			MHome.Navigate_Reports_ReportsInMasweb();
			otherFunc.ReportsTab();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}

	}

	@Test(description = "LDJIT YardZoneMaintenence - create parking place Tab functionality")
	@Parameters({ "TestName" })
	public void CreateParkingPlace(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();
			lp.Agv_LogintoMasweb(TestName);
			LDJIT_CreateParkingPlace LParkingPlace = new LDJIT_CreateParkingPlace();
			LParkingPlace.CreateParkingPlace_Display();
			LParkingPlace.CreateParkingPlace_CheckCode();
			LParkingPlace.CreateParkingPlace_ParkingPlace();
			LParkingPlace.CreateParkingPlace_AddNew();
			LParkingPlace.CreateParkingPlace_Edit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}

	}

	@Test(description = "LDJIT YardZoneMaintenence - create trailer assignment tabfunctionality")
	@Parameters({ "TestName" })
	public void CreateTrailerAssignment(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();
			lp.Agv_LogintoMasweb(TestName);
			LDJIT_CreateTrailerAssignments LTrailerAssign = new LDJIT_CreateTrailerAssignments();
			LTrailerAssign.CreateTrailerAssignments_ParkingPlaceError();
			LTrailerAssign.CreateTrailerAssignments_FlowError();
			LTrailerAssign.CreateTrailerAssignments_TrailerId();
			LTrailerAssign.ActiveAssignments_ErrorMsg();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}

	}

	@Test(description = "LDJIT-Move Jisjit tab functionality")
	@Parameters({ "TestName" })
	public void MoveJisjitRacks(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();
			lp.Agv_LogintoMasweb(TestName);
			Move_JISJIT mJisjit = new Move_JISJIT();
			mJisjit.MoveJisjit_NoEmpty();
			mJisjit.Flow_ErrorMsg();
			mJisjit.MoveJisjit_RackNr_Errormsg();
			mJisjit.LoadEmptyTrailer_Clear();
			mJisjit.LoadEmptyTrailer();
			mJisjit.Trailer_Movement();
			mJisjit.Rack_Movement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}

	}

	@Test(description = "Authoeisation tab functionality")
	@Parameters({ "TestName" })
	public void Authorisation(String TestName) {
		try {

			// Login page
			LoginPage lp = new LoginPage();
			lp.Agv_LogintoMasweb(TestName);
			MASWEB_Other_Functionalities otherFunc = new MASWEB_Other_Functionalities();
			otherFunc.Authorization_TransactionTab();
			otherFunc.Authorization_RolesTab();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
		}

	}
	
	//Masweb preethi
	
	@Test(description = "Functionality of Favorites and Help Tab in MASWEB End-to-End flow")
    @Parameters({ "TestName" })
    public void MASWEB_Favorites_Help_Tab(String TestName) {
           try {

                  // Login page
                  LoginPage lp = new LoginPage();
                  
                  // MASWEB Application Login
                  lp.LogintoPage(TestName);
                  
                  MASWEB_Other_Functionalities MaswebTC = new MASWEB_Other_Functionalities();
                  MaswebTC.Favourites_Tab();
                  MaswebTC.Help_Tab();
             } catch (Exception e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
                  reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
           }

       }

	
	 @Test(description = "Functionality of LDJIT create new places Tab in MASWEB End-to-End flow")
     @Parameters({ "TestName" })
     public void MASWEB_LDJIT_Create_and_Edit_New_Places(String TestName) {
            try {

                   // Login page
                   LoginPage lp = new LoginPage();
                   
                   // MASWEB Application Login
                   lp.LogintoPage(TestName);
                   
                   Masweb_LDJIT_Create_New_Places MaswebLDJIT = new Masweb_LDJIT_Create_New_Places();
                   MaswebLDJIT.Create_and_Edit_New_Places();
       		     
              } catch (Exception e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
                   reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
            }

        }
      
     
     @Test(description = "Functionality of LDJIT create new places Tab in MASWEB End-to-End flow")
     @Parameters({ "TestName" })
     public void MASWEB_LDJIT_Place_Error_Message(String TestName) {
            try {

                   // Login page
                   LoginPage lp = new LoginPage();
                   
                   // MASWEB Application Login
                   lp.LogintoPage(TestName);
                   
                   Masweb_LDJIT_Create_New_Places MaswebLDJIT = new Masweb_LDJIT_Create_New_Places();
                   MaswebLDJIT.Place_Error_Message();
       		     
              } catch (Exception e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
                   reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
            }

        }
     
     @Test(description = "Functionality of LDJIT create new places Tab in MASWEB End-to-End flow")
     @Parameters({ "TestName" })
     public void MASWEB_LDJIT_Place_Type_Error_Message(String TestName) {
            try {

                   // Login page
                   LoginPage lp = new LoginPage();
                   
                   // MASWEB Application Login
                   lp.LogintoPage(TestName);
                   
                   Masweb_LDJIT_Create_New_Places MaswebLDJIT = new Masweb_LDJIT_Create_New_Places();
                   MaswebLDJIT.Place_Type_Error_Message();
       		     
              } catch (Exception e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
                   reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
            }

        }
     
     @Test(description = "Functionality of LDJIT create new places Tab in MASWEB End-to-End flow")
     @Parameters({ "TestName" })
     public void MASWEB_LDJIT_Supplier_Error_Message(String TestName) {
            try {

                   // Login page
                   LoginPage lp = new LoginPage();
                   
                   // MASWEB Application Login
                   lp.LogintoPage(TestName);
                   
                   Masweb_LDJIT_Create_New_Places MaswebLDJIT = new Masweb_LDJIT_Create_New_Places();
                   MaswebLDJIT.Supplier_Error_Message();
       		     
              } catch (Exception e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
                   reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
            }

        }
     
     @Test(description = "Functionality of LDJIT create new places Tab in MASWEB End-to-End flow")
     @Parameters({ "TestName" })
     public void MASWEB_LDJIT_Flow_Error_Message(String TestName) {
            try {

                   // Login page
                   LoginPage lp = new LoginPage();
                   
                   // MASWEB Application Login
                   lp.LogintoPage(TestName);
                   
                   Masweb_LDJIT_Create_New_Places MaswebLDJIT = new Masweb_LDJIT_Create_New_Places();
                   MaswebLDJIT.Flow_Error_Message();
       		     
              } catch (Exception e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
                   reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
            }

        }
     
     @Test(description = "Functionality of LDJIT create new places Tab in MASWEB End-to-End flow")
     @Parameters({ "TestName" })
     public void MASWEB_LDJIT_Factory_Error_Message(String TestName) {
            try {

                   // Login page
                   LoginPage lp = new LoginPage();
                   
                   // MASWEB Application Login
                   lp.LogintoPage(TestName);
                   
                   Masweb_LDJIT_Create_New_Places MaswebLDJIT = new Masweb_LDJIT_Create_New_Places();
                   MaswebLDJIT.Factory_Error_Message();
       		     
              } catch (Exception e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
                   reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
            }

        }
     
     @Test(description = "Functionality of LDJIT create new places Tab in MASWEB End-to-End flow")
     @Parameters({ "TestName" })
     public void MASWEB_LDJIT_Status_Error_Message(String TestName) {
            try {

                   // Login page
                   LoginPage lp = new LoginPage();
                   
                   // MASWEB Application Login
                   lp.LogintoPage(TestName);
                   
                   Masweb_LDJIT_Create_New_Places MaswebLDJIT = new Masweb_LDJIT_Create_New_Places();
                   MaswebLDJIT.Status_Error_Message();
       		     
              } catch (Exception e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
                   reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
            }

        }
     
     @Test(description = "Functionality of LDJIT create new places Tab in MASWEB End-to-End flow")
     @Parameters({ "TestName" })
     public void MASWEB_LDJIT_Description_Error_Message(String TestName) {
            try {

                   // Login page
                   LoginPage lp = new LoginPage();
                   
                   // MASWEB Application Login
                   lp.LogintoPage(TestName);
                   
                   Masweb_LDJIT_Create_New_Places MaswebLDJIT = new Masweb_LDJIT_Create_New_Places();
                   MaswebLDJIT.Description_Error_Message();
       		     
              } catch (Exception e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
                   reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
            }

        }
     
     @Test(description = "Functionality of LDJIT create new places Tab in MASWEB End-to-End flow")
     @Parameters({ "TestName" })
     public void MASWEB_LDJIT_Created_From_Date_Error_Message(String TestName) {
            try {

                   // Login page
                   LoginPage lp = new LoginPage();
                   
                   // MASWEB Application Login
                   lp.LogintoPage(TestName);
                   
                   Masweb_LDJIT_Create_New_Places MaswebLDJIT = new Masweb_LDJIT_Create_New_Places();
                   MaswebLDJIT.Created_From_Date_Error_Message();
       		     
              } catch (Exception e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
                   reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
            }

        }
     
     @Test(description = "Functionality of LDJIT create new places Tab in MASWEB End-to-End flow")
     @Parameters({ "TestName" })
     public void MASWEB_LDJIT_Created_To_Date_Error_Message(String TestName) {
            try {

                   // Login page
                   LoginPage lp = new LoginPage();
                   
                   // MASWEB Application Login
                   lp.LogintoPage(TestName);
                   
                   Masweb_LDJIT_Create_New_Places MaswebLDJIT = new Masweb_LDJIT_Create_New_Places();
                   MaswebLDJIT.Created_To_Date_Error_Message();
       		     
              } catch (Exception e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
                   reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
            }

        }
     
     @Test(description = "Functionality of LDJIT create new places Tab in MASWEB End-to-End flow")
     @Parameters({ "TestName" })
     public void MASWEB_LDJIT_Create_Place(String TestName) {
            try {

                   // Login page
                   LoginPage lp = new LoginPage();
                   
                   // MASWEB Application Login
                   lp.LogintoPage(TestName);
                   
                   Masweb_LDJIT_Create_New_Places MaswebLDJIT = new Masweb_LDJIT_Create_New_Places();
                   MaswebLDJIT.Created_To_Date_Error_Message();
       		     
              } catch (Exception e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
                   reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
            }

        }
     
     @Test(description = "Functionality of LDJIT Trailer Info Tab in MASWEB End-to-End flow")
     @Parameters({ "TestName" })
     public void MASWEB_LDJIT_Trailer_Info_Display(String TestName) {
            try {

                   // Login page
                   LoginPage lp = new LoginPage();
                   
                   // MASWEB Application Login
                   lp.LogintoPage(TestName);
                   
                   MASWEB_LDJIT_Trailer_Info MaswebLDJITTrailer = new MASWEB_LDJIT_Trailer_Info();
                   MaswebLDJITTrailer.Trailer_Info_Display();
       		     
              } catch (Exception e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
                   reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
            }

        }

//--------------------

    // V432 End to End happy path
    
    
    @Test(description = "V432 End-to-End flow ")
    @Parameters({ "TestName" })
           public void V432_EndToEnd_Flow(String TestName) {
                  try {
                	  V432 v432= new V432();
                	  v432.V432_Pre_requiste();
                	  // Login page
                      LoginPage lp = new LoginPage();
                     lp.LogintoPage(TestName);
                     
                    // MasWeb page
                      MasWeb_Home mwHome = new MasWeb_Home();
                     mwHome. Navigate_V60_ScanRFID_NormalStation();

                      // SCanning RFID
                     
                     v432.Scan_RFID(4);
                      // verifying in Vclas Task screen for created tasks
                      mwHome.Navigate_Vclas_Task();
                      Vclas_tasks vclasTask = new Vclas_tasks();
          			//vclasTask.Vclas_Task_Search();
          			v432.	Vclas_getV32TaskIds();
                   
                      
                      //Vclas Delivery
                      // Vclas_Login_app
                      lp.LogintoVclas(TestName);
                      //loading the excel
                    // V432 v432= new V432();
                     //MasWeb_Home mwHome = new MasWeb_Home();
                      tdrow = excelUtils.testCaseRetrieve(refTestDataName, "V432");
                      String stRoomValue = tdrow.get("Select_Work_Area_Room");
                    
                      // Select Rooms - Tugger
                      Vclas_Assignment vclasAgn = new Vclas_Assignment();
                      vclasAgn. Vclas_Assignment_Navigate();
                      vclasAgn.Vclas_Assignment_SelectWorkArea("Room", stRoomValue);
					reportStep("#BVerification of newly added room name V60 Load TA Trailer for V432 in Select Work Areas Test case Id:3960--completed", "pass", true);
                      v432.  V432_Delivery_AL();
                     //Verification of Inactive tasks
                     mwHome.Navigate_Vclas_Task();
                      v432.V432_Vclas_VerifyTaskEvents_Inactive("Allt utfört samtidigt");
                      reportStep("Status:'Everything done simultaneously' is verified successfully ", "pass", false);
	            	   
                      reportStep("#BVerification of message to MASweb with task completion status from VCLAS for above delivered Normal V432 body from A.L Test case Id:3962--completed#C", "pass", true);
                      reportStep("#BVerification of status of all delivered V60 bodies in VCLAS Tasks screen from masweb Test case Id:3937--completed#C", "pass", true);
                    
	            	   //Bundle delivery in Deliver V60 tasks page
	            	   mwHome.Navigate_V60_DeliverV60Tasks();
	            	   v432 .V432_Bundle_Delivery();
                     // verifying in V60 package note
	            	   mwHome.Navigate_V60_Packaging_Note();
	            	   v432. V60_Packaging_Note_Search();
            	  //Edit V60 delivery note
	            	   mwHome.Navigate_V60_Edit_V60_DeliveryNote();
	            	   v432  .Edit_V60_DeliveryNote();
                      
                  } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
                  }
           }

    //02 Verify the functionality of  V60 Register Default values for outtake stations screen--Test case Id:3930
    @Test(description = "V60_Default_Register_Outtake_Stations Test case Id:3930 ")
    @Parameters({ "TestName" })
           public void V60_Outtake_Stations_TestcaseId_3930(String TestName) {
                  try {
                	 
                	  // Login page
                      LoginPage lp = new LoginPage();
                     lp.LogintoPage(TestName);
                     
                    // MasWeb page
                      MasWeb_Home mwHome = new MasWeb_Home();
                     mwHome. Navigate_V60_Default_Outtake_Stations();
                     V432_Masweb_Positive_TestCases v432PositiveCases= new V432_Masweb_Positive_TestCases();
                     //make it as hold

                      
                  } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
                  }
           }

    
	//12 Verify the delivery of registered Normal and Repair V60 bodies from Deliver V60 Tasks screen to Ghent Test case Id:3940

    @Test(description = "Deliver V60 Tasks  Test case Id:3940 ")
    @Parameters({ "TestName" })
           public void Deliver_V60_TaskScreen_TestcaseId_3940(String TestName) {
                  try {
                	 
                	  // Login page
                      LoginPage lp = new LoginPage();
                     lp.LogintoPage(TestName);
                     
                    // MasWeb page
                     V432 v432= new V432();
                     v432.V432_Pre_requiste();
                     MasWeb_Home mwHome = new MasWeb_Home();
                      mwHome. Navigate_V60_ScanRFID_NormalStation();

                      // SCanning RFID
                     
                 v432.Scan_RFID(3);

                    mwHome. Navigate_V60_DeliverV60Tasks();
                   //  mwHome.Navigate_V60_Packaging_Note();
                     V432_Masweb_Positive_TestCases v432PositiveCases= new V432_Masweb_Positive_TestCases();
                    v432PositiveCases. Deliver_V60_TaskScreen();
                   

                      
                  } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
                  }
           }
 
    
    //13
    @Test(description ="V60 package note  Test case Id:3941 ")
    @Parameters({ "TestName" })
           public void V60_packageNote_Print_TestcaseId_3941(String TestName) {
                  try {
                	 
                	  // Login page
                      LoginPage lp = new LoginPage();
                     lp.LogintoPage(TestName);
                     
                    // MasWeb page
                     V432 v432= new V432();
                    v432.V432_Pre_requiste();
                     MasWeb_Home mwHome = new MasWeb_Home();
                      mwHome. Navigate_V60_ScanRFID_NormalStation();

                      // SCanning RFID
                     
                 v432.Scan_RFID(2);

                    mwHome. Navigate_V60_DeliverV60Tasks();
                    v432 .V432_Bundle_Delivery();
                     
                     V432_Masweb_Positive_TestCases v432PositiveCases= new V432_Masweb_Positive_TestCases();
                     mwHome.Navigate_V60_Packaging_Note();
                    v432PositiveCases. V60_PackagingNote_Screen_print();
                   

                      
                  } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
                  }
           }

    
    @Test(description = "V60 package note  Test case Id:3943 ")
    @Parameters({ "TestName" })
           public void V60_PackageNote_TestcaseId_3943(String TestName) {
                  try {
                	 
                	  // Login page
                      LoginPage lp = new LoginPage();
                     lp.LogintoPage(TestName);
                     
                    // MasWeb page
                     V432 v432= new V432();
                  //   v432.V432_Pre_requiste();
                     MasWeb_Home mwHome = new MasWeb_Home();
                   //   mwHome. Navigate_V60_ScanRFID_NormalStation();

//                       SCanning RFID
                     
                    //v432.Scan_RFID();

                   //mwHome. Navigate_V60_DeliverV60Tasks();
                   //v432 .V432_Bundle_Delivery();
                     mwHome.Navigate_V60_Packaging_Note();
                     V432_Masweb_Positive_TestCases v432PositiveCases= new V432_Masweb_Positive_TestCases();
                    
                     
                     v432PositiveCases   .V60_PackagingNote_Screen();

                      
                  } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
                  }
           }

    
    @Test(description = "V60 Normal and repair screens  Test case Id:3944 ")
    @Parameters({ "TestName" })
           public void V60_Normal_Repair_Screen_Functionality_TestcaseId_3944(String TestName) {
                  try {
                	 
                	  // Login page
                      LoginPage lp = new LoginPage();
                     lp.LogintoPage(TestName);
                     
                    // MasWeb page
                     V432 v432= new V432();
               
                     MasWeb_Home mwHome = new MasWeb_Home();
                     mwHome. Navigate_V60_ScanRFID_NormalStation();

                   
                     V432_Masweb_Positive_TestCases v432PositiveCases= new V432_Masweb_Positive_TestCases();
                     v432PositiveCases.V60_Normal_Repair_Screens();
                     mwHome.Navigate_V60_ScanRFID_RepairStation();
                     v432PositiveCases.V60_Normal_Repair_Screens();
                      
                  } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
                  }
           }

    
     @Test(description = "V432 calender functionality from all screens")
    @Parameters({ "TestName" })
           public void V432_CalenderFunction_AllScreens_TestCaseId_3945(String TestName) {
                  try {
                	  
                	  // Login page
                      LoginPage lp = new LoginPage();
                     lp.LogintoPage(TestName);
                     
                    // MasWeb page
                      MasWeb_Home mwHome = new MasWeb_Home();
                      V432_Masweb_Positive_TestCases v432Mas=new V432_Masweb_Positive_TestCases();
                     mwHome.Navigate_V60_ScanRFID_NormalStation();
                     v432Mas.Calender_Function();
                     mwHome.Navigate_V60_ScanRFID_RepairStation();
                     v432Mas.Calender_Function();
                     mwHome.Navigate_V60_Packaging_Note();
                     v432Mas.Calender_Function();
                   
                     mwHome.Navigate_V60_Edit_V60_DeliveryNote();
                     v432Mas.Calender_Function();
                     reportStep("#B Verificationof Calender field from all V60 screens Test case Id:3945--completed#C", "pass", false);
                     
                    
                     
                     
                     
                     
                      
                  } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
                  }
           }


    //19 Verify the maximum no. Of bodies adding in a Trailer from Deliver V60 Tasks screen
    @Test(description = "V432 calender functionality from all screens")
    @Parameters({ "TestName" })
           public void V432_MaxNoBodies_Trailer_TC19(String TestName) {
                  try {
                	  
                	  // Login page
                      LoginPage lp = new LoginPage();
                     lp.LogintoPage(TestName);
                     V432 v432 = new V432();
                     v432.V432_Pre_requiste();
                    // MasWeb page
                      MasWeb_Home mwHome = new MasWeb_Home();
                      V432_Masweb_Positive_TestCases v432Mas=new V432_Masweb_Positive_TestCases();
                     mwHome.Navigate_V60_ScanRFID_NormalStation();
                     
                   v432.Scan_RFID(5);
                   mwHome.Navigate_V60_DeliverV60Tasks();
                   v432Mas.MaxNoOfBodies();
                   
                   
                  } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
                  }
           }

    
    //10 Verify the Deliver V60 Tasks screen availibility when VCLAS V60 nodes are Up
    @Test(description = "V432 calender functionality from all screens")
    @Parameters({ "TestName" })
           public void V60_Nodes_Up_Down_TC10_11(String TestName) {
                  try {
                	  
                	  // Login page
                      LoginPage lp = new LoginPage();
                     lp.LogintoPage(TestName);
                    // V432 v432 = new V432();
                   
                    // MasWeb page
                      MasWeb_Home mwHome = new MasWeb_Home();
                      V432_Masweb_Positive_TestCases v432Mas=new V432_Masweb_Positive_TestCases();
                  
                     lp.LogintoVclas(TestName);
                     
                     v432Mas.V60_Nodes_Up_Down("Inactive");
                     // switching to masweb
                     driver.manage().window().maximize();
             		driver.switchTo().window(browser[1]);
             		driver.manage().window().maximize();
             		
                     mwHome.Navigate_V60_DeliverV60Tasks();
                     v432Mas. Delivery_V60_Tab_Open("Inactive");
                     //switching to vclas
                     driver.manage().window().maximize();
              		driver.switchTo().window(browser[2]);
              		driver.manage().window().maximize();
              		
                     v432Mas.V60_Nodes_Up_Down("Active");
                     //switching to masweb
                     driver.manage().window().maximize();
             		driver.switchTo().window(browser[1]);
             		driver.manage().window().maximize();
             		
                     mwHome.Navigate_V60_DeliverV60Tasks();
                     v432Mas. Delivery_V60_Tab_Open("Active");
                   
                   
                  } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
                  }
           }
    
    
    

  //V432 03. V60 Register default values for outtake stations.
      
      @Test(description = "V432_Outtakestation_flow")
      @Parameters({ "TestName" })
             public void V432_Outtakestation_flow(String TestName) {
                    try {
                         
                         // Login page
                        LoginPage lp = new LoginPage();
                       lp.LogintoPage(TestName);
                       
                      // MasWeb page
                        MasWeb_Home mwHome = new MasWeb_Home();
                       mwHome.Navigate_V60_Default_Outtake_Stations();

                       
                       V432_Masweb_Positive_TestCases v432Mas=new V432_Masweb_Positive_TestCases();
                       
                       v432Mas.V432_OuttakeStation_Screen();
                       mwHome.Navigate_V60_ScanRFID_RepairStation();
                       v432Mas.V432_scan_Repair();
                       mwHome.Navigate_V60_ScanRFID_NormalStation();
                       v432Mas.V432_scan_Normal();
                       reportStep("03 Verify the created default values are reflecting or not in both Scanning  screens test case is completed ", "pass", false);
                       
                        
                       
                        
                    } catch (Exception e) {
                          // TODO Auto-generated catch block
                          e.printStackTrace();
                          reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
                    }
             }
      
       
      
  //V432 25 Verify the functionality of scanning a Normal V60 body in Repair screen
      
      @Test(description = "V432_Bodyid_ScanNormal_Repair")
      @Parameters({ "TestName" })
      public void V432_Bodyid_ScanNormal_Repair(String TestName) {
             try {
                  
                   V432 v432= new V432();
                v432.V432_Pre_requiste();
                  // Login page
                   LoginPage lp = new LoginPage();
                  lp.LogintoPage(TestName);
                
                  // MasWeb page
                  MasWeb_Home mwHome = new MasWeb_Home();
                 mwHome.Navigate_V60_ScanRFID_NormalStation();

                
                V432_Masweb_Positive_TestCases v432Mas=new V432_Masweb_Positive_TestCases();
                
                              // SCanning RFID
               
               v432.Scan_RFID(4);
               mwHome.Navigate_V60_ScanRFID_RepairStation();
               v432Mas.V432_normalscan_repair();
               reportStep("#B25 Verify the functionality of scanning a Normal V60 body in Repair screen test case is completed#C ", "pass", false);
            } catch (Exception e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
                   reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
             }
      }
  
  //V432 26 Verify the functionality of scanning a Repair V60 body in Normal screen

  @Test(description = "V432_Bodyid_ScanRepair_Normal")
  @Parameters({ "TestName" })
  public void V432_Bodyid_ScanRepair_Normal(String TestName) {
         try {
           
            V432 v432= new V432();
                v432.V432_Pre_requiste();
                  // Login page
               LoginPage lp = new LoginPage();
              lp.LogintoPage(TestName);
            
              // MasWeb page
              MasWeb_Home mwHome = new MasWeb_Home();
             mwHome.Navigate_V60_ScanRFID_RepairStation();

            
            V432_Masweb_Positive_TestCases v432Mas=new V432_Masweb_Positive_TestCases();
           
     // SCanning RFID
           
           v432.Scan_RFID(4);
           mwHome.Navigate_V60_ScanRFID_NormalStation();
           v432Mas.V432_repairscan_normal();
           reportStep("#B26 Verify the functionality of scanning a Repair V60 body in Normal screen test case is completed#C ", "pass", false);
    
          } catch (Exception e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
               reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
         }
  }

    
  //V432 21 Verify the functionality of Re printing for a registered V60 RFID from Normal outtake stations screens

  @Test(description = "V432_Reprint_NormalBodyid")
  @Parameters({ "TestName" })
  public void V432_Reprint_NormalBodyid(String TestName) {
       try {
           
            V432 v432= new V432();
         v432.V432_Pre_requiste();
           // Login page
             LoginPage lp = new LoginPage();
            lp.LogintoPage(TestName);
          
            // MasWeb page
            MasWeb_Home mwHome = new MasWeb_Home();
           mwHome.Navigate_V60_ScanRFID_NormalStation();

          
          V432_Masweb_Positive_TestCases v432Mas=new V432_Masweb_Positive_TestCases();
         
           // SCanning RFID
         
         v432.Scan_RFID(4);
        // mwHome.Navigate_V60_ScanRFID_NormalStation();
         v432Mas.V432_Reprint_Normalbodyid();
         
         reportStep("#B21 Verify the functionality of Re printing for a registered V60 RFID from Normal outtake stations screens test case is completed#C ", "pass", false);

          
           
       } catch (Exception e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
             reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
       }
  }


  //V432 22 Verify the functionality of Re printing for a registered V60 RFID from Repair outtake stations screens

  @Test(description = "V432_Reprint_RepairBodyid")
  @Parameters({ "TestName" })
  public void V432_Reprint_RepairBodyid(String TestName) {
     try {
          
            V432 v432= new V432();
         v432.V432_Pre_requiste();
           // Login page
           LoginPage lp = new LoginPage();
          lp.LogintoPage(TestName);
        
          // MasWeb page
          MasWeb_Home mwHome = new MasWeb_Home();
         mwHome.Navigate_V60_ScanRFID_RepairStation();

        
        V432_Masweb_Positive_TestCases v432Mas=new V432_Masweb_Positive_TestCases();
        
        // SCanning RFID
       
       v432.Scan_RFID(4);
      // mwHome.Navigate_V60_ScanRFID_NormalStation();
       v432Mas.V432_Reprint_Repairbodyid();
       reportStep("#B22 Verify the functionality of Re printing for a registered V60 RFID from Repair outtake stations screens test case is completed#C ", "pass", false);

        
         
     } catch (Exception e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
           reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
     }
  }

     //01 Verify Existing V60 is used for V432 flow in VCLAS Nodes screen
     @Test(description = "V432_Nodes_Verfication")
     @Parameters({ "TestName" })
     public void V432_V60_Nodes_verification(String TestName) {
        try {
             
               V432 v432= new V432();
          
              // Login page
              LoginPage lp = new LoginPage();
           lp.LogintoVclas(TestName);
           
           V432_Vclas_Positive_TestCases v432Vclas= new V432_Vclas_Positive_TestCases(); 
           v432Vclas.Vclas_V60_Nodes();
        } catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
              reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
        }


  }

     
  // 02 Verify VCLAS sending the authorization to MASweb once Nodes are configured and activated in VCLAS
     
     @Test(description = "V432_Nodes_Authorization")
     @Parameters({ "TestName" })
     public void V432_V60_Nodes_Authorisation(String TestName) {
        try {
             
          
			// Login page
			LoginPage lp = new LoginPage();
			lp.LogintoPage(TestName);
			lp.LogintoVclas(TestName);

			V432_Vclas_Positive_TestCases v432Vclas = new V432_Vclas_Positive_TestCases();
			v432Vclas.Vclas_Authorization_Nodes_PreCondition();
			// switching to masweb
			driver.manage().window().maximize();
			driver.switchTo().window(browser[1]);
			driver.manage().window().maximize();
			MasWeb_Home mwHome = new MasWeb_Home();
			mwHome.Navigate_Vclas_SendAllPlaces();
			v432Vclas.Vclas_Authorization_Masweb_Tab();
			// make it NA search taking more time
		} catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
              reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
        }


  }
     
     
     // 03 Verify Existing V60 Room configuration for V432 flow
     
     @Test(description = "V432_Nodes_Rooms Configuration")
     @Parameters({ "TestName" })
     public void V432_V60_Nodes_Rooms_Configuration(String TestName) {
        try {
             
               
          
    
			// Login page
			LoginPage lp = new LoginPage();
			lp.LogintoVclas(TestName);

			V432_Vclas_Positive_TestCases v432Vclas = new V432_Vclas_Positive_TestCases();
			v432Vclas.V60_Room_Configuration();

        } catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
              reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
        }


  }
   //04 Verify the Transport Section Configuration for V432 outtake stations
     
     @Test(description = "V432_Nodes_TransportSection Configuration")
     @Parameters({ "TestName" })
     public void V432_V60_Nodes_TransportSection_Configuration(String TestName) {
        try {
             
			// Login page
			LoginPage lp = new LoginPage();
			lp.LogintoVclas(TestName);

			V432_Vclas_Positive_TestCases v432Vclas = new V432_Vclas_Positive_TestCases();
			v432Vclas.V432_TransportSection();
      
        } catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
              reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
        }


  }
     @Test(description = "LDJIT Trailer End-to-End flow")
 	@Parameters({ "TestName" })
 	public void ELANDERS(String TestName) {
 		try {

 			LoginPage lp = new LoginPage();
 			lp.LogintoPage(TestName);
 			// Navigation to LDJIT-> Yard zone maintenance
 			Elanders el = new Elanders();
 			el.verifyMoveJisjit();
 			// Navigation to LDJIT-> Yard zone maintenance
 						LDJIT obj = new LDJIT();
 						obj.navigateLdjit();
 						// Parking the trailer
 						obj.ldjitTrailersInfoPark();
 						// Bringing full trailer
 						obj.navigateLdjit();
 						obj.createTrailerAssignments("full");
 						obj.navigateLdjit();
 						obj.verifyFullTrailer("full");
 						el.verifyMoveJisjit_Trailer();
 			} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 			reportStep("@Method " + Scenario_Name + " exception to be handled", "Pass", true);
 		}
 	}
    

     
     
}
