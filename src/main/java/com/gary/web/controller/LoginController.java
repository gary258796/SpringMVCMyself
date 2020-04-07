package com.gary.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/showMyLoginPage")
    public String showLoginPage() {
        return "fancy-login" ;
    }



    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }
}
