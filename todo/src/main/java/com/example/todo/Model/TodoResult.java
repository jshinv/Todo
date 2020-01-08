package com.example.todo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class TodoResult {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long todo_id;
	private String today;
	private int realCount =0;
	private String range;
	private int party_ID;
}