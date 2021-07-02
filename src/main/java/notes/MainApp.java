package notes;

import notes.command.CommandExecutor;

public class MainApp {

    public static void main(String[] args) {
        Function function;
        do {
            function = NotesManager.askOperation();
            CommandExecutor.execute(function);
        } while (function != Function.EXIT);

        JsonConverter.jsonConverter();
    }
}