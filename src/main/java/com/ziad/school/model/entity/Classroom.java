package com.ziad.school.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "t_classroom")
public class Classroom extends AbstractPersistable<Long> {
    @Column(columnDefinition = "YEAR")
    private Integer year;

    @Column(length = 2 , unique = true)
    private String section;

    @Column
    private Boolean isAvailable;

    @Column(length = 45)
    private String remarks;

    @ManyToMany(fetch = FetchType.LAZY , mappedBy = "classrooms")
    private Set<Student> students = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY , mappedBy = "classrooms")
    private Set<Teacher> teachers = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "grade_id")
    private Grade grade;

}