package com.example.SOCApplication.Mapper;


import com.example.SOCApplication.DTO.RequestDTO.UserLoginDTO;
import com.example.SOCApplication.Entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

//Mapper for converting between User entity and DTO
public interface UserMapper {

    UserLoginDTO userToDTO(User user);
    User DTOtoUser(UserLoginDTO userLoginDTO);
}
