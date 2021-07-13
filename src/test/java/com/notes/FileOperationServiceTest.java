package com.notes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static com.notes.FileOperationService.*;

public class FileOperationServiceTest {
    private Note note1;
    private Note note2;
    Set<String> set;
    long id;
    private String DIR = "notes_package";

    @Before
    public void init() {
        set = new HashSet<>();
        set.add("cars");
        set.add("jets");
    }

    @Test
    public void isFileCreated() {
        note1 = new Note("text", new Date(), set, "title");
        Assert.assertTrue(noteToFileNote(note1));

        deleteFileNote(note1.getId());
    }

    @Test
    public void isFileDeleted() throws InterruptedException {
        note2 = new Note("text", new Date(), set, "title");
        noteToFileNote(note2);
        Thread.sleep(2000);
        id = note2.getId();

        Assert.assertTrue(deleteFileNote(id));
    }

    @Test
    public void isFileModified() {
        Date date = new Date();
        Note notePrev = new Note("text", date, set, "title");
        Note noteModif = new Note("textNEW", date, set, "titleNEW");

        Assert.assertTrue(editFileNote(notePrev, noteModif));

        deleteFileNote(noteModif.getId());
    }
}