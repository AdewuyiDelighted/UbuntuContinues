package com.ubuntucontinues.ubuntu.dto.responses;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiResponse<T> {
    private boolean status;
    private T data;
}
