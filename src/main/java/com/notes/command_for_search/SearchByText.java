package com.notes.command_for_search;

import com.notes.Note;
import com.notes.NotesManager;

import java.util.Set;

public class SearchByText implements CommandForSearch {
    @Override
    public void executeSearch() {
        NotesManager.writeMessage("Search for notes by text...");

        if (!NotesManager.isNoteMapEmpty()) {
            NotesManager.writeMessage("Enter the substring you are looking for: ");

            String string = NotesManager.readString();
            Set<Note> resultSet = NotesManager.searchNoteByText(string);

            NotesManager.viewNotesOrPrintMessage(resultSet, "There are no notes with the desired substring!");
        }

        NotesManager.toBeContinued();
    }
}
