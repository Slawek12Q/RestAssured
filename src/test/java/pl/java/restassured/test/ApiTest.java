package pl.java.restassured.test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiTest {

    @Test
    public void sendGet() {
        given()
                .when().get("https://swaggerpetstore.przyklady.javastart.pl/v2/pet/0")
                .then().statusCode(404);
    }
}
