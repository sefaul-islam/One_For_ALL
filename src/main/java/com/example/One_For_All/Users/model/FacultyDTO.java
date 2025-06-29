package com.example.One_For_All.Users.model;

import com.example.One_For_All.Users.model.Entities.Faculty;
import lombok.Data;

@Data
public class FacultyDTO {
    private Long facultyId;
    private String department;
    private String academicTitle;
    private String contactNumber;
    private Long userId;
    private String username;
    private String email;

    public FacultyDTO(Faculty faculty) {
        this.facultyId = faculty.getFacultyId();
        this.department = faculty.getDepartment().getDeptname();
        this.academicTitle = faculty.getAcademicTitle();
        this.contactNumber = faculty.getContactNumber();
        this.userId = faculty.getUser().getId();
        this.username = faculty.getUser().getUsername();
        this.email = faculty.getUser().getEmail();
    }
}
