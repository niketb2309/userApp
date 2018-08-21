package com.web.userlogin;

import com.web.userlogin.model.Note;
import com.web.userlogin.repository.NotesRepository;
import com.web.userlogin.service.NotesService;
import com.web.userlogin.validator.NotesValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ComponentScan(basePackages="com")
public class NotesUnitTest {

    private MockMvc mvc;

    @InjectMocks
    private NotesService mockNotesService;

    @Mock
    private NotesRepository mockNotesRepository;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    TestHelper testHelper;

    @Mock
    Errors mockErrors;

    @MockBean
    Note noteForm;

    @Autowired
    NotesValidator notesValidator;

    @Mock
    NotesRepository notesRepository;

    @Before
    public void setUp() {

        noteForm = testHelper.createNote();
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void create_Note_Controller_test() throws Exception{
        Note note= testHelper.createNote();
        when(mockNotesService.addNote(note)).thenReturn(note);
        Note createdNote=mockNotesService.addNote(note);

        this.mvc
                .perform(
                        post("/addNote")
                                .flashAttr("note",createdNote)
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(model().hasNoErrors())
                .andExpect(status().isOk());

        verify(mockNotesService, times(1)).addNote(createdNote);
        verifyNoMoreInteractions(mockNotesService);
    }

    @Test
    public void update_Note_Controller_test() throws Exception{
        Note note= testHelper.createNote();
        note.setDescription("Hi Howzz the day");

        Optional<Note> optionalNote= java.util.Optional.of(note);
        Mockito.<Optional<Note>>when(mockNotesRepository.findById(124444L)).thenReturn(optionalNote);
        when(mockNotesService.updateNote(note)).thenReturn(note);
        Note updatedNote= mockNotesService.updateNote(note);

        this.mvc
                .perform(
                        post("/updateNote")
                                .flashAttr("note", updatedNote)
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(model().hasNoErrors())
                .andExpect(status().isOk());

        verify(mockNotesService, times(1)).updateNote(updatedNote);
        verifyNoMoreInteractions(mockNotesService);
    }

    @Test
    public void create_Note_Service_test() throws Exception{
        Note note= testHelper.createNote();
        when(mockNotesService.addNote(note)).thenReturn(note);
        Note createdNote=mockNotesService.addNote(note);
        assertThat(createdNote.getUserName()).isEqualTo("niketb2309");
        assertThat(createdNote.getTitle()).isEqualTo("Hello World!");
        assertThat(createdNote.getDescription()).isEqualTo("Hi How are you");
    }

    @Test
    public void update_Note_Service_test() throws Exception{
        Note note= testHelper.createNote();
        note.setDescription("Good Eve");
        when(mockNotesService.updateNote(note)).thenReturn(note);
        Note updatedNote= mockNotesService.updateNote(note);
        assertThat(updatedNote.getUserName()).isEqualTo("niketb2309");
        assertThat(updatedNote.getTitle()).isEqualTo("Hello World!");
        assertThat(updatedNote.getDescription()).isEqualTo("Good Eve");
    }

    @Test
    public void test_DeleteNote(){
        Note note= testHelper.createNote();
        doNothing().when(mockNotesService).deleteNote(note.getId());

    }
}
