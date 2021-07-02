package notes.command;

import notes.NotesManager;

public class ViewNoteCommand implements Command {
    @Override
    public void execute() {
        NotesManager.writeMessage("Просмотр заметок...");

        while (true) {
            if (NotesManager.getNoteListLength() > 0) {
                NotesManager.viewNotes(NotesManager.getNoteSet());
            } else {
                NotesManager.writeMessage("У вас пока нет ни одной заметки!");
            }
            NotesManager.writeMessage("Для продолжения нажмите Enter");
            NotesManager.readString();
            break;
        }
    }
}
