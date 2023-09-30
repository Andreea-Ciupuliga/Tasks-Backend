package com.example.Tasks.DTOs.UserDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String phone;
}
