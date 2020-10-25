package com.naru.katalk.exception;

public class DuplicateEmailException extends RegisterException {

    public DuplicateEmailException(final String message) {
        super(message);
    }
}
