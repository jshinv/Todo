package com.example.todo.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.todo.Model.TodoResult;

public interface TodoResultRepository extends JpaRepository<TodoResult, Long> {

	public TodoResult findByTodoId(long todo_id);
	public TodoResult findByTodoIdAndToday(long todoId, String today);
	public List<TodoResult> findAllByToday(String today);
}

