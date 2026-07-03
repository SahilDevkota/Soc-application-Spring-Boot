package com.example.SOCApplication.ServiceImpl;


import com.example.SOCApplication.Entity.ChatHistory;
import com.example.SOCApplication.Repository.ChatHistoryRepository;
import com.example.SOCApplication.Service.ChatHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatHistoryServiceImpl implements ChatHistoryService {

    private final ChatHistoryRepository chatHistoryRepository;

    @Override
    public ResponseEntity<String> addChatHistory(ChatHistory chatHistory) {
        chatHistoryRepository.save(chatHistory);
        return ResponseEntity.ok("Chat History added successfully");
    }
}
