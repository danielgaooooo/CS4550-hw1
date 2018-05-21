package com.example.myapp.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.myapp.models.Lesson;
import com.example.myapp.models.Module;
import com.example.myapp.models.User;

public interface LessonRepository extends CrudRepository<Lesson, Integer>{

}
