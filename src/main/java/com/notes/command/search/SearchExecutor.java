package com.notes.command.search;

import java.util.HashMap;
import java.util.Map;

/**
 * The modules of the search package contain classes that are responsible for the operation of the search block. Available search options: by text, by name, and at the same time by text and name. The work of the class is carried out by the implementation of the command pattern
 */
public class SearchExecutor {

    private static final Map<Search, CommandForSearch> allKnownSearchMap = new HashMap<>();

    static {
        allKnownSearchMap.put(Search.BY_TITLE, new SearchByTitle());
        allKnownSearchMap.put(Search.BY_TEXT, new SearchByText());
        allKnownSearchMap.put(Search.BY_TITLE_AND_TEXT, new SearchByTitleAndText());
        allKnownSearchMap.put(Search.MAIN_MENU, new SearchBackToMainMenu());
    }

    private SearchExecutor() {
    }

    public static void executeSearch(Search search) {
        allKnownSearchMap.get(search).executeSearch();
    }
}