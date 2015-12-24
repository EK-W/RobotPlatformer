package util;

import java.awt.Color;
import java.util.Random;

public class BrightColorMaker {
	public static Color getRandomColor(){
		Random rand = new Random();
		return getColor(rand.nextInt(1536));
	}
	private static int dist(int x, int from){
		return Math.abs(from - x);
	}
	public static Color getColor(int x){
		//to protect from x values out of the allowed range:
		x = ((x % 1536) + 1536) % 1536;
		return new Color(
				Math.max(0, Math.min(255, dist(x, 768) - 256)),
				Math.max(0, Math.min(255, 512 - dist(x, 1024))),
				Math.max(0, Math.min(255, 512 - dist(x, 512))));
	}
}
