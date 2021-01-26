package com.zalinius.zje.math.random;

import java.util.Random;

public class ZRand {
	private static final Random random = new Random();
	
	public static boolean coinFlip() {
		return Math.random() < .5d;
	}
	public static boolean random(int chance, int denominator) {
		return (chance-1) >= (random.nextInt(denominator));
	}
}
