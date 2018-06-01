package com.pack.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.base.Throwables;
import com.pack.base.Testbase;

public class CommonUtils {
	
	public static File projectPath = new File("");
	private static Properties prop = new Properties();
	
	
	public static String readPropertyValue(String strPropertyName) {
		String value = null;
		FileInputStream fileinputstream;

		try {
			fileinputstream = new FileInputStream(projectPath.getAbsolutePath()
					+ "/src/com/pack/config/properties/Config.properties");
			prop.load(fileinputstream);
			value = prop.getProperty(strPropertyName);
			fileinputstream.close();
		} catch (Exception e) {
			System.out.println("Could not read from Property File "+Throwables.getStackTraceAsString(e));
			
		}
		return value;

	}
	
	public static boolean waitforElement(WebDriver driver, final By locator,
			int intTimout) {
		
		try{
		WebDriverWait wait = new WebDriverWait(driver, intTimout);
		 wait.until(ExpectedConditions.refreshed(
		 ExpectedConditions.presenceOfElementLocated(locator)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    return driver.findElement(locator).isDisplayed();
		}catch(Exception e){
			
			return false;
		}
	
	}
	
	  public String CaptureScreen(WebDriver driver, String ImagesPath)
	    {
	        TakesScreenshot oScn = (TakesScreenshot) driver;
	        File oScnShot = oScn.getScreenshotAs(OutputType.FILE);
	     File oDest = new File(ImagesPath+"  "+"test.jpg");
	     try {
	          FileUtils.copyFile(oScnShot, oDest);
	     } catch (IOException e) {System.out.println(e.getMessage());}
	     return ImagesPath+"  "+"test.jpg";
	            }
	  
	  public String getCurrentTimeStamp() {
	        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");//dd/MM/yyyy
	        Date now = new Date();
	        String strDate = sdfDate.format(now);
	        System.out.println(strDate);
	        return strDate;
	    }
	  
	  public  void waitForLoad(WebDriver driver) {
			 
		  try{
			  untilAngularFinishHttpCalls(driver);
			  
			
			  waitForElementToBeNotVisible(driver,By.xpath("//*[@id='mySpinner']"),30);
			  new WebDriverWait(driver,60).until((ExpectedCondition<Boolean>) wd ->
	            ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
	
			 
			 
		  
		  }catch(Exception e){
				  
				  
				  
			  }
	    }
	  
	 
	  public  void untilAngularFinishHttpCalls(WebDriver driver) {
		  try{
			final String javaScriptToLoadAngular =
		            "var injector = window.angular.element('body').injector();" + 
		            "var $http = injector.get('$http');" + 
		            "return ($http.pendingRequests.length === 0)";

		    ExpectedCondition<Boolean> pendingHttpCallsCondition = new ExpectedCondition<Boolean>() {
		        @Override
				public Boolean apply(WebDriver driver) {
		            return ((JavascriptExecutor) driver).executeScript(javaScriptToLoadAngular).equals(true);
		        }
		    };
		    WebDriverWait wait = new WebDriverWait(driver,20); // timeout = 30 secs
		    wait.until(pendingHttpCallsCondition);


		    }catch(Exception e){
			
			
		}

		}
	  
	  
	   public  boolean waitForElementToBeNotVisible(WebDriver driver, By locator,int intTimout) {

		    boolean webElement = false;

		    try {
		        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		        @SuppressWarnings("deprecation")
				FluentWait<WebDriver> wait = new WebDriverWait(driver,intTimout ).ignoring(
		                NoSuchElementException.class, StaleElementReferenceException.class).pollingEvery(5,
		                        TimeUnit.SECONDS);
		        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
		     //   System.out.println("Element is not visible");
		        webElement = true;
		    } catch (Exception e) {            
		        System.out.println("Element is visible | ");
		        webElement = false;
		    } 
		    
		    return webElement;
		}
	   
		public static List<String> ReadExceldata(int SheetIndex) throws InterruptedException {
			String str;
			List<String> data = new ArrayList<String>();
				try {
					FileInputStream file = new FileInputStream(
					new File(Testbase.ExcelPath+"//BMS.xlsx"));
					XSSFWorkbook workbook = new XSSFWorkbook(file);
					XSSFSheet sheet = workbook.getSheetAt(SheetIndex);
					System.out.println("rows" + sheet.getLastRowNum());
					// Find number of rows in excel file
					int rowCount = sheet.getLastRowNum();  // Index from zero
					System.out.println("Total rows" + rowCount);
					System.out.println("Getting Patient data from Excel..");
					// Create a loop over all the rows of excel file to read it
					for (int i = 1; i <= rowCount; i++) {
						Row row = sheet.getRow(i);
						// Create a loop to print cell values in a row
						for (int j = 0; j < row.getLastCellNum(); j++) {
							DataFormatter formatter = new DataFormatter();
							str = formatter.formatCellValue(sheet.getRow(i).getCell(j));
							data.add(str);
						}
					}
					for(String s:data)
						System.out.println(s);
					workbook.close();
				} catch (Exception e) {
					e.printStackTrace();
				} 
				
				
				return data;
			}














}
