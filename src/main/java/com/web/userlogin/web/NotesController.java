package com.web.userlogin.web;

import com.web.userlogin.model.Note;
import com.web.userlogin.service.NotesService;
import com.web.userlogin.validator.NotesValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.lang.invoke.MethodHandles;
import java.util.Date;
import java.util.List;

@Controller
public class NotesController {

    @Autowired
    NotesService notesService;

    @Autowired
    HttpSession httpSession;

    @Autowired
    NotesValidator notesValidator;

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


    @PostMapping("/addNote")
    public ModelAndView addNote(Note noteModel, BindingResult bindingResult, ModelMap model, ModelAndView modelAndView) {
        modelAndView.addObject("note", noteModel);
        notesValidator.validate(noteModel, bindingResult);
        noteModel.setUserName(httpSession.getAttribute("userName").toString());
        noteModel.setNoteCreatedTime(new Date(System.currentTimeMillis()));
        Note createdNote=notesService.addNote(noteModel);
        log.debug("Note Created Details:",createdNote.toString());
        List<Note> noteList = notesService.retrieveNote(createdNote.getUserName());
        model.addAttribute("notes",noteList);
        modelAndView.setViewName("notes");
        return modelAndView;
    }

    @GetMapping("/deleteNote")
    public String deleteNote(Long id, ModelMap model) {
        Note note= notesService.findOne(id);
        notesService.deleteNote(id);
        List<Note> noteList = notesService.retrieveNote(note.getUserName());
        model.put("notes", noteList);
        httpSession.setAttribute("userNotes",noteList);
        return "notes";
    }

    @GetMapping("/getNotes")
    public ModelAndView getNotes(Note note, ModelMap model, ModelAndView modelAndView) {
        modelAndView.addObject("note", note);
        modelAndView.setViewName("notes");
        String userName=httpSession.getAttribute("userName").toString();
        List<Note> noteList = notesService.retrieveNote(userName);
        model.put("notes", noteList);
        httpSession.setAttribute("userNotes",noteList);
        return modelAndView;

    }

    @PostMapping("/updateNote")
    public ModelAndView updateNote( Note note,  BindingResult bindingResult, ModelMap model, ModelAndView modelAndView){
        notesValidator.validate(note, bindingResult);
        modelAndView.addObject("note", note);
        modelAndView.setViewName("notes");
        note.setNoteUpdatedTime(new Date(System.currentTimeMillis()));
        Note updatedNote=notesService.updateNote(note);
        log.debug("Note Updated Details:",updatedNote.toString());
        String userName=httpSession.getAttribute("userName").toString();
        List<Note> noteList = notesService.retrieveNote(userName);
        model.put("notes", noteList);
        return modelAndView;
    }

    @GetMapping("/findOne")
    @ResponseBody
    public Note findNote( Long id){
         Note note= notesService.findOne(id);
         return note;
    }

    @GetMapping("/notes")
    public String displayNotes() {
        return "notes";
    }

    @GetMapping("/addNote")
    public String addNote() {
        return "addNote";

    }
}
