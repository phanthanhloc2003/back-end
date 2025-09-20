package com.example.demo.service.user;

import com.example.demo.dto.user.UserDTO;
import com.example.demo.dto.user.UserResponseNoPassDTO;
import com.example.demo.entity.user.User;
import com.example.demo.exception.EmailAlreadyExistsException;
import com.example.demo.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserResponseNoPassDTO createUser(UserDTO userDTO) {
     if(userRepository.existsByEmail(userDTO.getEmail())) {
         throw new EmailAlreadyExistsException("Email already exists");
     }
        // Map DTO → Entity
        User user = User.builder()
                .fullName(userDTO.getFullName())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .avatar(userDTO.getAvatar() != null ? userDTO.getAvatar() : null)
                .role("USER")
                .status("ACTIVE")
                .build();

        User savedUser = userRepository.save(user);

        return UserResponseNoPassDTO.builder()
                .id(savedUser.getId())
                .fullName(savedUser.getFullName())
                .email(savedUser.getEmail())
                .status(savedUser.getStatus())
                .avatar(savedUser.getAvatar())
                .role(savedUser.getRole())
                .createdAt(savedUser.getCreatedAt())
                .updatedAt(savedUser.getUpdatedAt())
                .build();


    }
}
