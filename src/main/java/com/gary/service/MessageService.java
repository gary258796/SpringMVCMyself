package com.gary.service;

import com.gary.Bean.MessageJsonBean;
import com.gary.entity.Message;

import java.util.List;

public interface MessageService {

    List<Message> findMessagesByUserId(int id);

    Message findMessageById(int id);

    List<MessageJsonBean> findAllMessage();

    void saveMessage(Message message);

    void deleteMessage(Message message);

    void deleteMessageById( int msgId) ;

    Long findMessageCount();

    String getDate();

    // List<MessageJsonBean> findMessageByPage(int pageNo,int pageSize );


}
