package com.zalinius.zje.plugins;

import java.awt.Color;
import java.awt.Graphics2D;

public class BackgroundColor extends Plugin {
	private Color backgroundColor;

	public BackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
	@Override
	public void renderBefore(Graphics2D g) {
		g.setBackground(backgroundColor);
		g.clearRect(0, 0, 1920, 1080); //TODO obtain height and width properly
	}
}
