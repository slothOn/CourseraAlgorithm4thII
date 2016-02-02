package chapter6;

import edu.princeton.cs.algs4.StdDraw;

public class BouncingBalls {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = Integer.parseInt("10");
		Ball[] balls = new Ball[N];
		for (int i = 0; i < N; i++)
		balls[i] = new Ball();
		while(true)
		{
		StdDraw.clear();
		for (int i = 0; i < N; i++)
		{
		balls[i].move(0.5);
		balls[i].draw();
		}
		StdDraw.show(50);
		}
	}

}
