package com.example.One_For_All.Users.model;

import com.example.One_For_All.Users.model.Entities.Students;
import com.example.One_For_All.exception.UserNotFoundException;
import lombok.Data;

@Data
public class StudentDTO {
    private String studentNumber;

    private String gradeLevel;

    private String department;

    private String name;

    private Long userId;
    private String email;

    public StudentDTO(Students students) {
        this.studentNumber = students.getStudentNumber();
        this.gradeLevel = students.getGradeLevel();
        this.department = students.getDepartment().getDeptname();
        this.name = students.getUser().getUsername();
        this.email= students.getUser().getEmail();
        this.userId= students.getUser().getId();

    }
}
