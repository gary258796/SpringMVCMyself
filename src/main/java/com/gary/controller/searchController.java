package com.gary.controller;


import com.gary.entity.User;
import com.gary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class searchController {


    @Autowired
    private UserService userService ;


    @GetMapping(value = "/searchOthers")
    public String searchOthers(@RequestParam("findName") String searchName, Model theModel, HttpSession session) {
        System.out.println("\n >>>>> Searching Name : " + searchName);

        User curUser = (User) session.getAttribute("curUser") ;
        User hostUser = userService.findByUserName( searchName );


        theModel.addAttribute("curUser",curUser) ;
        session.setAttribute("curUser", curUser);

        if ( hostUser == null ) {
            System.out.println("\n >>>>> No Searching data with name : " + searchName);

            return "home" ;
        }

        theModel.addAttribute("hostUser" , hostUser) ;
        session.setAttribute("hostUser", hostUser);
        return "customerHomePage" ;
    }


    @GetMapping(value = "/search/returnHomePage")
    public String backtoHome(HttpSession session) {

        User curUser = (User) session.getAttribute("curUser") ;

        session.setAttribute("hostUser", curUser);

        return "home" ;
    }

}
