package com.example.winterproject.todoApplication.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Todo {

	@Id
	@Column(name="todo_id")
	@GeneratedValue
	private Integer todoId;
	
	@ManyToOne
	@JsonIgnore
	private User user;
	
	@Column(name="todo_date")
	private LocalDate todoDate;
	
	private String task;
	

	
}
