package com.zalinius.zje.plugins.configuration;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;

import com.zalinius.zje.architecture.GameStage;
import com.zalinius.zje.physics.Point;

public class DontResize extends ScreenStrategy{
	
	@Override
	public void renderInitialize(GameStage gameStage) {
		gameStage.setResizable(false);
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
	}

	@Override
	public Point getGameSize(Dimension currentScreenSize) {
		return new Point(currentScreenSize.getWidth(), currentScreenSize.getHeight());
	}

}
