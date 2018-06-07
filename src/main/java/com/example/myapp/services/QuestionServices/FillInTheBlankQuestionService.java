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
import com.example.myapp.models.QuestionTypes.EssayQuestion;
import com.example.myapp.models.QuestionTypes.FillInTheBlankQuestion;
import com.example.myapp.repositories.ExamRepository;
import com.example.myapp.repositories.QuestionRepo.FillInTheBlankQuestionRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class FillInTheBlankQuestionService {
	@Autowired
	FillInTheBlankQuestionRepository fillRepository;
	@Autowired
	ExamRepository examRepository;
	
	@PostMapping("/api/exam/{examId}/fillblank")
	public FillInTheBlankQuestion createFillInTheBlankQuestion(@RequestBody FillInTheBlankQuestion fillQuestion, 
			@PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			fillQuestion.setExam(data.get());
			return fillRepository.save(fillQuestion);
		} else {
			return null;
		}
	}
	
	@GetMapping("/api/fillblank/{questionId}")
	public FillInTheBlankQuestion findFillQuestionById(@PathVariable("questionId") int questionId) {
		Optional<FillInTheBlankQuestion> optional = fillRepository.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	@DeleteMapping("/api/fillblank/{questionId}")
	public void deleteFillBlankQuestion(@PathVariable("questionId") int questionId) {
		fillRepository.deleteById(questionId);
	}
	
	@PutMapping("api/fillblank/{questionId}")
	public FillInTheBlankQuestion updateFillInTheBlank(@PathVariable("questionId") int questionId, 
			@RequestBody FillInTheBlankQuestion newFill) {
		Optional<FillInTheBlankQuestion> data = fillRepository.findById(questionId);
		if (data.isPresent()) {
			FillInTheBlankQuestion fill = data.get();
			fill.setDescription(newFill.getDescription());
			fill.setPoints(newFill.getPoints());
			fill.setTitle(newFill.getTitle());
			fill.setVariables(newFill.getVariables());
			return fillRepository.save(fill);
		} else {
			return null;
		}
	}
}
