package com.example.todo.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Invite {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nickName1;
	private String nickName2;
	private boolean bool1 = false;
	private boolean bool2 = false;
	private boolean todoinvite;
	private long todo_id;
}