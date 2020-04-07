package com.gary.persistence.dao;

import com.gary.persistence.entity.VerificationToken;

public interface TokenDao {

    void save(VerificationToken token);

}
