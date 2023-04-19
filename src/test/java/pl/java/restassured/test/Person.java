package pl.java.restassured.test;


import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String name;
    private String surname;
    private int age;
    private boolean isMarried;

}

@RequiredArgsConstructor
class Car {
    private final String brand;
    private int age;
}
