package com.example.One_For_All.Users.Repos;

import com.example.One_For_All.Users.model.Entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository  extends JpaRepository<Department,Long> {

    Optional<Department> findByid(long dept_id);
    Optional<Department> findByDeptname(String deptName);
    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM Department d WHERE LOWER(d.deptname) = LOWER(:deptname)")
    boolean existsByDeptnameIgnoreCase(@Param("deptname") String deptname);

}
