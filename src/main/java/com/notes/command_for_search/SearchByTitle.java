package com.notes.command_for_search;

import com.notes.Note;
import com.notes.NotesManager;

import java.util.Set;

public class SearchByTitle implements CommandForSearch {
    @Override
    public void executeSearch() {
        NotesManager.writeMessage("Search for notes by title...");

        if (!NotesManager.isNoteMapEmpty()) {
            NotesManager.writeMessage("Enter the title you are searching for: ");
            String title = NotesManager.readString();

            Set<Note> resultSet = NotesManager.searchNoteByTitle(title);

            NotesManager.viewNotesOrPrintMessage(resultSet, "There are no notes with the desired title!");
        }

        NotesManager.toBeContinued();
    }
}