package com.example.myapp.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.myapp.models.Widget;
import com.example.myapp.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins="*")
public class WidgetService {

	@Autowired
	WidgetRepository widgetRepository;
	
	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets() {
		return (List<Widget>) widgetRepository.findAll();
	}
	
	@PostMapping("/api/widget/save")
	public void saveAllWidgets(@RequestBody List<Widget> widget) {
		widgetRepository.deleteAll();
		for (Widget w : widget) {
			widgetRepository.save(w);
		}
	}
}
