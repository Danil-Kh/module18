package org.example.module15.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnableAddNote extends RuntimeException {
    public UnableAddNote(String message) {
        super(message);
    }
}
