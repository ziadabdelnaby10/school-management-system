package com.ziad.school.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "t_exam_type")
public class ExamType {
  @Id
  @Column(length = 45 , unique = true , nullable = false)
  private String name;
  @Column(length = 45)
  private String description;

}