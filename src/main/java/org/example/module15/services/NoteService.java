package org.example.module15.services;

import lombok.RequiredArgsConstructor;
import org.example.module15.entities.Note;
import org.example.module15.entities.User;
import org.example.module15.exceptions.ExceptionMessages;
import org.example.module15.exceptions.FailedCreateNoteException;
import org.example.module15.repositories.NoteRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final UserService userService;

    public List<Note> listAllNotes(String username) {
        return noteRepository.getUserNotes(userService.findByUserName(username).getId());
    }
    public void deleteById(long id){
        noteRepository.deleteById(id);
    }

    public void addNote(String username, String title, String content) {
        User user = userService.findByUserName(username);
        if(title.isEmpty()){
            throw new FailedCreateNoteException(ExceptionMessages.UNABLE_ADD_NOTE_EMPTY_TITLE.getMessage());
        }
        noteRepository.save(Note.builder()
                .user(user)
                .title(title)
                .content(content)
                .build()
        );
    }

    public void updateNote(String title, String content, Long id, UserDetails userDetails) {
        User user = userService.findByUserName(userDetails.getUsername());
        if(title.isEmpty()){
            throw new FailedCreateNoteException(ExceptionMessages.UNABLE_ADD_NOTE_EMPTY_TITLE.getMessage());
        }
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        note.setId(id);
        note.setUser(user);
        noteRepository.save(note);
    }
}

