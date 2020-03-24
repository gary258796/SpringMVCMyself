package com.gary.service;

import com.gary.entity.Crm.CrmUser;
import com.gary.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {



    User findByUserEmail(String userEmail) ;

    User findByUserName(String userName);

    User findById(int id);

    String retNameById(int id) ;

    User findByName(String name);

    User findByEmail(String email);

    void saveUser(CrmUser user);

    void saveUser(User user);

    void updateUser(User user);

    void updateUserImage(User user) ;

    void deleteUser(User user);

    Long findUserCount();

    List<User> findUserByPage(int pageNo, int pageSize);

    List<User> findAllUser();

    boolean isUserEmailExist(String email);

    boolean isUserEmailExistExceptSelf(String sqlEmail, String localEmail);

}
