package com.example.SOCApplication.ServiceImpl;

import com.example.SOCApplication.DTO.ResponseDTO.FileResponseDTO;
import com.example.SOCApplication.Entity.FileEntity;
import com.example.SOCApplication.Mapper.FileMapper;
import com.example.SOCApplication.Repository.FileRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    @Value("${aws.bucket-name}")
    private String bucket;

    private final FileRepository fileRepository;
    private final FileMapper fileMapper;
    private final RestClient restClient;
    private final S3Client s3Client;

    public String uploadToBucket(MultipartFile file) throws IOException {

        String folder = "documents";
        String filename = UUID.randomUUID() + "-" + file.getOriginalFilename();
        String objectKey = folder + "/" + filename;


        InputStream inputStream = file.getInputStream();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucket).
                key(objectKey).
                build();

        RequestBody requestBody = RequestBody.fromInputStream(inputStream,file.getSize());

        PutObjectResponse putObjectResponse = s3Client.putObject(putObjectRequest,requestBody);

        FileResponseDTO fileDTO = new FileResponseDTO();
        fileDTO.setObjectKey(objectKey);

        return objectKey;
    }

    public String getUrl(FileResponseDTO fileResponseDTO){

        FileEntity fileEntity = fileMapper.DTOtoFile(fileResponseDTO);
        return (s3Client.utilities().getUrl(
                builder -> builder.bucket(bucket)
                        .key(fileResponseDTO.getObjectKey())
                        .build()).toString()
        );
    }

    public String getFile(Integer id) throws IOException {

        FileEntity fileEntity = fileRepository.findById(id).orElseThrow(()-> new RuntimeException("File not found"));
        String objectKey = fileEntity.getObjectKey();

        GetObjectRequest request = GetObjectRequest.builder().bucket(bucket).key(objectKey).build();

        ResponseInputStream<GetObjectResponse> response = s3Client.getObject(request);

        byte[] bytes = response.readAllBytes();

        ByteArrayResource resource = new ByteArrayResource(bytes){
            @Override
            public String getFilename(){
                return objectKey;
            }
        };




        MultiValueMap<String,Object> body = new LinkedMultiValueMap<>();

        body.add("file",resource);

        return restClient
                .post()
                .uri("http://127.0.0.1:8000/getTheFile")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(body)
                .retrieve()
                .body(String.class);
    }
    }

