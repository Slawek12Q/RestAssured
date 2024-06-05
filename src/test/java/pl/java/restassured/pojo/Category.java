package pl.java.restassured.pojo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class Category {

    public Integer id;
    public String name;

}