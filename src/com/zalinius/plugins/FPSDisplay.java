package com.zalinius.plugins;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FPSDisplay extends Plugin {

	private double averageFrameTime;
	
	private double secondTimer;
	private double shownFrameTime;
	
	public FPSDisplay() {
		this.averageFrameTime = 1/60.0;
		
		this.secondTimer = 1.0;
		this.shownFrameTime = averageFrameTime;
	}

	@Override
	public void render(GraphicsContext gc) {
		double fps = 1.0/shownFrameTime;
		NumberFormat formatter = new DecimalFormat("#0"); 
		String fpsText = formatter.format(fps);
		gc.setStroke(fpsColor(fps));
		gc.strokeText("FPS: " + fpsText, 1, 11);
	}

	@Override
	public void update(double delta) {
		averageFrameTime = (averageFrameTime * 14.0 / 15.0) + (delta / 15.0);
		secondTimer -= delta;
		if(secondTimer <= 0.0) {
			secondTimer += 1.0;
			shownFrameTime = averageFrameTime;
		}
	}

	@Override
	public Type getType() {
		return Type.AFTER;
	}
	

    private Color fpsColor(double fps) {
		if(fps >= 50) {
			return Color.GREEN;
		}
		else if(fps >= 30) {
			return Color.DARKORANGE;
		}else
		{
			return Color.DARKRED;
		}
	}
}
