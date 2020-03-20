package com.gary.controller;

import com.gary.entity.User;
import com.gary.service.UserService;
import com.gary.util.UploadImage;
import com.gary.util.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.logging.Logger;

@Controller
public class EditProfileController {

    private final String IMGURL = "/WEB-INF/upload";

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private UserService userService ;

    @Autowired
    private Validate validate;

    @Autowired
    private UploadImage uploadImg ;


    @GetMapping(value = "/editProfile/edit-{id}")
    public String editProfile(@PathVariable int id, Model theModel) {

        User theUser = userService.findById( id ) ;
        theModel.addAttribute("curUser", theUser) ;

        return "edit-info" ;
    }

    @PostMapping(value = "/editProfile/submitUpdate")
    public String saveInfo( @Valid @ModelAttribute("curUser") User user, Errors errors,
                            BindingResult theBindingResult, HttpSession session, HttpServletRequest request, Model model) {
//
//        validate.updateValidate(user, user.getId(), theBindingResult);
//        if( errors.hasErrors() ) {
//            logger.info("\n >>>>>>>>>>>  error : " + errors);
//            return "edit-info" ;
//        }

        validate.updateValidate(user, user.getId(), theBindingResult);
        if ( theBindingResult.hasErrors() ) {
            return  "edit-info" ;
        }

        userService.updateUser( user );
        return "home" ;
    }

    @GetMapping(value = "/editProfile/edit-img")
    public String editImage(HttpSession session, Model model) {

        User theUser = (User) session.getAttribute("curUser");
        model.addAttribute("curUser", theUser ) ;

        return "editimg" ;
    }




    @PostMapping("/editProfile/uploadImage")
    public String uploadFileHandler(HttpSession session, HttpServletRequest request, Model model,
                                    @RequestParam("uploadfile") MultipartFile file) {


        if ( file == null ) { // 沒有選擇圖片 就直接回去 home page
            return "home" ;
        }

        User user = (User) session.getAttribute("user");
        String rootPath = request.getServletContext().getRealPath(IMGURL);
        uploadImg.uploadimg(file, user, rootPath);


        model.addAttribute("curUser", user);
        return "home";
    }

}
