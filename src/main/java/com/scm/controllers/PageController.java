package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home page handler");
        // sending data to view
        model.addAttribute("name", "Piyush");
        model.addAttribute("youtubeChannel", "Piyush");
        model.addAttribute("githubRepo", "https://github.com/Piyushvishwakarma242004");
        return "home";
    }

    // about route

    @RequestMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("isLogin", true);
        System.out.println("About page loading");
        return "about";
    }

    // services

    @RequestMapping("/services")
    public String servicesPage() {
        System.out.println("services page loading");
        return "services";
    }

    // contact page

    @GetMapping("/contact")
    public String contact() {
        return new String("contact");
    }

    // this is showing login page
    @GetMapping("/login")
    public String login() {
        return new String("login");
    }

    // registration page
    @GetMapping("/register")
    public String register(Model model) {

        UserForm userForm = new UserForm();
        // default data bhi daal sakte hai
        // userForm.setName("Durgesh");
        // userForm.setAbout("This is about : Write something about yourself");
        model.addAttribute("userForm", userForm);

        return "register";
    }

    // processing register

    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult,
            HttpSession session) {
        System.out.println("Processing registration");
        // fetch form data
        // UserForm
        System.out.println(userForm);

        // validate form data
        if (rBindingResult.hasErrors()) {
            return "register";
        }

        // TODO::Validate userForm[Next Video]

        // save to database

        // userservice

        

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setEnabled(false);
        // user.setProfilePic(
        //         "https://www.learncodewithdurgesh.com/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Fdurgesh_sir.35c6cb78.webp&w=1920&q=75");
        user.setProfilePic(
                "data:image/png;base64,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");


        User savedUser = userService.saveUser(user);

        System.out.println("user saved :");

        // message = "Registration Successful"

        // add the message:

        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();

        session.setAttribute("message", message);

        // redirectto login page
        return "redirect:/register";
    }

}
