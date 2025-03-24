package com.ziad.school.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "t_grade")
public class Grade extends AbstractPersistable<Long> {
    @Column(length = 45)
    private String name;

    @Column(length = 45)
    private String description;

    @OneToOne(cascade = CascadeType.ALL , mappedBy = "grade")
    private Classroom classroom;

    @OneToOne(cascade = CascadeType.ALL , mappedBy = "grade")
    private Course course;
}