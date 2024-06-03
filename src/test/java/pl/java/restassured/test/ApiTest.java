package pl.java.restassured.test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiTest {

    String pet = "{\n" +
            "  \"id\": 4,\n" +
            "  \"category\": {\n" +
            "    \"id\": 1,\n" +
            "    \"name\": \"cats\"\n" +
            "  },\n" +
            "  \"name\": \"Filip\",\n" +
            "  \"photoUrls\": [\n" +
            "    \"http://photos.com/dog1.jpg\"\n" +
            "  ],\n" +
            "  \"tags\": [\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"name\": \"dogs-category\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"status\": \"available\"\n" +
            "}";

    @Test
    public void firstTest() {
        given()
                .when().get("https://swaggerpetstore.przyklady.javastart.pl/v2/pet/0")
                .then().statusCode(404);
    }

    @Test
    public void sendPost() {
        given().log().uri().log().method().body(pet).contentType("application/json")
                .when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/pet")
                .then().log().all().statusCode(200);

    }
    @Test
    public void createUser() {
        String user = "{\n" +
                "  \"id\": 445,\n" +
                "  \"username\": \"firstuser\",\n" +
                "  \"firstName\": \"Krzysztof\",\n" +
                "  \"lastName\": \"Kowalski\",\n" +
                "  \"email\": \"krzysztof@test.com\",\n" +
                "  \"password\": \"password\",\n" +
                "  \"phone\": \"+123456789\",\n" +
                "  \"userStatus\": 1\n" +
                "}";

        given().log().all().body(user).contentType("application/json")
                .when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/user")
                .then().log().all().statusCode(200);

        given().log().uri().log().body().contentType("application/json")
                .pathParam("username", "firstuser")
                .when().get("https://swaggerpetstore.przyklady.javastart.pl/v2/user/{username}")
                .then().log().all().statusCode(200);
    }
    @Test
    public void sendGet() {
        given().log().all().contentType("application/json")
                .pathParam("petId", 4)
                .when().get("https://swaggerpetstore.przyklady.javastart.pl/v2/pet/{petId}")
                .then().log().all().statusCode(200);
    }

    @Test
    public void sentPut() {

        given().log().all().body(pet).contentType("application/json")
                .when().put("https://swaggerpetstore.przyklady.javastart.pl/v2/pet")
                .then().log().all().statusCode(200);
    }
}
