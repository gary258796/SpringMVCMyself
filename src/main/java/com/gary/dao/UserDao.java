package com.gary.dao;

import com.gary.entity.User;
import java.util.List;

public interface UserDao {

    User findByUserEmail(String userEmail);

    User findByUserName(String userName);

    User findById(int id);

    List<User> findByName(String name);

    List<User> findByEmail(String email);

    void saveUser(User user);

    void updateUser(User user);

    void updateUserImage(User user) ;

    void deleteUser(User user);

    Long findUserCount();

    List<User> findAllUser();

    List<User> findUserByPage(int pageNo, int pageSize);
    
}
