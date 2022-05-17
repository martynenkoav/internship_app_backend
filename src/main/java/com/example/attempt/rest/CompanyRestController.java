package com.example.attempt.rest;

import com.example.attempt.dto.CompanyDTO;
import com.example.attempt.security.util.EmailValidator;
import com.example.attempt.model.Company;
import com.example.attempt.serviceImplementation.CompanyServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/company")
public class CompanyRestController {

    private final CompanyServiceImpl companyService;

    @RequestMapping(value = "/company/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Company> getCompanyById(@PathVariable("id") Long id) {

        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Company company = this.companyService.getById(id);

        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = this.companyService.getAll();

        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Company> getCompanyByUserId(@PathVariable("id") Long userId) {
        Long companyId = this.companyService.getByUserId(userId).getId();
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Company company = this.companyService.getById(companyId);

        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @PreAuthorize("#id == authentication.principal.id")
    @RequestMapping(value = "{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Company> saveCompany(@PathVariable("id") Long id, @RequestBody CompanyDTO companyDTO) {
        HttpHeaders headers = new HttpHeaders();

        Long companyId = this.companyService.getByUserId(id).getId();
        Company company = this.companyService.getById(companyId);
        if (!EmailValidator.validate(companyDTO.getEmail())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.companyService.save(companyDTO.toCompany(id, company));
        return new ResponseEntity<>(companyDTO.toCompany(id, company), headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Company> updateCompany(@RequestBody Company company) {
        HttpHeaders headers = new HttpHeaders();

        if (company == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.companyService.save(company);

        return new ResponseEntity<>(company, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Company> deleteCompany(@PathVariable("id") Long id) {
        Company company = this.companyService.getById(id);

        this.companyService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
