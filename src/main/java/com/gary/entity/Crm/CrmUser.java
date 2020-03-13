package com.gary.entity.Crm;

import com.gary.validation.FieldMatch ;
import com.gary.validation.FieldRepeat;
import com.gary.validation.ValidEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
public class CrmUser {

	@NotNull(message = "is required")
	@Size(min = 5, message = "userName should be at least 5 characters")
	private String userName;

	@NotNull(message = "is required")
	@Size(min = 4, max = 20, message = "is required")
	private String password;

	@NotNull(message = "is required")
	@Size(min = 4, max = 20, message = "is required")
	private String matchingPassword;

	@Email
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String email;

	public CrmUser() {

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

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
