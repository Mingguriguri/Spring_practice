package com.example.winterproject.todoApplication.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.winterproject.todoApplication.domain.Follow;
import com.example.winterproject.todoApplication.domain.Like;
import com.example.winterproject.todoApplication.domain.LikeId;
import com.example.winterproject.todoApplication.domain.Todo;
import com.example.winterproject.todoApplication.domain.User;
import com.example.winterproject.todoApplication.repository.LikeRepository;



@RestController
public class LikeResource {
	
	private LikeRepository likeRepository;
	
	public LikeResource(LikeRepository likeRepository) {
		 this.likeRepository = likeRepository;
	}
	
	/*
	 * 조회
	 */
	// GET /like
	@GetMapping("/like")
	public List<Like> retrieveLike() {
		return likeRepository.findAll();
	}
	
	 // 좋아요
    // POST /like
    @PostMapping("/like")
    public ResponseEntity<String> likeTodo(@RequestBody LikeId likeId) {
	    // Like 객체 생성
	    Like like = new Like();
	    like.setLikeId(likeId);
	
	    // Like 객체 저장
	    likeRepository.save(like);
	
	    // 성공 메시지 반환
	    return ResponseEntity.ok("Liked.");
    }
		

}
