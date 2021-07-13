package com.notes.command_for_main_menu;

import com.notes.exception.CannotSaveNoteException;
import com.notes.exception.ThereIsNoNoteWithIdException;

public interface Command {
    void execute() throws CannotSaveNoteException, ThereIsNoNoteWithIdException;
}
