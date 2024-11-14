package me.Josh123likeme.ItemMerger;

import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.Josh123likeme.ItemMerger.Item.Quality;
import me.Josh123likeme.ItemMerger.ItemType.ItemRarity;

public class ItemManager {
	
	//used for generating new random items
	private Random random = new Random();
	
	private final double[] qualityProbabilities = new double[] {0.01,0.02,0.05,0.08,0.094,0.15,0.2,0.2,0.1,0.05,0.02,0.01,0.01,0.005,0.001};
	private final double[] rarityProbabilities = new double[] {0.6,0.25,0.1,0.0389,0.01,0.001,0.0001};
	private final double[] tradeInQualityBuffProbabilities = new double[] {0.89,0.1,0.01};
	
	private List<ItemType[]> itemTypes = new ArrayList<ItemType[]>();
	
	//thank you Chat-GPT for these item names
	private static final String[] possibleItemNames = {
		    "Abyssal Fang", "Vortex Key", "Emberdusk", "Cindergale", "Frostbloom", "Nightwhisper", "Soulflare", "Ironclaw", "Dawnseeker", "Moonheart",
		    "Stormthorn", "Blightvine", "Cinderveil", "Tempestclaw", "Dreadmist", "Celestial Fang", "Frostspire", "Sunveil", "Gravesong", "Shadebranch",
		    "Thundershroud", "Violetflame", "Ironwreath", "Ravenshard", "Soulflare", "Moonstone", "Dawncry", "Voidspire", "Emberhelm", "Shadowspike",
		    "Stormwing", "Nightbane", "Celestia", "Whisperveil", "Ashenclaw", "Venomshade", "Lunarwing", "Frostcore", "Stormrune", "Bloodthorn",
		    "Shadowhollow", "Ravenshadow", "Dreadfang", "Soulshard", "Emberthorn", "Thunderglow", "Moonshade", "Frostbite", "Voidcaller", "Riftstone",
		    "Gravestone", "Flameveil", "Blightthorn", "Crimsonleaf", "Nightwarden", "Wraithclaw", "Tempestorb", "Tidalshard", "Blazingwhisper", "Stormrage",
		    "Arcanehelm", "Frostsong", "Obsidianroot", "Sunflare", "Gravewhisper", "Dreadshroud", "Shadowvine", "Frostsong", "Bloodshroud", "Ironspire",
		    "Celestialflare", "Gloomveil", "Ravensworn", "Emberstrike", "Dawnspike", "Vortexwing", "Thunderlash", "Grimsoul", "Ashenroot", "Soulstone",
		    "Voidwhisper", "Crimsonwing", "Stormwhisper", "Hallowcry", "Shadowblade", "Flamecore", "Lunarflare", "Venomfang", "Doomstone", "Cinderlash",
		    "Emberbloom", "Nightfang", "Cinderthorn", "Frostbloom", "Blazewind", "Moonshard", "Soulveil", "Ironstone", "Bloodfang", "Nightflare",
		    "Stormbreaker", "Crimsonstone", "Wraithflare", "Voidfang", "Silvermoon", "Violetroot", "Blightstone", "Embercrystal", "Gravestone", "Ravenscorch",
		    "Shadowthorn", "Starshard", "Voidroot", "Emberveil", "Ashenbloom", "Tempeststrike", "Frostglow", "Gravespire", "Celestialheart", "Doomvine",
		    "Shatterfang", "Nightbloom", "Cinderwhisper", "Ravenflare", "Tornveil", "Frostblade", "Whisperclaw", "Sunshard", "Flamevein", "Stormclaw",
		    "Blazeheart", "Cinderspike", "Nightstone", "Shatterveil", "Vortexheart", "Soulvine", "Emberrage", "Thunderspike", "Moonclaw", "Frostveil",
		    "Vortexfire", "Windcrystal", "Moonbloom", "Nightshard", "Bloodthorn", "Ravenwing", "Grimsoul", "Eclipsedgem", "Frostveil", "Glimmerdust",
		    "Emberveil", "Flamecrystal", "Stormcry", "Obsidianveil", "Darkwhisper", "Violetthorn", "Shadowgale", "Frostbite", "Graveflare", "Doomwing",
		    "Vexroot", "Searingflame", "Moonwhisper", "Wraithgale", "Nightstone", "Emberglow", "Cinderroot", "Vortexvine", "Frostwhisper", "Lunarflare",
		    "Infernosurge", "Ironwhisper", "Venomveil", "Blazeheart", "Doomwhisper", "Cinderstrike", "Raventhorn", "Flamefang", "Lunarwing", "Ashbloom",
		    "Obsidianfang", "Sunflare", "Mooncrest", "Thunderspike", "Tempestbloom", "Blazeclaw", "Gravemoon", "Vortexblossom", "Stormveil", "Shatterfang",
		    "Shadowstorm", "Nightshard", "Ravenscorch", "Stormclaw", "Doomheart", "Blazingthorn", "Frostfang", "Venomroot", "Cindertide", "Emberstorm",
		    "Stormlight", "Crimsonroot", "Violetveil", "Frostclaw", "Soulshard", "Ironveil", "Blazeclaw", "Cinderheart", "Stormroot", "Moonstone",
		    "Nightshade", "Emberlash", "Crimsonbloom", "Violetstorm", "Blightbloom", "Talonshard", "Tempestwing", "Ashroot", "Frostflare", "Moonwhisper",
		    "Emberclaw", "Nightflare", "Dreadwing", "Vortexwhisper", "Frostlance", "Bloodstone", "Venomthorn", "Stormbloom", "Moonfury", "Ironlance",
		    "Blazeclaw", "Cindergale", "Stormspire", "Emberlash", "Voidspire", "Ravencrown", "Moonfrost", "Gravesoul", "Shadowflame", "Froststone",
		    "Celestialbloom", "Ravenspire", "Bloodshard", "Cinderflare", "Dawnstone", "Ashenclaw", "Thundershard", "Frostgale", "Soulwind", "Vilethorn",
		    "Darkroot", "Bladespire", "Cinderdust", "Embermist", "Stormlash", "Silverflare", "Gravespike", "Ashspire", "Frostbloom", "Voidthorn",
		    "Blightroot", "Vortexrage", "Dawnflare", "Soulstone", "Cindershade", "Tempestgale", "Voidfury", "Blazeveil", "Doomthorn", "Stormcrystal",
		    "Emberroot", "Violetbloom", "Cinderwhisper", "Graveshade", "Ravenscorch", "Nightflare", "Sunshard", "Moonstone", "Ironmist", "Shadowlash",
		    "Moonflare", "Frostwind", "Wraithroot", "Soulvine", "Ashenroot", "Blightflare", "Dreadstone", "Blazewing", "Frostcloak", "Soulblossom",
		    "Ironheart", "Blazebloom", "Embervine", "Cinderstorm", "Nightflame", "Gravewhisper", "Voidbloom", "Doomfire", "Stormthorn", "Ravenstone",
		    "Ironwhisper", "Frostbloom", "Blightstone", "Nightshade", "Soulflare", "Moonwing", "Shadowroot", "Emberwhisper", "Tempestroot", "Cinderglow",
		    "Wraithbloom", "Doomlash", "Venomflare", "Blazewind", "Crimsonroot", "Moonveil", "Dreadwhisper", "Nightbloom", "Stormflare", "Voidbloom",
		    "Cindershade", "Emberthorn", "Frostshade", "Wraithlash", "Moonstone", "Bloodlash", "Nightroot", "Emberveil", "Thunderspire", "Frostbite"
		};

