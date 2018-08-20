package com.web.userlogin.web;

import com.web.userlogin.model.Note;
import com.web.userlogin.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
public class NotesController {

    @Autowired
    NotesService notesService;

    @PostMapping("/addNote")
    public String addNote(@ModelAttribute("note") Note noteModel, BindingResult bindingResult, ModelMap model) {
        noteModel.setNoteCreatedTime(new Date(System.currentTimeMillis()));
        Note createdNote=notesService.addNote(noteModel);
        List<Note> noteList = notesService.retrieveNote(createdNote.getUserName());
        model.addAttribute("notes",noteList);
        return "notes";
    }

    @GetMapping("/deleteNote")
    public String deleteNote(Long id, ModelMap model) {
        Note note= notesService.findOne(id);
        notesService.deleteNote(id);
        List<Note> noteList = notesService.retrieveNote(note.getUserName());
        model.put("notes", noteList);
        return "notes";
    }

    @GetMapping("/getNotes")
    public String getNotes(String userName, ModelMap model) {
        List<Note> noteList = notesService.retrieveNote(userName);
        model.put("notes", noteList);
        return "notes";

    }

    @PostMapping("/updateNote")
    public String updateNote(@ModelAttribute("note") Note note, ModelMap model){
        note.setNoteUpdatedTime(new Date(System.currentTimeMillis()));
        notesService.updateNote(note);
        List<Note> noteList = notesService.retrieveNote(note.getUserName());
        model.put("notes", noteList);
        return "notes";
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
