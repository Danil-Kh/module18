package org.example.module15.services;

import lombok.RequiredArgsConstructor;
import org.example.module15.entities.Note;
import org.example.module15.entities.User;
import org.example.module15.exceptions.UnableAddNote;
import org.example.module15.exceptions.UnableDeleteNote;
import org.example.module15.exceptions.UnableUpdateNote;
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
    public void deleteById(long id, UserDetails userDetails) {
        if (!isValidUserNote(id, userDetails.getUsername())) {
            throw new UnableDeleteNote("Notes with such id not found");
        }
       noteRepository.deleteById(id);
    }
    public void updateNote(Note note, UserDetails userDetails) {
        if(note.getId() == null) {
            throw new UnableUpdateNote("Unable to edit note with empty id");
        }
        if (!isValidUserNote(note.getId(), userDetails.getUsername())) {
            throw new UnableUpdateNote("Unable to edit note with such id not found");
        }
        if (note.getTitle().isBlank()) {
            throw new UnableUpdateNote("Unable to edit note with empty title");
        }
        note.setUser(userService.findByUserName(userDetails.getUsername()));
        noteRepository.save(note);

    }
    public boolean isValidUserNote(Long noteId, String username) {
        List<Long> noteIds = noteRepository.getUserNoteIds(userService.findByUserName(username).getId());
        return noteIds.contains(noteId);
    }
    public void addNote(Note note, String username) {
        if (note.getTitle().isBlank()) {
            throw new UnableAddNote("Note title is blank");
        }
        User user = userService.findByUserName(username);
        noteRepository.save(Note.builder()
                .user(user)
                .title(note.getTitle())
                .content(note.getContent())
                .build()
        );
       }
   }

