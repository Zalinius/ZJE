package com.zalinius.drawing;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Background {
	Color backgroundColor;
	
    public void drawBackground(GraphicsContext gc){
        gc.setFill(backgroundColor);
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }
}
