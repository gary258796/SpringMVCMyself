package com.gary.service.imp;

import com.gary.persistence.dao.RoleRepository;
import com.gary.persistence.dao.UserRepository;
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
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User findByUserEmail(String userEmail) {
        return userRepository.findByEmail(userEmail) ;
    }

    // 名稱無法修改因為是繼承
    // 但內容我們實質上 是 loadUserByEmail
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

//        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        try {
            User user = userRepository.findByEmail(userEmail);
            if (user == null) {
                throw new UsernameNotFoundException("No user found with username: " + userEmail);
            }

            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword().toLowerCase(),
                    user.isEnabled(),
                    accountNonExpired,
                    credentialsNonExpired,
                    accountNonLocked,
                    mapRolesToAuthorities(user.getRoles())
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        User user = userRepository.findByEmail(userEmail) ;
//        if (user == null) {
//            throw new UsernameNotFoundException("Invalid userEmail sor password.");
//        }
//        // user isn't null
//        // check if user is enabled or not(有沒有驗證過)
//        if( !user.isEnabled() ) { // not enabled
//            throw new UsernameNotFoundException("Not enabled yet. Please check your mail and click the link we sent for you when you registered.");
//        }
//
//        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
//                mapRolesToAuthorities(user.getRoles()));
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
        return userRepository.findById( id ) ;
    }

    @Override
    public String retNameById(int id) {
        return userRepository.findById(id).getUserName() ;
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
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));

        return userRepository.save(user);

    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) { // 編輯修改基本信息時 呼叫

        System.out.println("\n >>>>>>>>> user.getPassword() : " + user.getPassword()) ;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        user.setImgUrl( user.getImgUrl() );

        return userRepository.save(user);
    }

    @Override
    public User updateUserImage(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public long findUserCount() {
        return userRepository.count() ;
    }

    @Override
    public List<User> findUserByPage(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public boolean isUserEmailExist(String email) {
        User checkUser = userRepository.findByEmail(email) ;
        if(checkUser==null) return false ;

        return true;
    }

    @Override
    public boolean isUserEmailExistExceptSelf(String sqlEmail, String localEmail) {

        List<User> users = userRepository.findAll();
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
