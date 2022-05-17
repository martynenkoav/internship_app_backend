package com.example.attempt.service;

import com.example.attempt.model.Company;

import java.util.List;


public interface CompanyService {
    Company getById(Long id);

    void save(Company company);

    void delete(Long id);

    List<Company> getAll();

    Company getByUserId(Long id);
}
