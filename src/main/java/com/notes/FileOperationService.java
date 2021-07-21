package com.notes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notes.exception.CannotCreateDirException;
import com.notes.exception.CannotCreateFileForNoteException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The class contains the main CRUD methods for working with files, as well as methods for maintaining
 * the work of the above methods
 */
public class FileOperationService {
    private static final String DIR = "notes_package";
    private static final File DIR_FOLDER;

    static {
        DIR_FOLDER = checkDirOrCreate();
    }

    /**
     * This private method determines whether there is a directory for saving files. If the directory was
     * created successfully or already exists, the method returns it, but if it fails to create it, the method
     * throws new CannotCreateDirException().
     *
     * @return File dir
     * @throws CannotCreateDirException
     */
    private static File checkDirOrCreate() throws CannotCreateDirException {
        final File dir = new File(DIR);

        if (!dir.exists() && !dir.mkdir()) {
            // if directory could not be created
            throw new CannotCreateDirException();
        }
        // else if directory was created successfully or already exists
        return dir;
    }

    /**
     * The method by which all json files are subtracted from the DIR directory when the application is launched.
     *
     * @return Map <Long, Note>
     */
    static Map<Long, Note> loadNoteFromFileIntoCache() {
        List<File> fileList = FileOperationService.getListOfFileNotes();
        Map<Long, Note> noteMap = new HashMap<>();

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
        return noteMap;
    }


    /**
     * Creates a json file of the note in the directory obtained using the method checkDirOrCreate().
     * In case of successful creation, it returns true, in case of failure it returns false.
     *
     * @param note
     * @return
     * @throws CannotCreateFileForNoteException
     */
    public static boolean noteToFileNote(Note note) throws CannotCreateFileForNoteException {
        if (note == null) {
            return false;
        }

        ObjectMapper mapper = new ObjectMapper();
        File resultFile = new File(DIR_FOLDER, note.getId() + ".txt");
        try {
            if (resultFile.createNewFile()) {
                mapper.writeValue(resultFile, note);
                return true;
            }
        } catch (IOException e) {
            // NOPE
            throw new CannotCreateFileForNoteException(note);
        }
        return false;
    }

    /**
     * Deletes the json file of the note from the directory obtained using the method
     * checkDirOrCreate(). In case of successful creation, it returns true, in case of failure,
     * it returns false.
     *
     * @param id
     * @return
     */
    public static boolean deleteFileNote(Long id) {
        File fileToDelete = getFileByIdFromDir(id);
        if (fileToDelete != null) {
            return fileToDelete.delete();
        }
        return false;
    }

    /**
     * Returns a File according to the specified id from the list obtained using the
     * getListOfFileNotes() method
     *
     * @param id
     * @return
     */
    private static File getFileByIdFromDir(Long id) {
        List<File> fileList = getListOfFileNotes();
        File fileToFind = null;
        try {
            fileToFind = new File(DIR_FOLDER.getCanonicalPath() + "\\" + id + ".txt");
        } catch (IOException e) {
            // NOPE
        }

        if (fileToFind != null) {
            for (File file : fileList) {
                try {
                    if (file.getCanonicalPath().equals(fileToFind.getCanonicalPath())) {
                        return fileToFind;
                    }
                } catch (IOException e) {
                    // NOPE
                }
            }
        }
        return fileToFind;
    }

    /**
     * This method edits the note file, deleting the old file and replacing it with a new one.
     * If successful, it returns true, otherwise false.
     *
     * @param previousNote
     * @param modifiedNote
     * @return
     */
    public static boolean editFileNote(Note previousNote, Note modifiedNote) {
        if (previousNote != null && modifiedNote != null) {
            deleteFileNote(previousNote.getId());
            noteToFileNote(modifiedNote);
            return true;
        }
        return false;
    }

    /**
     * This method returns a list of all files in the directory File DIR_FOLDER
     *
     * @return
     */
    private static List<File> getListOfFileNotes() {
        List<File> fileList = new ArrayList<>();
        File[] fileArray = DIR_FOLDER.listFiles();

        if (fileArray != null) {
            for (File entry : fileArray) {
                if (entry.isFile()) {
                    fileList.add(entry);
                }
            }
        }
        return fileList;
    }
}
