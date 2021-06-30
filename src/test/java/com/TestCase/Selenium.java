package com.TestCase;

import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.commonFunction.ResuableFunction;
import com.utilities.GetExtentReport;

public class Selenium extends ResuableFunction {
	ExtentReports extent;
	ExtentTest test;

	static String actualText = "Selenium is a suite of tools for automating web browsers.";
	static String getText;
	
	@BeforeTest
	public void initiateDriver() throws Exception {
		extent = GetExtentReport.GetExtent();
		myTestCaseName = this.getClass().getSimpleName();
		executeBrowser();
	}

	@Test
	public void seleniumTest() throws IOException, InterruptedException {
		System.out.println(System.getProperty("user.dir"));

		if (continueTestCase == true) {
			sheetName = "DataSheet";
			int totalDataSheetRowCount = getData.getRowCount(sheetName);
			boolean foundTestCase = false;
			test = extent.createTest(this.getClass().getSimpleName());
			for (startIter = 1; startIter < totalDataSheetRowCount; startIter++) {
				if (this.getClass().getSimpleName().equals(getData.getCellData(sheetName, startIter, "TestCaseName"))) {
					System.out.println(getData.getCellData(sheetName, startIter, "TestCaseName"));
					foundTestCase = true;

					// test start here
					
					clickAboutUpButton();
					Thread.sleep(3000);
					
					screenShot.takeScreenShot("Selenium Test");
					test.log(Status.INFO, "About Up Click Successfull");
					
					clickAboutDownButton();
					Thread.sleep(3000);
					screenShot.takeScreenShot("Selenium Test");
					test.log(Status.INFO, "About Down Click Successfull");
					
					
					getText = getText();
					if (getText.equalsIgnoreCase(actualText)) {
						System.out.println("Text Match");
					}else {
						System.out.println("Text not match");
					}
					Thread.sleep(3000);
					
					screenShot.takeScreenShot("Selenium Test");
					test.log(Status.INFO, "Text get Successfull");
					
					clickHome();
					Thread.sleep(3000);
					
					screenShot.takeScreenShot("Selenium Test");
					test.log(Status.INFO, "Home Page Back Successfull");

					break;
				}
			}
			if (foundTestCase = false) {
				System.out.println(
						"No row/testCaseName found in the test data excel::" + this.getClass().getSimpleName());
			}
		} else {
			throw new SkipException("Skipping the testCase");
		}
		


	}

	@AfterTest
	public void closeBrowser() {
		if (continueTestCase = true) {
			extent.flush();
			driver.close();
		}
		driver.quit();
	}

}
