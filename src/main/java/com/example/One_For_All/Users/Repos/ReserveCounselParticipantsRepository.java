package com.example.One_For_All.Users.Repos;

import com.example.One_For_All.Users.model.Entities.ReserveCounsel;
import com.example.One_For_All.Users.model.Entities.ReserveCounselParticipants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReserveCounselParticipantsRepository extends JpaRepository<ReserveCounselParticipants,Long> {

    //works like count function of aggregate count function in sql
    List<ReserveCounselParticipants> findByReserveCounsel(ReserveCounsel reserveCounsel);
}
