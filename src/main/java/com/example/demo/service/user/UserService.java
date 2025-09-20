package com.example.demo.service.user;

import com.example.demo.dto.user.UserDTO;
import com.example.demo.dto.user.UserResponseNoPassDTO;

public interface UserService {
    UserResponseNoPassDTO createUser(UserDTO userDTO);
}
