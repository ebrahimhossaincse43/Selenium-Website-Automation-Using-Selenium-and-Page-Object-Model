package com.utilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BaseClass {
	
	public static WebDriver driver;
	public static GetSpreadsheetData getData;
	public static int startIter;
	public static String myBrowser;
	public static ScreenShot screenShot;
	public static String sheetName;
	public static String myUrl;
	public static String myEnvironment;
	public static String myTestCaseName;
	public static String chromeBrowserPath;
	public static String dbConnection;
	public static String authentication = null;
	public static boolean continueTestCase = false;
	public static boolean testCaseFoundinExcel = false;

	// We will use this method in testclass to open browser.
	public static void executeBrowser() throws Exception {
		sheetName = "General";
		int generalSheetRowCount = getData().getRowCount(sheetName);
		System.out.println(" Total row in the general Sheet= " + generalSheetRowCount);
		for (int row = 0; row < generalSheetRowCount; row++) {
			if (myTestCaseName.equals(getData.getCellData(sheetName, row, "TestCaseName"))) {
				System.out.println(" Current test case name= " + myTestCaseName);
				System.out.println(getData.getCellData(sheetName, row, "Execute(N/Y)"));
				if (getData.getCellData(sheetName, row, "Execute(N/Y)").equals("Yes")) {

					myUrl = getData.getCellData(sheetName, row, "Application URL");
					myBrowser = getData.getCellData(sheetName, row, "Browser");
					invokeMyBrowser();
					screenShot();
					continueTestCase = true;
					testCaseFoundinExcel = true;
					break;

				} else {
					System.out.println("TestCase name markes in general sheet not to execute");
					continueTestCase = false;

				}

			}
		}
		if (testCaseFoundinExcel == false) {
			System.out.println("The testCase name is not provided in General sheet in excel");
		}
	}

	// Calling driver
	public static void invokeMyBrowser() throws Exception {
		getDriver().manage().window().maximize();
		driver.get(myUrl);
	}

	// Initiate driver
	public static WebDriver getDriver() throws Exception {
		if (driver == null) {
			if (myBrowser.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.firefox.marionette", ".//geckodriver");
				driver = new FirefoxDriver();
			} else if (myBrowser.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver", ".//chromedriver.exe");
				driver = new ChromeDriver();
			} else if (myBrowser.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", ".//IEDriverServer");
				driver = new InternetExplorerDriver();
			} else {
				throw new Exception("Browser is not correct");
			}
		}
		return driver;
	}
	
	

	// Creating object of ScreenShot class
	public static void screenShot() {
		screenShot = new ScreenShot();

	}

// Creating object in GetSpreadsheetData class and providing excel path
	public static GetSpreadsheetData getData() throws IOException {
		if (getData == null) {
			getData = new GetSpreadsheetData(".//ExcelData//TestData.xlsx");
		}
		return getData;
	}
	
}
