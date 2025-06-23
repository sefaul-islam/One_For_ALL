package com.example.One_For_All.Users.Repos;

import com.example.One_For_All.Users.model.Entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findByUser_Id(Long user_id);
}
