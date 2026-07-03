package com.example.SOCApplication.Repository;

import com.example.SOCApplication.Entity.ChatHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatHistoryRepository extends JpaRepository<ChatHistory,Integer> {
}
