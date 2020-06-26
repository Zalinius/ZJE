package com.zalinius.utilities.time;

import java.util.Enumeration;
import java.util.Hashtable;

public class GameClock{

	private static Hashtable<Object, Timer> timers = new Hashtable<>();
	private static double time = 0;
	
	public GameClock() {
	}
		
	public static void addTimer(Object owner, double timerTime) {
		if(owner == null) {
			throw new NullPointerException();
		}
		
		timers.put(owner, new PassiveTimer(timerTime));
	}
	
	public static void addActionTimer(Runnable action, double timerTime) {
		timers.put(action, new ActionTimer(action, timerTime));
	}
	
	public static void addActionTimer(Runnable action, double timerTime, int instances) {
		if(instances < 0) {
			throw new RuntimeException("Negative number of instances: " + instances);
		}
		
		for(int i = 0; i != instances; ++i) {
			addActionTimer(action, timerTime * (i+1) );
		}
	}
	
	public static boolean isTimerDone(Object owner) {
		if(owner == null) {
			throw new NullPointerException();
		}

		if(timers.containsKey(owner)) {
			return timers.get(owner).isDone();
		}else {
			return true;
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
			return 0.0;
		}
	}

	public static void update(double delta) {
		time += delta;
		for(Enumeration<Timer> t = timers.elements(); t.hasMoreElements();) {
			t.nextElement().update(delta);
		}
		
	}

	public static boolean removeTimer(Object owner) {
		if(timers.containsKey(owner)) {
			timers.remove(owner);
			return true;
		}
		else {
			return false;
		}
		
	}
	
	/**
	 * @return The time since the beginning of the game, in seconds
	 */
	public static double timeNow() {
		return time;
	}
	

}
