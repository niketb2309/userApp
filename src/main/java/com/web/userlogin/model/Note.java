package com.web.userlogin.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Note")
public class Note {

    private Long id;
    private String userName;
    private String title;
    private String description;
    private Date noteCreatedTime;
    private Date noteUpdatedTime;

    public Note(){

    }

    public Note(Long id, String userName, String title, String description) {
        this.id=id;
        this.userName = userName;
        this.title = title;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getNoteCreatedTime() {
        return noteCreatedTime;
    }

    public void setNoteCreatedTime(Date noteCreatedTime) {
        this.noteCreatedTime = noteCreatedTime;
    }

    public Date getNoteUpdatedTime() {
        return noteUpdatedTime;
    }

    public void setNoteUpdatedTime(Date noteUpdatedTime) {
        this.noteUpdatedTime = noteUpdatedTime;
    }

}
