package com.study.java.jasper.infrastructure.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "tb_student")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String course;

    @Column
    private Integer workLoad;

    @Column
    private Date initialCourseDate;

    @Column
    private Date finishCourseDate;
}
