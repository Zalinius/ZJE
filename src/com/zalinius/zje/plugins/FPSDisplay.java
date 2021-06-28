package com.zalinius.zje.plugins;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class FPSDisplay extends RuntimePlugin {
	private double averageFrameTime;

	private double secondTimer;
	private double shownFrameTime;

	public FPSDisplay() {
		this.averageFrameTime = 1/60.0;

		this.secondTimer = 1.0;
		this.shownFrameTime = averageFrameTime;
	}

	@Override
	public void updateAfter(double delta) {
		averageFrameTime = (averageFrameTime * 14.0 / 15.0) + (delta / 15.0);
		secondTimer -= delta;
		if(secondTimer <= 0.0) {
			secondTimer += 1.0;
			shownFrameTime = averageFrameTime;
		}
	}

	@Override
	public void renderAfter(Graphics2D g) {
		double fps = 1.0/shownFrameTime;
		NumberFormat formatter = new DecimalFormat("#0"); 
		String fpsText = formatter.format(fps);
		
		g.setFont(new Font("SansSerif", Font.BOLD, 20));
		g.setColor(fpsColor(fps));
		g.drawString(fpsText, 10,  60);
	}

	private static Color fpsColor(double fps) {
		if(fps >= 50) {
			return Color.GREEN;
		}
		else if(fps >= 30) {
			return Color.ORANGE.darker();
		}else
		{
			return Color.RED.darker();
		}
	}


}
