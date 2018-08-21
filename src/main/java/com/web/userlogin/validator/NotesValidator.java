package com.web.userlogin.validator;

import com.web.userlogin.model.Note;
import com.web.userlogin.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class NotesValidator implements Validator {
    @Autowired
    private NotesService notesService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Note.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Note note = (Note) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty");

        if (note.getTitle().length() < 6 || note.getTitle().length() > 32) {
            errors.rejectValue("title", "Note.title", "Length should be more than 6 charecters and less than 32");
        }
        if (note.getDescription().length() < 6) {
            errors.rejectValue("description", "Note.title", "Length should be more than 6 charecters");
        }

    }

}