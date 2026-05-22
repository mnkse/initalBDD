package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiClient {

    static {
        RestAssured.baseURI = ConfigReader.getProperty("apiBaseUrl");
    }

    private static String getApiKey() {

        return ConfigReader.getProperty("apiKey");
    }

    public static Response get(String endpoint) {

        return RestAssured
                .given()
                .header("x-api-key", getApiKey())
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    public static Response post(String endpoint, Object body) {

        return RestAssured
                .given()
                .header("Content-Type", "application/json")
                .header("x-api-key", getApiKey())
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .extract()
                .response();
    }

    public static Response put(String endpoint, Object body) {

        return RestAssured
                .given()
                .header("Content-Type", "application/json")
                .header("x-api-key", getApiKey())
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();
    }

    public static Response delete(String endpoint) {

        return RestAssured
                .given()
                .header("x-api-key", getApiKey())
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();
    }
}