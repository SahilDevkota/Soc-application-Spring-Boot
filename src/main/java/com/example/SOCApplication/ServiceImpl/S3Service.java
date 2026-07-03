package com.example.SOCApplication.ServiceImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    @Value("${aws.bucket-name}")
    private String bucket;

    private final S3Client s3Client;

    public String uploadToBucket(MultipartFile file) throws IOException {

        String filename = UUID.randomUUID() + "-" + file.getOriginalFilename();

        InputStream inputStream = file.getInputStream();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucket).
                key(filename).
                build();

        RequestBody requestBody = RequestBody.fromInputStream(inputStream,file.getSize());


        s3Client.putObject(putObjectRequest,requestBody);

        return "Added successfully in s3!!";
    }






    }

