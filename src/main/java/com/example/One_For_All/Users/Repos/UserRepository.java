package com.example.One_For_All.Users.Repos;

import com.example.One_For_All.Users.model.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByUsername(String username);
    Optional<Users> findByEmail(String email);
}
