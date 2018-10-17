package com.zalinius.architecture;

import java.awt.event.WindowAdapter;
import java.util.Collection;

import com.zalinius.architecture.input.Inputtable;
import com.zalinius.drawing.camera.Camerable;

public class GameContainer {
	private GameLoop loop;
	private GameStage stage;

	public GameContainer(IGraphical graphics, ILogical logic) {
		stage = new GameStage(graphics);
		loop = new GameLoop(stage, logic);
	}
	
	public GameContainer(IGraphical graphics, ILogical logic, WindowAdapter exitAction ) {
		loop = new GameLoop(new GameStage(graphics), logic);
	}
	
	public void addControls(Collection<Inputtable> controls) {
		stage.addKeys(controls);
	}
	
	public void setCamera(Camerable camera) {
		stage.setCamera(camera);
	}
	
	public void startGame() {
		loop.start();
	}
	

}