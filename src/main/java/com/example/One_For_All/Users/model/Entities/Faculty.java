package com.example.One_For_All.Users.model.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "faculty")
public class Faculty {
    @Id
    private Long facultyId;

    @OneToOne
    @MapsId // This indicates that facultyId is both PK and FK to User
    @JoinColumn(name = "faculty_id")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)

    private Department department;

    @Column(name = "academic_title", length = 50)
    private String academicTitle;

    @Column(name = "contact_number", length = 20)
    private String contactNumber;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReserveCounsel> reserveCounsels;

    public void addReserveCounsel(ReserveCounsel reserveCounsel) {
        if (reserveCounsels == null) {
            reserveCounsels = new ArrayList<>();
        }
        reserveCounsels.add(reserveCounsel);
        reserveCounsel.setFaculty(this);
    }

    public void removeReserveCounsel(ReserveCounsel reserveCounsel) {
        if (reserveCounsels != null) {
            reserveCounsels.remove(reserveCounsel);
            reserveCounsel.setFaculty(null);
        }
    }
}
