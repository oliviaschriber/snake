package project;

import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.introcs.StdDraw;
import support.cse131.ArgsProcessor;



public class SnakeGame {



	private static int lastX;
	private static int lastY;
	private static int direction;
	static int eatCount = 0;

	private static LinkedList <Snakebody> snake = new LinkedList<Snakebody>();



	public static void snakePoint (double xloc, double yloc, int eatCount) {

		StdDraw.setPenColor(Color.BLUE);
		StdDraw.filledCircle(snake.get(eatCount).getXloc(), snake.get(eatCount).getYloc(), 2);
	}

	public static void background () {
		Color c = new Color (161, 205, 235, 200);
		StdDraw.setPenColor(c);
		StdDraw.filledSquare(50, 50, 50);
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.filledRectangle(50, 0,50, 6);
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(.002);
		StdDraw.rectangle(50, 0,50, 6);

	}

	public static void moveSnake(int direction) {

		snake.removeLast();

		Snakebody first = new Snakebody(snake.getFirst().getXloc(),snake.getFirst().getYloc(), 0);
		if(direction==1) {
			Snakebody newUp = new Snakebody(first.getXloc(),first.getYloc()+2, 0);
			snake.addFirst(newUp);

		}
		if(direction==2) {
			Snakebody newDown = new Snakebody(first.getXloc(),first.getYloc()-2, 0);
			snake.addFirst(newDown);

		}
		if(direction==3) {
			Snakebody newRight = new Snakebody(first.getXloc()+2,first.getYloc(), 0);
			snake.addFirst(newRight);

		}
		if (direction==4) {
			Snakebody newLeft = new Snakebody(first.getXloc()-2,first.getYloc(), 0);
			snake.addFirst(newLeft);

		}
	}

	public static void drawSnake() {
		for (Snakebody s: snake) {
			StdDraw.setPenColor(Color.BLUE);
			System.out.println("x to draw" + s.getXloc() + " y to draw " + s.getYloc());
			StdDraw.filledCircle(s.getXloc(), s.getYloc(), 2);
			StdDraw.setPenColor(Color.BLACK);
			StdDraw.setPenRadius(.003);
			StdDraw.circle(s.getXloc(), s.getYloc(), 2);

			if (s==snake.getFirst()) {
				StdDraw.setPenColor(239, 76, 172);
				StdDraw.filledCircle(s.getXloc(), s.getYloc(), 2);
				StdDraw.setPenColor(Color.BLACK);
				StdDraw.setPenRadius(.005);
				StdDraw.circle(s.getXloc(), s.getYloc(), 2);
			}
		}

		System.out.println("size " + snake.size());
	}

	public static int randomInRange(int start, int stop) {		

		double q=Math.random();
		int random = (int) (q*((stop-start)+1)+start);
		return random;
	}


	public static int xApple = randomInRange(2, 90);
	public static int yApple = randomInRange(8, 90);

	public static void drawApple(int xApple, int yApple) {

		StdDraw.setPenColor(Color.RED);
		StdDraw.filledCircle(xApple, yApple, 2);
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(.005);
		StdDraw.circle(xApple, yApple, 2);
		StdDraw.setPenColor(Color.GREEN);
		StdDraw.filledEllipse(xApple, yApple+3, 1, 1.5);
		StdDraw.setPenColor(18,103,34);
		StdDraw.ellipse(xApple, yApple+3, 1, 1.5);
	}

