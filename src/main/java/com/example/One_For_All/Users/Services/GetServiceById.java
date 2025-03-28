package com.example.One_For_All.Users.Services;

import com.example.One_For_All.Users.Entities.User;
import com.example.One_For_All.Users.Repos.UserRepository;
import com.example.One_For_All.Users.model.UserDTO;
import com.example.One_For_All.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetServiceById {
    private final UserRepository userRepository;

    public GetServiceById(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<UserDTO> execute (Integer id){
        Optional<User> optionalUser= userRepository.findById(id);
        if(optionalUser.isPresent()){
            return ResponseEntity.ok(new UserDTO(optionalUser.get()));
        }
        throw new UserNotFoundException();

    }
}
