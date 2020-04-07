package com.gary.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.gary.validation.FieldMatch;
import com.gary.validation.ValidEmail;
import com.gary.validation.ValidPassword;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
public class UserDto {

    @NotNull
    @Size(min = 3, message = "userName should be at least 3 characters!")
    private String userName;

    // @ValidPassword
    // close for easy test
    private String password;

    @NotNull
    @Size(min = 1)
    private String matchingPassword;

    @ValidEmail
    @NotNull
    @Size(min = 1, message = "Email can't be that short!")
    private String email;

    private boolean isUsing2FA;

    private Integer role;

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(final Integer role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(final String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public boolean isUsing2FA() {
        return isUsing2FA;
    }

    public void setUsing2FA(boolean isUsing2FA) {
        this.isUsing2FA = isUsing2FA;
    }

}
