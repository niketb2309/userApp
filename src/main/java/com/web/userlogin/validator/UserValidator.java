package com.web.userlogin.validator;

import com.web.userlogin.model.User;
import com.web.userlogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

    @Component
    @ComponentScan(basePackages = "com.web.userlogin.service")
    public class UserValidator implements Validator {

        @Autowired
        UserService userService;

        @Override
        public boolean supports(Class<?> aClass) {
            return User.class.equals(aClass);
        }

        @Override
        public void validate(Object o, Errors errors) {
            User user=(User)o;
           boolean formValidationWithErrors= validateForm( user,errors);
            if(formValidationWithErrors==false) {
                if (userService.getUserByUserName(user.getUserName()) != null) {
                    errors.rejectValue("userName", "Duplicate.userForm.username", "Username already available");
                }

            }
        }

        private boolean validateForm(User user, Errors errors) {

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");

            if (user.getUserName().length() < 6 || user.getUserName().length() > 32) {
                errors.rejectValue("userName", "Size.userForm.username", "size should be more than 6 charecters");
                return true;
            }

            if (user.getName().length() < 6 || user.getName().length() > 32) {
                errors.rejectValue("name", "Size.userForm.name", "size should be more than 6 charecters");
                return true;
            }

            if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
                errors.rejectValue("password", "Size.userForm.password", "size should be more than 6 charecters");
                return true;
            }

            if (!user.getConfirmPassword().equals(user.getPassword())) {
                errors.rejectValue("confirmPassword", "Diff.userForm.passwordConfirm","Passwords do not match");
                return true;
            }
            if(user.getPhoneNumber().length()<7||user.getPhoneNumber().length()>15){
                errors.rejectValue("phoneNumber", "User.phoneNumber","size should be less than 15");
                return true;
            }

            if ((user.getAddress().length())<15) {
                errors.rejectValue("address", "User.address", "size should be greater than 15");
                return true;
            }
            return false;
        }
    }

