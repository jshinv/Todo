package com.example.todo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByNickNameAndPassWord(String nickName, String passWord);
}