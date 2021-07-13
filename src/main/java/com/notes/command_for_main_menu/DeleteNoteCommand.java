package com.notes.command_for_main_menu;

import com.notes.FileOperationService;
import com.notes.NotesManager;
import com.notes.exception.ThereIsNoNoteWithIdException;

public class DeleteNoteCommand implements Command {
    @Override
    public void execute() throws ThereIsNoNoteWithIdException {
        NotesManager.writeMessage("Deleting a note...");

        if (!NotesManager.isNoteMapEmpty()) {
            String message = "Enter the ID of the note you want to delete:";

            long id = NotesManager.parseToIdFromString(message);

            if (FileOperationService.deleteFileNote(id)) {
                NotesManager.deleteNote(id);
                NotesManager.writeMessage("The note was successfully deleted!\n");
            } else {
                throw new ThereIsNoNoteWithIdException();
            }
        }
        NotesManager.toBeContinued();
    }
}
