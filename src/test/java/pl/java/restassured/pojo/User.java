package pl.java.restassured.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    public Integer id;
    public String username;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String phone;
    public Integer userStatus;

}