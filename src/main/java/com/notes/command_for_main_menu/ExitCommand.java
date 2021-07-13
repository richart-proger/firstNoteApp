package com.notes.command_for_main_menu;

import com.notes.NotesManager;

public class ExitCommand implements Command {
    @Override
    public void execute() {
        NotesManager.writeMessage("See you soon!");
    }
}
