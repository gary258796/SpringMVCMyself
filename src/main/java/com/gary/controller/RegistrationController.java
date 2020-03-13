package com.gary.controller;


import com.gary.entity.Crm.CrmUser;
import com.gary.entity.User;
import com.gary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Logger;
import javax.validation.Valid;


@Controller
@RequestMapping("/register")
public class RegistrationController {


    @Autowired
    private UserService userService ;

    private Logger logger = Logger.getLogger(getClass().getName());

    // clear space in form data's String
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showRegistrationForm")
    public String showMyRegistrationPage(Model theModel) {

        theModel.addAttribute("crmUser", new CrmUser());

        return "registration-form";
    }

    @PostMapping("/processRegistrationForm")
    public String processRegistrationForm( @Valid @ModelAttribute("crmUser") CrmUser theCrmUser,
            BindingResult theBindingResult,
            Model theModel) {


        String userName = theCrmUser.getUserName();
        String userEmail = theCrmUser.getEmail() ;
        logger.info("Processing registration form for: " + userName);
        logger.info("Registration email : " + userEmail );

        // form validation
        if (theBindingResult.hasErrors()){
            return "registration-form";
        }

        // check the database if user already exists
        User existing = userService.findByUserEmail(userEmail) ;  // findByUserName(userName);
        if (existing != null){
            theModel.addAttribute("crmUser", new CrmUser());
            theModel.addAttribute("registrationError", "Email already exists.");

            logger.warning(">>>>>>> User email already exists.");
            return "registration-form";
        }
        // not exist, so create user account
        userService.saveUser(theCrmUser);

        logger.info("Successfully created user: " + userName + "with email : " + userEmail);

        return "registration-confirmation";
    }

}
