package com.example.One_For_All.Users.model;

import com.example.One_For_All.Enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentDTO {
    private String username;
    private String email;
    private String password;
    private UserRole role;
    private String studentNumber;
    private  String gradeLevel;
    private String major;
}
