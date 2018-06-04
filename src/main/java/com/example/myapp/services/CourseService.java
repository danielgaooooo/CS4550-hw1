package com.example.myapp.services;

import java.util.Date;
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
import com.example.myapp.repositories.CourseRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseService {
	@Autowired
	CourseRepository courseRepository;	
	
	@GetMapping("/api/course")
	public Iterable<Course> findAllCourses() {
		Iterable<Course> allCourses = courseRepository.findAll(); 
		return  allCourses;
	}
	
	@GetMapping("/api/course/{courseId}")
	public Course findCourseById(@PathVariable("courseId") int id) {
		Optional<Course> c = courseRepository.findById(id);
		if (c.isPresent()) {
			return c.get();
		} else {
			return null;
		}
	}
	
	@PostMapping("/api/course")
	public Course createCourse(@RequestBody Course course) {
		course.setCreated(new Date());
		course.setModified(new Date());
		return courseRepository.save(course);
	}

	@DeleteMapping("/api/course/{courseId}")
	public void deleteCourse(@PathVariable("courseId") int id) {
		courseRepository.deleteById(id);
	}
	
	@PutMapping("api/course/{courseId}")
	public Course updateCourse(@PathVariable("courseId") int courseId, @RequestBody Course newCourse) {
		Optional<Course> data = courseRepository.findById(courseId);
		if (data.isPresent()) {
			Course course = data.get();
			if (newCourse.getCreated() != null) {
				course.setCreated(newCourse.getCreated());
			}
			if (newCourse.getModified() != null) {
				course.setModified(newCourse.getModified());
			}
			if (newCourse.getModules() != null) {
				course.setModules(newCourse.getModules());
			}
			if (newCourse.getOwner() != null) {
				course.setOwner(newCourse.getOwner());
			}
			if (newCourse.getTitle() != null) {
				course.setTitle(newCourse.getTitle());
			}
			courseRepository.save(course);
			return course;
		} else {
			return null;
		}
	}
}
