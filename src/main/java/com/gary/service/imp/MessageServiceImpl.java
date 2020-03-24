package com.gary.service.imp;

import com.gary.Bean.MessageJsonBean;
import com.gary.dao.MessageDao;
import com.gary.entity.Message;
import com.gary.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Transactional
@Service("MessageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao ;

    @Override
    public List<MessageJsonBean> findAllMessage() {
        return messageDao.findAllMessage() ;
    }

    @Override
    public List<Message> findMessagesByUserId(int id) {

        return messageDao.findMessagesByUserId( id ) ;
    }

    @Override
    public Message findMessageById(int id) {

        return  messageDao.findMessageById( id ) ;
    }

    @Override
    public void saveMessage(Message message) {
        messageDao.saveMessage( message );
    }

    @Override
    public void deleteMessage(Message message) {
        messageDao.deleteMessage( message );
    }

    @Override
    public void deleteMessageById(int msgId) {
        messageDao.deleteMessageById( msgId );
    }

    @Override
    public Long findMessageCount() {
        return messageDao.findMessageCount() ;
    }

    @Override
    public String getDate() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
        Date now = new Date() ;

        return  dateFormat.format(now) ;
    }
}
