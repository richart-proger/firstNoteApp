package com.notes.command_for_main_menu;

import com.notes.NotesManager;
import com.notes.command_for_search.Search;
import com.notes.command_for_search.SearchExecutor;

public class SearchCommonCommand implements Command {
    @Override
    public void execute() {
        if (!NotesManager.isNoteMapEmpty()) {
            NotesManager.writeMessage("Searching notes...");
            Search search = NotesManager.askSearch();
            SearchExecutor.executeSearch(search);
        }
    }
}
