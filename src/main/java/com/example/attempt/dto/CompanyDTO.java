package com.example.attempt.dto;

import com.example.attempt.model.Company;
import com.example.attempt.security.util.EmailValidator;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class CompanyDTO {
    private String name;
    private String description;
    private String email;
    private String address;
    private EmailValidator emailValidator;


    public CompanyDTO(String name, String description, String email, String address) {
        this.name = name;
        this.description = description;
        this.email = email;
        this.address = address;
    }

    public Company toCompany(Long id, Company company) {
        company.setName(this.name);
        company.setDescription(this.description);
        company.setEmail(this.email);
        company.setAddress(this.address);
        return company;
    }
}
