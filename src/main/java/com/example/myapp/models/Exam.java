package com.example.myapp.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.example.myapp.models.QuestionTypes.Question;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Exam extends Widget {
	private String title;
	private String description;
	
	@JsonIgnore
	@OneToMany(mappedBy="exam", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Question> questions;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
}
