package com.zalinius.utilities.time;

import java.util.Enumeration;
import java.util.Hashtable;

public class GameClock{

	private static Hashtable<Object, Timer> timers = new Hashtable<>();
	private static double time = 0;
	
	public GameClock() {
	}
		
	public static void addTimer(Object owner, double time) {
		if(owner == null) {
			throw new NullPointerException();
		}
		
		timers.put(owner, new PassiveTimer(time));
	}
	
	public static void addActionTimer(Runnable action, double time) {
		timers.put(action, new ActionTimer(action, time));
	}
	
	public static boolean isTimerDone(Object owner) {
		if(owner == null) {
			throw new NullPointerException();
		}

		if(timers.containsKey(owner)) {
			return timers.get(owner).isDone();
		}else {
			return false;
		}
	}
	
	public static double timeLeft(Object owner) {
		if(owner == null) {
			throw new NullPointerException();
		}
		
		if(timers.contains(owner)) {
			return timers.get(owner).timeLeft();
		}
		else {
			return -1.0;
		}
	}

	public static void update(double delta) {
		time += delta;
		for(Enumeration<Timer> t = timers.elements(); t.hasMoreElements();) {
			t.nextElement().update(delta);
		}
		
	}

	public static void removeTimer(Object owner) {
		if(timers.containsKey(owner)) {
			timers.remove(owner);
		}
		
	}
	
	public static double timeNow() {
		return time;
	}
	

}
