package com.example.One_For_All.Users.model;

import com.example.One_For_All.Users.model.Entities.ReserveCounsel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReserveCounselRequest {

    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer maxParticipants;

    public CreateReserveCounselRequest(ReserveCounsel reserveCounsel){
        this.title = reserveCounsel.getTitle();
        this.description = reserveCounsel.getDescription();
        this.startTime = reserveCounsel.getStartTime();
        this.endTime = reserveCounsel.getEndTime();
        this.maxParticipants = reserveCounsel.getMaxParticipants();

    }
}
