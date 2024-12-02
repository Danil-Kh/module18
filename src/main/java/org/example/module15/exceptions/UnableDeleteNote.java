package org.example.module15.exceptions;

public class UnableDeleteNote extends RuntimeException {
    public UnableDeleteNote(String message) {
        super(message);
    }
}
