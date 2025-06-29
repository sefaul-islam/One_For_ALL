package com.example.One_For_All.Users.controllers;

import com.example.One_For_All.Users.Services.DepartmentService;
import com.example.One_For_All.Users.model.CreateDepartmentDTO;
import com.example.One_For_All.Users.model.DepartmentDTO;
import com.example.One_For_All.Users.model.Entities.Department;
import com.example.One_For_All.Users.model.Entities.Faculty;
import com.example.One_For_All.Users.model.FacultyDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/department/departmentlist")
    public ResponseEntity<List<DepartmentDTO>> getAll() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{departmentid}/faculties")
    public ResponseEntity<List<FacultyDTO>> getFacultiesByDepartment(@PathVariable Long departmentid) {
        return ResponseEntity.ok(departmentService.getFacultiesByDepartmentId(departmentid));
    }

    @PostMapping ("/department/adddepartment")
    public ResponseEntity<DepartmentDTO> addDepartment(@RequestBody CreateDepartmentDTO createDepartmentDTO){
        return  ResponseEntity.status(HttpStatus.CREATED).body(  departmentService.createDepartment(createDepartmentDTO));
    }
}
