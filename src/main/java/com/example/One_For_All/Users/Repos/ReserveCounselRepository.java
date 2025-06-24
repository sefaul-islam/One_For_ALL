package com.example.One_For_All.Users.Repos;

import com.example.One_For_All.Users.model.Entities.Faculty;
import com.example.One_For_All.Users.model.Entities.ReserveCounsel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReserveCounselRepository extends JpaRepository<ReserveCounsel,Long> {

    List<ReserveCounsel> findByFaculty(Faculty faculty);

    @Query("SELECT rc FROM ReserveCounsel rc WHERE rc.startTime <= :currentTime AND rc.endTime > :currentTime")
    List<ReserveCounsel> findActive(@Param("currentTime") LocalDateTime currentTime);
    List<ReserveCounsel> findByEndTimeBeforeAndStatusNot(LocalDateTime now, ReserveCounsel.Status status);
    List<ReserveCounsel> findByStartTimeBeforeAndStatus(LocalDateTime now, ReserveCounsel.Status status);


}
