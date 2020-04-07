package com.gary.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gary.persistence.entity.User;
import com.gary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


@Component
@ComponentScan(basePackages = "com.gary")
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		System.out.println("\n\nIn customAuthenticationSuccessHandler\n\n");

		String userName = authentication.getName() ;
		User theUser = userService.findByUserName(userName) ;
		System.out.println("\n\n User : " + theUser + " login!\n" );

		HttpSession session = request.getSession();
		session.setAttribute("curUser", theUser);
		session.setAttribute("hostUser", theUser);
		
		// forward to home page
		
		response.sendRedirect(request.getContextPath() + "/home"); // 回到 home page
	}

}
