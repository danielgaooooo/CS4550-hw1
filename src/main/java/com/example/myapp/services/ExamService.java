package com.example.myapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.models.Exam;
import com.example.myapp.models.Lesson;
import com.example.myapp.repositories.ExamRepository;
import com.example.myapp.repositories.LessonRepository;
import com.example.myapp.repositories.QuestionRepo.MultipleChoiceQuestionRepository;
import com.example.myapp.repositories.QuestionRepo.TrueFalseQuestionRepository;



@RestController
@CrossOrigin(origins = "*")
public class ExamService {
	@Autowired
	ExamRepository examRepository;
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	TrueFalseQuestionRepository trueFalseRepository;
	@Autowired
	MultipleChoiceQuestionRepository multiRepo;
	
	@GetMapping("/api/exam")
	public List<Exam> findAllExams() {
		return (List<Exam>) examRepository.findAll();
	}
	
	@GetMapping("/api/exam/{eid}")
	public Exam findExamById(@PathVariable("eid") int examId) {
		Optional<Exam> exam = examRepository.findById(examId);
		if (exam.isPresent()) {
			return exam.get();
		} else {
			return null;
		}
	}
	
	@GetMapping("/api/lesson/{lid}/exam")
	public List<Exam> findAllExamsForLesson(@PathVariable("lid") int lessonId) {
		Optional<Lesson> lesson = lessonRepository.findById(lessonId);
		if (lesson.isPresent()) {
			Lesson l = lesson.get();
			List<Exam> exams = l.getExams();
			return exams;
		} else {
			return null;
		}
	}
	
	@PostMapping("/api/lesson/{lid}/exam")
	public Exam createExam(@RequestBody Exam exam, 
			@PathVariable("lid") int lessonId) {
		Optional<Lesson> lesson = lessonRepository.findById(lessonId);
		if (lesson.isPresent()) {
			Lesson l = lesson.get();
			exam.setLesson(l);
			return examRepository.save(exam);
		} else {
			return null;
		}
	}
	
	@DeleteMapping("/api/exam/{examId}")
	public void deleteExam(@PathVariable("examId") int examId) {
		examRepository.deleteById(examId);
	}
}
