package com.virusvaccine.common.exception;

public class NotLoginException extends RequestException{
    public NotLoginException(){
        super("로그인 상태가 아닙니다.");
    }
}
