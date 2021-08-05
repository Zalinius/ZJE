package com.zalinius.zje.plugins;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.function.Supplier;

public class Background extends RuntimePlugin {
	private Color backgroundColor;
	private Supplier<Dimension> resolutionSupplier;

	public Background(Color backgroundColor, Supplier<Dimension> resolutionSupplier) {
		this.backgroundColor = backgroundColor;
		this.resolutionSupplier = resolutionSupplier;
	}
	
	@Override
	public void renderBefore(Graphics2D g) {
		g.setBackground(backgroundColor);
		Dimension resolution = resolutionSupplier.get();
		g.clearRect(0, 0, resolution.width, resolution.height);
	}
}
