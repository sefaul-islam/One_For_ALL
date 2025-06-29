package com.example.One_For_All.Users.Services;

import com.example.One_For_All.Enums.UserRole;
import com.example.One_For_All.Users.Repos.*;
import com.example.One_For_All.Users.model.*;
import com.example.One_For_All.Users.model.Entities.*;
import com.example.One_For_All.exception.InvalidOperationException;
import com.example.One_For_All.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServices {

    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder bCryptPasswordEncoder;
    private final AdminRepository adminRepository;
    private final DepartmentRepository departmentRepository;

    public AdminServices(FacultyRepository facultyRepository, StudentRepository studentRepository, UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder, AdminRepository adminRepository,
                         DepartmentRepository departmentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.adminRepository = adminRepository;
        this.departmentRepository = departmentRepository;
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

        Department department = departmentRepository
                .findByDeptname(createFacultyDTO.getDepartment())
                .orElseGet(() -> {
                    Department newDept = new Department();
                    newDept.setDeptname(createFacultyDTO.getDepartment());
                    return departmentRepository.save(newDept);
                });

        Faculty faculty = new Faculty();
        faculty.setUser(saveduser);
        faculty.setDepartment(department);
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

        Department department = departmentRepository
                .findByDeptname(createStudentDTO.getDept())
                .orElseGet(() -> {
                    Department newDept = new Department();
                    newDept.setDeptname(createStudentDTO.getDept());
                    return departmentRepository.save(newDept);
                });
        Students students = new Students();
        students.setUser(saveduser);
        students.setDepartment(department);
        students.setStudentNumber(createStudentDTO.getStudentNumber());
        students.setGradeLevel(createStudentDTO.getGradeLevel());
        Students savedStudents = studentRepository.save(students);

        return new StudentDTO(savedStudents);

    }

    public AdminDTO createAdmin(CreateAdminDTO createAdminDTO){
        if (userRepository.findByUsername(createAdminDTO.getUsername()).isPresent()) {
            throw new InvalidOperationException("Username already exists");
        }
        if (userRepository.findByEmail(createAdminDTO.getEmail()).isPresent()) {
            throw new InvalidOperationException("Email already exists");
        }
        Users user = new Users();
        user.setUsername(createAdminDTO.getUsername());
        user.setEmail(createAdminDTO.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(createAdminDTO.getPassword()));
        user.setRole(UserRole.ADMIN);
        Users saveduser  = userRepository.save(user);

        Admin admin = new Admin();
        admin.setUser(saveduser);
        admin.setTitle(createAdminDTO.getTitle());
        Admin savedadmin= adminRepository.save(admin);
        return new AdminDTO(savedadmin);
    }

    public List<StudentDTO> getAllStudents(){
        List<Students> students = studentRepository.findAll();
        List<StudentDTO> studentDTOS= students.stream().map(StudentDTO::new).toList();
        return studentDTOS;
    }

    public List<FacultyDTO> getAllfaculties(){
        List<Faculty> faculties = facultyRepository.findAll();
        List<FacultyDTO> facultyDTOS= faculties.stream().map(FacultyDTO::new).toList();
        return facultyDTOS;
    }

    public void deleteFacultyById(Long id) {
        Faculty faculty = facultyRepository.findByUser_Id(id)
                .orElseThrow(() -> new UserNotFoundException());
        Users user = faculty.getUser();
        facultyRepository.delete(faculty);
        if (user != null) {
            userRepository.delete(user);
        }
    }

    public void deleteStudentyById(Long id) {
        Students students = studentRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());
        Users users = students.getUser();

        studentRepository.delete(students);
    if(users != null){
        userRepository.delete(users);
    }
    }




}
