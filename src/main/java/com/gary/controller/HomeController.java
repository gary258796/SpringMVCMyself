package com.gary.controller;


import com.gary.entity.User;
import com.gary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private UserService userService ;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model, HttpServletRequest request, HttpSession session) {

        session = request.getSession() ;
        User user = (User) session.getAttribute("user" ) ;

        session.setAttribute("curUser", user );
        session.setAttribute("hostUser", user);
        model.addAttribute("curUser", user );

        return "home" ;
    }

}
