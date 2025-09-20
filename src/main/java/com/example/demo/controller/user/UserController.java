package com.example.demo.controller.user;

import com.example.demo.dto.user.UserDTO;
import com.example.demo.dto.user.UserResponseNoPassDTO;
import com.example.demo.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    @PostMapping()
    public ResponseEntity<UserResponseNoPassDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserResponseNoPassDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

}
