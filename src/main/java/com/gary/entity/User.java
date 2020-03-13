package com.gary.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@Entity
@Table(name = "user")
@XmlRootElement(name = "User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected int id;

	@NotNull(message = "is required!")
	@Size(min = 5, message = "userName should be at least 5 characters")
	@Column(name = "name")
	private String userName;

	@NotNull(message = "is required!")
	@Column(name = "email", nullable = false)
	private String email;


	@NotNull(message = "is required!")
	@Size(min = 1, message = "is required")
	@Column(name = "passwd", nullable = false)
	private String password;


	@Column(name = "imgurl", nullable = true)
	private String imgUrl ;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles;


	public User() {}

	public User( String userName, String email, String password) {
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", userName='" + userName + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", imgUrl='" + imgUrl + '\'' +
				'}';
	}
}
