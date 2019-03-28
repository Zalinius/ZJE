package com.zalinius.architecture;

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
import javafx.scene.paint.Color;
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
		gc.setTransform(defaultCamera().getTransform(new Point2D()));
		
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
			public Affine getTransform(Point2D canvasSize) {
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
		Affine cameraTransform = camera.getTransform(new Point2D(width(), height()));
		//System.out.println("1:" + cameraTransform);
		cameraTransform.setMyy(cameraTransform.getMyy() * -1);
		cameraTransform.setTy(height() - cameraTransform.getTy());
		gc.setTransform(cameraTransform);
		
    	double offSetX = cameraTransform.getTx();
    	double offSetY = height() - cameraTransform.getTy();
    	//System.out.println("Clearing rectangle: " + -offSetX + " " + -offSetY + ", width height " + width() + " " + height());
        gc.clearRect(-offSetX , -offSetY,width(), height());
        gc.setFill(Color.BLACK);
        gc.fillRect(-offSetX, -offSetY, width(), height());
        
        gc.setStroke(Color.WHITE);
        gc.strokeLine(-1000, 0, 1000, 0);
        gc.strokeLine(0, -1000, 0, 1000);
		//System.out.println("2:" + cameraTransform);
		//System.out.println();
		

		gc.setFill(Color.YELLOW);
		graphics.render(gc);
	}

	@Override
	public void update(double delta) {
		input.update(delta);
	}

}
