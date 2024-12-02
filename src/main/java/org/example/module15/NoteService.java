package org.example.module15;

import org.example.module15.exceptions.UnableDeleteNote;
import org.example.module15.exceptions.UnableFindNote;
import org.example.module15.exceptions.UnableUpdateNote;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NoteService {
    private static Map<Long, Note> notes = new HashMap();
    private static Random idGenerator = new Random();

    public List<Note> listAllNotes() {

        return new ArrayList(notes.values());
    }
    public void deleteById(long id){
        int allNotes = notes.size();
        notes.remove(id);
        if (allNotes == notes.size()){
            throw new UnableDeleteNote("Unable to delete note");
        }
    }
    public void updateNote(Note note){
       if (notes.containsKey(note.getId())){
           notes.put(note.getId(), note);
       }
       else{
           throw new UnableUpdateNote("Unable to update note");
       }
    }
    public Note getNoteById(long id){
        if (notes.containsKey(id)){
           return notes.get(id);
        }
        else{
            throw new UnableFindNote("Unable to find note");
        }
    }

    public Note addNote(Note note) {
        Long id = Math.abs(idGenerator.nextLong());
       while (true) {
           if (notes.containsKey(id)) {
               id = Math.abs(idGenerator.nextLong());
           }
           else {
               note.setId(id);
               notes.put(id, note);
               return note;
           }
       }

   }
}
