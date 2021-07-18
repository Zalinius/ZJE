package com.zalinius.zje.utilities.input;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.zalinius.zje.architecture.input.actions.Axisable;
import com.zalinius.zje.architecture.input.actions.Inputtable;
import com.zalinius.zje.architecture.input.types.BinaryInput;
import com.zalinius.zje.math.ScalingStrategy;
import com.zalinius.zje.math.ScalingStrategyFactory;
import com.zalinius.zje.physics.Vector;

public class GamepadKeyboardDirection implements Directional {
	
	private KeyboardDirection wasdDirection;
	private KeyboardDirection arrowsDirection;
	private GamepadDirection  gamepadDirection;
	
	public GamepadKeyboardDirection() {
		ScalingStrategy strategy = ScalingStrategyFactory.scaleLengthBetweenZeroAndOne();
		
		this.wasdDirection = new KeyboardDirection(BinaryInput.KEY_W, BinaryInput.KEY_A, BinaryInput.KEY_S, BinaryInput.KEY_D, strategy);
		this.arrowsDirection = new KeyboardDirection(BinaryInput.KEY_UP, BinaryInput.KEY_LEFT, BinaryInput.KEY_DOWN, BinaryInput.KEY_RIGHT, strategy);
		this.gamepadDirection = new GamepadDirection(strategy);
	}

	@Override
	public Vector direction() {
		if(wasdDirection.direction().isNonZero()) {
			return wasdDirection.direction();
		}
		else if(arrowsDirection.direction().isNonZero()) {
			return arrowsDirection.direction();
		}
		else if(gamepadDirection.direction().isNonZero()) {
			return gamepadDirection.direction();
		}
		else {
			return new Vector();
		}
	}
	
	public List<Inputtable> inputs(){
		List<Inputtable> inputs = new ArrayList<>();
		inputs.addAll(wasdDirection.inputs());
		inputs.addAll(arrowsDirection.inputs());
		return inputs;
	}
	
	public Collection<Axisable> axisInput() {
		return gamepadDirection.axisInputs();
	}

}
