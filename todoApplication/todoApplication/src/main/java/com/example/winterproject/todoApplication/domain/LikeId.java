package com.example.winterproject.todoApplication.domain;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class LikeId implements Serializable {
	private Integer todo_id; 
    private String user_id;
	public Integer getTodoId() {
		return todo_id;
	}
	public void setTodoId(Integer todo_id) {
		this.todo_id = todo_id;
	}
	public String getUserId() {
		return user_id;
	}
	public void setUserId(String useuser_idrId) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "LikeId [todo_id=" + todo_id + ", user_id=" + user_id + "]";
	}
	
}
