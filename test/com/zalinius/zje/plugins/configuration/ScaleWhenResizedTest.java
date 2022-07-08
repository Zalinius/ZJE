package com.zalinius.zje.plugins.configuration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Dimension;

import org.junit.jupiter.api.Test;

import com.zalinius.zje.physics.Point;

public class ScaleWhenResizedTest {
	
	@Test
	void getGameSize_whenGameAtOriginalSquareSize_returnsOriginalSquareSize() throws Exception {
		ScaleWhenResized scaleWhenResized = makeScaleWhenResized(1000, 1000);
		Dimension permanentGameSize = new Dimension(1000, 1000);
		
		Point gameSize = scaleWhenResized.getGameSize(permanentGameSize);
		
		assertEquals(new Point(1000.0, 1000.0), gameSize);
	}
	
	@Test
	void getGameSize_whenGameAtOriginalSquareSizeWidenedToRectangle_returnsRectangleSize() throws Exception {
		ScaleWhenResized scaleWhenResized = makeScaleWhenResized(1000, 1000);
		Dimension widerGameSize = new Dimension(2000, 1000);
		
		Point gameSize = scaleWhenResized.getGameSize(widerGameSize);
		
		assertEquals(new Point(2000.0, 1000.0), gameSize);
	}
	
	@Test
	void getGameSize_whenGameAtOriginalSquareSizeThinnedToRectangle_returnsRectangleSize() throws Exception {
		ScaleWhenResized scaleWhenResized = makeScaleWhenResized(1000, 1000);
		Dimension thinnerGameSize = new Dimension(1000, 500);
		
		Point gameSize = scaleWhenResized.getGameSize(thinnerGameSize);
		
		assertEquals(new Point(2000.0, 1000.0), gameSize);
	}
	
	

	private static ScaleWhenResized makeScaleWhenResized(int x0, int y0) {
		ScaleWhenResized scaleWhenResized = new ScaleWhenResized(new Dimension(x0, y0));
						
		return scaleWhenResized;		
	}
}
