package com.example.todo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo.Model.Invite;

public interface  InviteRepository extends JpaRepository<Invite, Long>{
	public String findByNickName2(String nickName2);
	public Invite findById(long id);
	}