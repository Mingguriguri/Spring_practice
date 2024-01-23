package com.example.winterproject.todoApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.winterproject.todoApplication.domain.Follow;
import com.example.winterproject.todoApplication.domain.FollowId;


public interface FollowRepository extends JpaRepository<Follow, FollowId > {
	
}
