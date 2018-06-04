package com.example.myapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.myapp.models.QuestionTypes.MultipleChoiceQuestion;



public interface MultipleChoicesQuestionRepository extends CrudRepository<MultipleChoiceQuestion, Integer> {
	
}