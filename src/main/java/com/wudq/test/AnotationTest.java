package com.wudq.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.wudq.annotation.Permmisson;
import com.wudq.annotation.Person;

public class AnotationTest {
	
	@Permmisson(level=1)
	public String getFirstLevelMenu() {
		return "1";
	}
	
	@Permmisson(level=2)
	public String getSecondLevelMenu() {
		return "2";
	}
	
	@Permmisson(level=0)
	public String getAdminLevel() {
		return "0";
	}
	
	public static void main(String[] args) {
		Method[] methods = AnotationTest.class.getDeclaredMethods();
		
		Person p = new Person("员工", 1);
		AnotationTest test = new AnotationTest();
//		test.getSecondLevelMenu();
		for (Method method : methods) {
			Annotation[] annotation = method.getAnnotations();
			for (Annotation an : annotation) {
				Permmisson pms = (Permmisson) an;
				System.out.println(method.getName() + "的访问等级："+pms.level());
			}
			System.out.println(method.getName());
		}
	}
}
