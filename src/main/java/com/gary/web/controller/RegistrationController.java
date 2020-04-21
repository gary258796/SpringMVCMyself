package com.gary.web.controller;

import com.gary.event.OnRegistrationSuccessEvent;
import com.gary.persistence.entity.User;
import com.gary.persistence.entity.VerificationToken;
import com.gary.service.UserService;
import com.gary.web.dto.UserDto;
import com.gary.web.exception.EmailExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.logging.Logger;


@Controller
@RequestMapping("/register")
public class RegistrationController {


    @Autowired
    private UserService userService ;

    @Autowired
    private ApplicationEventPublisher eventPublisher ;

    private Logger logger = Logger.getLogger(getClass().getName());

    // clear space in form data's String
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showMyRegistrationPage(Model theModel) {

        theModel.addAttribute("userDto", new UserDto());

        return "registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm( @Valid @ModelAttribute("userDto") UserDto userDto,
            BindingResult theBindingResult, WebRequest request,
            Model theModel) {

        // form validation
        if (!theBindingResult.hasErrors()){
            try{
                // before safe , check if email is exists
                User registUser = userService.saveUser(userDto);
                String appUrl = request.getContextPath() ;

                // use ContextLoader instead of eventPublisher( which will cause listener triggered twice )
                ContextLoader.getCurrentWebApplicationContext().publishEvent(new OnRegistrationSuccessEvent(registUser, appUrl));
            }catch (EmailExistsException exe){
                theModel.addAttribute("registrationError", "Email already exists.");
                return "registration-form";
            }
        } else {
            return "registration-form";
        }


        logger.info("Successfully created user: " + userDto.getUserName() + "with email : " + userDto.getEmail());
        return "registration-confirmation";
    }

    @GetMapping("/confirmRegistration")
    public ModelAndView confirmRegistration(WebRequest request, Model model, @RequestParam("token") String token, RedirectAttributes redirectAttributes) {

        // get url token parameter ( token)
        VerificationToken verificationToken = userService.getVerificationToken(token);
        // if token is null, show invalidtoken msg in jsp page
        if(verificationToken==null){
            String message = "Token inValid! Please ask for new token!" ;
            return new ModelAndView("access-denied", "inValid_msg", message) ;
        }

        // get user from token method getUser()
        User confirmUser = verificationToken.getUser() ;
        // get Calender
        Calendar calendar = Calendar.getInstance() ;
        // if token expiry date is more than one day , show access-denied msg in jsp page
        if((verificationToken.getExpiryDate().getTime()-calendar.getTime().getTime()) <= 0 ){
            String message = "Token is expired! Apply a new one!" ;
            return new ModelAndView("access-denied", "inValid_msg", message) ;
        }

        // if we get here, means everything is ok
        // set enabled for user
        confirmUser.setEnabled(true);
        // update user in database
        userService.updateUser(confirmUser);
        // show success msg in login page
        String message = "Auth success! Please login!" ;
        return new ModelAndView("fancy-login", "success_Msg", message) ;
    }

}
