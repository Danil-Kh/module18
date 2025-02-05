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
    private static final  String PASSWORD_ERROR_REGISTER = "invalidPassword";
    private static final String USERNAME_ERROR_REGISTER = "invalidPassword";

    @ExceptionHandler(FailedRegistrationException.class)
    public String handleFileNotFoundException(FailedRegistrationException e, RedirectAttributes redirectAttributes) {
        if (e.getMessage().equals(ExceptionMessages.PASSWORD_TO_SHORT.getMessage())){
            redirectAttributes.addFlashAttribute(PASSWORD_ERROR_REGISTER, ExceptionMessages.PASSWORD_TO_SHORT.getMessage());
        }
        if (e.getMessage().equals(ExceptionMessages.USERNAME_ALREADY_EXISTS.getMessage())){
            redirectAttributes.addFlashAttribute(USERNAME_ERROR_REGISTER, ExceptionMessages.USERNAME_ALREADY_EXISTS.getMessage());
        }
        if (e.getMessage().equals(ExceptionMessages.UNABLE_SAVE_USER_EMPTY_USERNAME.getMessage())){
            redirectAttributes.addFlashAttribute(USERNAME_ERROR_REGISTER, ExceptionMessages.UNABLE_SAVE_USER_EMPTY_USERNAME.getMessage());
        }
        if (e.getMessage().equals(ExceptionMessages.PASSWORD_TOO_LONG.getMessage())){
            redirectAttributes.addFlashAttribute(PASSWORD_ERROR_REGISTER, ExceptionMessages.PASSWORD_TOO_LONG.getMessage());
        }
        if (e.getMessage().equals(ExceptionMessages.USERNAME_TOO_LONG.getMessage())){
            redirectAttributes.addFlashAttribute(PASSWORD_ERROR_REGISTER, ExceptionMessages.USERNAME_TOO_LONG.getMessage());
        }

        return "redirect:/register";
    }
    @ExceptionHandler(FailedCreateNoteException.class)
    public String handleFailedCreateNoteException(FailedCreateNoteException e, RedirectAttributes redirectAttributes) {
        if (e.getMessage().equals(ExceptionMessages.UNABLE_ADD_NOTE_EMPTY_TITLE.getMessage())) {
            redirectAttributes.addFlashAttribute("invalidNote", ExceptionMessages.UNABLE_ADD_NOTE_EMPTY_TITLE.getMessage());
        }
        return "redirect:/editPages";
    }
    @ExceptionHandler(FailedLoginException.class)
    public String handleFailedLoginException(FailedLoginException e, RedirectAttributes redirectAttributes) {
        if (e.getMessage().equals(ExceptionMessages.PASSWORD_TO_SHORT.getMessage())) {
            redirectAttributes.addFlashAttribute("invalidPasswordLogin", ExceptionMessages.PASSWORD_TO_SHORT.getMessage());
        }
        if (e.getMessage().equals(ExceptionMessages.USERNAME_ALREADY_EXISTS.getMessage())) {
            redirectAttributes.addFlashAttribute("invalidFeedbackK", ExceptionMessages.USERNAME_ALREADY_EXISTS.getMessage());
        }
        return "redirect:/login";
    }

    @ExceptionHandler(UnableDeleteNote.class)
    public String handleUnableDeleteNote(final UnableDeleteNote e) {
        return e.getMessage();
    }
    @ExceptionHandler(UnableUpdateNote.class)
    public String handleUnableUpdateNote(final UnableUpdateNote e) {
        return e.getMessage();
    }


}
