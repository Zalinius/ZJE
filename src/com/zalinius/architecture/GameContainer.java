package com.zalinius.architecture;

import java.util.ArrayList;
import java.util.Collection;

import com.zalinius.architecture.input.Clickable;
import com.zalinius.architecture.input.Inputtable;
import com.zalinius.drawing.camera.Camerable;
import com.zalinius.physics.Point2D;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class GameContainer extends Application {
	private AnimationTimer loop;
	//TODO window exit action?
	//TODO adding controls from where?
	
	//Graphics
	Canvas canvas;
	Stage stage;
	GraphicsContext gc;
	
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
		gc.setTransform(camera.getTransform());
	}
	
	public Camerable defaultCamera() {
		return new Camerable() {
			@Override
			public Affine getTransform() {
				return new Affine(1, 0, 0, -1, 0, height());
			}
		};
	}
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.initStyle(StageStyle.DECORATED);
		primaryStage.setTitle("Game");
		stage = primaryStage;

		Group root  = new Group();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		canvas = new Canvas(windowSize().x, windowSize().y);
		root.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		gc.setTransform(defaultCamera().getTransform());
		
		loop = new GameLoop(gameGraphics(), gameLogic(), gc);
		loop.start();
		primaryStage.show();
	}
	
	public void resizeWindow(double width, double height) {
		canvas.setHeight(height);
		canvas.setWidth(width);
		stage.sizeToScene();
	}
	
	public void resizeAndScale(double width, double height) {
		double xRatio = width / width();
		double yRatio = height / height();
		resizeWindow(width, height);
		gc.transform(xRatio, 0, 0, yRatio, 0, 0);
	}
	
	public double width() {
		return canvas.getWidth();
	}
	
	public double height() {
		return canvas.getHeight();
	}
	
	public abstract Logical gameLogic();	
	public abstract Graphical gameGraphics();
	public abstract Point2D windowSize();

}