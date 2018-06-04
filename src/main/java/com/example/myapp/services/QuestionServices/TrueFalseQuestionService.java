package com.example.myapp.services.QuestionServices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.models.Exam;
import com.example.myapp.models.QuestionTypes.TrueFalseQuestion;
import com.example.myapp.repositories.ExamRepository;
import com.example.myapp.repositories.QuestionRepo.TrueFalseQuestionRepository;

@RestController
public class TrueFalseQuestionService {
	@Autowired
	TrueFalseQuestionRepository tfRepo;
	@Autowired
	ExamRepository examRepository;
	
	@PostMapping("/api/exam/{examId}/essay")
	public TrueFalseQuestion createTrueFalseQuestion(@RequestBody TrueFalseQuestion tf,
			@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			tf.setExam(data.get());
			return tfRepo.save(tf);
		} else {
			return null;
		}
	}
	
	@GetMapping("/api/truefalse/{questionId}")
	public TrueFalseQuestion findTrueFalseQuestionById(@PathVariable("questionId") int questionId) {
		Optional<TrueFalseQuestion> optional = tfRepo.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
}
