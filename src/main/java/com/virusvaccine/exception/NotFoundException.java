package com.virusvaccine.exception;

public class NotFoundException extends RequestException{
    public NotFoundException(){
        super("결과가 존재하지 않습니다.");
    }
}
