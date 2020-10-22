package com.naru.katalk.exception;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(final String message) {
        super(message);
    }
}
