package com.fundodo.fayaospace.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class JwtTokenNotFoundException extends ApiException {
    public JwtTokenNotFoundException() {
        super(HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }

    public JwtTokenNotFoundException(Object body) {
        super(body);//
    }
}
