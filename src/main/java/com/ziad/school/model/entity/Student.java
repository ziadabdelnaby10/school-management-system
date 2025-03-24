package com.ziad.school.model.entity;

import com.ziad.school.model.enums.Year;
import com.ziad.school.model.base.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

//@Cacheable TODO Search For second level caching
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_student")
//@DynamicUpdate TODO Search About @DynamicUpdate
//@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class , property = "id")
public class Student extends Person {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Year currentYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
//    @JsonManagedReference
//    @JsonIgnoreProperties("student")
    private Set<Attendance> attendance = new HashSet<>();

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private Set<ExamResult> examResults = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "classroom_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "classroom_id")
    )
    private Set<Classroom> classrooms = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();

    //TODO Search About The @ElementCollection
//    @ElementCollection
//    @CollectionTable(
//            name = "nick_name"
//            , joinColumns = @JoinColumn(name = "student_id")
//    )
//    private List<String> nickNames = new ArrayList<>();
}