package org.example.module15.exceptions;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@ControllerAdvice
@RequiredArgsConstructor
public class NotesExceptionHandler {

    @ExceptionHandler(FailedRegistrationException.class)
    public String handleFileNotFoundException(FailedRegistrationException e, RedirectAttributes redirectAttributes) {
        if (e.getMessage().equals(ExceptionMessages.PASSWORD_TO_SHORT.getMessage())){
            redirectAttributes.addFlashAttribute("invalidPassword", ExceptionMessages.PASSWORD_TO_SHORT.getMessage());
        }
        if (e.getMessage().equals(ExceptionMessages.USERNAME_ALREADY_EXISTS.getMessage())){
            redirectAttributes.addFlashAttribute("invalidFeedback", ExceptionMessages.USERNAME_ALREADY_EXISTS.getMessage());
        }
        return "redirect:/register";
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
