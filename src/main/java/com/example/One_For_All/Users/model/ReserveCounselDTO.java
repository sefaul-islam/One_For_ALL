package com.example.One_For_All.Users.model;

import com.example.One_For_All.Users.model.Entities.ReserveCounsel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReserveCounselDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer maxParticipants;
    private Integer currentParticipants;
    private ReserveCounsel.Status status;
    private Long facultyId;
    private String facultyName;

    public ReserveCounselDTO(ReserveCounsel reserveCounsel) {
        this.id = reserveCounsel.getReserveCounselId();;
        this.title = reserveCounsel.getTitle();
        this.description = reserveCounsel.getDescription();
        this.startTime = reserveCounsel.getStartTime();
        this.endTime = reserveCounsel.getEndTime();
        this.maxParticipants = reserveCounsel.getMaxParticipants();
        this.currentParticipants = reserveCounsel.getCurrentParticipants();
        this.status = reserveCounsel.getStatus();
        this.facultyId = reserveCounsel.getFaculty().getFacultyId();
        this.facultyName = reserveCounsel.getFaculty().getUser().getUsername();
    }
}
