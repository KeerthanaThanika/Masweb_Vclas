//QA
package com.volvo.mfg.agv.pages;

import java.io.File;

import com.relevantcodes.extentreports.LogStatus;
import com.volvo.automation.commonutilities.CommonWrapperMethods;
import com.volvo.mfg.StepDefinition.DriverSuite;
import com.volvo.reports.Reports;

public class ParamaterPlanning extends DriverSuite {
	private static final Boolean FALSE = null;

	public static void main(String[] args) {

		int thersholdValue = planning();
		System.out.println(thersholdValue);

	}

	public static int planning() {
		int thresholdVal = 0;
		// INPUT
		int rackSize = 20;
		// Min column in Assignment
		int lopnumberCnt = 10;
		// Balance Column in Assignment Screen
		int totalSaldo = rackSize + lopnumberCnt;
		// NODEPARAMETERS
		Boolean useLocalTime = false;
		String priortizer = "Elastic";
		int maxCriticalAssignments = 3;
		int criticalCarNrLimit = 0;
		int carsPerHour = 60;
		int thresholdValue = -10;
		int maxSpace = 2;
		double multiSpaceDelayFactor = 0.0;
		int multiSpaceMaxDelay = 30;
		double safetyMargin = 0.1;
		int safetyMarginMaxTime = 15;
		int tsGraceTime = 900;
		int graceTime = 300;
		int reactionTime = 120;
		int manageTime = 90;
		// TRANSPORT SECTION PARAMTERS
		int tranportSectionTime = 420;
		// Planning

		int totalTimeLeft = (totalSaldo * 3600 / carsPerHour);

		int timeForDriving = (manageTime + tranportSectionTime + graceTime);
		int time = ((totalTimeLeft - timeForDriving)) - reactionTime;
		System.out.println(totalTimeLeft + " " + timeForDriving + " " + time);
		String planningBasedTime;
		if (time < tsGraceTime) {
			planningBasedTime = "plan";

		} else {
			planningBasedTime = "Dont plan";
		}
		int thresholdLopNum = (rackSize * thresholdValue / 100);
		String planningBasedThreshold;
		if (lopnumberCnt <= thresholdLopNum) {
			planningBasedThreshold = "Plan";

		} else {
			planningBasedThreshold = "Dont Plan";
		}

		if (planningBasedThreshold.equalsIgnoreCase("Plan")) {
			thresholdVal = thresholdLopNum;

			System.out.println("Planning based on threshold: " + thresholdVal);

		} else if (planningBasedTime.equalsIgnoreCase("Plan")) {
			thresholdVal = lopnumberCnt;
			System.out.println("Planning based on Time: " + thresholdVal);
		}
		else {
			thresholdVal = thresholdLopNum;
			System.out.println("doesnt plan at "+thresholdVal);
		}
		return thresholdVal;
	}

}
