package com.virusvaccine.exception;

public class NoneExistentUserException extends RequestException{

    public NoneExistentUserException(){
        super("존재하지 않는 회원 입니다.");
    }
}
