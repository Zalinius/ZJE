package com.zalinius.zje.plugins.debug;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.function.Supplier;

import com.zalinius.zje.physics.Locatable;
import com.zalinius.zje.physics.Point;
import com.zalinius.zje.plugins.RuntimePlugin;

public class Axes extends RuntimePlugin {
	
	private final Locatable visibleCenter;
	private final int gridSpacing;
	private final int gridLength;
	private final int gridThickness;
	private final Supplier<Color> xColor;
	private final Supplier<Color> yColor;
	
	
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
		this(visibleCenter, gridSpacing, gridLength, 2, () -> Color.BLUE, () -> Color.RED);
	}
	
	public Axes(Locatable visibleCenter, int gridSpacing, int gridLength, int gridThickness, Supplier<Color> xColor, Supplier<Color> yColor) {
		this.visibleCenter = visibleCenter;
		this.gridSpacing = gridSpacing;
		this.gridLength = gridLength;
		this.gridThickness = gridThickness;
		this.xColor = xColor;
		this.yColor = yColor;
	}
	
	
	@Override
	public void renderBefore(Graphics2D g) {
		int xOffset = (int) visibleCenter.x();
		xOffset -= xOffset%100;
		int yOffset = (int) visibleCenter.y();
		yOffset -= yOffset%100;

		g.setStroke(new BasicStroke(2*gridThickness));
		g.setColor(xColor.get());
		g.drawLine(xOffset - gridLength, 0, xOffset + gridLength, 0);
		g.setColor(yColor.get());
		g.drawLine(0, yOffset - gridLength, 0, yOffset + gridLength);
		g.setStroke(new BasicStroke(8));
		g.setColor(Color.WHITE);
		g.drawLine(0, 0, 0, 0);

		g.setStroke(new BasicStroke(gridThickness));
		int gridLines = (gridLength / gridSpacing) + 1;
		for (int i = -gridLines*gridSpacing; i <= gridLines*gridSpacing; i+= gridSpacing) {
			g.setColor(xColor.get());
			Line2D.Double lineX = new Line2D.Double(xOffset - gridLength, yOffset + i, xOffset + gridLength, yOffset + i);
			g.draw(lineX);
			g.setColor(yColor.get());
			Line2D.Double lineY = new Line2D.Double(xOffset + i, yOffset - gridLength, xOffset + i, yOffset + gridLength);
			g.draw(lineY);
		}
	}

}
