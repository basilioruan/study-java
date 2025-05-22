package com.study.java.jasper.controller;

import com.study.java.jasper.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public void generateStudentCertification(@RequestParam("id") Long id) {
        this.studentService.generateStudentCertification(id);
    }

}
