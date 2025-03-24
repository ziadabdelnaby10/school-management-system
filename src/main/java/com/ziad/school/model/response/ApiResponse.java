package com.ziad.school.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private final LocalDateTime responseTime = LocalDateTime.now();
    private Integer statusCode;
    private String message;
    private T data;
}
