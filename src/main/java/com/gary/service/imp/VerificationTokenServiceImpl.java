package com.gary.service.imp;

import com.gary.persistence.dao.VerificationTokenRepository;
import com.gary.persistence.entity.User;
import com.gary.persistence.entity.VerificationToken;
import com.gary.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional // 使用jpa transactionManager
@Service("VerificationTokenService")
public class VerificationTokenServiceImpl implements VerificationTokenService {

    @Autowired
    private VerificationTokenRepository tokenRepository ;

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken newToken = new VerificationToken(token, user) ;
        System.out.println("before\n");
        tokenRepository.save(newToken) ;
        System.out.println("after\n");
    }

    @Override
    public VerificationToken getVerificationToken(String token) {
        return tokenRepository.findByToken(token) ;
    }
}
