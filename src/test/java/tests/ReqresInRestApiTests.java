package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;


public class ReqresInRestApiTests {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }

    @DisplayName("Получить список пользователей")
    @Test
    void getListUsersTest() {
        given()
                .when()
                .queryParam("page", "2")
                .get("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("page", equalTo(2))
                .body("data.size()", greaterThan(0));
    }

    @DisplayName("Создать пользователя")
    @Test
    public void createUserTest() {
        String requestBody = "{\"name\": \"morpheus\", \"job\": \"leader\"}";

        given()
                .header("x-api-key", "reqres-free-v1")
                .contentType(JSON)
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("leader"));
    }

    @DisplayName("Полностью обновить информацию о пользователе (PUT)")
    @Test
    public void updateUserTest() {
        String requestBody = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}";

        given()
                .header("x-api-key", "reqres-free-v1")
                .contentType(JSON)
                .body(requestBody)
                .when()
                .put("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("zion resident"));
    }

    @DisplayName("Заменить чать информации o пользователе (PATCH)")
    @Test
    public void replaceDataUserTest() {
        String requestBody = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}";

        given()
                .header("x-api-key", "reqres-free-v1")
                .contentType(JSON)
                .body(requestBody)
                .when()
                .patch("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", equalTo("morpheus"))
                .body("job", equalTo("zion resident"));
    }

    @DisplayName("Ошибка при попытке получить несуществующего пользователя")
    @Test
    public void getUserNotFoundTest() {
        given()
                .header("x-api-key", "reqres-free-v1")
                .when()
                .get("/users/999")
                .then()
                .log().status()
                .statusCode(404);
    }

    @DisplayName("Удалить пользователя")
    @Test
    public void deleteUserTest() {
        given()
                .header("x-api-key", "reqres-free-v1")
                .when()
                .delete("/api/users/2")
                .then()
                .log().status()
                .statusCode(204);
    }
}