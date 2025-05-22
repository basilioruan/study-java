package com.study.java.jasper.services;

import com.study.java.jasper.infrastructure.model.Student;
import com.study.java.jasper.infrastructure.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public void generateStudentCertification(Long id) {
        Student student = this.studentRepository.findById(id).orElseThrow();
    }

}
