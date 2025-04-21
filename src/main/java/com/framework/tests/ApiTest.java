package com.framework.tests;

import com.framework.listeners.ExtentReportListener;
import com.framework.config.EnvironmentConfig;
import com.framework.reports.ExtentReportManager;
import com.framework.reports.ReportHelper;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ExtentReportListener.class)
public class ApiTest {

    EnvironmentConfig config = ConfigFactory.create(EnvironmentConfig.class);

    @Test
    void testGetUser() {
        int statusCode = RestAssured.get(config.baseUrl() + "/posts/1").getStatusCode();
        assertEquals(200, statusCode);
        ReportHelper.log().fail("Test Passed with"+ statusCode);
    }
}
