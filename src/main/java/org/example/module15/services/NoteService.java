package org.example.module15.services;

import lombok.RequiredArgsConstructor;
import org.example.module15.entities.Note;
import org.example.module15.repositories.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public List<Note> listAllNotes() {
        return new ArrayList((Collection) noteRepository.findAll());
    }
    public void deleteById(long id){
       noteRepository.deleteById(id);
    }
    public void updateNote(Note note){
      noteRepository.save(note);
    }
    public Optional<Note> getNoteById(long id){
      return noteRepository.findById(id);
    }

    public Note addNote(Note note) {
       return noteRepository.save(note);
       }
   }

