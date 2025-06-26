package com.example.One_For_All.Users.Services;

import com.example.One_For_All.Users.Repos.FacultyRepository;
import com.example.One_For_All.Users.Repos.ReserveCounselParticipantsRepository;
import com.example.One_For_All.Users.Repos.ReserveCounselRepository;
import com.example.One_For_All.Users.model.CreateReserveCounselRequest;
import com.example.One_For_All.Users.model.Entities.Faculty;
import com.example.One_For_All.Users.model.Entities.ReserveCounsel;
import com.example.One_For_All.Users.model.Entities.ReserveCounselParticipants;
import com.example.One_For_All.Users.model.Entities.Students;
import com.example.One_For_All.Users.model.ParticipantDTO;
import com.example.One_For_All.Users.model.ReserveCounselDTO;
import com.example.One_For_All.exception.InvalidOperationException;
import com.example.One_For_All.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReserveCounselService {

    private final ReserveCounselRepository reserveCounselRepository;
    private final FacultyRepository facultyRepository;
    private final ReserveCounselParticipantsRepository reserveCounselParticipantsRepository;

    public ReserveCounselService(ReserveCounselRepository reserveCounselRepository,
                                 FacultyRepository facultyRepository,
                                 ReserveCounselParticipantsRepository reserveCounselParticipantsRepository) {
        this.reserveCounselRepository = reserveCounselRepository;
        this.facultyRepository = facultyRepository;
        this.reserveCounselParticipantsRepository=reserveCounselParticipantsRepository;
    }

    public ReserveCounselDTO createReserveCounsel(CreateReserveCounselRequest request, Long facultyid){

        Faculty faculty = facultyRepository.findByUser_Id(facultyid).orElseThrow(()-> new UserNotFoundException());

        if (request.getStartTime().isBefore(LocalDateTime.now())) {
            throw new InvalidOperationException("Start time must be in the future");
        }

        if (request.getEndTime().isBefore(request.getStartTime())) {
            throw new InvalidOperationException("End time must be after start time");
        }
        ReserveCounsel reserveCounsel = new ReserveCounsel();
        reserveCounsel.setFaculty(faculty);
        reserveCounsel.setTitle(request.getTitle());
        reserveCounsel.setDescription(request.getDescription());
        reserveCounsel.setStartTime(request.getStartTime());
        reserveCounsel.setEndTime(request.getEndTime());
        reserveCounsel.setMaxParticipants(request.getMaxParticipants());
        reserveCounsel.setStatus(ReserveCounsel.Status.PENDING);

        ReserveCounsel savedReserveCounsel = reserveCounselRepository.save(reserveCounsel);

        return new ReserveCounselDTO(savedReserveCounsel);

    }

    public void deleteReserveCounsel(Long id, Long facultyId) {
        ReserveCounsel reserveCounsel = reserveCounselRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());

        // Security check - only allow deletion by the faculty who created it
        if (!reserveCounsel.getFaculty().getFacultyId().equals(facultyId)) {
            throw new InvalidOperationException("You are not authorized to delete this reserve counsel");
        }

        // Check if the counsel has already started
        if (reserveCounsel.getStartTime().isBefore(LocalDateTime.now())&&reserveCounsel.getStatus()!= ReserveCounsel.Status.COMPLETED) {
            throw new InvalidOperationException("Cannot delete a reserve counsel that has already started");
        }

        reserveCounselRepository.delete(reserveCounsel);
    }
    public ReserveCounselDTO getReserveCounselById(Long id) {
        ReserveCounsel reserveCounsel = reserveCounselRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());
        return new ReserveCounselDTO(reserveCounsel);
    }
    public List<ReserveCounselDTO> getReserveCounselsByFaculty(Long facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new UserNotFoundException());

        return reserveCounselRepository.findByFaculty(faculty)
                .stream()
                .map(ReserveCounselDTO::new)
                 .toList();
    }

    public List<ReserveCounselDTO> getActiveReserveCounsels() {
        return reserveCounselRepository.findActive(LocalDateTime.now())
                .stream()
                .map(ReserveCounselDTO::new)
                .toList();
    }

    @Scheduled(fixedRate = 60000) // every 1 minute
    @Transactional
    public void updateExpiredCounselStatuses() {
        LocalDateTime now = LocalDateTime.now();
        List<ReserveCounsel> expiredCounsels = reserveCounselRepository.findByEndTimeBeforeAndStatusNot(now, ReserveCounsel.Status.COMPLETED);

        for (ReserveCounsel counsel : expiredCounsels) {
            counsel.setStatus(ReserveCounsel.Status.COMPLETED);
        }

        reserveCounselRepository.saveAll(expiredCounsels);
    }

    public List<ParticipantDTO> getParticipantsByCounselId(Long reserveCounselId) {
        ReserveCounsel counsel = reserveCounselRepository.findById(reserveCounselId)
                .orElseThrow(() -> new RuntimeException("ReserveCounsel not found with ID: " + reserveCounselId));

        return counsel.getParticipants().stream()
                .map(ParticipantDTO::new)
                .collect(Collectors.toList());
    }


    @Scheduled(fixedRate = 60000) // every 1 minute
    @Transactional
    public void updateActiveCounselStatuses() {
        LocalDateTime now = LocalDateTime.now();
        List<ReserveCounsel> toActivate = reserveCounselRepository.findByStartTimeBeforeAndStatus(now, ReserveCounsel.Status.PENDING);

        for (ReserveCounsel counsel : toActivate) {
            counsel.setStatus(ReserveCounsel.Status.ACTIVE);
        }

        reserveCounselRepository.saveAll(toActivate);
    }

    public List<ReserveCounselDTO> getRegisteredCounselForStudent(Long studentId) {
        List<ReserveCounselParticipants> participants = reserveCounselParticipantsRepository.findByStudent_StudentId(studentId);

        return participants.stream()
                .map(ReserveCounselParticipants::getReserveCounsel)
                .map(ReserveCounselDTO::new)
                .collect(Collectors.toList());
    }

    public List<ReserveCounselDTO> getAllReserveCounsels() {
        return reserveCounselRepository.findAll()
            .stream()
            .map(ReserveCounselDTO::new)
            .toList();
    }
    public Optional<ReserveCounsel> findById(Long id) {
        return reserveCounselRepository.findById(id);
    }



}


