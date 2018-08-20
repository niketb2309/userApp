package com.web.userlogin.web;

import com.web.userlogin.model.Login;
import com.web.userlogin.model.User;
import com.web.userlogin.service.UserService;
import com.web.userlogin.validator.LoginValidator;
import com.web.userlogin.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    LoginValidator loginValidator;


    @GetMapping("/")
    public ModelAndView displayLoginpage(ModelAndView modelAndView, Login login) {
        modelAndView.addObject("login", login);
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/test")
    public ModelAndView test(ModelAndView modelAndView, Login login) {
        ModelAndView modelAndView1=new ModelAndView();
        return modelAndView1;
         }


    @GetMapping("/logout")
    public ModelAndView logoutUser(ModelAndView modelAndView, Login login) {
        modelAndView.addObject("login", login);
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView registerUser(ModelAndView modelAndView, User user) {
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }


    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute Login loginForm, BindingResult bindingResult, ModelAndView modelAndView) {
        loginValidator.validate(loginForm, bindingResult);
        if(bindingResult.hasErrors()){
            List<ObjectError> objectErrorList=bindingResult.getAllErrors();
            return "login";
        }
        return "welcome";
    }

    @PostMapping("/createUser")
    public String  registerUser(@Valid @ModelAttribute User userForm, BindingResult bindingResult, Model model) {
      User user=userForm;
      String userName=user.getUserName();
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            System.out.println("Validation has errors");
            List<ObjectError> objectErrorList=bindingResult.getAllErrors();
            System.out.println("Object errors list:"+objectErrorList);
            return "registration";
        }
        List<ObjectError> objectErrorList=bindingResult.getAllErrors();
        User createdUser= userService.save(userForm);
        model.addAttribute("userName",createdUser.getUserName() );
        return "success";
    }

    @GetMapping("/getUser")
    public void getUser(@RequestParam String userName){
        User user=userService.getUserByUserName(userName);
    }



    @GetMapping("/welcome")
    public String displayWelcomePage() {
        return "welcome";
    }

}

