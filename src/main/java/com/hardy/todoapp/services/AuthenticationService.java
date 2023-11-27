package com.hardy.todoapp.services;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public boolean authenticate(String userName, String password){
        boolean isUser = userName.equals("hamza");
        boolean isPass = password.equals("elhadrachi");

        return isUser && isPass;

    }
}