	public static void eatApple(int direction) {

		boolean isXClose = snake.get(0).getXloc()+2 ==xApple ||snake.get(0).getXloc()+1 ==xApple || snake.get(0).getXloc() ==xApple ||snake.get(0).getXloc()-2 ==xApple ||snake.get(0).getXloc()-1 ==xApple;
		boolean isYClose = snake.get(0).getYloc()+2 ==yApple ||snake.get(0).getYloc()+1 ==yApple || snake.get(0).getYloc() ==yApple ||snake.get(0).getYloc()-2 ==yApple ||snake.get(0).getYloc()-1 ==yApple;
		if (isXClose && isYClose) {
			int closeX=snake.get(0).getXloc()+1;
			int closeY=snake.get(0).getYloc()+1;
			System.out.print("x" + closeX + " y " + closeY);

			System.out.print("last x" + lastX + "lastY" + lastY);
			eatCount = eatCount +1;

			if (direction==1) {
				snake.addLast(new Snakebody(snake.getLast().getXloc(), snake.getLast().getYloc()-2 ,0));
			}
			if (direction==2) {
				snake.addLast(new Snakebody(snake.getLast().getXloc(), snake.getLast().getYloc()+2 ,0));
			}
			if (direction==3) {
				snake.addLast(new Snakebody(snake.getLast().getXloc()-2, snake.getLast().getYloc() ,0));
			}
			if (direction==4) {
				snake.addLast(new Snakebody(snake.getLast().getXloc() +2, snake.getLast().getYloc() ,0));
			}

			xApple = randomInRange(2, 90);
			yApple = randomInRange(8, 90);
		}

	}

	public static void endScreen() {

		if (isOverlapping()==true || isOffGrid() ==true ) {
			Color c = new Color (247, 183, 196, 230);
			StdDraw.setPenColor(c);
			StdDraw.filledRectangle(50, 50, 30, 15);
			Font font = new Font("Georgia", Font.BOLD, 18);
			StdDraw.setFont(font);
			StdDraw.setPenColor(Color.BLACK);
			StdDraw.text(50, 50, "You Lose! Final score = " + (snake.size()-1));
			StdDraw.setPenRadius(.003);
			StdDraw.rectangle(50, 50, 30, 15);

		}

	}

	public static void displayScore() {
		Font font = new Font("Georgia", Font.ITALIC, 16);
		StdDraw.setFont(font);
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.text(50, 3, "Score :  " + (snake.size()-1));



	}
	public static boolean isOverlapping() {
		for (int i =1 ; i<snake.size(); i++){
			Snakebody s = snake.get(i);
			boolean xOverLap = snake.getFirst().getXloc() == s.getXloc();
			boolean yOverLap = snake.getFirst().getYloc() == s.getYloc();

			if (xOverLap ==true && yOverLap ==true) {
				return true;
			}

		}
		return false;

	}

	public static boolean isOffGrid() {
		if (snake.getFirst().getXloc()<0 || snake.getFirst().getXloc()>100) {
			return true;
		}
		if (snake.getFirst().getYloc()<8 || snake.getFirst().getYloc()>100) {
			return true; 
		}
		return false;
	}



	//TODO: Implement the game of snake
	//		You may add other files/classes, but the game should start by running this file
	public static void main(String[] args) {
		ArgsProcessor ap = new ArgsProcessor(args);

		snake.add(new Snakebody(5,40,1));
		snake.add(new Snakebody(5,39,1));
		System.out.print(snake.getFirst().getYloc());
		System.out.print(snake.getLast().getYloc());


		System.out.println("I'm a snaaaaaaaake! HISSSSSSSSSSSssssss.....");
		// TODO: Run the game from here!
		StdDraw.setScale(0,100);
		//	background();

		StdDraw.enableDoubleBuffering();

		direction=1;

		while (true) {

			StdDraw.clear();

			background();
			drawApple(xApple,yApple);
			displayScore();
			eatApple(direction);
			drawSnake();
			endScreen();
			StdDraw.show();
			StdDraw.pause(50);

			if (isOverlapping()==true || isOffGrid()==true) {
				break;
			}

			//UP
			if(StdDraw.isKeyPressed(87)) {
				direction =1;

			}

			//DOWN
			if(StdDraw.isKeyPressed(83)) {
				direction = 2;

			}

			//RIGHT
			if(StdDraw.isKeyPressed(68)) {
				direction =3;

			}

			//LEFT
			if(StdDraw.isKeyPressed(65)) {
				direction=4;
			}
			moveSnake(direction);


			
		}


	}




}



