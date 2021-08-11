package com.virusvaccine.exception;

//  잘못된 요청으로 발생하는 커스텀 예외들은 이 클래스를 상속받는다.
public class RequestException extends RuntimeException{
    public RequestException(String message){
        super(message);
    }
}
