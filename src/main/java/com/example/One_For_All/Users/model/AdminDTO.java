package com.example.One_For_All.Users.model;

import com.example.One_For_All.Users.model.Entities.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminDTO {
    private Long userId;
    private String username;
    private String title;

    public AdminDTO(Admin admin){
        userId=admin.getUser().getId();
        username=admin.getUser().getUsername();
        title=admin.getTitle();
    }

}
