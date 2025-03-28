package com.example.One_For_All.Users.model;

import com.example.One_For_All.Users.Entities.User;
import lombok.Getter;

@Getter
public class UpdateUserCommand {
    private Integer id;

    private User user;

    public UpdateUserCommand(Integer id, User user) {
        this.id = id;
        this.user = user;
    }
}
