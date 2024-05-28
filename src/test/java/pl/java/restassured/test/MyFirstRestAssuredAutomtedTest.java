package pl.java.restassured.test;


import pl.java.restassured.object.Car;
import pl.java.restassured.object.Person;

public class MyFirstRestAssuredAutomtedTest {
    public static void main (String [] args){


        Person person = new Person("Michal", "Czerwinski", 22, true);
        Person person1 = Person.builder().build();

        Car car = new Car("Fiat");

    }

}
