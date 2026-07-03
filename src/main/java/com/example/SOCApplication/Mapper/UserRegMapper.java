package com.example.SOCApplication.Mapper;

import com.example.SOCApplication.DTO.RequestDTO.UserRegDTO;
import com.example.SOCApplication.Entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

//Mapper for converting between User entity and DTO
public interface UserRegMapper {

    UserRegDTO userToRegDTO(User user);
    User regDTOtoUser(UserRegDTO userRegDTO);


}
