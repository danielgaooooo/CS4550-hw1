package com.example.myapp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Widget {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	// common fields ============================
	private int id;
	private String name;
	private String text;
	private String widgetType;
	private int precedence;
	private int maxPrecedence;
	@ManyToOne
	@JsonIgnore
	private Lesson lesson;
	
	// for IMAGE ================================
	private String src;
	private int height;
	private int width;
	
	// for HEADING ==============================
	private int size;
	
	// for LINK =================================
	private String href;
	
	// for LIST =================================
	private String listItems;
	private String listType;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getWidgetType() {
		return widgetType;
	}
	public void setWidgetType(String widgetType) {
		this.widgetType = widgetType;
	}
	public int getPrecedence() {
		return precedence;
	}
	public void setPrecedence(int precedence) {
		this.precedence = precedence;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Lesson getLesson() {
		return lesson;
	}
	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getListItems() {
		return listItems;
	}
	public void setListItems(String listItems) {
		this.listItems = listItems;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public int getMaxPrecedence() {
		return maxPrecedence;
	}
	public void setMaxPrecedence(int maxPrecedence) {
		this.maxPrecedence = maxPrecedence;
	}
}
