package com.example.winterproject.todoApplication.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.winterproject.todoApplication.domain.User;
import com.example.winterproject.todoApplication.dto.UserInfoResponse;
import com.example.winterproject.todoApplication.exception.UserNotFoundException;
import com.example.winterproject.todoApplication.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserResource {
	
	private UserRepository userRepository;
	
	 public UserResource(UserRepository userRepository) {
		 this.userRepository = userRepository;
	 }
	 
	 // GET /users 
	 // 사용자 조회
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return userRepository.findAll();		
	}
	
	// 회원가입
	// POST /sign_up
	@PostMapping("/sign_up")
	public ResponseEntity<String> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		// /users/{id} => 즉, user.getId()
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
							.path("/{id}")
							.buildAndExpand(savedUser.getUserId())
							.toUri();
		return ResponseEntity.created(location).build();
	}
	
	// 로그인
	// POST /log_in
	@PostMapping("/log_in")
	public ResponseEntity<String> loginUser(@RequestBody User user){
		String userId = user.getUserId();
		String password = user.getPassword();
		
		Optional<User> userOptional = userRepository.findById(userId);
		
		if (userOptional.isPresent()) {
	        User existingUser = userOptional.get();
	        if (existingUser.getPassword().equals(password)) {
	            // 로그인 성공
	            return ResponseEntity.ok("Log in!");
	        }
	    }

	    // 아이디 없거나 비밀번호 틀림
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("401 Unauthorized");
		
	}
	
	// 로그아웃
	// POST /log_out
	// BODY: {}
	@PostMapping("/log_out")
	public ResponseEntity<String> logoutUser(@RequestBody User user){
		// 로그아웃 세션 또는 토큰 무효화
		return ResponseEntity.ok("Logout");
	}
	
	// 회원정보 조회
	// GET /user_info/{user_id}
	@GetMapping("/user_info/{user_id}")
	public EntityModel<UserInfoResponse> retrieveUser(@PathVariable String user_id){
		Optional<User> userOptional = userRepository.findById(user_id);
		
		if(userOptional.isEmpty())
			throw new UserNotFoundException("id:"+user_id);
		
		User user = userOptional.get();
	    UserInfoResponse userInfoResponse = new UserInfoResponse(user.getEmail(), user.getName(), user.getLevel());

	    EntityModel<UserInfoResponse> entityModel = EntityModel.of(userInfoResponse);
	    
	    return entityModel;
	}


	// 회원정보 수정
	// PUT /user_info/{user_id}
	@PutMapping("/user_info/{user_id}")
	public ResponseEntity<String> updateUser(@PathVariable String user_id, @RequestBody User updatedUser){
		
		Optional<User> userOptional = userRepository.findById(user_id);

	    if (userOptional.isEmpty()) {
	        throw new UserNotFoundException("id:" + user_id);
	    }

	    User existingUser = userOptional.get();
	    
	    // 업데이트할 필드 설정
	    existingUser.setEmail(updatedUser.getEmail());
	    existingUser.setName(updatedUser.getName());

	    // 수정된 정보 저장
	    userRepository.save(existingUser);
	    
		return ResponseEntity.ok("Successfully modify!");
	}
	
	// 회원탈퇴
	// DELETE /delete_account/{user_id}
	@DeleteMapping("/delete_account/{user_id}")
	public ResponseEntity<String> deleteUser(@PathVariable String user_id){
		userRepository.deleteById(user_id);
	    return ResponseEntity.ok("delete account");
	}
}
