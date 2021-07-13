package com.notes.exception;

import com.notes.Note;

public class CannotCreateFileForNoteException extends RuntimeException {
    public static String message = "Failed to create file for note with ID %s";

    public CannotCreateFileForNoteException(Note note) {
        super(String.format(message, note.getId()));
    }
}
