package com.zalinius.zje.graphics.text;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.zalinius.zje.architecture.Graphical;
import com.zalinius.zje.physics.Point;

/**
 * A multi-line String, which can be rendered in Java AWT.
 * The lines are delimited by newline characters (\n)
 */
public class MultilineString implements Graphical{

	private Point center;
	private List<String> textString;

	public MultilineString(Point center, String textString) {
		this.center = center;
		setTextString(textString);
	}

	@Override
	public void render(Graphics2D g) {
		FontMetrics metrics = g.getFontMetrics();

		double greatestWidth = 0;
		double totalHeight = 0;

		for (Iterator<String> it = textString.iterator(); it.hasNext();) {
			String string = it.next();

			greatestWidth = Math.max(greatestWidth, metrics.stringWidth(string));
			totalHeight += metrics.getHeight();
		}

		double currentHeight = 0;
		for (Iterator<String> it = textString.iterator(); it.hasNext();) {
			String string = it.next();

			double xText = center.x - metrics.stringWidth(string) / 2.0  ;				
			double yText = center.y - totalHeight/2 + currentHeight + metrics.getAscent();

			currentHeight += metrics.getHeight();

			g.drawString(string,(float) xText,(float) yText);
		}
	}

	public void setTextString(String newTextString) {
		this.textString = Arrays.asList(newTextString.split("\n"));
	}
}
