package com.example.attempt.rest;

import com.example.attempt.dto.StudentDTO;
import com.example.attempt.security.util.EmailValidator;
import com.example.attempt.model.Student;
import com.example.attempt.serviceImplementation.InternshipServiceImpl;
import com.example.attempt.serviceImplementation.StudentServiceImpl;
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
@RequestMapping("/api/student")
public class StudentRestController {

    private final StudentServiceImpl studentService;

    private final InternshipServiceImpl internshipService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<StudentDTO> getStudentByUserId(@PathVariable("id") Long userId) {
        Long studentId = this.studentService.getByUserId(userId).getId();
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Student student = this.studentService.getById(studentId);

        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        StudentDTO studentDTO = new StudentDTO(student);
        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }


    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
        HttpHeaders headers = new HttpHeaders();

        if (student == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!EmailValidator.validate(student.getEmail())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.studentService.save(student);
        return new ResponseEntity<>(student, headers, HttpStatus.CREATED);
    }

    @PreAuthorize("#id == authentication.principal.id")
    @RequestMapping(value = "{id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable("id") Long id, @RequestBody StudentDTO studentDTO) {
        HttpHeaders headers = new HttpHeaders();

        if (studentDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Student student = studentDTO.toStudent(internshipService);
        this.studentService.save(student);

        return new ResponseEntity<>(studentDTO, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") Long id) {
        Student student = this.studentService.getById(id);

        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.studentService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = this.studentService.getAll();

        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }
}
