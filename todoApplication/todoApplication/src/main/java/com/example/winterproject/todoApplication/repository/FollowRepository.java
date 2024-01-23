package com.example.winterproject.todoApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.winterproject.todoApplication.domain.Follow;
import com.example.winterproject.todoApplication.domain.FollowId;


public interface FollowRepository extends JpaRepository<Follow, FollowId > {

    /*@Query("SELECT f.followId.following FROM Follow f WHERE f.followId.follower = :userId")
    List<String> findFollowingUserIdsByUserId(@Param("userId") String userId);*/

}
