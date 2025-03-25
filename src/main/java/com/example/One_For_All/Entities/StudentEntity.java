package com.example.One_For_All.Entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student_entity")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message="Name is required")
    @NotBlank(message = "Name can't be blank")
    @Column(name = "name")
    private String name;

    @Column(name = "studentId")
    @NotNull(message="Id is required")
    @NotBlank(message = "Id can't be blank")
    @Size(min =10, message = "StudentId should be atleast 10 digits")
    private String StudentId;







}
