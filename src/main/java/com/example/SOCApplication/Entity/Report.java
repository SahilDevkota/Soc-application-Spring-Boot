package com.example.SOCApplication.Entity;


import com.example.SOCApplication.Entity.subEntity.ReportChatHistory;
import com.example.SOCApplication.Entity.subEntity.ReportDocument;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "report")

//Entity that stores generated report information
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(length = 5000)
    private String content;

    @OneToOne
    private Incident incident;

    @OneToMany(mappedBy ="report")
    private List<ReportDocument> reportDocument;


    @OneToMany(mappedBy = "report")
    private List<ReportChatHistory> reportChatHistories;
}
