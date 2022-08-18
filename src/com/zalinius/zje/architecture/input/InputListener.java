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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.zalinius.zje.architecture.Logical;
import com.zalinius.zje.architecture.input.actions.Axisable;
import com.zalinius.zje.architecture.input.actions.Clickable;
import com.zalinius.zje.architecture.input.actions.Inputtable;
import com.zalinius.zje.architecture.input.types.AxialInput;
import com.zalinius.zje.architecture.input.types.BinaryInput;
import com.zalinius.zje.architecture.input.types.MouseInput;
import com.zalinius.zje.physics.Locatable;
import com.zalinius.zje.physics.Point;

import uk.co.electronstudio.sdl2gdx.SDL2Controller;

public class InputListener extends KeyAdapter implements MouseListener, MouseMotionListener, Locatable, RumbleListener, Logical{

	private Map<BinaryInput, Inputtable> binaryInputs;
	private Map<BinaryInput, Boolean> binaryInputStates; //pressed or not

	private Map<MouseInput, Collection<Clickable>> mouseInputs;
	private Map<Clickable, Boolean> mouseAreasStatus;
	
	private Map<AxialInput, Collection<Axisable>> mouseAxesInputs;

	private int mouseX;
	private int mouseY;
	
	private Locatable screenSize;

	private GamepadManager gamepads;

	public InputListener(Locatable screenSize){
		this.screenSize = screenSize;
		this.binaryInputs = new EnumMap<>(BinaryInput.class);
		this.binaryInputStates = new EnumMap<>(BinaryInput.class);
		
		this.mouseInputs = new EnumMap<>(MouseInput.class);
		this.mouseAreasStatus = new HashMap<>();
		this.mouseAxesInputs = new EnumMap<>(AxialInput.class);
		this.mouseAxesInputs.put(AxialInput.MOUSE_X, new ArrayList<>());
		this.mouseAxesInputs.put(AxialInput.MOUSE_Y, new ArrayList<>());

		mouseX = mouseY = 0;
		 
		gamepads = new GamepadManager(); 
	}

	public void addInput(Axisable axis) {
		if(axis.axialInput().mouseBased()) {
			if(!this.mouseAxesInputs.containsKey(axis.axialInput() )) {
				this.mouseAxesInputs.put(axis.axialInput(), new ArrayList<>());
			}	

			mouseAxesInputs.get(axis.axialInput()).add(axis);
		}
		else {
			gamepads.addAxisInput(axis);			
		}
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
		
		mouseAreasStatus.put(click, click.clickArea().contains(mouseX, mouseY));
	}
	
	public void resetClickables(Collection<Clickable> newClickables) {
		mouseInputs.clear();
		mouseAreasStatus.clear();
		
		newClickables.forEach(this::addInput);
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
				if(Boolean.TRUE.equals(binaryInputStates.get(keyboardInput))) {
					Inputtable input = binaryInputs.get(keyboardInput);
					input.released();
					binaryInputStates.put(keyboardInput, false);
				}
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
		
		mouseAxesInputs.get(AxialInput.MOUSE_X).forEach(mouseXInput -> mouseXInput.axisMoved((float)(mouse.x / screenSize.x())));
		mouseAxesInputs.get(AxialInput.MOUSE_Y).forEach(mouseYInput -> mouseYInput.axisMoved((float)(mouse.y / screenSize.y())));
		
		mouseX = (int) mouse.x;
		mouseY = (int) mouse.y;
		
		for (Iterator<Clickable> it = mouseAreasStatus.keySet().iterator(); it.hasNext();) {
			Clickable clickable = it.next();
			
			if(Boolean.TRUE.equals(mouseAreasStatus.get(clickable))) {
				//Area was already entered
				if(!clickable.clickArea().contains(mouseX, mouseY)) {
					mouseAreasStatus.put(clickable, false);
					clickable.mouseLeft();
				}
			}
			else {				//Area was not yet entered

				if(clickable.clickArea().contains(mouseX, mouseY)) {
					mouseAreasStatus.put(clickable, true);
					clickable.mouseEntered();
				}
			}
		}
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
