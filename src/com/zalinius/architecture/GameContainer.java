package com.zalinius.architecture;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.Collection;

import com.zalinius.architecture.input.Clickable;
import com.zalinius.architecture.input.Inputtable;
import com.zalinius.drawing.camera.Camerable;
import com.zalinius.physics.Vector;

public abstract class GameContainer implements Graphical, Logical {
	private GameLoop loop;
	private GameStage stage;

	public GameContainer(String windowText, int width, int height, Color backgroundColor) {
		stage = new GameStage(this, windowText, width, height, backgroundColor);
		loop = new GameLoop(stage, this);
	}
	
	public void addControls(Collection<Inputtable> keyControls, Collection<Clickable> mouseControls) {
		if(keyControls == null) {
			keyControls = new ArrayList<>();
		}
		if(mouseControls == null) {
			mouseControls = new ArrayList<>();
		}
		stage.addKeys(keyControls, mouseControls);
	}
	
	public void setCamera(Camerable camera) {
		stage.setCamera(camera);
	}
	
	public abstract Vector resolution();
	
	public void startGame() {
		loop.start();
	}

	public void exit() {
		stage.dispose();
		System.exit(0);
	}
	
	public Locatable mouseLocator() {
		return stage.mouseLocator();
	}
	

}