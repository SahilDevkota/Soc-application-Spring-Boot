package com.example.SOCApplication.Service;


import com.example.SOCApplication.Entity.ChatHistory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface ChatHistoryService {

    ResponseEntity<String> addChatHistory(ChatHistory chatHistory);
}
