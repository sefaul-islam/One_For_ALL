package com.example.One_For_All.Users.model;

import com.example.One_For_All.Users.model.Entities.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private Long id;
    private String deptname;

    public DepartmentDTO(Department department){
        this.id = department.getId();
        this.deptname = department.getDeptname();
    }
}
