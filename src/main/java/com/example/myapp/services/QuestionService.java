package com.example.myapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.models.Exam;
import com.example.myapp.models.QuestionTypes.FillInTheBlankQuestion;
import com.example.myapp.models.QuestionTypes.MultipleChoiceQuestion;
import com.example.myapp.models.QuestionTypes.Question;
import com.example.myapp.models.QuestionTypes.TrueFalseQuestion;
import com.example.myapp.repositories.ExamRepository;
import com.example.myapp.repositories.QuestionRepo.FillInTheBlankQuestionRepository;
import com.example.myapp.repositories.QuestionRepo.MultipleChoiceQuestionRepository;
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
	@Autowired
	ExamRepository examRepository;
	@Autowired
	MultipleChoiceQuestionRepository multiRepo;
	
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
	

	@GetMapping("/api/multi/{questionId}")
	public MultipleChoiceQuestion findMultiQuestionById(@PathVariable("questionId") int questionId) {
		Optional<MultipleChoiceQuestion> optional = multiRepo.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@GetMapping("/api/truefalse/{questionId}")
	public TrueFalseQuestion findTrueFalseQuestionById(@PathVariable("questionId") int questionId) {
		Optional<TrueFalseQuestion> optional = trueRepo.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	@GetMapping("/api/exam/{examId}/question")
	public List<Question> findAllQuestionsForExam(@PathVariable("examId") int examId) {
		Optional<Exam> optionalExam = examRepository.findById(examId);
		if(optionalExam.isPresent()) {
			Exam exam = optionalExam.get();
			List<Question> questions = exam.getQuestions();
			return questions;
		}
		return null;
	}
}
