package org.example.module15.controllers;

import lombok.AllArgsConstructor;
import org.example.module15.entities.Note;
import org.example.module15.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@AllArgsConstructor
public class NoteController {
    NoteService noteService;
    @PostMapping("/createNote")
    public ModelAndView createNote(
            @RequestParam String title,
            @RequestParam String content) {
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        noteService.addNote(note);

        return new ModelAndView("index").addObject("message", note);
    }
    @GetMapping("/list")
    public ModelAndView getAllNotes(Model model) {
        model.addAttribute("action", "list");
        return new ModelAndView("index").addObject("listAllNotes", noteService.listAllNotes());
    }
    @GetMapping("/editPages")
    public ModelAndView rediredEditPages(Model model) {
      return new ModelAndView("edit").addObject("listAllNotes", noteService.listAllNotes());
    }
    @PostMapping("/edit")
    public ModelAndView editNote(@RequestParam("noteId") Long id, @RequestParam String title, @RequestParam String content) {
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        note.setId(id);
        noteService.updateNote(note);
        return new ModelAndView("redirect:/list");
    }
    @PostMapping("/delete")
    public ModelAndView deleteNote(@RequestParam("noteId") Long id) {
       noteService.deleteById(id);
       return new ModelAndView("redirect:/list");
    }



}
