package com.example.SOCApplication.Entity;

import com.example.SOCApplication.Enum.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")

//Entity that stores user detailed information
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(unique = true)
    private String username;

    private String password;

    private String email;

    private Roles role;

    @OneToMany(mappedBy = "user")
    private List<Incident> incidentList;

    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "user")
    private List<FileEntity> documents;

    private int failedLoginAttempt;

}
