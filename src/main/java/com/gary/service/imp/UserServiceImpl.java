package com.gary.service.imp;

import com.gary.persistence.dao.RoleDao;
import com.gary.persistence.dao.UserDao;
import com.gary.persistence.entity.Role;
import com.gary.persistence.entity.User;
import com.gary.service.UserService;
import com.gary.web.dto.UserDto;
import com.gary.web.exception.EmailExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Transactional// 使用默認的transactionManager
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao ;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    @Override
    public User findByUserEmail(String userEmail) {
        return userDao.findByUserEmail(userEmail) ;
    }

    // 名稱無法修改因為是繼承
    // 但內容我們實質上 是 loadUserByEmail
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = userDao.findByUserEmail(userEmail);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid userEmail sor password.");
        }
        // user isn't null
        // check if user is enabled or not(有沒有驗證過)
        if( !user.isEnabled() ) { // not enabled
            throw new UsernameNotFoundException("Not enabled yet. Please check your mail and click the link we sent for you when you registered.");
        }

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }


    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    // -----------------------------------------

    public UserServiceImpl() {
        super();
    }

    @Override
    public User findById(int id) {
        return userDao.findById( id ) ;
    }

    @Override
    public User findByName(String name) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public String retNameById(int id) {
        return userDao.retNameById(id) ;
    }

    @Override
    public User saveUser(UserDto userDto) throws EmailExistsException {

        if(isUserEmailExist(userDto.getEmail())){
            throw new EmailExistsException() ;
        }

        User user = new User();
        // assign user details to the user object
        user.setUserName(userDto.getUserName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        // give user default role of "USER"
        user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_USER")));

        userDao.saveUser(user);

        return user ;
    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public void updateUser(User user) { // 編輯修改基本信息時 呼叫


        System.out.println("\n >>>>>>>>> user.getPassword() : " + user.getPassword()) ;
        System.out.println("\n >>>>>>>>> user.getPassword() : " + user.getPassword()) ;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_USER")));
        user.setImgUrl( user.getImgUrl() );

        userDao.updateUser( user );
    }

    @Override
    public void updateUserImage(User user) {
        userDao.updateUserImage( user );
    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public Long findUserCount() {
        return null;
    }

    @Override
    public List<User> findUserByPage(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public List<User> findAllUser() {
        return userDao.findAllUser() ;
    }

    @Override
    public boolean isUserEmailExist(String email) {
        User checkUser = userDao.findByUserEmail(email) ;
        if(checkUser==null) return false ;

        return true;
    }

    @Override
    public boolean isUserEmailExistExceptSelf(String sqlEmail, String localEmail) {

        List<User> users = findAllUser();
        for(User user : users){
            if(!user.getEmail().equals(localEmail)){
                if(user.getEmail().equals(sqlEmail)){
                    return true;
                }
            }
        }
        return false;
    }
}
