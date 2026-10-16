package com.example.SOCApplication.Mapper;

import com.example.SOCApplication.DTO.ResponseDTO.FileResponseDTO;
import com.example.SOCApplication.Entity.FileEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

//Mapper for converting between Document entity and DTO
public interface FileMapper {

    FileResponseDTO fileToDTO(FileEntity fileEntity);
    FileEntity DTOtoFile(FileResponseDTO fileResponseDTO);

}
