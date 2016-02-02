package chapter6;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Particle;
import edu.princeton.cs.algs4.StdDraw;

//碰撞模拟
public class CollisionSystem {
	double t=0.0;//模拟时钟
	private MinPQ<Event> pq;
	private Particle[] particles;
	public CollisionSystem(Particle[] particles) {
		// TODO Auto-generated constructor stub
		this.particles=particles;
	}
	
	public void predictCollisions(Particle a, double limit){
		if(a == null) return;
		for(int i=0;i<particles.length;i++){
			double dt=a.timeToHit(particles[i]);
			if(t+dt <= limit) pq.insert(new Event(t+dt, a, particles[i]));
		}
		double dtx=a.timeToHitVerticalWall();
		if(t+dtx<limit) pq.insert(new Event(t+dtx, a, null));
		double dty=a.timeToHitHorizontalWall();
		if(t+dty<limit) pq.insert(new Event(t+dty, null, a));
	}
	
	public void redraw(double limit, double Hz){
		StdDraw.clear();
		for(int i=0;i<particles.length;i++){
			particles[i].draw();
		}
		StdDraw.show(20);
		if(t+Hz<limit) {
			pq.insert(new Event(t+1.0/Hz, null, null));
		}
	}
	
	public void simulate(double limit, double Hz){
		pq=new MinPQ<Event>();
		for(int i=0;i<particles.length;i++){
			predictCollisions(particles[i], limit);
		}
		pq.insert(new Event(0, null, null));
		while(!pq.isEmpty()){
			Event e=pq.delMin();
			if(!e.isValid()) continue;
			for(int i=0;i<particles.length;i++){
				particles[i].move(e.t-t);
			}
			t=e.t;
			Particle a=e.a;
			Particle b=e.b;
			if(a != null && b != null) {
				a.bounceOff(b);
			}else if(a != null && b == null){
				a.bounceOffVerticalWall();
			}else if(b != null && a == null){
				b.bounceOffHorizontalWall();
			}else{
				redraw(limit, Hz);
			}
			predictCollisions(a, limit);
			predictCollisions(b, limit);
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StdDraw.show(0);
		int N=15;
		Particle[] ps=new Particle[N];
		for(int i=0;i<N;i++){
			ps[i]=new Particle();
		}
		CollisionSystem system=new CollisionSystem(ps);
		system.simulate(10000, 0.5);
	}

}
