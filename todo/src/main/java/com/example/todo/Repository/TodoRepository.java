package com.example.todo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo.Model.Todo;

public interface  TodoRepository extends JpaRepository<Todo, Long>{

	
	public Optional<Todo> findById(long id);
	public Todo findByHostIdAndTitle(String nickName, String title);
	public List<Todo> findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(String today1, String today2);
	
}



