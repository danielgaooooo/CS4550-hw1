package com.example.myapp.repositories.QuestionRepo;

import org.springframework.data.repository.CrudRepository;

import com.example.myapp.models.QuestionTypes.MultipleChoiceQuestion;


public interface MultipleChoiceQuestionRepository extends CrudRepository<MultipleChoiceQuestion, Integer> {

}
