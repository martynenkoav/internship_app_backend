package com.example.attempt.repository;

import com.example.attempt.model.Internship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InternshipRepository extends JpaRepository<Internship, Long> {

    List<Internship> getAllByCompanyId(Long id);
}
