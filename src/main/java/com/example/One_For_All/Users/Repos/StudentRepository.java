package com.example.One_For_All.Users.Repos;

import com.example.One_For_All.Users.model.Entities.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Students,Long> {

    Optional<Students> findById(Long studentId);
}
