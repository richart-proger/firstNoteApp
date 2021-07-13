package com.notes.command_for_filter;

import com.notes.Note;
import com.notes.NotesManager;

import java.util.Set;

public class FilterViewNotes implements CommandForFilter {
    @Override
    public void executeFilter() {
        NotesManager.writeMessage("Viewing notes...");

        if (!NotesManager.isNoteMapEmpty()) {
            Set<Note> noteSet = NotesManager.getNoteSetFromMap();
            NotesManager.viewNotes(noteSet);
        }

        NotesManager.toBeContinued();
    }
}
