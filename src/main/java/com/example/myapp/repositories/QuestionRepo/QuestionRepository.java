package com.example.myapp.repositories.QuestionRepo;

import org.springframework.data.repository.CrudRepository;

import com.example.myapp.models.QuestionTypes.Question;

public interface QuestionRepository extends CrudRepository<Question, Integer> {

}
