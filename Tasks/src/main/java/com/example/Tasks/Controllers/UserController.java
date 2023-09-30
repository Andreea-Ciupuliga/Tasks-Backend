package com.example.Tasks.Controllers;


import com.example.Tasks.DTOs.TaskDTOs.GetTaskWithFormattedDateDTO;
import com.example.Tasks.DTOs.UserDTOs.GetUserDTO;
import com.example.Tasks.DTOs.UserDTOs.RegisterUserDTO;
import com.example.Tasks.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void registerStudent(@RequestBody RegisterUserDTO registerUserDTO) {
         userService.registerUser(registerUserDTO);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<GetUserDTO>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
    @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity<GetUserDTO> getAllUserByUsername(@PathVariable String username) {
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }
}
