package com.ubuntucontinues.ubuntu.util;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
@Builder
@Data
public class ApiResponse {
    private Object data;
    private HttpStatus httpStatus;
    private int statusCode;
    private boolean isSuccessful;

}
