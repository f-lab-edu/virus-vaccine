package com.virusvaccine.common.exception;

public class NotIdenticalPasswordException extends RequestException{
    public NotIdenticalPasswordException(){
        super("비밀번호가 일치하지 않습니다.");
    }
}
