package com.virusvaccine.exception;

public class WrongPasswordException extends RequestException{

    public WrongPasswordException(){
        super("잘못된 비밀번호 입니다.");
    }
}
