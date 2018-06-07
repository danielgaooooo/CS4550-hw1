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
import com.example.myapp.models.QuestionTypes.MultipleChoiceQuestion;
import com.example.myapp.repositories.ExamRepository;
import com.example.myapp.repositories.QuestionRepo.MultipleChoiceQuestionRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MultipleChoiceQuestionService {
	@Autowired
	MultipleChoiceQuestionRepository multiRepo;
	@Autowired
	ExamRepository examRepository;
	
	@PostMapping("/api/exam/{examId}/multi")
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
	
	@DeleteMapping("/api/multi/{multiId}")
	public void deleteMultiQuestion(@PathVariable("multiId") int multiId) {
		multiRepo.deleteById(multiId);
	}
	
	@PutMapping("api/multi/{multiId}")
	public MultipleChoiceQuestion updateMultipleChoiceQuestion(@PathVariable("multiId") int multiId, 
			@RequestBody MultipleChoiceQuestion newMulti) {
		Optional<MultipleChoiceQuestion> data = multiRepo.findById(multiId);
		if (data.isPresent()) {
			MultipleChoiceQuestion multi = data.get();
			multi.setDescription(newMulti.getDescription());
			multi.setPoints(newMulti.getPoints());
			multi.setTitle(newMulti.getTitle());
			multi.setOptions(newMulti.getOptions());
			multi.setCorrectOption(newMulti.getCorrectOption());
			return multiRepo.save(multi);
		} else {
			return null;
		}
	}
}
