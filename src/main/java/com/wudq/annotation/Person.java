package com.wudq.annotation;

public class Person {
	
	private int level;
	
	private String name;
	
	public Person(String _name,int _level) {
		this.name = _name;
		this.level = _level;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}	
