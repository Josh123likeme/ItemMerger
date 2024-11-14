package me.Josh123likeme.ItemMerger;

public class Item {
	
	public final ItemType itemType;
	public final Quality quality;
	
	public Item(ItemType itemType, Quality quality) {
		
		this.itemType = itemType;
		this.quality = quality;
		
	}
	
	public int calculateValue() {
		
		return 2 * quality.ordinal() + 12 * itemType.rarity.ordinal();
		
	}
	
	public String itemString() {
		
		return quality.name() + " " + itemType.itemName + " (" + itemType.rarity + ") ($" + calculateValue() + ")";
		
	}
	
	public enum Quality {
		
		RUSTED,
		DAMAGED,
		CRUDE,
		WORN,
		USED,
		CLEAN,
		NEW,
		GREAT,
		REFINED,
		SUPERIOR,
		PRISTINE,
		SUNFORGED,
		COSMIC,
		ABSOLUTE,
		PERFECTED
		
	}
	
}
