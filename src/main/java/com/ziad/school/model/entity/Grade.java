package com.ziad.school.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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