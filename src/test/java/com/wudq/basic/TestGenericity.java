//package com.wudq.basic;
//
///**
// *泛型
// * @author wudq
// *
// */
//public class TestGenericity {
//
//	public static void main(String[] args) {
//		Animal<Dog> an = new Animal<Dog>(new Dog());
//		System.out.println(an.toString());
//	}
//}
//
//class Animal <T> {
//	
//	private T an;
//	
//	public Animal(T animal) {
//		this.an = animal;
//	}
//
//	public T getAn() {
//		return an;
//	}
//	
//	public String toString () {
//		return "an:"+an;
//	}
//}
//
//class Dog {
//	
//}