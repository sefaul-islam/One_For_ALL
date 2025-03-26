package com.example.One_For_All.model;

import com.example.One_For_All.Entities.User;
import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String name;
    private String email;

    public UserDTO(User users) {
        this.id = users.getId();
        this.name = users.getName();
        this.email = users.getEmail();
    }
}
