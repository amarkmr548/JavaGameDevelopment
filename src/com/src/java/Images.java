package com.src.java;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Images extends JFrame{

	public static void main(String[] args){
		/*Thread t1 = new Thread(new Apple("one"));
		Thread t2 = new Thread(new Apple("two"));
		Thread t3 = new Thread(new Apple("three"));
		
		t1.start();
		t2.start();
		t3.start();*/
		
		DisplayMode dm = new DisplayMode(1366, 768, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
		Images i = new Images();
		
		i.run(dm);
	}
	
	private Screen s;
	private Image im;
	private Image pic;
	boolean loaded;
	
	public void run(DisplayMode dm){
		setBackground(Color.lightGray);
		setForeground(Color.green);
		setFont(new Font("Arial",Font.PLAIN,24));
		loaded = false;
		
		s = new Screen();
		try{
			s.setFullScreen(dm, this);
			loadPics();
			try{
				Thread.sleep(5000);
			}
			catch(Exception e){}
		}
		finally{
			s.restoreScreen();
		}
	}
	
	private void loadPics() {
		im = new ImageIcon("d:\\fotos\\some.png").getImage();
		pic = new ImageIcon("d:\\fotos\\abcd.jpg").getImage();
		loaded = true;
		repaint();
	}

	public void paint(Graphics g){
		if(g instanceof Graphics2D){
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		}
		g.drawImage(im, 0, 0, null);
		g.drawImage(pic, 200, 200, null);
		
	}
}
