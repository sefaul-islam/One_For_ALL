package com.example.One_For_All.Users.controllers;

import com.example.One_For_All.Users.Services.ReserveCounselParticipantsService;
import com.example.One_For_All.Users.model.ParticipantDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservecounselparticipant")
public class ReserveCounselParticipantController {
    private final ReserveCounselParticipantsService reserveCounselParticipantsService;

    public ReserveCounselParticipantController(ReserveCounselParticipantsService reserveCounselParticipantsService) {
        this.reserveCounselParticipantsService = reserveCounselParticipantsService;
    }


    @PostMapping("/{reserveCounselId}/register/{studentId}")
    public ResponseEntity<ParticipantDTO> registerStudent(
            @PathVariable Long reserveCounselId,
            @PathVariable Long studentId) {
        return ResponseEntity.ok(reserveCounselParticipantsService.registerStudent(reserveCounselId, studentId));
    }

}
