package com.framework.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static final ExtentReports extent = new ExtentReports();


    static {
        ExtentSparkReporter reporter = new ExtentSparkReporter("reports/ExtentReport.html");
        extent.attachReporter(reporter);
        extent.setSystemInfo("Author", "Miisco");
    }

    public static ExtentReports getExtentReports() {
        return extent;
    }
}
