package com.example.One_For_All.Users.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @NotNull(message="Name is required")
    @NotBlank(message = "Name can't be blank")
    @Column(name = "name")
    private String name;

    @Column(name = "email" ,unique = true, nullable = false)
    @NotNull(message="email is required")
    @NotBlank(message = "email can't be blank")
    private String email;

    @Column(name = "dept_id" , nullable = false)
    @NotNull(message = "dept id can't be null")
    private Integer dept_id;





}
