package com.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class PageActions extends BaseClass{
	public static int defaultElementLoadTime = 30;
	String ReportElement = null;
	public static int rNum ;
	//static WebElement elementVisible = null;
	
	public static String getElement (String pageName,String userMsg,By locator, int timeout,String action,String value) {
		WebElement elementVisible=null;
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		try {
			 elementVisible =(WebElement)wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    System.out.println("x-path value of " +pageName +" = "+ locator +" found");
	    String myLocator = "clickeble locator found "+locator;
	    Reporter.log(myLocator);
		}catch (TimeoutException el){
			el.printStackTrace();
			System.out.println(locator + "element not found after waiting for " + timeout+ "second");
			String myLocator = "Clickable not found ::" + locator;
			Assert.assertFalse(elementVisible==null,myLocator);
			 Reporter.log("Clickable element not found");
			 return myLocator;
		}
		System.out.println("Element found "+ locator);
		if(!(action == " ")) {
			performAction(locator, action, value);
		}
		return value;
	}
	
	
	// Performing Actions
	public static void performAction(By locator, String action, String value) {
		switch (action) {
		case "sendKeys":
			driver.findElement(locator).sendKeys(value);
			break;
		case "click":
			driver.findElement(locator).click();
			break;
		case "submit":
			driver.findElement(locator).submit();
			break;
		case "clear":
			driver.findElement(locator).clear();
			break;
				
		}
	}
	
}
