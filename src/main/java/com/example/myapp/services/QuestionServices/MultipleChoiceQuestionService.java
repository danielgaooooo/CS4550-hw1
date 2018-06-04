package com.example.myapp.services.QuestionServices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.models.Exam;
import com.example.myapp.models.QuestionTypes.MultipleChoiceQuestion;
import com.example.myapp.repositories.ExamRepository;
import com.example.myapp.repositories.QuestionRepo.MultipleChoiceQuestionRepository;

@RestController
public class MultipleChoiceQuestionService {
	@Autowired
	MultipleChoiceQuestionRepository multiRepo;
	@Autowired
	ExamRepository examRepository;
	
	@PostMapping("/api/exam/{examId}/essay")
	public MultipleChoiceQuestion createMultipleChoiceQuestion(@RequestBody MultipleChoiceQuestion multi, 
			@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			multi.setExam(data.get());
			return multiRepo.save(multi);
		} else {
			return null;
		}
	}
	
	@GetMapping("/api/multi/{questionId}")
	public MultipleChoiceQuestion findMultiQuestionById(@PathVariable("questionId") int questionId) {
		Optional<MultipleChoiceQuestion> optional = multiRepo.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
}
