package com.example.myapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// knows to map it to a user?
@Entity
public class User {
	// declares id as primary key
	@Id
	// what does this do?
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String username;
	private String firstName;
	private String lastName;
	private String password;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastname(String lastname) {
		this.lastName = lastname;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
