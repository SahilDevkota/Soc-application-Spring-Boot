package com.example.SOCApplication.DTO.RequestDTO;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor

//DTO used for transferring User's registration data between client and server
public class UserRegDTO {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private String role;

    @NotNull
    private String password;

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private LocalDate dateOfBirth;
}
