package com.example.attempt.dto;

import com.example.attempt.model.ERole;
import com.example.attempt.model.Role;
import com.example.attempt.model.User;
import com.example.attempt.repository.RoleRepository;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String role;

    UserDTO(Long id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User toUser(RoleRepository roleRepository, PasswordEncoder encoder) {
        User user = new User(this.username, encoder.encode(this.password));
        String role = this.role;
        ERole eRole = ERole.valueOf(role);
        Set<Role> roles = new HashSet<>();
        switch (eRole) {
            case ROLE_ADMIN:
                Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(adminRole);
                break;
            case ROLE_STUDENT:
                Role modRole = roleRepository.findByName(ERole.ROLE_STUDENT)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(modRole);
                break;
            case ROLE_COMPANY:
                Role userRole = roleRepository.findByName(ERole.ROLE_COMPANY)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
        }
        user.setRoles(roles);
        return user;
    }
}
