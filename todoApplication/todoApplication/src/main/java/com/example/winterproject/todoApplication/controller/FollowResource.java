package com.example.winterproject.todoApplication.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.winterproject.todoApplication.domain.Follow;
import com.example.winterproject.todoApplication.domain.FollowId;
import com.example.winterproject.todoApplication.domain.User;
import com.example.winterproject.todoApplication.repository.FollowRepository;
import com.example.winterproject.todoApplication.repository.UserRepository;

@RestController
public class FollowResource {
	private FollowRepository followRepository;
	private UserRepository userRepository;
	
	public FollowResource(FollowRepository followRepository, UserRepository userRepository) {
		this.followRepository = followRepository;
		this.userRepository = userRepository;
	 }
	 
	// 전체 조회
	// GET /follow
	@GetMapping("/follow")
	public List<Follow> retrieveAllUsers(){
		return followRepository.findAll();		
	}
	 
	// 팔로우
	// POST /follow
	@PostMapping("/follow")
	public ResponseEntity<String> followUser(@RequestBody FollowId followId) {
		Optional<User> followerOptional = userRepository.findById(followId.getFollower());
		Optional<User> followingOptional = userRepository.findById(followId.getFollowing());
		
		// follwer랑 following 모두 존재하면 follow
		if (followerOptional.isPresent() && followingOptional.isPresent()) {
			Follow follow = new Follow();
		    follow.setFollowId(followId); // 복합키 설정
		    follow.setFollowerUser(followingOptional.get());
		    follow.setFollingUser(followingOptional.get());
		    followRepository.save(follow);
		    return ResponseEntity.ok("Follow."); 
		}
		return ResponseEntity.badRequest().build();
	   
	}
	
	// 언팔로우
	// DELETE /cancel_follow/{follower}/{following}
	@DeleteMapping("/cancel_follow/{follower}/{following}")
	public ResponseEntity<String> cancelFollow(@PathVariable String follower, @PathVariable String following) {
	    FollowId followId = new FollowId();
	    followId.setFollower(follower);
	    followId.setFollowing(following);
	    
	    Optional<Follow> followOptional = followRepository.findById(followId);
	    
	    if (followOptional.isPresent()) {
	        Follow follow = followOptional.get();
	        followRepository.delete(follow); // 팔로우 관계 삭제
	        return ResponseEntity.ok("Unfollow."); // 언팔로우 성공
	    } else {
	    	return ResponseEntity.badRequest().build();
	    }
	}

	// 팔로워 조회
	// GET /users/{id}/following
	//@GetMapping("/users/{id}/following")
	//public void checkFollowing(@PathVariable String user_id) {
		
	//}
	
	/*@GetMapping("/following/{id}")
	public ResponseEntity<List<User>> checkFollowing(@PathVariable("id") String user_id) {
	    // 팔로잉하는 사용자의 ID 목록을 조회
	    List<String> followingUserIds = followRepository.findFollowingUserIdsByUserId(user_id);

	    // 해당 ID를 사용하여 User 엔티티 목록 조회
	    List<User> followingUsers = userRepository.findAllById(followingUserIds);

	    return ResponseEntity.ok(followingUsers);
	}*/
	
    
}
