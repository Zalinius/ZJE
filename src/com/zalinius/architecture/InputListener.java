package com.zalinius.architecture;

import com.zalinius.architecture.input.Inputtable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.HashMap;

public class InputListener extends KeyAdapter {

	HashMap<Integer, Inputtable> inputs;


	public InputListener(){
		this.inputs = new HashMap<>();
	}

	public InputListener(Collection<Inputtable> inputs) {
		this.inputs = new HashMap<>();
		inputs.forEach(input -> this.inputs.put(input.keyCode(), input));
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
			Inputtable input = inputs.get(keyCode);
			input.action();
		}
	}

}
