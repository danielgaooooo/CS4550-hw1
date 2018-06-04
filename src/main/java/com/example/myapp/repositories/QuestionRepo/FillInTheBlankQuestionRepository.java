package com.example.myapp.repositories.QuestionRepo;

import org.springframework.data.repository.CrudRepository;
import com.example.myapp.models.QuestionTypes.FillInTheBlankQuestion;

public interface FillInTheBlankQuestionRepository extends CrudRepository<FillInTheBlankQuestion, Integer> {

}
