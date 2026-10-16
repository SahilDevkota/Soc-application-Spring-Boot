package com.example.SOCApplication.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

//DTO used for transferring Document data between client and server
public class FileResponseDTO {

    private String objectKey;


}
