package com.gary.config;

import com.gary.security.CustomAuthenticationProvider;
import com.gary.security.CustomAuthenticationSuccessHandler;
import com.gary.security.CustomAuthenticationFailureHandler;
import com.gary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = { "com.gary.security" })
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler authenticationFailureHandler ;

//    @Autowired
//    private DataSource securityDataSource ;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // auth.jdbcAuthentication().dataSource( securityDataSource ) ;
        auth.authenticationProvider( authenticationProvider() ) ;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

       http.authorizeRequests()
               .antMatchers("/").hasRole("USER")
                    .and()
               .formLogin()
                    .loginPage("/showMyLoginPage")
                    .loginProcessingUrl("/authenticateTheUser")
                    .usernameParameter("email")
                    .successHandler(customAuthenticationSuccessHandler)
                    .failureHandler(authenticationFailureHandler)
               .permitAll()
                    .and()
               .logout().permitAll()
                    .and()
               .exceptionHandling().accessDeniedPage("/access-denied");


    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder() ;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        final CustomAuthenticationProvider auth = new CustomAuthenticationProvider() ;
        auth.setUserDetailsService(userService); //set the custom user details service
        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
        return auth;
    }


}
