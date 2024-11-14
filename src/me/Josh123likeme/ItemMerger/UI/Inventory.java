package me.Josh123likeme.ItemMerger.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import me.Josh123likeme.ItemMerger.Game;
import me.Josh123likeme.ItemMerger.Item;
import me.Josh123likeme.ItemMerger.ItemManager;

public class Inventory {
	
	//inventory box constants
	private final int itemSize = 200;
	private final int itemSpacing = 20;
	
	//filter box constants
	private final int filterBoxTargetW = 400;
	
	private final Game game;
	private final UIManager uim;
	
	int currentRow = 0;
	
	public Inventory(Game game, UIManager uim) {
		
		this.game = game;
		this.uim = uim;
		
	}
	
	public void render(Graphics graphics) {
		
		List<Item> items = game.items;
		
		//location of the mouse on the screen
		final int mouseX = game.mouseWitness.getMouseX();
		final int mouseY = game.mouseWitness.getMouseY();
		
		//info about how many items are shown in the inventory section
		final int itemsPerRow = (game.getWidth() - uim.borderSpacing * 3 - filterBoxTargetW - itemSpacing) / (itemSize + itemSpacing);
		final int numberOfRows = (game.getHeight() - uim.borderSpacing * 2 - uim.topBorderSpacing - itemSpacing) / (itemSize + itemSpacing);
		
		//location of the origin and dimensions of the inventory section of the ui
		final int inventoryBoxX = uim.borderSpacing;
		final int inventoryBoxY = uim.borderSpacing + uim.topBorderSpacing;
		final int inventoryBoxW = itemSpacing + (itemSize + itemSpacing) * itemsPerRow;
		final int inventoryBoxH = game.getHeight() - uim.topBorderSpacing - uim.borderSpacing * 2;
		
		//location of the origin and dimensions of the filter section of the ui
		final int filterBoxX = inventoryBoxX + inventoryBoxW + uim.borderSpacing;
		final int filterBoxY = uim.borderSpacing + uim.topBorderSpacing;
		final int filterBoxW = game.getWidth() - uim.borderSpacing - filterBoxX;
		final int filterBoxH = game.getHeight() - uim.topBorderSpacing - uim.borderSpacing * 2;
		
		//location of row and column on the screen of item being hovered over
		int cursorItemX = (mouseX - inventoryBoxX - itemSpacing) / (itemSize + itemSpacing);
		int cursorItemY = (mouseY - inventoryBoxY - itemSpacing) / (itemSize + itemSpacing);
		
		//location of the origin of the cursor box
		int cursorX = cursorItemX * (itemSize + itemSpacing) + inventoryBoxX;
		int cursorY = cursorItemY * (itemSize + itemSpacing) + inventoryBoxY;
		
		if (cursorItemX < 0 || cursorItemX > itemsPerRow - 1 || cursorItemY < 0 || cursorItemY > numberOfRows - 1) {
			
			cursorItemX = -1;
			cursorItemY = -1;
			cursorX = -1;
			cursorY = -1;
		}
		
		graphics.setFont(new Font(graphics.getFont().getFontName(), Font.PLAIN, 100)); //set font size to 100
		int testTextWidth = graphics.getFontMetrics().stringWidth("eeeeeeeeeeee"); //get width of 100 font text
		int fontSize = (int) ((double) itemSize / ((double) testTextWidth / 100));
		graphics.setFont(new Font(graphics.getFont().getFontName(), Font.PLAIN, fontSize)); 
		
		//debug drawings
		graphics.setColor(new Color(50,50,255));
		graphics.fillRect(inventoryBoxX, inventoryBoxY, inventoryBoxW, inventoryBoxH);
		
		graphics.setColor(Color.green);
		graphics.fillRect(filterBoxX, filterBoxY, filterBoxW, filterBoxH);
		
		graphics.setColor(Color.white);
		
		//cursor
		if (cursorX != -1 && cursorY != -1) {
			
			graphics.setColor(Color.red);
			graphics.fillRoundRect(cursorX, cursorY, itemSize + itemSpacing * 2, itemSize + itemSpacing * 2, itemSize / 4, itemSize / 4);
		}
		
		//tracks the current index of items
		int i = itemsPerRow * currentRow;
		
		//display items
		for (int y = 0; y < numberOfRows; y++) {

			if (i > items.size() - 1) break;
				
			for (int x = 0; x < itemsPerRow; x++) {
				
				if (i > items.size() - 1) break;
				
				graphics.setColor(Color.white);
				
				graphics.fillRect(inventoryBoxX + itemSpacing + x * (itemSize + itemSpacing), inventoryBoxY + itemSpacing + y * (itemSize + itemSpacing), itemSize, itemSize);
				
				graphics.setColor(Color.black);
				
				int stringWidth = graphics.getFontMetrics().stringWidth(items.get(i).itemType.itemName);
				
				graphics.drawString(items.get(i).itemType.itemName,
						inventoryBoxX + itemSpacing + x * (itemSize + itemSpacing) + (itemSize - stringWidth) / 2,
						inventoryBoxY + itemSpacing + y * (itemSize + itemSpacing) + fontSize);
				
				i++;
				
			}
			
		}
		
	}
	
}