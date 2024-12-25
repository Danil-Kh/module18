package org.example.module15.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExaptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FailedRegistrationException.class)
    public String handleFailedRegistrationException(final FailedRegistrationException e) {
        return e.getMessage();
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnableDeleteNote.class)
    public String handleUnableDeleteNote(final UnableDeleteNote e) {
        return e.getMessage();
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnableUpdateNote.class)
    public String handleUnableUpdateNote(final UnableUpdateNote e) {
        return e.getMessage();
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnableAddNote.class)
    public String handleUnableAddNote(final UnableAddNote e) {
        return e.getMessage();
    }
}
