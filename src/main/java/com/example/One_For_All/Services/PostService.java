package com.example.One_For_All.Services;

import com.example.One_For_All.Entities.User;
import com.example.One_For_All.Repos.UserRepository;
import com.example.One_For_All.model.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final UserRepository userRepository;

    public PostService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<UserDTO> execute(User user){
        User saveduser= userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new UserDTO(saveduser));
    }

}
