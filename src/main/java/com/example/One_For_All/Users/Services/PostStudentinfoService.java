package com.example.One_For_All.Users.Services;

import com.example.One_For_All.Users.Repos.StudentRepository;
import com.example.One_For_All.Users.Repos.UserRepository;
import com.example.One_For_All.Users.model.Entities.Students;
import com.example.One_For_All.Users.model.Entities.Users;
import com.example.One_For_All.Users.model.StudentDTO;
import com.example.One_For_All.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostStudentinfoService {

    private final StudentRepository studentRepository;

    private final UserRepository userRepository;

    public PostStudentinfoService(StudentRepository studentRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<StudentDTO> execute (Students students){

         Users user = userRepository.findById(students.getUser().getId()).orElseThrow(()-> new UserNotFoundException());

         students.setUser(user);

         Students savestudents = studentRepository.save(students);

         return ResponseEntity.status(HttpStatus.CREATED).body(new StudentDTO(savestudents));
    }
}
