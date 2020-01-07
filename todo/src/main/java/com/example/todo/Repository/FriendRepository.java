package com.example.todo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo.Model.Friend;

public interface  FriendRepository extends JpaRepository<Friend, Long>{

}