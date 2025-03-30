package com.ziad.school.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.ziad.school.model.entity.Attendance}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceInfo implements Serializable {
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "yyyy/MM/dd@HH:mm:ss")
    private LocalDateTime dateOfAttendance;
    private Boolean isAttendance;
    private String remark;
}