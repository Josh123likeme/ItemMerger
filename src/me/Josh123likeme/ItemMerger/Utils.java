package me.Josh123likeme.ItemMerger;

public class Utils {
	
	public static int select(double[] frequencies, double choice) {
		
		//check that frequencies add up
		
		double checkTotal = 0;
		
		for (int i = 0; i < frequencies.length; i++) {
			
			checkTotal += frequencies[i];
			
		}
		
		if (Math.abs(checkTotal - 1) > 0.000001) throw new IllegalArgumentException("Frequencies don't total to 1.0 (" + checkTotal + ")");
		
		//selection
		
		double total = 0;
		
		for (int i = 0; i < frequencies.length; i++) {
			
			if ((choice >= total) && (choice < total + frequencies[i])) return i;
			
			total += frequencies[i];
			
		}
		
		System.out.println(choice);
		
		return -1;
		
	}
	
}
