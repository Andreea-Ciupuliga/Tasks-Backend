package com.example.Tasks.DTOs.UserDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String username;

    private String phone;

}
