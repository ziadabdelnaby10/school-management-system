package com.ziad.school.repository;

import com.ziad.school.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID>, JpaSpecificationExecutor<Student> {
    @Query("select (count(s) > 0) from t_student s where s.email = ?1")
    boolean existsByEmail(String email);

    @Query("select s from t_student s where s.email = ?1")
    Student findByEmail(String email);

    @Query("select count(s) from t_student s where s.isMale = ?1")
    Long countAllByIsMale(Boolean isMale);

    @Query("select count(s) from t_student s")
    Long countAll();

    @Query("select s from t_student s left join fetch Attendance where s.id = ?1")
//    @EntityGraph(attributePaths = {"attendance"})
    Student findStudentWithAttendances(UUID id);

    @Query("select s from t_student s left join fetch t_parent where s.id = ?1")
    Student findStudentWithParent(UUID id);

    @Query("select s from t_student s left join fetch Course where s.id = ?1")
    Student findStudentWithCourses(UUID id);

    @Query("select s from t_student s left join fetch Classroom where s.id = ?1")
    Student findStudentWithClassrooms(UUID id);





    //For Projections
    //TODO Search for using projection
//    <T> T findById(UUID id, Class<T> clazz);

//    <T> List<T> findAllById(Iterable<UUID> ids, Class<T> clazz);

//    <T> List<T> findAll(Class<T> clazz);
}