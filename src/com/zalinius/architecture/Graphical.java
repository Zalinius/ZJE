package com.zalinius.architecture;

import javafx.scene.canvas.GraphicsContext;

public interface Graphical {

    /**
     * Called every frame. Anything drawn to the screen goes here.
     * @param g The "screen".
     */
    public void render(GraphicsContext gc);
}
