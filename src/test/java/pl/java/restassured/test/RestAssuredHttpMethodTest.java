package pl.java.restassured.test;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.java.restassured.pojo.Category;
import pl.java.restassured.pojo.Pet;
import pl.java.restassured.pojo.Tag;
import pl.java.restassured.pojo.User;

import java.util.Collections;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;
public class RestAssuredHttpMethodTest {

    @BeforeClass
    public void setupConfiguration() {
        RestAssured.baseURI = "https://swaggerpetstore.przyklady.javastart.pl";
        RestAssured.basePath = "v2";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    Category category = Category.builder().name("dogs").id(1).build();
    Tag tag = Tag.builder().name("dogs-category").id(1).build();
    Pet reksioDog = Pet.builder().id(4).category(category).photoUrls(Collections.singletonList("http://photos.com/dog1.jpg")).tags(Collections.singletonList(tag)).status("available").name("Reksio").build();
    Pet burekDog = Pet.builder().id(5).category(Category.builder().name("doberman").id(2).build()).photoUrls(Collections.singletonList("http://photos.com/dog1.jpg")).tags(Collections.singletonList(tag)).status("available").name("Reksio").build();

    User user = User.builder().id(200).username("firsuser").firstName("Adam").lastName("Smith").email("adam.smith@gmail.com").password("123456").phone("+111000444").userStatus(100).build();

    @Test
    public void sendPostPet() {

        String tagName = given().log().all().body(reksioDog).contentType("application/json")
                .when().post("pet")
                .then().log().all().statusCode(200).extract().jsonPath().getString("tags[0].name");

        System.out.println("Tag name: " + tagName);
    }

    @Test
    public void sendPostUser() {

        given().log().all().body(user).contentType("application/json")
                .when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/user")
                .then().log().all().statusCode(200);

        given().log().all().contentType("application/json")
                .pathParam("username", "firsuser")
                .when().get("https://swaggerpetstore.przyklady.javastart.pl/v2/user/{username}")
                .then().log().all().statusCode(200);
    }

    @Test
    public void createNewPetAndCheckResponse() {
        Response response = given().body(burekDog).contentType("application/json")
                .when().post("pet")
                .then().extract().response();

        int statusCode = response.getStatusCode();
        String statusLine = response.getStatusLine();
        Headers responseHeaders = response.getHeaders();
        Map<String, String> cookies = response.getCookies();

        assertEquals(statusLine, "HTTP/1.1 200 OK", "Status line");
        assertEquals(statusCode, 200, "Status code");
        assertNotNull(responseHeaders.get("Date"));
        assertEquals(responseHeaders.get("Content-Type").getValue(), "application/json", "Content type header");
        assertEquals(responseHeaders.get("Server").getValue(), "openresty", "Server header");
        assertTrue(cookies.isEmpty(), "Cookies are empty");
    }
}
