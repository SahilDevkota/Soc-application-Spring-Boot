package com.example.SOCApplication.ServiceImpl;


import com.example.SOCApplication.Entity.FileEntity;
import com.example.SOCApplication.Mapper.FileMapper;
import com.example.SOCApplication.Repository.FileRepository;
import com.example.SOCApplication.Service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileMapper fileMapper;
    private final FileRepository fileRepository;
    private final S3Service s3Service;



    @Override
    public List<String> uploadDocument(List<MultipartFile> files) throws IOException {

        return files.stream()
                .map((file -> {
                    try{
                        String objectKey = s3Service.uploadToBucket(file);
                        FileEntity fileEntity = new FileEntity();
                        fileEntity.setFileName(file.getOriginalFilename());
                        fileEntity.setObjectKey(objectKey);
                        fileRepository.save(fileEntity);
                        return objectKey;

                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                })).collect(Collectors.toList());
    }

    @Override
    public String getTheFile(Integer Id) throws IOException {
        return s3Service.getFile(Id);
    }


}