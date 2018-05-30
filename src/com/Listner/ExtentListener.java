package com.Listner;
import com.relevantcodes.extentreports.*;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.internal.IResultListener;

import java.io.File;
import java.util.Locale;

/**
 * Created by andrey.smirnov on 14.06.2016.
 */
public class ExtentListener implements IResultListener {

    private ExtentReports reporter;
    private ExtentTest testReporter;
   

   
    
    
    
    
    
    
    @Override
    public void onTestStart(ITestResult result) {
    	System.out.println("**********ON START**********");
    	if (reporter == null){
    		
    		reporter =  new ExtentReports("build/SimpleReport.html", true, DisplayOrder.NEWEST_FIRST, NetworkMode.ONLINE, Locale.ENGLISH);
    		
    		
    	}
    	reporter.loadConfig(new File("./config.xml"));
        testReporter = reporter.startTest(result.getMethod().getMethodName(), "Some description"+result.getTestContext());
        testReporter.log(LogStatus.INFO, "Starting test " + result.getMethod().getMethodName());
        //reporter.flush();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    	System.out.println("**********ON SUccess**********");
        testReporter.log(LogStatus.PASS, "Test PASSED");
        
        reporter.endTest(testReporter);
        reporter.flush();
    }

    @Override
    public void onFinish(ITestContext context) {
    	System.out.println("**********ON FINISH**********");
    	reporter.flush();
       // reporter.close();
    
    }

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConfigurationFailure(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConfigurationSkip(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConfigurationSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub
		

}

}