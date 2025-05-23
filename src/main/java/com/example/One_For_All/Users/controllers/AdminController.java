package com.example.One_For_All.Users.controllers;

import com.example.One_For_All.Users.Services.AdminServices;
import com.example.One_For_All.Users.model.CreateFacultyDTO;
import com.example.One_For_All.Users.model.CreateStudentDTO;
import com.example.One_For_All.Users.model.Entities.Students;
import com.example.One_For_All.Users.model.FacultyDTO;
import com.example.One_For_All.Users.model.StudentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminServices adminServices;

    public AdminController(AdminServices adminServices) {
        this.adminServices = adminServices;
    }

    @PostMapping("/createfaculty")
    public ResponseEntity<FacultyDTO> createfaculty(@RequestBody CreateFacultyDTO createFacultyDTO){

        return ResponseEntity.status(HttpStatus.CREATED).body(adminServices.createFaculty(createFacultyDTO));
    }

    @PostMapping("/createstudent")
    public ResponseEntity<StudentDTO> createstudent(@RequestBody CreateStudentDTO createStudentDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(adminServices.createStudent(createStudentDTO));
    }

}
