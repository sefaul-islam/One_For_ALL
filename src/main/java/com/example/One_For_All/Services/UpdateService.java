package com.example.One_For_All.Services;

import com.example.One_For_All.Entities.User;
import com.example.One_For_All.Repos.UserRepository;
import com.example.One_For_All.exception.UserNotFoundException;
import com.example.One_For_All.model.UpdateUserCommand;
import com.example.One_For_All.model.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateService {
    private final UserRepository userRepository;

    public UpdateService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public ResponseEntity<UserDTO> execute (UpdateUserCommand command){
        Optional<User> optionalUser = userRepository.findById(command.getId());
        if(optionalUser.isPresent()){
            User user = command.getUser();
            user.setId(command.getId());
            userRepository.save(user);
            return ResponseEntity.ok(new UserDTO(user));

        }
        throw new UserNotFoundException();
    }
}
