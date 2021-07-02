package notes.command;

import notes.HashTag;
import notes.Note;
import notes.NotesManager;

import java.util.List;

public class FilterByHashtagCommand implements Command {
    @Override
    public void execute() {
        NotesManager.writeMessage("Фильтрация заметок по хэштегу...");

        while (true) {
            if (NotesManager.getNoteListLength() > 0) {
                NotesManager.writeMessage("Введите хэштег: ");
                String tagString = NotesManager.readString();
                HashTag hashTag = HashTag.getHashTag(tagString);

                List<Note> resultList = NotesManager.viewNotesFilteredByHashTag(hashTag);
                if (resultList.size() > 0) {
                    NotesManager.viewNotes(resultList);
                } else {
                    NotesManager.writeMessage("С таким хэштегом нет заметок!");
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
