package com.example.SOCApplication.Controller;


import com.example.SOCApplication.ServiceImpl.FileServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/document")
public class DocumentController {

    //Service for handling document operations
    private final FileServiceImpl documentService;

    //Endpoint for adding document
    @PostMapping("/add")
       public ResponseEntity<List<String>> uploadDocument(@RequestParam("files") List<MultipartFile> files) throws IOException {
        return ResponseEntity.ok(documentService.uploadDocument(files));
    }

    @PostMapping("/{id}")
    public String sendDocs(@PathVariable Integer id) throws IOException {
        return documentService.getTheFile(id);
    }



}
