package com.example.myapp.models.QuestionTypes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.myapp.models.Question;

@Entity
@Table(name = "JOINED_FILL_IN_THE_BLANK_QUESTION")
public class FillInTheBlankQuestion
	extends Question {
	@Column(name = "VARIABLES", nullable = false)
	private String variables;
	public String getVariables() {
		return variables;
	}
	public void setVariables(String variables) {
		this.variables = variables;
	}
}
