package com.hardy.todoapp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("name")
public class WelcomeController {

    @GetMapping("/")
    public String goToWelcomePage(ModelMap map){
        map.put("name", getLoggedUserName());
        return "index";
    }

    private String getLoggedUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }

}
