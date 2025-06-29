package com.example.One_For_All.Users.controllers;

import com.example.One_For_All.Users.Repos.StudentRepository;
import com.example.One_For_All.Users.Services.AdminServices;
import com.example.One_For_All.Users.model.*;
import com.example.One_For_All.Users.model.Entities.Faculty;
import com.example.One_For_All.Users.model.Entities.Students;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminServices adminServices;
    private final StudentRepository studentRepository;

    public AdminController(AdminServices adminServices, StudentRepository studentRepository) {
        this.adminServices = adminServices;
        this.studentRepository = studentRepository;
    }

    @PostMapping("/createfaculty")
    public ResponseEntity<FacultyDTO> createfaculty(@RequestBody CreateFacultyDTO createFacultyDTO){

        return ResponseEntity.status(HttpStatus.CREATED).body(adminServices.createFaculty(createFacultyDTO));
    }

    @PostMapping("/createstudent")
    public ResponseEntity<StudentDTO> createstudent(@RequestBody CreateStudentDTO createStudentDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(adminServices.createStudent(createStudentDTO));
    }
    @PostMapping("/createadmin")
    public ResponseEntity<AdminDTO> createAdmin(@RequestBody CreateAdminDTO createAdminDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(adminServices.createAdmin(createAdminDTO));
    }

    @GetMapping("/allstudents")
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = adminServices.getAllStudents();
        return ResponseEntity.ok(students);
    }


    @GetMapping("/allfaculties")
    public ResponseEntity<List<FacultyDTO>> getAllFaculties() {
        List<FacultyDTO> facultyDTOS = adminServices.getAllfaculties();
        return ResponseEntity.ok(facultyDTOS);
    }

    @DeleteMapping("/{facultyId}/deletefaculty")
    public ResponseEntity<?> deletefacultyById (@PathVariable Long facultyId){
        adminServices.deleteFacultyById(facultyId);
        return  ResponseEntity.ok("Faculty Deleted Successfully");
    }

    @DeleteMapping ("/{studentId}/deletestudentbyid")
    public ResponseEntity<?> deletestudentbyid(@PathVariable Long studentId){
        adminServices.deleteStudentyById(studentId);
        return ResponseEntity.ok("Student Deleted Succesfully");
    }





}
