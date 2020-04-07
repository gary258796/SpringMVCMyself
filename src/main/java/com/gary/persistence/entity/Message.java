package com.gary.persistence.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "message")
public class Message {


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id ;

    @Column(name = "userid")
    private int userid ;

    @NotNull(message = "Can't leave empty message !")
    @Column(name = "message")
    private String message ;

    @Column(name = "fromuserid")
    private int fromUserId ;

    private String date;

    private String ip;

    @Transient
    private String fromUserName ;

    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", userid=" + userid +
                ", message='" + message + '\'' +
                ", date='" + date + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}


