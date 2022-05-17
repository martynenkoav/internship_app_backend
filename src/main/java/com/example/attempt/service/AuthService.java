package com.example.attempt.service;

import com.example.attempt.dto.UserDTO;
import com.example.attempt.payload.JwtResponse;

public interface AuthService {
    JwtResponse createJWTResponse(UserDTO userDTO);
}
