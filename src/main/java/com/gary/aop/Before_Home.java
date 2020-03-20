package com.gary.aop;

import com.gary.entity.Message;
import com.gary.entity.User;
import com.gary.service.MessageService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Logger;

@Component
@Aspect
public class Before_Home {

    @Autowired
    private MessageService messageService ;

    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.gary.controller.HomeController.home(..))" )
    private void home() {}

    @AfterReturning("home() && args(model,request,session,..)")
    public void beforeGoHome(HttpSession session,HttpServletRequest request, Model model) {

        System.out.println("\n >>>>>>>> We got into home !!!!\n");
        prepareBeforeGoHome(session,request,model,false);

    }

    @AfterReturning("execution(* com.gary.controller.MessageController.index(..)) && args(session,request,model,..)")
    public void index( HttpSession session, HttpServletRequest request, Model model) {

        System.out.println("\n >>>>>>>> after return home and got into index !!!!\n");
        prepareBeforeGoHome(session,request,model,true);
    }

    @AfterReturning("execution(* com.gary.controller.EditProfileController.uploadFileHandler(..)) && args(session,request,model,..)")
    private void uploadFile(HttpSession session, HttpServletRequest request, Model model) {
        System.out.println("\n >>>>>>>> after return home and got into uploadFile !!!!\n");
        prepareBeforeGoHome(session,request,model,false);
    }

    @AfterReturning("execution(* com.gary.controller.EditProfileController.saveInfo(..)) && args(..,session,request,model)")
    public void saveInfo(HttpSession session, HttpServletRequest request, Model model){
        System.out.println("\n >>>>>>>> after return home and got into saveInfo !!!!\n");
        prepareBeforeGoHome(session,request,model,false);
    }

    @AfterReturning("execution(* com.gary.controller.searchController.searchOthers(..)) && args(..,session,request,model)")
    public void searchOthers(HttpSession session, HttpServletRequest request, Model model) {
        System.out.println("\n >>>>>>>> after return home and got into searchOthers home !!!!\n");
        prepareBeforeGoHome(session,request,model,true);
    }

    public void prepareBeforeGoHome(HttpSession session, HttpServletRequest request, Model model, boolean isIndex) {

        List<Message> msgList ;
        session = request.getSession() ;
        User curUser = (User) session.getAttribute("curUser");
        User hostUser = (User) session.getAttribute("hostUser");


        // 因為有可能 是從customer地方留言
        // 留言完成之後 , 留在 customer page
        // cur != host  代表在人家版上留言
        // msgList 要抓人家的
        // cur host 不用設定為一樣
        if ( isIndex && curUser != hostUser ) { // 留言功能
            msgList = messageService.findMessagesByUserId(hostUser.getId()) ;
        }
        else { // 其他非留言功能回主頁
            session.setAttribute("hostUser", curUser);
            msgList = messageService.findMessagesByUserId(curUser.getId()) ;
        }


        model.addAttribute("message", new Message()) ;
        model.addAttribute("curUser", curUser );
        model.addAttribute("id", curUser.getId()) ;
        model.addAttribute("messages", msgList) ;
    }


}
