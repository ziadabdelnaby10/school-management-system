package com.ziad.school.model.entity;

import com.ziad.school.model.base.Person;
import com.ziad.school.model.base.SystemRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

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
        this.setRole(SystemRole.PARENT.toString());
    }

    public Parent(String firstName, String lastName, Boolean isMale, String email, String password, String phone, String mobile, Date dateOfBirth, Boolean isActive, Date createdAt, Date updatedAt, Set<Student> student) {
        super(firstName, lastName, isMale, email, SystemRole.PARENT.toString(), password, phone, mobile, dateOfBirth, isActive, createdAt, updatedAt);
        this.student = student;
    }
}