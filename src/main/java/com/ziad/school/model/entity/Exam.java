package com.ziad.school.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "t_exam")
public class Exam extends AbstractPersistable<Long> {
    @Column(length = 45)
    private String name;

    @Column
    @Temporal(TemporalType.DATE)
    private Date start_date;

    @ManyToOne
    private ExamType examType;



}