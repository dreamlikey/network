package com.wudq.genericity;

public class Cat extends Animal {
	private String call;

	public String getCall() {
		return call;
	}

	public void setCall(String call) {
		this.call = call;
	}

	public String toString() {
		return "Cat[color:" + getColor() + " height:" + getHeight() + " call:" + getCall() + "]";
	}
}
