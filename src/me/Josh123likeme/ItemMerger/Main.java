package me.Josh123likeme.ItemMerger;

public class Main {
	
	public static void main(String[] args) {
		
		new Game();
		/*
		ItemManager itemManager = new ItemManager();
		
		itemManager.generateItemTypes(69420, 50, 50, 40, 40, 30, 20, 15);
		
		Item[] items = new Item[100];
		
		int index = 0;
		while (index < 100) {
			
			Item item = itemManager.createItem();
			
			if (item.itemType.rarity.equals(ItemType.ItemRarity.MYTHIC)) {
				items[index] = item;
				index++;
				
				System.out.println(item.itemString());
				
			}
			
		}
		
		for (int i = 0; i < items.length; i += 5) {
			
			Item item = itemManager.tradeIn(items[i], items[i + 1], items[i + 2], items[i + 3], items[i + 4]);
			
			System.out.println("\nTRADE:");
			
			System.out.println(items[i].itemString());
			System.out.println(items[i + 1].itemString());
			System.out.println(items[i + 2].itemString());
			System.out.println(items[i + 3].itemString());
			System.out.println(items[i + 4].itemString());
			
			System.out.println("INTO:");
			
			System.out.println(item.itemString());
			
		}
		
		for (int i = 0; i < items.length; i += 2) {
			
			Item item = itemManager.infuse(items[i], items[i + 1]);
			
			System.out.println("\nINFUSE:");
			
			System.out.println(items[i].itemString());
			
			System.out.println("WITH:");
			
			System.out.println(items[i + 1].itemString());
			
			System.out.println("BECOMES:");
			
			System.out.println(item.itemString());
			
		}
		*/
	}
	
}
