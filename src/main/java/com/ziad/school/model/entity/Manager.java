package com.ziad.school.model.entity;

import com.ziad.school.model.base.Person;
import com.ziad.school.model.base.SystemRole;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity(name = "t_manager")
public class Manager extends Person {

    public Manager() {
        this.setRole(SystemRole.ROLE_MANAGER.toString());
    }

    public Manager(String firstName, String lastName, Boolean isMale, String email, String password, String phone, String mobile, Date dateOfBirth, Boolean isActive, Date createdAt, Date updatedAt) {
        super(firstName, lastName, isMale, email, SystemRole.ROLE_MANAGER.toString(), password, phone, mobile, dateOfBirth, isActive, createdAt, updatedAt);
    }
}