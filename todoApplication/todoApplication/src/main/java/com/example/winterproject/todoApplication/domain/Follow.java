package com.example.winterproject.todoApplication.domain;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity(name="follow")
public class Follow implements Serializable{
	@EmbeddedId
	private FollowId followId;

	public FollowId getFollowId() {
		return followId;
	}

	public void setFollowId(FollowId followId) {
		this.followId = followId;
	}

	@Override
	public String toString() {
		return "Follow [followId=" + followId + "]";
	}
}
