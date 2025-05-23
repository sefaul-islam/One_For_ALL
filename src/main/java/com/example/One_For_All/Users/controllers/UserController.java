package com.example.One_For_All.Users.controllers;


import com.example.One_For_All.Users.model.Entities.Users;
import com.example.One_For_All.Users.Services.*;
import com.example.One_For_All.Users.model.UpdateUserCommand;
import com.example.One_For_All.Users.model.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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


       @PostMapping("/createuser")
        public ResponseEntity<UserDTO> postInfoController(@RequestBody Users user){

                 return postService.execute(user);
       }


        @PutMapping("/updateuserinfo/{id}")
        public ResponseEntity<UserDTO> updateInfoController(@RequestBody Users user , @PathVariable Long id){
            return updateService.execute(new UpdateUserCommand(id,user));
        }


        @DeleteMapping("/deleteuserinfo/{id}")
        public ResponseEntity<Void> deleteInfoController(@PathVariable Long id){
           return  deleteService.execute(id);
        }


        @GetMapping("getinfobyid/{id}")
        public ResponseEntity<UserDTO> getInfoById(@PathVariable Long id){
            return getServiceById.execute(id);
        }

}
