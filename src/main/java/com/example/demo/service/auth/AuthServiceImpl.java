package com.example.demo.service.auth;

import com.example.demo.dto.user.LoginRequestUser;
import com.example.demo.dto.user.LoginResponseUser;
import com.example.demo.dto.user.UserResponseNoPassDTO;
import com.example.demo.entity.user.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.user.UserRepository;
import com.example.demo.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    @Override
    public LoginResponseUser login(LoginRequestUser loginRequestUser) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestUser.getEmail(),
                        loginRequestUser.getPassword()
                )
        );

        User user = userRepository.findByEmail(loginRequestUser.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        String token = jwtUtil.generateToken(user.getFullName(), user.getRole());

        UserResponseNoPassDTO userDto = new UserResponseNoPassDTO(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getAvatar(),
                user.getStatus(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );

        return new LoginResponseUser(userDto, token);
    }

    public void signup(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
