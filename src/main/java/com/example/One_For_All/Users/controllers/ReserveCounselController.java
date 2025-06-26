package com.example.One_For_All.Users.controllers;

import com.example.One_For_All.Users.Services.ReserveCounselParticipantsService;
import com.example.One_For_All.Users.Services.ReserveCounselService;
import com.example.One_For_All.Users.model.CreateReserveCounselRequest;
import com.example.One_For_All.Users.model.Entities.ReserveCounsel;
import com.example.One_For_All.Users.model.ParticipantDTO;
import com.example.One_For_All.Users.model.ReserveCounselDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    // for faculty only later will be authorized
    @PostMapping("/faculty/{facultyId}")
    public ResponseEntity<?> createReserveCounsel(
            @PathVariable Long facultyId,
            @Valid @RequestBody CreateReserveCounselRequest request,BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors()
                    .forEach(error -> System.out.println("Validation error: " + error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body( reserveCounselService.createReserveCounsel(request, facultyId));
    }

    // getting reserve counsel by counsel id
    @GetMapping("/{id}")
    public ResponseEntity<ReserveCounselDTO> getReserveCounselById(@PathVariable Long id) {
        return ResponseEntity.ok(reserveCounselService.getReserveCounselById(id));
    }

    //get list of reserve counsels created by faculty
    @GetMapping("/faculty/{facultyId}")
    public ResponseEntity<List<ReserveCounselDTO>> getReserveCounselsByFaculty(@PathVariable Long facultyId) {
        return ResponseEntity.ok(reserveCounselService.getReserveCounselsByFaculty(facultyId));
    }

    //delete reserve counsel
    @DeleteMapping("/{id}/faculty/{facultyId}")
    public ResponseEntity<Void> deleteReserveCounsel(
            @PathVariable Long id,
            @PathVariable Long facultyId) {
        reserveCounselService.deleteReserveCounsel(id, facultyId);
        return ResponseEntity.noContent().build();
    }

    //getting list of active reserve counsels
    @GetMapping("/active")
    public ResponseEntity<List<ReserveCounselDTO>> getActiveReserveCounsels() {
        return ResponseEntity.ok(reserveCounselService.getActiveReserveCounsels());
    }

    // getting participants of a counsel
    @GetMapping("/{id}/participants")
    public ResponseEntity<List<ParticipantDTO>> getParticipants(@PathVariable Long id) {
        List<ParticipantDTO> participants = reserveCounselService.getParticipantsByCounselId(id);
        return ResponseEntity.ok(participants);
    }

    // get counsels registered by the student
    @GetMapping("/registered/student/{studentId}")
    public ResponseEntity<List<ReserveCounselDTO>> getRegisteredCounselByStudentId(@PathVariable Long studentId) {
        List<ReserveCounselDTO> counsels = reserveCounselService.getRegisteredCounselForStudent(studentId);
        return ResponseEntity.ok(counsels);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReserveCounselDTO>> getAllReserveCounsels() {
        return ResponseEntity.ok(reserveCounselService.getAllReserveCounsels());
    }













}
