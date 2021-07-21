package com.notes.command.filter;

import com.notes.Note;
import com.notes.NotesManager;

import java.util.Set;

public class FilterViewNotes implements CommandForFilter {
    @Override
    public void executeFilter() {
        NotesManager.writeMessage("Viewing notes...");

        if (!NotesManager.isCacheEmpty()) {
            Set<Note> noteSet = NotesManager.getNoteSetFromCache();
            NotesManager.viewNotes(noteSet);
        }

        NotesManager.toBeContinued();
    }
}
