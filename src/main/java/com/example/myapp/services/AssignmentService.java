package com.example.myapp.services;


import java.util.ArrayList;
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

import com.example.myapp.models.Assignment;
import com.example.myapp.models.Course;
import com.example.myapp.models.Lesson;
import com.example.myapp.models.Widget;
import com.example.myapp.repositories.AssignmentRepository;
import com.example.myapp.repositories.LessonRepository;


@RestController
@CrossOrigin(origins = "*")
public class AssignmentService {
	@Autowired
	AssignmentRepository assignmentRepository;
	@Autowired
	LessonRepository lessonRepository;

	@GetMapping("/api/assignment")
	public List<Assignment> findAllAssignments() {
		return (List<Assignment>) assignmentRepository.findAll();
	}
	
	
	@GetMapping("/api/assignment/{assignmentId}")
	public Assignment findAssignmentById(@PathVariable("assignmentId") int assignmentId) {
		Optional<Assignment> assignment = assignmentRepository.findById(assignmentId);
		if (assignment.isPresent()) {
			return assignment.get();
		} else {
			return null;
		}
	}
	
	@GetMapping("/api/lesson/{lid}/assignment")
	public List<Assignment> findAllAssignmentsForLesson(@PathVariable("lid") int lessonId) {
		Optional<Lesson> lesson = lessonRepository.findById(lessonId);
		if (lesson.isPresent()) {
			Lesson l = lesson.get();
			List<Widget> widgets = l.getWidgets();
			List<Assignment> assignments = new ArrayList<Assignment>();
			for (Widget w : widgets) {
				if (w instanceof Assignment) {
					assignments.add((Assignment) w);
				}
			}
			return assignments;
		} else {
			return null;
		}
	}
	

	@PostMapping("/api/lesson/{lid}/assignment")
	public Assignment createAssignment(@RequestBody Assignment assignment, 
			@PathVariable("lid") int lessonId) {
		Optional<Lesson> lesson = lessonRepository.findById(lessonId);
		if (lesson.isPresent()) {
			Lesson l = lesson.get();
			assignment.setLesson(l);
			return assignmentRepository.save(assignment);
		} else {
			return null;
		}
	}
	
	@DeleteMapping("/api/assignment/{assignmentId}")
	public void deleteAssignment(@PathVariable("assignmentId") int assignmentId) {
		assignmentRepository.deleteById(assignmentId);
	}
	
	@PutMapping("api/assignment/{assignmentId}")
	public Assignment updateAssignment(@PathVariable("assignmentId") int assignmentId, 
			@RequestBody Assignment newAssignment) {
		Optional<Assignment> data = assignmentRepository.findById(assignmentId);
		if (data.isPresent()) {
			Assignment ass = data.get();
			ass.setName(newAssignment.getName());
			ass.setPoints(newAssignment.getPoints());
			ass.setDescription(newAssignment.getDescription());
			assignmentRepository.save(ass);
			return ass;
		} else {
			return null;
		}
	}
}
