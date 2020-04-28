package com.gary.config;

import com.gary.persistence.entity.User;
import com.gary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.annotation.Transactional;


public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    private UserService userService ;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        final User user = userService.findByUserEmail(auth.getName());

        System.out.println("Name: " + auth.getName());
        if ((user == null)) {
            throw new BadCredentialsException("Invalid username or password");
        }

        Authentication result = super.authenticate(auth);
        return new UsernamePasswordAuthenticationToken(user, result.getCredentials(), result.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
