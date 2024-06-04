package pl.java.restassured.pojo;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class Pet {

    public Integer id;
    public Category category;
    public String name;
    public List<String> photoUrls;
    public List<Tag> tags;
    public String status;

}