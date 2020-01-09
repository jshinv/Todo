package com.example.todo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo.Model.Todo;
import com.example.todo.Model.TodoResult;
import com.example.todo.Model.User;

public interface  TodoRepository extends JpaRepository<Todo, Long>{
	public TodoResult findByTodoIdAndToday(long todoId, String Today);

}



