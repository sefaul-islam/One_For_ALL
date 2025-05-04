package com.example.One_For_All.Users.Services;

import com.example.One_For_All.Users.model.Entities.Users;
import com.example.One_For_All.Users.Repos.UserRepository;
import com.example.One_For_All.Users.model.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final UserRepository userRepository;

    public PostService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<UserDTO> execute(Users user){
        Users saveduser= userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new UserDTO(saveduser));
    }

}
