package com.notes.exception;

public class ThereIsNoNoteWithIdException extends Exception {
    private static String message = "There is no note with the specified ID!\n";

    public ThereIsNoNoteWithIdException() {
        super(message);
    }
}
