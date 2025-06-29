package com.example.One_For_All.Users.Services;

import com.example.One_For_All.Users.Repos.DepartmentRepository;
import com.example.One_For_All.Users.Repos.FacultyRepository;
import com.example.One_For_All.Users.model.CreateDepartmentDTO;
import com.example.One_For_All.Users.model.DepartmentDTO;
import com.example.One_For_All.Users.model.Entities.Department;
import com.example.One_For_All.Users.model.Entities.Faculty;
import com.example.One_For_All.Users.model.FacultyDTO;
import com.example.One_For_All.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
   private final DepartmentRepository departmentRepository;
   private final FacultyRepository facultyRepository;

    public DepartmentService(DepartmentRepository departmentRepository, FacultyRepository facultyRepository) {
        this.departmentRepository = departmentRepository;
        this.facultyRepository = facultyRepository;
    }

    public DepartmentDTO createDepartment(CreateDepartmentDTO createDepartmentDTO) {
        String trimmedName = createDepartmentDTO.getDeptname().trim();

        if (departmentRepository.existsByDeptnameIgnoreCase(trimmedName)) {
            throw new IllegalArgumentException("Department with this name already exists.");
        }

        Department department = new Department();
        department.setDeptname(trimmedName);

        Department saved = departmentRepository.save(department);

        System.out.println("Saved department: id=" + saved.getId() + ", name=" + saved.getDeptname());

        return new DepartmentDTO(saved);
    }


    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll().stream().map(dept-> new DepartmentDTO(dept.getId(),dept.getDeptname())).collect(Collectors.toList());
    }

    public DepartmentDTO getDepartmentById(Long id) {
       Department department = departmentRepository.findByid(id).orElseThrow(()->new UserNotFoundException());
       return new DepartmentDTO(department);
    }

    public List<FacultyDTO> getFacultiesByDepartmentId(Long id) {
        Department department = departmentRepository.findByid(id).orElseThrow(()->new UserNotFoundException());
        List<Faculty> faculty = facultyRepository.findByDepartment_Id(id);

        return faculty.stream().map(f-> new FacultyDTO(f)).collect(Collectors.toList());
    }

}
