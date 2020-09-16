package com.example.loginsystem.controller;

import com.example.loginsystem.model.User;
import com.example.loginsystem.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    IUserRepository userDBRepository;

    @GetMapping("/")
    public String index(HttpSession session) {

        if (session.getAttribute("isLogIn)") != null) {
            return "panel";
        }

        return "index";
    }

    @PostMapping("/")
    public String login(@ModelAttribute User user, Model model, HttpSession session) {
        User u = userDBRepository.read(user.getEmail());
        if (u != null) {
            session.setAttribute("isLogIn", "yes");

            model.addAttribute("users", userDBRepository.readAll());
            return "panel";
        }
        return "index";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        userDBRepository.create(user);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {

        session.removeAttribute("isLogIn");
        if (session.getAttribute("isLogIn") != null) {
            return "panel";
        }

        return "index";
    }

}
