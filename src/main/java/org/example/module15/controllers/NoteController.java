package org.example.module15.controllers;

import lombok.AllArgsConstructor;
import org.example.module15.entities.Note;
import org.example.module15.entities.User;
import org.example.module15.services.NoteService;
import org.example.module15.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;


@Controller
@AllArgsConstructor
public class NoteController {
   private final NoteService noteService;
   private final UserService userService;

    @PostMapping("/createNote")
    public ResponseEntity<Object> createNote(
            @RequestBody Note note,
            @AuthenticationPrincipal UserDetails userDetails) {

        if (note.getTitle().isBlank()) {
            return ResponseEntity.badRequest().body("Unable to save note with empty title");
        }
        noteService.addNote(note, userDetails.getUsername());

        return ResponseEntity.ok(note);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> getAllNotes(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(noteService.listAllNotes(userDetails.getUsername()));

    }
    @GetMapping("/editPages")
    public ResponseEntity<Object>  rediredEditPages(@AuthenticationPrincipal UserDetails userDetails) {
           return ResponseEntity.ok(noteService.listAllNotes(userDetails.getUsername()));
    }
    @PostMapping("/edit")
    public ResponseEntity<Object> editNote(@AuthenticationPrincipal UserDetails userDetails, @RequestBody Note note) {
        note.setUser(userService.findByUserName(userDetails.getUsername()));
            if(note.getId() == null) {
                return ResponseEntity.badRequest().body("Unable to edit note with empty id");
            }
            if (!noteService.isValidUserNote(note, userDetails.getUsername())) {
                return ResponseEntity.badRequest().body("Unable to edit note with such id not found");
            }
            if (note.getTitle().isBlank()) {
                return ResponseEntity.badRequest().body("Unable to save note with empty title");
            }
        noteService.updateNote(note);
        return ResponseEntity.ok(note);
    }
    @PostMapping("/delete")
    public ResponseEntity<Object> deleteNote(
            @RequestParam(name = "Number") Long noteId) {

       noteService.deleteById(noteId);
       return ResponseEntity.ok().build();
    }

}
