package com.web.userlogin.web;

import com.web.userlogin.model.Login;
import com.web.userlogin.model.User;
import com.web.userlogin.service.NotesService;
import com.web.userlogin.service.UserService;
import com.web.userlogin.validator.LoginValidator;
import com.web.userlogin.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.lang.invoke.MethodHandles;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    LoginValidator loginValidator;

    @Autowired
    HttpSession httpSession;

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private NotesService notesService;

    @GetMapping("/")
    public ModelAndView displayLoginpage(ModelAndView modelAndView, Login login) {
        modelAndView.addObject("login", login);
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logoutUser(ModelAndView modelAndView, Login login) {
        httpSession.invalidate();
        modelAndView.addObject("login", login);
        modelAndView.setViewName("logout");
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView registerUser(ModelAndView modelAndView, User user) {
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }


    @PostMapping("/login")
    public String loginUser(HttpSession session, @Valid @ModelAttribute Login loginForm, BindingResult bindingResult, ModelAndView modelAndView) {
        loginValidator.validate(loginForm, bindingResult);
        if(bindingResult.hasErrors()){
            List<ObjectError> objectErrorList=bindingResult.getAllErrors();
            return "login";
        }
        session.setAttribute("userName",loginForm.getUserName());
        List notesList=notesService.retrieveNote(loginForm.getUserName());
        session.setAttribute("userNotes",notesList);
        return "welcome";
    }

    @PostMapping("/createUser")
    public String  registerUser(@Valid @ModelAttribute User userForm, BindingResult bindingResult, Model model) {
      User user=userForm;
      String userName=user.getUserName();
        userValidator.validate(userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        User createdUser= userService.save(userForm);
        log.debug("User Created Details:",createdUser.toString());
        model.addAttribute("userName",createdUser.getUserName() );
        return "success";
    }

    @GetMapping("/getUser")
    public void getUser(@RequestParam String userName){
        User user=userService.getUserByUserName(userName);
    }

    @GetMapping("/welcome")
    public String displayWelcomePage() {
        List notesList=notesService.retrieveNote((String)httpSession.getAttribute("userName"));
        httpSession.setAttribute("userNotes",notesList);
        return "welcome";
    }
}
