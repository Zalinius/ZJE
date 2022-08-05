package com.zalinius.zje.plugins.debug;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import com.zalinius.zje.physics.Locatable;
import com.zalinius.zje.physics.Point;
import com.zalinius.zje.plugins.RuntimePlugin;

public class Axes extends RuntimePlugin {
	
	private final Locatable visibleCenter;
	private final int gridSpacing;
	private final int gridLength;
	
	public Axes() {
		this(100);
	}
	
	public Axes(int gridSpacing) {
		this(Point::new, gridSpacing);
	}
	
	public Axes(Locatable visibleCenter, int gridSpacing) {
		this(visibleCenter, gridSpacing, 1000);
	}
	
	public Axes(Locatable visibleCenter, int gridSpacing, int gridLength) {
		this.visibleCenter = visibleCenter;
		this.gridSpacing = gridSpacing;
		this.gridLength = gridLength;
	}
	
	
	@Override
	public void renderBefore(Graphics2D g) {
		int xOffset = (int) visibleCenter.x();
		xOffset -= xOffset%100;
		int yOffset = (int) visibleCenter.y();
		yOffset -= yOffset%100;

		g.setStroke(new BasicStroke(4));
		g.setColor(Color.BLUE);
		g.drawLine(xOffset - gridLength, 0, xOffset + gridLength, 0);
		g.setColor(Color.RED);
		g.drawLine(0, yOffset - gridLength, 0, yOffset + gridLength);
		g.setStroke(new BasicStroke(9));
		g.setColor(Color.WHITE);
		g.drawLine(0, 0, 0, 0);

		g.setStroke(new BasicStroke(1));
		int gridLines = (gridLength / gridSpacing) + 1;
		for (int i = -gridLines*gridSpacing; i <= gridLines*gridSpacing; i+= gridSpacing) {
			g.setColor(new Color(0,  0,  1f,  0.5f));
			Line2D.Double lineX = new Line2D.Double(xOffset - gridLength, yOffset + i, xOffset + gridLength, yOffset + i);
			g.draw(lineX);
			g.setColor(new Color(1f,  0,  0,  0.5f));
			Line2D.Double lineY = new Line2D.Double(xOffset + i, yOffset - gridLength, xOffset + i, yOffset + gridLength);
			g.draw(lineY);
		}
	}

}
