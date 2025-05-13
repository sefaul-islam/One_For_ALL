package com.example.One_For_All.Users.model;

import com.example.One_For_All.Users.model.Entities.Students;
import com.example.One_For_All.exception.UserNotFoundException;
import lombok.Data;

@Data
public class StudentDTO {
    private String studentNumber;

    private String gradeLevel;

    private String major;

    private String name;

    private String email;

    public StudentDTO(Students students) {
        this.studentNumber = students.getStudentNumber();
        this.gradeLevel = students.getGradeLevel();
        this.major = students.getMajor();
        this.name = students.getUser().getUsername();
        this.email= students.getUser().getEmail();

    }
}
