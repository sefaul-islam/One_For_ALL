package com.example.One_For_All.Users.model.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
public class Students {
    @Id
    private Long studentId; // This matches with user_id

    @OneToOne
    @MapsId // This indicates that studentId is both PK and FK to User
    @JoinColumn(name = "student_id")
    private Users user;

    @Column(nullable = false, unique = true)
    private String studentNumber;

    @Column
    private String gradeLevel;

    @Column
    private String major;
}
