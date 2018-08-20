package com.web.userlogin;

import com.web.userlogin.service.NotesService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {UserloginApplication.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages="com.web.userlogin.validator")
public class NotesIntegerationTest {

    MockMvc mockMvc;

    @Autowired
    NotesService notesService;

    TestHelper testHelper=new TestHelper();


    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        testHelper.createNote(notesService);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void addNoteTest() throws Exception {
        this.mockMvc
                .perform(
                        post("/addNote")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("userName","niketb2309")
                                .param("title", "Memory")
                                .param("description", "Not Found"))
                .andExpect(model().hasNoErrors())
                .andExpect(status().isOk());
    }


    @Test
    public void getNotesTest() throws Exception {
        this.mockMvc
                .perform(
                        get("/getNotes")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("userName","niketb2309"))

                .andExpect(status().isOk());
    }

    @Test
    public void updateNoteTest() throws Exception {
        this.mockMvc
                .perform(
                        post("/updateNote")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("id", "1")
                                .param("userName", "niket87")
                                .param("title", "Good Day")
                                .param("description", "Have a great day")
                )
                .andExpect(model().hasNoErrors())
                .andExpect(status().isOk());

    }

    @Test
    public void deleteNoteTest() throws Exception {
        this.mockMvc
                .perform(
                        get("/deleteNote")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id","1"))

                .andExpect(model().hasNoErrors())
                .andExpect(status().isOk());
    }
}
