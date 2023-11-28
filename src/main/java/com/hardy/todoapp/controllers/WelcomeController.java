package com.hardy.todoapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("name")
public class WelcomeController {

    @GetMapping("/")
    public String goToWelcomePage(){
        return "index";
    }

}
