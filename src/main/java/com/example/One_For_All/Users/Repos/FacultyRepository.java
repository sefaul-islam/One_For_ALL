package com.example.One_For_All.Users.Repos;

import com.example.One_For_All.Users.model.Entities.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty,Long> {
    Optional<Faculty> findByUser_Id(Long userId);
}
