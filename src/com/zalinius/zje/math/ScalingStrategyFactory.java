package com.zalinius.zje.math;

import com.zalinius.zje.physics.Vector;

public class ScalingStrategyFactory {
	
	private ScalingStrategyFactory() {}
	
	public static ScalingStrategy dontScale() {
		return vector -> vector;
	}
	
	public static ScalingStrategy clampLengthBetweenZeroAndOne() {
		return vector -> {
			Vector scaledVector = vector;

			if(!vector.isZeroVector() && vector.length() > 1) {
				scaledVector = scaledVector.normalize();
			}
			
			return scaledVector;
		};
	}
	
}
