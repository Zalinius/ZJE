package com.zalinius.architecture;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.zalinius.architecture.input.Clickable;
import com.zalinius.architecture.input.InputListener;
import com.zalinius.architecture.input.Inputtable;
import com.zalinius.drawing.camera.Camerable;
import com.zalinius.physics.Point2D;
import com.zalinius.plugins.Plugin;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameStage implements Logical{

	private Canvas canvas;
	private Stage stage;
	private GraphicsContext gc;
	private Graphical graphics;
	private Camerable camera;
	private InputListener input;
	
	private List<Plugin> plugins;
	
	public GameStage(Stage primaryStage, Graphical graphics, Point2D size, String title, List<Plugin> plugins) {
		primaryStage.initStyle(StageStyle.DECORATED);
		primaryStage.setTitle(title);
		stage = primaryStage;
		this.graphics = graphics;

		Group root  = new Group();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		canvas = new Canvas(size.x, size.y);
		root.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		gc.setTransform(defaultCamera().getTransform(new Point2D()));
		
		input = new InputListener();
		scene.setOnKeyPressed(input);
		scene.setOnKeyReleased(input);

		camera = defaultCamera();
		
		this.plugins = plugins;
	}
	
	public void setCamera(Camerable camera) {
		this.camera = camera;
	}
	
	public Camerable defaultCamera() {
		return new Camerable() {
			@Override
			public Affine getTransform(Point2D canvasSize) {
				return new Affine(1, 0, 0, 0, 1, 0);
			}
		};
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

	public void addKeys(Collection<Inputtable> keys, Collection<Clickable> clicks){
		Iterator<Inputtable> keysIt = keys.iterator();
		while (keysIt.hasNext()) {
			Inputtable inputtable = keysIt.next();
			input.addInput(inputtable);
		}
		
		Iterator<Clickable> mouseIt = clicks.iterator();
		while (mouseIt.hasNext()) {
			Clickable clickable = mouseIt.next();
			input.addInput(clickable);
		}
		
    }

	public void addInput(Inputtable keyInput) {
		if(input == null) {
			input = new InputListener();
		}
		input.addInput(keyInput);
	}
	public void addInput(Clickable mouseInput) {
		if(input == null) {
			input = new InputListener();
		}
		input.addInput(mouseInput);
	}

	public void render() {
		Affine cameraTransform = camera.getTransform(new Point2D(width(), height()));
		gc.setTransform(cameraTransform);
		
        gc.clearRect(0,0,width(), height());
		graphics.render(gc);
		
		for (Iterator<Plugin> iterator = plugins.iterator(); iterator.hasNext();) {
			Plugin plugin = iterator.next();
			plugin.render(gc);
		}
	}

	@Override
	public void update(double delta) {
		input.update(delta);
	}

}
