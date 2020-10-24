package com.zalinius.architecture;

import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.Collection;

import com.zalinius.architecture.input.Clickable;
import com.zalinius.architecture.input.Inputtable;
import com.zalinius.drawing.camera.Camerable;

public abstract class GameContainer implements Graphical, Logical {
	private GameLoop loop;
	private GameStage stage;

	public GameContainer() {
		stage = new GameStage(this);
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