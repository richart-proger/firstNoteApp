package com.notes.command.search;

import com.notes.NotesManager;

public class SearchBackToMainMenu implements CommandForSearch {
    @Override
    public void executeSearch() {
        NotesManager.writeMessage("");
    }
}
