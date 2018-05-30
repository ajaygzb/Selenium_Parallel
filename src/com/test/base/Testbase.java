package com.test.base;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.utils.commons.CommonUtils;

import Reporting.Reporter;
import interfaces.ITestReporter;

public class Testbase {
	 
	public WebDriver driver;
	private File projectPath = new File("");
	private String driverPath = projectPath.getAbsolutePath() + "\\src\\com\\dataRepository\\browserDrivers\\";
	public static DesiredCapabilities caps;
	protected ITestReporter testReporter; 
	protected String ScreenshotPath=System.getProperty("user.home")+"\\parallelscreen\\";
	public String browsername;
	
	
	
	@BeforeTest
	public void classSetUp()
	{
		testReporter = Reporter.getTestReporter();
	}
	
	@BeforeMethod
	public synchronized void methodSetup(Method caller)
	{
		testReporter.startTest(getTestName(caller), getTestDescription(caller));
		
		 
	}
	
	@AfterMethod
	public synchronized void methodSetup1(Method caller)
	{
		testReporter.endTest();
		
		
	}
	
	private String getTestName(Method caller)
	{
		Test testAnnotation = caller.getAnnotation(Test.class);
		if(testAnnotation != null)
		{
			return testAnnotation.testName();
		}
		return "";
	}
	
	private String getTestDescription(Method caller)
	{
		Test testAnnotation = caller.getAnnotation(Test.class);
		if(testAnnotation != null)
		{
			return testAnnotation.description();
		}
		return "";
	}

	@Parameters("browser")
	@BeforeClass
	  public void beforeTest(String browser) {
		  
	  if(browser.equalsIgnoreCase("firefox")) {
		  System.out.println("launching Firefox browser");
		System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
		  driver = new FirefoxDriver();
		  driver.manage().deleteAllCookies();
		  driver.manage().window().maximize();
		  this.browsername=browser.toUpperCase();
		  
		 

	  // If browser is IE, then do this	  

	  }else if (browser.equalsIgnoreCase("ie")) { 

		  // Here I am setting up the path for my IEDriver
		  System.out.println("launching IE browser");
		  System.setProperty("webdriver.ie.driver",driverPath+"IEDriverServer.exe");
		 
		  driver = new InternetExplorerDriver();
		  driver.manage().deleteAllCookies();
		  driver.manage().window().maximize();
		  this.browsername=browser.toUpperCase();
		  
		  

	  }
	  
	  else if (browser.equalsIgnoreCase("chrome")) {
		  System.out.println("launching Chrome browser");
		  System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
		  ChromeOptions options = new ChromeOptions();
		  options.addArguments("--disable-extensions");
		  options.addArguments("disable-infobars");
		 // options.addArguments("-incognito"); //to run incognito mode
		  options.merge(caps);  
		  driver = new ChromeDriver(options); 
		  driver.manage().deleteAllCookies();
		  driver.manage().window().maximize();
		  this.browsername=browser.toUpperCase();
		  
	  } else if (browser.equalsIgnoreCase("Edge")) {
		System.out.println("launching Microsoft Edge browser");
		System.setProperty("webdriver.edge.driver",driverPath+"MicrosoftWebDriver.exe");  
		driver = new EdgeDriver();
		this.browsername=browser.toUpperCase();
		}

	  // launch the URL
	  driver.get(CommonUtils.readPropertyValue("URL"));
	 
	 // driver.manage().timeouts().pageLoadTimeout(80, TimeUnit.SECONDS);
	  driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
	 
	  
	  }
	 
	
	
	public WebDriver getDriver() {
	    return driver;
	}

	  @AfterClass public void afterTest() {
		 driver.close();
		 //driver.quit();

		}
	  
	  @AfterSuite public void afterSuite() {
			// driver.close();
			 driver.quit();

			}





























}
