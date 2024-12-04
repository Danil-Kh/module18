package org.example.module15;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class NoteService {
    private final Random idGenerator = new Random();
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

