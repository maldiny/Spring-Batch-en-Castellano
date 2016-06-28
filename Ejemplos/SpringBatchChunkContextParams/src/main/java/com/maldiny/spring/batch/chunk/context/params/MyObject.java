package com.maldiny.spring.batch.chunk.context.params;

import java.io.Serializable;

public class MyObject implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private Integer age;
	
	public MyObject(int id, String name, Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "MyObject [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	
}
