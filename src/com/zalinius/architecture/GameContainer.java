package com.zalinius.architecture;

import java.util.ArrayList;
import java.util.Collection;

import com.zalinius.architecture.input.Clickable;
import com.zalinius.architecture.input.Inputtable;
import com.zalinius.physics.Point2D;

import javafx.application.Application;
import javafx.stage.Stage;

public abstract class GameContainer extends Application {
	private GameLoop loop;
	private GameStage gameStage;
	//TODO window exit action?
	//TODO adding controls from where?
	

	public void addControls(Collection<Inputtable> keyControls, Collection<Clickable> mouseControls) {
		if(keyControls == null) {
			keyControls = new ArrayList<>();
		}
		if(mouseControls == null) {
			mouseControls = new ArrayList<>();
		}
		gameStage.addKeys(keyControls, mouseControls);
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		gameStage = new GameStage(primaryStage, gameGraphics(), windowSize(), windowTitle());
		loop = new GameLoop(gameLogic(), gameStage);
		loop.start();
		primaryStage.show();
	}
	
	public GameStage gameStage() {
		return gameStage;
	}
	
	public abstract Logical gameLogic();	
	public abstract Graphical gameGraphics();
	public abstract Point2D windowSize();
	public abstract String windowTitle();

}