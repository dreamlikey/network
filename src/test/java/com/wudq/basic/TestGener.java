package com.wudq.basic;

import com.wudq.genericity.Animal;
import com.wudq.genericity.Cat;
import com.wudq.genericity.Pig;

public class TestGener {
	
	public Pig getPig(Pig p) {
		p.setColor("black");
		p.setHeight(100f);
		p.setSleep("honghong");
		return p;
	}
	
	public Cat getCat(Cat c) {
		c.setColor("white");
		c.setHeight(10f);
		c.setCall("miaomiao");
		return c;
	}
	
	public <T extends Animal> T getAnimal(T t) {
		t.setColor("wh");
		t.setHeight(15f);
		return t;
	}
	
	public static void main(String[] args) {
		TestGener tg = new TestGener();
		Cat cat = new Cat();
		cat.setCall("miaomiao");
		cat = tg.getAnimal(cat);
		
		System.out.println(cat.toString());
	}
}
