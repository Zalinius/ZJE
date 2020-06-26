package com.zalinius.architecture.input;

import com.zalinius.architecture.Logical;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class InputListener implements EventHandler<KeyEvent>, Logical{

	Map<KeyCode, Inputtable> keyInputs;
	Map<KeyCode, Boolean> keyStates; //pressed or not

	Map<Integer, Collection<Clickable>> mouseInputs;


	public InputListener(){
		this(new ArrayList<>(), new ArrayList<>());
	}

	public InputListener(Collection<Inputtable> keyInputs, Collection<Clickable> mouseInputs) {
		this.keyInputs = new HashMap<>();
		this.keyStates = new HashMap<>();

		for (Iterator<Inputtable> iterator = keyInputs.iterator(); iterator.hasNext();) {
			Inputtable inputtable = iterator.next();
			this.keyInputs.put(inputtable.keyCode(), inputtable);
			this.keyStates.put(inputtable.keyCode(), false);


		}

		this.mouseInputs = new HashMap<>();
		for (Clickable clickable : mouseInputs) {
			if(!this.mouseInputs.containsKey(clickable.mouseButtonCode() )) {
				this.mouseInputs.put(clickable.mouseButtonCode(), new ArrayList<>());
			}

			this.mouseInputs.get(clickable.mouseButtonCode()).add(clickable);
		}
	}

	public void addInput(Inputtable input) {
			this.keyInputs.put(input.keyCode(), input);
			this.keyStates.put(input.keyCode(), false);

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
