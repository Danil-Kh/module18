package org.example.module15.exceptions;

public class FailedCreateNoteException extends RuntimeException {
    public FailedCreateNoteException(String message) {
        super(message);
    }
}
