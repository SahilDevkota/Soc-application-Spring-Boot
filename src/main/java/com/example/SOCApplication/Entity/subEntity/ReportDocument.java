package com.example.SOCApplication.Entity.subEntity;


import com.example.SOCApplication.Entity.Report;
import com.example.SOCApplication.Entity.FileEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "ReportDocument")

//Entity that represents the relationship between the reports and chat histories
public class ReportDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Report report;

    @ManyToOne
    private FileEntity document;

}
