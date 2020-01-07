package com.example.todo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo.Model.TodoResult;
import com.example.todo.Model.User;

public interface  TodoResultRepository extends JpaRepository<TodoResult, Long>{
	
}