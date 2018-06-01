package com.pack.base;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pack.Utils.CommonUtils;

import Reporting.Reporter;
import interfaces.ITestReporter;

public class Testbase {
	 
	public WebDriver driver;
	private static File projectPath = new File("");
	private String driverPath = projectPath.getAbsolutePath() + "\\src\\com\\dataRepository\\browserDrivers\\";
	public static DesiredCapabilities caps;
	public ITestReporter testReporter; 
	protected String ScreenshotPath=System.getProperty("user.home")+"\\parallelscreen\\";
	public static String ExcelPath=projectPath.getAbsolutePath() + "\\src\\com\\dataRepository\\Inputdata";
	public String browsername;
	 public static String BuildVersion ;
	
	
	
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
	  public void beforeTest(String browser) throws MalformedURLException {
		  
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
		}else if (browser.equalsIgnoreCase("safari")) {
			System.out.println("launching safari browser");
			 
			driver = new SafariDriver();
			this.browsername=browser.toUpperCase();
			}
	  
		else if (browser.equalsIgnoreCase("cloud_Safari")) {
			System.out.println("launching cloud browser Safari");
			String username = "ajaykumar64" ;
			String accessKey = "trCg4wh81EyLz7Zne48n";
			String server ="hub-cloud.browserstack.com/wd/hub" ;
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("os", "OS X");
			caps.setCapability("os_version", "High Sierra");
			caps.setCapability("browser", "Safari");
			caps.setCapability("browser_version", "11.1");
			caps.setCapability("browserstack.local", "false");
			caps.setCapability("browserstack.selenium_version", "3.5.2");
			driver = new RemoteWebDriver(new URL("http://"+username+":"+accessKey+"@"+server),caps);
			driver.manage().window().maximize();
			this.browsername=browser.toUpperCase();
			}
	  
		else if (browser.equalsIgnoreCase("cloud_AndroidTab")) {
			System.out.println("launching cloud browser on Google Nexus 6 ");
			String username = "ajaykumar64" ;
			String accessKey = "trCg4wh81EyLz7Zne48n";
			String server ="hub-cloud.browserstack.com/wd/hub" ;
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("os_version", "6.0");
			caps.setCapability("device", "Google Nexus 6");
			caps.setCapability("real_mobile", "true");
			caps.setCapability("project", "LoginBMS");
			caps.setCapability("build", "Buildversion");
			caps.setCapability("name", "LoginTest");
			caps.setCapability("browserstack.local", "false");
			caps.setCapability("browserstack.appium_version", "1.7.1");
			driver = new RemoteWebDriver(new URL("http://"+username+":"+accessKey+"@"+server),caps);
			//driver.manage().window().maximize();
			this.browsername=browser.toUpperCase();
			}

	  // launch the URL
	  driver.get(CommonUtils.readPropertyValue("URL"));
	  driver.manage().timeouts().implicitlyWait(80,TimeUnit.SECONDS);
	  CommonUtils.waitforElement(driver, By.xpath("//img[contains(@src,'logobig')]//.."),30);
	  BuildVersion = driver.findElement(By.xpath("//img[contains(@src,'logobig')]//..")).getText().trim();
	 
	  
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
