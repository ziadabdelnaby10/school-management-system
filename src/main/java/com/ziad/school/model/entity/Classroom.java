package com.ziad.school.model.entity;

import com.ziad.school.model.base.StudyYear;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "t_classroom")
public class Classroom {
    @Id
    @Column(nullable = false , unique = true)
    private String name;


    @Column(length = 2 , unique = true)
    private String section;

    @Column
    private Boolean isAvailable;

    @Column(length = 45)
    private String remarks;

    @Enumerated(EnumType.STRING)
    private StudyYear year;

    @ManyToMany(fetch = FetchType.LAZY , mappedBy = "classrooms")
    private Set<Student> students = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY , mappedBy = "classrooms")
    private Set<Teacher> teachers = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "grade_id")
    private Grade grade;

}