	private boolean[] usedNames = new boolean[possibleItemNames.length];
	
	private final long seed;
	private final int commonsCount;
	private final int uncommonsCount;
	private final int raresCount;
	private final int epicsCount;
	private final int legendariesCount;
	private final int mythicsCount;
	private final int empyreansCount;
	
	public ItemManager(long seed, int commonsCount, int uncommonsCount, int raresCount, int epicsCount, int legendariesCount, int mythicsCount, int empyreansCount) {
		
		this.seed = seed;
		this.commonsCount = commonsCount;
		this.uncommonsCount = uncommonsCount;
		this.raresCount = raresCount;
		this.epicsCount = epicsCount;
		this.legendariesCount = legendariesCount;
		this.mythicsCount = mythicsCount;
		this.empyreansCount = empyreansCount;
		
		itemTypes.add(new ItemType[commonsCount]);
		itemTypes.add(new ItemType[uncommonsCount]);
		itemTypes.add(new ItemType[raresCount]);
		itemTypes.add(new ItemType[epicsCount]);
		itemTypes.add(new ItemType[legendariesCount]);
		itemTypes.add(new ItemType[mythicsCount]);
		itemTypes.add(new ItemType[empyreansCount]);
		
		if (commonsCount + uncommonsCount + raresCount + epicsCount + legendariesCount + mythicsCount + empyreansCount > possibleItemNames.length) {
			throw new IllegalArgumentException("there arent enough names to choose for this amount of items");
		}
		
		//used for generating the item types
		Random randomTypesGen = new Random(seed);
		
		for (int i = 0; i < itemTypes.size(); i++) {
			
			for (int j = 0; j < itemTypes.get(i).length; j++) {
				
				int choice;
				
				do {	
					choice = randomTypesGen.nextInt(possibleItemNames.length);
				}
				while (usedNames[choice]);
				
				ItemType itemType = new ItemType(possibleItemNames[choice], ItemRarity.values()[i]);
				itemTypes.get(i)[j] = itemType;
				
			}
			
		}
		
	}
		
