package org.example.module15.exceptions;

public class UnableAddNote extends RuntimeException {
    public UnableAddNote(String message) {
        super(message);
    }
}
