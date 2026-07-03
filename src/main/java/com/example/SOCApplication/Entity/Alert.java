package com.example.SOCApplication.Entity;


import com.example.SOCApplication.Enum.Severity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="alert")

//Represents a security alert generated within the SOC system
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String source;

    private String message;

    private Severity severity;

    @ManyToOne
    private Incident incident;
}
