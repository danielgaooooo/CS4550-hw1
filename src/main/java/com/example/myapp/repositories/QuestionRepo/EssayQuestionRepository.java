package com.example.myapp.repositories.QuestionRepo;

import org.springframework.data.repository.CrudRepository;

import com.example.myapp.models.QuestionTypes.EssayQuestion;

public interface EssayQuestionRepository extends CrudRepository<EssayQuestion, Integer> {

}
