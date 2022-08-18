package com.zalinius.zje.graphics;

import java.awt.Color;

public class ColorUtils {
	
	private ColorUtils() {}
	
	public static Color makeColorWithAlpha(Color color, double alpha) {
		int integerAlpha = (int) (255.0 * (alpha));
		return new Color(color.getRed(), color.getGreen(), color.getBlue(), integerAlpha);

	}
}
