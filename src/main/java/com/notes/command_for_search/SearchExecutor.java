package com.notes.command_for_search;

import java.util.HashMap;
import java.util.Map;

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