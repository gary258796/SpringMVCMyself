package com.gary.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Controller
public class HomeController {

    private Logger logger = Logger.getLogger(getClass().getName());

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model, HttpServletRequest request, HttpSession session) {


        logger.info("\n\n\n >>>>>>> HomeController !! \n\n\n");

        return "home" ;
        // home 需要的資料會由aop去抓取及輸入到model 及session
    }

    @RequestMapping(value = "/customerHomePage", method = RequestMethod.GET)
    public String customerHome(Model model, HttpServletRequest request, HttpSession session) {

        logger.info("\n\n\n >>>>>>> customerHomePage get !! \n\n\n");

        return "customerHomePage" ;
        // customerHomePage 需要的資料會由aop去抓取及輸入到model 及session
    }

}
