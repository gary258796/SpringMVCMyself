package com.gary.event;

import com.gary.persistence.entity.User;
import org.springframework.context.ApplicationEvent;


@SuppressWarnings("serial")
public class OnRegistrationSuccessEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L ;
    private String appUrl ;
    private User user ;

    public OnRegistrationSuccessEvent(User user, String appUrl) {
        super(user);
        this.user = user ;
        this.appUrl = appUrl ;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
