package com.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class GetExtentReport {

	private static ExtentReports extent; // By using this class we set the path where our reports need to generate.

	private static ExtentTest test; // using this class we could generate the logs in the report.
	private static ExtentHtmlReporter htmlReporter;
	private static String filepath = System.getProperty("user.dir") + "//target//extentreport.html";

	public static ExtentReports GetExtent() {
		if (extent != null)
			return extent;
		extent = new ExtentReports();
		extent.attachReporter(getHtmlReporter());
		return extent;
	}

	private static ExtentHtmlReporter getHtmlReporter() {
		htmlReporter = new ExtentHtmlReporter(filepath);
		htmlReporter.loadXMLConfig("./ReportsConfig.XML");
		return htmlReporter;

	}

	public static ExtentTest createTest(String name, String description) {

		test = extent.createTest(name, description);
		return test;
	}

}
