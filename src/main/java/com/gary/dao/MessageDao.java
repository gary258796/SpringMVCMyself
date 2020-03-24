package com.gary.dao;

import com.gary.Bean.MessageJsonBean;
import com.gary.entity.Message;

import java.util.List;

public interface MessageDao {

    List<Message> findMessagesByUserId(int id);

    Message findMessageById(int id);

    List<MessageJsonBean> findAllMessage();

    void saveMessage(Message message);

    void deleteMessage(Message message);

    void deleteMessageById(int msgId) ;

    Long findMessageCount();

    // List<MessageJsonBean> findMessageByPage(final int pageNo,final int pageSize );
}
