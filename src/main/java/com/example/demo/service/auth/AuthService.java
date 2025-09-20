package com.example.demo.service.auth;

import com.example.demo.dto.user.LoginRequestUser;
import com.example.demo.dto.user.LoginResponseUser;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {
    public LoginResponseUser login(@RequestBody LoginRequestUser loginRequestUser);
}
