package com.example.myapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.models.QuestionTypes.FillInTheBlankQuestion;
import com.example.myapp.models.QuestionTypes.Question;
import com.example.myapp.models.QuestionTypes.TrueFalseQuestion;
import com.example.myapp.repositories.QuestionRepo.FillInTheBlankQuestionRepository;
import com.example.myapp.repositories.QuestionRepo.QuestionRepository;
import com.example.myapp.repositories.QuestionRepo.TrueFalseQuestionRepository;

@RestController
public class QuestionService {
	@Autowired
	QuestionRepository baseRepo;
	@Autowired
	FillInTheBlankQuestionRepository fillRepo;
	@Autowired
	TrueFalseQuestionRepository trueRepo;
	
	@GetMapping("/api/inheritance/joined/base")
	public Question createBaseQuestion() {
		Question q = new Question();
		q.setDescription("descriptions 123");
		q.setInstructions("instructions 123");
		q.setPoints(123);
		q.setTitle("title 123");
		return baseRepo.save(q);
	}
	
	@GetMapping("/api/inheritance/joined/fill")
	public FillInTheBlankQuestion createFillQuestion() {
		FillInTheBlankQuestion q = new FillInTheBlankQuestion();
		q.setDescription("descriptions 234");
		q.setInstructions("instructions 234");
		q.setPoints(234);
		q.setTitle("title 234");
		q.setVariables("variables 234");
		return fillRepo.save(q);
	}
	
	@GetMapping("/api/inheritance/joined/true")
	public TrueFalseQuestion createTrueQuestion() {
		TrueFalseQuestion q = new TrueFalseQuestion();
		q.setDescription("descriptions 345");
		q.setInstructions("instructions 345");
		q.setPoints(345);
		q.setTitle("title 345");
		q.setTrue(true);
		return trueRepo.save(q);
	}
}
