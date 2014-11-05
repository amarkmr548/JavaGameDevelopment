package com.src.java;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.ColorModel;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Bucky extends JFrame{

	//private Screen s;
	private Animation a;
	private Image bg;
	
	private ScreenManager s;
	
	public static final DisplayMode[] modes1= {
		new DisplayMode(1366,768,64,0),
		new DisplayMode(800,600,32,0),
		new DisplayMode(800,600,16,0),
		new DisplayMode(800,600,24,0),
		new DisplayMode(640,480,32,0),
		new DisplayMode(640,480,16,0),
		new DisplayMode(640,480,24,0),
	};
	
	public static void main(String[] args){
		/*Thread t1 = new Thread(new Apple("one"));
		Thread t2 = new Thread(new Apple("two"));
		Thread t3 = new Thread(new Apple("three"));
		
		t1.start();
		t2.start();
		t3.start();*/
		
		//DisplayMode dm = new DisplayMode(1366, 768, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
		Bucky b = new Bucky();
		
		b.run();
	}
	
	/**
	 * @param dm
	 */
	public void run(){
		
		/*this.getContentPane().setBackground(Color.white);
		setForeground(Color.green);
		setFont(new Font("Britannic Bold",Font.PLAIN,24)); */
		
		
		s = new ScreenManager();
		
		try{
			//s.setFullScreen(dm, this);
			DisplayMode dm = s.findFirstCompatibleMode(modes1);
			s.setFullScreen(dm);
			loadImages();
			movieLoop();
			
		}
		finally{
			s.restoreScreen();
		}
	}
	
	private void movieLoop() {
		long startingTime = System.currentTimeMillis();
		long cumTime = startingTime;
		
		while(cumTime - startingTime < 6000){
			long timePassed = System.currentTimeMillis() - cumTime;
			
			cumTime += timePassed;
			a.update(timePassed);
			Graphics2D g = s.getGraphics();
			draw(g);
			g.dispose();
			s.update();
			try{
				Thread.sleep(20);
			}catch(Exception e){}
		}
	}

	private void loadImages() {
		bg = new ImageIcon("d:\\fotos\\some.png").getImage();
		Image face1 = new ImageIcon("d:\\fotos\\Check1.jpg").getImage();
		Image face2 = new ImageIcon("d:\\fotos\\Check2.jpg").getImage();
		a = new Animation();
		a.addScene(face1, 250);
		a.addScene(face2, 250);
	}
	
	public void draw(Graphics g){
		g.drawImage(bg,0,0,null);
		g.drawImage(a.getImage(),0,0,null);
	}
	
	/*@Override
	public void paint(Graphics g){
		super.paintComponents(g);
		if(g instanceof Graphics2D){
			
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		}
		g.drawString("This is going to be awesome:", 200, 200);
		
	}*/
}
