package com.everis.uco.spring.batch.multiresourceitemreader;

public class MyDomain {
	
	private String id;
	
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MyDomain(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public MyDomain() {
		super();
	}
	
}
