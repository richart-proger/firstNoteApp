package com.notes.command_for_filter;

import com.notes.Note;
import com.notes.NotesManager;

import java.util.Set;

public class FilterByHashTag implements CommandForFilter {
    @Override
    public void executeFilter() {

        NotesManager.writeMessage("Filtering notes by hashtag...");

        if (!NotesManager.isNoteMapEmpty()) {
            NotesManager.writeMessage("Enter the hashtag:");
            String tagString = NotesManager.readString();

            Set<Note> resultSet = NotesManager.viewNotesFilteredByHashTag(tagString);

            NotesManager.viewNotesOrPrintMessage(resultSet, "There are no notes with this hashtag!");
        }

        NotesManager.toBeContinued();
    }
}
