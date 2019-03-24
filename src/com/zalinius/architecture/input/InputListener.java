package com.zalinius.architecture.input;

import com.zalinius.architecture.Logical;
import com.zalinius.architecture.input.gamePad.XBox360Controller;
import com.zalinius.architecture.input.gamePad.XBox360Controller.Button360;
import com.zalinius.utilities.Debug;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller.Type;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class InputListener implements EventHandler<KeyEvent>, Logical{

	Map<KeyCode, Inputtable> keyInputs;
	Map<KeyCode, Boolean> keyStates; //pressed or not
	Map<Button360, Inputtable> buttonInputs;
	Map<Button360, Boolean> buttonStates;
	Controller controller;
	
	Map<Integer, Collection<Clickable>> mouseInputs;


	public InputListener(){
		this(new ArrayList<>(), new ArrayList<>());
	}

	public InputListener(Collection<Inputtable> keyInputs, Collection<Clickable> mouseInputs) {
		this.keyInputs = new HashMap<>();
		this.keyStates = new HashMap<>();
		this.buttonInputs = new EnumMap<>(Button360.class);
		this.buttonStates = new EnumMap<>(Button360.class);

		
		for (Iterator<Inputtable> iterator = keyInputs.iterator(); iterator.hasNext();) {
			Inputtable inputtable = iterator.next();
			if(inputtable.button().isKey()) {
				this.keyInputs.put(inputtable.button().keyCode(), inputtable);
				this.keyStates.put(inputtable.button().keyCode(), false);
			}
			else if(inputtable.button().is360Button()) {
				this.buttonInputs.put(inputtable.button().button360(), inputtable);
				this.buttonStates.put(inputtable.button().button360(), false);
			}
		}
		
		this.mouseInputs = new HashMap<>();
		for (Clickable clickable : mouseInputs) {
			if(!this.mouseInputs.containsKey(clickable.mouseButtonCode() )) {
				this.mouseInputs.put(clickable.mouseButtonCode(), new ArrayList<>());
			}
			
			this.mouseInputs.get(clickable.mouseButtonCode()).add(clickable);
		}
		
		Controller[] ca = ControllerEnvironment.getDefaultEnvironment().getControllers();
		ArrayList<Controller> gamePads = new ArrayList<>();
		for(int i =0;i<ca.length;i++){
			if(ca[i].getType().equals(Type.GAMEPAD)) {
				gamePads.add(ca[i]);
				
			}
		}
		
		if(!gamePads.isEmpty()) {
		    this.controller = gamePads.get(0);
		    Debug.log("Added Controller!");
		}
	}
	
	public void addInput(Inputtable input) {
		if(input.button().isKey()) {
			this.keyInputs.put(input.button().keyCode(), input);
			this.keyStates.put(input.button().keyCode(), false);
		}
		else if(input.button().is360Button()) {
			this.buttonInputs.put(input.button().button360(), input);
			this.buttonStates.put(input.button().button360(), false);
		}
	}
	
	public void addInput(Clickable click) {
		if(!this.mouseInputs.containsKey(click.mouseButtonCode() )) {
			this.mouseInputs.put(click.mouseButtonCode(), new ArrayList<>());
		}	
		mouseInputs.get(click.mouseButtonCode()).add(click);
	}
	

	@Override
	public void handle(KeyEvent event) {
		keyPressSwitchBoard(event.getCode(), event.getEventType());
	}
	
	



	private void keyPressSwitchBoard(KeyCode keyCode, EventType<KeyEvent> type){
		if(keyInputs.containsKey(keyCode)) {
			if(type.equals(KeyEvent.KEY_PRESSED)) {
				if(!keyStates.get(keyCode)) {
					Inputtable input = keyInputs.get(keyCode);
					input.pressed();
					keyStates.put(keyCode, true);
				}
			}
			else if(type.equals(KeyEvent.KEY_RELEASED)) {
				Inputtable input = keyInputs.get(keyCode);
				input.released();
				keyStates.put(keyCode, false);
			}
		}
	}

	@Override
	public void update(double delta) {
		//Activate held keys
		for (Iterator<KeyCode> iterator = keyStates.keySet().iterator(); iterator.hasNext();) {
			KeyCode key = iterator.next();
			if(keyStates.get(key)) {
				keyInputs.get(key).held(delta);
			}
		}
		
		//Poll controllers
		controller.poll();
		for (Iterator<Button360> iterator = buttonInputs.keySet().iterator(); iterator.hasNext();) {
			Button360 button360 = iterator.next();
			Identifier id = XBox360Controller.getIdentifier(button360);
			float value = controller.getComponent(id).getPollData();
			if(button360 == Button360.A) {
				System.out.print(value+ "\n");
			}
			if(value != 0 && (button360 == Button360.D_UP || button360 == Button360.D_DOWN || button360 == Button360.D_LEFT || button360 == Button360.D_RIGHT)) {
				Button360 pressedButton = XBox360Controller.getDpadDirection(value);
				
				for (Iterator<Button360> dpadIt = XBox360Controller.dpadButtons(); dpadIt.hasNext();) {
					Button360 dpadButt = dpadIt.next();
					
					if(pressedButton == dpadButt) {//Active dpad
						if(buttonStates.get(dpadButt)) {
							buttonInputs.get(dpadButt).held(delta);
						}
						else {
							buttonInputs.get(dpadButt).pressed();
							buttonStates.put(dpadButt, true);
						}

					}
					else {//Inactive dpad
						if(buttonStates.get(dpadButt)) {
							buttonInputs.get(dpadButt).released();
							buttonStates.put(dpadButt, false);
						}
					}
				}
				
				
			}
			else {
				boolean oldState = buttonStates.get(button360);
				boolean newState = value == 1.0;
				buttonStates.put(button360, newState);
				Inputtable action = buttonInputs.get(button360);
				
				if(oldState == true && newState == true) {
					action.held(delta);
				}
				else if(oldState == false && newState == true) {
					action.pressed();
					System.out.println("pressed!");
				}
				else if(oldState == true && newState == false) {
					action.released();
				}

			}
		}
	}
		
	
//
//	public void mouseClicked(MouseEvent event) {
//		if(mouseInputs.containsKey(event.getButton())) {
//			for (Clickable clickable : mouseInputs.get(event.getButton())) {
//				if(clickable.clickArea().contains(event.getPoint())) {
//					clickable.mouseClicked();
//				}
//			}
//		}
//	}
//
//	public void mouseEntered(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void mouseExited(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void mousePressed(MouseEvent event) {
//		if(mouseInputs.containsKey(event.getButton())) {
//			for (Clickable clickable : mouseInputs.get(event.getButton())) {
//				if(clickable.clickArea().contains(event.getPoint())) {
//					clickable.mousePressed();
//				}
//			}
//		}		
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent event) {
//		if(mouseInputs.containsKey(event.getButton())) {
//			for (Clickable clickable : mouseInputs.get(event.getButton())) {
//				if(clickable.clickArea().contains(event.getPoint())) {
//					clickable.mouseReleased();
//				}
//			}
//		}				
//	}

}
