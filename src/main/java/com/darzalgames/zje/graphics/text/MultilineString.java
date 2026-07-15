package com.darzalgames.zje.graphics.text;

import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.darzalgames.zje.architecture.Graphical;
import com.darzalgames.zje.architecture.Logical;
import com.darzalgames.zje.physics.Point;

/**
 * A multi-line String, which can be rendered in Java AWT.
 * The lines are delimited by newline characters (\n)
 */
public class MultilineString implements Graphical, Logical {

	private final Point position;
	private List<String> textString;
	private final boolean centered;

	private final boolean spooling;
	private float age;
	private static final float CHARACTER_PER_SECOND = 250;

	public MultilineString(Point position, String textString) {
		this(position, textString, true);
	}

	public MultilineString(Point position, String textString, boolean centered) {
		this(position, textString, centered, false);
	}

	public MultilineString(Point position, String textString, boolean centered, boolean spooling) {
		this.position = position;
		setTextString(textString);
		this.centered = centered;
		this.spooling = spooling;
		age = 0;
	}

	@Override
	public void update(double delta) {
		if (spooling) {
			age += delta;
		}
	}

	@Override
	public void render(Graphics2D g) {
		FontMetrics metrics = g.getFontMetrics();

		double greatestWidth = 0;
		double totalHeight = 0;

		int numberOfCharsToShow;
		if (spooling) {
			numberOfCharsToShow = Math.round(age * CHARACTER_PER_SECOND);
		} else {
			numberOfCharsToShow = Integer.MAX_VALUE;
		}

		int totalChars = textString.stream().map(String::length).reduce(0, Integer::sum);
		if (numberOfCharsToShow > totalChars) {
			numberOfCharsToShow = totalChars;
		}

		for (Iterator<String> it = textString.iterator(); it.hasNext();) {
			String string = it.next();

			greatestWidth = Math.max(greatestWidth, metrics.stringWidth(string));
			totalHeight += metrics.getHeight();
		}

		double currentHeight = 0;
		for (Iterator<String> it = textString.iterator(); it.hasNext();) {
			String string = it.next();

			double xText;
			double yText;

			if (centered) {
				xText = position.x - metrics.stringWidth(string) / 2.0;
				yText = position.y - totalHeight / 2 + currentHeight + metrics.getAscent();
			} else {
				xText = position.x;
				yText = position.y + currentHeight + metrics.getAscent();
			}
			currentHeight += metrics.getHeight();

			if (numberOfCharsToShow > string.length()) {
				numberOfCharsToShow -= string.length();
			} else {
				string = string.substring(0, numberOfCharsToShow);
				numberOfCharsToShow -= string.length();
			}

			g.drawString(string, (float) xText, (float) yText);
		}
	}

	public void setTextString(String newTextString) {
		textString = Arrays.asList(newTextString.split("\n"));
	}
}
