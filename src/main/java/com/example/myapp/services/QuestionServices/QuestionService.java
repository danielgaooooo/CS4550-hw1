package com.example.myapp.services.QuestionServices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.models.Exam;
import com.example.myapp.models.QuestionTypes.Question;
import com.example.myapp.repositories.ExamRepository;
import com.example.myapp.repositories.QuestionRepo.FillInTheBlankQuestionRepository;
import com.example.myapp.repositories.QuestionRepo.MultipleChoiceQuestionRepository;
import com.example.myapp.repositories.QuestionRepo.QuestionRepository;
import com.example.myapp.repositories.QuestionRepo.TrueFalseQuestionRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
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
