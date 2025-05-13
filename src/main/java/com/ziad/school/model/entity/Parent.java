package com.ziad.school.model.entity;

import com.ziad.school.model.base.Person;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "t_parent")
public class Parent extends Person {

    //TODO Test the Orphan removal attribute
    @OneToMany(mappedBy = "parent" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private Set<Student> student = new HashSet<>();

    public Parent() {
        this.setRole("ROLE_PARENT");
    }

    public Parent(String firstName, String lastName, Boolean isMale, String email, String password, String phone, String mobile, Date dateOfBirth, Boolean isActive, Set<Student> student) {
        super(firstName, lastName, isMale, email, "ROLE_PARENT", password, phone, mobile, dateOfBirth, isActive);
        this.student = student;
    }

}