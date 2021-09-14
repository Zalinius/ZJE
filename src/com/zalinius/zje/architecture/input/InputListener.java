package com.zalinius.zje.architecture.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

import com.zalinius.zje.architecture.Logical;
import com.zalinius.zje.architecture.input.actions.Axisable;
import com.zalinius.zje.architecture.input.actions.Clickable;
import com.zalinius.zje.architecture.input.actions.Inputtable;
import com.zalinius.zje.architecture.input.types.BinaryInput;
import com.zalinius.zje.architecture.input.types.MouseInput;
import com.zalinius.zje.physics.Locatable;
import com.zalinius.zje.physics.Point;

import uk.co.electronstudio.sdl2gdx.SDL2Controller;

public class InputListener extends KeyAdapter implements MouseListener, MouseMotionListener, Locatable, RumbleListener, Logical{

	private Map<BinaryInput, Inputtable> binaryInputs;
	private Map<BinaryInput, Boolean> binaryInputStates; //pressed or not

	private Map<MouseInput, Collection<Clickable>> mouseInputs;

	private int mouseX;
	private int mouseY;

	private GamepadManager gamepads;

	public InputListener(){
		this.binaryInputs = new EnumMap<>(BinaryInput.class);
		this.binaryInputStates = new EnumMap<>(BinaryInput.class);
		this.mouseInputs = new EnumMap<>(MouseInput.class);
		mouseX = mouseY = 0;

		gamepads = new GamepadManager(); 
	}

	public void addInput(Axisable axis) {
		gamepads.addAxisInput(axis);
	}

	public void addInput(Inputtable binaryInput) {
		if(binaryInput.binaryInput().isKeyboardKey()) {
			binaryInputs.put(binaryInput.binaryInput(), binaryInput);
			binaryInputStates.put(binaryInput.binaryInput(), false);
		}
		else {
			gamepads.addButtonInput(binaryInput);
		}

	}

	public void addInput(Clickable click) {
		if(!this.mouseInputs.containsKey(click.mouseButton() )) {
			this.mouseInputs.put(click.mouseButton(), new ArrayList<>());
		}	
		mouseInputs.get(click.mouseButton()).add(click);
	}

	private void keyPressSwitchBoard(int keycode, boolean press){
		BinaryInput keyboardInput = BinaryInput.getKeyboardValueFromKeyEvent(keycode);
		if(binaryInputs.containsKey(keyboardInput)) {
			if(press) {
				if(Boolean.FALSE.equals(binaryInputStates.get(keyboardInput))) {
					Inputtable input = binaryInputs.get(keyboardInput);
					input.pressed();
					binaryInputStates.put(keyboardInput, true);
				}
			}
			else {
				Inputtable input = binaryInputs.get(keyboardInput);
				input.released();
				binaryInputStates.put(keyboardInput, false);
			}
		}
	}


	@Override
	public void keyPressed(KeyEvent e) {
		keyPressSwitchBoard(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keyPressSwitchBoard(e.getKeyCode(), false);
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		MouseInput input = MouseInput.getMouseInput(event.getButton());
		if(mouseInputs.containsKey(input)) {
			for (Clickable clickable : mouseInputs.get(input)) {
				if(clickable.clickArea().contains(event.getPoint())) {
					clickable.mouseClicked();
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) { /*Purposefully empty, as no swing components*/}

	@Override
	public void mouseExited(MouseEvent arg0) { /*Purposefully empty, as no swing components*/	}

	@Override
	public void mousePressed(MouseEvent event) {
		MouseInput input = MouseInput.getMouseInput(event.getButton());

		if(mouseInputs.containsKey(input)) {
			for (Clickable clickable : mouseInputs.get(input)) {
				if(clickable.clickArea().contains(event.getPoint())) {
					clickable.mousePressed();
				}
			}
		}		
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		MouseInput input = MouseInput.getMouseInput(event.getButton());

		if(mouseInputs.containsKey(input)) {
			for (Clickable clickable : mouseInputs.get(input)) {
				clickable.mouseReleased();
			}
		}				
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Point mouse = new Point(e.getPoint());
		mouseX = (int) mouse.x;
		mouseY = (int) mouse.y;
	}

	@Override
	public Point position() {
		return new Point(mouseX, mouseY);
	}

	@Override
	public void requestRumble(Duration duration, double magnitude) {
		SDL2Controller controller = gamepads.mainController();
		if(controller != null) {
			gamepads.mainController().rumble((float)magnitude, (float)magnitude, (int)duration.toMillis());
		}
	}

	@Override
	public void update(double delta) {
		gamepads.update(delta);
	}


}
