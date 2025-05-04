package com.example.One_For_All.Users.Services;

import com.example.One_For_All.Users.model.Entities.Users;
import com.example.One_For_All.Users.Repos.UserRepository;
import com.example.One_For_All.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteService {
    private final UserRepository userRepository;

    public DeleteService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<Void> execute(Integer id){
        Optional<Users> userOptional= userRepository.findById(id);
        if(userOptional.isPresent()){
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        throw new UserNotFoundException();
    }
}
