package com.example.One_For_All.Users.model;

import com.example.One_For_All.Users.model.Entities.Users;
import lombok.Data;

@Data

public class LoginReq {
    private String email;
    private String password;

    public LoginReq (Users users){
        this.email= users.getEmail();
        this.password= users.getPassword();
    }
}
