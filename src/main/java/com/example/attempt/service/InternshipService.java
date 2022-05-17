package com.example.attempt.service;

import com.example.attempt.model.Internship;

import java.util.List;

public interface InternshipService {
    Internship getById(Long id);

    void save(Internship internship);

    void delete(Long id);

    List<Internship> getAll();

    List<Internship> getAllByCompanyId(Long id);
}
