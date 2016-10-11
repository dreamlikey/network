package com.wudq.test;

import java.util.Arrays;
import java.util.List;

/**
 * rtti是c++中的概念
 * 它与java中的reflect反射是同一个事情，
 * 反射机制更加完善
 * @author wudq
 *
 */
public class RttiTest {

	public static void main(String[] args) {
		List<Shape> shapes = Arrays.asList(
							new Circle(),
							new Square(),
							new Triangle());
		for (Shape shape : shapes) {
			shape.draw();
		}
	}
}

abstract class Shape {
	void draw() {
		System.out.println(this + ".draw()");
	}
	abstract public String toString();
}

class Circle extends Shape{
	@Override
	public String toString() {
		return "Circle";
	}
}

class Square extends Shape{
	@Override
	public String toString() {
		return "Square";
	}
}

class Triangle extends Shape{
	@Override
	public String toString() {
		return "Triangle";
	}
}