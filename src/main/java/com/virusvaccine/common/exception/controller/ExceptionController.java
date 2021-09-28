package com.virusvaccine.common.exception.controller;

import com.virusvaccine.common.exception.NotLoginException;
import com.virusvaccine.common.exception.RequestException;
import com.virusvaccine.common.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class ExceptionController {
    // 잘못된 요청으로 발생하는 예외를 처리하는 핸들러
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RequestException.class)
    public HashMap<String, String> RequestExceptionController(RequestException e){

        HashMap<String, String> exceptionResponse = new HashMap<>();

        exceptionResponse.put("ExceptionType", e.getClass().getSimpleName());
        exceptionResponse.put("ExceptionMessage", e.getMessage());

        return exceptionResponse;
    }
    // 서버 내부 동작에서 발생하는 예외를 처리하는 핸들러
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public String InternalExceptionController(RuntimeException e){
        return e.getClass().getSimpleName();
    }

    //Validation Exception 예외 처리 핸들러
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Map<String, String>> handleUnauthorizedException(UnauthorizedException ex){
        Map<String, String> errors = new HashMap<>();

        errors.put("ExceptionType", ex.getClass().getSimpleName());
        errors.put("ExceptionMessage", ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errors);
    }

    @ExceptionHandler(NotLoginException.class)
    public ResponseEntity<Map<String, String>> handleNotLoginException(NotLoginException ex){
        Map<String, String> errors = new HashMap<>();

        errors.put("ExceptionType", ex.getClass().getSimpleName());
        errors.put("ExceptionMessage", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errors);
    }
}
