package com.gary.dao.imp;

import com.gary.Bean.MessageJsonBean;
import com.gary.dao.MessageDao;
import com.gary.entity.Message;
import com.gary.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
//extends BaseDaoImp<Message>

@Repository("MessageDao")
public class MessageDaoImpl extends BaseDaoImp<Message> implements MessageDao {

    @Autowired
    private SessionFactory sessionFactory ;

    @Override
    public List<MessageJsonBean> findAllMessage() {

        // logger.info("\n >>> Get All Message..... \n");

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();


        String hql = "select new com.gary.Bean.MessageJsonBean(" +
                "u.userName, m.ip, m.date, m.message, u.imgUrl) " +
                "from User u, Message m where m.userid = u.id Order by m.id desc" ;
        // now retrieve/read from database using username
        Query<MessageJsonBean> theQuery = currentSession.createQuery(hql);

        List<MessageJsonBean> allJsonMessage = theQuery.getResultList();

        System.out.println("\n >>>> count of findAllMessage() : " + theQuery.getFetchSize()) ;
        return allJsonMessage;
    }


    @Override
    public List<Message> findMessagesByUserId(int id) {
        String hql = "from Message m where m.userid = "+ id +
                "Order by m.id desc";
        //return find(hql, id);
        return find(hql);
    }

    @Override
    public Message findMessageById(int id) {
        return get( Message.class , id ) ;
    }

    @Override
    public void saveMessage(Message message) {
        save( message );
    }

    @Override
    public void deleteMessage(Message message) {
        delete(message);
    }

    @Override
    public Long findMessageCount() {

        String hql = "select count(*) from Message as message";

        Session currentSession = sessionFactory.getCurrentSession();
        Query<Long> theQuery = currentSession.createQuery(hql);

        Long a = theQuery.getSingleResult() ;

        System.out.println("\n >>>> count of findMessageCount() : " + a) ;

        return a;
    }
}
