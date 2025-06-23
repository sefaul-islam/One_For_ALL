package com.example.One_For_All.Users.model.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "admin")
public class Admin {
    @Id
    private Long adminId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "admin_id")
    private Users user;


    @Column(name = "Title")
    private String title;

}
