package com.example.SOCApplication.DTO.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

//DTO used for transferring Document data between client and server
public class DocumentDTO {

    private String objectKey;

    private List<String> fileURLs;

}
