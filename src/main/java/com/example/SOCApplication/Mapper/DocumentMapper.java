package com.example.SOCApplication.Mapper;

import com.example.SOCApplication.DTO.RequestDTO.DocumentDTO;
import com.example.SOCApplication.Entity.FileEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

//Mapper for converting between Document entity and DTO
public interface DocumentMapper {

    DocumentDTO documentToDTO(FileEntity fileEntity);
    FileEntity DTOtoDocument(DocumentDTO documentDTO);

}
