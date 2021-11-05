package project;

import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;

public class Snakebody {
	
	public int xloc; 
	public int yloc;
	public static int length;
	
	public Snakebody(int xloc, int yloc, int length) {
		this.xloc = xloc;
		this.yloc = yloc;
		this.length = length;
	}
	public int getXloc() {
		return xloc;
	}

	public void setXloc(int xloc) {
		this.xloc = xloc;
	}

	public int getYloc() {
		return yloc;
	}

	public void setYloc(int yloc) {
		this.yloc = yloc;
	}

	
	
	public static void snakePoint (double xloc, double yloc, double length) {
		StdDraw.setPenColor(Color.BLUE);
		StdDraw.filledCircle(xloc, yloc, .001);
		StdDraw.show();
	}
	
	
	
	
	
	
}


