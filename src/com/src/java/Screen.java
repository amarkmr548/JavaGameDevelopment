package com.src.java;

import java.awt.*;

import javax.swing.JFrame;

public class Screen {
	GraphicsDevice vc;
	
	public Screen(){
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		vc = env.getDefaultScreenDevice();
	}
	
	public void setFullScreen(DisplayMode dm,JFrame window){
		window.setUndecorated(true);
		window.setResizable(false);
		vc.setFullScreenWindow(window);
		
		if(dm != null && vc.isDisplayChangeSupported()){
			try{
				vc.setDisplayMode(dm);
				
			}catch(Exception e){
				
			}
		}
	}
	
	public Window getFullScreenWindow(){
		return vc.getFullScreenWindow();
	}
	
	public void restoreScreen(){
		if(vc.getFullScreenWindow() != null){
			vc.getFullScreenWindow().dispose();
		}
		
		vc.setFullScreenWindow(null);
	}
}
