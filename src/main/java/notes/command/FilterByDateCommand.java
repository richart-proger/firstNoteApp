package notes.command;

import notes.Note;
import notes.NotesManager;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class FilterByDateCommand implements Command {
    @Override
    public void execute() {
        NotesManager.writeMessage("Фильтрация заметок по дате...");

        while (true) {
            if (NotesManager.getNoteListLength() > 0) {
                NotesManager.writeMessage("Введите желаемую дату, например, 01.07.2021: ");
                String string = NotesManager.readString();
                String[] dateString = string.split("\\.");
                int year = Integer.parseInt(dateString[2]);
                int month = Integer.parseInt(dateString[1]) - 1;
                int day = Integer.parseInt(dateString[0]);

                Calendar dateForFilter = new GregorianCalendar(year, month, day);
                List<Note> resultList = NotesManager.viewNotesFilteredByDate(dateForFilter);

                if (resultList.size() > 0) {
                    NotesManager.viewNotes(resultList);
                } else {
                    NotesManager.writeMessage("Нет заметок согласно указанной дате!");
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
