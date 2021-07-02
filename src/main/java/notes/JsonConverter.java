package notes;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Set;

public class JsonConverter {
    private static final String DIR = "NotesPackage";

    public static void jsonConverter() {
        final File dir = new File(DIR);
        if (!dir.exists()) {
            dir.mkdir();
        }

        Set<Note> noteSet = NotesManager.getNoteSet();

        try (FileWriter fileWriter = new FileWriter(dir.getAbsolutePath() + "\\notes.txt")) {
            fileWriter.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Note note : noteSet) {
            StringWriter writer = new StringWriter();
            ObjectMapper mapper = new ObjectMapper();
            try (FileWriter fileWriter = new FileWriter(dir.getAbsolutePath() + "\\notes.txt", true)) {
                mapper.writeValue(writer, note);
                String result = writer.toString();
                fileWriter.write(result + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
