package com.example.Tasks.Services;

import com.example.Tasks.DTOs.TaskDTOs.GetTaskDTO;
import com.example.Tasks.DTOs.TaskDTOs.GetTaskWithFormattedDateDTO;
import com.example.Tasks.DTOs.UserDTOs.GetUserDTO;
import com.example.Tasks.DTOs.UserDTOs.RegisterUserDTO;
import com.example.Tasks.Exceptions.AlreadyExistException;
import com.example.Tasks.Exceptions.NotFoundException;
import com.example.Tasks.Models.User;
import com.example.Tasks.Repositories.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private final KeycloakAdminService keycloakAdminService;

    @Autowired
    public UserService(UserRepository userRepository,KeycloakAdminService keycloakAdminService) {
        this.userRepository = userRepository;
        this.keycloakAdminService = keycloakAdminService;
    }

    @SneakyThrows
    public void registerUser(RegisterUserDTO registerUserDTO) {


        if (userRepository.findByUsername(registerUserDTO.getUsername()).isPresent() ) {
            throw new AlreadyExistException("Username Already Exist");
        }

        if (userRepository.findByEmail(registerUserDTO.getEmail()).isPresent()) {
            throw new AlreadyExistException("Email Already Exist");
        }

        User user = User.builder()
                .firstName(registerUserDTO.getFirstName())
                .lastName(registerUserDTO.getLastName())
                .email(registerUserDTO.getEmail())
                .username(registerUserDTO.getUsername())
                .phone(registerUserDTO.getPhone())
                .build();

        userRepository.save(user);

        keycloakAdminService.registerUser(registerUserDTO.getLastName(), registerUserDTO.getFirstName(), registerUserDTO.getUsername(), registerUserDTO.getPassword(),
                registerUserDTO.getEmail(), "ROLE_USER");
    }

    @SneakyThrows
    public List<GetUserDTO> getAllUsers() {
        return userRepository.findAllUsers();
    }

    @SneakyThrows
    public GetUserDTO getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found"));
    }
}
