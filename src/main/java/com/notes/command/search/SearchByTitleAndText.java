package com.notes.command.search;

import com.notes.Note;
import com.notes.NotesManager;

import java.util.Set;

public class SearchByTitleAndText implements CommandForSearch {
    @Override
    public void executeSearch() {
        NotesManager.writeMessage("Search for notes by title and text...");

        if (!NotesManager.isCacheEmpty()) {
            NotesManager.writeMessage("Enter the substring you are searching for: ");
            String string = NotesManager.readString();

            Set<Note> resultSet = NotesManager.searchNoteByTitle(string);
            Set<Note> resultSetAdd = NotesManager.searchNoteByText(string);
            resultSet.addAll(resultSetAdd);

            NotesManager.viewNotesOrPrintMessage(resultSet, "There are no notes with the desired substring!");
        }

        NotesManager.toBeContinued();
    }
}