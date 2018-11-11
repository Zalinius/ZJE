package com.zalinius.architecture;

import java.awt.event.WindowAdapter;
import java.util.ArrayList;
import java.util.Collection;

import com.zalinius.architecture.input.Clickable;
import com.zalinius.architecture.input.Inputtable;
import com.zalinius.drawing.camera.Camerable;

public class GameContainer {
	private GameLoop loop;
	private GameStage stage;

	public GameContainer(Graphical graphics, Logical logic) {
		stage = new GameStage(graphics);
		loop = new GameLoop(stage, logic);
	}
	
	public GameContainer(Graphical graphics, Logical logic, WindowAdapter exitAction ) {
		loop = new GameLoop(new GameStage(graphics), logic);
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
	

}