package org.example.module15.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionMessages {
    USERNAME_ALREADY_EXISTS("Username already exists"),
    PASSWORD_TO_SHORT("Password must be at least 6 characters"),
    UNABLE_ADD_NOTE_EMPTY_TITLE("Unable to save note with empty title"),
    USER_NOT_FOUND("User not found"),
    INVALID_USERNAME_OR_PASSWORD("Invalid username or password"),
    UNABLE_SAVE_USER_EMPTY_USERNAME("Unable to create user with empty username"),
    USERNAME_TOO_LONG("Username too long"),
    PASSWORD_TOO_LONG("Username too long");


    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }
}
