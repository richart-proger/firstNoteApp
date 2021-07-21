package com.notes.command.mainmenu;

import com.notes.NotesManager;
import com.notes.command.search.Search;
import com.notes.command.search.SearchExecutor;

public class SearchCommonCommand implements Command {
    @Override
    public void execute() {
        if (!NotesManager.isCacheEmpty()) {
            NotesManager.writeMessage("Searching notes...");
            Search search = NotesManager.askSearch();
            SearchExecutor.executeSearch(search);
        }
    }
}
