package com.example.attempt.rest;

import com.example.attempt.dto.InternshipDTO;
import com.example.attempt.model.Internship;
import com.example.attempt.model.Student;
import com.example.attempt.serviceImplementation.CompanyServiceImpl;
import com.example.attempt.serviceImplementation.InternshipServiceImpl;
import com.example.attempt.serviceImplementation.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/internship")
public class InternshipRestController {

    private final InternshipServiceImpl internshipService;

    private final CompanyServiceImpl companyService;

    private final StudentServiceImpl studentService;

    @PreAuthorize("#id == authentication.principal.id")
    @RequestMapping(value = "{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Internship> saveInternship(@PathVariable("id") Long id, @RequestBody InternshipDTO internshipDTO) {
        HttpHeaders headers = new HttpHeaders();
        Internship internship = internshipDTO.toInternship();
        if (internship == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.internshipService.save(internship);
        return new ResponseEntity<>(internship, headers, HttpStatus.CREATED);
    }


    @RequestMapping(value = "{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Internship> updateInternship(@PathVariable Long id, @RequestBody InternshipDTO internshipDTO) {
        HttpHeaders headers = new HttpHeaders();

        Internship internship = this.internshipService.getById(internshipDTO.getId());

        internship.setResponses(internshipDTO.getResponses());

        if (internship == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.internshipService.save(internship);
        return new ResponseEntity<>(internship, headers, HttpStatus.OK);
    }


    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Internship> deleteInternship(@PathVariable("id") Long id) {
        Internship internship = this.internshipService.getById(id);

        if (internship == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.internshipService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<InternshipDTO>> getAllInternships() {
        List<Internship> internships = this.internshipService.getAll();
        List<InternshipDTO> internshipsDTO = new ArrayList<>();
        for (Internship internship : internships) {
            internshipsDTO.add(new InternshipDTO(internship));
        }
        return new ResponseEntity<>(internshipsDTO, HttpStatus.OK);
    }

    @PreAuthorize("#id == authentication.principal.id")
    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<InternshipDTO>> getAllInternshipsByUserId(@PathVariable("id") Long id) {

        Long companyId = this.companyService.getByUserId(id).getId();

        if (companyId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Internship> internships = this.internshipService.getAllByCompanyId(companyId);
        List<InternshipDTO> internshipsDTO = new ArrayList<>();
        for (Internship internship : internships) {
            internshipsDTO.add(new InternshipDTO(internship));
        }

        return new ResponseEntity<>(internshipsDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/company/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<InternshipDTO>> getAllInternshipsByCompanyId(@PathVariable("id") Long id) {

        Long companyId = id;

        if (companyId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Internship> internships = this.internshipService.getAllByCompanyId(companyId);
        List<InternshipDTO> internshipsDTO = new ArrayList<>();
        for (Internship internship : internships) {
            internshipsDTO.add(new InternshipDTO(internship));
        }
        return new ResponseEntity<>(internshipsDTO, HttpStatus.OK);
    }

    @PreAuthorize("#id == authentication.principal.id")
    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Set<InternshipDTO>> getAllInternshipsByStudentId(@PathVariable("id") Long id) {

        Student student = this.studentService.getByUserId(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Set<Internship> internships = student.getInternships();
        Set<InternshipDTO> internshipsDTO = new HashSet<>();
        for (Internship internship : internships) {
            internshipsDTO.add(new InternshipDTO(internship));
        }
        return new ResponseEntity<>(internshipsDTO, HttpStatus.OK);
    }
}
