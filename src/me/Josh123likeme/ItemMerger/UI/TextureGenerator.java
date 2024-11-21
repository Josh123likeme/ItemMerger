package me.Josh123likeme.ItemMerger.UI;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TextureGenerator {
	
	public static BufferedImage generateConnectedDots(long seed, BufferedImage img, int frames, int currentFrame, int voronoiPoints, int pointSpread) {
		
		Random random = new Random(seed);
		
		int[] vpxs = new int[voronoiPoints * 2];
		int[] vpys = new int[voronoiPoints * 2];
		int[] vpzs = new int[voronoiPoints * 2];
		int[] vpcs = new int[voronoiPoints * 2];
		
		for (int i = 0; i < voronoiPoints; i++) {
			
			vpxs[i] = random.nextInt(img.getWidth());
			vpxs[i + voronoiPoints] = vpxs[i]; 
			vpys[i] = random.nextInt(img.getHeight());
			vpys[i + voronoiPoints] = vpys[i]; 
			vpzs[i] = random.nextInt(frames);
			vpzs[i + voronoiPoints] = vpzs[i] + frames;
			//vpcs[i] = 0xFF000000 | ((random.nextInt(128)) << 16);
			vpcs[i] = 0xFF << 24 | i;
			vpcs[i + voronoiPoints] = vpcs[i];
			
		}
		
		for (int y = 0; y < img.getHeight(); y++) {
			
			for (int x = 0; x < img.getWidth(); x++) {
				
				img.setRGB(x, y, 0xFF000000);
				
			}
			
		}
		
		int step = 1;
		
		for (int y = pointSpread; y < img.getHeight() - pointSpread; y += step) {
			
			for (int x = pointSpread; x < img.getWidth() - pointSpread; x += step) {
				
				int closestIndex = -1;
				double closestDistSquared = Double.MAX_VALUE;
				int secondClosestIndex = -1;
				double secondClosestDistSquared = Double.MAX_VALUE;
				
				for (int i = 0; i < vpxs.length; i++) {
					
					double distSquared = (vpxs[i] - x)*(vpxs[i] - x)
							+ (vpys[i] - y)*(vpys[i] - y)
							+ (vpzs[i] - frames / 2 - currentFrame)*(vpzs[i] - frames / 2 - currentFrame);
					
					if (distSquared < closestDistSquared) {
						
						secondClosestDistSquared = closestDistSquared;
						secondClosestIndex = closestIndex;
						
						closestDistSquared = distSquared;
						closestIndex = i;
						
					}
					else if (distSquared < secondClosestDistSquared) {
						
						secondClosestDistSquared = distSquared;
						secondClosestIndex = i;
						
					}
					
				}
				
				//img.setRGB(x, y, vpcs[closestIndex]);
				
				//on the boundary of two sections
				if (Math.abs(Math.sqrt(closestDistSquared) - Math.sqrt(secondClosestDistSquared)) < step) {
					
					if (vpcs[closestIndex] == vpcs[secondClosestIndex]) continue;
					
					for (int y2 = -pointSpread; y2 <= pointSpread; y2++) {
						
						for (int x2 = -pointSpread; x2 <= pointSpread; x2++) {
							
							img.setRGB(x + x2, y + y2, 0xFF800000);
							
						}
						
					}
					
				}
				
			}
			
		}
		
		return img;
		
	}
	
}
