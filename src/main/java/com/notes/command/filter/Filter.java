package com.notes.command.filter;

public enum Filter {
    BY_HASHTAG("Filter by hashtag"),
    BY_DATE("Filter by date"),
    VIEW_NOTES("View all notes"),
    MAIN_MENU("Back to the main menu");

    private final String filterName;

    Filter(String filterName) {
        this.filterName = filterName;
    }

    public static Filter getAllowableFiltersByNumber(Integer i) {

        for (Filter filter : Filter.values()) {
            if (filter.ordinal() == i - 1) {
                return filter;
            }
        }
        throw new IllegalArgumentException();
    }

    public String getFilterName() {
        return filterName;
    }
}
