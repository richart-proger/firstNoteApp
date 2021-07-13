package com.notes.exception;

public class CannotCreateDirException extends RuntimeException {
    public static String message = "Failed to create a directory";

    public CannotCreateDirException() {
        super(message);
    }
}
