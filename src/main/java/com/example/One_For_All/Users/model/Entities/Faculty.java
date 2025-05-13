package com.example.One_For_All.Users.model.Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faculty_id")
    private Long facultyId;

    @OneToOne
    @MapsId // This indicates that facultyId is both PK and FK to User
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "department", nullable = false, length = 100)
    private String department;

    @Column(name = "academic_title", length = 50)
    private String academicTitle;

    @Column(name = "contact_number", length = 20)
    private String contactNumber;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReserveCounsel> reserveCounsels;
}
