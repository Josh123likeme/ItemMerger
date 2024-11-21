package me.Josh123likeme.ItemMerger;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.util.List;

import me.Josh123likeme.ItemMerger.InputListener.*;
import me.Josh123likeme.ItemMerger.UI.UIManager;

public class Game extends Canvas implements Runnable {

	public static final int INITIAL_WIDTH = 400, INITIAL_HEIGHT = 400;
	
	private Thread thread;
	private boolean running = false;
	
	public MouseWitness mouseWitness;
	public KeyboardWitness keyboardWitness;
	
	private double deltaFrame;
	private int fps;
	private int frame = 0;
	
	private ItemManager im;
	public List<Item> items;
	
	private UIManager uiManager;

	public Game() {
		
		//load save
		
		im = new ItemManager(69420, 50, 50, 40, 40, 30, 20, 15);
		
		items = im.load(new File("save.txt"));
		
		//initialise UIs
		
		uiManager = new UIManager(this);
		
		initInputs();
		
		new Window(INITIAL_WIDTH, INITIAL_HEIGHT, "Item Merger", this);
		
	}
	
	private void initInputs() {
		
		mouseWitness = new MouseWitness();
		keyboardWitness = new KeyboardWitness();
		
		addMouseListener(mouseWitness);
		addMouseMotionListener(mouseWitness);
		addKeyListener(keyboardWitness);
		
		requestFocus();
		
	}
	
	public synchronized void start() {
		
		thread = new Thread(this);
		thread.start();
		running = true;
		
	}
	
	public synchronized void stop() {
		
		try {
			
			thread.join();
			running = false;
		}
		
		catch(Exception e) {e.printStackTrace();}
		
	}
	
	public void run() {
		
		double targetfps = 60d;
		long targetDeltaFrame = Math.round((1d / targetfps) * 1000000000);
		long lastSecond = System.nanoTime();
		int frames = 0;
		
		long lastFrame = System.nanoTime();
		
		while (running) {
			
			frames++;
			
			if (lastSecond + 1000000000 < System.nanoTime()) {
				
				fps = frames;
				
				frames = 0;
				
				lastSecond = System.nanoTime();
				
				targetDeltaFrame = Math.round((1d / targetfps) * 1000000000);
				
			}
			
			//starting to push frame
			
			long nextTime = System.nanoTime() + targetDeltaFrame;
			
			deltaFrame = ((double) (System.nanoTime() - lastFrame)) / 1000000000;
			
			lastFrame = System.nanoTime();
			
			preFrame();
			
			paint();
			
			//finished pushing frame
			
			frame++;
			
			keyboardWitness.purgeTypedKeys();
			mouseWitness.purgeClickedButtons();
			
			while (nextTime > System.nanoTime());
			
		}
		
		stop();
		
	}
	
	private void preFrame() {
	
	}

	private void paint() {
	
		BufferStrategy bufferStrategy = this.getBufferStrategy();
		if(bufferStrategy == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics graphics = bufferStrategy.getDrawGraphics();
		
		//basic black background to stop flashing
		graphics.setColor(Color.black); 
		graphics.fillRect(0, 0, getWidth(), getHeight());
		
		//put rendering stuff here
		
		uiManager.render(graphics);
		
		//this pushes the graphics to the window
		bufferStrategy.show();
		
	}
	
	public double getDeltaFrame() {
		
		return deltaFrame;
	}
	
	public int getFrame() {
		
		return frame;
		
	}
	
}
