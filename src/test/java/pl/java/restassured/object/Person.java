package pl.java.restassured.object;


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

