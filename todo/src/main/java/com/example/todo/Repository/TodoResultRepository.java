package com.example.todo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo.Model.TodoResult;

public interface  TodoResultRepository extends JpaRepository<TodoResult, Long>{
}