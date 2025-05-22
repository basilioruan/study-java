package com.study.java.jasper.infrastructure.repository;

import com.study.java.jasper.infrastructure.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
