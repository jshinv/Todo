package com.example.todo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Friend {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String usr1;
	private String usr2;
}