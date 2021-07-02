package notes.command;

import notes.Note;
import notes.NotesManager;

import java.util.List;

public class SearchByTitleCommand implements Command {
    @Override
    public void execute() {
        NotesManager.writeMessage("Поиск заметок по названию...");

        while (true) {
            if (NotesManager.getNoteListLength() > 0) {
                NotesManager.writeMessage("Введите искомое название: ");
                String title = NotesManager.readString();

                List<Note> resultList = NotesManager.searchNoteByTitle(title);
                if (resultList.size() > 0) {
                    NotesManager.viewNotes(resultList);
                } else {
                    NotesManager.writeMessage("Нет заметок с искомым названием!");
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
