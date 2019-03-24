package com.zalinius.architecture;

import java.awt.*;
import java.util.Collection;
import java.util.Iterator;
import com.zalinius.architecture.input.Clickable;
import com.zalinius.architecture.input.InputListener;
import com.zalinius.architecture.input.Inputtable;
import com.zalinius.drawing.camera.Camerable;
import com.zalinius.physics.Point2D;

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
	
	public GameStage(Stage primaryStage, Graphical graphics, Point2D size, String title) {
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
		gc.setTransform(defaultCamera().getTransform());
		
		input = new InputListener();
		scene.setOnKeyPressed(input);
		scene.setOnKeyReleased(input);

		camera = defaultCamera();
	}
	
	public void setCamera(Camerable camera) {
		this.camera = camera;
	}
	
	public Camerable defaultCamera() {
		return new Camerable() {
			@Override
			public Affine getTransform() {
				return new Affine(1, 0, 0, 0, -1, height());
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
	
	private double currentFPS;
	

//
//    public GameStage(Graphical graphics, String windowText, int width, int height, Color backgroundColor) {
//    	super(windowText);
//    	this.graphics = graphics;
//        setResizable(false);
//        setSize(width, height);
//        setVisible(true);
//        setBackground(backgroundColor);
//        addWindowListener(defaultCloseAction());
//        addKeyListener(getInput());
//        addMouseListener(getInput());
//        this.camera = new StaticCam();
//    }
//    
//    public GameStage(Graphical graphics, String windowText, int width, int height, Color backgroundColor, WindowAdapter closeAction, Camerable camera) {
//    	super(windowText);
//    	this.graphics = graphics;
//        setResizable(false);
//        setSize(width, height);
//        setVisible(true);
//        setBackground(backgroundColor);
//        addWindowListener(closeAction);
//        addKeyListener(getInput());
//        addMouseListener(getInput());
//        this.camera = camera;
//    }
//    
    public void paintBuffer(Graphics2D g){
//    	AffineTransform trans = camera.getTransform();
//    	g.setTransform(trans);
//
//    	//graphics.render(g);
//    	
//    	g.setColor(fpsColor());
//    	g.setFont(new Font("SansSerif", Font.BOLD, 20));
//    	
//    	float offSetX = (float) trans.getTranslateX();
//    	float offSetY = (float) trans.getTranslateY();
//    	g.drawString(Integer.toString((int)currentFPS), 10 - offSetX, 50 - offSetY);
      }

    private Color fpsColor() {
		if(currentFPS >= 50) {
			return Color.GREEN;
		}
		else if(currentFPS >= 30) {
			return Color.YELLOW;
		}else
		{
			return Color.RED;
		}
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
	
	
    public void setFPS(double fps) {
    	currentFPS = fps;
    }
//    
//    private WindowAdapter defaultCloseAction() {
//    	return new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//                dispose();
//                System.exit(0);
//            }
//        };
//    }

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
		gc.setTransform(camera.getTransform());
		graphics.render(gc);
	}

	@Override
	public void update(double delta) {
		input.update(delta);
	}

}
