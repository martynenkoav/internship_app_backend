package com.example.attempt.serviceImplementation;

import com.example.attempt.model.Student;
import com.example.attempt.repository.StudentRepository;
import com.example.attempt.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class StudentServiceImpl implements StudentService {


    private final StudentRepository studentRepository;

    @Override
    public Student getById(Long id) {
        log.info("IN StudentService getById {}", id);
        return studentRepository.findById(id).get();
    }

    @Override
    public void save(Student student) {
        log.info("IN StudentService save {}", student);
        studentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        log.info("IN StudentService delete {}", id);
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getAll() {
        log.info("IN StudentService getAll");
        return studentRepository.findAll();
    }

    @Override
    public Student getByUserId(Long id) {
        log.info("IN StudentService getByUserId {}", id);
        return studentRepository.getStudentByUserId(id);
    }
}
