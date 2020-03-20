package com.gary.controller;


import com.gary.Bean.MessageJsonBean;
import com.gary.entity.Message;
import com.gary.entity.User;
import com.gary.service.MessageService;
import com.gary.util.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@Controller
public class MessageController {


    private final int PAGENUM = 5;
    private final float FPAGENUM = 5.0f;

    @Autowired
    private MessageService messageService ;

    @Autowired
    private Validate validate ;

    @RequestMapping(value = "/message/submitMsg", method = {RequestMethod.POST})
    public String index(HttpSession session, HttpServletRequest request, Model model,@Valid Message message, BindingResult result) throws Exception{

        validate.messageValidate(message, result);
        if(result.hasErrors()){
            return "home";
        }

        // get host and cur user
        User hostUser = (User) session.getAttribute("hostUser");
        User curUser = (User) session.getAttribute("curUser");

        // set message and store by calling Service to help
        message.setUserid(hostUser.getId());
        message.setDate(messageService.getDate());
        message.setIp(request.getRemoteAddr());
        message.setFromusername(curUser.getUserName());
        messageService.saveMessage(message);

        System.out.println("\n >>>> message : " + message);

        // after return home or customer home
        // aop will help to set some info for home pages

        if( hostUser != curUser ) return "customerHomePage";
        return "home" ;
    }

//
//    @RequestMapping(value = "/message/seeMsg-{page}", method = {RequestMethod.GET, RequestMethod.HEAD})
//    public String seeMessage( HttpSession session, Model themodel, @PathVariable int page) {
//
//        User curUser = (User) session.getAttribute("curUser") ;
//        List<Message> msgList = messageService.findMessagesByUserId(curUser.getId()) ;
//        themodel.addAttribute("messages", msgList) ;
//        themodel.addAttribute("id", curUser.getId()) ;
//        themodel.addAttribute("count", (int)( Math.ceil(messageService.findMessageCount() / PAGENUM) )) ;
//
//        return "message" ;
//    }


    @GetMapping( value = "/user/deleteMessage-{messageid}")
    public String deleteMessage( @PathVariable("messageid") int messageid, Model model , HttpSession session) {
        return null ;
    }


}
