package com.zalinius.architecture;

import com.zalinius.architecture.input.Inputtable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.HashMap;

public class InputListener extends KeyAdapter {

	HashMap<Integer, Inputtable> inputs;
	HashMap<Integer, Boolean> states; //pressed or not


	public InputListener(){
		this.inputs = new HashMap<>();
		this.states = new HashMap<>();
	}

	public InputListener(Collection<Inputtable> inputs) {
		this.inputs = new HashMap<>();
		inputs.forEach(input -> this.inputs.put(input.keyCode(), input));

		this.states = new HashMap<>();
		inputs.forEach(input -> this.states.put(input.keyCode(), false));
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keyPressSwitchBoard(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyPressSwitchBoard(e.getKeyCode(), false);
	}


	private void keyPressSwitchBoard(int keyCode, boolean press){
		if(inputs.containsKey(keyCode)) {
			if(press) {
				if(!states.get(keyCode)) {
					Inputtable input = inputs.get(keyCode);
					input.pressed();
					states.put(keyCode, true);
				}
			}
			else {
				Inputtable input = inputs.get(keyCode);
				input.released();
				states.put(keyCode, false);
			}
		}
	}

}
