package com.example.SOCApplication.Entity;


import com.example.SOCApplication.Entity.subEntity.ReportChatHistory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "chatHistory")

//Entity that stores chat interaction between the user and the chatbot
public class ChatHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String prompt;

    private String response;

    @OneToMany(mappedBy = "chatHistory")
    private List<ReportChatHistory> reportChatHistories;
}
