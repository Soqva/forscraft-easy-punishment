package com.s0qva.easypunishment.client.exception;

public class ClassMismatchException extends RuntimeException {
    private static final String DEFAULT_MISMATCH_EXCEPTION_MESSAGE = "Provided types are not equal";

    public ClassMismatchException() {
        this(DEFAULT_MISMATCH_EXCEPTION_MESSAGE);
    }

    public ClassMismatchException(String message) {
        super(message);
    }

    public ClassMismatchException(Throwable cause) {
        this(DEFAULT_MISMATCH_EXCEPTION_MESSAGE, cause);
    }

    public ClassMismatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
