package com.example.todo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo.Model.Calender;
import com.example.todo.Model.Todo;
import com.example.todo.Model.User;

public interface  CalenderRepository extends JpaRepository<Todo, Long>{

}