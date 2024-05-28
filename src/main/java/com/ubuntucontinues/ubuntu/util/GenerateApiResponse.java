package com.ubuntucontinues.ubuntu.util;

import org.springframework.http.HttpStatus;

public class GenerateApiResponse {
    public static  ApiResponse validationError(Object data) {
        return
                ApiResponse.builder()
                        .data(data)
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .isSuccessful(false)
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .build();

    }

}
