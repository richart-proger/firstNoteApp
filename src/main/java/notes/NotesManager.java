package notes;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class NotesManager {
    private static final String DIR = "NotesPackage\\notes.txt";
    private static final File dir = new File(DIR);
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static DateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private static Set<Note> noteSet = new HashSet<>();

    static {
        if (dir.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(dir))) {
                while (reader.ready()) {
                    String jsonString = reader.readLine();
                    StringReader stringReader = new StringReader(jsonString);

                    ObjectMapper mapper = new ObjectMapper();
                    Note note = mapper.readValue(stringReader, Note.class);
                    noteSet.add(note);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addNote(Note note) {
        noteSet.add(note);
    }

    public static void deleteNote(Note note) {
        List<Note> resultList = new ArrayList<>(noteSet);
        resultList.remove(note);
        noteSet.clear();
        noteSet.addAll(resultList);
    }

    public static void editNote(Note previous, Note modified) {
        if (noteSet.contains(previous)) {
            noteSet.remove(previous);
            noteSet.add(modified);
        }
    }

    public static List<Note> viewNotesFilteredByDate(Calendar dateForFilter) {
        List<Note> resultList = new ArrayList<>();

        for (Note note : noteSet) {
            Calendar dateNote = new GregorianCalendar();
            dateNote.setTime(note.getDate());

            if (dateNote.get(Calendar.YEAR) == dateForFilter.get(Calendar.YEAR) &&
                    dateNote.get(Calendar.MONTH) == dateForFilter.get(Calendar.MONTH) &&
                    dateNote.get(Calendar.DATE) == dateForFilter.get(Calendar.DATE)) {
                resultList.add(note);
            }
        }
        return resultList;
    }

    public static List<Note> viewNotesFilteredByHashTag(HashTag hashTag) {
        List<Note> resultList = new ArrayList<>();

        for (Note note : noteSet) {
            List<HashTag> hashTagList = note.getHashTagList();

            if (hashTagList.contains(hashTag)) {
                resultList.add(note);
            }
        }
        return resultList;
    }

    public static List<Note> searchNoteByText(String text) {
        List<Note> resultList = new ArrayList<>();

        for (Note note : noteSet) {
            if (note.getText().contains(text)) {
                resultList.add(note);
            }
        }

        return resultList;
    }

    public static List<Note> searchNoteByTitle(String title) {
        List<Note> resultList = new ArrayList<>();

        for (Note note : noteSet) {
            if (note.getTitle().contains(title)) {
                resultList.add(note);
            }
        }

        return resultList;
    }

    public static Note searchNoteByTextForModifyFunc(String text) {
        Note note = null;
        for (Note n : noteSet) {
            if (n.getText().equals(text)) {
                note = n;
            }
        }
        return note;
    }

    public static int getNoteListLength() {
        return noteSet.size();
    }

    public static Set<Note> getNoteSet() {
        return noteSet;
    }

    public static void viewNotes(Collection<Note> notes) {
        int i = 1;
        for (Note note : notes) {
            System.out.println("NOTE №" + i);
            System.out.println("Title: " + note.getTitle());
            System.out.println("Text: " + note.getText());

            System.out.print("Hashtag: ");
            if (note.getHashTagList().size() > 0) {
                HashTag.printHashtags(note.getHashTagList());
            } else {
                System.out.println();
            }
            System.out.println("Date: " + simpleDateFormat.format(note.getDate()) + "\n");
            i++;
        }
    }

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        try {
            String string = bis.readLine();
            if (string.toLowerCase().equals("exit")) {
//                throw new InterruptOperationException();
            }
            return string;
        } catch (IOException e) {
            // IGNORE
        }
        return null;
    }

    public static Function askOperation() {
        while (true) {
            NotesManager.writeMessage("Выберите функцию:");
            NotesManager.writeMessage("\t 1 - добавить заметку");
            NotesManager.writeMessage("\t 2 - удалить заметку");
            NotesManager.writeMessage("\t 3 - редактировать заметку");
            NotesManager.writeMessage("\t 4 - просмотреть заметки");
            NotesManager.writeMessage("\t 5 - фильтровать по дате");
            NotesManager.writeMessage("\t 6 - фильтровать по хэштегу");
            NotesManager.writeMessage("\t 7 - поиск по тексту");
            NotesManager.writeMessage("\t 8 - поиск по названию");
            NotesManager.writeMessage("\t 9 - выход");
            String numberOfOperation = NotesManager.readString().trim();

            if (numberOfOperation.length() == 1) {
                try {
                    int n = Integer.parseInt(numberOfOperation);
                    return Function.getAllowableFunctionByNumber(n);
                } catch (IllegalArgumentException e) {
                    NotesManager.writeMessage("Такой функции нет в списке.");
                }
            }
        }
    }

}
