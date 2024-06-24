package com.fundodo.fayaospace.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends ApiException {
    public NotFoundException() {
        super(HttpStatus.NOT_FOUND.getReasonPhrase());
    }

    public NotFoundException(Object body) {
        super(body);
    }
}
