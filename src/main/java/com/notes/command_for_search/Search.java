package com.notes.command_for_search;

public enum Search {

    BY_TITLE("Search by title"),
    BY_TEXT("Search by text"),
    BY_TITLE_AND_TEXT("Search by title and text"),
    MAIN_MENU("Back to the main menu");

    private final String searchName;

    Search(String searchName) {
        this.searchName = searchName;
    }

    public static Search getAllowableSearchByNumber(Integer i) {

        for (Search search : Search.values()) {
            if (search.ordinal() == i - 1) {
                return search;
            }
        }
        throw new IllegalArgumentException();
    }

    public String getSearchName() {
        return searchName;
    }

}