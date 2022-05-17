package com.example.attempt.serviceImplementation;

import com.example.attempt.model.Internship;
import com.example.attempt.repository.InternshipRepository;
import com.example.attempt.service.InternshipService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class InternshipServiceImpl implements InternshipService {

    private final InternshipRepository internshipRepository;

    @Override
    public Internship getById(Long id) {
        log.info("IN InternshipService getById {}", id);
        return internshipRepository.findById(id).get();
    }

    @Override
    public void save(Internship internship) {
        log.info("IN InternshipService save {}", internship);
        internshipRepository.save(internship);
    }

    @Override
    public void delete(Long id) {
        log.info("IN InternshipService delete {}", id);
        internshipRepository.deleteById(id);
    }

    @Override
    public List<Internship> getAll() {
        log.info("IN InternshipService getAll");
        return internshipRepository.findAll();
    }

    public List<Internship> getAllByCompanyId(Long id) {
        log.info("IN InternshipService getAll");
        return internshipRepository.getAllByCompanyId(id);
    }

}
