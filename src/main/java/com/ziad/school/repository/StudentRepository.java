package com.ziad.school.repository;

import com.ziad.school.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID>, JpaSpecificationExecutor<Student> {
    @Query("select (count(s) > 0) from Student s where s.email = ?1")
    boolean existsByEmail(String email);

    @Query("select s from Student s where s.email = ?1")
    Student findByEmail(String email);

    @Query("select count(s) from Student s where s.isMale = ?1")
    Long countAllByIsMale(Boolean isMale);

    @Query("select count(s) from Student s")
    Long countAll();

    @Query("select s from Student s left join fetch Attendance where s.id = ?1")
//    @EntityGraph(attributePaths = {"attendance"})
    Student findAllWithAttendance(UUID id);



    //For Projections
    //TODO Search for using projection
//    <T> T findById(UUID id, Class<T> clazz);

//    <T> List<T> findAllById(Iterable<UUID> ids, Class<T> clazz);

//    <T> List<T> findAll(Class<T> clazz);
}