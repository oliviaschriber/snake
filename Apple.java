package project;

import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;

public class Apple {
	
	private static int xApple;
	private static int yApple;
	
	public Apple (int xApple, int yApple) {
		this.xApple = xApple;
		this.yApple = yApple;
	}
	
	public static int getxApple() {
		xApple = (int)(10*Math.random())+1;
		return xApple;
	}
	public static int getyApple() {
		yApple = (int)(10*Math.random())+1;
		return yApple;
	} 
	public static int randomInRange(int start, int stop) {		

		double q=Math.random();
		int random = (int) (q*((stop-start)+1)+start);
		return random;
	}
//	public static void drawApple() {
//		int xApple = randomInRange(1, 99);
//		int yApple = randomInRange(1, 99);
//		StdDraw.setPenColor(Color.GREEN);
//		StdDraw.filledCircle(xApple, yApple, 10);
//	}
}
