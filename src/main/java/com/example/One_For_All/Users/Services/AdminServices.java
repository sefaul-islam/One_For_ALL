package com.example.One_For_All.Users.Services;

import com.example.One_For_All.Enums.UserRole;
import com.example.One_For_All.Users.Repos.FacultyRepository;
import com.example.One_For_All.Users.Repos.StudentRepository;
import com.example.One_For_All.Users.Repos.UserRepository;
import com.example.One_For_All.Users.model.CreateFacultyDTO;
import com.example.One_For_All.Users.model.CreateStudentDTO;
import com.example.One_For_All.Users.model.Entities.Faculty;
import com.example.One_For_All.Users.model.Entities.Students;
import com.example.One_For_All.Users.model.Entities.Users;
import com.example.One_For_All.Users.model.FacultyDTO;
import com.example.One_For_All.Users.model.StudentDTO;
import com.example.One_For_All.exception.InvalidOperationException;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServices {

    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder bCryptPasswordEncoder;

    public AdminServices(FacultyRepository facultyRepository, StudentRepository studentRepository, UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public FacultyDTO createFaculty(CreateFacultyDTO createFacultyDTO){
        if (userRepository.findByUsername(createFacultyDTO.getUsername()).isPresent()) {
            throw new InvalidOperationException("Username already exists");
        }

        if (userRepository.findByEmail(createFacultyDTO.getEmail()).isPresent()) {
            throw new InvalidOperationException("Email already exists");
        }
        Users user = new Users();
        user.setUsername(createFacultyDTO.getUsername());
        user.setEmail(createFacultyDTO.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(createFacultyDTO.getPassword()));
        user.setRole(UserRole.FACULTY);
        Users saveduser  = userRepository.save(user);

        Faculty faculty = new Faculty();
        faculty.setUser(saveduser);
        faculty.setDepartment(createFacultyDTO.getDepartment());
        faculty.setAcademicTitle(createFacultyDTO.getAcademicTitle());
        faculty.setContactNumber(createFacultyDTO.getContactNumber());
        Faculty savedfaculty = facultyRepository.save(faculty);

        return new FacultyDTO(savedfaculty);

    }


    public StudentDTO createStudent(CreateStudentDTO createStudentDTO){
        if (userRepository.findByUsername(createStudentDTO.getUsername()).isPresent()) {
            throw new InvalidOperationException("Username already exists");
        }
        if (userRepository.findByEmail(createStudentDTO.getEmail()).isPresent()) {
            throw new InvalidOperationException("Email already exists");
        }
        Users user = new Users();
        user.setUsername(createStudentDTO.getUsername());
        user.setEmail(createStudentDTO.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(createStudentDTO.getPassword()));
        user.setRole(UserRole.STUDENT);
        Users saveduser  = userRepository.save(user);

        Students students = new Students();
        students.setUser(saveduser);
        students.setStudentNumber(createStudentDTO.getStudentNumber());
        students.setGradeLevel(createStudentDTO.getGradeLevel());
        students.setMajor(createStudentDTO.getMajor());
        Students savedStudents = studentRepository.save(students);

        return new StudentDTO(savedStudents);

    }

}
