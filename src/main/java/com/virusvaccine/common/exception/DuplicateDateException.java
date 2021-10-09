package com.virusvaccine.common.exception;

public class DuplicateDateException extends RequestException{
  public DuplicateDateException(){
    super("동일한 날짜에 또 예약 할 수 없습니다.");
  }
}
