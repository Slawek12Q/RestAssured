package pl.java.restassured.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Tag {

    public Integer id;
    public String name;
}
