package com.fundodo.fayaospace.model.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiErrorResponse {
    private final String status;
    private final int code;
    private final Object data;

    public ApiErrorResponse(Object data, HttpStatus status) {
        this.status = status.name();
        this.code = status.value();
        this.data = data;
    }

    public ApiErrorResponse(Object data) {
        this(data, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ApiErrorResponse fail(Object data) {
        return new ApiErrorResponse(data);
    }

    public static ApiErrorResponse fail(Object data, HttpStatus status) {
        return new ApiErrorResponse(data, status);
    }
}
