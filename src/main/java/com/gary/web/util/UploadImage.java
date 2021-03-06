package com.gary.web.util;


import com.gary.persistence.entity.User;
import com.gary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Component
public class UploadImage {


    @Autowired
    private UserService userService ;

    public void uploadimg(MultipartFile file, User user, String rootPath){

        File uploadRootDir = new File(rootPath);
        System.out.println("\n <<<< uploadRootDir : " + uploadRootDir.getPath() );

        if ( uploadRootDir.getPath() == rootPath ) { // 沒有選擇照片上傳


            System.out.println("\n <<<<  nothing!!!!!!!!!!!!!!!!");

            // *** 跳通知 請使用者選擇照片
            // *** 加上返回button

        }

        // make dir
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }

        String name = file.getOriginalFilename();
        System.out.println("\n >>>>>>>>>> name : " + name);

        try {
            if(name != "" && user.getImgUrl() != null){
                String imgurl = rootPath + File.separator + user.getImgUrl() ;
                File imgFile = new File(imgurl);
                imgFile.delete(); // 存在就先刪掉那檔案
            }
        }
        catch( Exception e ){
            System.out.println("Exception : "+ e);
        }

        user.setImgUrl(name);
        // userService.updateUser(user);
        // System.out.println("Client File Name = " + name);
        if (name != null && name.length() > 0) {

            userService.updateUserImage(user);

            try {
                byte[] bytes = file.getBytes();
                // 创建跨平台的路径
                File serverFile = new File(uploadRootDir.getAbsolutePath()
                        + File.separator + name);
                // 创建输入，输出流
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                System.out.println("Write file: " + serverFile);
            } catch (Exception e) {
                System.out.println("Error Write file: " + name);
            }
        }
    }

}
