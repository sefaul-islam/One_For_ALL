package com.example.One_For_All.Users.Repos;

import com.example.One_For_All.Users.model.Entities.ReserveCounsel;
import com.example.One_For_All.Users.model.Entities.ReserveCounselParticipants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.One_For_All.Users.model.Entities.Students;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReserveCounselParticipantsRepository extends JpaRepository<ReserveCounselParticipants,Long> {

    //works like count function of aggregate count function in sql
    List<ReserveCounselParticipants> findByReserveCounsel(ReserveCounsel reserveCounsel);

    //to check whether the student has registered in the certain counsel
    Optional<ReserveCounselParticipants> findByReserveCounselAndStudent(ReserveCounsel reserveCounsel,Students student);

    List<ReserveCounselParticipants> findByStudent_StudentId(Long studentId);




}
