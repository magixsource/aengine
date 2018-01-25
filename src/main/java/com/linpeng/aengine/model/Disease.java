package com.linpeng.aengine.model;

public class Disease {

	private Long id;
	private String name;

	public Disease() {
	}

	public Disease(String name) {
		this.name = name;
	}

	public Disease(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
