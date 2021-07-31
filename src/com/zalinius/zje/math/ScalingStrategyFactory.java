package com.zalinius.zje.math;

import com.zalinius.zje.physics.Vector;

public class ScalingStrategyFactory {
	
	public static ScalingStrategy dontScale() {
		return new ScalingStrategy() {
			
			@Override
			public Vector scale(Vector vector) {
				return vector;
			}
		};
	}
	
	public static ScalingStrategy clampLengthBetweenZeroAndOne() {
		return new ScalingStrategy() {
			
			@Override
			public Vector scale(Vector vector) {
				Vector scaledVector = vector;

				if(!vector.isZeroVector() && vector.length() > 1) {
					scaledVector = scaledVector.normalize();
				}
				
				return scaledVector;

			}
		};
	}
	
}
