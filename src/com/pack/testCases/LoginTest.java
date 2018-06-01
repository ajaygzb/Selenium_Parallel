package com.pack.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.base.Throwables;
import com.pack.Utils.CommonUtils;
import com.pack.base.Testbase;
import com.pack.pageobjects.LoginPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Reporting.Reporter;

public class LoginTest extends Testbase {
	CommonUtils CU = new CommonUtils();






	@Test(testName = "Login BMS",description = "This is Test Login BMS")
	public void login() throws InterruptedException {
		testReporter.log(LogStatus.INFO, "Thread id is " + Thread.currentThread().getId());
		testReporter.log(LogStatus.INFO, "Steps of Login BMS");
		testReporter.assignCategory(browsername);	
		LoginPage lp = new LoginPage();
		try{

			Assert.assertTrue(lp.loginBMS(driver).contains("provider"));
			testReporter.log(LogStatus.INFO, "Snapshot below: " + 
					testReporter.addScreenCapture
					(CU.CaptureScreen(driver,ScreenshotPath+ CU.getCurrentTimeStamp())));
			testReporter.log(LogStatus.PASS, "************Assertion PASS************");
		}catch(AssertionError e){			
			testReporter.log(LogStatus.INFO, "Snapshot below: " + 
					testReporter.addScreenCapture
					(CU.CaptureScreen(driver,ScreenshotPath+ CU.getCurrentTimeStamp())));
			testReporter.log(LogStatus.FAIL, "************Assertion FAILED************"+Throwables.getStackTraceAsString(e));
			Assert.assertFalse(true);
		}
	}  



























}
