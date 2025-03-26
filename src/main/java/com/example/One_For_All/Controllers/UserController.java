package com.example.One_For_All.Controllers;


import com.example.One_For_All.Services.*;
import com.example.One_For_All.model.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class UserController {

    private final GetService getService;

    private final UpdateService updateService;

    private final PostService postService;

    private final DeleteService deleteService;

    private final GetServiceById getServiceById;


    public UserController(GetService getService, UpdateService updateService, PostService postService, DeleteService deleteService, GetServiceById getServiceById) {
        this.getService = getService;
        this.updateService = updateService;
        this.postService = postService;
        this.deleteService = deleteService;
        this.getServiceById = getServiceById;
    }
       @GetMapping("/getuserinfo")
          public ResponseEntity<List<UserDTO>> getInfoController(){

           return getService.execute(null);
       }


       @PostMapping
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
