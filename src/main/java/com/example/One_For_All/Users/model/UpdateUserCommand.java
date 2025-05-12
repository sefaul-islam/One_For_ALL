package com.example.One_For_All.Users.model;

import com.example.One_For_All.Users.model.Entities.Users;
import lombok.Getter;

@Getter
public class UpdateUserCommand {
    private Long id;

    private Users user;

    public UpdateUserCommand(Long id, Users user) {
        this.id = id;
        this.user = user;
    }
}
