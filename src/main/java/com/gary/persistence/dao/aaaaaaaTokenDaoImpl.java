//package com.gary.persistence.dao;
//
//import com.gary.persistence.entity.VerificationToken;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//
//@Repository("TokenDao")
//public class TokenDaoImpl implements TokenDao {
//
//
//    @Autowired
//    private SessionFactory sessionFactory ;
//
//    @Override
//    public void save(VerificationToken token) {
//        Session currentSession = sessionFactory.getCurrentSession() ;
//
//        currentSession.save(token);
//    }
//}
