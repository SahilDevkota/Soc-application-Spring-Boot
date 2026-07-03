package com.example.SOCApplication.Repository;

import com.example.SOCApplication.Entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<FileEntity,Integer> {
}
