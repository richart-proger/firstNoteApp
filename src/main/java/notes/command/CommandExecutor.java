package notes.command;

import notes.Function;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private static final Map<Function, Command> allKnownFunctionsMap = new HashMap<>();

    static {
        allKnownFunctionsMap.put(Function.ADD_NOTE, new AddNoteCommand());
        allKnownFunctionsMap.put(Function.DELETE_NOTE, new DeleteNoteCommand());
        allKnownFunctionsMap.put(Function.EDIT_NOTE, new EditNoteCommand());
        allKnownFunctionsMap.put(Function.VIEW_NOTE, new ViewNoteCommand());
        allKnownFunctionsMap.put(Function.FILTER_BY_DATE, new FilterByDateCommand());
        allKnownFunctionsMap.put(Function.FILTER_BY_HASHTAG, new FilterByHashtagCommand());
        allKnownFunctionsMap.put(Function.SEARCH_BY_TEXT, new SearchByTextCommand());
        allKnownFunctionsMap.put(Function.SEARCH_BY_TITLE, new SearchByTitleCommand());
        allKnownFunctionsMap.put(Function.EXIT, new ExitCommand());

    }

    private CommandExecutor() {
    }

    public static final void execute(Function function) {
        allKnownFunctionsMap.get(function).execute();
    }
}
