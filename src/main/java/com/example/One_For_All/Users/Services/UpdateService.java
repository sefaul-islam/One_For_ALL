package com.example.One_For_All.Users.Services;

import com.example.One_For_All.Users.Entities.User;
import com.example.One_For_All.Users.Repos.UserRepository;
import com.example.One_For_All.Users.model.UpdateUserCommand;
import com.example.One_For_All.Users.model.UserDTO;
import com.example.One_For_All.exception.UserNotFoundException;
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
