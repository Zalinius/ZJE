package com.zalinius.architecture;

import com.zalinius.architecture.input.Clickable;
import com.zalinius.architecture.input.Holding;
import com.zalinius.architecture.input.Inputtable;

import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class InputListener extends KeyAdapter implements Holding, MouseListener, MouseMotionListener, Locatable{

	HashMap<Integer, Inputtable> keyInputs;
	HashMap<Integer, Boolean> keyStates; //pressed or not
	
	HashMap<Integer, Collection<Clickable>> mouseInputs;
	
	private int mouseX, mouseY;


	public InputListener(){
		this.keyInputs = new HashMap<>();
		this.keyStates = new HashMap<>();
		this.mouseInputs = new HashMap<>();
		mouseX = mouseY = 0;
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
	}
	
	public void addInput(Clickable click) {
		if(!this.mouseInputs.containsKey(click.mouseButtonCode() )) {
			this.mouseInputs.put(click.mouseButtonCode(), new ArrayList<>());
		}	
		mouseInputs.get(click.mouseButtonCode()).add(click);
	}


	private void keyPressSwitchBoard(int keyCode, boolean press){
		if(keyInputs.containsKey(keyCode)) {
			if(press) {
				if(!keyStates.get(keyCode)) {
					Inputtable input = keyInputs.get(keyCode);
					input.pressed();
					keyStates.put(keyCode, true);
				}
			}
			else {
				Inputtable input = keyInputs.get(keyCode);
				input.released();
				keyStates.put(keyCode, false);
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
	public boolean isHeldDown(int keyCode) {
		return keyStates.get(keyCode);
	}
	
	

	@Override
	public void mouseClicked(MouseEvent event) {
		if(mouseInputs.containsKey(event.getButton())) {
			for (Clickable clickable : mouseInputs.get(event.getButton())) {
				if(clickable.clickArea().contains(event.getPoint())) {
					clickable.mouseClicked();
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent event) {
		if(mouseInputs.containsKey(event.getButton())) {
			for (Clickable clickable : mouseInputs.get(event.getButton())) {
				if(clickable.clickArea().contains(event.getPoint())) {
					clickable.mousePressed();
				}
			}
		}		
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		if(mouseInputs.containsKey(event.getButton())) {
			for (Clickable clickable : mouseInputs.get(event.getButton())) {
		//todo		if(clickable.clickArea().contains(event.getPoint())) {
					clickable.mouseReleased();
				//}
			}
		}				
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point mouse = e.getPoint();
		mouseX = mouse.x;
		mouseY = mouse.y;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Point mouse = e.getPoint();
		mouseX = mouse.x;
		mouseY = mouse.y;
	}

	@Override
	public com.zalinius.physics.Point center() {
		return new com.zalinius.physics.Point(mouseX, mouseY);
	}

}
