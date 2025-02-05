package org.example.module15.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.example.module15.services.NoteService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@AllArgsConstructor
public class NoteController {
   private final NoteService noteService;

    @PostMapping("/createNote")
    public ModelAndView createNote(
            @RequestParam String title,
            @RequestParam String content,
            @AuthenticationPrincipal UserDetails userDetails) {

        noteService.addNote(userDetails.getUsername(), title, content);

        return new ModelAndView("index").addObject("message", title);
    }
    @GetMapping("/list")
    public ModelAndView getAllNotes(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("action", "list");
        return new ModelAndView("index")
                .addObject("listAllNotes", noteService.listAllNotes(userDetails.getUsername()));

    }

    @GetMapping("/editPages")
    public ModelAndView redirectedEditPages(@AuthenticationPrincipal UserDetails userDetails) {
      return new ModelAndView("edit")
              .addObject("listAllNotes", noteService.listAllNotes(userDetails.getUsername()));
    }
    @PostMapping("/edit")
    public String editNote(@RequestParam("noteId") Long id,
                                 @RequestParam String title,
                                 @RequestParam String content,
                                 @AuthenticationPrincipal UserDetails userDetails) {
        noteService.updateNote(title, content, id, userDetails);
        return "redirect:/list";
    }
    @PostMapping("/delete")
    public ModelAndView deleteNote(@RequestParam("noteId") Long id) {
       noteService.deleteById(id);
       return new ModelAndView("redirect:/list");
    }

}
