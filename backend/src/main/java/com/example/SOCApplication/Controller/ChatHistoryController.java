package com.example.SOCApplication.Controller;


import com.example.SOCApplication.Entity.ChatHistory;
import com.example.SOCApplication.ServiceImpl.ChatHistoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatHistoryController {

    //Service responsible for handling chat history operations
    private final ChatHistoryServiceImpl chatHistoryService;

    //Endpoint for saving chat history
    @PostMapping("/add")
    public ResponseEntity<String> addChatHistory(@RequestBody ChatHistory chatHistory){

        return chatHistoryService.addChatHistory(chatHistory);
    }



}
