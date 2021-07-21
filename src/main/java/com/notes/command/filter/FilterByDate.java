package com.notes.command.filter;

import com.notes.Note;
import com.notes.NotesManager;

import java.util.Calendar;
import java.util.Set;

public class FilterByDate implements CommandForFilter {
    @Override
    public void executeFilter() {
        NotesManager.writeMessage("Filtering notes by date...");

        if (!NotesManager.isCacheEmpty()) {
            String message = "Enter the desired date, e.g., 01.07.2021: ";

            Calendar dateForFilter;
            do {
                NotesManager.writeMessage(message);
                String string = NotesManager.readString();
                dateForFilter = NotesManager.parseToCalendarFromString(string);
            } while (dateForFilter == null);

            Set<Note> resultSet = NotesManager.viewNotesFilteredByDate(dateForFilter);

            NotesManager.viewNotesOrPrintMessage(resultSet, "There are no notes according to the specified date!");
        }

        NotesManager.toBeContinued();
    }
}
