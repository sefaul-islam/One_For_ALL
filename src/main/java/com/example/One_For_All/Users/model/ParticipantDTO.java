package com.example.One_For_All.Users.model;

import com.example.One_For_All.Users.model.Entities.ReserveCounselParticipants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantDTO {
    private Long id;
    private Long reserveCounselId;
    private String reserveCounselTitle;
    private Long studentId;
    private String studentName;
    private String studentNumber;
    private String status;
    private LocalDateTime joinedAt;

    public ParticipantDTO(ReserveCounselParticipants reserveCounselParticipants){
        this.id= reserveCounselParticipants.getId();
        this.reserveCounselId = reserveCounselParticipants.getReserveCounsel().getReserveCounselId();
        this.reserveCounselTitle = reserveCounselParticipants.getReserveCounsel().getTitle();
        this.studentId = reserveCounselParticipants.getStudent().getStudentId();
        this.studentName = reserveCounselParticipants.getStudent().getUser().getUsername();
        this.studentNumber = reserveCounselParticipants.getStudent().getStudentNumber();
        this.status = reserveCounselParticipants.getStatus().toString();
        this.joinedAt= reserveCounselParticipants.getJoinedAt();
    }
}
