package com.example.myapp.services.QuestionServices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.models.Exam;
import com.example.myapp.models.Module;
import com.example.myapp.models.QuestionTypes.EssayQuestion;
import com.example.myapp.repositories.ExamRepository;
import com.example.myapp.repositories.QuestionRepo.EssayQuestionRepository;
import com.example.myapp.repositories.QuestionRepo.QuestionRepository;



@RestController
public class EssayQuestionService {
	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	EssayQuestionRepository essayRepository;
	@Autowired
	ExamRepository examRepository;
	
	@PostMapping("/api/exam/{examId}/essay")
	public EssayQuestion createEssayQuestion(@RequestBody EssayQuestion essay, @PathVariable("examId") int examId) {
		Optional<Exam> data = examRepository.findById(examId);
		if (data.isPresent()) {
			essay.setExam(data.get());
			return essayRepository.save(essay);
		} else {
			return null;
		}
	}
	
	@GetMapping("/api/essay/{questionId}")
	public EssayQuestion findFillQuestionById(@PathVariable("questionId") int questionId) {
		Optional<EssayQuestion> optional = essayRepository.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	@DeleteMapping("/api/essay/{essayId}")
	public void deleteEssayQuestion(@PathVariable("essayId") int essayId) {
		essayRepository.deleteById(essayId);
	}
	
	@PutMapping("/api/essay/{essayId}")
	public EssayQuestion updateEssay(@PathVariable("essayId") int essayId, 
			@RequestBody EssayQuestion newEssay) {
		Optional<EssayQuestion> data = essayRepository.findById(essayId);
		if (data.isPresent()) {
			EssayQuestion essay = data.get();
			essay.setDescription(newEssay.getDescription());
			essay.setPoints(newEssay.getPoints());
			essay.setTitle(newEssay.getTitle());
			return essayRepository.save(essay);
		} else {
			return null;
		}
	}
}
