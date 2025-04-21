package com.framework.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.framework.listeners.ExtentReportListener;
import com.framework.config.EnvironmentConfig;
import com.framework.reports.ExtentReportManager;
import com.framework.reports.ReportHelper;
import com.framework.tests.POJO.Product;
import com.framework.utils.JsonUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ExtentReportListener.class)
public class ApiTest {

    EnvironmentConfig config = ConfigFactory.create(EnvironmentConfig.class);

    @Test
    void testGetUser() {
        var statusCode = RestAssured.get(config.baseUrl() + "/posts/1").getStatusCode();
        assertEquals(200, statusCode);
        ReportHelper.log().fail("Test Passed with"+ statusCode);
    }
    @Test
    void testWithJsonData() throws IOException, IOException {
        User user = JsonUtils.readJsonFile("src/test/resources/testdata.json", User.class);
        ReportHelper.log().info("Loaded user: " + user.getName() + " / " + user.getEmail());
        //User user = new ObjectMapper().readValue(response.asString(), User.class);  Convert the response JSON to a Java class (User.class) using:
        assertEquals("Ramesh", user.getName());
        assertEquals("ramesh@example.com", user.getEmail());
    }

    @Test
    void testMultipleUsersFromJson() throws IOException {
        List<User> users = JsonUtils.readJsonList("src/test/resources/Test2.json", User.class);

        for (User user : users) {
            ReportHelper.log().info("🔍 Testing user: " + user.getName() + ", Email: " + user.getEmail());

            // Simulate GET or validation - replace with real API logic if needed
            assertNotNull(user.getEmail());
            assertTrue(user.getEmail().contains("@"));
        }

        ReportHelper.log().pass("✅ All user records passed validations");
    }
    @Test
    void testPostUserPayload() throws IOException {
        ReportHelper.log().info("Step 1: Creating User object");

        User user = new User();
        user.setId(101);
        user.setName("Arjun");
        user.setUsername("arjun.dev");
        user.setEmail("arjun@example.com");

        ReportHelper.log().info("Step 2: Converting to JSON payload");
        String jsonPayload = JsonUtils.convertObjectToJson(user);
        ReportHelper.log().info("Payload: \n" + jsonPayload);

        ReportHelper.log().info("Step 3: Sending POST request to API");

        String endpoint = "https://jsonplaceholder.typicode.com/posts"; // Mock API

        int responseCode = given()
                .contentType(ContentType.JSON)
                .body(jsonPayload)
                .when()
                .post(endpoint)
                .then()
                .log().body()
                .statusCode(201)
                .extract()
                .statusCode();

        ReportHelper.log().info("Step 4: Received response code = " + responseCode);
        ReportHelper.log().pass("✅ User posted successfully");
    }
    @Test
    void testGetAPIData() throws JsonProcessingException {
        ReportHelper.log().info("Step 1: Calling endpoint https://api.restful-api.dev/objects");
        String endpoint = "https://api.restful-api.dev/objects";
        Response response = given().when().get(endpoint).then().statusCode(200).extract().response();
        String responseBody =  response.getBody().asString();
        List<Product> products = JsonUtils.convertJsonToList(responseBody,Product.class);
        assertNotNull(products.get(0).getId());
        System.out.println(products.get(0).getId());
    }
}