	//create a new random item with the probabilities under consideration
	public Item createItem() {
		
		int qualityChoice = Utils.select(qualityProbabilities, random.nextDouble());
		
		int rarityChoice = Utils.select(rarityProbabilities, random.nextDouble());
		
		int itemChoice = random.nextInt(itemTypes.get(rarityChoice).length);
		
		return new Item(itemTypes.get(rarityChoice)[itemChoice], Quality.values()[qualityChoice]);
		
	}
	
	//can turn 5 items of the same rarity into 1 item of the next rarity
	public Item tradeIn(Item i0, Item i1, Item i2, Item i3, Item i4) {
		
		if (i0.itemType.rarity != i1.itemType.rarity) {
			if (i0.itemType.rarity != i2.itemType.rarity) {
				if (i0.itemType.rarity != i3.itemType.rarity) {
					if (i0.itemType.rarity != i4.itemType.rarity) {
						throw new IllegalArgumentException("All items have to be the same rarity");
					}
				}
			}
		}
		
		if (i0.itemType.rarity.equals(ItemType.ItemRarity.EMPYREAN)) throw new IllegalArgumentException("Cant trade in items that are empyrean");
		
		int averageQuality = (i0.quality.ordinal() + i1.quality.ordinal() + i2.quality.ordinal() + i3.quality.ordinal() + i4.quality.ordinal()) / 5;
		
		int qualityBuff = Utils.select(tradeInQualityBuffProbabilities,random.nextDouble());
		
		int newItemQuality = averageQuality + qualityBuff;
		
		if (newItemQuality > Quality.values().length - 1) newItemQuality = Quality.values().length - 1;
		
		int newItemChoice = random.nextInt(itemTypes.get(i0.itemType.rarity.ordinal() + 1).length);
		
		return new Item(itemTypes.get(i0.itemType.rarity.ordinal() + 1)[newItemChoice], Quality.values()[newItemQuality]);
		
	}
	
	//can have a chance to buff the quality of an item by sacrificing another item of the same rarity
	public Item infuse(Item target, Item sacrifice) {
		
		if (target.itemType.rarity != sacrifice.itemType.rarity) throw new IllegalArgumentException("The sacrifice must be the same rarity as the target");
		
		//the sacrifice is higher quality than the target
		if (target.quality.ordinal() < sacrifice.quality.ordinal()) {
			
			int qualityDifference = sacrifice.quality.ordinal() - target.quality.ordinal();
			
			//successfully buffed the quality of the target to be the same as the sacrifice quality
			if (random.nextDouble() < 1 - 1D / (Math.pow(10, qualityDifference))) {
				
				return new Item(target.itemType, sacrifice.quality);
				
			}
			
		}
		//the sacrifice is the same or lower quality than the target
		else {
			
			int qualityDifference = target.quality.ordinal() - sacrifice.quality.ordinal();
			
			//successfully buffed the quality of the target by one
			if (random.nextDouble() < 1D / (Math.pow(10, qualityDifference + 1))) {
				
				int newQuality = target.quality.ordinal() + 1;
				
				if (newQuality > Quality.values().length - 1) newQuality = Quality.values().length - 1;
				
				return new Item(target.itemType, Quality.values()[newQuality]);
				
			}
			
		}
		
		//failed to make a difference with infusion
		return target;
		
	}
	
	public List<Item> load(File file) {
		//TODO implement
		//this currently just generates 100 random items
		List<Item> items = new ArrayList<Item>();
		
		for (int i = 0; i < 100; i++) {
			
			items.add(createItem());
			
		}
		
		return items;
		
	}
	
	public void save(File file, List<Item> items) {
		
		
		
	}
	
	private String serialise(Item item) {
		
		String data = "";
		
		
		
		return data;
		
	}
	
}
