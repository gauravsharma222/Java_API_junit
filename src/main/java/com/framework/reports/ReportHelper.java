package com.framework.reports;

import com.aventstack.extentreports.ExtentTest;

public class ReportHelper {

    private static final ThreadLocal<ExtentTest> threadLocalLog = new ThreadLocal<>();

    public static void setLog(ExtentTest test) {
        threadLocalLog.set(test);
    }

    public static ExtentTest log() {
        return threadLocalLog.get();
    }

    public static void unload() {
        threadLocalLog.remove();
    }
}
