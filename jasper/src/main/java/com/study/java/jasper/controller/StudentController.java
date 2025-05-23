package com.study.java.jasper.controller;

import com.study.java.jasper.infrastructure.model.Student;
import com.study.java.jasper.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/generate-certificate")
    public void generateStudentCertificate(@RequestParam("id") Long id) {
        this.studentService.generateStudentCertificate(id);
    }

    @PostMapping("/generate-certificate")
    public void generateStudentCertificate(@RequestBody Student student) {
        this.studentService.generate(student);
    }

}
