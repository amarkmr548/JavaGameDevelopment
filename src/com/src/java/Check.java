package com.src.java;

interface A{
	void update();
}

interface B{
	void update();
}

public class Check implements A,B {

	@Override
	public void update() {
		System.out.println("instance");
		
	}
	
	public static void main(String[] args) {
		Check a = new Check();
		
		a.update();
	}

}
