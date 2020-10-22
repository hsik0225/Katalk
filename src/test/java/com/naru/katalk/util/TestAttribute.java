package com.naru.katalk.util;

public enum TestAttribute {

    // login
    EMAIL("test@naru.com"),
    PASSWORD("test1234"),
    CONFIRM_PASSWORD("test1234"),

    // register
    USER_NAME("naru"),
    PICTURE("s3.com");

    private final String attribute;

    TestAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }
}
