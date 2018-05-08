package com.example.myapp;

import com.example.myapp.Hello;
import org.springframework.data.repository.CrudRepository;

public interface HelloRepository extends CrudRepository<Hello, Integer> {}