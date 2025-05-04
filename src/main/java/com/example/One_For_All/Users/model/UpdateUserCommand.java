package com.example.One_For_All.Users.model;

import com.example.One_For_All.Users.model.Entities.Users;
import lombok.Getter;

@Getter
public class UpdateUserCommand {
    private Integer id;

    private Users user;

    public UpdateUserCommand(Integer id, Users user) {
        this.id = id;
        this.user = user;
    }
}
