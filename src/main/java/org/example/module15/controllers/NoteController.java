package org.example.module15.controllers;

import lombok.AllArgsConstructor;
import org.example.module15.entities.Note;
import org.example.module15.entities.User;
import org.example.module15.services.NoteService;
import org.example.module15.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



@Controller
@AllArgsConstructor
public class NoteController {
   private final NoteService noteService;
   private final UserService userService;
    @PostMapping("/createNote")
    public ModelAndView createNote(
            @RequestParam String title,
            @RequestParam String content,
            @AuthenticationPrincipal UserDetails userDetails) {
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        noteService.addNote(note, userDetails.getUsername());

        return new ModelAndView("index").addObject("message", note);
    }
    @GetMapping("/list")
    public ModelAndView getAllNotes(Model model, @AuthenticationPrincipal UserDetails userDetails) {

        model.addAttribute("action", "list");
        return new ModelAndView("index")
                .addObject("listAllNotes", noteService.listAllNotes(userDetails.getUsername()));

    }
    @GetMapping("/editPages")
    public ModelAndView rediredEditPages(Model model, @AuthenticationPrincipal UserDetails userDetails) {
      return new ModelAndView("edit")
              .addObject("listAllNotes", noteService.listAllNotes(userDetails.getUsername()));
    }
    @PostMapping("/edit")
    public ModelAndView editNote(@RequestParam("noteId") Long id,
                                 @RequestParam String title,
                                 @RequestParam String content,
                                 @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUserName(userDetails.getUsername());
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        note.setId(id);
        note.setUser(user);
        noteService.updateNote(note);
        return new ModelAndView("redirect:/list");
    }
    @PostMapping("/delete")
    public ModelAndView deleteNote(@RequestParam("noteId") Long id) {
       noteService.deleteById(id);
       return new ModelAndView("redirect:/list");
    }

}
