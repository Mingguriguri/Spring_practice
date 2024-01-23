package com.example.winterproject.todoApplication.domain;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="follow")
public class Follow implements Serializable{
	@EmbeddedId
	private FollowId followId;

  // 테이블들간 연관관계를 설정해 줄때 일대다(1:N) 관계일때 @JoinColumn 어노테이션을 사용해서 해당 컬럼의 이름을 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower", insertable = false, updatable = false)
    private User followerUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following", insertable = false, updatable = false)
    private User follingUser;
    
	
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
