package com.ziad.school.repository;

import com.ziad.school.model.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
//    @EntityGraph(attributePaths = {"student"})
//    @Query(nativeQuery = true, value = "SELECT * FROM t_attendance left join school_system.t_student ts on ts.id = t_attendance.student_id")
//    List<Attendance> findAllWithStudent();
}