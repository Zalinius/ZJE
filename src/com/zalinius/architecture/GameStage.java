package com.zalinius.architecture;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.awt.geom.AffineTransform;

import com.zalinius.architecture.input.Clickable;
import com.zalinius.architecture.input.Inputtable;
import com.zalinius.drawing.camera.Camerable;
import com.zalinius.drawing.camera.StaticCam;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Affine;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameStage extends Application{

	public static final int GAME_WIDTH = 1366, GAME_HEIGHT = 768; //TODO make this changeable
	
	private Graphical graphics;
	private Camerable camera;
	private double currentFPS;
	private static InputListener input;
	

//	
//    public GameStage(Graphical graphics) {
//      //  this(graphics, "Game!", GAME_WIDTH, GAME_HEIGHT, Color.black);
//    }
//    
    public static void main(String[] args) {
    	launch();
	}
    
   // public abstract Graphical getGraphics();
    //public abstract Graphical getLogic();
    

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.initStyle(StageStyle.DECORATED);
		primaryStage.setTitle("Moon Factory 🌙");

		Group root  = new Group();
		Scene scene = new Scene(root);

		primaryStage.setScene(scene);
		Canvas canvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
		root.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		gc.setTransform(new Affine(1, 0, 0, 0, -1, GAME_HEIGHT));
		
		Logical l = new Logical() {
			
			@Override
			public void update(double delta) {
				System.out.println("Meow");
			}
		};
	}
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
//
//	public void addKeys(Collection<Inputtable> keys, Collection<Clickable> clicks){
//    	input = new InputListener(keys, clicks);
//        addKeyListener(input);
//        addMouseListener(input);
//    }
	
	public void setCamera(Camerable camera) {
		this.camera = camera;
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
    
    
	public static boolean isHeld(int keyCode) {
		return input.isHeldDown(keyCode);
	}
	

	public static void addInput(Inputtable keyInput) {
		if(input == null) {
			input = new InputListener();
		}
		input.addInput(keyInput);
	}
	public static void addInput(Clickable mouseInput) {
		if(input == null) {
			input = new InputListener();
		}
		input.addInput(mouseInput);
	}
	
	private static InputListener getInput() {
		if(input == null) {
			input = new InputListener();
		}
		
		return input;
	}

}
