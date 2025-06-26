package com.example.One_For_All.Users.controllers;

import com.example.One_For_All.Users.Repos.ReserveCounselRepository;
import com.example.One_For_All.Users.Repos.StudentRepository;
import com.example.One_For_All.Users.Services.ReserveCounselParticipantsService;
import com.example.One_For_All.Users.Services.ReserveCounselService;
import com.example.One_For_All.Users.Services.StudentServices;
import com.example.One_For_All.Users.model.Entities.ReserveCounsel;
import com.example.One_For_All.Users.model.Entities.Students;
import com.example.One_For_All.Users.model.ParticipantDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.One_For_All.Users.model.ReserveCounselDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservecounselparticipant")
public class ReserveCounselParticipantController {
    private final ReserveCounselParticipantsService reserveCounselParticipantsService;
    private final StudentServices studentServices;
    private final ReserveCounselService reserveCounselService;
    private final ReserveCounselRepository reserveCounselRepository;

    public ReserveCounselParticipantController(ReserveCounselParticipantsService reserveCounselParticipantsService, StudentServices studentServices, ReserveCounselService reserveCounselService,
                                               ReserveCounselRepository reserveCounselRepository) {
        this.reserveCounselParticipantsService = reserveCounselParticipantsService;
        this.studentServices = studentServices;
        this.reserveCounselService = reserveCounselService;
        this.reserveCounselRepository = reserveCounselRepository;
    }

    @PostMapping("/{reserveCounselId}/register/{studentId}")
    public ResponseEntity<ParticipantDTO> registerStudent(
            @PathVariable Long reserveCounselId,
            @PathVariable Long studentId) {
        return ResponseEntity.ok(reserveCounselParticipantsService.registerStudent(reserveCounselId, studentId));
    }
    @DeleteMapping("/cancelreg/{counselId}/{studentId}")
    public ResponseEntity<?> cancelCounselling(@PathVariable Long studentId , @PathVariable Long counselId){
        reserveCounselParticipantsService.deleteParticipantByStudentAndCounsel(studentId, counselId);
        return ResponseEntity.ok("Cancelled Session");
    }



}
