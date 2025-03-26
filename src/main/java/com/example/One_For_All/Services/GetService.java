package com.example.One_For_All.Services;

import com.example.One_For_All.Entities.User;
import com.example.One_For_All.Repos.UserRepository;
import com.example.One_For_All.model.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetService {
    private final UserRepository userRepository;

    public GetService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<List<UserDTO>> execute(Void input){
        List<User> users= userRepository.findAll();
        List<UserDTO> userDTOS= users.stream().map(UserDTO::new).toList();

        return ResponseEntity.status(HttpStatus.OK).body(userDTOS);
    }

}
