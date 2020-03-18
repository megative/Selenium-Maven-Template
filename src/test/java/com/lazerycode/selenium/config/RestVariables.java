package com.lazerycode.selenium.config;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestVariables {

    // Storage for variables getting during tests
    public static String integrationUrl;
    public static String sessionToken;

    // Rest Assure objects
    public static Response VeriffPostRequest(String postBaseUri, String body) {
        Response postResponse =

                given()
                        .baseUri(postBaseUri)
                        .header("Accept", ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .body(body)
                        .post();

        return postResponse;
    }

    public static Response MagicVeriffGetRequest(String getBaseUri, String sessionToken) {
        Response getResponse =
                given()
                        .baseUri(getBaseUri)
                        .header("Accept", ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .get("/v1/web/session/" + sessionToken);

        return getResponse;
    }

}