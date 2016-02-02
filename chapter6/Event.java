package chapter6;

import edu.princeton.cs.algs4.Particle;

public class Event implements Comparable<Event>{
	public final Particle a, b;
	public final double t;
	private final int countA, countB;//用于判断该事件是否失效，实现延时删除
	public Event(double t, Particle a, Particle b){
		//发生在时间t且与a,b有关
		this.t=t; this.a=a; this.b=b;
		if(a != null) this.countA=a.count();
		else countA=-1;
		if(b != null) this.countB=b.count();
		else countB=-1;
	}

	@Override
	public int compareTo(Event o) {
		// TODO Auto-generated method stub
		if(this.t<o.t) return -1;
		else if(this.t>o.t) return 1;
		return 0;
	}
	
	public boolean isValid(){
		if(a != null && a.count() != countA) return false;
		if(b != null && b.count() != countB) return false;
		return true;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
