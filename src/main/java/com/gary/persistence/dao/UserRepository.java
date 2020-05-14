package com.gary.persistence.dao;


import com.gary.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String userEmail);

    User findByUserName(String userName);

    User findById(int id);

    List<User> findAllByUserName(String userName);

    List<User> findAllByEmail(String email);

    //    String retNameById(int id) ;

//    void saveUser(User user);

//    void updateUser(User user);
//
//    void updateUserImage(User user) ;

//    void deleteUser(User user);

//    Long findUserCount();

//    List<User> findAllUser();

//    List<User> findUserByPage(int pageNo, int pageSize);
    
}
