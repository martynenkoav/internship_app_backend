package com.example.attempt.service;

import com.example.attempt.model.Student;

import java.util.List;

public interface StudentService {
    Student getById(Long id);

    void save(Student student);

    void delete(Long id);

    List<Student> getAll();

    Student getByUserId(Long id);
}
