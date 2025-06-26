package com.example.One_For_All.Users.Services;

import com.example.One_For_All.Users.Repos.ReserveCounselParticipantsRepository;
import com.example.One_For_All.Users.Repos.ReserveCounselRepository;
import com.example.One_For_All.Users.Repos.StudentRepository;
import com.example.One_For_All.Users.model.Entities.ReserveCounsel;
import com.example.One_For_All.Users.model.Entities.ReserveCounselParticipants;
import com.example.One_For_All.Users.model.Entities.Students;
import com.example.One_For_All.Users.model.ParticipantDTO;
import com.example.One_For_All.Users.model.ReserveCounselDTO;
import com.example.One_For_All.exception.InvalidOperationException;
import com.example.One_For_All.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReserveCounselParticipantsService {

    private final ReserveCounselParticipantsRepository participantsRepository;
    private final ReserveCounselRepository reserveCounselRepository;
    private final StudentRepository studentRepository;

    public ReserveCounselParticipantsService(ReserveCounselParticipantsRepository participantsRepository,
                                             ReserveCounselRepository reserveCounselRepository,
                                             StudentRepository studentRepository) {
        this.participantsRepository = participantsRepository;
        this.reserveCounselRepository = reserveCounselRepository;
        this.studentRepository = studentRepository;
    }

     public ParticipantDTO registerStudent(Long reserveCounselId, Long studentId) {
        // Get reserve counsel
        ReserveCounsel reserveCounsel = reserveCounselRepository.findById(reserveCounselId)
                .orElseThrow(() -> new UserNotFoundException());

        // Get student
        Students student = studentRepository.findById(studentId)
                .orElseThrow(() -> new UserNotFoundException());

        // Check if counsel is active and accepting registrations
        if (reserveCounsel.getStatus() != ReserveCounsel.Status.PENDING &&
                reserveCounsel.getStatus() != ReserveCounsel.Status.ACTIVE) {
            throw new InvalidOperationException("This reserve counsel is not accepting registrations");
        }

        // Check if counsel is in the future
        if (reserveCounsel.getStartTime().isBefore(LocalDateTime.now())) {
            throw new InvalidOperationException("Cannot register for a reserve counsel that has already started");
        }

        // Check if the student is already registered
        if (participantsRepository.findByReserveCounselAndStudent(reserveCounsel, student).isPresent()) {
            throw new InvalidOperationException("Student is already registered for this reserve counsel");
        }

        // Check if the counsel has reached max capacity
        if (reserveCounsel.getCurrentParticipants() >= reserveCounsel.getMaxParticipants()) {
            throw new InvalidOperationException("This reserve counsel has reached maximum capacity");
        }

        // Register the student
        ReserveCounselParticipants participant = new ReserveCounselParticipants();
        participant.setReserveCounsel(reserveCounsel);
        participant.setStudent(student);
        participant.setStatus(ReserveCounselParticipants.ParticipantStatus.REGISTERED);

        // Save participant
        ReserveCounselParticipants savedParticipant = participantsRepository.save(participant);

        // Update current participants count
        reserveCounsel.setCurrentParticipants(reserveCounsel.getCurrentParticipants() + 1);

        // If counsel is now full, update its status
        if (reserveCounsel.getCurrentParticipants() >= reserveCounsel.getMaxParticipants()) {
            reserveCounsel.setStatus(ReserveCounsel.Status.FULL);
        }

        reserveCounselRepository.save(reserveCounsel);

        return new ParticipantDTO(savedParticipant);
    }
    public void deleteParticipantByStudentAndCounsel(Long studentId,Long counselId) {
        Students student = studentRepository.findById(studentId)
                .orElseThrow(() -> new UserNotFoundException());
        ReserveCounsel reserveCounsel = reserveCounselRepository.findById(counselId)
                .orElseThrow(() -> new UserNotFoundException());

        ReserveCounselParticipants participant = participantsRepository
                .findByReserveCounselAndStudent(reserveCounsel, student)
                .orElseThrow(() -> new RuntimeException("Participant not found"));

        participantsRepository.delete(participant);
        reserveCounsel.setCurrentParticipants(reserveCounsel.getCurrentParticipants() - 1);
        reserveCounselRepository.save( reserveCounsel);


    }







}
