package com.example.myapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.models.User;
import com.example.myapp.repositories.UserRepository;

@RestController
public class UserService {
	@Autowired
	UserRepository repo;
	
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		repo.deleteById(id);
	}
	
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return repo.save(user);
	}
	
	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>) repo.findAll();
	}
	
	@GetMapping("/api/user/{userId}")
	public User findAllUsers(@PathVariable("userId") int id) {
		Optional<User> data = repo.findById(id);
		if (data.isPresent()) {
			return data.get();
		} else {
			return null;
		}
	}
	
	@PutMapping("api/user/{userId}")
	public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser) {
		Optional<User> data = repo.findById(userId);
		if (data.isPresent()) {
			User user = data.get();
			user.setUsername(newUser.getUsername());
			user.setPassword(newUser.getPassword());
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setEmail(newUser.getEmail());
			user.setPhone(newUser.getPhone());
			user.setDateOfBirth(newUser.getDateOfBirth());
			user.setRole(newUser.getRole());
			repo.save(user);
			return user;
		} else {
			return null;
		}
	}
	
	@PostMapping("/api/login")
	public User findUserByUsername(@RequestBody User user) {
		return ((List<User>) repo
				.findUserByCredentials(user.getUsername(), user.getPassword())).get(0);
	}
	
	@PostMapping("/api/register")
	public User register(@RequestBody User user) {
		if (((List<User>)repo.findUserByUsername(user.getUsername())).size() == 0) {
			return this.createUser(user);
		} else {
			return null;
		}
	}
}
