package notes.command;

import notes.Note;
import notes.NotesManager;

import java.util.List;

public class SearchByTextCommand implements Command {
    @Override
    public void execute() {
        NotesManager.writeMessage("Поиск заметок по тексту...");

        while (true) {
            if (NotesManager.getNoteListLength() > 0) {
                NotesManager.writeMessage("Введите искомую подстроку: ");
                String string = NotesManager.readString();

                List<Note> resultList = NotesManager.searchNoteByText(string);
                if (resultList.size() > 0) {
                    NotesManager.viewNotes(resultList);
                } else {
                    NotesManager.writeMessage("Нет заметок с искомой подстрокой!");
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
