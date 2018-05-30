package UIMAP;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utils.commons.CommonUtils;

public class LoginPage{

	CommonUtils CU = new CommonUtils();	
	
	
	
	
	
	
	
public String loginBMS(WebDriver driver){
	
	String username= CommonUtils.readPropertyValue("username");
	String password=CommonUtils.readPropertyValue("password");
	
	driver.findElement(By.xpath("//input[@id='userNameTxt']")).sendKeys(username);
	driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
	driver.findElement(By.xpath("//button[contains(.,'Sign in')]")).click();
	WebDriverWait wait =  new WebDriverWait(driver,60); 
	wait.until(ExpectedConditions.urlContains("provider"));
	CU.waitForLoad(driver);
	System.out.println(driver.getCurrentUrl());
	return driver.getCurrentUrl();			
}










}
