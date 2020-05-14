package com.gary.service;

import com.gary.persistence.entity.User;
import com.gary.persistence.entity.VerificationToken;

public interface VerificationTokenService {

    void createVerificationToken(User user, String token) ;

    VerificationToken getVerificationToken(String token) ;
}
