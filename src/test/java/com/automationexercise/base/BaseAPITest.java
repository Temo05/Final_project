package com.automationexercise.base;

import io.qameta.allure.Allure;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;

public class BaseAPITest {
    protected RequestSpecification requestSpec;

    @BeforeClass
    public void setupAPI() {
        RestAssured.baseURI = "https://automationexercise.com";
        RestAssured.basePath = "/api";

        requestSpec = new RequestSpecBuilder()
                .setContentType("application/x-www-form-urlencoded; charset=ISO-8859-1")
                .addFilter(new AllureRestAssured())
                .build();
    }

    protected void logResponse(Response response) {
        Allure.addAttachment("Response", "text/plain", response.asString());
    }

    protected JSONObject extractJson(Response response) {
        String responseBody = response.asString();
        
        if (responseBody.contains("<html>")) {
            int start = responseBody.indexOf("{");
            int end = responseBody.lastIndexOf("}") + 1;
            if (start >= 0 && end > start) {
                responseBody = responseBody.substring(start, end);
            }
        }
        
        return new JSONObject(responseBody);
    }
}
