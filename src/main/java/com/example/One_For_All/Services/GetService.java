package com.example.One_For_All.Services;

import com.example.One_For_All.Repos.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class GetService {
    private final StudentRepository studentRepository;

    public GetService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

}
