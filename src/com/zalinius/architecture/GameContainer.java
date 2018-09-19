package com.zalinius.architecture;

import java.awt.event.WindowAdapter;

public class GameContainer {
	private GameLoop loop;
	

	public GameContainer(IGraphical graphics, ILogical logic) {
		loop = new GameLoop(new GameStage(graphics), logic);
	}
	
	public GameContainer(IGraphical graphics, ILogical logic, WindowAdapter exitAction ) {
		loop = new GameLoop(new GameStage(graphics), logic);
	}
	
	public void startGame() {
		loop.start();
	}
	

}