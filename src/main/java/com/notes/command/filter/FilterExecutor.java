package com.notes.command.filter;

import java.util.HashMap;
import java.util.Map;

/**
 * The modules of the filter package contain classes that are responsible for the operation of the filter block. Available filtering options: by hashtag, by date, as well as the usual viewing of notes. The work of the class is carried out by the implementation of the command pattern
 */
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
