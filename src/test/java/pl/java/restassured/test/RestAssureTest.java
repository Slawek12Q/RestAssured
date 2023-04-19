package pl.java.restassured.test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RestAssureTest {

    @Test
    public void test(){

        given().log().all().get("https://swaggerpetstore.przyklady.javastart.pl/v2/pet/234").then().log().all().statusCode(200);
    }
}
