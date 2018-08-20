package com.web.userlogin.service;

import com.web.userlogin.model.Note;
import com.web.userlogin.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotesService {

    @Autowired
    NotesRepository notesRepository;

    @Autowired
    UserService userService;

   public Note addNote(Note note) {
           Note createdNote = notesRepository.save(note);
           List<Note> notesList= notesRepository.findAll();
           return createdNote;
    }

    public List<Note> retrieveNoteByID(Long id){
        List<Note> notesList=new ArrayList<>();
        List<Long> notesId=new ArrayList<Long>();
        notesId.add(id);
        notesList=notesRepository.findAllById(notesId);
        return notesList;
    }
    public List<Note> retrieveNote(String userName){
        List<Note> notesList=new ArrayList<>();
        notesList=notesRepository.findByUsername(userName);
        return notesList;
    }

    public void deleteNote(Long id){
        notesRepository.deleteById(id);
    }

    public Note updateNote(Note note){
        Long id=note.getId();
        Optional<Note> existingNote=notesRepository.findById(id);
        Note updatedNote= existingNote.get();
        updatedNote.setTitle(note.getTitle());
        updatedNote.setDescription(note.getDescription());
        updatedNote.setNoteUpdatedTime(new Date(System.currentTimeMillis()));
        Note newUpdatedNote= notesRepository.save(updatedNote);
        return newUpdatedNote;
    }
   public Note findOne(Long id){
        Optional<Note> note=notesRepository.findById(id);
        return note.get();
   }
}
