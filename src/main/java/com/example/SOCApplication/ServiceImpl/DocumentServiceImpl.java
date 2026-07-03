package com.example.SOCApplication.ServiceImpl;


import com.example.SOCApplication.Repository.DocumentRepository;
import com.example.SOCApplication.Service.DocumentService;
import com.example.SOCApplication.Mapper.DocumentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentMapper documentMapper;
    private final DocumentRepository documentRepository;
    private final S3Service s3Service;



    @Override
    public List<String> uploadDocument(List<MultipartFile> files) throws IOException {

        return files.stream()
                .map((file -> {
                    try{
                        return s3Service.uploadToBucket(file);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                })).collect(Collectors.toList());


    }


}