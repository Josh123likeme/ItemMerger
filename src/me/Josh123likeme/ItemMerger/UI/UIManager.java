package me.Josh123likeme.ItemMerger.UI;

import java.awt.Color;
import java.awt.Graphics;

import me.Josh123likeme.ItemMerger.Game;

public class UIManager {
	
	//general constants
	public final int borderSpacing = 40;
	public final int topBorderSpacing = 100;
	
	private final Game game;
	
	private Slide slide;
	
	private Inventory inventory;
	
	public UIManager(Game game) {
		
		this.game = game;
		
		slide = Slide.INVENTORY;
		
		inventory = new Inventory(game, this);
		
	}
	
	public void render(Graphics graphics) {
		
		//location of the origin and dimensions of the slider picker section of the ui
		final int slidePickerX = borderSpacing;
		final int slidePickerY = borderSpacing;
		final int slidePickerW = game.getWidth() - borderSpacing * 2;
		final int slidePickerH = topBorderSpacing - borderSpacing;
		
		graphics.setColor(Color.yellow);
		graphics.fillRect(slidePickerX, slidePickerY, slidePickerW, slidePickerH);
		
		graphics.setColor(new Color(50, 50, 50));
		
		for (int i = 0; i < borderSpacing / 4; i++) {
			
			graphics.drawRect(i * 2,  i * 2, game.getWidth() - 1 - i * 4, game.getHeight() - 1 - i * 4);
			
		}
		
		//slide rendering
		switch(slide) {
		
		case INVENTORY:
			
			inventory.render(graphics);
			break;
			
		case TRADEIN:
			
			break;
		
		case INFUSE:
			
			break;
		}
		
	}
	
	public void setSlide(Slide slide) {
		
		this.slide = slide;
		
	}
	
	public enum Slide {
		
		INVENTORY,
		TRADEIN,
		INFUSE
		
	}
	
}
