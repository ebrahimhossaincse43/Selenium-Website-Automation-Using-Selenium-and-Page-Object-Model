package com.commonFunction;

import org.openqa.selenium.By;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.objectRepositery.ObjectRepository_xpath;
import com.utilities.PageActions;

public class ResuableFunction extends PageActions {
	ExtentReports extent;
	ExtentTest test;

	
	
	public static void clickAboutUpButton() {

		getElement("Selenium about button", "Selenium about button Found", (By.xpath(ObjectRepository_xpath.clickAboutUp)),
				defaultElementLoadTime, "click", "");
	}

	public static void clickAboutDownButton() {

		getElement("Selenium about button", "Selenium about button Found", (By.xpath(ObjectRepository_xpath.clickAboutDown)),
				defaultElementLoadTime, "click", "");
	}

	public static String getText() {
		
		String actualText = driver.findElement(By.xpath(ObjectRepository_xpath.getText)).getText();
		
		return actualText;
		
	}
	
	public static void clickHome() {

		getElement("Selenium home page", "Back to home page",
				(By.xpath(ObjectRepository_xpath.backHome)), defaultElementLoadTime, "click", "");
	}

}
