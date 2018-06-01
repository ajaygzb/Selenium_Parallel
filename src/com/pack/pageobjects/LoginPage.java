package com.pack.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.base.Throwables;
import com.pack.Utils.CommonUtils;
import com.relevantcodes.extentreports.LogStatus;

import Reporting.Reporter;

public class LoginPage{

CommonUtils CU = new CommonUtils();
private static WebElement element = null;
	
	
public static WebElement username(WebDriver driver){
	
	element = driver.findElement(By.xpath("//input[@id='userNameTxt']"));
	return element;	
}

public static WebElement password(WebDriver driver){
	
	element = driver.findElement(By.xpath("//input[@name='password']"));
	return element;	
}

public static WebElement SignInButton(WebDriver driver){
	
	element = driver.findElement(By.xpath("//button[contains(.,'Sign in')]"));
	return element;	
}


	
	
	



















// Method for Login into Application
public String loginBMS(WebDriver driver){
	
	String username= CommonUtils.readPropertyValue("username");
	String password=CommonUtils.readPropertyValue("password");	
	try{
		
	System.out.println("Enter Username");		
	Reporter.getTestReporter().log(LogStatus.INFO,"Enter Username");	
	username(driver).sendKeys(username);
	System.out.println("Enter PASSWORD");
	Reporter.getTestReporter().log(LogStatus.INFO,"Enter PASSWORD");
	password(driver).sendKeys(password);
	SignInButton(driver).click();
	System.out.println("Clicked on Sign In Button");
	System.out.println("Waiting for Home Page to Load....");
	Reporter.getTestReporter().log(LogStatus.INFO,"Clicked on Sign In Button");
	Reporter.getTestReporter().log(LogStatus.INFO,"Waiting for Home Page to Load....");
	WebDriverWait wait =  new WebDriverWait(driver,30); 
	wait.until(ExpectedConditions.urlContains("provider"));
	CU.waitForLoad(driver);
	System.out.println(driver.getCurrentUrl());
	Reporter.getTestReporter().log(LogStatus.INFO,driver.getCurrentUrl());
	}catch(TimeoutException e){
		
		System.out.println("Unable to Login into Application"+Throwables.getStackTraceAsString(e));
		Reporter.getTestReporter().log(LogStatus.ERROR,"Unable to Login into Application"+Throwables.getStackTraceAsString(e));
	}
	return driver.getCurrentUrl();			
}










}
