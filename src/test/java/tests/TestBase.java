package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    public static final String api_key = "x-api-key";
    public static final String api_value = "reqres-free-v1";
    public static final String endpoint_users = "/users/";

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }
}
