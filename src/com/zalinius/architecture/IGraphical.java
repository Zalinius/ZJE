package com.zalinius.architecture;

import java.awt.Graphics2D;

public interface IGraphical {

    /**
     * Called every frame. Anything drawn to the screen goes here.
     * @param g The "screen".
     */
    public void render(Graphics2D g);
}
