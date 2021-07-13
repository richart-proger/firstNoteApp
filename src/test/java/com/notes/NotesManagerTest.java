package com.notes;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static com.notes.NotesManager.*;

/**
 * The class testing NotesManager.
 * Note that all tests work with an empty DIR directory. Otherwise they fall.
 */
public class NotesManagerTest {
    //    private static DateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
//    HashMap<Long, Note> noteHashMap = new HashMap<>();
    private Set<String> set1;
    private Set<String> set2;
//    Note note = new Note("text", new Date(), set1, "title");

    @Before
    public void setUp() throws Exception {
        set1 = new HashSet<>();
        set1.add("cars");
        set1.add("jets");
        set1.add("trucks");

        set2 = new HashSet<>();
        set2.add("");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void viewNotesFilteredByDateTest() {
        Date date1 = new Date(1625930817259L); // 10.07.2021
        Date date2 = new Date(1625932363324L); // 10.07.2021
        Date date3 = new Date(1212121212121L); // 30.05.2008
        Date date4 = new Date(1412127215121L); // 01.10.2014
        Note note1 = new Note("text1", date1, set1, "title1");
        Note note2 = new Note("text2", date2, set1, "title2");
        Note note3 = new Note("text3", date3, set1, "title3");
        Note note4 = new Note("text4", date4, set1, "title4");

        Set<Note> mySet = new HashSet<>();

        addNote(note1);
        addNote(note2);
        addNote(note3);
        addNote(note4);

        mySet.add(note1);
        mySet.add(note2);

        // month = num-1, cuz it's calendar
        Calendar calendar = new GregorianCalendar(2021, 6, 10);

        Set<Note> testSet = viewNotesFilteredByDate(calendar);

        int i = 0;
        Note[] myArray = new Note[mySet.size()];
        for (Note note : mySet) {
            myArray[i] = note;
            i++;
        }

        int j = 0;
        Note[] testArray = new Note[testSet.size()];
        for (Note note : testSet) {
            testArray[j] = note;
            j++;
        }

        Assert.assertArrayEquals(testArray, myArray);
    }

    @Test
    public void viewNotesFilteredByHashTagTest() {
        Date date1 = new Date(1625930817259L); // 10.07.2021
        Date date2 = new Date(1625932363324L); // 10.07.2021
        Date date3 = new Date(1212121212121L); // 30.05.2008
        Date date4 = new Date(1412127215121L); // 01.10.2014
        Note note1 = new Note("text1", date1, set1, "title1");
        Note note2 = new Note("text2", date2, set1, "");
        Note note3 = new Note("text3", date3, set2, "title3"); // tag = ""
        Note note4 = new Note("text4", date4, set2, "title4"); // tag = ""

        Set<Note> mySet = new HashSet<>();

        addNote(note1);
        addNote(note2);
        addNote(note3);
        addNote(note4);

        mySet.add(note3);
        mySet.add(note4);

        Set<Note> testSet = viewNotesFilteredByHashTag("");

        int i = 0;
        Note[] myArray = new Note[mySet.size()];
        for (Note note : mySet) {
            myArray[i] = note;
            i++;
        }

        int j = 0;
        Note[] testArray = new Note[testSet.size()];
        for (Note note : testSet) {
            testArray[j] = note;
            j++;
        }

        Assert.assertArrayEquals(testArray, myArray);
    }

    @Test
    public void searchNoteByTextTest() {
        Date date1 = new Date(1625930817259L); // 10.07.2021
        Date date2 = new Date(1625932363324L); // 10.07.2021
        Date date3 = new Date(1212121212121L); // 30.05.2008
        Date date4 = new Date(1412127215121L); // 01.10.2014
        Note note1 = new Note("cApRiCoRn", date1, set1, "title1"); // text = "capricorn"
        Note note2 = new Note("text2", date2, set1, "");
        Note note3 = new Note("Capricorn", date3, set2, "title3"); // text = "capricorn"
        Note note4 = new Note("texcapricOrnt4", date4, set2, "title4"); // text = "capricorn"

        Set<Note> mySet = new HashSet<>();

        addNote(note1);
        addNote(note2);
        addNote(note3);
        addNote(note4);

        mySet.add(note1);
        mySet.add(note3);
        mySet.add(note4);

        Set<Note> testSet = searchNoteByText("cAprIcoRn");

        int i = 0;
        Note[] myArray = new Note[mySet.size()];
        for (Note note : mySet) {
            myArray[i] = note;
            i++;
        }

        int j = 0;
        Note[] testArray = new Note[testSet.size()];
        for (Note note : testSet) {
            testArray[j] = note;
            j++;
        }

        Assert.assertArrayEquals(testArray, myArray);
    }

    @Test
    public void searchNoteByTitleTest() {
        Date date1 = new Date(1625930817259L); // 10.07.2021
        Date date2 = new Date(1625932363324L); // 10.07.2021
        Date date3 = new Date(1212121212121L); // 30.05.2008
        Date date4 = new Date(1412127215121L); // 01.10.2014
        Note note1 = new Note("text1", date1, set1, "Super supeR"); // title = "super"
        Note note2 = new Note("text2", date2, set2, "");
        Note note3 = new Note("text2", date3, set1, "uper");
        Note note4 = new Note("text4", date4, set2, "Super"); // title = "super"

        Set<Note> mySet = new HashSet<>();

        addNote(note1);
        addNote(note2);
        addNote(note3);
        addNote(note4);

        mySet.add(note1);
        mySet.add(note4);

        Set<Note> testSet = searchNoteByTitle("sUpEr");

        int i = 0;
        Note[] myArray = new Note[mySet.size()];
        for (Note note : mySet) {
            myArray[i] = note;
            i++;
        }

        int j = 0;
        Note[] testArray = new Note[testSet.size()];
        for (Note note : testSet) {
            testArray[j] = note;
            j++;
        }

        Assert.assertArrayEquals(testArray, myArray);
    }

    @Test
    public void getNoteByIdTest() {
        Date date1 = new Date(1625930817259L); // 10.07.2021
        Date date2 = new Date(1625932363324L); // 10.07.2021
        Date date3 = new Date(1212121212121L); // 30.05.2008
        Date date4 = new Date(1412127215121L); // 01.10.2014
        Note note1 = new Note("text1", date1, set1, "title1");
        Note note2 = new Note("text2", date2, set2, "title2"); // id = 1625932363324
        Note note3 = new Note("text3", date3, set1, "title3");
        Note note4 = new Note("text4", date4, set1, "title4");

        addNote(note1);
        addNote(note2);
        addNote(note3);
        addNote(note4);

        Note testNote = getNoteById(1625932363324L);

        Assert.assertEquals(testNote, note2);
    }

    @Test
    public void getNoteSetFromMapTest() {
        Date date1 = new Date(1625930817259L); // 10.07.2021
        Date date2 = new Date(1625932363324L); // 10.07.2021
        Date date3 = new Date(1212121212121L); // 30.05.2008
        Date date4 = new Date(1412127215121L); // 01.10.2014
        Note note1 = new Note("text1", date1, set1, "title1");
        Note note2 = new Note("text2", date2, set2, "title2");
        Note note3 = new Note("text3", date3, set1, "title3");
        Note note4 = new Note("text4", date4, set2, "title4");

        Comparator<Note> comparator = Comparator.comparingLong(Note::getId);
        List<Note> myList = new ArrayList<>();

        addNote(note1);
        addNote(note2);
        addNote(note3);
        addNote(note4);

        myList.add(note1);
        myList.add(note2);
        myList.add(note3);
        myList.add(note4);

        Set<Note> testSet = getNoteSetFromMap();
        List<Note> testList = new ArrayList<>(testSet);

        myList.sort(comparator);
        testList.sort(comparator);

        int i = 0;
        Note[] myArray = new Note[myList.size()];
        for (Note note : myList) {
            myArray[i] = note;
            i++;
        }

        int j = 0;
        Note[] testArray = new Note[testList.size()];
        for (Note note : testList) {
            testArray[j] = note;
            j++;
        }

        Assert.assertArrayEquals(testArray, myArray);
    }

    @Test
    public void isNoteMapEmptyTest() {
        Date date = new Date(1412127215121L); // 01.10.2014
        Note note = new Note("text1", date, set1, "title1");
        addNote(note);
        Assert.assertFalse(isNoteMapEmpty());
    }

    @Test
    public void parseToCalendarFromStringTest() {
        Calendar myCalendar = new GregorianCalendar(2021, 6, 10);
        Calendar testCalendar = parseToCalendarFromString("10.07.2021");
        Assert.assertEquals(testCalendar, myCalendar);
    }
}