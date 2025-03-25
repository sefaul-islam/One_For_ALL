package com.example.One_For_All.Controllers;


import com.example.One_For_All.Repos.StudentRepository;
import com.example.One_For_All.Services.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final GetService getService;

    private final UpdateService updateService;

    private final PostService postService;

    private final DeleteService deleteService;

    private final GetServiceById getServiceById;

    public StudentController(GetService getService, UpdateService updateService, PostService postService, DeleteService deleteService, GetServiceById getServiceById) {
        this.getService = getService;
        this.updateService = updateService;
        this.postService = postService;
        this.deleteService = deleteService;
        this.getServiceById = getServiceById;
    }

    private  StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    //      @GetMapping("/getuserinfo")
//
//
//      @PostMapping
//
//
//      @PutMapping
//
//
//      @DeleteMapping
//
//
//      @GetMapping

}
