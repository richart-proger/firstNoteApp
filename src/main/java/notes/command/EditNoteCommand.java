package notes.command;

import notes.HashTag;
import notes.Note;
import notes.NotesManager;

import java.util.ArrayList;
import java.util.List;

public class EditNoteCommand implements Command {
    @Override
    public void execute() {
        NotesManager.writeMessage("Редактирование заметки...");

        while (true) {
            if (NotesManager.getNoteListLength() > 0) {
                NotesManager.writeMessage("Введите текст, содержащийся в заметке, которую нужно редактировать:");
                String textPrev = NotesManager.readString();
                Note notePrev = NotesManager.searchNoteByTextForModifyFunc(textPrev);

                if (notePrev != null) {
                    NotesManager.writeMessage("Введите новое название заметки, если необходимо:");
                    String titleNew = NotesManager.readString();
                    NotesManager.writeMessage("Введите новый текст заметки:");
                    String textNew = NotesManager.readString();

                    String hashtag = null;
                    List<HashTag> hashTagListNew = new ArrayList<>();
                    while (hashtag == null || !hashtag.equals("")) {
                        NotesManager.writeMessage("Введите новый хэштег, либо нажмите Enter для продолжения:");
                        hashtag = NotesManager.readString();

                        if (!hashtag.equals("")){
                            hashTagListNew.add(HashTag.getHashTag(hashtag));
                        } else {
                            break;
                        }
                    }
                    Note noteModif = new Note(textNew, notePrev.getDate(), hashTagListNew, titleNew);
                    NotesManager.editNote(notePrev, noteModif);
                    NotesManager.writeMessage("Заметка успешно изменена!");
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
