package com.notes.command.mainmenu;

import com.notes.NotesManager;

public class ExitCommand implements Command {
    @Override
    public void execute() {
        NotesManager.writeMessage("See you soon!");
    }
}
