package com.zalinius.architecture;

import java.util.ArrayList;
import java.util.Collection;

import com.zalinius.architecture.input.Clickable;
import com.zalinius.architecture.input.Inputtable;
import com.zalinius.drawing.camera.Camerable;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class GameContainer extends Application {
	private AnimationTimer loop;
	//TODO window exit action?
	//TODO adding controls from where?
	
	public void addControls(Collection<Inputtable> keyControls, Collection<Clickable> mouseControls) {
		if(keyControls == null) {
			keyControls = new ArrayList<>();
		}
		if(mouseControls == null) {
			mouseControls = new ArrayList<>();
		}
		//stage.addKeys(keyControls, mouseControls);
	}
	
	public void setCamera(Camerable camera) {
		//TODO adding a camera?
		//stage.setCamera(camera);
	}
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		primaryStage.initStyle(StageStyle.DECORATED);
		primaryStage.setTitle("Moon Factory 🌙");

		Group root  = new Group();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		Canvas canvas = new Canvas(500, 800);
		root.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		loop = new GameLoop(gameGraphics(), gameLogic(), gc);
		loop.start();
		primaryStage.show();
	}
	
	public abstract Logical gameLogic();	
	public abstract Graphical gameGraphics();	

}