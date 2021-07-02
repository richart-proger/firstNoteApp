package notes.command;

import notes.*;

public class ExitCommand implements Command {
    @Override
    public void execute() {
        NotesManager.writeMessage("До встречи!");
    }
}
