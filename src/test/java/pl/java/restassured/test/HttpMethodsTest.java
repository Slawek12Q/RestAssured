package pl.java.restassured.test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.java.restassured.pojo.Category;
import pl.java.restassured.pojo.Pet;
import pl.java.restassured.pojo.Tag;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class HttpMethodsTest {

    @BeforeClass
    public void setupConfiguration(){
        RestAssured.baseURI = "https://swaggerpetstore.przyklady.javastart.pl";
        RestAssured.basePath = "v2";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        RestAssured.requestSpecification = new RequestSpecBuilder().setContentType("application/json").build();
    }

    @Test
    public void sendPostRequest() {

        Category category = Category.builder().id(1).name("dogs").build();
        Tag tag = Tag.builder().id(1).name("dogs-category").build();
        Pet pet = Pet.builder().id(1231).category(category).photoUrls(Collections.singletonList("http://photos.com/dog1.jpg")).tags(Collections.singletonList(tag)).status("available").name("Azor").build();


        Pet responsePet = given().log().all().body(pet).contentType("application/json")
                .when().post("pet")
                .then().log().all().statusCode(200).extract().as(Pet.class);

        System.out.println(responsePet);
    }

    @Test
    public void sendPutRequest(){
        String pet = "{\n" +
                "  \"id\": 234,\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Bokser\"\n" +
                "  },\n" +
                "  \"name\": \"Brunon\",\n" +
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

        given().log().all().body(pet).contentType("application/json")
                .when().put("https://swaggerpetstore.przyklady.javastart.pl/v2/pet")
                .then().log().all().statusCode(200);
    }

    @Test
    public void sendDeleteRequest(){

        given().log().all().contentType("application/json")
                .pathParam("petId", 234)
                .when().delete("https://swaggerpetstore.przyklady.javastart.pl/v2/pet/{petId}")
                .then().log().all().statusCode(200);
    }

    @Test
    public void findByStatus() {

        Pet[] list =  given().log().all().contentType(("application/json"))
                .queryParam("status", "sold")
                .when().get("https://swaggerpetstore.przyklady.javastart.pl/v2/pet/findByStatus")
                .then().log().all().statusCode(200).extract().as(Pet[].class);

        List <Pet> petList= List.of(list);

        for (Pet pet: petList) {
            System.out.println(pet.getName() + " " + pet.getCategory().getName());
        }
    }

    @Test
    public void createNewAnimal() {
        Category category = Category.builder().id(12122).name("dogs").build();
        Tag tag = Tag.builder().id(1).name("dogs-category").build();
        Pet pet = Pet.builder().id(1231).category(category).photoUrls(Collections.singletonList("http://photos.com/dog1.jpg")).tags(Collections.singletonList(tag)).status("available").name("Brunon").build();

        Response response = given().body(pet)
                .when().post("pet")
                .then().assertThat().body("id", equalTo(1231), "category.id", equalTo(12122))
                .extract().response();

        System.out.println(response.getStatusCode());
    }
}
