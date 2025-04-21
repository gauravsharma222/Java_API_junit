package com.framework.listeners;

import org.junit.jupiter.api.extension.*;
import com.aventstack.extentreports.*;
import com.framework.reports.ExtentReportManager;
import com.framework.reports.ReportHelper;

public class ExtentReportListener implements BeforeAllCallback, AfterAllCallback,
        BeforeEachCallback, AfterEachCallback, TestWatcher {

    private static ExtentReports extent;

    @Override
    public void beforeAll(ExtensionContext context) {
        extent = ExtentReportManager.getExtentReports();
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        ExtentTest test = extent.createTest(context.getDisplayName());
        ReportHelper.setLog(test);
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        if (ReportHelper.log() != null) {
            ReportHelper.log().pass("✅ Test Passed");
        }
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        if (ReportHelper.log() != null) {
            ReportHelper.log().fail("❌ Test Failed: " + cause.getMessage());
        }
    }

    @Override
    public void afterEach(ExtensionContext context) {
        extent.flush();
        ReportHelper.unload(); // Cleanup thread-local
    }

    @Override
    public void afterAll(ExtensionContext context) {
        extent.flush();
    }
}
