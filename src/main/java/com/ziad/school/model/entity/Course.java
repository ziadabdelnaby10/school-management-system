package com.ziad.school.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "t_course")
public class Course extends AbstractPersistable<Long> {
    @Column(length = 45)
    private String name;

    @Column(length = 45)
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "grade_id")
    private Grade grade;

    @OneToMany(mappedBy = "course")
    private Set<ExamResult> examResults = new HashSet<>();

    @ManyToMany(mappedBy = "courses",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    private Set<Student> students = new HashSet<>();
}