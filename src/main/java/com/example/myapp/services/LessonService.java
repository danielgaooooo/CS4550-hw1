package com.example.myapp.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

import com.example.myapp.models.Course;
import com.example.myapp.models.Lesson;
import com.example.myapp.models.Module;
import com.example.myapp.repositories.CourseRepository;
import com.example.myapp.repositories.LessonRepository;
import com.example.myapp.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonService {
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	LessonRepository lessonRepository;	
	
	@PostMapping("/api/course/{cid}/module/{mid}/lesson")
	public Lesson createLesson(@RequestBody Lesson lesson,
			@PathVariable("cid") int courseId,
			@PathVariable("mid") int moduleId) {
		Optional<Module> data = moduleRepository.findById(moduleId);
		if (data.isPresent()) {
			Module curr = data.get();
			lesson.setModule(curr);
			Course course = courseRepository.findById(courseId).get();
			course.setModified(new Date());
			return lessonRepository.save(lesson);
		}
		return null;
	}
	
	@DeleteMapping("/api/lesson/{lessonId}")
	public void deleteLesson(@PathVariable("lessonId") int id) {
		Optional<Lesson> lesson = lessonRepository.findById(id);
		if (lesson.isPresent()) {
			Lesson l = lesson.get();
			Module curr = l.getModule();
			Course course = curr.getCourse();
			course.setModified(new Date());
		}
		lessonRepository.deleteById(id);
	}
	
	@GetMapping("/api/lesson")
	public Iterable<Lesson> findAllLessons() {
		return lessonRepository.findAll(); 
	}
	
	@GetMapping("api/lesson/{lessonId}")
	public Lesson findLessonById(@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> lesson = lessonRepository.findById(lessonId);
		if (lesson.isPresent()) {
			return lesson.get();
		} else {
			return null;
		}
	}
	
	@GetMapping("/api/course/{cid}/module/{mid}/lesson")
	public List<Lesson> findAllLessonsForModule(@PathVariable("mid") int modId) {
		Optional<Module> mod = moduleRepository.findById(modId);
		if (mod.isPresent()) {
			Module m = mod.get();
			return m.getLessons();
		} else {
			List<Lesson> lessons = new ArrayList<Lesson>();
			lessons.add(new Lesson());
			return lessons;
		}
	}
	
	@PutMapping("api/lesson/{lessonId}")
	public Lesson updateLesson(@PathVariable("lessonId") int lessonId, 
			@RequestBody Lesson newLesson) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if (data.isPresent()) {
			Lesson lesson = data.get();
			if (newLesson.getTitle() != null) {
				lesson.setTitle(newLesson.getTitle());
			}
			if (newLesson.getModule() != null) {
				lesson.setModule(newLesson.getModule());
			}
			lessonRepository.save(lesson);
			return lesson;
		} else {
			return null;
		}
	}
}
