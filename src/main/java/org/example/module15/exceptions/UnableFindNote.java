package org.example.module15.exceptions;

public class UnableFindNote extends RuntimeException {
    public UnableFindNote(String message) {
        super(message);
    }
}
