package com.virusvaccine.common.exception;

public class DuplicateUserException extends RequestException{
    public DuplicateUserException(){
        super("이미 존재하는 회원 입니다.");
    }
}
