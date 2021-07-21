package com.notes.command.filter;

import com.notes.NotesManager;

public class FilterBackToMainMenu implements CommandForFilter {
    @Override
    public void executeFilter() {
        NotesManager.writeMessage("");
    }
}
