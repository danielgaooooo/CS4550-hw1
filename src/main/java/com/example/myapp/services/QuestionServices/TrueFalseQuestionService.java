package com.example.myapp.services.QuestionServices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.models.Exam;
import com.example.myapp.models.QuestionTypes.TrueFalseQuestion;
import com.example.myapp.repositories.ExamRepository;
import com.example.myapp.repositories.QuestionRepo.TrueFalseQuestionRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TrueFalseQuestionService {
	@Autowired
	TrueFalseQuestionRepository tfRepo;
	@Autowired
	ExamRepository examRepository;
	
	@PostMapping("/api/exam/{examId}/truefalse")
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
	
	@DeleteMapping("/api/truefalse/{truefalseId}")
	public void deleteTrueFalseQuestion(@PathVariable("truefalseId") int truefalse) {
		tfRepo.deleteById(truefalse);
	}
	
	@PutMapping("api/truefalse/{questionId}")
	public TrueFalseQuestion updateTrueFalseQuestion(@PathVariable("questionId") int questionId, 
			@RequestBody TrueFalseQuestion newTf) {
		Optional<TrueFalseQuestion> data = tfRepo.findById(questionId);
		if (data.isPresent()) {
			TrueFalseQuestion tf = data.get();
			tf.setDescription(newTf.getDescription());
			tf.setPoints(newTf.getPoints());
			tf.setTitle(newTf.getTitle());
			tf.setTrue(newTf.isTrue());
			return tfRepo.save(newTf);
		} else {
			return null;
		}
	}
}
