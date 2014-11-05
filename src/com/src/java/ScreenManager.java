package com.src.java;

import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class ScreenManager {
	GraphicsDevice vc;
	
	public ScreenManager(){
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		vc = ge.getDefaultScreenDevice();
	}
	
	public DisplayMode findFirstCompatibleMode(DisplayMode[] modes){
		DisplayMode goodModes[] = vc.getDisplayModes();
		
		
		
		for(int x=0;x<modes.length;x++){
			for(int y=0;y<goodModes.length;y++){
				if(displayModesMatch(modes[x],goodModes[y])){
					return modes[x];
				}
			}
		}
		return null;
	}
	
	private boolean displayModesMatch(DisplayMode displayMode,
			DisplayMode displayMode2) {
		System.out.println("good modes width,height n bitdepth"+ displayMode2.getWidth() +" " + displayMode2.getHeight() + " " + displayMode2.getBitDepth() + " ");
		if(displayMode.getWidth() != displayMode2.getWidth() || displayMode.getHeight() != displayMode2.getHeight()){
			
			return false;
		}
		
		if(displayMode.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && displayMode2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && displayMode.getBitDepth() != displayMode.getBitDepth()){
			return false;
		}
		
		if(displayMode.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && displayMode2.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && displayMode.getRefreshRate() != displayMode2.getRefreshRate()){
			return false;
		}
		
		return true;
	}

	public DisplayMode[] getCompatibleDisplayModes(){
		return vc.getDisplayModes();
	}

	public void setFullScreen(DisplayMode dm) {
		JFrame win = new JFrame();
		win.setUndecorated(true);
		win.setIgnoreRepaint(true);
		win.setResizable(false);
		vc.setFullScreenWindow(win);
		
		if(dm != null && vc.isDisplayChangeSupported()){
			try{
				vc.setDisplayMode(dm);
			}catch(Exception e){
				
			}
		}
		
		win.createBufferStrategy(2);
	}
	
	public Graphics2D getGraphics(){
		Window w = vc.getFullScreenWindow();
		
		if(w != null){
			BufferStrategy s = w.getBufferStrategy();
			return (Graphics2D)s.getDrawGraphics();
		}
		else{
			return null;
		}
	}
	
	public void update(){
		Window w = vc.getFullScreenWindow();
		
		if( w != null ){
			BufferStrategy s = w.getBufferStrategy();
			
			if(!s.contentsLost()){
					s.show();
			}
		}
	}
	
	public Window getFullScreenScreenWindow(){
		return vc.getFullScreenWindow();
	}
	
	public DisplayMode getCurrentDisplayMode(){
		return vc.getDisplayMode();
	}
	
	public int getWidth(){
		Window w = vc.getFullScreenWindow();
		
		if(w != null){
			return w.getWidth();
		}
		else{
			return 0;
		}
	}
	
	public int getHeight(){
		Window w = vc.getFullScreenWindow();
		
		if(w != null){
			return w.getHeight();
		}
		else{
			return 0;
		}
	}
	
	public void restoreScreen(){
		Window w = vc.getFullScreenWindow();
		
		if(w != null){
			w.dispose();
		}
		
		vc.setFullScreenWindow(null);
	}
	
	public BufferedImage getCompatibleImage(int w, int h, int t){
		Window win = vc.getFullScreenWindow();
		
		if(win != null){
			GraphicsConfiguration gc = win.getGraphicsConfiguration();
			return gc.createCompatibleImage(w, h, t);
		}
		
		return null;
	}
}
