package com.darzalgames.zje.utilities.input;

import java.util.ArrayList;
import java.util.List;

import com.darzalgames.zje.architecture.input.actions.Inputtable;
import com.darzalgames.zje.architecture.input.types.BinaryInput;
import com.darzalgames.zje.math.ScalingStrategy;
import com.darzalgames.zje.physics.UnitVector;
import com.darzalgames.zje.physics.Vector;

public class KeyboardDirection implements Directional{
	
	private BinaryInput upKey;
	private BinaryInput leftKey;
	private BinaryInput downKey;
	private BinaryInput rightKey;	
	private ScalingStrategy scalingStrategy;
	
	private Vector directionOfInput;

	public KeyboardDirection(BinaryInput upKey, BinaryInput leftKey, BinaryInput downKey, BinaryInput rightKey, ScalingStrategy scalingStrategy) {
		this.upKey = upKey;
		this.leftKey = leftKey;
		this.downKey = downKey;
		this.rightKey = rightKey;
		this.scalingStrategy = scalingStrategy;
		
		this.directionOfInput = new Vector();
	}

	@Override
	public Vector direction() {
		return scalingStrategy.scale(directionOfInput);
	}
	
	public List<Inputtable> inputs(){
		List<Inputtable> inputs = new ArrayList<>();
		inputs.add(directionInput(upKey, UnitVector.down())); //Note, down and up are inverted because java awt's y axis points downwards
		inputs.add(directionInput(leftKey, UnitVector.left()));
		inputs.add(directionInput(downKey, UnitVector.up()));
		inputs.add(directionInput(rightKey, UnitVector.right()));
		
		return inputs;
	}


	private Inputtable directionInput(BinaryInput input, Vector direction) {
		return new Inputtable() {

			@Override
			public void released() {
				directionOfInput = directionOfInput.subtract(direction);
			}

			@Override
			public void pressed() {
				directionOfInput = directionOfInput.add(direction);		
			}

			@Override
			public BinaryInput binaryInput() {
				return input;
			}
		};
	}
	
}
