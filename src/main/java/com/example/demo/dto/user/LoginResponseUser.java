package com.example.demo.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponseUser {
    private UserResponseNoPassDTO user;
    private String token;
}
