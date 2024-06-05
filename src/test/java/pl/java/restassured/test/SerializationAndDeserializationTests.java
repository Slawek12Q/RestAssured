package pl.java.restassured.test;

import org.testng.annotations.Test;
import pl.java.restassured.pojo.Category;
import pl.java.restassured.pojo.Pet;
import pl.java.restassured.pojo.Tag;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class SerializationAndDeserializationTests {

    @Test
    public void createNewPet() {

        Category category = Category.builder().name("dogs").id(1).build();
        Tag tag = Tag.builder().name("dogs-category").id(1).build();
        Pet pet = Pet.builder().id(4).category(category).photoUrls(Collections.singletonList("http://photos.com/dog1.jpg")).tags(Collections.singletonList(tag)).status("available").name("Reksio").build();


        Pet actualPet = given().log().all().body(pet).contentType("application/json")
                .when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/pet")
                .then().log().all().statusCode(200).extract().as(Pet.class);

        assertEquals(actualPet.getId(), pet.getId(), "Pet id");
        assertEquals(actualPet.getCategory().getId(), pet.getCategory().getId(), "Category id");
        assertEquals(actualPet.getCategory().getName(), pet.getCategory().getName(), "Category name");
        assertEquals(actualPet.getPhotoUrls().get(0), pet.getPhotoUrls().get(0), "Photo URL");
        assertEquals(actualPet.getTags().get(0).getId(), pet.getTags().get(0).getId(), "Pet tag id");
        assertEquals(actualPet.getTags().get(0).getName(), pet.getTags().get(0).getName(), "Pet tag name");
        assertEquals(actualPet.getStatus(), pet.getStatus(), "Pet status");
    }

}

