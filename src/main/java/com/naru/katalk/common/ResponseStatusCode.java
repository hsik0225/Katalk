package com.naru.katalk.common;

// enum 클래스는 내부적으로 Enum<T>를 상속받고 있기 때문에 다른 클래스를 extends 할 수 없다
// enum 을 확장하여 사용하고 싶다면 인터페이스를 상속받아야 한다
public interface ResponseStatusCode {

    String getMessage();

    int getCode();

    String getStatusText();
}
