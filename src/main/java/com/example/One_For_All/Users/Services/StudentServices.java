package com.example.One_For_All.Users.Services;

import com.example.One_For_All.Users.Repos.StudentRepository;
import com.example.One_For_All.Users.model.Entities.Students;
import com.example.One_For_All.Users.model.StudentDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServices {
    private final StudentRepository studentRepository;

    public StudentServices(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public Optional<Students> findById(Long id) {
        return studentRepository.findById(id);
    }

}


