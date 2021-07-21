package com.notes;

import com.notes.command.mainmenu.CommandExecutor;
import com.notes.command.mainmenu.Function;
import com.notes.exception.CannotSaveNoteException;
import com.notes.exception.ThereIsNoNoteWithIdException;

public class MainApp {
    public static void main(String[] args) {
        Function function;
        do {
            function = NotesManager.askOperation();
            try {
                CommandExecutor.execute(function);
            } catch (CannotSaveNoteException | ThereIsNoNoteWithIdException e) {
                NotesManager.writeMessage(e.getLocalizedMessage());
            }
        } while (function != Function.EXIT);
    }
}