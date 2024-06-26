package com.fayao.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public abstract class ApiException extends RuntimeException {
    @NonNull
    private Object body;
}
