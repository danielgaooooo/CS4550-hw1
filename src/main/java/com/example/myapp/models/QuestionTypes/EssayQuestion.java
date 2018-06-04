package com.example.myapp.models.QuestionTypes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "JOINED_ESSAY_QUESTION")
public class EssayQuestion
	extends Question {
	@Column(name = "RESPONSE", nullable = false)
	private String response;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}
