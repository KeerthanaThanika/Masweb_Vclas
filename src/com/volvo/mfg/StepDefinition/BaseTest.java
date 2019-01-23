package com.volvo.mfg.StepDefinition;

import com.relevantcodes.extentreports.ExtentTest;
import com.volvo.automation.commonutilities.CommonWrapperMethods;
import com.volvo.automation.commonutilities.Mail;

import org.testng.annotations.BeforeMethod;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.xml.XmlSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class BaseTest extends CommonWrapperMethods {
	Mail mail = new Mail();
            
            //public Reports rp;

            
            public ITestContext testContext;
            
           
  @BeforeMethod(alwaysRun=true)
  @Parameters({"TestName"})
  public void beforeMethod(String refTestSheetName, Method M, ITestContext context) {
              
      System.out.println("@BeforeMethod");
    
              //Sheet reference for test data
      refTestDataName = refTestSheetName;
      
      //Scenario_Name = context.getCurrentXmlTest().getName().toString();
      Scenario_Name = M.getName();
      Test testRunning = M.getAnnotation(Test.class);
      testDescription = testRunning.description();
      
              //Creation of Report Steps
              ExtentTest test = startTestCase(Scenario_Name,testDescription);
      test.assignAuthor("TechM_Automation");
      test.assignCategory("Regression_Testing");
     
  }
  

  @AfterMethod(alwaysRun = true)
  public void afterMethod() {
              //extentreport.reportend
              System.out.println("@AfterMethod");
              //Complete the test case
              endTestcase();
  }

  @BeforeTest
  public void beforeTest() {
              System.out.println("@BeforeTest");
              
              //XmlSuite suite = testContext.getSuite().getXmlSuite();
              //System.out.println(testContext.getCurrentXmlTest().getName());
              //Scenario_Name = testContext.getSuite().getName().toString();
               // Get thread count        
      //int threads = suite.getThreadCount();
              //System.out.println(suite.getCurrentXmlTest().getSuite().getFileName().length());
              
              
  }

  @AfterTest
  public void afterTest() throws FileNotFoundException, IOException, InterruptedException {
	  
              System.out.println("@AfterTest");
              endTestcase();
              
           
  			
  			
  			
  }

  @BeforeSuite
  public void beforeSuite() {
            System.out.println("Initiating Automation Suite...");
            //Creating the Test Report
            startResult();
            //Loading the Objects (Page Objects) and Varaibles
            loadObject();
            suiteVariables();
            //Launching the Browser 
            launchBrowser(Browser);
            // initializeLogger();
  }
  @Parameters({"mail"})
  @AfterSuite
  public void afterSuite(@Optional("N") String mail)  {
              System.out.println("Clearing variables and object memory");
              if (driver !=null) {
                          driver.quit();
              }
              endResult();
              System.out.println("...Exiting Automation Suite");
              //Displaying the Executed Test Result
              testDisplayResult();
              try {
            	  System.out.println(mail);
              if (mail.equalsIgnoreCase("Y"))
              {
              Mail.sendmail();
              }
              }
              catch(Exception e)
              {
            	  System.out.println("Exception in Mail Sent"+e);
              }
              clearingMemory();
  }
  
}
