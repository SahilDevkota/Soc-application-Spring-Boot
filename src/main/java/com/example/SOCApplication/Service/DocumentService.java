package com.example.SOCApplication.Service;

import com.example.SOCApplication.DTO.RequestDTO.DocumentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DocumentService {


    List<String> uploadDocument(List<MultipartFile> files) throws IOException;

}
