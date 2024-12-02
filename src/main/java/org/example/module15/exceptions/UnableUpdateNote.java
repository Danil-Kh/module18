package org.example.module15.exceptions;

public class UnableUpdateNote extends RuntimeException {
    public UnableUpdateNote(String message)
    {
        super(message);
    }
}
