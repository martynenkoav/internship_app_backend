package com.example.attempt.serviceImplementation;

import com.example.attempt.model.Tag;
import com.example.attempt.repository.TagRepository;
import com.example.attempt.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public Tag getById(Long id) {
        return tagRepository.findById(id).get();
    }
}
