package org.sda.bms.controller;

import java.util.Optional;

public enum UserOption {
    CREATE_AUTHOR(1, "Create Author"),
    UPDATE_AUTHOR(2, "Update Author"),
    DELETE_AUTHOR(3, "Delete Author"),
    VIEW_ALL_AUTHORS(4, "List All Authors"),
    CREATE_BOOK(5, "Create Book"),
    UPDATE_BOOK(6, "Update Book"),
    DELETE_BOOK(7, "Delete Book"),
    VIEW_ALL_BOOK(8, "List All Book"),
    VIEW_BOOK_BY_ID(9, "Show Book Details By Id"),
    CREATE_REVIEW(10, "Create Review"),
    EXIT(99, "Exit"),
    UNKNOWN(9999, "Unknown");

    private final int option;
    private final String displayName;

    UserOption(int option, String displayName) {
        this.option = option;
        this.displayName = displayName;
    }

    public static void printAllOptions() {
        System.out.println("-------------------------------");
        for (UserOption userOption : values()) {
            if (userOption != UNKNOWN) {
                System.out.println(userOption.displayName + " -> " + userOption.option);
            }
        }
    }

    public static Optional<UserOption> findUserOption(int optionInput) {
        for (UserOption userOption : values()) {
            if (userOption.option == optionInput) {
                return Optional.of(userOption);
            }
        }
        return Optional.empty();
    }


}
