package com.example.winterproject.todoApplication.domain;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class LikeId implements Serializable {
	private Integer todoId; 
    private String userId; 
    	
}
