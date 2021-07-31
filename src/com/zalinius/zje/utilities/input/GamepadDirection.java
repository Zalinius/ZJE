package com.zalinius.zje.utilities.input;

import java.util.Arrays;
import java.util.Collection;

import com.zalinius.zje.architecture.input.actions.Axisable;
import com.zalinius.zje.architecture.input.types.AxialInput;
import com.zalinius.zje.math.ScalingStrategy;
import com.zalinius.zje.physics.Vector;

public class GamepadDirection implements Directional{

	private float xAxis;
	private float yAxis;
	private ScalingStrategy scalingStrategy;
	
	public GamepadDirection(ScalingStrategy scalingStrategy) {
		this.xAxis = 0f;
		this.yAxis = 0f;
		this.scalingStrategy = scalingStrategy;
	}

	@Override
	public Vector direction() {
		Vector direction = new Vector(xAxis, yAxis);
		return scalingStrategy.scale(direction);
	}

	public Collection<Axisable> axisInputs(){
		Axisable verticalAxis = new Axisable() {
			@Override
			public AxialInput axialInput() {
				return AxialInput.LEFT_STICK_VERTICAL;
			}
			@Override
			public void axisMoved(float value) {
				yAxis = value;
			}
		};
		Axisable horizontalAxis = new Axisable() {
			@Override
			public AxialInput axialInput() {
				return AxialInput.LEFT_STICK_HORIZONTAL;
			}
			@Override
			public void axisMoved(float value) {
				xAxis = value;
			}
		};
		
		return Arrays.asList(verticalAxis, horizontalAxis);
	}


}
