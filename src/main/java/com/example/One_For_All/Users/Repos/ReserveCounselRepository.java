package com.example.One_For_All.Users.Repos;

import com.example.One_For_All.Users.model.Entities.Faculty;
import com.example.One_For_All.Users.model.Entities.ReserveCounsel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReserveCounselRepository extends JpaRepository<ReserveCounsel,Long> {

    List<ReserveCounsel> findByFaculty(Faculty faculty);
}
