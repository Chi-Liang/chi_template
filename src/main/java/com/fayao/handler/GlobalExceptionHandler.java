package com.fayao.handler;

import com.fayao.exception.ApiException;
import com.fayao.model.entity.LogSysAlertTrack;
import com.fayao.model.response.ApiErrorResponse;
import com.fayao.service.LogService;
import com.fayao.utils.HttpTools;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final LogService logService;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String defaultMessages = errors.stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(";"));

        log.error("MethodArgumentNotValidException {} :", defaultMessages);
        return ResponseEntity.badRequest().body(ApiErrorResponse.fail(defaultMessages, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiErrorResponse> apiExceptionHandler(ApiException e) throws IOException {
        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class);
        log.error("apiExceptionHandler {} :", e.getMessage(), e);
        return ResponseEntity.status(responseStatus.value()).body(ApiErrorResponse.fail(ExceptionUtils.getStackTrace(e), responseStatus.value()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> exceptionHandler(Exception e) throws IOException {
        log.error("exceptionHandler {} :", e.getMessage(), e);

        LogSysAlertTrack logSysAlertTrack = LogSysAlertTrack.builder()
                .alertMsg(ExceptionUtils.getStackTrace(e))
                .mid("mid")
                .alertUri(HttpTools.getQueryUri())
                .userIp(HttpTools.getIp())
                .build();

        logService.loggingAlert(logSysAlertTrack);

        return ResponseEntity.internalServerError().body(ApiErrorResponse.fail(ExceptionUtils.getStackTrace(e), HttpStatus.INTERNAL_SERVER_ERROR));
    }

}
