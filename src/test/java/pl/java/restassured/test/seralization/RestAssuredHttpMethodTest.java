package pl.java.restassured.test.seralization;

import org.testng.annotations.Test;
import pl.java.restassured.pojo.Category;
import pl.java.restassured.pojo.Pet;
import pl.java.restassured.pojo.Tag;
import pl.java.restassured.pojo.User;

import java.util.Collections;

import static io.restassured.RestAssured.given;

public class RestAssuredHttpMethodTest {
    Category category = Category.builder().name("dogs").id(1).build();
    Tag tag = Tag.builder().name("dogs-category").id(1).build();
    Pet reksioDog = Pet.builder().id(4).category(category).photoUrls(Collections.singletonList("http://photos.com/dog1.jpg")).tags(Collections.singletonList(tag)).status("available").name("Reksio").build();
    Pet burekDog = Pet.builder().id(5).category(Category.builder().name("doberman").id(2).build()).photoUrls(Collections.singletonList("http://photos.com/dog1.jpg")).tags(Collections.singletonList(tag)).status("available").name("Reksio").build();

    User user = User.builder().id(200).username("firsuser").firstName("Adam").lastName("Smith").email("adam.smith@gmail.com").password("123456").phone("+111000444").userStatus(100).build();
    @Test
    public void sendPostPet() {

        given().log().all().body(reksioDog).contentType("application/json")
                .when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/pet")
                .then().log().all().statusCode(200);
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
}
