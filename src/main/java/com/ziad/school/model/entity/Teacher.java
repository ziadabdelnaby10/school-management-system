package com.ziad.school.model.entity;

import com.ziad.school.model.base.Person;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "t_teacher")
public class Teacher extends Person {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "classroom_teacher" ,
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "classroom_id")
    )
    private Set<Classroom> classrooms = new HashSet<>();

    @ManyToOne
    private Subject teachingSubject;

    public Teacher() {
        this.setRole("ROLE_TEACHER");
    }

    public Teacher(String firstName, String lastName, Boolean isMale, String email, String password, String phone, String mobile, Date dateOfBirth, Boolean isActive, Set<Classroom> classrooms, Subject teachingSubject) {
        super(firstName, lastName, isMale, email, "ROLE_TEACHER", password, phone, mobile, dateOfBirth, isActive);
        this.classrooms = classrooms;
        this.teachingSubject = teachingSubject;
    }
}