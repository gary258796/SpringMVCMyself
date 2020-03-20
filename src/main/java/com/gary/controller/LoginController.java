package com.gary.controller;


import com.gary.Bean.MessageJsonBean;
import com.gary.entity.Message;
import com.gary.entity.User;
import com.gary.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

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
