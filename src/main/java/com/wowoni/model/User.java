package com.wowoni.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotEmpty
	@Column(nullable = false)
	private String firstName;
	private String lastName;

	@NotEmpty
	@Column(nullable = false, unique = true)
	@Email(message = "{Invalid Email Errorr}")
	private String email;

	@NotEmpty
	private String password;

	@ManyToMany(cascade=CascadeType.MERGE,fetch=FetchType.EAGER)@JoinTable(name="user_role",joinColumns={@JoinColumn(name="USER_ID",referencedColumnName="ID")},inverseJoinColumns=
	{@JoinColumn(name="ROLE_ID", referencedColumnName = "ID")})
	private List<Role> roles;

	public User(int id, @NotEmpty String firstName, String lastName,
			@NotEmpty @Email(message = "{Invalid Email Errorr}") String email, @NotEmpty String password,
			List<Role> roles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public User(User user) {
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.roles = user.getRoles();
	}

}
