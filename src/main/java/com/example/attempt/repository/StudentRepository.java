package com.example.attempt.repository;

import com.example.attempt.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student getStudentByUserId(Long id);
}
