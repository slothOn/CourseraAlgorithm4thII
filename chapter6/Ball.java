package chapter6;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Ball {
	private double rx, ry, vx, vy;
	private final double radius;
	public Ball(){
		radius=.01;
		rx=StdRandom.random();
		ry=StdRandom.random();
		vx=.01; vy=.01;
	}
	
	public void move(double dt){
		if ((rx + vx*dt < radius) || (rx + vx*dt > 1.0 - radius)) { vx = -vx; }
		if ((ry + vy*dt < radius) || (ry + vy*dt > 1.0 - radius)) { vy = -vy; }
		rx = rx + vx*dt;
		ry = ry + vy*dt;
	}
	
	public void draw(){
		StdDraw.filledCircle(rx, ry, radius);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
