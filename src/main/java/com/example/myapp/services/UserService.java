package com.example.myapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
}
