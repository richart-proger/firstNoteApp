package com.notes.command_for_main_menu;

import com.notes.FileOperationService;
import com.notes.Note;
import com.notes.NotesManager;
import com.notes.exception.CannotSaveNoteException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class AddNoteCommand implements Command {
    private DateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    @Override
    public void execute() throws CannotSaveNoteException {
        NotesManager.writeMessage("Creating a note...");

        String title;
        String text;
        Date date;
        Set<String> hashTagSet;

        NotesManager.writeMessage("Enter the title of the note (or leave it empty):");
        title = NotesManager.readString();

        do {
            NotesManager.writeMessage("Enter the text of the note:");
            text = NotesManager.readString();
        }
        while (text == null || text.equals(""));

        hashTagSet = NotesManager.addHashtagIntoHashtagSet("Enter the hashtag of the note (or leave it empty):");

        date = new Date();
        NotesManager.writeMessage("Date of note creation: " + simpleDateFormat.format(date));

        Note note = new Note(text, date, hashTagSet, title);

        if (FileOperationService.noteToFileNote(note)) {
            NotesManager.addNote(note);
            NotesManager.writeMessage("Done!\n");
        } else {
            throw new CannotSaveNoteException();
        }
    }
}
