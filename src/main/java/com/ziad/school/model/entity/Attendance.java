package com.ziad.school.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "t_attendance")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class , property = "id")
public class Attendance extends AbstractPersistable<Long> {
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private final LocalDateTime dateOfAttendance = LocalDateTime.now();

    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
//  @JsonBackReference
//  @JsonIgnoreProperties("attendance")
    private Student student;

    @Column
    private Boolean isAttendance;

    @Column
    private String remark;

    //TODO Search for @Version
//  @Version
//  private Long version;
}