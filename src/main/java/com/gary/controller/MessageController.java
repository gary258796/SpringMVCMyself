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
import javax.servlet.http.HttpServletResponse;
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


    @RequestMapping(value = "message/submitMsg",  method = {RequestMethod.GET, RequestMethod.HEAD})
    public String leaveMessage(Model theModel, HttpSession session) {
        List<MessageJsonBean> list = messageService.findAllMessage();
        System.out.println( "\n >>>>>  list of messages : " + list + "\n") ;

        theModel.addAttribute("messages", list);
        theModel.addAttribute("message", new Message());

        User curUser = (User) session.getAttribute("curUser") ;
        if( curUser != null ) {
            // 已經登入的狀態
            theModel.addAttribute("curUser", curUser) ;
            theModel.addAttribute("ifLogin",true) ;
            return "leaveMessage" ;
        }

        // not login yet
        theModel.addAttribute("ifLogin", false ) ;

        return "leaveMessage" ;
    }


    @RequestMapping(value = "/message/submitMsg", method = {RequestMethod.POST})
    public String index(@Valid Message message, BindingResult result, Model model,
                        HttpSession session, HttpServletRequest request,
                        HttpServletResponse response) throws Exception{

        validate.messageValidate(message, result);
        if(result.hasErrors()){
            return "leaveMessage";
        }

        List<MessageJsonBean> list = messageService.findAllMessage();
        model.addAttribute("messages", list);

        // model.addAttribute("pageCount", (int)( Math.ceil(messageService.findMessageCount() / FPAGENUM) ));

        //信息正确则将留言信息set给message对象，调用messageService保存留言
        User hostUser = (User) session.getAttribute("hostUser");
        User curUser = (User) session.getAttribute("curUser");
        message.setUserid(hostUser.getId());
        message.setDate(messageService.getDate());
        message.setIp(request.getRemoteAddr());
        message.setFromusername(curUser.getUserName());
        messageService.saveMessage(message);

        // call mail service


        System.out.println("\n >>>> message : " + message);

        model.addAttribute("ifLogin", true);
        return "customerHomePage";
    }


    @RequestMapping(value = "/message/seeMsg-{page}", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String seeMessage( HttpSession session, Model themodel, @PathVariable int page) {

        User curUser = (User) session.getAttribute("curUser") ;
        List<Message> msgList = messageService.findMessagesByUserId(curUser.getId()) ;
        themodel.addAttribute("messages", msgList) ;
        themodel.addAttribute("id", curUser.getId()) ;
        themodel.addAttribute("count", (int)( Math.ceil(messageService.findMessageCount() / PAGENUM) )) ;

        return "message" ;
    }


    @GetMapping( value = "/user/deleteMessage-{messageid}")
    public String deleteMessage( @PathVariable("messageid") int messageid, Model model , HttpSession session) {

        System.out.println("\n >>>>>>> Delete msg with Id : " + messageid);
        messageService.deleteMessage( messageService.findMessageById(messageid) );
        System.out.println("\n >>>>>>> Success !  \n" );

        User curUser = (User) session.getAttribute("curUser") ;
        List<Message> msgList = messageService.findMessagesByUserId(curUser.getId()) ;
        model.addAttribute("messages", msgList) ;
        model.addAttribute("id", curUser.getId()) ;
        model.addAttribute("count" , (int)( Math.ceil(messageService.findMessageCount() / PAGENUM) )) ;

        return "message" ;
    }


}
