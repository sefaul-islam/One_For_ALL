package com.example.One_For_All.Users.model;

import com.example.One_For_All.Users.model.Entities.Users;
import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String name;
    private String email;

    public UserDTO(Users users) {
        this.id = users.getId();
        this.name = users.getUsername();
        this.email = users.getEmail();
    }
}
