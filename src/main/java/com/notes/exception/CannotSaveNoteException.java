package com.notes.exception;

public class CannotSaveNoteException extends Exception {
    public static String message = "Cannot save the note";

    public CannotSaveNoteException() {
        super(message);
    }
}
