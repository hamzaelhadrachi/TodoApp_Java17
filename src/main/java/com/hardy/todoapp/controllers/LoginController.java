package com.hardy.todoapp.controllers;

import com.hardy.todoapp.services.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("name")
public class LoginController {

    private AuthenticationService authenticationService;
    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String goToLoginPage(){
        return "login";
    }

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String goToHomePage(@RequestParam String name,@RequestParam String password, ModelMap model){
        model.put("name",name);
        if (authenticationService.authenticate(name,password)){
            return "index";
        }
        model.put("errorMessage", "invalid Credentials");
        return "login";
    }

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
}
