package com.ziad.school.model.entity;

import com.ziad.school.model.base.Person;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity(name = "t_manager")
public class Manager extends Person {

    public Manager() {
        this.setRole("ROLE_MANAGER");
    }

    public Manager(String firstName, String lastName, Boolean isMale, String email, String password, String phone, String mobile, Date dateOfBirth, Boolean isActive) {
        super(firstName, lastName, isMale, email, "ROLE_MANAGER", password, phone, mobile, dateOfBirth, isActive);
    }
}