package com.notes.command_for_filter;

import java.util.HashMap;
import java.util.Map;

public class FilterExecutor {

    private static final Map<Filter, CommandForFilter> allKnownFiltersMap = new HashMap<>();

    static {
        allKnownFiltersMap.put(Filter.BY_HASHTAG, new FilterByHashTag());
        allKnownFiltersMap.put(Filter.BY_DATE, new FilterByDate());
        allKnownFiltersMap.put(Filter.VIEW_NOTES, new FilterViewNotes());
        allKnownFiltersMap.put(Filter.MAIN_MENU, new FilterBackToMainMenu());
    }

    private FilterExecutor() {
    }

    public static void executeFilter(Filter filter) {
        allKnownFiltersMap.get(filter).executeFilter();
    }

}
