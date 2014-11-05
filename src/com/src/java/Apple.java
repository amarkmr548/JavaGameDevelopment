package com.src.java;

import java.util.Random;

public class Apple implements Runnable{

	int time;
	String name;
	Random r = new Random();
	
	public Apple(String s){
		name = s;
		time = r.nextInt(999);
	}
	
	public void run(){
		try{
			System.out.printf("%s is sleeping for %d \n", name,time);
			Thread.sleep(time);
			System.out.printf("%s is done\n",name);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
