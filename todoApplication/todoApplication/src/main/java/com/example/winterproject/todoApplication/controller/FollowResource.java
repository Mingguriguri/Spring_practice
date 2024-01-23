package com.example.winterproject.todoApplication.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.winterproject.todoApplication.domain.Follow;
import com.example.winterproject.todoApplication.domain.FollowId;
import com.example.winterproject.todoApplication.repository.FollowRepository;

@RestController
public class FollowResource {

	private FollowRepository followRepository;
	
	 public FollowResource(FollowRepository followRepository) {
		 this.followRepository = followRepository;
	 }
	 
	// 전체 조회
	@GetMapping("/follow")
	public List<Follow> retrieveAllUsers(){
		return followRepository.findAll();		
	}
	 
	// 팔로우
	@PostMapping("/follow")
	public ResponseEntity<String> followUser(@RequestBody FollowId followId) {
	    Follow follow = new Follow();
	    follow.setFollowId(followId); // 복합키 설정
	    followRepository.save(follow);
	    return ResponseEntity.ok("Follow."); // 팔로우 성공
	}
	
	// 언팔로우
	@DeleteMapping("/cancel_follow/{follower}/{following}")
	public ResponseEntity<String> cancelFollow(
	    @PathVariable String follower,
	    @PathVariable String following
	) {
	    FollowId followId = new FollowId();
	    followId.setFollower(follower);
	    followId.setFollowing(following);
	    
	    Optional<Follow> followOptional = followRepository.findById(followId);
	    
	    if (followOptional.isPresent()) {
	        Follow follow = followOptional.get();
	        followRepository.delete(follow); // 팔로우 관계 삭제
	        return ResponseEntity.ok("Unfollow."); // 언팔로우 성공
	    } else {
	        return ResponseEntity.ok("Follow relationship not found."); // 해당 팔로우 관계가 없음
	    }
	}

    
}
