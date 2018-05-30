package TestModule;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.test.base.Testbase;
import com.utils.commons.CommonUtils;

import Reporting.Reporter;
import UIMAP.LoginPage;

public class LoginTest extends Testbase {
CommonUtils CU = new CommonUtils();



	


	@Test(testName = "Login BMS",description = "This is Test Login BMS")
	public void login() throws InterruptedException {
		testReporter.log(LogStatus.INFO, "Thread id is " + Thread.currentThread().getId());
		testReporter.log(LogStatus.INFO, "Step1 of Login BMS");
		testReporter.log(LogStatus.INFO, "Step2 of Login BMS");
		testReporter.log(LogStatus.INFO, "Step3 of Login BMS");
		testReporter.assignCategory(browsername);	
		LoginPage lp = new LoginPage();
		Assert.assertTrue(lp.loginBMS(driver).contains("provider"));
		testReporter.log(LogStatus.INFO, "Snapshot below: " + 
		testReporter.addScreenCapture
		(CU.CaptureScreen(driver,ScreenshotPath+ CU.getCurrentTimeStamp())));
		testReporter.log(LogStatus.INFO, "************Assertion PASS************");


	}  



























}
