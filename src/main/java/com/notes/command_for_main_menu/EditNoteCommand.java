package com.notes.command_for_main_menu;

import com.notes.FileOperationService;
import com.notes.Note;
import com.notes.NotesManager;
import com.notes.exception.ThereIsNoNoteWithIdException;

import java.util.Set;

public class EditNoteCommand implements Command {
    @Override
    public void execute() throws ThereIsNoNoteWithIdException {
        NotesManager.writeMessage("Editing a note...");

        if (!NotesManager.isNoteMapEmpty()) {
            String message = "Enter the ID of the note you want to edit:";

            long id = NotesManager.parseToIdFromString(message);
            Note notePrev = NotesManager.getNoteById(id);

            if (notePrev != null) {
                NotesManager.writeMessage("Enter a new title (or leave it empty):");
                String titleNew = NotesManager.readString();

                NotesManager.writeMessage("Enter a new text:");
                String textNew = NotesManager.readString();

                Set<String> hashTagSetNew = NotesManager.addHashtagIntoHashtagSet("Enter a new hashtag of the note (or leave it empty):");

                Note noteModif = new Note(textNew, notePrev.getDate(), hashTagSetNew, titleNew);

                if (FileOperationService.editFileNote(notePrev, noteModif)) {
                    NotesManager.editNote(notePrev, noteModif);
                    NotesManager.writeMessage("The note was successfully changed!\n");
                }
            } else {
                throw new ThereIsNoNoteWithIdException();
            }
        }
        NotesManager.toBeContinued();
    }
}
