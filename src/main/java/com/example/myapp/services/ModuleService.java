package com.example.myapp.services;

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
import com.example.myapp.models.Module;
import com.example.myapp.repositories.CourseRepository;
import com.example.myapp.repositories.ModuleRepository;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuleService {
	@Autowired
	CourseRepository courseRepository;

	@Autowired
	ModuleRepository moduleRepository;

	@PostMapping("/api/course/{courseId}/module")
	public Module createModule(
			@PathVariable("courseId") int courseId,
			@RequestBody Module newModule) {
		Optional<Course> data = courseRepository.findById(courseId);
		if(data.isPresent()) {
			Course course = data.get();
			newModule.setCourse(course);
			course.setModified(new Date());
			return moduleRepository.save(newModule);
		}
		return null;		
	}

	@GetMapping("/api/course/{courseId}/module")
	public List<Module> findAllModulesForCourse(@PathVariable("courseId") int courseId) {
		Optional<Course> data = courseRepository.findById(courseId);
		if(data.isPresent()) {
			Course course = data.get();
			return course.getModules();
		}
		return null;		
	}

	@DeleteMapping("/api/module/{moduleId}")
	public void deleteModule(@PathVariable("moduleId") int moduleId) {
		moduleRepository.deleteById(moduleId);
	}

	@GetMapping("/api/module")
	public List<Module> findAllModules() {
		return (List<Module>) moduleRepository.findAll();
	}

	@GetMapping("/api/module/{moduleId}")
	public Module findModuleById(@PathVariable("moduleId") int id) {
		Optional<Module> m = moduleRepository.findById(id);
		if (m.isPresent()) {
			return m.get();
		} else {
			return null;
		}
	}

	@PutMapping("api/module/{moduleId}")
	public Module updateModule(@PathVariable("moduleId") int moduleId, @RequestBody Module newMod) {
		Optional<Module> data = moduleRepository.findById(moduleId);
		if (data.isPresent()) {
			Module module = data.get();
			if (newMod.getCourse() != null) {
				module.setCourse(newMod.getCourse());
			}
			if (newMod.getLessons() != null) {
				module.setLessons(newMod.getLessons());
			}
			if (newMod.getTitle() != null) {
				module.setTitle(newMod.getTitle());
			}
			moduleRepository.save(module);
			return module;
		} else {
			return null;
		}
	}
}