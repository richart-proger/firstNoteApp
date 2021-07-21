package com.notes.command.mainmenu;

import com.notes.exception.CannotSaveNoteException;
import com.notes.exception.ThereIsNoNoteWithIdException;

import java.util.HashMap;
import java.util.Map;

/**
 * The modules of the mainmenu package contain classes that are responsible for the operation of the main menu, which contains the main functions of the notes application: create, delete, edit. The work of the class is carried out by the implementation of the command pattern
 */
public class CommandExecutor {
    private static final Map<Function, Command> allKnownFunctionsMap = new HashMap<>();

    static {
        allKnownFunctionsMap.put(Function.ADD, new AddNoteCommand());
        allKnownFunctionsMap.put(Function.DELETE, new DeleteNoteCommand());
        allKnownFunctionsMap.put(Function.EDIT, new EditNoteCommand());
        allKnownFunctionsMap.put(Function.FILTER, new FilterCommonCommand());
        allKnownFunctionsMap.put(Function.SEARCH, new SearchCommonCommand());
        allKnownFunctionsMap.put(Function.EXIT, new ExitCommand());
    }

    private CommandExecutor() {
    }

    public static void execute(Function function) throws CannotSaveNoteException, ThereIsNoNoteWithIdException {
        allKnownFunctionsMap.get(function).execute();
    }
}
