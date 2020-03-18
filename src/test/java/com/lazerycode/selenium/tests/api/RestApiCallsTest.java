package com.lazerycode.selenium.tests.api;

import com.lazerycode.selenium.config.RestVariables;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RestApiCallsTest {
    // Before testing it's good to be sure the endpoint is available.
    // Tests won't be started if endpoint is not available.
    // Used Rest Assure as a tool for precondition checking, TestNG for test runner.
    @BeforeTest
    public void initTest() {
        try {
            given()
                    .baseUri("https://demo.veriff.me/")
                    .when()
                    .get()
                    .then()
                    .assertThat()
                    .statusCode(200);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    @Test
    public void sessionCreationTestWithRestAssured(){

        // It's my first experience with this library,
        // I just wanted to try something new during this test task

        String postBaseUri = "https://demo.veriff.me/";

        String body = "{\"full_name\":\"Pavel Konev\"," +
                "\"lang\":\"en\"," +
                "\"document_country\":\"EE\"," +
                "\"document_type\":\"ID_CARD\"}";

        Response response = RestVariables.VeriffPostRequest(postBaseUri, body);

        response.then().assertThat().statusCode(200);

        Assert.assertNotNull(response.path("integrationUrl"));
        Assert.assertEquals(response.path("integrationUrl"), "https://magic.veriff.me");
        Assert.assertNotNull(response.path("sessionToken"));

        RestVariables.integrationUrl = response.path("integrationUrl");
        RestVariables.sessionToken = response.path("sessionToken");

    }

    @Test
    public void tokenUsingSessionCreation() {
        // Using the results of previous test as a precondition for this one.
        // The reason of this strategy because if user can't get generated token the workflow itself is broken.

        Response response = RestVariables.MagicVeriffGetRequest(RestVariables.integrationUrl, RestVariables.sessionToken);

        response.then().assertThat().statusCode(200);
        Assert.assertEquals(response.path("status"), "success");

    }

}
