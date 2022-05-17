package com.example.attempt.serviceImplementation;

import com.example.attempt.dto.UserDTO;
import com.example.attempt.model.Role;
import com.example.attempt.repository.RoleRepository;
import com.example.attempt.service.RoleService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getById(Long id) {
        return roleRepository.findById(id).get();
    }
}
