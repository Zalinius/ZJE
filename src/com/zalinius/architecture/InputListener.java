package com.zalinius.architecture;

import com.zalinius.architecture.input.Clickable;
import com.zalinius.architecture.input.Holding;
import com.zalinius.architecture.input.Inputtable;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InputListener implements Holding, EventHandler<KeyEvent>{

	Map<KeyCode, Inputtable> keyInputs;
	Map<KeyCode, Boolean> keyStates; //pressed or not
	
	Map<Integer, Collection<Clickable>> mouseInputs;


	public InputListener(){
		this.keyInputs = new HashMap<>();
		this.keyStates = new HashMap<>();
		this.mouseInputs = new HashMap<>();
	}
	
	public InputListener(Collection<Inputtable> keyInputs, Collection<Clickable> mouseInputs) {
		this.keyInputs = new HashMap<>();
		keyInputs.forEach(input -> this.keyInputs.put(input.keyCode(), input));

		this.keyStates = new HashMap<>();
		keyInputs.forEach(input -> this.keyStates.put(input.keyCode(), false));
		
		this.mouseInputs = new HashMap<>();
		for (Clickable clickable : mouseInputs) {
			if(!this.mouseInputs.containsKey(clickable.mouseButtonCode() )) {
				this.mouseInputs.put(clickable.mouseButtonCode(), new ArrayList<>());
			}
			
			this.mouseInputs.get(clickable.mouseButtonCode()).add(clickable);
		}
	}
	
	public void addInput(Inputtable input) {
		keyInputs.put(input.keyCode(), input);
		keyStates.put(input.keyCode(), false);
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
	public boolean isHeldDown(int keyCode) {
		return keyStates.get(keyCode);
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
