package com.web.userlogin;

import com.web.userlogin.model.Note;
import com.web.userlogin.model.User;
import com.web.userlogin.repository.NotesRepository;
import com.web.userlogin.repository.UserRepository;
import com.web.userlogin.service.NotesService;
import com.web.userlogin.service.UserService;
import com.web.userlogin.web.NotesController;
import com.web.userlogin.web.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
//  @ContextConfiguration(classes = {ApplicationTestContext.class})
public class TestHelper {

    @Autowired
    UserController userController;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    NotesController notesController;

    @Autowired
    NotesService notesService;

    @Autowired
    NotesRepository notesRepository;

    public void createUser(UserService userService){
        User user1= new User(1234L, "niketb2309","Niket Bhagwath",  "N!ket@1987", "N!ket@1987","niketb2309@gmail.com", "6-105, Aryanagar, Nizamabad",
                "9177533622","Male");
        User user2= new User(124L,"niketb81","Niket ",  "N!ket@1987", "N!ket@1987","niketb2309@gmail.com", "6-305, SSSnagar, Nizamabad",
                "9177233622","Male");
        User user3= new User(123L,"niketb23","Mary",  "N!ket@1987", "N!ket@1987","niketb2309@gmail.com", "6-205, RRnagar, Nizamabad",
                "9177513622","Female");

        User createdUser1=userService.save(user1);
        System.out.println("Created User:" + createdUser1.getUserName());
        User createdUser2=userService.save(user2);
        User createdUser3=userService.save(user3);

        List arrayList=new ArrayList<User>();
        arrayList.add(createdUser1);
        arrayList.add(createdUser2);
        arrayList.add(createdUser3);

    }

    void createNote(NotesService notesService){
        Note note1=new Note(124444L,"niketb2309","Hello World!","Hi How are you");
        Note note2=new Note(134444L,"niketb23","Memory Test","Memory Not Found");
        notesService.addNote(note1);
        notesService.addNote(note2);
    }

    public User createUser(){
        User user= new User(1234L, "niketb2309","Niket Bhagwath",  "N!ket@1987", "N!ket@1987","niketb2309@gmail.com", "6-105, Aryanagar, Nizamabad",
                "9177533622","Male");
        return user;
    }

    public Note createNote(){
        Note note=new Note(124444L,"niketb2309","Hello World!","Hi How are you");
        return note;
    }
}

