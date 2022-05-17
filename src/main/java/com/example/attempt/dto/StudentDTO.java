package com.example.attempt.dto;

import com.example.attempt.model.Internship;
import com.example.attempt.model.Student;
import com.example.attempt.service.InternshipService;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class StudentDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String email;
    private String phone;
    private Long userId;
    private Set<Long> internships = new HashSet<>();

    public Set<Long> getInternships() {
        return internships;
    }

    public void setInternships(Set<Long> internships) {
        this.internships = internships;
    }

    public StudentDTO(Long id, String firstName, String lastName, String patronymic, String email,
                      String phone, Long userId, Set<Long> internships) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.phone = phone;
        this.userId = userId;
        this.internships.addAll(internships);
    }

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.patronymic = student.getPatronymic();
        this.email = student.getEmail();
        this.phone = student.getPhone();
        this.userId = student.getUserId();
        if (student.getInternships() == null) {
            this.internships = null;
        } else {
            for (Internship internship : student.getInternships()) {
                this.internships.add(internship.getId());
            }
        }
    }

    public Student toStudent(InternshipService internshipService) {
        Student student = new Student();
        student.setId(this.id);
        student.setFirstName(this.firstName);
        student.setLastName(this.lastName);
        student.setPatronymic(this.patronymic);
        student.setEmail(this.email);
        student.setPhone(this.phone);
        student.setUserId(this.userId);
        for (Long idInternship : this.getInternships()) {
            Internship internship = internshipService.getById(idInternship);
            student.getInternships().add(internship);
        }
        return student;
    }
}
