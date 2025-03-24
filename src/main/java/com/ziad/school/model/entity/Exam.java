package com.ziad.school.model.entity;

import jakarta.persistence.*;
import lombok.*;
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

    @OneToOne(orphanRemoval = true)
    private ExamType examType;
}