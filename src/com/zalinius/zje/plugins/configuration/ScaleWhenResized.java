package com.zalinius.zje.plugins.configuration;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;

import com.zalinius.zje.architecture.GameStage;
import com.zalinius.zje.physics.Point;

public class ScaleWhenResized extends ScreenStrategy{
	
	private double scale;
	private Dimension originalSize;

	public ScaleWhenResized() {
		this.scale = 1;
	}

	public ScaleWhenResized(Dimension originalSize) {
		this.scale = 1;
		this.originalSize = new Dimension(originalSize);
	}

	@Override
	public void renderInitialize(GameStage gameStage) {
		gameStage.setResizable(true);
		gameStage.addComponentListener(this);
		originalSize = gameStage.getPreferredSize();
	}
	
	@Override
	public void renderBefore(Graphics2D g) {
		g.scale(scale, scale);
	}
	
	@Override
	public void componentResized(ComponentEvent e) {
		double originalAspectRatio = originalSize.getWidth() / originalSize.getHeight();
		Dimension changedSize = e.getComponent().getSize();
		double newAspectRatio = changedSize.getWidth() / changedSize.getHeight();
		if(newAspectRatio > originalAspectRatio) {
			scale = changedSize.getHeight() / originalSize.getHeight();			
		}
		else {
			scale = changedSize.getWidth() / originalSize.getWidth();
		}
		
	}
	
	@Override
	public void componentMoved(ComponentEvent e) {
		//Do Nothing
	}

	@Override
	public void componentShown(ComponentEvent e) {
		//Do Nothing
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		//Do Nothing
	}

	@Override
	public Point getGameSize(Dimension currentScreenSize) {
		double originalAspectRatio = originalSize.getWidth() / originalSize.getHeight();
		double newAspectRatio = currentScreenSize.getWidth() / currentScreenSize.getHeight();
		if(newAspectRatio > originalAspectRatio) {
			double relativeScale = newAspectRatio/originalAspectRatio;	
			return new Point(originalSize.getWidth() * relativeScale, originalSize.getHeight());
		}
		else {
			double relativeScale = originalAspectRatio/ newAspectRatio;
			return new Point(originalSize.getWidth(), originalSize.getHeight() * relativeScale);
		}

		
	}


}
