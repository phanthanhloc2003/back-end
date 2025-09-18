package com.example.demo.service.user;

import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserResponseDTO;

public interface UserService {
    UserResponseDTO createUser(UserDTO userDTO);
}
