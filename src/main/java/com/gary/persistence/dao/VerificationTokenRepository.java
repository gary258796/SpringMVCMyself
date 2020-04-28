package com.gary.persistence.dao;

import com.gary.persistence.entity.User;
import com.gary.persistence.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.stream.Stream;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);

    Stream<VerificationToken> findAllByExpiryDateLessThan(Date now);

    void deleteByExpiryDateLessThan(Date now);

//    @Modifying
//    @Query("delete from verificationToken t where t.expiryDate <= ?1")
//    void deleteAllExpiredSince(Date now);

}
