package com.company.abc.management.projects.api.utils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult<T> {
    private boolean success;
    private String message;
    private T data;
    private T errors;
}