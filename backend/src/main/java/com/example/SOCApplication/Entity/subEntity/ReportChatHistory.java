package com.example.SOCApplication.Entity.subEntity;


import com.example.SOCApplication.Entity.ChatHistory;
import com.example.SOCApplication.Entity.Report;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

//Entity that links reports with their related chat histories
public class ReportChatHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private ChatHistory chatHistory;

    @ManyToOne
    private Report report;
}
