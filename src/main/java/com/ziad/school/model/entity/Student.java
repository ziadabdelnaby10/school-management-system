package com.ziad.school.model.entity;

import com.ziad.school.model.base.Person;
import com.ziad.school.model.base.StudyYear;
import com.ziad.school.model.base.SystemRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//@Cacheable TODO Search For second level caching
@Getter
@Setter
@Entity(name = "t_student")
//@DynamicUpdate TODO Search About @DynamicUpdate
//@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class , property = "id")
public class Student extends Person {

    //Convert this to enumerated
//    @ManyToOne(cascade = CascadeType.ALL , optional = true)
    @Enumerated(EnumType.STRING)
    private com.ziad.school.model.base.StudyYear currentYear;

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


    public Student() {
        this.setRole(SystemRole.STUDENT.toString());
    }

    public Student(String firstName, String lastName, Boolean isMale, String email, String password, String phone,
                   String mobile, Date dateOfBirth, Boolean isActive, Date createdAt, Date updatedAt, StudyYear currentYear,
                   Parent parent, Set<Attendance> attendance, Set<ExamResult> examResults, Set<Classroom> classrooms, Set<Course> courses) {
        super(firstName, lastName, isMale, email, SystemRole.STUDENT.toString(), password, phone, mobile, dateOfBirth, isActive, createdAt, updatedAt);
        this.currentYear = currentYear;
        this.parent = parent;
        this.attendance = attendance;
        this.examResults = examResults;
        this.classrooms = classrooms;
        this.courses = courses;
    }
}