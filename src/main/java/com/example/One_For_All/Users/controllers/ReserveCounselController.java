package com.example.One_For_All.Users.controllers;

import com.example.One_For_All.Users.Services.ReserveCounselService;
import com.example.One_For_All.Users.model.CreateReserveCounselRequest;
import com.example.One_For_All.Users.model.ReserveCounselDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/reserve-counsels")
public class ReserveCounselController {

    private final ReserveCounselService reserveCounselService;

    public ReserveCounselController(ReserveCounselService reserveCounselService) {
        this.reserveCounselService = reserveCounselService;

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



}
