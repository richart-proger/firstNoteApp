package notes;

public enum Function {
    ADD_NOTE,
    DELETE_NOTE,
    EDIT_NOTE,
    VIEW_NOTE,
    FILTER_BY_DATE,
    FILTER_BY_HASHTAG,
    SEARCH_BY_TEXT,
    SEARCH_BY_TITLE,
    EXIT;

    public static Function getAllowableFunctionByNumber(Integer i) {
        switch (i) {
            case 1:
                return ADD_NOTE;
            case 2:
                return DELETE_NOTE;
            case 3:
                return EDIT_NOTE;
            case 4:
                return VIEW_NOTE;
            case 5:
                return FILTER_BY_DATE;
            case 6:
                return FILTER_BY_HASHTAG;
            case 7:
                return SEARCH_BY_TEXT;
            case 8:
                return SEARCH_BY_TITLE;
            case 9:
                return EXIT;
            default:
                throw new IllegalArgumentException();
        }
    }

}
