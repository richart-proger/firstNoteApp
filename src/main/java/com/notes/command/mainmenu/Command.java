package com.notes.command.mainmenu;

import com.notes.exception.CannotSaveNoteException;
import com.notes.exception.ThereIsNoNoteWithIdException;

public interface Command {
    void execute() throws CannotSaveNoteException, ThereIsNoNoteWithIdException;
}
