package com.example.winterproject.todoApplication.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="follow")
public class Follow implements Serializable{
	@EmbeddedId
	@Column(name="follow_id")
	private FollowId followId;

  // 테이블들간 연관관계를 설정해 줄때 일대다(1:N) 관계일때 @JoinColumn 어노테이션을 사용해서 해당 컬럼의 이름을 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "follower", insertable = false, updatable = false)
    private User followerUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "following", insertable = false, updatable = false)
    private User follingUser;
    
    public User getFollowingUser() {
        return follingUser;
    }
	
	public FollowId getFollowId() {
		return followId;
	}

	public void setFollowId(FollowId followId) {
		this.followId = followId;
	}

	public User getFollowerUser() {
		return followerUser;
	}

	public void setFollowerUser(User followerUser) {
		this.followerUser = followerUser;
	}

	public User getFollingUser() {
		return follingUser;
	}

	public void setFollingUser(User follingUser) {
		this.follingUser = follingUser;
	}

	@Override
	public String toString() {
		return "Follow [followId=" + followId + ", followerUser=" + followerUser + ", follingUser=" + follingUser + "]";
	}
}
