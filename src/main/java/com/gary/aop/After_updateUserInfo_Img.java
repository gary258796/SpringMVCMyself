package com.gary.aop;

import com.gary.persistence.entity.User;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Component
@Aspect
public class After_updateUserInfo_Img {

    @Autowired
    private HttpSession session ;

    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.gary.persistence..dao.UserDaoImpl.updateUser(..))")
    public void updateUser(){}

    @After("updateUser() && args(user)")
    public void refreshSessionData(User user) {
        logger.info("\n\n\n &&&&& Refresh session after update userInfo with UserName : " + user.getUserName() + "\n\n");

        session.setAttribute("curUser", user);
    }
}
