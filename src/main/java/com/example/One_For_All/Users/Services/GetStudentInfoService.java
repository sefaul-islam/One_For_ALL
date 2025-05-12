package com.example.One_For_All.Users.Services;

import com.example.One_For_All.Users.Repos.StudentRepository;
import com.example.One_For_All.Users.model.Entities.Students;
import com.example.One_For_All.Users.model.StudentDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetStudentInfoService {

    private final StudentRepository studentRepository;

    public GetStudentInfoService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<List<StudentDTO>> execute (Void input){
        List<Students> students = studentRepository.findAll();
        List<StudentDTO> studentDTOS= students.stream().map(StudentDTO::new).toList();

        return ResponseEntity.status(HttpStatus.OK).body(studentDTOS);
    }
}
