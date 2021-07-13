package com.notes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notes.exception.CannotCreateDirException;
import com.notes.exception.CannotCreateFileForNoteException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class contains the main CRUD methods for working with files, as well as methods for maintaining
 * the work of the above methods
 */
public class FileOperationService {
    private static final String DIR = "notes_package";

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

        if (!dir.exists()) {
            if (dir.mkdir()) {
                // if directory was created successfully
                return dir;
            } else {
                // if directory could not be created
                throw new CannotCreateDirException();
            }
        } else {
            // if directory exists
            return dir;
        }
    }

    /**
     * Creates a json file of the note in the directory obtained using the method checkDirOrCreate().
     * In case of successful creation, it returns true, in case of failure, it throws a new
     * CannotCreateFileForNoteException().
     *
     * @param note
     * @return
     * @throws CannotCreateFileForNoteException
     */
    public static boolean noteToFileNote(Note note) throws CannotCreateFileForNoteException {
        File dir = checkDirOrCreate();
        boolean statusOfOperation = false;

        if (dir != null && note != null) {
            ObjectMapper mapper = new ObjectMapper();
            File resultFile = new File(dir, note.getId() + ".txt");
            try {
                if (resultFile.createNewFile()) {
                    mapper.writeValue(resultFile, note);
                    statusOfOperation = true;
                } else {
                    throw new CannotCreateFileForNoteException(note);
                }
            } catch (IOException e) {
                // NOPE
            }
        }
        return statusOfOperation;
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
        File dir = checkDirOrCreate();
        boolean statusOfOperation = false;

        File fileToDelete = getFileByIdFromDir(dir, id);
        if (fileToDelete != null) {
            statusOfOperation = fileToDelete.delete();
        }
        return statusOfOperation;
    }

    /**
     * Returns a File according to the specified id from the list obtained using the
     * getListOfFileNotes() method according to the specified directory File dir
     *
     * @param dir
     * @param id
     * @return
     */
    private static File getFileByIdFromDir(File dir, Long id) {
        List<File> fileList = getListOfFileNotes(dir);
        File fileToFind = null;
        try {
            fileToFind = new File(dir.getCanonicalPath() + "\\" + id + ".txt");
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
        boolean statusOfOperation = false;

        if (previousNote != null && modifiedNote != null) {
            deleteFileNote(previousNote.getId());
            noteToFileNote(modifiedNote);
            statusOfOperation = true;
        }
        return statusOfOperation;
    }

    /**
     * This method returns a list of all files in the directory File dir
     *
     * @param dir
     * @return
     */
    static List<File> getListOfFileNotes(File dir) {
        List<File> fileList = null;
        if (dir != null) {
            fileList = new ArrayList<>();
            File[] fileArray = dir.listFiles();

            if (fileArray != null) {
                for (File entry : fileArray) {
                    if (entry.isFile()) {
                        fileList.add(entry);
                    }
                }
            }
        }
        return fileList;
    }
}
