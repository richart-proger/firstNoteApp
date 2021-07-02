package notes.command;

import notes.HashTag;
import notes.Note;
import notes.NotesManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddNoteCommand implements Command {
    private DateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    @Override
    public void execute() {
        NotesManager.writeMessage("Создание заметки...");

        while (true) {
            String title;
            String text = null;
            String hashtag = null;
            Date date;

            NotesManager.writeMessage("Введите название заметки (возможно оставить пустым):");
            title = NotesManager.readString();

            while (text == null || text.equals("")) {
                NotesManager.writeMessage("Введите текст заметки:");
                text = NotesManager.readString();
            }

            List<HashTag> hashTagList = new ArrayList<>();

            while (hashtag == null || !hashtag.equals("")) {
                NotesManager.writeMessage("Введите хэштег, либо нажмите Enter для продолжения:");
                hashtag = NotesManager.readString();

                if (!hashtag.equals("")) {
                    hashTagList.add(HashTag.getHashTag(hashtag));
                } else {
                    break;
                }
            }

            date = new Date();
            NotesManager.writeMessage("Дата создания заметки: " + simpleDateFormat.format(date));
            NotesManager.writeMessage("Готово!\n");

            Note note = new Note(text, date, hashTagList, title);
            NotesManager.addNote(note);
            break;
        }
    }
}
