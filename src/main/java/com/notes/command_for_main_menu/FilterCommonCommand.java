package com.notes.command_for_main_menu;

import com.notes.NotesManager;
import com.notes.command_for_filter.Filter;
import com.notes.command_for_filter.FilterExecutor;

public class FilterCommonCommand implements Command {
    @Override
    public void execute() {
        if (!NotesManager.isNoteMapEmpty()) {
            NotesManager.writeMessage("Filtering notes...");
            Filter filter = NotesManager.askFilter();
            FilterExecutor.executeFilter(filter);
        }
    }
}