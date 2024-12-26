package org.example.module15.controllers;

import lombok.AllArgsConstructor;
import org.example.module15.entities.Note;
import org.example.module15.services.NoteService;
import org.example.module15.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class NoteController {
   private final NoteService noteService;
   private final UserService userService;

    @PostMapping("/createNote")
    public ResponseEntity<Note> createNote(@RequestBody Note note,
                                             @AuthenticationPrincipal UserDetails userDetails) {

        noteService.addNote(note, userDetails.getUsername());

        return ResponseEntity.status(HttpStatus.CREATED).body(note);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Note>> getAllNotes(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(noteService.listAllNotes(userDetails.getUsername()));

    }
    @PostMapping("/edit")
    public ResponseEntity<Note> editNote(@AuthenticationPrincipal UserDetails userDetails,
                                           @RequestBody Note note) {
        noteService.updateNote(note, userDetails);
        return ResponseEntity.ok(note);
    }
    @PostMapping("/delete")
    public ResponseEntity<String> deleteNote(
            @RequestParam(name = "Number") Long noteId, @AuthenticationPrincipal UserDetails userDetails) {

       noteService.deleteById(noteId, userDetails);
       return ResponseEntity.ok("Note successfully deleted");
    }

}
