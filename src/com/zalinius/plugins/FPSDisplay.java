package com.zalinius.plugins;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FPSDisplay extends Plugin {

	private double averageFrameTime;
	
	public FPSDisplay() {
		this.averageFrameTime = 1/30.0;
	}

	@Override
	public void render(GraphicsContext gc) {
		double fps = 1.0/averageFrameTime;
		NumberFormat formatter = new DecimalFormat("#0.00"); 
		String fpsText = formatter.format(fps);
		gc.setStroke(fpsColor(fps));
		gc.strokeText("FPS: " + fpsText, 100.0, 100.0);
	}

	@Override
	public void update(double delta) {
		averageFrameTime = (averageFrameTime * 14.0 / 15.0) + (delta / 15.0);
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
			return Color.YELLOW;
		}else
		{
			return Color.RED;
		}
	}


}
