package com.src.java;

import java.awt.Image;
import java.util.ArrayList;



public class Animation {
	private ArrayList scenes;
	private long totalTime;
	private long movieTime;
	private int index;
	
	public Animation(){
		totalTime = 0;
		scenes = new ArrayList();
		start();
	}

	public synchronized void start(){
		movieTime = 0;
		index = 0; 
	}
	
	public synchronized void addScene(Image pic, long t){
		totalTime += t;
		scenes.add(new OneScene(pic,totalTime));
	}
	
	private OneScene getScene(int ind){
		return (OneScene)scenes.get(ind);
	}
	
	public synchronized void update(long timePassed){
		if(scenes.size()>1){
			movieTime += timePassed;
			if(movieTime>totalTime){
				movieTime = 0;
				index = 0;
			}
			while (movieTime> getScene(index).t){
				index++;
			}
		}
	}
	
	public synchronized Image getImage(){
		if(scenes.size() == 0){
			return null;
		}
		else{
			return getScene(index).im;
		}
	}
	
	private class OneScene{
		long t;
		Image im;
		
		public OneScene(Image im, long t){
			this.im = im;
			this.t = t;
		}
	}
}
