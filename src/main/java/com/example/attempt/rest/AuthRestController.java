package com.example.attempt.rest;

import com.example.attempt.dto.UserDTO;
import com.example.attempt.model.*;
import com.example.attempt.payload.JwtResponse;
import com.example.attempt.repository.RoleRepository;
import com.example.attempt.repository.UserRepository;
import com.example.attempt.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final AuthService authservice;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserDTO userDTO) {
        JwtResponse jwtResponse = this.authservice.createJWTResponse(userDTO);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        User user = userDTO.toUser(roleRepository, encoder);
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}