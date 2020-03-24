package com.gary.controller;

import com.gary.entity.User;
import com.gary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class searchController {


    @Autowired
    private UserService userService ;


    @GetMapping(value = "/searchOthers")
    public String searchOthers(@RequestParam("findName") String searchName, HttpSession session, HttpServletRequest request, Model model) {
        System.out.println("\n >>>>> Searching Name : " + searchName);

        User hostUser = userService.findByUserName( searchName );

        if ( hostUser == null ) {
            System.out.println("\n >>>>> No Searching data with name : " + searchName);

            // 可以加上 通知 沒有此收尋之使用者
            return "redirect:/home" ;
        }

        model.addAttribute("hostUser" , hostUser) ;
        session.setAttribute("hostUser", hostUser);
        return "redirect:/customerHomePage" ;
    }

}
