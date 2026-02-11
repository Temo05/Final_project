package com.automationexercise.api;

import com.automationexercise.base.BaseAPITest;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TemuriJibgashviliAPITests extends BaseAPITest {

    @Test(description = "API 1: Get All Products List")
    @Epic("API Testing")
    @Feature("Products API")
    @Story("Get all products")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetAllProductsList() {
        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/productsList")
                .then()
                .extract()
                .response();

        logResponse(response);

        JSONObject responseJson = extractJson(response);
        Assert.assertEquals(responseJson.getInt("responseCode"), 200);
        Assert.assertTrue(responseJson.has("products"));
    }

    @Test(description = "API 2: POST To All Products List")
    @Epic("API Testing")
    @Feature("Products API")
    @Story("POST request to products list")
    @Severity(SeverityLevel.NORMAL)
    public void testPostToAllProductsList() {
        Response response = given()
                .spec(requestSpec)
                .when()
                .post("/productsList")
                .then()
                .extract()
                .response();

        logResponse(response);

        JSONObject responseJson = extractJson(response);
        Assert.assertEquals(responseJson.getInt("responseCode"), 405);
    }

    @Test(description = "API 9: DELETE To Verify Login")
    @Epic("API Testing")
    @Feature("Authentication API")
    @Story("DELETE request to login")
    @Severity(SeverityLevel.NORMAL)
    public void testDeleteToVerifyLogin() {
        Response response = given()
                .spec(requestSpec)
                .when()
                .delete("/verifyLogin")
                .then()
                .extract()
                .response();

        logResponse(response);

        JSONObject responseJson = extractJson(response);
        Assert.assertEquals(responseJson.getInt("responseCode"), 405);
    }

    @Test(description = "API 3: Get All Brands List")
    @Epic("API Testing")
    @Feature("Brands API")
    @Story("Get all brands")
    @Severity(SeverityLevel.NORMAL)
    public void testGetAllBrandsList() {
        Response response = given()
                .spec(requestSpec)
                .when()
                .get("/brandsList")
                .then()
                .extract()
                .response();

        logResponse(response);

        JSONObject responseJson = extractJson(response);
        Assert.assertEquals(responseJson.getInt("responseCode"), 200);
        Assert.assertTrue(responseJson.has("brands"));
    }

    @Test(description = "API 8: POST To Verify Login without email parameter")
    @Epic("API Testing")
    @Feature("Authentication API")
    @Story("Login with invalid credentials")
    @Severity(SeverityLevel.NORMAL)
    public void testPostVerifyLoginInvalid() {
        String email = "invalid_test_" + System.currentTimeMillis() + "@gmail.com";

        Response response = given()
                .spec(requestSpec)
                .formParam("email", email)
                .formParam("password", "InvalidPassword123!")
                .when()
                .post("/verifyLogin")
                .then()
                .extract()
                .response();

        logResponse(response);

        JSONObject responseJson = extractJson(response);
        Assert.assertEquals(responseJson.getInt("responseCode"), 404);
    }
}
