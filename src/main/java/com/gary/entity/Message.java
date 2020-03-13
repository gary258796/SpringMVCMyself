package com.gary.entity;

import javax.persistence.*;

@Entity
@Table(name = "message")
public class Message {


    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id ;

    @Column(name = "userid")
    private int userid ;

    @Column(name = "message")
    private String message ;

    @Column(name = "fromusername")
    private String fromusername ;

    private String date;

    private String ip;


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

    public String getFromusername() {
        return fromusername;
    }

    public void setFromusername(String fromusername) {
        this.fromusername = fromusername;
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


