package com.web.userlogin.validator;

import com.web.userlogin.model.Login;
import com.web.userlogin.model.User;
import com.web.userlogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

import static org.springframework.validation.ValidationUtils.rejectIfEmptyOrWhitespace;

@Component
public class LoginValidator implements Validator {

    @Autowired
    private UserService userService;

    public boolean supports(Class aClass) {
        return Login.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Login login = (Login) obj;
        rejectIfEmptyOrWhitespace(errors, "userName",
                "username.required", "Required field");

        rejectIfEmptyOrWhitespace(errors, "password",
                "password.required", "Required field");

        User user=userService.getUserByUserName(login.getUserName());
        if(Objects.isNull(user)){
            System.out.println("User is null");
            errors.rejectValue("userName","User.NotFound");
        }
        else if(!Objects.equals(login.getPassword(),user.getPassword())||
                !Objects.equals(login.getUserName(),user.getUserName())){

            System.out.println("userPassword:" +user.getPassword());
            System.out.println("loginPassword:" +login.getPassword());

            System.out.println("userPassword hashCode"+user.getPassword().hashCode());
            System.out.println("loginPassword hashCode"+login.getPassword().hashCode());

            errors.rejectValue("password", "Incorrect.Password");
        }
    }
}

