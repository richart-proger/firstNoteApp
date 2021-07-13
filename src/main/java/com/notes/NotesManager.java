package com.notes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notes.command_for_filter.Filter;
import com.notes.command_for_main_menu.Function;
import com.notes.command_for_search.Search;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * The class contains methods for working with notes (adding, deleting, editing, searching, filtering) with
 * the participation of a cache for fast information processing (HashMap). Some methods access the
 * FileOperationService.
 */
public class NotesManager {
    private static final String DIR = "notes_package/";
    private static final File dir = new File(DIR);

    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static DateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    private static Map<Long, Note> noteMap = new HashMap<>();

    /*
      A static initialization block in which, when the application starts, all json files are subtracted from
      the dir directory. This is done using the getListOfFileNotes() method of the FileOperationService class.
     */
    static {
        if (dir.exists()) {
            List<File> fileList = FileOperationService.getListOfFileNotes(dir);

            for (File file : fileList) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    while (reader.ready()) {
                        String jsonString = reader.readLine();
                        StringReader stringReader = new StringReader(jsonString);

                        ObjectMapper mapper = new ObjectMapper();
                        Note note = mapper.readValue(stringReader, Note.class);
                        noteMap.put(note.getId(), note);
                    }
                } catch (IOException e) {
                    // NOPE
                }
            }
        }
    }

    /**
     * Adding a note to the cache (HashMap)
     *
     * @param note
     */
    public static void addNote(Note note) {
        if (note != null) {
            noteMap.put(note.getId(), note);
        }
    }

    /**
     * Deleting a note from the cache (HashMap) according to the id
     *
     * @param id
     */
    public static void deleteNote(Long id) {
        noteMap.remove(id);
    }

    /**
     * Editing a note in the cache (HashMap)
     *
     * @param previous
     * @param modified
     */
    public static void editNote(Note previous, Note modified) {
        if (previous != null && modified != null) {
            if (noteMap.containsKey(previous.getId())) {
                noteMap.remove(previous.getId());
                noteMap.put(modified.getId(), modified);
            }
        }
    }

    /**
     * Returns a set of notes by specified date (calendar).
     *
     * @param dateForFilter
     * @return Set<Note>
     */
    public static Set<Note> viewNotesFilteredByDate(Calendar dateForFilter) {
        Set<Note> resultSet = new HashSet<>();

        for (Note note : getNoteSetFromMap()) {
            Calendar dateNote = new GregorianCalendar();
            dateNote.setTime(note.getDate());

            if (dateNote.get(Calendar.YEAR) == dateForFilter.get(Calendar.YEAR) &&
                    dateNote.get(Calendar.MONTH) == dateForFilter.get(Calendar.MONTH) &&
                    dateNote.get(Calendar.DATE) == dateForFilter.get(Calendar.DATE)) {
                resultSet.add(note);
            }
        }
        return resultSet;
    }

    /**
     * Returns a set of notes for the specified hashtag.
     *
     * @param hashTag
     * @return Set<Note>
     */
    public static Set<Note> viewNotesFilteredByHashTag(String hashTag) {
        Set<Note> resultSet = new HashSet<>();

        for (Map.Entry<Long, Note> entry : noteMap.entrySet()) {
            Set<String> hashTagList = entry.getValue().getHashTagSet();

            if (hashTagList.contains(hashTag)) {
                resultSet.add(entry.getValue());
            }
        }
        return resultSet;
    }

    /**
     * Returns a set of notes for the specified substring.
     *
     * @param text
     * @return Set<Note>
     */
    public static Set<Note> searchNoteByText(String text) {
        Set<Note> resultSet = new HashSet<>();

        for (Note note : getNoteSetFromMap()) {
            if (note.getText().toLowerCase().contains(text.toLowerCase())) {
                resultSet.add(note);
            }
        }
        return resultSet;
    }

    /**
     * Returns a set of notes for the specified title.
     *
     * @param title
     * @return Set<Note>
     */
    public static Set<Note> searchNoteByTitle(String title) {
        Set<Note> resultSet = new HashSet<>();

        for (Note note : getNoteSetFromMap()) {
            if (note.getTitle().toLowerCase().contains(title.toLowerCase())) {
                resultSet.add(note);
            }
        }
        return resultSet;
    }

    /**
     * Returns a note for the specified id.
     *
     * @param id
     * @return Note
     */
    public static Note getNoteById(Long id) {
        Note note = null;
        if (id != 0) {
            if (noteMap.containsKey(id)) {
                note = noteMap.get(id);
            }
        }
        return note;
    }

    /**
     * The method allows you to enter multiple hashtags until ""is entered. If you enter ""
     * immediately when starting the method, the method will terminate its action.
     * Returns Set<String>
     *
     * @param serviceMessage
     * @return Set<String>
     */
    public static Set<String> addHashtagIntoHashtagSet(String serviceMessage) {
        String hashtag;
        Set<String> hashTagSet = new HashSet<>();

        do {
            NotesManager.writeMessage(serviceMessage);
            hashtag = NotesManager.readString().toLowerCase();
            if (hashTagSet.size() != 0 && hashtag.equals("")) {
                break;
            } else {
                hashTagSet.add(hashtag);
            }
        } while (!hashtag.equals(""));
        return hashTagSet;
    }

    /**
     * Returns Map<Long, Note> noteMap
     *
     * @return Map<Long       ,               Note>
     */
    private static Map<Long, Note> getNoteMap() {
        return noteMap;
    }

    /**
     * Returns Set<Note> which is derived from Map<Long, Note> noteMap
     *
     * @return Set<Note>
     */
    public static Set<Note> getNoteSetFromMap() {
        Set<Note> noteSet = new HashSet<>();

        for (Map.Entry<Long, Note> entry : getNoteMap().entrySet()) {
            noteSet.add(entry.getValue());
        }
        return noteSet;
    }

    /**
     * Returns true if Map<Long, Note> noteMap is empty or false if it contains elements.
     *
     * @return boolean
     */
    public static boolean isNoteMapEmpty() {
        boolean status = false;

        if (noteMap.size() > 0) {
            status = true;
        } else {
            NotesManager.writeMessage("You don't have any notes yet!\n");
        }
        return !status;
    }

    /**
     * The method allows you to enter a String string until a string corresponding to the condition
     * of the isIdDigitChecker() method is entered. If the condition is met, the parseToLong()
     * method is executed. If not, String message is printed.
     *
     * @param message
     * @return Long
     */
    public static Long parseToIdFromString(String message) {
        long id;

        do {
            NotesManager.writeMessage(message);
            String string = NotesManager.readString();

            if (NotesManager.isIdDigitChecker(string)) {
                id = NotesManager.parseToLong(string);
                break;
            }
        } while (true);

        return id;
    }

    /**
     * The method returns true if the specified string consists entirely of digits.
     * Otherwise, false
     *
     * @param string
     * @return boolean
     */
    private static boolean isIdDigitChecker(String string) {
        int charCounter = 0;
        char[] chars;
        boolean isDigit = false;

        if (string != null && !string.equals("")) {
            chars = string.toCharArray();

            for (char aChar : chars) {
                if (!Character.isDigit(aChar)) {
                    break;
                } else {
                    charCounter++;
                }
            }

            if (charCounter == chars.length) {
                isDigit = true;
            }
        }
        return isDigit;
    }

    /**
     * The method returns a Long from the specified string.
     *
     * @param string
     * @return Long
     */
    private static Long parseToLong(String string) {
        long id = 0;
        if (string != null) {
            try {
                id = Long.parseLong(string);
            } catch (NumberFormatException e) {
                // NOPE
            }
        }
        return id;
    }

    /**
     * Returns an initialized Calendar if the specified string fully corresponds to the regex.
     * Otherwise, it returns Calendar equal to null.
     *
     * @param string
     * @return Calendar
     */
    @SuppressWarnings("MagicConstant")
    public static Calendar parseToCalendarFromString(String string) {
        int year;
        int month;
        int day;
        Calendar calendar = null;
        if (string != null) {
            String regex = "([0-9][0-9]\\.)([0-9][0-9]\\.)([0-9][0-9][0-9][0-9])";

            if (Pattern.matches(regex, string)) {
                String[] dateString = string.split("\\.");
                year = Integer.parseInt(dateString[2]);
                month = Integer.parseInt(dateString[1]) - 1;
                day = Integer.parseInt(dateString[0]);

                calendar = new GregorianCalendar(year, month, day);
            }
        }
        return calendar;
    }

    /**
     * The method prints Set<Note> notes according to the template.
     *
     * @param notes
     */
    public static void viewNotes(Set<Note> notes) {
        if (notes != null) {
            for (Note note : notes) {
                NotesManager.writeMessage("\tNote ID: " + note.getId());
                NotesManager.writeMessage("\tTitle: " + note.getTitle());
                NotesManager.writeMessage("\tText: " + note.getText());
                NotesManager.writeMessage("\tHashtag: " + printHashtags(note.getHashTagSet()));
                NotesManager.writeMessage("\tDate: " + simpleDateFormat.format(note.getDate()) + "\n");
            }
        }
    }

    /**
     * If the specified conditions are met, it runs viewNotes (). Otherwise, it prints message.
     *
     * @param notes
     * @param message
     */
    public static void viewNotesOrPrintMessage(Set<Note> notes, String message) {
        if (notes != null && message != null) {
            if (notes.size() > 0) {
                NotesManager.viewNotes(notes);
            } else {
                NotesManager.writeMessage(message);
            }
        }
    }

    /**
     * Output a message to the console.
     *
     * @param message
     */
    public static void writeMessage(String message) {
        System.out.println(message);
    }

    /**
     * Input using the BufferedReader bis static variable.
     *
     * @return
     */
    public static String readString() {
        String string = null;
        try {
            string = bis.readLine();
        } catch (IOException e) {
            // NOPE
        }
        return string;
    }

    static Function askOperation() {
        while (true) {
            NotesManager.writeMessage("Select command for main menu: ");

            for (Function function : Function.values()) {
                NotesManager.writeMessage(String.format("\t %s - %s", function.ordinal() + 1, function.getCommandName()));
            }

            String numberOfOperation = NotesManager.readString().trim();

            if (numberOfOperation.length() == 1) {
                try {
                    int n = Integer.parseInt(numberOfOperation);
                    return Function.getAllowableFunctionByNumber(n);
                } catch (IllegalArgumentException e) {
                    NotesManager.writeMessage("There is no such command!\n");
                }
            }
        }
    }

    public static Filter askFilter() {
        while (true) {
            NotesManager.writeMessage("Select command for filter: ");

            for (Filter filter : Filter.values()) {
                NotesManager.writeMessage(String.format("\t %s - %s", filter.ordinal() + 1, filter.getFilterName()));
            }

            String numberOfOperation = NotesManager.readString().trim();

            if (numberOfOperation.length() == 1) {
                try {
                    int n = Integer.parseInt(numberOfOperation);
                    return Filter.getAllowableFiltersByNumber(n);
                } catch (IllegalArgumentException e) {
                    NotesManager.writeMessage("There is no such command!\n");
                }
            }
        }
    }

    public static Search askSearch() {
        while (true) {
            NotesManager.writeMessage("Select command for search: ");

            for (Search search : Search.values()) {
                NotesManager.writeMessage(String.format("\t %s - %s", search.ordinal() + 1, search.getSearchName()));
            }

            String numberOfOperation = NotesManager.readString().trim();

            if (numberOfOperation.length() == 1) {
                try {
                    int n = Integer.parseInt(numberOfOperation);
                    return Search.getAllowableSearchByNumber(n);
                } catch (IllegalArgumentException e) {
                    NotesManager.writeMessage("There is no such command!\n");
                }
            }
        }
    }

    public static void toBeContinued() {
        NotesManager.writeMessage("Please, press Enter to continue");
        NotesManager.readString();
    }

    private static String printHashtags(Set<String> tags) {
        StringBuilder sb = new StringBuilder();
        if (tags != null) {
            for (String tag : tags) {
                sb.append(tag).append(" ");
            }
        }
        return sb.toString().trim();
    }
}
