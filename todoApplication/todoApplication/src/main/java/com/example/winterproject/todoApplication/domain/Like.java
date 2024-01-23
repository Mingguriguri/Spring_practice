package com.example.winterproject.todoApplication.domain;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity(name="likes")
public class Like implements Serializable{

	@EmbeddedId
	private LikeId likeId;
	
}
