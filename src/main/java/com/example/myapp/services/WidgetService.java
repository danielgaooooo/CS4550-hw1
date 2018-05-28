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
import com.example.myapp.models.Widget;
import com.example.myapp.repositories.LessonRepository;
import com.example.myapp.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins="*")
public class WidgetService {

	@Autowired
	WidgetRepository widgetRepository;
	@Autowired
	LessonRepository lessonRepository;
	
	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets() {
		return (List<Widget>) widgetRepository.findAll();
	}
	
	@GetMapping("/api/widget/{widgetId}")
	public Widget findWidgetById(@PathVariable("widgetId") int widgetId) {
		Optional<Widget> widget = widgetRepository.findById(widgetId);
		if (widget.isPresent()) {
			return widget.get();
		} else {
			return null;
		}
	}
	
	@GetMapping("/api/lesson/{lid}/widget")
	public List<Widget> findAllWidgetsForLesson(@PathVariable("lid") int lessonId) {
		Optional<Lesson> lesson = lessonRepository.findById(lessonId);
		if (lesson.isPresent()) {
			Lesson l = lesson.get();
			return l.getWidgets();
		} else {
			List<Widget> widgets = new ArrayList<Widget>();
			widgets.add(new Widget());
			return widgets;
		}
	}
	
	@PostMapping("/api/lesson/{lid}/widget")
	public Widget createWidget(@RequestBody Widget widget, 
			@PathVariable("lid") int lessonId) {
		Optional<Lesson> lesson = lessonRepository.findById(lessonId);
		if (lesson.isPresent()) {
			Lesson l = lesson.get();
			widget.setLesson(l);
			return widgetRepository.save(widget);
		} else {
			return null;
		}
	}
	
	@PutMapping("api/widget/{widgetId}")
	public Widget updateWidget(@PathVariable("widgetId") int widgetId, 
			@RequestBody Widget newWidget) {
		Optional<Widget> data = widgetRepository.findById(widgetId);
		if (data.isPresent()) {
			Widget widget = data.get();
			if (newWidget.getWidgetType() != null) {
				widget.setWidgetType(newWidget.getWidgetType());
			}
			if (newWidget.getName() != null) {
				widget.setName(newWidget.getName());
			}
			if (newWidget.getText() != null) {
				widget.setText(newWidget.getText());
			}
			if (newWidget.getPrecedence() != 0) {
				widget.setPrecedence(newWidget.getPrecedence());
			}
			if (newWidget.getLesson() != null) {
				widget.setLesson(newWidget.getLesson());
			}
			if (newWidget.getSrc() != null) {
				widget.setSrc(newWidget.getSrc());
			}
			if (newWidget.getHref() != null) {
				widget.setHref(newWidget.getHref());
			}
			if (newWidget.getSize() != 0) {
				widget.setSize(newWidget.getSize());
			}
			if (newWidget.getListItems() != null) {
				widget.setListItems(newWidget.getListItems());
			}
			if (newWidget.getWidth() != 0) {
				widget.setWidth(newWidget.getWidth());
			}
			if (newWidget.getHeight() != 0) {
				widget.setHeight(newWidget.getHeight());
			}
			widget.setOrdered(newWidget.isOrdered());
			widgetRepository.save(widget);
			return widget;
		} else {
			return null;
		}
	}
	
	@DeleteMapping("/api/widget/{widgetId}")
	public void deleteWidget(@PathVariable("widgetId") int widgetId) {
		widgetRepository.deleteById(widgetId);
	}
	
	@PostMapping("/api/widget/save")
	public void saveAllWidgets(@RequestBody List<Widget> widget) {
		widgetRepository.deleteAll();
		for (Widget w : widget) {
			widgetRepository.save(w);
		}
	}
}
