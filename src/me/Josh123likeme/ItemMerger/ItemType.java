package me.Josh123likeme.ItemMerger;

public class ItemType {
	
	public final String itemName;
	public final ItemRarity rarity;
	
	public ItemType(String itemName, ItemRarity rarity) {
		
		this.itemName = itemName;
		this.rarity = rarity;
		
	}
	
	public enum ItemRarity {
		
		COMMON,
		UNCOMMON,
		RARE,
		EPIC,
		LEGENDARY,
		MYTHIC,
		EMPYREAN
		
	}
	
}
