package org.example.module15.services;

import lombok.RequiredArgsConstructor;
import org.example.module15.entities.Note;
import org.example.module15.entities.User;
import org.example.module15.repositories.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final UserService userService;

    public List<Note> listAllNotes(String username) {
        return noteRepository.getUserNotes(username);
    }
    public void deleteById(long id){
       noteRepository.deleteById(id);
    }
    public void updateNote(Note note) {
      noteRepository.save(note);
    }
//    public Optional<Note> getNoteById(long id){
//      return noteRepository.findById(id);
//    }

    public Note addNote(Note note, String username) {
        User user = userService.findByUserName(username);
        Note createdNote = noteRepository.save(Note.builder()
                .user(user)
                .title(note.getTitle())
                .content(note.getContent())
                .build()
        );
        return noteRepository.save(createdNote);
       }
   }

