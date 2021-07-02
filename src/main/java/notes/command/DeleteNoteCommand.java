package notes.command;

import notes.Note;
import notes.NotesManager;

public class DeleteNoteCommand implements Command {
    @Override
    public void execute() {
        NotesManager.writeMessage("Удаление заметки...");

        while (true) {
            if (NotesManager.getNoteListLength() > 0) {
                NotesManager.writeMessage("Введите текст, содержащийся в заметке, которую нужно удалить:");
                String text = NotesManager.readString();
                Note note = NotesManager.searchNoteByTextForModifyFunc(text);

                if (note != null) {
                    NotesManager.deleteNote(note);
                    NotesManager.writeMessage("Заметка успешно удалена!");
                } else {
                    NotesManager.writeMessage("Заметки, с указанным текстом, не найдено!");
                }
            } else {
                NotesManager.writeMessage("У вас пока нет ни одной заметки!");
            }
            NotesManager.writeMessage("Для продолжения нажмите Enter");
            NotesManager.readString();
            break;
        }
    }
}
