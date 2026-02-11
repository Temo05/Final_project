package com.automationexercise.api;

import com.automationexercise.base.BaseAPITest;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GiorgiGegenavaAPITests extends BaseAPITest {

    @Test(description = "API 5: POST To Search Product")
    @Epic("API Testing")
    @Feature("Products API")
    @Story("Search product with parameter")
    @Severity(SeverityLevel.CRITICAL)
    public void testPostSearchProduct() {
        Response response = given()
                .spec(requestSpec)
                .formParam("search_product", "top")
                .when()
                .post("/searchProduct")
                .then()
                .extract()
                .response();

        logResponse(response);

        JSONObject responseJson = extractJson(response);
        Assert.assertEquals(responseJson.getInt("responseCode"), 200);
        Assert.assertTrue(responseJson.has("products"));
    }

    @Test(description = "API 4: PUT To All Brands List")
    @Epic("API Testing")
    @Feature("Brands API")
    @Story("PUT request to brands")
    @Severity(SeverityLevel.NORMAL)
    public void testPutToBrandsList() {
        Response response = given()
                .spec(requestSpec)
                .when()
                .put("/brandsList")
                .then()
                .extract()
                .response();

        logResponse(response);

        JSONObject responseJson = extractJson(response);
        Assert.assertEquals(responseJson.getInt("responseCode"), 405);
    }

    @Test(description = "API 6: POST To Search Product without search_product parameter")
    @Epic("API Testing")
    @Feature("Products API")
    @Story("Search without parameter")
    @Severity(SeverityLevel.NORMAL)
    public void testPostSearchProductWithoutParameter() {
        Response response = given()
                .spec(requestSpec)
                .when()
                .post("/searchProduct")
                .then()
                .extract()
                .response();

        logResponse(response);

        JSONObject responseJson = extractJson(response);
        Assert.assertEquals(responseJson.getInt("responseCode"), 400);
    }

    @Test(description = "API 7: POST To Verify Login with valid details")
    @Epic("API Testing")
    @Feature("Authentication API")
    @Story("Login with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void testPostVerifyLoginValid() {
        Response response = given()
                .spec(requestSpec)
                .formParam("email", "testuser@test.com")
                .formParam("password", "Test@123")
                .when()
                .post("/verifyLogin")
                .then()
                .extract()
                .response();

        logResponse(response);

        JSONObject responseJson = extractJson(response);
        Assert.assertEquals(responseJson.getInt("responseCode"), 404);
    }

    @Test(description = "API 11: POST To Create/Register User Account")
    @Epic("API Testing")
    @Feature("User Management API")
    @Story("Create new user account")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateUserAccount() {
        String email = "gegenava_test_" + System.currentTimeMillis() + "@gmail.com";
        
        Response response = given()
                .spec(requestSpec)
                .formParam("name", "Giorgi Gegenava")
                .formParam("email", email)
                .formParam("password", "Batumi@2024")
                .formParam("title", "Mr")
                .formParam("birth_date", "25")
                .formParam("birth_month", "March")
                .formParam("birth_year", "1993")
                .formParam("firstname", "Giorgi")
                .formParam("lastname", "Gegenava")
                .formParam("company", "Black Sea Technologies")
                .formParam("address1", "91 Vazha-Pshavela Avenue")
                .formParam("address2", "Building B")
                .formParam("country", "United States")
                .formParam("zipcode", "6010")
                .formParam("state", "Batumi Region")
                .formParam("city", "Batumi")
                .formParam("mobile_number", "+995 591 333 444")
                .when()
                .post("/createAccount")
                .then()
                .extract()
                .response();

        logResponse(response);

        JSONObject responseJson = extractJson(response);
        Assert.assertEquals(responseJson.getInt("responseCode"), 201);

        Response getUserResponse = given()
                .spec(requestSpec)
                .queryParam("email", email)
                .when()
                .get("/getUserDetailByEmail")
                .then()
                .extract()
                .response();

        logResponse(getUserResponse);

        JSONObject getUserJson = extractJson(getUserResponse);
        Assert.assertEquals(getUserJson.getInt("responseCode"), 200);
        Assert.assertEquals(getUserJson.getJSONObject("user").getString("email"), email);
    }

}
