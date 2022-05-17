package com.example.attempt.repository;

import com.example.attempt.model.ETag;
import com.example.attempt.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByName(ETag name);
}
