package com.example.One_For_All.Users.controllers;

import com.example.One_For_All.Users.Services.GetStudentInfoService;
import com.example.One_For_All.Users.Services.PostStudentinfoService;
import com.example.One_For_All.Users.model.Entities.Students;
import com.example.One_For_All.Users.model.StudentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final GetStudentInfoService getStudentInfoService;
    private final PostStudentinfoService postStudentinfoService;

    public AdminController(GetStudentInfoService getStudentInfoService, PostStudentinfoService postStudentinfoService) {
        this.getStudentInfoService = getStudentInfoService;
        this.postStudentinfoService=postStudentinfoService;
    }
    @GetMapping("/getStudentinfo")
    public ResponseEntity<List<StudentDTO>> getStudentInfo(){
        return getStudentInfoService.execute(null);
    }

    @PostMapping("/postStudentInfo")
    public ResponseEntity<StudentDTO> postStudentinfo(@RequestBody Students students){
        return postStudentinfoService.execute(students);
    }

}
