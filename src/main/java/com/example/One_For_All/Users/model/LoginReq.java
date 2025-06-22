package com.example.One_For_All.Users.model;

import com.example.One_For_All.Users.model.Entities.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LoginReq {
    private String username;
    private String password;

}
