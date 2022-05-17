package com.example.attempt.serviceImplementation;

import com.example.attempt.model.Company;
import com.example.attempt.repository.CompanyRepository;
import com.example.attempt.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public Company getById(Long id) {
        log.info("IN CompanyService getById {}", id);
        return companyRepository.findById(id).get();
    }

    @Override
    public void save(Company company) {
        log.info("IN CompanyService save {}", company);
        companyRepository.save(company);
    }

    @Override
    public void delete(Long id) {
        log.info("IN CompanyService delete {}", id);
        companyRepository.deleteById(id);
    }

    @Override
    public List<Company> getAll() {
        log.info("IN CompanyService getAll");
        return companyRepository.findAll();
    }

    public Company getByUserId(Long id) {
        log.info("IN CompanyService getByUserId {}", id);
        return companyRepository.getByUserId(id);
    }
}
