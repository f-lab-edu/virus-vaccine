package com.virusvaccine.exceptioncontroller;

import com.virusvaccine.exception.RequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;


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

}
