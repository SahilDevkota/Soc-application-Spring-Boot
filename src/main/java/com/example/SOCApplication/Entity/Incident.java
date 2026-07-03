package com.example.SOCApplication.Entity;


import com.example.SOCApplication.Enum.Severity;
import com.example.SOCApplication.Enum.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "incident")

//Entity that stores the incident details
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer incidentCode;

    private String name;

    @ManyToOne
    private User user;

    private String description;

    private Severity severity;

    private Status status;

    private LocalDateTime createdAt;

    @OneToOne
    private Report report;

    @OneToMany(mappedBy = "incident")
    private List<Alert> alert;
}
