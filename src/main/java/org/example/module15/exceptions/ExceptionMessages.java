package org.example.module15.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionMessages {
    USERNAME_ALREADY_EXISTS("Username already exists"),
    PASSWORD_TO_SHORT("Password must be at least 6 characters");

    private final String message;

    ExceptionMessages(String message) {
        this.message = message;
    }
}
