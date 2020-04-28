package com.gary.service;

import com.gary.persistence.entity.User;

public interface VerificationTokenService {

    void createVerificationToken(User user, String token) ;

    com.gary.persistence.entity.VerificationToken getVerificationToken(String token) ;
}
