package com.notes.command.search;

import com.notes.Note;
import com.notes.NotesManager;

import java.util.Set;

public class SearchByTitle implements CommandForSearch {
    @Override
    public void executeSearch() {
        NotesManager.writeMessage("Search for notes by title...");

        if (!NotesManager.isCacheEmpty()) {
            NotesManager.writeMessage("Enter the title you are searching for: ");
            String title = NotesManager.readString();

            Set<Note> resultSet = NotesManager.searchNoteByTitle(title);

            NotesManager.viewNotesOrPrintMessage(resultSet, "There are no notes with the desired title!");
        }

        NotesManager.toBeContinued();
    }
}