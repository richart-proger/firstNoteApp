package com.notes.command.mainmenu;

import com.notes.NotesManager;
import com.notes.command.filter.Filter;
import com.notes.command.filter.FilterExecutor;

public class FilterCommonCommand implements Command {
    @Override
    public void execute() {
        if (!NotesManager.isCacheEmpty()) {
            NotesManager.writeMessage("Filtering notes...");
            Filter filter = NotesManager.askFilter();
            FilterExecutor.executeFilter(filter);
        }
    }
}