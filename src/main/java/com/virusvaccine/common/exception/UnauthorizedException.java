package com.virusvaccine.common.exception;

public class UnauthorizedException extends RequestException {
    public UnauthorizedException() {
        super("접근 권한이 없습니다.");
    }
}
