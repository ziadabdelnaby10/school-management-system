package com.ziad.school.model.entity;

import com.ziad.school.model.base.Person;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_parent")
public class Parent extends Person {

    //TODO Test the Orphan removal attribute
    @OneToMany(mappedBy = "parent" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private Set<Student> student = new HashSet<>();
}