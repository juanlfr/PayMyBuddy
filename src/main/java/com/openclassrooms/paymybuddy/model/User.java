package com.openclassrooms.paymybuddy.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
	@Column(unique = true)
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	@OneToMany(mappedBy = "user")
	private Set<Account> accounts;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private Set<User> connections;

	public User() {
	}

	public User(String email, String password, String firstName, String lastName, Set<Account> accounts,
			Set<User> connections) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accounts = accounts;
		this.connections = connections;
	}

	public Long getUserId() {
		return userId;
	}

	@Column(unique = true)
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

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccount(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public Set<User> getConnections() {
		return connections;
	}

	public void setConnections(Set<User> connections) {
		this.connections = connections;
	}

}
