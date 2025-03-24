package com.ziad.school.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "t_exam_result")
public class ExamResult extends AbstractPersistable<Long> {

  @ManyToOne
  @JoinColumn(name = "student_id")
  private Student student;

  @ManyToOne(cascade = CascadeType.ALL , optional = false)
  @JoinColumn(name = "course_id")
  private Course course;

  @OneToOne(cascade = CascadeType.ALL)
  private Exam exam;

  @Column(length = 45 , nullable = false)
  private String marks;
}