package com.zalinius.architecture;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.util.Collection;

import com.zalinius.architecture.input.Inputtable;
import com.zalinius.drawing.camera.Camerable;
import com.zalinius.drawing.camera.StaticCam;

public class GameStage extends DoubleBufferedFrame{
	private static final long serialVersionUID = 1L;

	public static final int GAME_WIDTH = 1366, GAME_HEIGHT = 768; //TODO make this changeable
	
	private IGraphical graphics;
	private Camerable camera;
	private double currentFPS;
	private static InputListener input;

    public GameStage(IGraphical graphics) {
        this(graphics, "Game!", GAME_WIDTH, GAME_HEIGHT, Color.black);
    }

    public GameStage(IGraphical graphics, String windowText, int width, int height, Color backgroundColor) {
    	super(windowText);
    	this.graphics = graphics;
        setResizable(false);
        setSize(width, height);
        setVisible(true);
        setBackground(backgroundColor);
        addWindowListener(defaultCloseAction());
        addKeyListener(new InputListener());
        this.camera = new StaticCam();
    }
    
    public GameStage(IGraphical graphics, String windowText, int width, int height, Color backgroundColor, WindowAdapter closeAction, Camerable camera) {
    	super(windowText);
    	this.graphics = graphics;
        setResizable(false);
        setSize(width, height);
        setVisible(true);
        setBackground(backgroundColor);
        addWindowListener(closeAction);
        addKeyListener(new InputListener());
        this.camera = camera;
    }
    
    public void paintBuffer(Graphics2D g){
    	g.setTransform(camera.getTransform());
    	graphics.render(g);
    	g.setColor(fpsColor());
    	g.setFont(new Font("SansSerif", Font.BOLD, 20));
    	g.drawString(Integer.toString((int)currentFPS), 10, 50);
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

	public void addKeys(Collection<Inputtable> keys){
    	input = new InputListener(keys);
        addKeyListener(input);
    }
    
	public static boolean isHeld(int keyCode) {
		return input.isHeldDown(keyCode);
	}
	
	public void setCamera(Camerable camera) {
		this.camera = camera;
	}
	
    public void setFPS(double fps) {
    	currentFPS = fps;
    }
    
    private WindowAdapter defaultCloseAction() {
    	return new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        };
    }
}
