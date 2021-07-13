package com.notes.command_for_main_menu;

public enum Function {
    ADD("Add note"),
    DELETE("Delete note"),
    EDIT("Edit note"),
    FILTER("Filter by ..."),
    SEARCH("Search by ..."),
    EXIT("Exit");

    private final String commandName;

    Function(String commandName) {
        this.commandName = commandName;
    }

    public static Function getAllowableFunctionByNumber(Integer i) {

        for (Function function : Function.values()) {
            if (function.ordinal() == i - 1) {
                return function;
            }
        }
        throw new IllegalArgumentException();
    }

    public String getCommandName() {
        return commandName;
    }
}