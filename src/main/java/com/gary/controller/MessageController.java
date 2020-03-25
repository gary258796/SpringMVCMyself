package com.gary.controller;


import com.gary.entity.Message;
import com.gary.entity.User;
import com.gary.service.MessageService;
import com.gary.util.SendEmailTLS;
import com.gary.util.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.logging.Logger;

@EnableAsync
@Controller
public class MessageController {


    private final int PAGENUM = 5;
    private final float FPAGENUM = 5.0f;
    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private MessageService messageService ;

    @Autowired
    private Validate validate ;

    @Autowired
    private SendEmailTLS sendEmailTLS ;

    @RequestMapping(value = "/message/submitMsg", method = {RequestMethod.POST})
    public String index(HttpSession session, HttpServletRequest request, Model model,@Valid Message message, BindingResult result) throws Exception {


        // get host and cur user
        User hostUser = (User) session.getAttribute("hostUser");
        User curUser = (User) session.getAttribute("curUser");

        validate.messageValidate(message, result);
        if (result.hasErrors()) {
            logger.info("\n\n>>>>>> error : " + result);

            if ( hostUser.getUserName() == curUser.getUserName() ) {
                return "redirect:/home";
            }else {
                return "redirect:/customerHomePage" ;
            }
        }


        // set message and store by calling Service to help
        message.setUserid(hostUser.getId());
        message.setDate(messageService.getDate());
        message.setIp(request.getRemoteAddr());
        message.setFromUserId(curUser.getId());
        messageService.saveMessage(message);


        // after return home or customer home
        // aop will help to set some info for home pages
        if (hostUser != curUser) { // return 之前 , 發訊息通知 hostuser有人留言給他
            sendEmailTLS.sendSimpleEmail("TestAccount@text.com",hostUser.getEmail(),"New Message", curUser.getUserName() + " leave message for you!");
            return "redirect:/customerHomePage";
        }
        return "redirect:/home";
    }

    @GetMapping( value = "/user/deleteMessage-{messageid}")
    public String deleteMessage( @PathVariable("messageid") int messageid) {

        logger.info("\n\n ^^^^ delete message with id : " + messageid );

        messageService.deleteMessageById( messageid );

        return "redirect:/home" ;
    }


}
