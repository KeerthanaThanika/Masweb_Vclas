package com.volvo.mfg.agv.pages;

import java.util.HashMap;
import com.volvo.automation.commonutilities.ExcelUtils;
import java.io.File;
import com.relevantcodes.extentreports.LogStatus;
import com.volvo.automation.commonutilities.CommonWrapperMethods;
import com.volvo.mfg.StepDefinition.DriverSuite;
import com.volvo.reports.Reports;

public class Parameter_differentracksize extends DriverSuite {
            // Excel class object to access the function
            HashMap<String, String> tdrow;
            ExcelUtils excelUtils = new ExcelUtils();
            static boolean check = false;
            double thresholdValue = -10;
            private static final Boolean FALSE = null;

            public double planning() throws InterruptedException {
                        String sheetName = "Vclas_Assignments";

                        // retrieve data from MasWeb_Page
                        // Load Test Data File
                        tdrow = excelUtils.testCaseRetrieve("vclass_01", sheetName);
                        String planningBasedThreshold = "";
                        String planningBasedTime = "";
                        String thresholdValueq="";
                        double thresholdVal = 0.0;
                        double thresholdLopNum = 0.0;
                        
                        int lopnumberCnt = 30;
                        do {

                                    int racksize = 20; // Integer.parseInt(tdrow.get("Article_Count"));
                                    System.out.println("racksize :" + racksize);

                                    // Min column in Assignments

                                    // Balance Column in Assignment Screen
                                    int totalSaldo = racksize + lopnumberCnt;
                                    // NODEPARAMETERS
                                    Boolean useLocalTime = false;
                                    String priortizer = "Elastic";
                                    int maxCriticalAssignments = 3;
                                    int criticalCarNrLimit = 0;
                                    int carsPerHour = 60;
                                    
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

                                    if (time < tsGraceTime) {
                                                planningBasedTime = "plan";
                                                break;
                                                // check..

                                    } else {
                                                planningBasedTime = "Dont plan";

                                    }
                                    thresholdLopNum = (racksize * thresholdValue / 100);
                                    System.out.println("thresholdLopNum : " + thresholdLopNum);

                                    if (lopnumberCnt <= thresholdLopNum) {
                                                planningBasedThreshold = "Plan";
                                                System.out.println("lopnumberCnt: " + lopnumberCnt + " thresholdLopNum: " + thresholdLopNum + " Plan");
                                                break;

                                    } else {
                                                planningBasedThreshold = "Dont Plan";
                                                System.out.println("lopnumberCnt: " + lopnumberCnt + " Dont Plan");
                                    }

                                    lopnumberCnt--;
                                    // System.out.println("lopnumberCnt:" +lopnumberCnt);

                                    if (check == false) {
                                                reportStep("tsGraceTime: " + tsGraceTime + " graceTime: " + graceTime + " thresholdValue: "
                                                                        + thresholdValue + " racksize: " + racksize, "pass", false);
                                                // System.out.println("once");

                                    }
                                    check = true;

                        } while (planningBasedThreshold.equalsIgnoreCase("Dont Plan")
                                                || planningBasedTime.equalsIgnoreCase("Dont Plan"));

                        if (planningBasedThreshold.equalsIgnoreCase("Plan")) {
                                    thresholdVal = thresholdLopNum;
                                    System.out.println("Planning based on threshold: " + thresholdVal);
                                    if (thresholdVal % 1 != 0) {

                                                System.out.println(Math.ceil(thresholdVal));
                                                reportStep("Planning Based on threshold - " + thresholdVal, "pass", false);
                                                return thresholdVal;
                                    }

                        } else if (planningBasedTime.equalsIgnoreCase("Plan")) {
                                    thresholdVal = lopnumberCnt;
                                    System.out.println("Planning based on Time: " + thresholdVal);
                                    reportStep("Planning Based on Time - " + thresholdVal, "pass", false);

                        } else {
                                    thresholdVal = thresholdLopNum;
                                    System.out.println("doesnt plan at " + thresholdVal);
                        }
                        
                        return thresholdVal;
                        
            
            }
}
