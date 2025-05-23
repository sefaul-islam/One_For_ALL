package com.example.One_For_All.Users.controllers;

import com.example.One_For_All.Users.Services.ReserveCounselParticipantsService;
import com.example.One_For_All.Users.Services.ReserveCounselService;
import com.example.One_For_All.Users.model.CreateReserveCounselRequest;
import com.example.One_For_All.Users.model.ParticipantDTO;
import com.example.One_For_All.Users.model.ReserveCounselDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reserve-counsels")
public class ReserveCounselController {

    private final ReserveCounselService reserveCounselService;
    private final ReserveCounselParticipantsService reserveCounselParticipantsService;

    public ReserveCounselController(ReserveCounselService reserveCounselService, ReserveCounselParticipantsService reserveCounselParticipantsService) {
        this.reserveCounselService = reserveCounselService;
        this.reserveCounselParticipantsService = reserveCounselParticipantsService;
    }

    @PostMapping("/faculty/{facultyId}")
    public ResponseEntity<ReserveCounselDTO> createReserveCounsel(
            @PathVariable Long facultyId,
            @RequestBody CreateReserveCounselRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body( reserveCounselService.createReserveCounsel(request, facultyId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReserveCounselDTO> getReserveCounselById(@PathVariable Long id) {
        return ResponseEntity.ok(reserveCounselService.getReserveCounselById(id));
    }

    @GetMapping("/faculty/{facultyId}")
    public ResponseEntity<List<ReserveCounselDTO>> getReserveCounselsByFaculty(@PathVariable Long facultyId) {
        return ResponseEntity.ok(reserveCounselService.getReserveCounselsByFaculty(facultyId));
    }
    @DeleteMapping("/{id}/faculty/{facultyId}")
    public ResponseEntity<Void> deleteReserveCounsel(
            @PathVariable Long id,
            @PathVariable Long facultyId) {
        reserveCounselService.deleteReserveCounsel(id, facultyId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/active")
    public ResponseEntity<List<ReserveCounselDTO>> getActiveReserveCounsels() {
        return ResponseEntity.ok(reserveCounselService.getActiveReserveCounsels());
    }




    ////////////////participants endpoints///////////

    @PostMapping("/{reserveCounselId}/register/{studentId}")
    public ResponseEntity<ParticipantDTO> registerStudent(
            @PathVariable Long reserveCounselId,
            @PathVariable Long studentId) {
        return ResponseEntity.ok(reserveCounselParticipantsService.registerStudent(reserveCounselId, studentId));
    }



}
