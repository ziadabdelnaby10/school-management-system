package com.ziad.school.model.entity;

import com.ziad.school.model.enums.Subject;
import com.ziad.school.model.base.Person;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "t_teacher")
public class Teacher extends Person {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "classroom_teacher" ,
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "classroom_id")
    )
    private Set<Classroom> classrooms = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Subject teachingSubject;
}