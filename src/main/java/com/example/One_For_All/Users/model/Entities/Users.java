package com.example.One_For_All.Users.model.Entities;

import com.example.One_For_All.Enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "User")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @NotNull(message="Name is required")
    @NotBlank(message = "Name can't be blank")
    @Column(name = "name", unique = true)
    private String username;

    @Column(name = "email" ,unique = true, nullable = false)
    @NotNull(message="email is required")
    @NotBlank(message = "email can't be blank")
    private String email;


    @Column(name = "password")
    @NotNull(message="password is required")
    @NotBlank(message = "password can't be blank")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;






}
