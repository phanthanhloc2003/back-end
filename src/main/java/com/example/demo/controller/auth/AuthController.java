package com.example.demo.controller.auth;

import com.example.demo.dto.user.LoginRequestUser;
import com.example.demo.dto.user.LoginResponseUser;
import com.example.demo.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
   private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseUser> login(@RequestBody LoginRequestUser request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
