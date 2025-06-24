package com.example.One_For_All.Users.Services;

import com.example.One_For_All.Users.Repos.FacultyRepository;
import com.example.One_For_All.Users.Repos.ReserveCounselRepository;
import com.example.One_For_All.Users.model.CreateReserveCounselRequest;
import com.example.One_For_All.Users.model.Entities.Faculty;
import com.example.One_For_All.Users.model.Entities.ReserveCounsel;
import com.example.One_For_All.Users.model.ReserveCounselDTO;
import com.example.One_For_All.exception.InvalidOperationException;
import com.example.One_For_All.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReserveCounselService {

    private final ReserveCounselRepository reserveCounselRepository;
    private final FacultyRepository facultyRepository;

    public ReserveCounselService(ReserveCounselRepository reserveCounselRepository,
                                 FacultyRepository facultyRepository) {
        this.reserveCounselRepository = reserveCounselRepository;
        this.facultyRepository = facultyRepository;
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
        if (reserveCounsel.getStartTime().isBefore(LocalDateTime.now())) {
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



}
