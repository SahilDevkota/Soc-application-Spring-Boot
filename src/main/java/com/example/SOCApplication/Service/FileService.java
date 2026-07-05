package com.example.SOCApplication.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {


    List<String> uploadDocument(List<MultipartFile> files) throws IOException;
    String getTheFile(Integer Id) throws IOException;
}
